package com.keypath.graph;

import com.keypath.server.model.Link;
import com.keypath.server.model.RenderData;

import java.util.List;

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

        return renderData;
    }
}
