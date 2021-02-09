package com.farmers.buyers.modules.profile;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.farmers.buyers.R;
import com.farmers.buyers.common.provider.PermissionProvider;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView backImage;
    private Button saveBtn;
    private TextView changeCoverImageTv;
    private TextView changeProfileImageTv;
    private PermissionProvider permissionProvider;
    private int CAMERA_PROVIDER_CODE = 556;
    private int GALLERY_PROVIDER_CODE = 667;
    private int PERMISSION_REQUEST_CODE = 111;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        init();
        listener();
    }

    private void init() {
        backImage = findViewById(R.id.edi_profile_back_image);
        saveBtn = findViewById(R.id.edit_profile_save_btn);
        changeCoverImageTv = findViewById(R.id.edit_profile_change_cover_image_tv);
        changeProfileImageTv = findViewById(R.id.edit_profile_change_profile_tv);
        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissionProvider = new PermissionProvider(this, permissions, PERMISSION_REQUEST_CODE);
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
                edit_profile_dialog();
            }
        });

       changeCoverImageTv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             permissionProvider.requestPermissions();
           }
       });
    }

    public void edit_profile_dialog() {

        final Dialog dialog = new Dialog(this, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.edit_profile_dialog);


        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
      
        LayoutInflater li = LayoutInflater.from(this);
        View promptsView = li.inflate(R.layout.edit_profile_dialog, null);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.NewDialog);
        alertDialogBuilder.setView(promptsView);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Boolean isGranted = permissionProvider.processPermissionResult(requestCode, permissions, grantResults);
        if (isGranted) {
            Log.e("permission", "granted");
        }
    }
}