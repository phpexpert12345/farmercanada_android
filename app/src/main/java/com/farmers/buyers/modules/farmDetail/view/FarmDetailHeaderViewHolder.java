package com.farmers.buyers.modules.farmDetail.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.farmDetail.adapter.FarmDetailHeaderAdapter;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailHeaderListItem;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 14:40
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailHeaderViewHolder extends BaseViewHolder {
    private FarmDetailHeaderAdapter adapter;
    private ImageView backImage;

    public FarmDetailHeaderViewHolder(@NonNull ViewGroup parent, final FarmHeaderClickListener listener) {
        super(Extensions.inflate(parent, R.layout.farm_detail_header_layout));
        ViewPager2 viewPager = itemView.findViewById(R.id.farm_Detail_viewPager);
        backImage = itemView.findViewById(R.id.farm_detail_header_back_img);
        adapter = new FarmDetailHeaderAdapter();
        viewPager.setAdapter(adapter);


        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOnBackClickListener();
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        adapter.updateData(((FarmDetailHeaderListItem) items).getItem());

    }

    public interface FarmHeaderClickListener {
        void onOnBackClickListener();
    }
}
