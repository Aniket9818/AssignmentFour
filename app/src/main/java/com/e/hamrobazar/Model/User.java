package com.e.hamrobazar.Model;

public class User {
    private String email;
    private String fullName;
    private String password;
    private String phone;
    private String cityName;
    private String image;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getAreaLocation() {
        return areaLocation;
    }

    public void setAreaLocation(String areaLocation) {
        this.areaLocation = areaLocation;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String mobilePhone;
    private String streetName;
    private String areaLocation;

    public User(String email, String fullName, String password, String phone, String mobilePhone, String streetName, String areaLocation, String cityName, String image) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
        this.mobilePhone = mobilePhone;
        this.streetName = streetName;
        this.areaLocation = areaLocation;
        this.cityName = cityName;
        this.image = image;
    }

}

