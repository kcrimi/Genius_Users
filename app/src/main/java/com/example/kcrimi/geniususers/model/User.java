package com.example.kcrimi.geniususers.model;

import android.net.Uri;

/**
 * Created by kcrimi on 1/29/18.
 */

public class User {

    private long id;
    private String name;
    private Uri imageUri;
    private String bio;

    public User(long id, String name, Uri imageUri, String bio) {
        this.id = id;
        this.name = name;
        this.imageUri = imageUri;
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

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
