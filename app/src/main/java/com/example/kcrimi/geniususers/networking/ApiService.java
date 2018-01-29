package com.example.kcrimi.geniususers.networking;

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

    public void addUser(String name, String imageUrl, String bio) {
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
}
