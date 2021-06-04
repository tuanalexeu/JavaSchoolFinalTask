package com.alekseytyan.logiweb.controller.rest;

import com.alekseytyan.logiweb.dto.ClientLoadDTO;
import com.alekseytyan.logiweb.dto.StatusDTO;
import com.alekseytyan.logiweb.entity.enums.LoadStatus;
import com.alekseytyan.logiweb.service.api.LoadService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class OrderRestController {

    private final LoadService loadService;

    @PostMapping(value = "/save-client-order", produces = "application/json")
    public StatusDTO saveClientOrder(@RequestParam String clientId,
                                     @RequestParam String cityLoad,
                                     @RequestParam String cityUnload,
                                     @RequestParam String name,
                                     @RequestParam Integer weight,
                                     @RequestParam String status,
                                     @RequestParam String orderToken) {

        ClientLoadDTO clientLoadDTO = new ClientLoadDTO(
                clientId, cityLoad, cityUnload, name,
                weight, LoadStatus.valueOf(status), orderToken
        );


        return loadService.saveClientLoad(clientLoadDTO) != null
                ? new StatusDTO(true)
                : new StatusDTO(false);
    }

    @GetMapping(value = "/find-client-order", produces = "application/json")
    public ClientLoadDTO findClientOrder(@RequestParam String orderToken) {
        return ClientLoadDTO.convert(loadService.findByToken(orderToken));
    }

    @GetMapping(value = "/find-client-orders", produces = "application/json")
    public List<ClientLoadDTO> findClientOrders(@RequestParam String clientId) {
        return loadService.findAllByClientId(clientId)
                .stream()
                .map(ClientLoadDTO::convert)
                .collect(Collectors.toList());
    }

}
