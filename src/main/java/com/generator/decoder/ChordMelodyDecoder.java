package com.generator.decoder;

import com.generator.melody.Config;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChordMelodyDecoder {

    public static String INVALID = "invalid";
    private Config config;
    private PitchCorrection pitchCorrection;

    public ChordMelodyDecoder(Config config){
        this.config = config;
        this.pitchCorrection = new PitchCorrection();
    }

    public String extractValidChords(String seq) {
        //#31:310-610%310*610*310*610
        if(count(seq, "*") != 3) {
            return INVALID;
        }

        if(count(seq, "#") != 1) {
            return INVALID;
        }

        if(count(seq, "-") != 1) {
            return INVALID;
        }

        if(count(seq, "%") != 1) {
            return INVALID;
        }

        return seq;
    }

    public String extractValidMelody(String seq) {
        //"31*313*715*3150-3151-3151-3151-3151-3151-3151-3151-2140-2141-2141-2141-7140-7141-7141-7141"

        //must be greater than 89 chars
        if (seq.length() < 354) {
            return INVALID;
        }
        if (seq.length() > 354) {
            seq = seq.substring(0, 354);
        }

        if (StringUtils.countMatches(seq, "^") != 1) {
            return INVALID;
        }

        if (StringUtils.countMatches(seq, "*") != 8) {
            return INVALID;
        }

        if (StringUtils.countMatches(seq, "+") != 3) {
            return INVALID;
        }

        if (StringUtils.countMatches(seq, "-") != 60) {
            return INVALID;
        }

        //check prefix
        String prefix = seq.substring(3, 35);
        String[] chords = prefix.split("\\*");
        if (chords.length != 8) {
            return INVALID;
        }

        for (String chord : chords) {
            if (chord.length() != 3) {
                return INVALID;
            }
        }

        //check suffix
        String suffix = seq.substring(35, seq.length());
        String[] bars = suffix.split("\\+");
        for (String bar : bars) {
            String[] barNotes = bar.split("-");
            if (barNotes.length != 16) {
                return INVALID;
            }

            for (String note : barNotes) {
                if (note.length() != 4) {
                    return INVALID;
                }
            }
        }

        return seq;
    }

    public String decodeChords(String encoded) {
        String prefix = encoded.substring(3, 34);
        System.out.println("CHORDS : " + prefix);

        List<String> jFugueChords = new ArrayList<>();
        String[] chordStrings = prefix.split("\\*");
        for(String cStr : chordStrings){
            String noteName = getNoteName(cStr.substring(0,2));
            String chordType = getChordType(cStr.substring(2,3));
            jFugueChords.add(noteName + chordType + "h ");
        }

        return String.join(" ",jFugueChords);
    }

    public String decodeBassline(String encoded) {
        String prefix = encoded.substring(3, 34);
        System.out.println("CHORDS : " + prefix);

        List<String> jFugueChords = new ArrayList<>();
        String[] chordStrings = prefix.split("\\*");
        for(String cStr : chordStrings){
            String noteName = getNoteName(cStr.substring(0,2));
            String chordType = getChordType(cStr.substring(2,3));
            jFugueChords.add(noteName + "2q " + noteName + "2q ");
        }

        return String.join(" ",jFugueChords);
    }

    public String getChordType(String chordCode){

        switch (chordCode){
            case "0":
                return "maj";
            case "1":
                return "min";
            case "2":
                return "dim";
            case "3":
                return "maj7";
            case "4":
                return "min7";
            case "5":
                return "dom7";
            case "6":
                return "dim";
        }

        throw new RuntimeException("ERROR");
    }

    public String decodeMelody(String encoded, String key) {
        List<Measure> measures = new ArrayList<>();
        //check suffix
        String suffix = encoded.substring(35, encoded.length());
        String[] bars = suffix.split("\\+");
        for (String bar : bars) {
            String[] barNotes = bar.split("-");

            Measure measure = new Measure();
            measure.setNotes(Arrays.asList(barNotes));
            measures.add(measure);
        }

        String jFugueStr = measureToJFugueString(measures);

        System.out.println("BEFORE FORCE CORRECT " + jFugueStr);
        if(config.shouldForceCorrectPitches()){
            System.out.println("IN FORCE CORRECT");
            System.out.println("FUCK " + jFugueStr);
            String[] barNotes = pitchCorrection.forceCorrectPitches(jFugueStr.split(" "), getKey(encoded));
            jFugueStr = String.join(" ", barNotes);
            System.out.println("FUCK " + jFugueStr);
        }
        System.out.println("AFtER FORCE CORRECT " + jFugueStr);

        return jFugueStr;
    }

    class NoteDuration {
        public String note;
        public String octave;
        public int duration;
    }

    private String measureToJFugueString(List<Measure> measures) {

        String jfugueString = "";

        List<NoteDuration> noteDurations = new ArrayList<>();

        for (Measure measure : measures) {

            NoteDuration noteDuration = null;
            for (int i = 0; i < measure.getNotes().size(); i++) {

                if (measure.getNotes().get(i).substring(3, 4).equalsIgnoreCase("0") ) {
                    noteDuration = new NoteDuration();
                    noteDuration.note = getNoteName(measure.getNotes().get(i).substring(0, 2));
                    noteDuration.octave = measure.getNotes().get(i).substring(2, 3);
                    noteDuration.duration = 1;
                    noteDurations.add(noteDuration);
                } else if (measure.getNotes().get(i).substring(3, 4).equalsIgnoreCase("1")) {
                    noteDuration.duration = noteDuration.duration + 1;
                }
            }
        }

        for (NoteDuration noteDuration : noteDurations) {
            String durationStr = extractDuration(noteDuration.duration);
            String note = noteDuration.note;
            String octave = noteDuration.octave;
            if(note.equalsIgnoreCase("r")){
                octave = "";
            }
            jfugueString = jfugueString + note + octave + durationStr + " ";
        }

        return jfugueString;
    }

    public String extractDuration(int duration) {
        switch (duration) {
            case 1:
                return "s";
            case 2:
                return "i";
            case 3:
                return "i.";
            case 4:
                return "q";
            case 6:
                return "q.";
            case 8:
                return "h";
            case 12:
                return "h.";
            case 16:
                return "w";
        }

        throw new RuntimeException("cannot extract duration: " + duration);
    }

    public String getNoteName(String encoded) {
        switch (encoded) {
            case "00":
                return "r";
            case "01":
                return "r";
            case "02":
                return "r";
            case "10":
                return "g#";
            case "11":
                return "a";
            case "12":
                return "a#";
            case "20":
                return "a#";
            case "21":
                return "b";
            case "31":
                return "c";
            case "32":
                return "c#";
            case "40":
                return "c#";
            case "41":
                return "d";
            case "42":
                return "d#";
            case "50":
                return "d#";
            case "51":
                return "e";
            case "61":
                return "f";
            case "62":
                return "f#";
            case "70":
                return "f#";
            case "71":
                return "g";
            case "72":
                return "g#";
        }

        throw new RuntimeException("cannot decode: " + encoded);
    }

    public String getKey(String encoded){
        String encodedKey = encoded.split("\\^")[0];
        return getNoteName(encodedKey);
    }

    class Measure {
        private String key;
        private String chordOne;
        private String chordTwo;
        private List<String> notes = new ArrayList<>();

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getChordOne() {
            return chordOne;
        }

        public void setChordOne(String chordOne) {
            this.chordOne = chordOne;
        }

        public String getChordTwo() {
            return chordTwo;
        }

        public void setChordTwo(String chordTwo) {
            this.chordTwo = chordTwo;
        }

        public List<String> getNotes() {
            return notes;
        }

        public void setNotes(List<String> notes) {
            this.notes = notes;
        }
    }

    public int count(String text, String find) {
        int index = 0, count = 0, length = find.length();
        while( (index = text.indexOf(find, index)) != -1 ) {
            index += length; count++;
        }
        return count;
    }
}
