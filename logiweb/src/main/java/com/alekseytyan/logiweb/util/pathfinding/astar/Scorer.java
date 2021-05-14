package com.alekseytyan.logiweb.util.pathfinding.astar;

public interface Scorer<T extends GraphNode> {
    double computeCost(T from, T to);
}