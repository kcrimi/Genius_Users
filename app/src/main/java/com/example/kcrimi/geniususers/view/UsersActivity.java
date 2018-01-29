package com.example.kcrimi.geniususers.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kcrimi.geniususers.R;
import com.example.kcrimi.geniususers.adapter.UserAdapter;
import com.example.kcrimi.geniususers.presenter.Presenter;
import com.example.kcrimi.geniususers.presenter.UsersPresenter;

public class UsersActivity extends BaseActivity {

    private UsersPresenter presenter = new UsersPresenter(this);
    private UserAdapter adapter = new UserAdapter(presenter);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        RecyclerView recyclerView = findViewById(R.id.user_recycler);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addNewUser();
            }
        });
        presenter.onViewAttached();
    }

    public void updateRecycler() {
        adapter.notifyDataSetChanged();
    }
}
