package com.alekseytyan.logiweb.controller.rest;

import com.alekseytyan.logiweb.dto.ClientLoadDTO;
import com.alekseytyan.logiweb.dto.StatusDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

    @PostMapping(value = "/save-client-order")
    public StatusDTO saveClientOrder(@RequestParam String clientId,
                                     @RequestParam String cityLoad,
                                     @RequestParam String cityUnload,
                                     @RequestParam String name,
                                     @RequestParam Integer weight,
                                     @RequestParam String orderToken) {
        ClientLoadDTO clientLoadDTO = new ClientLoadDTO(clientId, cityLoad, cityUnload, name, weight, orderToken);
        return null;
    }

}
