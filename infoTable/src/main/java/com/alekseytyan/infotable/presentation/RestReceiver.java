package com.alekseytyan.infotable.presentation;

import com.alekseytyan.infotable.data.DriverStatsDTO;
import com.alekseytyan.infotable.data.LorryStatsDTO;
import com.alekseytyan.infotable.data.OrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.List;

@Named
@Startup
@Stateless
//@ApplicationScoped
public class RestReceiver extends Connector {

    public List<OrderDTO> getOrders() throws JsonProcessingException {
        return getObjectMapper().readValue(
                        getOrderTarget().request("application/json").get(String.class),
                        new TypeReference<List<OrderDTO>>(){}
                );
    }

    public DriverStatsDTO getDriverStats() throws JsonProcessingException {
        return getObjectMapper().readValue(
                getDriverTarget().request("application/json").get(String.class),
                new TypeReference<DriverStatsDTO>(){}
        );
    }

    public LorryStatsDTO getLorryStats() throws JsonProcessingException {
        return getObjectMapper().readValue(
                getLorryTarget().request("application/json").get(String.class),
                new TypeReference<LorryStatsDTO>(){}
        );
    }
}
