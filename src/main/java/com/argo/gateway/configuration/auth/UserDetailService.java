package com.argo.gateway.configuration.auth;

import com.argo.gateway.User.domain.User;
import com.argo.gateway.User.domain.repositroy.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {


    @Autowired
    private IUser iUser;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {



        Optional<User> optionalUser = iUser.findByUsername(s);
        User user = optionalUser.orElseThrow(() -> {
            throw new RuntimeException("User not found");
        });

        return new UserAuth(user.getUsername(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+user.getRoles().getRol().toString())));


    }
}
