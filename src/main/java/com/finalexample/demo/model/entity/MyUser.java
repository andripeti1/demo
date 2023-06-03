package com.finalexample.demo.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@Data
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Email")
    private String email;

    @Column(name = "Username")
    private String username;

    @Column(name = "Password")
    private String password;

    @Column(name = "Authority")
    private String authority;
}
