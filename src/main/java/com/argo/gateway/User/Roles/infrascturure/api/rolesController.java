package com.argo.gateway.User.Roles.infrascturure.api;

import com.argo.gateway.User.Roles.application.dto.roldto;
import com.argo.gateway.User.Roles.application.rolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class rolesController {



    private final rolesService service;

    @Autowired
    public rolesController(rolesService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<?> crearRol(@RequestBody roldto roldto){


        if(!this.service.crearRol(roldto)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
