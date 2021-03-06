package com.example.gocarrentspringbootapplication.data.po;

import com.example.gocarrentspringbootapplication.repositories.TableNamesRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = TableNamesRepository.USER_DETAILS_TABLE_NAME)
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;

    private String phone;
    private String image;

    @JsonIgnore
    @OneToOne(mappedBy = "userDetails")
    private User user;

    public UserDetails(@NotEmpty String name, @NotEmpty String surname, String phone, String image) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.image = image;
    }

    public UserDetails() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
