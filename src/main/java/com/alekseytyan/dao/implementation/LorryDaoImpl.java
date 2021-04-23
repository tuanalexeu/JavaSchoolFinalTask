package com.alekseytyan.dao.implementation;

import com.alekseytyan.dao.api.LorryDao;
import com.alekseytyan.entity.Lorry;
import org.springframework.stereotype.Repository;

@Repository
public class LorryDaoImpl extends AbstractDaoImpl<Lorry, String> implements LorryDao {
    public LorryDaoImpl() {
        super(Lorry.class);
    }
}
