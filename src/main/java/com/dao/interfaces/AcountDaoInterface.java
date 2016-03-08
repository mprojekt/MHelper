package com.dao.interfaces;

import com.models.Acount;
import com.models.Passwd;

public interface AcountDaoInterface {
    
    public Acount getByPass(Passwd pass);
}
