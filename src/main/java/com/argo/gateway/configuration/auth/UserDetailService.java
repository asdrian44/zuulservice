package com.argo.gateway.configuration.auth;

import com.argo.gateway.configuration.infrastructure.consumeApi.dto.UserDTO;
import com.argo.gateway.configuration.infrastructure.consumeApi.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailService implements UserDetailsService {


    @Autowired
    private IUser iUser;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {




        UserDTO userDTO =iUser.getUser(s);

        return new UserAuth(userDTO.getUsername(), userDTO.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+ userDTO.getRoles().getRol().toString())));


    }
}
