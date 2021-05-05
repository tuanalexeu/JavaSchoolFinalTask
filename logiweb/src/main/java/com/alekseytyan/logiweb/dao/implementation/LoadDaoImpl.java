package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.LoadDao;
import com.alekseytyan.logiweb.entity.Load;
import org.springframework.stereotype.Repository;

@Repository
public class LoadDaoImpl extends AbstractDaoImpl<Load, Long> implements LoadDao {
    public LoadDaoImpl() {
        super(Load.class);
    }
}
