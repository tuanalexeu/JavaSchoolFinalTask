package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.dto.LoadDTO;
import com.alekseytyan.logiweb.entity.Load;

public interface LoadService extends AbstractService<Load, LoadDTO, Long> {
    Long findOrderId(Long loadId);
}
