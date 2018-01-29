package com.example.kcrimi.geniususers.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kcrimi.geniususers.R;
import com.example.kcrimi.geniususers.presenter.UsersPresenter;

import java.util.ArrayList;

/**
 * Created by kcrimi on 1/29/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    public UsersPresenter usersPresenter;
    private final Context context;

    public UserAdapter(Context parentContext, UsersPresenter usersPresenter) {
        context = parentContext;
        this.usersPresenter = usersPresenter;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vh_user, parent, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        usersPresenter.onBindUserRowAtPosition(holder, position);
    }

    @Override
    public int getItemCount() {
        return usersPresenter.getUserCount();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView userImage;
        private TextView name;
        private TextView bio;

        public UserViewHolder(View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.user_image);
            name = itemView.findViewById(R.id.name_text);
            bio = itemView.findViewById(R.id.bio_text);
            itemView.setOnClickListener(this);
        }

        public void setNameText(String nameString) {
            name.setText(nameString);
        }

        public void setBioText(String bioString) {
            bio.setText(bioString);
        }

        public void setUserImage(Uri imageUri) {
            Glide.with(context).load(imageUri).override(200, 200).into(userImage);
        }

        @Override
        public void onClick(View v) {
            usersPresenter.selectRow(getAdapterPosition());
        }
    }

}
