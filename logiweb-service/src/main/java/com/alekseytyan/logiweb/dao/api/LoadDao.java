package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.Load;

import java.util.List;

/**
 * Data access object to work with Load table
 */
public interface LoadDao extends AbstractDao<Load, Long> {
    Load findByToken(String token);

    List<Load> findAllByClientId(String clientId);
}
