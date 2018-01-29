package com.example.kcrimi.geniususers.networking;

import android.net.Uri;

import com.example.kcrimi.geniususers.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by kcrimi on 1/29/18.
 */

public class ApiService {

    // Mock API which could be swapped out if the app was developed later

    public static AtomicLong userIdIncrementer = new AtomicLong(0);

    private static ApiService apiService;
    private List<User> users;

    public ApiService() {
        users = new ArrayList<>();
//        createBaseUsers();
    }

    private void createBaseUsers() {
        addUser("Kevin", null, "I like drawings");
        addUser("Juliet", null, "Wherefor art thou romeo?");
        addUser("Steve P", null, "Come with me if you want to live");
        addUser("GeoMan", null, "I'm here to save the mid compact cars!");
    }

    public static ApiService getInstance() {
            if (apiService == null) {
                apiService = new ApiService();
            }
            return apiService;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(String name, Uri imageUrl, String bio) {
        users.add(new User(userIdIncrementer.incrementAndGet(), name, imageUrl, bio));
    }

    public User getUser(Long id) {
        for (User user : users) {
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public void updateUser(User user) {
        // Not needed - we are modifying the user directly since both our "DB" and view are referencing the same objects
    }
}
