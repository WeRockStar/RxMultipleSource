package com.werockstar.rxmultiplesource.model;


import com.google.gson.annotations.SerializedName;

public class UserInfoCollection {
    @SerializedName("login")
    private String login;

    @SerializedName("name")
    private String fullName;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("company")
    private String company;

    @SerializedName("location")
    private String location;

    public String getLogin() {
        return login;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }
}
