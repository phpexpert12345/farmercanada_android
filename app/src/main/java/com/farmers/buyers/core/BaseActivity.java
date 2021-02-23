package com.farmers.buyers.core;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.farmers.buyers.R;
import com.farmers.buyers.common.widget.ProgressDialog;

/**
 * created by Mohammad Sajjad
 * on 22-01-2021 at 16:28
 * mohammadsajjad679@gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity {

    private TextView mTextViewScreenTitle;
    private ImageView mImageButtonBack, menuMoreBtn;
    private RelativeLayout fakeToolbar;
    private Toolbar toolbar;
    ProgressDialog progressDialog = ProgressDialog.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) getLayoutInflater().inflate(R.layout.base_layout, null);
        FrameLayout container = coordinatorLayout.findViewById(R.id.layout_container);
        mTextViewScreenTitle = coordinatorLayout.findViewById(R.id.text_screen_title);
        mImageButtonBack = coordinatorLayout.findViewById(R.id.image_back_button);
        menuMoreBtn = coordinatorLayout.findViewById(R.id.image_more_btn);
        toolbar = coordinatorLayout.findViewById(R.id.base_toolbar);
        getLayoutInflater().inflate(layoutResID, container,true);
        fakeToolbar = coordinatorLayout.findViewById(R.id.dummy_base_toolbar);

        if (showToolbar()) toolbar.setVisibility(View.VISIBLE); else toolbar.setVisibility(View.GONE);


        super.setContentView(coordinatorLayout);
    }

    public abstract Boolean showToolbar() ;

    public void setupToolbar(ToolbarConfig toolbarConfig) {
        mTextViewScreenTitle.setText(toolbarConfig.title);
        if (toolbarConfig.backButtonImg != 0) {
            mImageButtonBack.setImageResource(toolbarConfig.backButtonImg);
        }
        mImageButtonBack.setOnClickListener(toolbarConfig.onBackButtonClickListener);
        if (toolbarConfig.showBackButton) {
            mImageButtonBack.setVisibility(View.VISIBLE);
        }
        else {
            mImageButtonBack.setVisibility(View.GONE);
        }
        if (toolbarConfig.showMenuButton) {
            menuMoreBtn.setVisibility(View.VISIBLE);
        }
        else {
            menuMoreBtn.setVisibility(View.GONE);

        }

        if (toolbarConfig.menuConfig != null) {
            if (toolbarConfig.menuConfig.icon != 0 ) {
                menuMoreBtn.setImageDrawable(getResources().getDrawable(toolbarConfig.menuConfig.icon));
            }
            menuMoreBtn.setOnClickListener(toolbarConfig.menuConfig.onClickListener);
        }

    }

    public class ToolbarMenuConfig {
        int icon;
        View.OnClickListener onClickListener;

        public ToolbarMenuConfig(int iconSrc, View.OnClickListener onClick){
            this.icon = iconSrc;
            this.onClickListener = onClick;
        }

    }

    public class ToolbarConfig {
        String title = "";
        Boolean showBackButton;
        View.OnClickListener onBackButtonClickListener;
        Boolean showMenuButton;
        ToolbarMenuConfig menuConfig;
        int backButtonImg = 0;

        public ToolbarConfig(String title, Boolean showBackButton, View.OnClickListener backButtonClickListener,
                             Boolean showMenuButton, ToolbarMenuConfig toolbarMenuConfig) {
            this.title = title;
            this.showBackButton = showBackButton;
            this.onBackButtonClickListener = backButtonClickListener;
            this.showMenuButton = showMenuButton;
            this.menuConfig = toolbarMenuConfig;

        }
        public ToolbarConfig(String title, Boolean showBackButton, int backIcon, View.OnClickListener backButtonClickListener,
                             Boolean showMenuButton, ToolbarMenuConfig toolbarMenuConfig) {
            this.title = title;
            this.backButtonImg = backIcon;
            this.showBackButton = showBackButton;
            this.onBackButtonClickListener = backButtonClickListener;
            this.showMenuButton = showMenuButton;
            this.menuConfig = toolbarMenuConfig;

        }
    }

    public void showLoader(String title) {
        progressDialog.init(this, title);
    }

    public void showLoader() {
        progressDialog.init(this, "");
    }

    public void dismissLoader() {
        progressDialog.dismiss();
    }
}
