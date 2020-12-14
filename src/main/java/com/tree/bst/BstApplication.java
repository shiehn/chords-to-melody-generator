package com.tree.bst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BstApplication {
    public static void main(String[] args) {
        SpringApplication.run(BstApplication.class, args);
    }

    BstApplication(){
//        BSTTree tree = new BSTTree();
//
//        //level one
//        tree.addNode(100);
//
//        //level two
//        tree.addNode(50);
//        tree.addNode(150);
//
//        //level three
//        tree.addNode(25);
//        tree.addNode(75);
//
//        tree.addNode(125);
//        tree.addNode(175);
//
//        tree.transverseBF();



        // define edges of the graph
        List<Edge> edges = Arrays.asList(new Edge(0, 1, 2),new Edge(0, 2, 4),
                new Edge(1, 2, 4),new Edge(2, 0, 5), new Edge(2, 1, 4),
                new Edge(3, 2, 3), new Edge(4, 5, 1),new Edge(5, 4, 3));

        // call graph class Constructor to construct a graph
        Graph graph = new Graph(edges);

        // print the graph as an adjacency list
        //Graph.printGraph(graph);
    }
}
