package com.alekseytyan.logiweb.util.pathfinding.astar;

import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Graph<T extends GraphNode> {

    private final Set<T> nodes;
    private final Map<String, Set<String>> connections;

    public T getNode(String name) {
        return nodes.stream()
                .filter(node -> node.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No node found with ID"));
    }

    public Set<T> getConnections(T node) {
        return connections.get(node.getName()).stream()
                .map(this::getNode)
                .collect(Collectors.toSet());
    }
}