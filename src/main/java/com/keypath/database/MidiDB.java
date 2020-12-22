package com.keypath.database;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

public class MidiDB {

    private static final Logger log = LoggerFactory.getLogger(MidiDB.class);

    public String createMidiFilePath(String sessionId) throws IOException {
        String filePath = "output/" + sessionId;
        Files.createDirectories(Paths.get(filePath));

        File f = new File(filePath + "/created.txt");
        if(!f.exists()) {
            FileWriter myWriter = new FileWriter(filePath + "/created.txt");
            myWriter.write(Long.toString((new Date()).getTime()));
            myWriter.close();
        }

        return filePath;
    }

    public void deleteExpiredSessions() throws IOException {
        File[] directories = new File("output/").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });

        for (File f : directories) {
            File[] files = new File(f.getAbsolutePath()).listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.isFile();
                }
            });

            for (File file : files) {
                if(file.getName().contains("created")) {
                    String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);

                    Date creationDate = new Date(Long.parseLong(content));
                    Date currentDate = new Date();

                    long diff = currentDate.getTime() - creationDate.getTime();
                    long diffMinutes = diff / (60 * 1000);

                    if(diffMinutes > 60) {
                        log.info("CLEANUP: Deleting expired session: " + f.getName());
                        FileUtils.deleteDirectory(f);
                    }
                }
            }
        }
    }

    public void deleteExpiredFiles(){
        File[] directories = new File("output/").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });

        for (File f : directories) {
            File[] midiFiles = new File(f.getAbsolutePath()).listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.isFile();
                }
            });

            for (File m : midiFiles) {
                if(m.getName().contains("created")) {
                    continue;
                }

                String[] stringSplit = m.getName().split("\\$");

                Date creationDate = new Date(Long.parseLong(stringSplit[0]));
                Date currentDate = new Date();

                long diff = currentDate.getTime() - creationDate.getTime();
                long diffMinutes = diff / (60 * 1000);

                if(diffMinutes > 10) {
                    log.info("CLEANUP: Deleting expired MIDI file: " + m.getName());
                    m.delete();
                }
            }
        }

    }
}
