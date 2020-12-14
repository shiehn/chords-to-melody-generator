package com.keypath.graph.util;

import com.keypath.server.model.IdKeyNote;

import java.util.*;

/**
 * Created by Navdeep on 18-02-2016.
 */
public class ShortestPathUnweighted {

//    static List<IdKeyNote> aNaturalMajor = new ArrayList<>() {
//        {
//            this.add(new IdKeyNote(0, "an", "a"));
//            this.add(new IdKeyNote(1, "an", "b"));
//            this.add(new IdKeyNote(2, "an", "c#"));
//            this.add(new IdKeyNote(3, "an", "d"));
//            this.add(new IdKeyNote(4, "an", "e"));
//            this.add(new IdKeyNote(5, "an", "f#"));
//            this.add(new IdKeyNote(6, "an", "g#"));
//        }
//    };
//    static List<IdKeyNote> aSharpMajor = new ArrayList<>() {
//        {
//            this.add(new IdKeyNote(7, "a#", "a#"));
//            this.add(new IdKeyNote(8, "a#", "c"));
//            this.add(new IdKeyNote(9, "a#", "d"));
//            this.add(new IdKeyNote(10, "a#", "d#"));
//            this.add(new IdKeyNote(11, "a#", "f"));
//            this.add(new IdKeyNote(12, "a#", "g"));
//            this.add(new IdKeyNote(13, "a#", "a"));
//        }
//    };
//
//    /*
//         // A-MAJOR
//         0=an,a
//         1=an,b
//         2=an,c#
//         3=an,d
//         4=an,e
//         5=an,f#
//         6=an,g#
//    */
//    static List<IdKeyNote> bNaturalMajor = new ArrayList<>() {
//        {
//            this.add(new IdKeyNote(14, "bn", "b"));
//            this.add(new IdKeyNote(15, "bn", "c#"));
//            this.add(new IdKeyNote(16, "bn", "d#"));
//            this.add(new IdKeyNote(17, "bn", "e"));
//            this.add(new IdKeyNote(18, "bn", "f#"));
//            this.add(new IdKeyNote(19, "bn", "g#"));
//            this.add(new IdKeyNote(20, "bn", "a#"));
//        }
//    };

/*
     // A#-MAJOR
     7=a#,a#
     8=a#,c
     9=a#,d
     10=a#,d#
     11=a#,f
     12=a#,g
     13=a#,a
    */

    static public List<Connection> createConnectionsToSelf(List<IdKeyNote> idKeyNotes) {
        List<Connection> connections = new ArrayList<>();

        for (int i = 0; i < idKeyNotes.size(); i++) {
            for (int j = i; j < idKeyNotes.size(); j++) {
                connections.add(new Connection(idKeyNotes.get(i).Id, idKeyNotes.get(j).Id));
            }
        }

        return connections;
    }

    /*
     // B-MAJOR
     14=bn,b
     15=bn,c#
     16=bn,d#
     17=bn,e
     18=bn,f#
     19=bn,g#
     20=bn,a#
*/

    static public List<Connection> createCrossKeyConnections(List<IdKeyNote> idKeyNotes) {
        Map<String, Connection> connectionMap = new HashMap<>();

        for (int i = 0; i < idKeyNotes.size(); i++) {
            for (int j = 0; j < idKeyNotes.size(); j++) {
                IdKeyNote keyNoteOne = idKeyNotes.get(i);
                IdKeyNote keyNoteTwo = idKeyNotes.get(j);

                String keyOrder1 = String.valueOf(keyNoteOne.Id) + "-" + String.valueOf(keyNoteTwo.Id);
                String keyOrder2 = String.valueOf(keyNoteTwo.Id) + "-" + String.valueOf(keyNoteOne.Id);

                if (keyNoteOne.Note.equals(keyNoteTwo.Note) && (connectionMap.get(keyOrder1) == null) && (connectionMap.get(keyOrder2) == null)) {
                    connectionMap.put(keyOrder1, new Connection(keyNoteOne.Id, keyNoteTwo.Id));
                }
            }
        }

        List<Connection> connections = new ArrayList<>();
        for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
            connections.add(entry.getValue());
        }

