package com.alekseytyan.infotable.presentation;

import com.alekseytyan.infotable.data.DriverStatsDTO;
import com.alekseytyan.infotable.data.LorryStatsDTO;
import com.alekseytyan.infotable.data.OrderStatsDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.primefaces.model.chart.PieChartModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Named
@Startup
@Stateless
@Dependent
public class RestReceiver extends Connector {

    private static final Logger logger = LoggerFactory.getLogger(RestReceiver.class);

    public List<OrderStatsDTO> getOrders() throws JsonProcessingException {

        logger.info("getOrders()");

        return getObjectMapper().readValue(
                getOrderTarget().request(MediaType.APPLICATION_JSON).get(String.class),
                new TypeReference<List<OrderStatsDTO>>(){}
        );
    }

    public PieChartModel getDriverStats() throws JsonProcessingException {

        logger.info("getDriverStats()");

        DriverStatsDTO statsDTO = getObjectMapper().readValue(
                getDriverTarget().request(MediaType.APPLICATION_JSON).get(String.class),
                new TypeReference<DriverStatsDTO>(){}
        );

        return ChartConverter.getDriverChart("Drivers", statsDTO);
    }

    public PieChartModel getLorryStats() throws JsonProcessingException {

        logger.info("getLorryStats()");


        LorryStatsDTO statsDTO = getObjectMapper().readValue(
                getLorryTarget().request(MediaType.APPLICATION_JSON).get(String.class),
                new TypeReference<LorryStatsDTO>(){}
        );

        return ChartConverter.getTruckChart("Trucks", statsDTO);
    }
}
