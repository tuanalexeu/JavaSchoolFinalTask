package com.alekseytyan.infotable.presentation;

import com.alekseytyan.infotable.reader.PropertyReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

@Getter @Setter
public abstract class Connector {

    private static String HOST_NAME = PropertyReader.getPropValue("host");

    private Client orderClient;
    private Client driverClient;
    private Client lorryClient;

    private WebTarget orderTarget;
    private WebTarget driverTarget;
    private WebTarget lorryTarget;

    private ObjectMapper objectMapper;

    protected Connector() {
        orderClient = ClientBuilder.newClient();
        orderTarget = orderClient.target(HOST_NAME + "/info-table/orders");

        driverClient = ClientBuilder.newClient();
        driverTarget = driverClient.target(HOST_NAME + "/info-table/driver-stats");

        lorryClient = ClientBuilder.newClient();
        lorryTarget = lorryClient.target(HOST_NAME + "/info-table/lorry-stats");

        objectMapper = new ObjectMapper();
    }

}
