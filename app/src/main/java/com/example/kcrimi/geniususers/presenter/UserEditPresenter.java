package com.example.kcrimi.geniususers.presenter;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.example.kcrimi.geniususers.R;
import com.example.kcrimi.geniususers.model.User;
import com.example.kcrimi.geniususers.networking.ApiService;
import com.example.kcrimi.geniususers.view.UserEditActivity;

import static com.example.kcrimi.geniususers.view.UserEditActivity.EXTRA_USER_ID;
import static com.example.kcrimi.geniususers.view.UserEditActivity.REQUEST_CODE_GALLERY;

/**
 * Created by kcrimi on 1/29/18.
 */

public class UserEditPresenter extends BasePresenter<UserEditActivity> {

    private User user;
    private Uri selectedImageUri;

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
            displayUserData();
        }
    }

    private void displayUserData() {
        view.setNameText(user.getName());
        view.setBioText(user.getBio());
        view.setUserImage(user.getImageUri());
    }

    @Override
    public void onViewDetached() {
        //No-Op
    }

    public void saveUserEdits(String name, String bio) {
        if (name.length() <= 0) {
            Toast.makeText(view, view.getString(R.string.name_required), Toast.LENGTH_SHORT).show();
        } else {
            if (user == null) {
                ApiService.getInstance().addUser(name, selectedImageUri, bio);
            } else {
                user.setName(name);
                user.setBio(bio);
                user.setImageUri(selectedImageUri);
                ApiService.getInstance().updateUser(user);
            }
            view.finish();
        }
    }

    public void chooseImage() {
        Intent pickImageIntent = new Intent(Intent.ACTION_PICK);
        pickImageIntent.setType("image/*");
        view.startActivityForResult(pickImageIntent, REQUEST_CODE_GALLERY);
    }

    public void onImageSelected(Intent data) {
        selectedImageUri = data.getData();
        view.setUserImage(selectedImageUri);
    }

    public void resetUser() {
        if (user == null) {
            view.finish();
        } else {
            displayUserData();
            view.setEditMode(false);
        }
    }
}
