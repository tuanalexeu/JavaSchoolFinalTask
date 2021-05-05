package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.Lorry;

import java.util.List;

public interface LorryDao extends AbstractDao<Lorry, String> {
    List<Lorry> findSuitableLorries(int weight);
}
