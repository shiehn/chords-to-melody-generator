package com.keypath.graph;

import com.keypath.server.model.Link;
import com.keypath.server.model.RenderData;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.player.Player;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class ChordGraph {

    private KeyMembers keyMembers;
    private ChordPaths chordPaths;
    private ChordEdges chordEdges;

    public ChordGraph() {
        this.keyMembers = new KeyMembers();
        this.chordPaths = new ChordPaths();
        this.chordEdges = new ChordEdges();
    }

    public RenderData createGraph() {

        System.out.println("0 KEY = " + this.keyMembers.getById(0).Key + " CHORD = " + this.keyMembers.getById(0).Chord);
        System.out.println("71 KEY = " + this.keyMembers.getById(70).Key + " CHORD = " + this.keyMembers.getById(70).Chord);

        List<Link> edges = this.chordEdges.createEdges(this.keyMembers.getKeyNoteMap());

        RenderData renderData = new RenderData(this.keyMembers.getAllKeys(), edges);

        List<Link> chordPath = this.chordPaths.generateChordPath(edges, this.keyMembers);

        edges.addAll(chordPath);

        System.out.println("************************************************************************");
        System.out.println("************************************************************************");

        String chordString = "";
        for(int i=0; i<chordPath.size(); i++){
            if(i == 0) {
                chordString = this.keyMembers.getById(chordPath.get(i).Source).Note;
                chordString = chordString + this.keyMembers.getById(chordPath.get(i).Source).Chord + "w ";
            }

            chordString = chordString + this.keyMembers.getById(chordPath.get(i).Target).Note;
            chordString = chordString + this.keyMembers.getById(chordPath.get(i).Target).Chord + "w ";
        }

        System.out.println(chordPath.toString());
        System.out.println("************************************************************************");
        System.out.println("************************************************************************");

        Player player = new Player();
        System.out.println("F");
        String patternStr = "V0 I[Piano] " + chordString.trim();
        System.out.println(patternStr);
        Pattern pattern = new Pattern(patternStr);
        pattern.setTempo(60);
        //player.play("V0 " + playString + " V1 " + chordString);
        //player = null;

        return saveMidiFile(renderData, pattern);
    }

    private RenderData saveMidiFile(RenderData renderData, PatternProducer pattern) {
        String fileName = "output/midi" + UUID.randomUUID().toString() + ".mid";
        try {
            MidiFileManager.savePatternToMidi(pattern, new File(fileName));

            MidiFileManager.save(new Player().getSequence(pattern), new File(fileName));
            renderData.MidiURL = "http://localhost:8080/" + fileName;
        } catch (Exception e){
            renderData.ErrorMessage = "failed to generate Midi data";
            System.out.println("ERROR: " + e.toString());
        }

        return renderData;
    }
}



/*
Encoding format
Each bar consists of a 72 digit encoding

[key][chords][bar1][bar2][bar3][bar4]
KEY EXAMPLE
[F-sharp] = [62]

CHORDS EXAMPLE
[G-major-7 | eb minor] = [713501]

BAR EXAMPLE
Each beat in a bar: [NOTE-SHARP-OCATAVE-LIFECYCLE]
[g-sharp-quarter | rest-quarter | d-quarter | e-eighth | f-eight] = [7140-7141-7141-7141--0000-0000-0000-0000--4140-4141-4141-4141--5140-5141-6140-6140]

NOTES:
0 | Rest
1 | a
2 | b
3 | c
4 | d
5 | e
6 | f
7 | g

FLAT/NATURAL/SHARP
0 - FLAT | 1 - NATURAL | 2 - SHARP

OCTAVES
0-5

NOTE LIFE_CYCLE
0 - START | 1 - SUSTAIN

CHORD TYPES
0 | maj
1 | min
2 | dim
3 | maj7
4 | min7
5 | dom7
6 | min7b5
#### CHORD TYPE

START/SUSTAIN
0 - START | 1 - SUSTAIN
 */
