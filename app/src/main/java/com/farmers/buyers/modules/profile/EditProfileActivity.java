package com.farmers.buyers.modules.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

            }
        });
    }
}