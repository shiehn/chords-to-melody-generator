package com.keypath.graph.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Navdeep on 18-02-2016.
 */
public class AdjacencyMatrixGraph implements Graph {

    private int[][] adjacencyMatrix;
    private GraphType graphType = GraphType.DIRECTED;
    private int numVertices = 0;

    public AdjacencyMatrixGraph(int numVertices, GraphType graphType) {
        this.graphType = graphType;
        this.numVertices = numVertices;
        this.adjacencyMatrix = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                this.adjacencyMatrix[i][j] = 0;
            }
        }
    }

    @Override
    public GraphType TypeofGraph() {
        return this.graphType;
    }

    @Override
    public void addEdge(int v1, int v2) {
        if (v1 >= this.numVertices || v2 >= this.numVertices || v1 < 0 || v2 < 0) {
            throw new IllegalArgumentException("Vertex number is not valid");
        }
        this.adjacencyMatrix[v1][v2] = 1;
        if (this.graphType == GraphType.UNDIRECTED) {
            this.adjacencyMatrix[v2][v1] = 1;
        }
    }

    @Override
    public void addEdge(int v1, int v2, int weight) {
        if (v1 >= this.numVertices || v2 >= this.numVertices || v1 < 0 || v2 < 0) {
            throw new IllegalArgumentException("Vertex number is not valid");
        }
        this.adjacencyMatrix[v1][v2] = weight;
        if (this.graphType == GraphType.UNDIRECTED) {
            this.adjacencyMatrix[v2][v1] = weight;
        }
    }

    @Override
    public int getWeightedEdge(int v1, int v2) {
        return this.adjacencyMatrix[v1][v2];
    }

    @Override
    public List<Integer> getAdjacentVertices(int v) {
        if (v < 0 || v >= this.numVertices) {
            throw new IllegalArgumentException("Vertex number is not valid");
        }
        List<Integer> integerList = new ArrayList<>();
        for (int i = 0; i < this.numVertices; i++) {
            if (this.adjacencyMatrix[v][i] != 0) {
                integerList.add(i);
            }
        }
        return integerList;
    }

    @Override
    public int getIndegree(int v) {
        if (v < 0 || v >= this.numVertices) {
            throw new IllegalArgumentException("Vertex number is not valid");
        }
        int indegree = 0;
        for (int i = 0; i < this.getNumVertices(); i++) {
            if (this.adjacencyMatrix[i][v] != 0) {
                indegree++;
            }
        }
        return indegree;
    }

    @Override
    public int getNumVertices() {
        return this.numVertices;
    }
}
