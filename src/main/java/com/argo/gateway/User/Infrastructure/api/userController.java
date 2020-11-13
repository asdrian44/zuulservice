package com.argo.gateway.User.Infrastructure.api;

import com.argo.gateway.User.Application.dto.createAccount;
import com.argo.gateway.User.Application.dto.password;
import com.argo.gateway.User.Application.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class userController {


    private final userService service;

    @Autowired
    public userController(userService service) {
        this.service = service;
    }


    @PutMapping("/user")
    public ResponseEntity<?> changePassword(@RequestBody password pw) {


        if (!this.service.changePassword(pw)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody createAccount account) {


        if (!this.service.createAccount(account)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
