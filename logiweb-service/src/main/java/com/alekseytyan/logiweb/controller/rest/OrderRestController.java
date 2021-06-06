package com.alekseytyan.logiweb.controller.rest;

import com.alekseytyan.logiweb.dto.ClientLoadDTO;
import com.alekseytyan.logiweb.dto.GenericResponse;
import com.alekseytyan.logiweb.dto.StatusDTO;
import com.alekseytyan.logiweb.entity.Order;
import com.alekseytyan.logiweb.entity.enums.LoadStatus;
import com.alekseytyan.logiweb.service.api.LoadService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class OrderRestController {

    private static final Logger logger = LoggerFactory.getLogger(OrderRestController.class);

    private final LoadService loadService;

    @PostMapping(value = "/save-client-order", produces = "application/json")
    public StatusDTO saveClientOrder(@RequestParam String clientId,
                                     @RequestParam String cityLoad,
                                     @RequestParam String cityUnload,
                                     @RequestParam String name,
                                     @RequestParam Integer weight,
                                     @RequestParam String status,
                                     @RequestParam String orderToken) {

        logger.info("External request to save client order. Client ID:" + clientId);

        ClientLoadDTO clientLoadDTO = new ClientLoadDTO(
                clientId, cityLoad, cityUnload, name,
                weight, LoadStatus.valueOf(status), orderToken
        );

        return loadService.saveClientLoad(clientLoadDTO) != null
                ? new StatusDTO(true)
                : new StatusDTO(false);
    }

    @GetMapping(value = "/find-client-order", produces = "application/json")
    public GenericResponse<ClientLoadDTO> findClientOrder(@RequestParam String orderToken) {

        logger.info("External request to find client order. Order token:" + orderToken);

        return new GenericResponse<>(ClientLoadDTO.convert(loadService.findByToken(orderToken)));
    }

    @GetMapping(value = "/find-client-orders", produces = "application/json")
    public List<ClientLoadDTO> findClientOrders(@RequestParam String clientId) {

        logger.info("External request to find all client orders. Client ID:" + clientId);

        return loadService.findAllByClientId(clientId)
                .stream()
                .map(ClientLoadDTO::convert)
                .collect(Collectors.toList());
    }

}
