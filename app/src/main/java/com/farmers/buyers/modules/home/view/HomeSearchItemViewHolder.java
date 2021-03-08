package com.farmers.buyers.modules.home.view;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 20:35
 * mohammadsajjad679@gmail.com
 */

public class HomeSearchItemViewHolder extends BaseViewHolder {
    private TextView editText;

    public HomeSearchItemViewHolder(@NonNull ViewGroup parent, SearchItemClickListener listener) {
        super(Extensions.inflate(parent, R.layout.home_search_item_view_holder_layout));
        editText = itemView.findViewById(R.id.home_header_item_search_et);
        editText.setOnClickListener(v -> listener.onSearchItemClicked(editText.getText().toString()));
//                editText.addTextChangedListener(new TextWatcher() {
//                    @Override
//                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                    }
//
//                    @Override
//                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//                        if (s.length() >= 3) {
//                            listener.onSearchItemClicked(editText.getText().toString());
//                        }
//
//                    }
//
//                    @Override
//                    public void afterTextChanged(Editable s) {
//
//                    }
//                });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

    }

    public interface SearchItemClickListener {
        void onSearchItemClicked(String query);
    }
}
