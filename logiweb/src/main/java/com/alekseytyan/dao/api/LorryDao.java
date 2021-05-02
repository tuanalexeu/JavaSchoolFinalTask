package com.alekseytyan.dao.api;

import com.alekseytyan.entity.Lorry;

import java.util.List;

public interface LorryDao extends AbstractDao<Lorry, String> {
    List<Lorry> findSuitableLorries(int weight);
}
