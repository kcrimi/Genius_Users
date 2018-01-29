package com.example.kcrimi.geniususers.presenter;

import com.example.kcrimi.geniususers.adapter.UserAdapter;
import com.example.kcrimi.geniususers.model.User;
import com.example.kcrimi.geniususers.networking.ApiService;
import com.example.kcrimi.geniususers.view.UsersActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kcrimi on 1/29/18.
 */

public class UsersPresenter extends BasePresenter<UsersActivity> {

    private List<User> users = new ArrayList<>();

    public UsersPresenter(UsersActivity view) {
        super(view);
    }

    public void addNewUser() {
        // Launch intent for profile creation
    }

    public void selectRow(int position) {
        // Launch intent for profile view
    }

    public int getUserCount() {
        return users.size();
    }

    public void onBindUserRowAtPosition(UserAdapter.UserViewHolder holder, int position){
        User user = users.get(position);
        holder.setNameText(user.getName());
        holder.setBioText(user.getBio());
    }

    public void retrieveUserData() {
        users.clear();
        users.addAll(ApiService.getInstance().getUsers());
        view.updateRecycler();
    }

    @Override
    public void onViewAttached() {
        retrieveUserData();
    }

    @Override
    public void onViewDetached() {
        // NO - OP
    }
}
