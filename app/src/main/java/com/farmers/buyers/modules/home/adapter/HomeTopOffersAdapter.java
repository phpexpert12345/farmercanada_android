package com.farmers.buyers.modules.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.modules.home.models.HomeTopOffersItem;

import java.util.ArrayList;
import java.util.List;

import static com.farmers.buyers.app.App.getAppContext;

/**
 * created by Mohammad Sajjad
 * on 26-01-2021 at 23:55
 * mohammadsajjad679@gmail.com
 */

public class HomeTopOffersAdapter extends PagerAdapter {
    private Context context;
    private List<HomeTopOffersItem> items = new ArrayList<>();

    public HomeTopOffersAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.home_top_offers_item_layout, container, false);
        container.addView(view);

        ImageView home_top_offers_item_image = view.findViewById(R.id.home_top_offers_item_image);

        Glide.with(context)
                .load(items.get(position).getOfferName())
                .placeholder(R.mipmap.top_offer_img)
                .into(home_top_offers_item_image);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public void updateData(List<HomeTopOffersItem> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}