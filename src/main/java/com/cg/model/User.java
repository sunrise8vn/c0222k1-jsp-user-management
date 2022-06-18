package com.cg.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class User {
    private int id;
    private String fullName;
    private String phone;
    private int cityId;
    private String address;
    private int age;
    private LocalDateTime dob;

    public User() {
    }

    public User(String fullName, String phone, String address) {
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
    }

    public User(int id, String fullName, String phone, String address) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
    }

    public User(String fullName, String phone, int cityId, String address) {
        this.fullName = fullName;
        this.phone = phone;
        this.cityId = cityId;
        this.address = address;
    }

    public User(String fullName, String phone, int cityId, String address, int age) {
        this.fullName = fullName;
        this.phone = phone;
        this.cityId = cityId;
        this.address = address;
        this.age = age;
    }

    public User(int id, String fullName, String phone, int cityId, String address) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.cityId = cityId;
        this.address = address;
    }

    public User(String fullName, String phone, int cityId, String address, int age, LocalDateTime dob) {
        this.fullName = fullName;
        this.phone = phone;
        this.cityId = cityId;
        this.address = address;
        this.age = age;
        this.dob = dob;
    }

    public User(int id, String fullName, String phone, int cityId, String address, int age, LocalDateTime dob) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.cityId = cityId;
        this.address = address;
        this.age = age;
        this.dob = dob;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDateTime getDob() {
        return dob;
    }

    public void setDob(LocalDateTime dob) {
        this.dob = dob;
    }
}
