package com.GradingSystem.model;

import javax.persistence.*;

/**
 * Created by Didoy on 6/17/2016.
 */
@Entity
@NamedQuery(name ="FindByEmail", query = "select t from Teacher t where t.email = :email")
public class Teacher {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
