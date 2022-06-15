package com.cg.dto;

public class UserDTO {

    private int id;
    private String fullName;
    private String phone;
    private String cityName;
    private String address;

    public UserDTO() {
    }

    public UserDTO(int id, String fullName, String phone, String cityName, String address) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.cityName = cityName;
        this.address = address;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
