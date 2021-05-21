package com.alekseytyan.infotable.presentation;

import com.alekseytyan.infotable.data.DriverStatsDTO;
import com.alekseytyan.infotable.data.LorryStatsDTO;
import org.primefaces.model.chart.PieChartModel;

public class ChartConverter {

    public static PieChartModel getDriverChart(String title, DriverStatsDTO driverStats) {

        PieChartModel model = new PieChartModel();
        model.setTitle(title);

        model.set("Available", driverStats.getAvailable());
        model.set("Unavailable", driverStats.getUnavailable());

        model.setShowDataLabels(true);
        model.setLegendPosition("w");
        model.setShadow(true);

        return model;
    }

    public static PieChartModel getTruckChart(String title, LorryStatsDTO lorryStats) {

        PieChartModel model = new PieChartModel();
        model.setTitle(title);

        model.set("Available", lorryStats.getAvailable());
        model.set("Unavailable", lorryStats.getUnavailable());
        model.set("Broken", lorryStats.getBroken());

        model.setShowDataLabels(true);
        model.setLegendPosition("w");
        model.setShadow(true);

        return model;
    }

}
