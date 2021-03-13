package com.farmers.seller.modules.storeSetting;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.farmers.buyers.common.utils.AlertHelper;
import com.farmers.buyers.common.utils.CameraProvider;
import com.farmers.buyers.common.utils.OnAlertClickListener;
import com.farmers.buyers.common.utils.Util;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.seller.product.ProductListActivity;
import com.farmers.seller.modules.setupSellerAccount.SetupStoreViewModel;
import com.farmers.seller.modules.setupSellerAccount.documentUpload.DocumentUploadActivity;
import com.farmers.seller.modules.setupSellerAccount.model.SetupStoreApiModel;
import com.farmers.seller.modules.setupSellerAccount.storeDetails.StoreSetupExtra;
import com.farmers.seller.modules.storeSetting.model.StoreFarmDetails;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;

import kotlin.Unit;

public class UpdateDocActivity extends BaseActivity {
    LinearLayout linear_step_doc;
    ImageView tv_setup_seller_back_img;
    TextView tv_setup_seller_toolbar_name;
    TextInputEditText setup_seller_store_name_et,second_doc_et;
    ImageView store_setup_document_one_front_img,store_setup_document_one_back_img,store_setup_document_two_front_img,store_setup_document_two_back_img;
    Button bt_next_document_upload;
    StoreFarmDetails storeFarmDetails;
    private static final int FRONT_DOC_CAMERA_REQUEST_CODE = 501;
    private static final int FRONT_DOC_TWO_CAMERA_REQUEST_CODE = 502;
    private static final int BACK_DOC_CAMERA_REQUEST_CODE = 503;
    private static final int BACK_DOC_TWO_CAMERA_REQUEST_CODE = 504;

