package com.alekseytyan.logiweb.util.pathfinding;

import com.alekseytyan.logiweb.entity.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Formatter;
import java.util.List;
import java.util.Objects;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    private boolean isPossible;

    private List<City> cityList;

    private int distance;
    private int time;
    private int maxWeight;

    @Override
    public String toString() {
        return new Formatter()
                .format("Is possible: %b\n" +
                        "Route: %s\n" +
                        "Distance: %d\n" +
                        "Time: %d\n" +
                        "Max weight: %d\n",
                        isPossible, cityList, distance, time, maxWeight)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Route)) return false;
        Route route = (Route) o;
        return isPossible == route.isPossible
                && cityList.equals(route.cityList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isPossible, cityList);
    }
}
