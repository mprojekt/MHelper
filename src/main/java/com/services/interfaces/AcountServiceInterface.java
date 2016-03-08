package com.services.interfaces;

import com.models.Acount;
import com.models.RegistryForm;
import java.util.List;

public interface AcountServiceInterface {
    
    public void update(Acount acount);
    public void create(RegistryForm registryForm);
    public Acount getByLogin(String login);
    public List<Acount> getAll();
    public boolean existLogin(String login);
}
