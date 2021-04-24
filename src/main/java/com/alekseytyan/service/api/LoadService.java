package com.alekseytyan.service.api;

import com.alekseytyan.dto.LoadDTO;
import com.alekseytyan.entity.Load;

import java.util.List;

public interface LoadService extends AbstractService<Load, LoadDTO, Long> {
    List<LoadDTO> findByOrderId(Long orderId);
}
