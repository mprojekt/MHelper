package com.dao.impl;

import com.dao.interfaces.CategoryDaoInterface;
import com.models.Category;
import com.models.Role;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDao extends CRUD<Category> implements CategoryDaoInterface{

    @Override
    public Category findCategory(String name) {
        Criteria c = getSession().createCriteria(Category.class);
        c.add(Restrictions.eq("name", name));
        List<Category> tmp = c.list();
        if(!tmp.isEmpty()){
            return tmp.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean existCategory(String name) {
        Criteria c = getSession().createCriteria(Category.class);
        c.add(Restrictions.eq("name", name));
        List<Category> tmp = c.list();
        return !tmp.isEmpty();
    }

}
