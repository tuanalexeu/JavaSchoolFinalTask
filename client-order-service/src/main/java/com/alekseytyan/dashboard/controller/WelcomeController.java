package com.alekseytyan.dashboard.controller;

import com.alekseytyan.dashboard.dto.CityDTO;
import com.alekseytyan.dashboard.dto.ClientLoadDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Controller
@PropertySource("classpath:general.properties")
public class WelcomeController {

    private Client loadClient;

    private WebTarget cityTarget;

    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        Client cityClient = ClientBuilder.newClient();
        cityTarget = cityClient.target("http://" + env.getProperty("logiweb-host") + "/get-cities");

        loadClient = ClientBuilder.newClient();

    }

    /**
     * Check if authorized user has any role in Spring security
     * @return - true, if they have
     */
    private boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken);
    }

    @GetMapping(value = "/")
    public String welcomePage() {
        if(isAuthenticated()) {
            return "redirect:/profile";
        }
        return "home-non-auth";
    }

    @GetMapping(value = "/profile")
    public String profile() {
        return "home";
    }

    @SneakyThrows
    @GetMapping(value = "/my-orders")
    public String myOrders(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("newOrder", new ClientLoadDTO());

        ObjectMapper cityMapper = new ObjectMapper();
        ObjectMapper orderMapper = new ObjectMapper();

        WebTarget loadTarget = loadClient.target("http://" + env.getProperty("logiweb-host") + "/find-client-orders&clientId=" + auth.getName());

            List<CityDTO> cityResponse = cityMapper.readValue(
                    cityTarget.request(MediaType.APPLICATION_JSON).get(String.class),
                    new TypeReference<List<CityDTO>>(){}
            );

            List<ClientLoadDTO> orderResponse = orderMapper.readValue(
                    loadTarget.request(MediaType.APPLICATION_JSON).get(String.class),
                    new TypeReference<List<ClientLoadDTO>>(){}
            );

            model.addAttribute("cities", cityResponse);
            model.addAttribute("orders", orderResponse);

        return "my-orders";
    }
}
