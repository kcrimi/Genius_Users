package com.example.kcrimi.geniususers.model;

/**
 * Created by kcrimi on 1/29/18.
 */

public class User {

    private long id;
    private String name;
    private String imageUrl;
    private String bio;

    public User(long id, String name, String imageUrl, String bio) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.bio = bio;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
