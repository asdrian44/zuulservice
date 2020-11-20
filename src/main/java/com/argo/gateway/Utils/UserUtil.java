package com.argo.gateway.Utils;

import com.argo.gateway.config.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserUtil {


    private final IUser iUser;

    @Autowired
    public UserUtil(IUser iUser) {
        this.iUser = iUser;
    }

    public UserDTO getUser() {


        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return this.iUser.getUser(username);


    }
}
