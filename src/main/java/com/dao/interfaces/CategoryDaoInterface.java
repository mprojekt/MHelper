package com.dao.interfaces;

import com.models.Category;

public interface CategoryDaoInterface {
    
    public Category findCategory(String name);
    public boolean existCategory(String name);
}
