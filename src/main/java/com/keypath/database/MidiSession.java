package com.keypath.database;

import java.util.HashMap;
import java.util.Map;

public class MidiSession {
    public Long sessionStart;
    public Map<String, Long> midiFileNameToDate = new HashMap<>();

    public MidiSession(Long sessionStart, String midiFile) {
        this.sessionStart = sessionStart;
        this.midiFileNameToDate = new HashMap<>();

        this.midiFileNameToDate.put(midiFile, sessionStart);
    }
}
