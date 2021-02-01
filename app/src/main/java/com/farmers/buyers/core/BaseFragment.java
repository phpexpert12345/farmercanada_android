package com.farmers.buyers.core;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.farmers.buyers.R;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 17:45
 * mohammadsajjad679@gmail.com
 */

public abstract class BaseFragment extends Fragment {
    public BaseActivity baseActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        baseActivity = (BaseActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getResourceFile(), container, false);
        bindBundle();
        bindViewModel();
        bindView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        onViewCreated();
    }

    public abstract String getTitle();
    public abstract int getResourceFile();
    public abstract void bindView(View view);

    public void bindViewModel() {}
    public void bindBundle() {}
    public void onViewCreated() {}
}
