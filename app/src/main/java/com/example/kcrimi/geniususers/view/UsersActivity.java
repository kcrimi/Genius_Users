package com.example.kcrimi.geniususers.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.kcrimi.geniususers.R;
import com.example.kcrimi.geniususers.adapter.UserAdapter;
import com.example.kcrimi.geniususers.presenter.UsersPresenter;

public class UsersActivity extends AppCompatActivity {

    private UsersPresenter presenter = new UsersPresenter(this);
    private UserAdapter adapter = new UserAdapter(presenter);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        RecyclerView recyclerView = findViewById(R.id.user_recycler);
        FloatingActionButton fab = findViewById(R.id.fab);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addNewUser();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onViewAttached();
    }

    public void updateRecycler() {
        adapter.notifyDataSetChanged();
    }
}
