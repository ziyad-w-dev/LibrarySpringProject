package com.ziyad.libraryspringproject.domain.entity;


import jakarta.persistence.*;


@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;


    private String userName;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
