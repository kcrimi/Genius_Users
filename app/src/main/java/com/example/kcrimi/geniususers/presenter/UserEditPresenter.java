package com.example.kcrimi.geniususers.presenter;

import com.example.kcrimi.geniususers.model.User;
import com.example.kcrimi.geniususers.networking.ApiService;
import com.example.kcrimi.geniususers.view.UserEditActivity;

import static com.example.kcrimi.geniususers.view.UserEditActivity.EXTRA_USER_ID;

/**
 * Created by kcrimi on 1/29/18.
 */

public class UserEditPresenter extends BasePresenter<UserEditActivity> {

    private User user;

    public UserEditPresenter(UserEditActivity view) {
        super(view);
    }

    @Override
    public void onViewAttached() {
        long selectedUserId = view.getIntent().getLongExtra(EXTRA_USER_ID, -1);
        view.setEditMode(selectedUserId < 0);
        if (selectedUserId > 0) {
            // user selected
            user = ApiService.getInstance().getUser(selectedUserId);
            view.setNameText(user.getName());
            view.setBioText(user.getBio());
        }
    }

    @Override
    public void onViewDetached() {
        //No-Op
    }

    public void saveUserEdits(String name, String bio) {
        if (user == null) {
            ApiService.getInstance().addUser(name, "www", bio);
        } else {
            user.setName(name);
            user.setBio(bio);
            ApiService.getInstance().updateUser(user);
        }
        view.setEditMode(false);
    }
}
