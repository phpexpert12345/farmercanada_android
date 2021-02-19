package com.farmers.buyers.common.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.farmers.buyers.R;

/**
 * created by Mohammad Sajjad
 * on 11-02-2021 at 14:41
 * mohammadsajjad679@gmail.com
 */

public class GenericTextWatcher implements TextWatcher {
    private View view;
    private EditText[] editTexts;

    public GenericTextWatcher(View view, EditText[] editTexts) {
        this.view = view;
        this.editTexts = editTexts;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = editable.toString();

        switch (view.getId()) {
            case R.id.otp_edit_box1: {
                if (text.length() == 1) editTexts[1].requestFocus();
                break;
            }
            case R.id.otp_edit_box2: {
                if (text.length() == 1) {
                    editTexts[2].requestFocus();
                } else if (text.isEmpty()) editTexts[0].requestFocus();
                break;

            }
            case R.id.otp_edit_box3: {
                if (text.length() == 1) {
                    editTexts[3].requestFocus();
                } else if (text.isEmpty()) editTexts[1].requestFocus();
                break;

            }
            case R.id.otp_edit_box4: {
                if (text.isEmpty()) editTexts[2].requestFocus();
                break;

            }
        }

    }
}