    private static final int FRONT_DOC_GALLERY_REQUEST_CODE = 505;
    private static final int FRONT_DOC_TWO_GALLERY_REQUEST_CODE = 506;
    private static final int BACK_DOC_GALLERY_REQUEST_CODE = 507;
    private static final int BACK_DOC_TWO_GALLERY_REQUEST_CODE = 508;
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
    private SetupStoreViewModel viewModel;
    private MutableLiveData<DataFetchState<SetupStoreApiModel>> stateMachine = new MutableLiveData<>();
    private AppController appController = AppController.get();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document_upload);
        linear_step_doc=findViewById(R.id.linear_step_doc);
        tv_setup_seller_back_img=findViewById(R.id.tv_setup_seller_back_img);
        tv_setup_seller_toolbar_name=findViewById(R.id.tv_setup_seller_toolbar_name);
        setup_seller_store_name_et=findViewById(R.id.setup_seller_store_name_et);
        second_doc_et=findViewById(R.id.second_doc_et);
        store_setup_document_one_front_img=findViewById(R.id.store_setup_document_one_front_img);
        store_setup_document_one_back_img=findViewById(R.id.store_setup_document_one_back_img);
        store_setup_document_two_front_img=findViewById(R.id.store_setup_document_two_front_img);
        store_setup_document_two_back_img=findViewById(R.id.store_setup_document_two_back_img);
        bt_next_document_upload=findViewById(R.id.bt_next_document_upload);
        linear_step_doc.setVisibility(View.GONE);
        tv_setup_seller_toolbar_name.setText("Update Document");
        tv_setup_seller_back_img.setOnClickListener(v->{
            finish();
        });
        PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        bindViewModel();
        init();

    }

    @Override
    public Boolean showToolbar() {
        return false;
    }

    private void init(){
        tv_setup_seller_back_img.setOnClickListener(v->{
            finish();
        });
        store_setup_document_one_front_img.setOnClickListener(new View.OnClickListener() {
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

        store_setup_document_one_back_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Util.hasPermissions(UpdateDocActivity.this, PERMISSIONS)) {
                    ActivityCompat.requestPermissions(UpdateDocActivity.this, PERMISSIONS, 1);
                } else {
                    showImagePickerDialog3(BACK_DOC_CAMERA_REQUEST_CODE,
                            BACK_DOC_GALLERY_REQUEST_CODE);

                }
            }
        });

        store_setup_document_two_front_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Util.hasPermissions(UpdateDocActivity.this, PERMISSIONS)) {
                    ActivityCompat.requestPermissions(UpdateDocActivity.this, PERMISSIONS, 1);
                } else {
                    showImagePickerDialog2(FRONT_DOC_TWO_CAMERA_REQUEST_CODE,
                            FRONT_DOC_TWO_GALLERY_REQUEST_CODE);
                }
            }
        });

        store_setup_document_two_back_img.setOnClickListener(new View.OnClickListener() {
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
        bt_next_document_upload.setOnClickListener(v->{
            if (doc1 == null) {
                Toast.makeText(UpdateDocActivity.this, "Please select first front Image", Toast.LENGTH_SHORT).show();
                return;
            }
            if (doc2 == null) {
                Toast.makeText(UpdateDocActivity.this, "Please select first back Image", Toast.LENGTH_SHORT).show();
                return;
            }
            if (doc3 == null) {
                Toast.makeText(UpdateDocActivity.this, "Please select second front Image", Toast.LENGTH_SHORT).show();
                return;
            }
            if (doc4 == null) {
                Toast.makeText(UpdateDocActivity.this, "Please select second back Image", Toast.LENGTH_SHORT).show();
                return;
            }
            viewModel.UpdateDocs(stateMachine, doc1, doc2, doc3, doc4,setup_seller_store_name_et.getText().toString(),second_doc_et.getText().toString(),appController.getAuthenticationKey(),appController.getLoginId());
        });


        stateMachine.observe(this, setupStoreApiModelDataFetchState -> {
            switch (setupStoreApiModelDataFetchState.status) {
                case LOADING:
                    showLoader();
                    break;
                case SUCCESS: {
                    dismissLoader();
                    AlertHelper.showAlert(UpdateDocActivity.this, "Store Update",
                            setupStoreApiModelDataFetchState.status_message, true, "Ok",
                            false, "", true, new OnAlertClickListener() {
                                @Override
                                public void onNegativeBtnClicked() {
                                    finish();
                                }

                                @Override
                                public void onPositiveBtnClicked() {

                                    finish();
                                }
                            });
                    Toast.makeText(UpdateDocActivity.this, setupStoreApiModelDataFetchState.status_message, Toast.LENGTH_SHORT).show();

                }
                case ERROR:
                    dismissLoader();
                    Toast.makeText(UpdateDocActivity.this, setupStoreApiModelDataFetchState.status_message, Toast.LENGTH_SHORT).show();
//                    error(setupStoreApiModelDataFetchState.status_message);
                    break;
            }
        });

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
    private void bindViewModel() {
        StoreSetupExtra storeSetupExtra=new StoreSetupExtra();

        viewModel = new SetupStoreViewModel(storeSetupExtra);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        docOneFrontCameraProvider.processOnActivityResult(requestCode, resultCode, data, (file, s) -> {
            doc1 = file;
            store_setup_document_one_front_img.setImageBitmap(BitmapFactory.decodeFile(s));
            return Unit.INSTANCE;
        });
        docTwoFrontCameraProvider.processOnActivityResult(requestCode, resultCode, data, (file, s) -> {
            doc2 = file;
            store_setup_document_two_front_img.setImageBitmap(BitmapFactory.decodeFile(s));
            return Unit.INSTANCE;
        });
        docOneBackCameraProvider.processOnActivityResult(requestCode, resultCode, data, (file, s) -> {
            doc3 = file;
            store_setup_document_one_back_img.setImageBitmap(BitmapFactory.decodeFile(s));
            return Unit.INSTANCE;
        });
        docTwoBackCameraProvider.processOnActivityResult(requestCode, resultCode, data, (file, s) -> {
            doc4 = file;
            store_setup_document_two_back_img.setImageBitmap(BitmapFactory.decodeFile(s));
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

}
