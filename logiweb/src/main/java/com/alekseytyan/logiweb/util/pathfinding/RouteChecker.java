package com.alekseytyan.logiweb.util.pathfinding;

import com.alekseytyan.logiweb.entity.City;
import com.alekseytyan.logiweb.entity.DistanceMap;
import com.alekseytyan.logiweb.entity.Load;
import com.alekseytyan.logiweb.util.pathfinding.dijkstra.Node;
import com.alekseytyan.logiweb.util.pathfinding.hamiltonianpath.HamiltonianPath;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class contains order calculation methods (e.g. calculating max weight needed by truck to be able to get order)
 */
@Getter @Setter
public class RouteChecker {

    private static final Logger logger = LoggerFactory.getLogger(RouteChecker.class);

    /**
     * Method calculates final route
     */
    public static Route calculateRoute(List<DistanceMap> distances, List<Load> loads) {

        // Convert city set to node set
        Set<Node> neededCities = checkCities(loads)
                .stream()
                .map(Node::new)
                .collect(Collectors.toSet());

        // Convert DistanceMap set to Node set
        Set<Node> alLCities = convertToNodeList(distances);

        // Choose starting node
        Node cityStart = new Node(loads.get(0).getCityLoad());
        for (Node n: alLCities) {
            if(n.equals(cityStart)) {
                cityStart = n;
            }
        }

        Route route = HamiltonianPath.calculateRoute(neededCities, alLCities, cityStart);
        route.setMaxWeight(calculateMaxWeight(loads));

        return route;
    }

    /**
     * Converts list of DistanceMap to node set
     */
    public static Set<Node> convertToNodeList(List<DistanceMap> distances) {

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
     * @return - Set of cities each of which is a key in a map
     */
    private static Set<City> checkCities(List<Load> loads) {

        // Map contains city and the weight a truck needs to be able to get
        Set<City> cities = new HashSet<>();

        // Get set of all cities we need to visit on the way
        loads.forEach(l -> {
            cities.add(l.getCityLoad());
            cities.add(l.getCityUnload());
        });

        return cities;
    }
}
