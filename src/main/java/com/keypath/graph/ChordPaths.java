package com.keypath.graph;

import com.keypath.graph.util.AdjacencyMatrixGraph;
import com.keypath.graph.util.Graph;
import com.keypath.graph.util.GraphType;
import com.keypath.graph.util.ShortestPathUnweighted;
import com.keypath.server.model.Link;

import java.util.ArrayList;
import java.util.List;

public class ChordPaths {

    public List<Link> generateChordPath(List<Link> edges, KeyMembers keyMembers) {

        Graph graph = new AdjacencyMatrixGraph(84, GraphType.UNDIRECTED);
        for (Link link : edges) {
            //System.out.println("EDGE S:" + link.Source + " T:" + link.Target);
            graph.addEdge(link.Source, link.Target);
        }

        List<Integer> idPath = ShortestPathUnweighted.shortestPath(graph, 0, 83);
        //0    1    2     3
        //0 -> 1 -> 72 -> 70
        List<Link> chordPath = new ArrayList<>();
        for (int i = 0; i < idPath.size(); i++) {
            if (i + 1 < idPath.size()) {
                chordPath.add(new Link(keyMembers.getById(idPath.get(i)).Id, keyMembers.getById(idPath.get(i + 1)).Id).setIsPath());
            }
        }

        return chordPath;
    }
}
