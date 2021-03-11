package com.farmers.seller.modules.setupSellerAccount.documentUpload;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.farmers.buyers.R;
import com.farmers.buyers.common.utils.AlertHelper;
import com.farmers.buyers.common.utils.CameraProvider;
import com.farmers.buyers.common.utils.OnAlertClickListener;
import com.farmers.buyers.common.utils.Util;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.seller.product.ProductListActivity;
import com.farmers.seller.modules.setupSellerAccount.SetupStoreViewModel;
import com.farmers.seller.modules.setupSellerAccount.model.SetupStoreApiModel;
import com.farmers.seller.modules.setupSellerAccount.storeDetails.StoreSetupExtra;

import java.io.File;
import java.util.ArrayList;

import kotlin.Unit;

public class DocumentUploadActivity extends BaseActivity implements View.OnClickListener {

    private static final int FRONT_DOC_CAMERA_REQUEST_CODE = 501;
    private static final int FRONT_DOC_TWO_CAMERA_REQUEST_CODE = 502;
    private static final int BACK_DOC_CAMERA_REQUEST_CODE = 503;
    private static final int BACK_DOC_TWO_CAMERA_REQUEST_CODE = 504;

    private static final int FRONT_DOC_GALLERY_REQUEST_CODE = 505;
    private static final int FRONT_DOC_TWO_GALLERY_REQUEST_CODE = 506;
    private static final int BACK_DOC_GALLERY_REQUEST_CODE = 507;
    private static final int BACK_DOC_TWO_GALLERY_REQUEST_CODE = 508;

    private SetupStoreViewModel viewModel;
    private MutableLiveData<DataFetchState<SetupStoreApiModel>> stateMachine = new MutableLiveData<>();


    private ImageView img_back;
    private TextView tv_toolbar_name;
    private Button bt_next_document_upload;
    private ImageView docOneFrontImage, docTwoFrontImage, docOneBackImage, docTwoBackImage;

    private StoreSetupExtra extra = new StoreSetupExtra();
    CameraProvider docOneFrontCameraProvider = new CameraProvider(this,
            FRONT_DOC_CAMERA_REQUEST_CODE, FRONT_DOC_GALLERY_REQUEST_CODE);

    CameraProvider docTwoFrontCameraProvider = new CameraProvider(this,
            FRONT_DOC_TWO_CAMERA_REQUEST_CODE, FRONT_DOC_TWO_GALLERY_REQUEST_CODE);

    CameraProvider docOneBackCameraProvider = new CameraProvider(this,
            BACK_DOC_CAMERA_REQUEST_CODE, BACK_DOC_GALLERY_REQUEST_CODE);

    CameraProvider docTwoBackCameraProvider = new CameraProvider(this,
            BACK_DOC_TWO_CAMERA_REQUEST_CODE, BACK_DOC_TWO_GALLERY_REQUEST_CODE);

