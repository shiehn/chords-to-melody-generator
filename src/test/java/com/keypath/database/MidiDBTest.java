package com.keypath.database;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MidiDBTest {



    @Test
    public void createsSessionPathAndCreatedAtFile() throws IOException {
        String sessionID = UUID.randomUUID().toString();

        MidiDB midiDB = new MidiDB();
        midiDB.createMidiFilePath(sessionID);

        //assert
        File f = new File("output/" + sessionID);
        Boolean dirExisits = f.exists() && f.isDirectory();

        f = new File("output/" + sessionID + "/created.txt");
        Boolean createdAtFileExisits = f.exists() && !f.isDirectory();

        assertTrue(dirExisits);
        assertTrue(createdAtFileExisits);

        //cleanup
        assertDoesNotThrow(()-> FileUtils.deleteDirectory(new File("output/" + sessionID)));
    }

    @Test
    public void deleteExpiredFiles(){
        MidiDB midiDB = new MidiDB();
        midiDB.deleteExpiredFiles();
    }

    @Test
    public void deleteExpiredSessions() throws IOException {
        MidiDB midiDB = new MidiDB();
        midiDB.deleteExpiredSessions();
    }
}
