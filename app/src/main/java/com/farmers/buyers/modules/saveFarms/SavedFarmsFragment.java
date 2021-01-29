package com.farmers.buyers.modules.saveFarms;

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
 * on 28-01-2021 at 17:42
 * mohammadsajjad679@gmail.com
 */

public class SavedFarmsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_profile_fragment, container,false);
        bindView(view);
        return view;
    }

    private void bindView(View view) {

    }
}
