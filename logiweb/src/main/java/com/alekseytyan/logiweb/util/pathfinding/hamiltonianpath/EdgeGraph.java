package com.alekseytyan.logiweb.util.pathfinding.hamiltonianpath;

import com.alekseytyan.logiweb.util.pathfinding.dijkstra.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to represent a graph object
 */
public class EdgeGraph {

    // A list of lists to represent an adjacency list
    List<List<Integer>> adjList = null;

    List<Edge> edges;

 
    // Constructor
    public EdgeGraph(List<Edge> edges, int N) {

        this.edges = edges;

        adjList = new ArrayList<>();
 
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }
 
        // add edges to the undirected graph
        for (Edge edge: edges)
        {
            int src = edge.getSource();
            int dest = edge.getDestination();
 
            adjList.get(src).add(dest);
            adjList.get(dest).add(src);
        }
    }

    public Node findNodeByIndex(int index) {
        for (Edge e: edges) {
            if(e.getSource() == index) {
                return e.getNodeSource();
            }
        }
        return null;
    }
}
 