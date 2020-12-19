package com.keypath.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task {
    private static final Logger log = LoggerFactory.getLogger(Task.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public static Map<String, >

    @Scheduled(fixedRate = 5000)
    public void cleanupMidiFiles() {
        log.info("Cleanup Task is running now {}", dateFormat.format(new Date()));
    }
}