    private File doc1, doc2, doc3, doc4;
    private String[] PERMISSIONS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_upload);

        img_back = findViewById(R.id.tv_setup_seller_back_img);
        tv_toolbar_name = findViewById(R.id.tv_setup_seller_toolbar_name);
        bt_next_document_upload = findViewById(R.id.bt_next_document_upload);
        docOneFrontImage = findViewById(R.id.store_setup_document_one_front_img);
        docOneBackImage = findViewById(R.id.store_setup_document_one_back_img);
        docTwoFrontImage = findViewById(R.id.store_setup_document_two_front_img);
        docTwoBackImage = findViewById(R.id.store_setup_document_two_back_img);
        PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

        bt_next_document_upload.setOnClickListener(this);
        extra = (StoreSetupExtra) getIntent().getSerializableExtra("storeExtra");

        bindViewModel();
        init();
    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    private void init() {
        Log.e("name", extra.getName());
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        docOneFrontImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkCameraPermission()) {
                    showImagePickerDialog1(FRONT_DOC_CAMERA_REQUEST_CODE,
                            FRONT_DOC_GALLERY_REQUEST_CODE);
                } else {
                    requestPermission();

                }
            }
        });

        docOneBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Util.hasPermissions(DocumentUploadActivity.this, PERMISSIONS)) {
                    ActivityCompat.requestPermissions(DocumentUploadActivity.this, PERMISSIONS, 1);
                } else {
                    showImagePickerDialog2(BACK_DOC_CAMERA_REQUEST_CODE,
                            BACK_DOC_GALLERY_REQUEST_CODE);

                }
            }
        });

        docTwoFrontImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Util.hasPermissions(DocumentUploadActivity.this, PERMISSIONS)) {
                    ActivityCompat.requestPermissions(DocumentUploadActivity.this, PERMISSIONS, 1);
                } else {
                    showImagePickerDialog3(FRONT_DOC_TWO_CAMERA_REQUEST_CODE,
                            FRONT_DOC_TWO_GALLERY_REQUEST_CODE);
                }
            }
        });

        docTwoBackImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkCameraPermission()) {
                    showImagePickerDialog4(BACK_DOC_TWO_CAMERA_REQUEST_CODE,
                            BACK_DOC_TWO_GALLERY_REQUEST_CODE);
                } else {
                    requestPermission();
                }
            }
        });

        stateMachine.observe(this, setupStoreApiModelDataFetchState -> {
            switch (setupStoreApiModelDataFetchState.status) {
                case LOADING:
                    loading();
                    break;
                case SUCCESS: {
                    dismissLoader();
                    Toast.makeText(DocumentUploadActivity.this, setupStoreApiModelDataFetchState.status_message, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DocumentUploadActivity.this, ProductListActivity.class));
                }
                case ERROR:
                    error(setupStoreApiModelDataFetchState.status_message);
                    break;
            }
        });
    }

    private void bindViewModel() {
        ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if (modelClass.isAssignableFrom(SetupStoreViewModel.class)) {
                    return (T) new SetupStoreViewModel((StoreSetupExtra) getIntent().getSerializableExtra("storeExtra"));
                } else return null;
            }
        };

        viewModel = factory.create(SetupStoreViewModel.class);
    }

    private void loading() {
        showLoader();
    }

    private void success() {
        dismissLoader();
        Log.e("success", "success");
    }

    private void error(String error) {
        dismissLoader();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
        }, 100);
    }

    public boolean checkCameraPermission() {
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private void showImagePickerDialog1(int CAMERA_REQUEST_CODE, int GALLERY_REQUEST_CODE) {
        AlertHelper.showAlert(this, "Choose Store Photo", "Choose your store Picture",
                true, "Camera", true, "Gallery", true,
                new OnAlertClickListener() {
                    @Override
                    public void onNegativeBtnClicked() {
                        docOneFrontCameraProvider.openGallery();
                    }

                    @Override
                    public void onPositiveBtnClicked() {
                        docOneFrontCameraProvider.openCamera();
                    }
                });
    }

    private void showImagePickerDialog2(int CAMERA_REQUEST_CODE, int GALLERY_REQUEST_CODE) {
        AlertHelper.showAlert(this, "Choose Store Photo", "Choose your store Picture",
                true, "Camera", true, "Gallery", true,
                new OnAlertClickListener() {
                    @Override
                    public void onNegativeBtnClicked() {
                        docTwoFrontCameraProvider.openGallery();
                    }

                    @Override
                    public void onPositiveBtnClicked() {
                        docTwoFrontCameraProvider.openCamera();
                    }
                });
    }

    private void showImagePickerDialog3(int CAMERA_REQUEST_CODE, int GALLERY_REQUEST_CODE) {
        AlertHelper.showAlert(this, "Choose Store Photo", "Choose your store Picture",
                true, "Camera", true, "Gallery", true,
                new OnAlertClickListener() {
                    @Override
                    public void onNegativeBtnClicked() {
                        docOneBackCameraProvider.openGallery();
                    }

                    @Override
                    public void onPositiveBtnClicked() {
                        docOneBackCameraProvider.openCamera();
                    }
                });
    }

    private void showImagePickerDialog4(int CAMERA_REQUEST_CODE, int GALLERY_REQUEST_CODE) {
        AlertHelper.showAlert(this, "Choose Store Photo", "Choose your store Picture",
                true, "Camera", true, "Gallery", true,
                new OnAlertClickListener() {
                    @Override
                    public void onNegativeBtnClicked() {
                        docTwoBackCameraProvider.openGallery();
                    }

                    @Override
                    public void onPositiveBtnClicked() {
                        docTwoBackCameraProvider.openCamera();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        docOneFrontCameraProvider.processOnActivityResult(requestCode, resultCode, data, (file, s) -> {
            doc1 = file;
            docOneFrontImage.setImageBitmap(BitmapFactory.decodeFile(s));
            return Unit.INSTANCE;
        });
        docTwoFrontCameraProvider.processOnActivityResult(requestCode, resultCode, data, (file, s) -> {
            doc2 = file;
            docOneBackImage.setImageBitmap(BitmapFactory.decodeFile(s));
            return Unit.INSTANCE;
        });
        docOneBackCameraProvider.processOnActivityResult(requestCode, resultCode, data, (file, s) -> {
            doc3 = file;
            docTwoFrontImage.setImageBitmap(BitmapFactory.decodeFile(s));
            return Unit.INSTANCE;
        });
        docTwoBackCameraProvider.processOnActivityResult(requestCode, resultCode, data, (file, s) -> {
            doc4 = file;
            docTwoBackImage.setImageBitmap(BitmapFactory.decodeFile(s));
            return Unit.INSTANCE;
        });

//        if (resultCode == RESULT_OK && data.getData() != null) {
//
//                switch (requestCode) {
//                    case FRONT_DOC_CAMERA_REQUEST_CODE: {
//                        docOneFrontImage.setImageBitmap(ImageUtil.photoTakenFromCamera(data, DocumentUploadActivity.this).getBitmap());
////                        extra.setDocOneFront(ImageUtil.photoTakenFromCamera(data, DocumentUploadActivity.this).getFile());
//                        doc1 = ImageUtil.photoTakenFromCamera(data, DocumentUploadActivity.this).getFile();
//                        break;
//                    }
//                    case FRONT_DOC_TWO_CAMERA_REQUEST_CODE: {
//                        docTwoFrontImage.setImageBitmap(ImageUtil.photoTakenFromCamera(data, DocumentUploadActivity.this).getBitmap());
////                        extra.setDocTwoFront(ImageUtil.photoTakenFromCamera(data, DocumentUploadActivity.this).getFile());
//                        doc3 = ImageUtil.photoTakenFromCamera(data, DocumentUploadActivity.this).getFile();
//                        break;
//                    }
//                    case BACK_DOC_CAMERA_REQUEST_CODE: {
//                        docOneBackImage.setImageBitmap(ImageUtil.photoTakenFromCamera(data, DocumentUploadActivity.this).getBitmap());
////                        extra.setDocOneBack(ImageUtil.photoTakenFromCamera(data, DocumentUploadActivity.this).getFile());
//                        doc2 = ImageUtil.photoTakenFromCamera(data, DocumentUploadActivity.this).getFile();
//                        break;
//                    }
//                    case BACK_DOC_TWO_CAMERA_REQUEST_CODE: {
//                        docTwoBackImage.setImageBitmap(ImageUtil.photoTakenFromCamera(data, DocumentUploadActivity.this).getBitmap());
////                        extra.setDocTwoBack(ImageUtil.photoTakenFromCamera(data, DocumentUploadActivity.this).getFile());
//                        doc4 = ImageUtil.photoTakenFromCamera(data, DocumentUploadActivity.this).getFile();
//                        break;
//                    }
//                    case FRONT_DOC_GALLERY_REQUEST_CODE: {
//                        docOneFrontImage.setImageBitmap(ImageUtil.phototakenFromGallery(data, DocumentUploadActivity.this).getBitmap());
////                        extra.setDocOneFront(ImageUtil.phototakenFromGallery(data, DocumentUploadActivity.this).getFile());
//                        doc1 = ImageUtil.phototakenFromGallery(data, DocumentUploadActivity.this).getFile();
//                        break;
//                    }
//                    case FRONT_DOC_TWO_GALLERY_REQUEST_CODE: {
//                        docTwoFrontImage.setImageBitmap(ImageUtil.phototakenFromGallery(data, DocumentUploadActivity.this).getBitmap());
////                        extra.setDocTwoFront(ImageUtil.phototakenFromGallery(data, DocumentUploadActivity.this).getFile());
//                        doc3 = ImageUtil.phototakenFromGallery(data, DocumentUploadActivity.this).getFile();
//                        break;
//                    }
//                    case BACK_DOC_GALLERY_REQUEST_CODE: {
//                        docOneBackImage.setImageBitmap(ImageUtil.phototakenFromGallery(data, DocumentUploadActivity.this).getBitmap());
////                        extra.setDocOneBack(ImageUtil.phototakenFromGallery(data, DocumentUploadActivity.this).getFile());
//                        doc2 = ImageUtil.phototakenFromGallery(data, DocumentUploadActivity.this).getFile();
//                        break;
//                    }
//                    case BACK_DOC_TWO_GALLERY_REQUEST_CODE: {
//                        docTwoBackImage.setImageBitmap(ImageUtil.phototakenFromGallery(data, DocumentUploadActivity.this).getBitmap());
////                        extra.setDocTwoBack(ImageUtil.phototakenFromGallery(data, DocumentUploadActivity.this).getFile());
//                        doc4 = ImageUtil.phototakenFromGallery(data, DocumentUploadActivity.this).getFile();
//                        break;
//                    }
//                }
//            }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.bt_next_document_upload:
                if (doc1 == null) {
                    Toast.makeText(DocumentUploadActivity.this, "Please select Image", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (doc2 == null) {
                    Toast.makeText(DocumentUploadActivity.this, "Please select Image", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (doc3 == null) {
                    Toast.makeText(DocumentUploadActivity.this, "Please select Image", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (doc4 == null) {
                    Toast.makeText(DocumentUploadActivity.this, "Please select Image", Toast.LENGTH_SHORT).show();
                    return;
                }
                viewModel.doSetupStore(stateMachine, doc1, doc2, doc3, doc4);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}