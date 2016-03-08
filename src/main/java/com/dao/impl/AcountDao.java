package com.dao.impl;

import com.models.Acount;
import com.dao.interfaces.AcountDaoInterface;
import com.models.Passwd;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class AcountDao extends CRUD<Acount> implements AcountDaoInterface{

    @Override
    public Acount getByPass(Passwd pass) {
        Criteria c = getSession().createCriteria(Acount.class);
        List<Acount> tmp = c.add(Restrictions.eq("passwd", pass)).list();
        if(tmp.isEmpty()){
            return null;
        } else {
            return tmp.get(0);
        }
    }

}
