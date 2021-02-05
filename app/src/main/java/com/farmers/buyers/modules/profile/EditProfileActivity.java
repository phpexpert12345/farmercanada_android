package com.farmers.buyers.modules.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.farmers.buyers.R;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView backImage;
    private Button saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        init();
        listener();
    }

    private void init() {
        backImage = findViewById(R.id.edit_profile_cover_image);
        saveBtn = findViewById(R.id.edit_profile_save_btn);
    }

    private void listener() {
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_profile_dialog(EditProfileActivity.this);
            }
        });
    }

    private void edit_profile_dialog(Activity activity) {

        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.edit_profile_dialog, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.NewDialog);
        alertDialogBuilder.setView(promptsView);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

}