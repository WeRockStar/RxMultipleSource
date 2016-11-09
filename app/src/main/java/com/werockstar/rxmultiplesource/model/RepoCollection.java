package com.werockstar.rxmultiplesource.model;

import com.google.gson.annotations.SerializedName;

public class RepoCollection {

    private int id;
    private String name;

    @SerializedName("full_name")
    private String fullName;

    @SerializedName("owner")
    private Owner owner;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public Owner getOwner() {
        return owner;
    }

    public static class Owner {
        private String login;
        private int id;
        private String followersUrl;
        private String followingUrl;

        public String getLogin() {
            return login;
        }

        public int getId() {
            return id;
        }

        public String getFollowersUrl() {
            return followersUrl;
        }

        public String getFollowingUrl() {
            return followingUrl;
        }
    }
}
