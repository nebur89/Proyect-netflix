package com.everis.d4i.tutorial.entities;

import com.everis.d4i.tutorial.entities.enums.RoleUser;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;


    //**ATTRIBUTES *************************

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "NICK_USER", unique = true, nullable = false)
    private String  nickUser;

    @Column(name = "EMAIL_USER", unique = true, nullable = false)
    private String   emailUser;

    @Column(name = "ROLE_USER", unique = true, nullable = false)
    private RoleUser roleUser;

    @Column(name = "PASSWORD_USER", unique = true, nullable = false)
    private String   passwordUser;

    @Column(name = "NAME_USER", unique = true, nullable = false)
    private String   nameUser;


    //**BUILDER *******************************

    public User() {}

    public User(Integer userId, String nickUser, String emailUser, RoleUser roleUser, String passwordUser, String nameUser) {
        this.userId = userId;
        this.nickUser = nickUser;
        this.emailUser = emailUser;
        this.roleUser = roleUser;
        this.passwordUser = passwordUser;
        this.nameUser = nameUser;
    }

//***GET AND SET ****************************************


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getNickUser() {
        return nickUser;
    }

    public void setNickUser(String nickUser) {
        this.nickUser = nickUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}
