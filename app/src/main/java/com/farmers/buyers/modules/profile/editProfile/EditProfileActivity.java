package com.farmers.buyers.modules.profile.editProfile;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
import com.farmers.buyers.common.provider.PermissionProvider;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.addMoney.AddMoneyViewModel;
import com.farmers.buyers.modules.addMoney.AddMoneyWallet;
import com.farmers.buyers.modules.addMoney.model.AddMoneyRequestParams;
import com.farmers.buyers.modules.signUp.model.SignUpApiModel;
import com.farmers.buyers.modules.wallet.WalletActivity;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.farmers.buyers.remote.ApiConstants.IMAGE_PATH_USER;

public class EditProfileActivity extends BaseActivity {

    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(EditProfileViewModel.class)) {
                return (T) new EditProfileViewModel();
            }
            return null;
        }
    };

    private EditProfileViewModel viewModel = factory.create(EditProfileViewModel.class);
    private MutableLiveData<DataFetchState<SignUpApiModel>> stateMachine = new MutableLiveData<>();

    private ImageView backImage;
    private Button saveBtn;
    private TextView changeCoverImageTv;
    private TextView changeProfileImageTv;
    private EditText edit_profile_user_name, ed_profile_phone, ed_profile_email;
    private CircleImageView edit_profile_user_image;
    public AppController appController = AppController.get();
    private static final int USER_IMAGE = 3;
    public boolean User = false;
    public File extStore = null;
    public String docFileName;
    public Uri uriAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        init();
        listener();
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    private void init() {
        backImage = findViewById(R.id.edi_profile_back_image);
        saveBtn = findViewById(R.id.edit_profile_save_btn);
        changeCoverImageTv = findViewById(R.id.edit_profile_change_cover_image_tv);
        changeProfileImageTv = findViewById(R.id.edit_profile_change_profile_tv);
        edit_profile_user_name = findViewById(R.id.edit_profile_user_name);
        ed_profile_email = findViewById(R.id.ed_profile_email);
        ed_profile_phone = findViewById(R.id.ed_profile_phone);
        edit_profile_user_image = findViewById(R.id.edit_profile_user_image);

        edit_profile_user_name.setText(String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("USER_NAME", "")));
        ed_profile_email.setText(String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("USER_EMAIL", "")));
        ed_profile_phone.setText(String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("USER_MOBILE", "")));

        stateMachine.observe(this, signUpApiModelDataFetchState -> {
            switch (signUpApiModelDataFetchState.status) {
                case LOADING: {
                    loading();
                    break;
                }

                case SUCCESS: {
                    success(signUpApiModelDataFetchState.status_message);
                    break;
                }

                case ERROR: {
                    error(signUpApiModelDataFetchState.status_message);
                    break;
                }
            }
        });

    }

    private void loading() {
        showLoader();
    }

    private void success(String msg) {
        dismissLoader();
        edit_profile_dialog(msg);
    }

    private void error(String error) {
        dismissLoader();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private void listener() {
        backImage.setOnClickListener(view -> onBackPressed());

        saveBtn.setOnClickListener(view -> {
            if (!User) {
                Toast.makeText(EditProfileActivity.this, "Please Choose profile picture", Toast.LENGTH_SHORT).show();
            } else {
                editProfile();
            }
        });
        changeProfileImageTv.setOnClickListener(view -> {
            if (checkCameraPermission()) {
                selectImage(USER_IMAGE);
            } else {
                requestPermission();
            }
        });

        changeCoverImageTv.setOnClickListener(view -> {
        });
    }

    private void editProfile() {
        AddMoneyRequestParams addMoneyRequestParams = new AddMoneyRequestParams(AppController.get().getLoginId(),
                edit_profile_user_name.getText().toString().trim(),
                ed_profile_email.getText().toString().trim(),
                new File(IMAGE_PATH_USER.getPath()),
                AppController.get().getAuthenticationKey());

        viewModel.editProfile(stateMachine, addMoneyRequestParams);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(EditProfileActivity.this, new String[]{Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, 100);
    }

    public boolean checkCameraPermission() {
        return (ContextCompat.checkSelfPermission(EditProfileActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(EditProfileActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(EditProfileActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private void selectImage(int imageType) {
        try {
            PackageManager pm = EditProfileActivity.this.getPackageManager();
            int hasPerm = pm.checkPermission(Manifest.permission.CAMERA, EditProfileActivity.this.getPackageName());
            if (hasPerm == PackageManager.PERMISSION_GRANTED) {
                try {
                    Intent intent;
                    if (imageType == USER_IMAGE) {
                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                    } else {
                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra("android.intent.extras.CAMERA_FACING", 0);
                    }

                    File storageDir = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES + File.separator + "FARMER");
                    docFileName = "farmer" + "_" + System.currentTimeMillis();

                    try {
                        extStore = File.createTempFile(
                                docFileName,  /* prefix */
                                ".jpg",         /* suffix */
                                storageDir      /* directory */
                        );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (!extStore.exists()) {
                        extStore.mkdirs();
                    }

                    if (Build.VERSION.SDK_INT >= 24) {
                        try {
                            Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                            m.invoke(null);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(extStore));
                            uriAll = Uri.fromFile(extStore);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(extStore));
                        uriAll = Uri.fromFile(extStore);
                    }

                    if (imageType == USER_IMAGE) {
                       /* Intent intent2 = new Intent(KycUpdate2.this, KycForProfile.class);
                        startActivityForResult(intent2, USER_IMAGE);*/
                        IMAGE_PATH_USER = Uri.fromFile(extStore);
                        startActivityForResult(intent, USER_IMAGE);
                    } else {
                        Log.v("er1", "Camera Permission error");
                        Toast.makeText(EditProfileActivity.this, "Camera Permission error", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap resizedBitmap = null;
        try {
            if (requestCode == USER_IMAGE) {
                File file = new File(IMAGE_PATH_USER.getPath());
                try {
                    Bitmap bmap = BitmapFactory.decodeFile(file.getPath());
                    resizedBitmap = getResizedBitmap(bmap, 500);
                    resizedBitmap.compress(Bitmap.CompressFormat.PNG, 25, new FileOutputStream(file));

                } catch (Exception e) {
                    Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                //  civ_user.setBackgroundColor(Color.WHITE);
                edit_profile_user_image.setImageBitmap(resizedBitmap);
                // civ_user.setRotation(-90);
                User = true;
            }
        } catch (Exception e) {
        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        try {
            int width = image.getWidth();
            int height = image.getHeight();

            float bitmapRatio = (float) width / (float) height;
            if (bitmapRatio > 1) {
                width = maxSize;
                height = (int) (width / bitmapRatio);
            } else {
                height = maxSize;
                width = (int) (height * bitmapRatio);
            }
            return Bitmap.createScaledBitmap(image, width, height, true);
        } catch (Exception e) {
            Toast.makeText(EditProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return image;
    }

    public void edit_profile_dialog(String msg) {

        final Dialog dialog = new Dialog(this, R.style.NewDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.edit_profile_dialog);

        Button bt_ok = dialog.findViewById(R.id.bt_ok);
        TextView tv_profile_msg = dialog.findViewById(R.id.tv_profile_msg);
        tv_profile_msg.setText(msg);
        bt_ok.setOnClickListener(view -> {
            dialog.dismiss();
            finish();
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);
        dialog.show();
    }
}