package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.dto.LorryDTO;
import com.alekseytyan.logiweb.dto.OrderDTO;
import com.alekseytyan.logiweb.entity.Lorry;

import java.util.List;

public interface LorryService extends AbstractService<Lorry, LorryDTO, String> {
    List<LorryDTO> findSuitableLorries(OrderDTO orderDTO);
}
