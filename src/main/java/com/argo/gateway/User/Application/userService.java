package com.argo.gateway.User.Application;

import com.argo.gateway.User.Application.dto.createAccount;
import com.argo.gateway.User.Application.dto.password;
import com.argo.gateway.User.Roles.domain.IRoles;
import com.argo.gateway.User.Roles.domain.en.roles_enum;
import com.argo.gateway.User.domain.User;
import com.argo.gateway.User.domain.repositroy.IUser;
import com.argo.gateway.Utils.UserUtil;
import com.argo.gateway.Utils.EncodePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class userService {


    private final EncodePassword password;
    private final IUser iUser;
    private final IRoles iRoles;
    private final UserUtil userUtil;


    @Autowired
    public userService(EncodePassword encodePassword, IUser iUser, IRoles iRoles, UserUtil userUtil) {
        this.password = encodePassword;
        this.iUser = iUser;
        this.iRoles = iRoles;
        this.userUtil = userUtil;
    }


    public boolean changePassword(password pw) {

        try {
            this.userUtil.getUser().setPassword(this.password.encodePassword(pw.getPassword()));
            return true;
        } catch (Exception e) {
            return false;
        }


    }

    @Transactional(rollbackFor = Exception.class)
    public boolean createAccount(createAccount dto){

        try {

            User user =new User();
            user.setUsername(dto.getUsername());
            user.setPassword(this.password.encodePassword(dto.getPassword()));
            user.setRoles(this.iRoles.findByRol(roles_enum.ADMIN).get());
            this.iUser.save(user);


            return true;

        }catch (Exception ex){
            return false;
        }


    }


}
