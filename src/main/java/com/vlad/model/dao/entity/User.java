package com.vlad.model.dao.entity;

import com.vlad.model.dao.UserDAO;

public class User {
    private int id;
    private String login;
    private String password;
    private String permissions;
    private String language;
    private Double bill;
    private String name;
    private String surname;
    private String telephone;
    private Double spent;

    public User() {
    }

    public User(int id, String login, String password, String permissions, String language, Double bill, String name, String surname, String telephone, Double spent) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.permissions = permissions;
        this.language = language;
        this.bill = bill;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.spent = spent;
    }

    public User(String login, String password, String permissions) {
        this.login = login;
        this.password = password;
        this.permissions = permissions;
    }

    public static User createUser(String login, String password, String permissions, UserDAO userDAO){
        User user = new User(login, password, permissions);
        userDAO.createUser(user);
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String rights) {
        this.permissions = permissions;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getBill() {
        return bill;
    }

    public void setBill(Double bill) {
        this.bill = bill;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Double getSpent() {
        return spent;
    }

    public void setSpent(Double spent) {
        this.spent = spent;
    }
}