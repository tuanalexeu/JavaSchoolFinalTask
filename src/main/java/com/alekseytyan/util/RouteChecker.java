package com.alekseytyan.util;

import com.alekseytyan.entity.City;
import com.alekseytyan.entity.Load;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter @Setter
public class RouteChecker {

    private List<Load> loads;
    private List<com.alekseytyan.entity.Map> maps;
    private Map<City, Integer> cities;

    public RouteChecker(List<Load>                          loads,
                        List<com.alekseytyan.entity.Map>    maps) {
        this.loads = loads;
        this.maps = maps;
        this.cities = checkCities();
    }

    public Route calculateRoute() {
        MutableValueGraph<City, Integer> weightedGraph = ValueGraphBuilder.directed().build();

        for (com.alekseytyan.entity.Map m: maps) {
            weightedGraph.putEdgeValue(m.getCityFrom(), m.getCityTo(), m.getDistance());
        }

        // TODO traverse graph and decide whether the route is possible or not

        return null;
    }

    public int calculateRouteDistance() {
        return 0; // TODO implement
    }

    public int calculateRouteTime() {
        return 0; // TODO implement
    }

    /**
     * Calculates max weight on the way
     * @return - weight as int number
     */
    public int calculateMaxWeight() {

        int maxWeight = 0;

        for (Map.Entry<City, Integer> k: cities.entrySet()) {

            int currentWeight = maxWeight;

            for (Load l: loads) {
                if(k.getKey().equals(l.getCityLoad())) {
                    currentWeight += l.getWeight();
                } else if(k.getKey().equals(l.getCityUnload())) {
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
    private Map<City, Integer> checkCities() {

        // Map contains city and the weight a truck needs to be able to get
        Map<City, Integer> routeWeight = new HashMap<>();

        // Get set of all cities we need to visit on the way
        loads.forEach(l -> {
            routeWeight.put(l.getCityLoad(), 0);
            routeWeight.put(l.getCityUnload(), 0);
        });

        return routeWeight;
    }
}
