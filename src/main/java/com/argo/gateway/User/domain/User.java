    package com.argo.gateway.User.domain;

import com.argo.gateway.User.Roles.domain.Roles;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "user")
public class User  implements Serializable {


    @Id
    @Column(name = "id_user",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @Column(unique = true,length = 100,nullable = false)
    private String username;

    @Column(name="password",nullable = false)
    private String password;



    @ManyToOne(targetEntity = Roles.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_rol",nullable = false)
    private Roles roles;



    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
