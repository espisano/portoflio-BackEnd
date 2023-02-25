
package com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Service;

import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Entity.MainUser;
import com.portfolioargentinaprograma.ernesto.salvador.pisano.Security.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ImpUserDetailsService implements UserDetailsService{
    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getByUserName(userName).get();
        return MainUser.build(user);
        
    }
    
}
