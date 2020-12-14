package com.keypath.server;

import com.keypath.graph.ChordGraph;
import com.keypath.server.model.RenderData;
import org.springframework.web.bind.annotation.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
public class Server {

    public Server() {
    }

    @PostMapping("/tree")
    String addNode(@RequestBody String input) {
        return null;
    }

    @GetMapping("/graph")
    RenderData get() {
        ChordGraph chordGraph = new ChordGraph();
        return chordGraph.createGraph();
    }

    @GetMapping("/file")
    String getFile() {

        UUID uuid = UUID.randomUUID();

        try {
            FileWriter myWriter = new FileWriter("output/" + uuid.toString() + ".txt");
            myWriter.write(uuid.toString());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return "http://localhost:8080/output/" + uuid.toString() + ".txt";
    }
}

