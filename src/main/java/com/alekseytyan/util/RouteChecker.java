package com.alekseytyan.util;

import com.alekseytyan.entity.City;
import com.alekseytyan.entity.DistanceMap;
import com.alekseytyan.entity.Load;
import com.alekseytyan.util.graph.Graph;
import com.alekseytyan.util.graph.Node;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class contains order calculation methods (e.g. calculating max weight needed by truck to be able to get order)
 */
@Getter @Setter
public class RouteChecker {

    /**
     * List of loads which are all in current order
     */
    private List<Load> loads;

    /**
     * Map contains pair of two cities and the distance between them
     */
    private List<DistanceMap> distances;

    /**
     * City which the order will start from
     */
    private City cityStart;

    /**
     * List contains cities, that we need to visit during ordering
     */
    private List<City> cities;

    public RouteChecker(List<Load> loads,
                        List<DistanceMap> distanceMaps,
                        City cityStart) {
        this.loads = loads;
        this.distances = distanceMaps;
        this.cityStart = cityStart;

        this.cities = checkCities();
    }

    public Route calculateRoute() {

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

            if(checkIfContains(currentCities,cities) && n.getDistance() < distance) {
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
        route.setMaxWeight(calculateMaxWeight());

        return route;
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
     * Method checks distance between two cities
     */
    private int checkDistance(City cityFrom, City cityTo) {
        for (DistanceMap d: distances) {
            if(d.getCityFrom().equals(cityFrom) && d.getCityTo().equals(cityTo)) {
                return d.getDistance();
            }
        }
        throw new IllegalStateException("Route between cities does not exist");
    }

    /**
     * Method calculates actual time need to complete the order
     * @return - time as hours
     */
    public int calculateRouteTime(int distance) {
        return distance / 96;
    }

    /**
     * Calculates max weight on the way
     * @return - weight as int number
     */
    public int calculateMaxWeight() {

        int maxWeight = 0;

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
    private List<City> checkCities() {

        // Map contains city and the weight a truck needs to be able to get
        List<City> routeWeight = new ArrayList<>();

        // Get set of all cities we need to visit on the way
        loads.forEach(l -> {
            routeWeight.add(l.getCityLoad());
            routeWeight.add(l.getCityUnload());
        });

        return routeWeight;
    }
}
