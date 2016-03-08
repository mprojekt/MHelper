package com.services.impl;

import com.dao.impl.*;
import com.models.*;
import com.services.interfaces.AcountServiceInterface;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="acountService")
@Transactional
public class AcountService implements AcountServiceInterface{
    
    @Inject private BCryptPasswordEncoder passwordEncoder;
    @Inject private AcountDao acountDao;
    @Inject private PasswdDao passwdDao;
    @Inject private RoleDao roleDao;

    @Override
    public void update(Acount acount) {
        Date date = new Date();
        acount.setDateModify(date);
        this.acountDao.update(acount);        
    }

    @Override
    public Acount getByLogin(String login) {
        Passwd pass = this.passwdDao.getByLogin(login);
        return this.acountDao.getByPass(pass);
    }

    @Override
    public List<Acount> getAll() {
        return this.acountDao.getAll();
    }

    @Override
    public void create(RegistryForm registryForm) {
        String encodePassword = this.passwordEncoder.encode(registryForm.getPassword());
        Date date = new Date();
        
        Role role = this.roleDao.get(2);
        
        Passwd pass = new Passwd();
        pass.setLogin(registryForm.getLogin());
        pass.setPassword(encodePassword);
        pass.setRoleUser(role);
        
        Acount acount = new Acount();
        acount.setName(registryForm.getName());
        acount.setMail(registryForm.getMail());
        acount.setPasswd(pass);
        acount.setDateCreated(date);
        acount.setEnabled(true);
        
        this.acountDao.create(acount);
    }

    @Override
    public boolean existLogin(String login) {
        return this.passwdDao.existLogin(login);
    }

}
