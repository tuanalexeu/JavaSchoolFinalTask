package com.alekseytyan.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Class contain information about loads which don't have both LOADING & UNLOADING points on the route
 */
@AllArgsConstructor
@Getter @Setter
public class LoadChecker {

    /**
     * Message describing error
     */
    private String message;

    /**
     * Indices of error loads
     */
    private List<Integer> loadIndices;

    /**
     * Method tells whether there are errors or not
     * @return - true, if there are
     */
    public boolean hasErrors() {
        return !loadIndices.isEmpty();
    }

    @Override
    public String toString() {
        return message + " " + loadIndices;
    }
}
