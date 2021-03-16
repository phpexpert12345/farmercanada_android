package com.farmers.buyers.modules.home.view;

import android.view.ViewGroup;

import com.farmers.buyers.common.view.SingleItemViewHolder;
import com.farmers.buyers.core.BaseDelegate;
import com.farmers.buyers.core.BaseViewHolder;

public class SearchTitleDelegate extends BaseDelegate {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent) {
        return new SearchSingleViewHolder(parent);

    }
}
