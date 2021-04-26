package com.alekseytyan.service.api;

import com.alekseytyan.dto.LoadDTO;
import com.alekseytyan.entity.Load;

public interface LoadService extends AbstractService<Load, LoadDTO, Long> {
    Long findOrderId(Long loadId);
}
