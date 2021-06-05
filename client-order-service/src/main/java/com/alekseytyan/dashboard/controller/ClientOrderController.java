package com.alekseytyan.dashboard.controller;

import com.alekseytyan.dashboard.dto.ClientLoadDTO;
import com.alekseytyan.dashboard.dto.StatusDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@PropertySource("classpath:general.properties")
public class ClientOrderController {

    private Client clientFindOrder;

    private WebTarget targetSaveOrder;
    private WebTarget targetFindOrder;

    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        Client clientSaveOrder = ClientBuilder.newClient();
        targetSaveOrder = clientSaveOrder.target("http://" + env.getProperty("logiweb-host") + "/save-client-order");

        clientFindOrder = ClientBuilder.newClient();
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

    @GetMapping(value = "/make-order")
    public String makeOrder(Model model, @RequestParam ClientLoadDTO clientLoadDTO) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String token = UUID.randomUUID().toString();

        ObjectMapper mapper = new ObjectMapper();

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpPost request = new HttpPost("http://" + env.getProperty("logiweb-host") + "/save-client-order");

            ArrayList<NameValuePair> postParameters = new ArrayList<>();
            postParameters.add(new BasicNameValuePair("clientId",auth.getName()));
            postParameters.add(new BasicNameValuePair("cityLoad", clientLoadDTO.getCityLoad()));
            postParameters.add(new BasicNameValuePair("cityUnload", clientLoadDTO.getCityUnload()));
            postParameters.add(new BasicNameValuePair("name", clientLoadDTO.getName()));
            postParameters.add(new BasicNameValuePair("weight", String.valueOf(clientLoadDTO.getWeight())));
            postParameters.add(new BasicNameValuePair("status", clientLoadDTO.getStatus().toString()));
            postParameters.add(new BasicNameValuePair("token", token));

            request.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));

            StatusDTO response = client.execute(request, httpResponse ->
                    mapper.readValue(httpResponse.getEntity().getContent(), StatusDTO.class));


            if(response.isSuccess()) {
                model.addAttribute("message", "Your order has been successfully created");
                model.addAttribute("token", token);
            } else {
                model.addAttribute("message", "Something went wrong with your order");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "dashboard-extended";
    }

    @SneakyThrows
    @GetMapping(value = "/find-order")
    public String findOrder(Model model, @RequestParam String orderToken) {

        ObjectMapper mapper = new ObjectMapper();

        targetFindOrder = clientFindOrder.target("http://" + env.getProperty("logiweb-host") + "/find-client-order&orderToken=" + orderToken);


        ClientLoadDTO response = mapper.readValue(
                targetFindOrder.request(MediaType.APPLICATION_JSON).get(String.class),
                new TypeReference<ClientLoadDTO>(){}
        );

        if(response != null) {
            model.addAttribute("order", response);
        } else {
            model.addAttribute("error", "No such order");
        }

        if(isAuthenticated()) {
            return "dashboard-extended";
        }
        return "home-non-auth";
    }

}
