package com.dao.interfaces;

import com.models.Passwd;

public interface PasswdDaoInterface {
    
    public Passwd getByLogin(String login);
    public boolean existLogin(String login);
}
