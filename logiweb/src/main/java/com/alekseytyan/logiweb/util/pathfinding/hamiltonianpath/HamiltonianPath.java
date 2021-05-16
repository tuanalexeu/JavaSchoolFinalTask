package com.alekseytyan.logiweb.util.pathfinding.hamiltonianpath;

import com.alekseytyan.logiweb.util.pathfinding.Route;
import com.alekseytyan.logiweb.util.pathfinding.dijkstra.Graph;
import com.alekseytyan.logiweb.util.pathfinding.dijkstra.Node;

import java.util.*;
import java.util.stream.Collectors;

public class HamiltonianPath {
    public static List<Integer> printAllHamiltonianPaths(EdgeGraph g,
                                                int v,
                                                boolean[] visited,
                                                List<Integer> path,
                                                int N) {
        // if all the vertices are visited, then the Hamiltonian path exists
        if (path.size() == N) {
            // print the Hamiltonian path
            System.out.println(path);
            return path;
        }
 
        // Check if every edge starting from vertex `v` leads
        // to a solution or not
        for (int w: g.adjList.get(v)) {
            // process only unvisited vertices as the Hamiltonian
            // path visit each vertex exactly once
            if (!visited[w]) {
                visited[w] = true;
                path.add(w);
 
                // check if adding vertex `w` to the path leads
                // to the solution or not
                List<Integer> list = printAllHamiltonianPaths(g, w, visited, path, N);
                if(list != null) {
                    return list;
                }
 
                // backtrack
                visited[w] = false;
                path.remove(path.size() - 1);
            }
        }
        return null;
    }
 
    public static Route calculateRoute(Set<Node> neededCities, Set<Node> allCities, Node cityStart) {
 
        // build a graph from the given edges
        EdgeGraph g = convertToEdgeGraph(neededCities, allCities, cityStart);
 
        // starting node
        int start = 0;
 
        // add starting node to the path
        List<Integer> path = new ArrayList<>();
        path.add(start);
 
        // mark the start node as visited
        boolean[] visited = new boolean[neededCities.size()];
        visited[start] = true;
 
        List<Integer> list = printAllHamiltonianPaths(g, start, visited, path, neededCities.size());

        List<Node> neededCitiesOrder = new ArrayList<>();

        if(list == null) {
            Route route = new Route();
            route.setPossible(false);

            return route;
        }

        for (Integer i: list) {
            neededCitiesOrder.add(g.findNodeByIndex(i));
        }


        List<Node> finalCities = new ArrayList<>();
        int distance = 0;

        for (int i = 0; i < neededCitiesOrder.size() - 1; i++) {


            for (Node n: allCities) {
                n.setShortestPath(new ArrayList<>());
                n.setDistance(Integer.MAX_VALUE);
            }

            Node cityStart_inner = neededCitiesOrder.get(i);
            for (Node n2: allCities) {
                if (n2.equals(cityStart_inner)) {
                    cityStart_inner = n2;
                }
            }

            Graph graph = Graph.calculateShortestPathFromSource(new Graph(allCities), cityStart_inner);
            for (Node n : graph.getNodes()) {
                if(n.equals(neededCitiesOrder.get(i + 1))) {

                    if(!finalCities.isEmpty()) {
                        finalCities.remove(finalCities.size() - 1);
                    }

                    List<Node> path_inner = n.getShortestPath();
                    path_inner.add(n);

                    System.out.println(path_inner);

                    finalCities.addAll(path_inner);

                    distance += n.getDistance();

                    break;
                }
            }
        }


        System.out.println();
        System.out.println(neededCitiesOrder);
        System.out.println(finalCities);
        System.out.println(distance);

        Route route = new Route();
        route.setPossible(distance < Integer.MAX_VALUE);
        route.setTime(distance / 96);
        route.setDistance(distance);
        route.setCityList(finalCities.stream().map(Node::getCity).collect(Collectors.toList()));

        return route;
    }


    private static EdgeGraph convertToEdgeGraph(Set<Node> neededCities, Set<Node> allCities, Node cityStart) {

        Map<Node, Integer> map = new HashMap<>();

        Node cityStartInitial = cityStart;

        int index = 0;
        map.put(cityStart, index++);

        for (Node n: neededCities) {
            if(!n.equals(cityStart)) {
                map.put(n, index++);
            }
        }

        List<Edge> edges = new ArrayList<>();

        for (Node n: allCities) {
            if(n.equals(cityStart)) {
                cityStart = n;
            }
        }

        Graph graph = Graph.calculateShortestPathFromSource(new Graph(allCities), cityStart);

        for (Node n: graph.getNodes()) {
            if(!n.equals(cityStart)
                    && neededCities.contains(n)
                    && n.getDistance() < Integer.MAX_VALUE) {
                edges.add(new Edge(map.get(cityStart), map.get(n), n.getDistance(), cityStart, n));
            }
        }


        for (Node n: neededCities) {

            if(!n.equals(cityStart) && !n.equals(cityStartInitial)) {

                for (Node n2: allCities) {
                    if(n2.equals(n)) {
                        cityStart = n2;
                    }
                }

                allCities = allCities
                        .stream()
                        .peek(n2 -> {
                            n2.setDistance(Integer.MAX_VALUE);
                            n2.setShortestPath(new ArrayList<>());
                        })
                        .collect(Collectors.toSet());

                graph = Graph.calculateShortestPathFromSource(new Graph(allCities), cityStart);

                for (Node n2: graph.getNodes()) {
                    if(!n2.equals(cityStart)
                            && neededCities.contains(n2)
                            && n2.getDistance() < Integer.MAX_VALUE) {
                        edges.add(new Edge(map.get(cityStart), map.get(n2), n2.getDistance(), cityStart, n2));
                    }
                }
            }
        }


        return new EdgeGraph(edges, neededCities.size());
    }
}