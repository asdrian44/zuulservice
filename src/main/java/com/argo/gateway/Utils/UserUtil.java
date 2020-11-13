package com.argo.gateway.Utils;

import com.argo.gateway.User.domain.User;
import com.argo.gateway.User.domain.repositroy.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserUtil {


    private final IUser iUser;

    @Autowired
    public UserUtil(IUser iUser) {
        this.iUser = iUser;
    }

    public User getUser() {


        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return this.iUser.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));


    }
}
