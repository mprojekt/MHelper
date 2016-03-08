package com.dao.impl;

import com.dao.interfaces.TagDaoInterface;
import com.models.Tag;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class TagDao extends CRUD<Tag> implements TagDaoInterface{

    @Override
    public Tag findTag(String name) {
        Criteria c = getSession().createCriteria(Tag.class);
        c.add(Restrictions.eq("name", name));
        List<Tag> tmp = c.list();
        if(!tmp.isEmpty()){
            return tmp.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean existTag(String name) {
        Criteria c = getSession().createCriteria(Tag.class);
        c.add(Restrictions.eq("name", name));
        List<Tag> tmp = c.list();
        return !tmp.isEmpty();
    }

    @Override
    public List<Tag> getAllTagsAsc() {
        Criteria c = getSession().createCriteria(Tag.class);
        c.addOrder(Order.asc("name"));
        return c.list();
    }

}
