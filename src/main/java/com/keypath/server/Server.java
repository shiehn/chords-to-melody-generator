package com.keypath.server;

import com.keypath.graph.ChordGraph;
import com.keypath.server.model.RenderData;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;
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

    @GetMapping("/graph/{sessionid}")
    RenderData get(@PathVariable("sessionid") String sessionId) {
        ChordGraph chordGraph = new ChordGraph();
        RenderData renderData = null;
        try {
            renderData = chordGraph.createGraph(sessionId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return renderData;
    }

    @GetMapping(
            value = "/midi/{sessionid}/{midiid}",
            produces = MediaType.MULTIPART_MIXED_VALUE
    )
    public @ResponseBody byte[] getImageWithMediaType(@PathVariable("sessionid") String sessionId, @PathVariable("midiid") String midiId) throws IOException {

        File dir = new File(String.format("output/%s", sessionId));
        FileFilter fileFilter = new WildcardFileFilter(String.format("*$%s.mid", midiId));
        File[] files = dir.listFiles(fileFilter);

        //handle no file exception ..

        final InputStream in =
                new DataInputStream(new FileInputStream(files[0]));
        return IOUtils.toByteArray(in);
    }
}

