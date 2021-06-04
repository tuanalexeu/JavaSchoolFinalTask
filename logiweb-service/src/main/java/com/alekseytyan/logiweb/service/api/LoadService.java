package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.dto.ClientLoadDTO;
import com.alekseytyan.logiweb.dto.LoadDTO;
import com.alekseytyan.logiweb.entity.Load;

import java.util.List;

public interface LoadService extends AbstractService<Load, LoadDTO, Long> {
    Long findOrderId(Long loadId);

    LoadDTO findByToken(String token);

    List<LoadDTO> findAllByClientId(String clientId);

    LoadDTO saveClientLoad(ClientLoadDTO clientLoadDTO);

}
