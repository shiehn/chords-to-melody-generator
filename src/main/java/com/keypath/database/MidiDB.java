package com.keypath.database;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MidiSession {
    public Date sessionStart;
    public Map<String, Date> midiFileNameToDate = new HashMap<>();
}

public class MidiDB {
    public Map<String, MidiSession> sessionMap = new HashMap<>();

    public void addMidiFile(){

    }
}
