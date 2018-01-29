package com.example.kcrimi.geniususers.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.kcrimi.geniususers.R;
import com.example.kcrimi.geniususers.presenter.Presenter;
import com.example.kcrimi.geniususers.presenter.UserEditPresenter;

public class UserEditActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_GALLERY = 1;

    public static String EXTRA_USER_ID = "extra_user_id";
    UserEditPresenter presenter = new UserEditPresenter(this);
    boolean editMode;
    private ImageView userImage;
    private ImageView imageEditButton;
    private EditText nameEditText;
    private EditText bioEditText;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        userImage = findViewById(R.id.user_image);
        imageEditButton = findViewById(R.id.edit_user_image);
        nameEditText = findViewById(R.id.name_edit);
        bioEditText = findViewById(R.id.bio_edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editMode) {
                    presenter.saveUserEdits(
                            nameEditText.getText().toString(),
                            bioEditText.getText().toString());
                } else {
                    setEditMode(true);
                }
            }
        });
        imageEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.chooseImage();
            }
        });

        presenter.onViewAttached();
    }


    public void setEditMode(boolean desiredEditMode) {
        editMode = desiredEditMode;
        getSupportActionBar().setDisplayHomeAsUpEnabled(editMode);
        nameEditText.setInputType(editMode ? InputType.TYPE_CLASS_TEXT : InputType.TYPE_NULL);//setFocusable(editMode);
        bioEditText.setInputType(editMode ? InputType.TYPE_CLASS_TEXT : InputType.TYPE_NULL);//setFocusable(editMode);
        imageEditButton.setVisibility(editMode ? View.VISIBLE : View.GONE);
        fab.setImageDrawable(ContextCompat.getDrawable(this,
                editMode ? android.R.drawable.ic_menu_save : android.R.drawable.ic_menu_edit));
    }

    public void setNameText(String nameString) {
        nameEditText.setText(nameString);
    }

    public void setBioText(String bioString) {
        bioEditText.setText(bioString);
    }

    public void setUserImage(Uri imageUri) {
        Glide.with(this).load(imageUri).into(userImage);
    }

    public static void LaunchWithUser(Context context, Long userId) {
        Intent intent = new Intent(context, UserEditActivity.class);
        intent.putExtra(EXTRA_USER_ID, userId);
        context.startActivity(intent);
    }

    public static void LaunchForNewUser(Context context) {
        Intent intent = new Intent(context, UserEditActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK) {
            presenter.onImageSelected(data);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home ) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (editMode) {
            presenter.resetUser();
        } else {
            finish();
        }
    }
}
