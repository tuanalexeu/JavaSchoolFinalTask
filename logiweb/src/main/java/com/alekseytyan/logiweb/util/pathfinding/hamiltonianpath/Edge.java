package com.alekseytyan.logiweb.util.pathfinding.hamiltonianpath;

import com.alekseytyan.logiweb.util.pathfinding.dijkstra.Node;
import lombok.*;

import java.util.Formatter;

// A class to store a graph edge
@AllArgsConstructor
@EqualsAndHashCode
@Getter @Setter
public class Edge {
    private int source;
    private int destination;
    private int distance;

    private Node nodeSource;
    private Node nodeDest;


    @Override
    public String toString() {
        return new Formatter()
                .format("Source: %d\n" +
                        "Dest: %d\n" +
                        "Distance: %d\n" +
                        "NodeSource: %s\n" +
                        "Node dest: %s\n",
                        source, destination, distance, nodeSource, nodeDest)
                .toString();
    }
}