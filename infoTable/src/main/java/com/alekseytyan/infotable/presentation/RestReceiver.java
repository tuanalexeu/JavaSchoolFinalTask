package com.alekseytyan.infotable.presentation;

import com.alekseytyan.infotable.data.DriverStatsDTO;
import com.alekseytyan.infotable.data.LorryStatsDTO;
import com.alekseytyan.infotable.data.OrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Named
@Startup
@Stateless
public class RestReceiver extends Connector {

    private static final Logger logger = LoggerFactory.getLogger(RestReceiver.class);

    public List<OrderDTO> getOrders() throws JsonProcessingException {

        logger.info("getOrders()");

        return getObjectMapper().readValue(
                        getOrderTarget().request(MediaType.APPLICATION_JSON).get(String.class),
                        new TypeReference<List<OrderDTO>>(){}
                );
    }

    public DriverStatsDTO getDriverStats() throws JsonProcessingException {

        logger.info("getDriverStats()");

        return getObjectMapper().readValue(
                getDriverTarget().request(MediaType.APPLICATION_JSON).get(String.class),
                new TypeReference<DriverStatsDTO>(){}
        );
    }

    public LorryStatsDTO getLorryStats() throws JsonProcessingException {

        logger.info("getLorryStats()");


        return getObjectMapper().readValue(
                getLorryTarget().request(MediaType.APPLICATION_JSON).get(String.class),
                new TypeReference<LorryStatsDTO>(){}
        );
    }
}
