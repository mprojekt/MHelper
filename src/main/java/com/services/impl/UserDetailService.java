package com.services.impl;

import com.models.Acount;
import com.models.UserDetailAdapter;
import com.services.interfaces.AcountServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailService implements UserDetailsService{
    
    @Autowired(required=true)
    @Qualifier(value="acountService")
    private AcountServiceInterface acountService;

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        Acount acount = this.acountService.getByLogin(string);
                
        if (acount == null) {
            throw new UsernameNotFoundException("Nie ma takiego użytkownika: " + string);
        } else if (acount.getPasswd().getRoleUser() == null) {
            throw new UsernameNotFoundException("Użytkowik " + string + " nie ma żadnych praw");
        }
        
        UserDetailAdapter user = new UserDetailAdapter(acount);
        return user;
    }

}
