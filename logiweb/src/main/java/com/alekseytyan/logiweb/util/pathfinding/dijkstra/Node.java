package com.alekseytyan.logiweb.util.pathfinding.dijkstra;

import com.alekseytyan.logiweb.entity.City;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * Node, containing city, distance from source node and adjacent nodes
 */
@Getter @Setter
public class Node {

    private City city;

    private List<Node> shortestPath = new LinkedList<>();
    private Integer distance = Integer.MAX_VALUE;
    private Map<Node, Integer> adjacentNodes = new HashMap<>();

    public Node(City city) {
        this.city = city;
    }

    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return city.equals(node.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city);
    }

    @Override
    public String toString() {
        return city.toString();
    }
}