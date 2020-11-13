package com.argo.gateway.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncodePassword {


    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public EncodePassword(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public String encodePassword(String pw) {

        return this.passwordEncoder.encode(pw);

    }
}
