package com.farmers.buyers.modules.seller.addProduct;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.farmers.buyers.R;
import com.farmers.buyers.common.model.FileBitmapModel;
import com.farmers.buyers.common.model.ImageUtil;
import com.farmers.buyers.common.utils.AlertHelper;
import com.farmers.buyers.common.utils.CameraProvider;
import com.farmers.buyers.common.utils.OnAlertClickListener;
import com.farmers.buyers.common.utils.Util;
import com.farmers.buyers.core.BaseActivity;
import com.farmers.buyers.core.DataFetchState;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.seller.addProduct.adapter.CategoryItemAdapter;
import com.farmers.buyers.modules.seller.addProduct.model.AddProductApiResponseModel;

import java.io.File;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class AddProductActivity extends BaseActivity {

    private static final int CAMERA_REQUEST = 201;
    private static final int GALLERY_REQUEST = 102;

    private ImageView micImage;
    private EditText productNameEt;
    private Spinner spinner;
    private TextView quantityTv;
    private ImageView increaseImg, decreaseImg;
    private EditText unitPriceEt, salesPriceEt, noteEt, unitEt;
    private Button saveBtn;
    private LinearLayout chooseImageLl;
    private CircleImageView productImage;
    private File file;
    private int counter = 1;
    private MutableLiveData<DataFetchState<AllDataModel>> categoryStateMachine = new MutableLiveData<>();
    private MutableLiveData<DataFetchState<AddProductApiResponseModel>> stateMachine = new MutableLiveData<>();
    private String[] PERMISSIONS;
    CameraProvider cameraProvider = new CameraProvider(this, CAMERA_REQUEST, GALLERY_REQUEST);
    private File productFile;




    private ViewModelProvider.Factory factory = new ViewModelProvider.Factory() {
        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(AddProductViewModel.class));
            return (T) new AddProductViewModel();
        }
    };

    private AddProductViewModel viewModel = factory.create(AddProductViewModel.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        setupToolbar(new ToolbarConfig("Add Product", true, R.drawable.ic_arrow_back_black, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        }, false, null));




        init();
    }

    private void init() {
        micImage = findViewById(R.id.add_product_mic_img);
        productNameEt = findViewById(R.id.add_product_name_et);
        spinner = findViewById(R.id.add_product_spinner);
        quantityTv = findViewById(R.id.add_product_quantity_tv);
        increaseImg = findViewById(R.id.add_product_increase_img);
        decreaseImg = findViewById(R.id.add_product_decrease_img);
        unitPriceEt = findViewById(R.id.add_product_unit_price_et);
        salesPriceEt = findViewById(R.id.add_product_sales_price_et);
        noteEt = findViewById(R.id.add_product_note_et);
        saveBtn = findViewById(R.id.add_product_save_btn);
        unitEt = findViewById(R.id.add_product_unit_et);
        chooseImageLl = findViewById(R.id.add_store_choose_image_ll);
        productImage = findViewById(R.id.add_product_image);

        quantityTv.setText(String.valueOf(counter));

        PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};




        chooseImageLl.setOnClickListener(v -> {
            if (!Util.hasPermissions(AddProductActivity.this, PERMISSIONS)) {
                ActivityCompat.requestPermissions(AddProductActivity.this, PERMISSIONS, 1);
            }else {
                showProfilePicturePickerDialog();
            }
        });

        increaseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment();
                quantityTv.setText(String.valueOf(counter));
            }
        });

        decreaseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement();
                quantityTv.setText(String.valueOf(counter));
            }
        });

        micImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                try {
                    startActivityForResult(intent, 101);
                } catch (ActivityNotFoundException a) {

                }
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addProduct(stateMachine, productNameEt.getText().toString(), salesPriceEt.getText().toString(), unitPriceEt.getText().toString(), noteEt.getText().toString(), unitEt.getText().toString(), String.valueOf(counter), productFile);

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    return;
                }
                else {
                    viewModel.onCategorySelected(viewModel.categoryList.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        categoryStateMachine.observe(this, new Observer<DataFetchState<AllDataModel>>() {
            @Override
            public void onChanged(DataFetchState<AllDataModel> allDataModelDataFetchState) {
                switch (allDataModelDataFetchState.status) {
                    case LOADING:  loading(); break;
                    case SUCCESS: success(); break;
                    case ERROR: error(allDataModelDataFetchState.status_message);
                }
            }
        });

        stateMachine.observe(this, new Observer<DataFetchState<AddProductApiResponseModel>>() {
            @Override
            public void onChanged(DataFetchState<AddProductApiResponseModel> addProductResponse) {
                switch (addProductResponse.status) {
                    case LOADING:
                        loading();
                        break;
                    case SUCCESS: {
                        dismissLoader();
                        AlertHelper.showAlert(AddProductActivity.this, "Product added", addProductResponse.status_message, true, "Ok", false, "", true, new OnAlertClickListener() {
                            @Override
                            public void onNegativeBtnClicked() {

                            }

                            @Override
                            public void onPositiveBtnClicked() {
                                finish();
                            }
                        });
                        Toast.makeText(AddProductActivity.this, addProductResponse.status_message, Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case ERROR:
                        error(addProductResponse.status_message);

                }
            }
        });

        viewModel.getCategoryList(categoryStateMachine);

    }

    private void showProfilePicturePickerDialog() {
        AlertHelper.showAlert(this, "Choose Store Photo", "Choose your store Picture", true, "Camera", true, "Gallery", true, new OnAlertClickListener() {
            @Override
            public void onNegativeBtnClicked() {
                cameraProvider.openGallery();
            }

            @Override
            public void onPositiveBtnClicked(){
                cameraProvider.openCamera();
            }
        });
    }

    private void increment(){
        counter = counter+1;
    }
    private void decrement(){
        if (counter==1)
            return;
        else {
            counter = counter-1;
        }
    }

    private void loading() {
        showLoader();
    }

    private void success() {
        dismissLoader();
        spinner.setAdapter(new CategoryItemAdapter(this, viewModel.categoryList));
    }

    private void error(String message) {
        dismissLoader();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cameraProvider.processOnActivityResult(requestCode, resultCode, data, (file, s) -> {
            productFile = file;
            productImage.setImageBitmap(BitmapFactory.decodeFile(s));
            return Unit.INSTANCE;
        });

        if (resultCode == RESULT_OK && data != null) {


            switch (requestCode) {
                case 101: {

                    if (resultCode == RESULT_OK && null != data) {

                        ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                        productNameEt.setText(result.get(0));
                    }
                    break;
                }

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public Boolean showToolbar() {
        return true;
    }
}