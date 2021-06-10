package com.alekseytyan.logiweb.util.pathfinding.hamiltonianpath;

import com.alekseytyan.logiweb.util.pathfinding.Route;
import com.alekseytyan.logiweb.util.pathfinding.dijkstra.Graph;
import com.alekseytyan.logiweb.util.pathfinding.dijkstra.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class HamiltonianPath {

    private static final Logger logger = LoggerFactory.getLogger(HamiltonianPath.class);

    /**
     * Hamiltonian path algorithm. Calculates optimal route with given nodes
     * @param g - edge graph
     * @param v - starting node
     * @param visited - visited nodes
     * @param path - result path
     * @param N - number of nodes
     * @return - list of number, each of witch represents number of node
     */
    public static List<Integer> getHamiltonianPath(EdgeGraph g,
                                          int v,
                                          boolean[] visited,
                                          List<Integer> path,
                                          int N) {
        // if all the vertices are visited, then the Hamiltonian path exists
        if (path.size() == N) {
            // the Hamiltonian path
            return new ArrayList<>(path);
        }

        List<Integer> result = new ArrayList<>();

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
                result = getHamiltonianPath(g, w, visited, path, N);

                // backtrack
                visited[w] = false;
                path.remove(path.size() - 1);
            }
        }
        return result;
    }

    /**
     * Method calls hamiltonian path and builds result Route object
     * @param neededCities - cities to visit
     * @param allCities - all cities and distances between them
     * @param cityStart - Starting city
     * @return - result route object
     */
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

        List<Integer> result = getHamiltonianPath(g, start, visited, path, neededCities.size());

        List<Node> neededCitiesOrder = new ArrayList<>();

        logger.info("Result: " + result);

        if(result == null || result.isEmpty()) {
            Route route = new Route();
            route.setPossible(false);

            return route;
        }

        for (Integer i: result) {
            neededCitiesOrder.add(g.findNodeByIndex(i));
        }

        logger.info("Needed cities order: " + neededCitiesOrder);

        return calculateCitiesBetween(neededCitiesOrder, allCities);
    }

    /**
     * Calculates route between needed cities, after their order is calculated
     * @param neededCitiesOrder - order of initial cities
     * @param allCities - all existing ciites
     * @return - object representing route information
     */
    private static Route calculateCitiesBetween(List<Node> neededCitiesOrder, Set<Node> allCities) {
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

                    finalCities.addAll(path_inner);

                    distance += n.getDistance();

                    break;
                }
            }
        }

        logger.info("Distance: " + distance);

        Route route = new Route();
        route.setPossible(distance < Integer.MAX_VALUE);
        route.setTime(distance / 96);
        route.setDistance(distance);
        route.setCityList(finalCities.stream().map(Node::getCity).collect(Collectors.toList()));

        return route;
    }


    /**
     * Method converts node graph to edge graph
     * @param neededCities - cities to visit
     * @param allCities - all cities and distances between them
     * @param cityStart - starting city
     * @return - resulting edge graph
     */
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