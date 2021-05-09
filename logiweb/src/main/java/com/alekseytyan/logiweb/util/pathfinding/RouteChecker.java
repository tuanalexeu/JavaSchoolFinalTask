package com.alekseytyan.logiweb.util.pathfinding;

import com.alekseytyan.logiweb.util.pathfinding.dijkstra.graph.Graph;
import com.alekseytyan.logiweb.entity.City;
import com.alekseytyan.logiweb.entity.DistanceMap;
import com.alekseytyan.logiweb.entity.Load;
import com.alekseytyan.logiweb.util.pathfinding.dijkstra.graph.Node;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class contains order calculation methods (e.g. calculating max weight needed by truck to be able to get order)
 */
@Getter @Setter
public class RouteChecker {

    public static Route calculateRoute(List<DistanceMap> distances, List<Load> loads, City cityStart) {

//        Map<List<City>, Integer> finalCities = new HashMap<>();
//
//        for (Load l: loads) {
//
//            Set<City> lCities = checkCities(l);
//
//            Set<Node> nodes = convertToNodeList(lCities, distances);
//
//            Graph lGraph = new Graph(nodes);
//
//            Node nodeStart = new Node(cityStart);
//            for (Node n: nodes) {
//                if(n.equals(nodeStart)) {
//                    nodeStart = n;
//                }
//            }
//
//            lGraph = Graph.calculateShortestPathFromSource(lGraph, nodeStart);
//
//            int distance = Integer.MAX_VALUE;
//
//
//            List<City> finalRoute = new ArrayList<>();
//            boolean isPossible = false;
//
//            for (Node n: lGraph.getNodes()) {
//
//                List<Node> n2 = n.getShortestPath();
//                n2.add(n);
//
//                List<City> currentCities = n2.stream().map(Node::getCity).collect(Collectors.toList());
//
//                if(checkIfContains(currentCities, new ArrayList<>(lCities)) && n.getDistance() < distance) {
//                    finalRoute = currentCities;
//                    distance = n.getDistance();
//                    isPossible = true;
//                }
//            }
//
//            finalCities.put(finalRoute, distance);
//        }


        Set<City> cities = checkCities(loads);
        Set<Node> nodes = convertToNodeList(cities, distances);
        Graph graph = new Graph(nodes);
        Node nodeStart = new Node(cityStart);
        for (Node n: nodes) {
            if(n.equals(nodeStart)) {
                nodeStart = n;
            }
        }

        graph = Graph.calculateShortestPathFromSource(graph, nodeStart);

        int distance = Integer.MAX_VALUE;


        List<City> finalCities = new ArrayList<>();
        boolean isPossible = false;

        for (Node n: graph.getNodes()) {

            List<Node> n2 = n.getShortestPath();
            n2.add(n);

            List<City> currentCities = n2.stream().map(Node::getCity).collect(Collectors.toList());

            if(checkIfContains(currentCities, new ArrayList<>(cities)) && n.getDistance() < Integer.MAX_VALUE) {
                finalCities = currentCities;
                distance = n.getDistance();
                isPossible = true;
            }

        }

        Route route = new Route();

        route.setPossible(isPossible);
        route.setCityList(finalCities);
        route.setDistance(distance);
        route.setTime(calculateRouteTime(distance));
        route.setMaxWeight(calculateMaxWeight(loads));

        return route;
    }

    private static Set<Node> convertToNodeList(Set<City> cities, List<DistanceMap> distances) {

        Set<Node> nodes = new HashSet<>();

        for (DistanceMap d: distances) {

            Node node1 = new Node(d.getCityFrom());
            Node node2 = new Node(d.getCityTo());

            for (Node n: nodes) {
                if(n.equals(node1)) {
                    node1 = n;
                } else if(n.equals(node2)) {
                    node2 = n;
                }
            }

            node1.addDestination(node2, d.getDistance());
            node2.addDestination(node1, d.getDistance());

            nodes.remove(node1);
            nodes.remove(node2);

            nodes.add(node1);
            nodes.add(node2);
        }

        return nodes;
    }

    public static Route calculateRoute(List<DistanceMap> distances, List<Load> loads) {
        return calculateRoute(distances, loads, loads.get(0).getCityLoad());
    }


    public static boolean checkIfContains(List<City> c1, List<City> c2) {
        for (City c: c2) {
            if(!c1.contains(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method calculates actual time need to complete the order
     * @return - time as hours
     */
    public static int calculateRouteTime(int distance) {
        return distance / 96;
    }

    /**
     * Calculates max weight on the way
     * @return - weight as int number
     */
    public static int calculateMaxWeight(List<Load> loads) {

        int maxWeight = 0;

        Set<City> cities = checkCities(loads);

        for (City c: cities) {

            int currentWeight = maxWeight;

            for (Load l: loads) {
                if(c.equals(l.getCityLoad())) {
                    currentWeight += l.getWeight();
                } else if(c.equals(l.getCityUnload())) {
                    currentWeight -= l.getWeight();
                }
            }

            maxWeight = Math.max(currentWeight, maxWeight);
        }

        return maxWeight;
    }

    /**
     * Method converts set of cities to a map key set
     * @return - Map, where cities are stored as keys
     */
    private static Set<City> checkCities(List<Load> loads) {

        // Map contains city and the weight a truck needs to be able to get
        Set<City> routeWeight = new HashSet<>();

        // Get set of all cities we need to visit on the way
        loads.forEach(l -> {
            routeWeight.add(l.getCityLoad());
            routeWeight.add(l.getCityUnload());
        });

        return routeWeight;
    }

    private static Set<City> checkCities(Load load) {

        Set<City> result = new HashSet<>();
        result.add(load.getCityLoad());
        result.add(load.getCityUnload());

        return result;
    }
}
