package com.farmers.buyers.modules.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

    public void edit_profile_dialog(Activity activity) {

<<<<<<< HEAD
        final Dialog dialog = new Dialog(activity, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.edit_profile_dialog);


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
=======
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.edit_profile_dialog, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity, R.style.NewDialog);
        alertDialogBuilder.setView(promptsView);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
>>>>>>> 2d7db8b34a731b00e0152e1313715f5a0039cc9b
    }

}