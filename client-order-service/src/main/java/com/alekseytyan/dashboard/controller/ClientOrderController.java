package com.alekseytyan.dashboard.controller;

import com.alekseytyan.dashboard.dto.ClientLoadDTO;
import com.alekseytyan.dashboard.dto.StatusDTO;
import com.alekseytyan.dashboard.service.api.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Controller
@AllArgsConstructor
@PropertySource("classpath:general.properties")
public class ClientOrderController {

    private final Environment env;

    private final UserService userService;

    @GetMapping(value = "/make-order")
    public String makeOrder(Model model, @RequestParam ClientLoadDTO clientLoadDTO) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String orderToken = UUID.randomUUID().toString();

        ObjectMapper mapper = new ObjectMapper();

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpPost request = new HttpPost(env.getProperty("logiweb-host") + "/save-client-order");

            ArrayList<NameValuePair> postParameters = new ArrayList<>();
            postParameters.add(new BasicNameValuePair("clientId",auth.getName()));
            postParameters.add(new BasicNameValuePair("cityLoad", clientLoadDTO.getCityLoad()));
            postParameters.add(new BasicNameValuePair("cityUnload", clientLoadDTO.getCityUnload()));
            postParameters.add(new BasicNameValuePair("name", clientLoadDTO.getName()));
            postParameters.add(new BasicNameValuePair("weight", String.valueOf(clientLoadDTO.getWeight())));

            postParameters.add(new BasicNameValuePair("orderToken", orderToken));

            request.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));

            StatusDTO response = client.execute(request, httpResponse ->
                    mapper.readValue(httpResponse.getEntity().getContent(), StatusDTO.class));


            if(response.isSuccess()) {
                model.addAttribute("message", "Your order has been successfully created");
            } else {
                model.addAttribute("message", "Something went wrong with your order");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "dashboard-extended";
    }

    @GetMapping(value = "/find-order")
    public String findOrder(Model model, @RequestParam String orderToken) {

        ObjectMapper mapper = new ObjectMapper();

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet(env.getProperty("logiweb-host") + "/find-client-order?orderToken=" + orderToken);

            ClientLoadDTO response = client.execute(request, httpResponse ->
                    mapper.readValue(httpResponse.getEntity().getContent(), ClientLoadDTO.class));


            if(response != null) {
                model.addAttribute("order", response);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "dashboard-extended";
    }

}
