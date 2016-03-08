package com.dao.impl;

import com.models.Passwd;
import com.dao.interfaces.PasswdDaoInterface;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class PasswdDao extends CRUD<Passwd> implements PasswdDaoInterface{

    @Override
    public Passwd getByLogin(String login) {        
        Criteria c = getSession().createCriteria(Passwd.class);
        c.add(Restrictions.eq("login", login));
        List<Passwd> tmp = c.list();
        if(!tmp.isEmpty()){
            return tmp.get(0);
        } else
            return null;
    }

    @Override
    public boolean existLogin(String login) {
        Criteria c = getSession().createCriteria(Passwd.class);
        c.add(Restrictions.eq("login", login));
        List<Passwd> tmp = c.list();
        return !tmp.isEmpty();
    }

}
