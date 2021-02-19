package com.farmers.buyers.modules.home.view;

import android.os.Build;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.home.adapter.HomeTopOffersAdapter;
import com.farmers.buyers.modules.home.models.HomeTopOffersListItems;

/**
 * created by Mohammad Sajjad
 * on 26-01-2021 at 13:18
 * mohammadsajjad679@gmail.com
 */

public class HomeTopOffersViewHolder extends BaseViewHolder {
    private HomeTopOffersAdapter adapter;
    private ViewPager viewPager;
    private LinearLayout indicatorLl;
    private Handler handler;
    private Runnable runnable;
    private int page = 0;
    private TextView[] dots;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public HomeTopOffersViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.home_top_offers_holder_layout));
        viewPager = itemView.findViewById(R.id.home_top_offers_view_pager);
        indicatorLl = itemView.findViewById(R.id.home_top_offers_indicator_ll);
        handler = new Handler();
        adapter = new HomeTopOffersAdapter(itemView.getContext());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        final HomeTopOffersListItems item = (HomeTopOffersListItems) items;
        adapter.updateData(item.getOffersItems());
        addBottomDots(0, item.getOffersItems().size());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position, item.getOffersItems().size());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        runnable = new Runnable() {
            public void run() {
                if (item.getOffersItems().size() == page) {
                    page = 0;
                } else {
                    page++;
                }
                viewPager.setCurrentItem(page, true);
                handler.postDelayed(runnable, 3000);
            }
        };

        handler.postDelayed(runnable, 3000);
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
}