        return connections;
    }

    public static void main(String[] args) {

        //INNER KEY CONNECTIONS
        //INNER KEY CONNECTIONS
        //INNER KEY CONNECTIONS

        List<Connection> connections = new ArrayList<>();

//        connections.addAll(createConnectionsToSelf(aNaturalMajor));
//        connections.addAll(createConnectionsToSelf(aSharpMajor));
//        connections.addAll(createConnectionsToSelf(bNaturalMajor));

        //END INNER KEY CONNECTIONS
        //END INNER KEY CONNECTIONS
        //END INNER KEY CONNECTIONS

        //CROSS KEY CONNECTIONS
        //CROSS KEY CONNECTIONS
        //CROSS KEY CONNECTIONS

//        List<IdKeyNote> allKeys = new ArrayList<>();
//        allKeys.addAll(aNaturalMajor);
//        allKeys.addAll(aSharpMajor);
//        allKeys.addAll(bNaturalMajor);

//        List<Connection> crossKeyConnections = createCrossKeyConnections(allKeys);

        //END CROSS KEY CONNECTIONS
        //END CROSS KEY CONNECTIONS
        //END CROSS KEY CONNECTIONS


        Graph graph1 = new AdjacencyMatrixGraph(21, GraphType.UNDIRECTED);

        //APPLY CONNECTIONS
        //APPLY CONNECTIONS
        //APPLY CONNECTIONS

//        connections.addAll(crossKeyConnections);

        for (Connection conn : connections) {
            graph1.addEdge(conn.VertexA, conn.VertexB);
        }

        //END APPLY CONNECTIONS
        //END APPLY CONNECTIONS
        //END APPLY CONNECTIONS


    }

    private static Map<Integer, DistanceInfo> buildDistanceTable(Graph graph, int source) {
        Map<Integer, DistanceInfo> distanceTable = new HashMap<>();
        for (int j = 0; j < graph.getNumVertices(); j++) {
            distanceTable.put(j, new DistanceInfo());
        }

        distanceTable.get(source).setDistance(0);
        distanceTable.get(source).setLastVertex(source);

        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(source);

        while (!queue.isEmpty()) {
            int currentVertex = queue.pollFirst();
            for (int i : graph.getAdjacentVertices(currentVertex)) {
                int currentDistance = distanceTable.get(i).getDistance();
                if (currentDistance == -1) {
                    currentDistance = 1 + distanceTable.get(currentVertex).getDistance();
                    distanceTable.get(i).setDistance(currentDistance);
                    distanceTable.get(i).setLastVertex(currentVertex);
                    // Enqueue the neighbour only if it has other adjacent vertices.
                    if (!graph.getAdjacentVertices(1).isEmpty()) {
                        queue.add(i);
                    }
                }
            }

        }
        return distanceTable;
    }

    public static List<Integer> shortestPath(Graph graph, int source, int destination) {
        Map<Integer, DistanceInfo> distanceTable = buildDistanceTable(graph, source);

        Stack<Integer> stack = new Stack<>();
        stack.push(destination);

        int previousVertex = distanceTable.get(destination).getLastVertex();
        while (previousVertex != -1 && previousVertex != source) {
            stack.push(previousVertex);
            previousVertex = distanceTable.get(previousVertex).getLastVertex();
        }

        List<Integer> path = new ArrayList<>();

        if (previousVertex == -1) {
            System.out.println("There is no path from node: " + source
                    + " to node: " + destination);
        } else {
            path.add(source);
            System.out.print("Smallest path is " + source);
            while (!stack.isEmpty()) {
                int popped = stack.pop();
                path.add(popped);
                System.out.print(" -> " + popped);
            }
            System.out.println(" Shortest Path Unweighted DONE!");
        }

        return path;
    }

    static class Connection {
        public int VertexA;
        public int VertexB;

        public Connection(int VertexA, int VertexB) {
            this.VertexA = VertexA;
            this.VertexB = VertexB;
        }

        public boolean isSame(int a, int b) {
            return ((a == this.VertexA && b == this.VertexB) || (a == this.VertexB && b == this.VertexA));
        }
    }

    /**
     * A class which holds the distance information of any vertex.
     * The distance specified is the distance from the source node
     * and the last vertex is the last vertex just before the current
     * one while traversing from the source node.
     */
    public static class DistanceInfo {

        private int distance;
        private int lastVertex;

        public DistanceInfo() {
            this.distance = -1;
            this.lastVertex = -1;
        }

        public int getDistance() {
            return this.distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public int getLastVertex() {
            return this.lastVertex;
        }

        public void setLastVertex(int lastVertex) {
            this.lastVertex = lastVertex;
        }
    }
}
