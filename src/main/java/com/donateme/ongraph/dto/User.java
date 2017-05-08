package com.donateme.ongraph.dto;


import java.util.Date;

public class
User {
    private int userId;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Date dob;
    private String mobileNo;
    private String address;
    private int role;
    private String bloodGroup;
    private String accessToken;
    private Boolean donationEn;
    private Boolean requestEn;
    private int donationId;
    private int cityId;
    private Boolean enable=true;


    public int getUserId(){return this.userId;}
    public void setUserId(int userId){this.userId=userId;}

    public String getUserName() {
        return this.userName;

    }

    public int getRole() {
        return this.role;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return this.firstName;

    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAddress() {
        return this.address;

    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public Date getDob() {
        return this.dob;
    }

    public String getBloodGroup() {
        return this.bloodGroup;

    }



    public void setUserName(String userName) {
        this.userName = userName;

    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;

    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;

    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;

    }


    public String getAccessToken() {
        return this.accessToken;
    }
    public void setAccessToken(String at){this.accessToken=at;}
    public Boolean getDonationEn(){return this.donationEn;}
    public void setDonationEn(Boolean donationEn){this.donationEn=donationEn;}
    public Boolean getRequestEn(){return this.requestEn;}
    public void setRequestEn(Boolean requestEn){this.requestEn=requestEn;}
    public int getCityId(){return this.cityId;}
    public void setCityId(int cityId){this.cityId=cityId;}
    public int getDonationId(){return this.donationId;}
    public void setDonationId(int donationId){this.donationId=donationId;}
    public Boolean getEnable(){return this.enable;}
    public void setEnable(Boolean enable){this.enable=enable;}
}