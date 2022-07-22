package com.models;

public class User {

    private Integer user_Id;
    private String first_name;
    private String last_name;
    private String email;
    private String username;
    private String pass_word;
    private String zodiac_sign;
    private String mood;



    public User(){

    }


    public User(Integer user_Id, String first_name, String last_name, String email, String username, String pass_word, String zodiac_sign, String mood) {
        this.user_Id = user_Id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.pass_word = pass_word;
        this.zodiac_sign = zodiac_sign;
        this.mood = mood;
    }

    public Integer getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Integer user_Id) {
        this.user_Id = user_Id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass_word() {
        return pass_word;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public String getZodiac_sign() {
        return zodiac_sign;
    }

    public void setZodiac_sign(String zodiac_sign) {
        this.zodiac_sign = zodiac_sign;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public User(String first_name, String last_name, String email, String username, String pass_word, String zodiac_sign) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.username = username;
        this.pass_word = pass_word;
        this.zodiac_sign = zodiac_sign;
    }

    public User(String username, String pass_word) {
        this.username = username;
        this.pass_word = pass_word;
    }

    public User(Integer userid, String mood){
        this.user_Id = userid;
        this.mood = mood;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_Id=" + user_Id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
