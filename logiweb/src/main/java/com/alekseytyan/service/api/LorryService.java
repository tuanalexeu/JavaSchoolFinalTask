package com.alekseytyan.service.api;

import com.alekseytyan.dto.LorryDTO;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.entity.Lorry;

import java.util.List;

public interface LorryService extends AbstractService<Lorry, LorryDTO, String> {
    List<LorryDTO> findSuitableLorries(OrderDTO orderDTO);
}
