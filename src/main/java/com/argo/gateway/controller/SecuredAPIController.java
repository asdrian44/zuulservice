package com.argo.gateway.controller;

import com.argo.gateway.services.OAuthServiceToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/oauth")
public class SecuredAPIController {


    private final OAuthServiceToken serviceToken;

    @Autowired
    public SecuredAPIController(OAuthServiceToken serviceToken) {
        this.serviceToken = serviceToken;
    }

    @RequestMapping("/token")
    public ResponseEntity<?> getAuth(HttpServletRequest request) {


        return new ResponseEntity(serviceToken.getToken(request), HttpStatus.OK);


    }
}
