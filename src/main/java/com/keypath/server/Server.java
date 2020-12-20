package com.keypath.server;

import com.keypath.graph.ChordGraph;
import com.keypath.server.model.RenderData;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.*;

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
        RenderData renderData = null;
        try {
            renderData = chordGraph.createGraph();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return renderData;
    }

    @GetMapping(
            value = "/midi",
            produces = MediaType.MULTIPART_MIXED_VALUE
    )
    public @ResponseBody byte[] getImageWithMediaType() throws IOException {
        final File initialFile = new File("output/midi0d05fbe3-b6e0-45f3-830e-5031d31a8b4f.mid");
        final InputStream in =
                new DataInputStream(new FileInputStream(initialFile));
        return IOUtils.toByteArray(in);
    }
}

