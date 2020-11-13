package com.argo.gateway.User.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "user_details")
public class UserDetails implements Serializable {

    @Id
    private int idUser;


    @OneToOne
    @JoinColumn(name = "id_user",nullable = false)
    @MapsId
    private User userId;


    @Column(name = "name",length = 100,nullable = false)
    private String name;

    @Column(name = "last_name",length = 100,nullable = false)
    private String lastName;

    @Column(name = "email",length = 100,nullable = false)
    private String email;

    @Column(name = "phone",length = 100,nullable = false)
    private String cell;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
}
