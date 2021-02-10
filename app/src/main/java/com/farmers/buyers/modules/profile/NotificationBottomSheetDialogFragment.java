package com.farmers.buyers.modules.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.farmers.buyers.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * created by Mohammad Sajjad
 * on 10-02-2021 at 17:39
 * mohammadsajjad679@gmail.com
 */

public class NotificationBottomSheetDialogFragment extends BottomSheetDialogFragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notify_me_switches_dialog, container, false);
        bind(view);
        return view;
    }

    public void bind(View view) {

    }
}
