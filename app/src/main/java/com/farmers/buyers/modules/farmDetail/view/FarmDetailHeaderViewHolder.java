package com.farmers.buyers.modules.farmDetail.view;

import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.farmDetail.adapter.FarmDetailHeaderAdapter;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailHeaderListItem;

import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 14:40
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailHeaderViewHolder extends BaseViewHolder {
    private FarmDetailHeaderAdapter adapter;
    private ImageView backImage;
    private LinearLayout indicatorLl;
    private Handler handler;
    private Runnable runnable;
    private int page = 0;
    private TextView[] dots;



    public FarmDetailHeaderViewHolder(@NonNull ViewGroup parent, final FarmHeaderClickListener listener) {
        super(Extensions.inflate(parent, R.layout.farm_detail_header_layout));
        ViewPager2 viewPager = itemView.findViewById(R.id.farm_Detail_viewPager);
        backImage = itemView.findViewById(R.id.farm_detail_header_back_img);
        indicatorLl = itemView.findViewById(R.id.farm_detail_header_indicator_ll);
        handler = new Handler();

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
        FarmDetailHeaderListItem item = (FarmDetailHeaderListItem) items;
        adapter.updateData(((FarmDetailHeaderListItem) items).getItem());

        addBottomDots(0, item.getItem().size());


    }

    private void addBottomDots(int currentPage, int size) {
        dots = new TextView[size];

        indicatorLl.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(itemView.getContext());
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(25);
            dots[i].setTextColor(itemView.getContext().getResources().getColor(R.color.in_active_indicator_color));
            indicatorLl.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(itemView.getContext().getResources().getColor(R.color.indicator_color));
    }


    public interface FarmHeaderClickListener {
        void onOnBackClickListener();
    }
}
