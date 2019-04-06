package com.example.studly.model;

/**
 *  Class containing methods required to create entity of users.
 */

public class User {
    private Integer user_id;
    private String uni ;
    private String login;
    private String name;
    private String email;
    private int points;

    public User(){}

    public User(Integer id, String uni, String login, String name, String email, int points) {
        this.user_id = id;
        this.uni = uni;
        this.login = login;
        this.name = name;
        this.email = email;
        this.points = points;
    }

    public Integer getId() {
        return user_id;
    }

    public void setId(Integer id) {
        this.user_id = id;
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public double getPoints() { return points; }

    public void setPoints(int points) {
        this.points = points;
    }
}