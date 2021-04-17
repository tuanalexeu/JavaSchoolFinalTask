package com.alekseytyan.dao.implementation;

import com.alekseytyan.dao.api.LoadDao;
import com.alekseytyan.entity.Load;
import org.springframework.stereotype.Repository;

@Repository
public class LoadDaoImpl extends AbstractDaoImpl<Load> implements LoadDao {
    public LoadDaoImpl() {
        super(Load.class);
    }
}
