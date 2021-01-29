package com.farmers.buyers.modules.farmDetail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.farmers.buyers.R;
import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailsHeaderItems;
import com.farmers.buyers.modules.farmDetail.view.FarmDetailHeaderItemDelegate;
import com.farmers.buyers.storage.CardConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 14:42
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailHeaderAdapter extends BaseAdapter {

    public FarmDetailHeaderAdapter() {
        super();
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.FARM_DETAIL_HEADER_ITEM_ADAPTER, new FarmDetailHeaderItemDelegate());
    }
}
//public class FarmDetailHeaderAdapter extends PagerAdapter {
//    private Context context;
//    private List<FarmDetailsHeaderItems> items = new ArrayList<>();
//
//    public FarmDetailHeaderAdapter(Context context) {
//        this.context = context;
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        View view = layoutInflater.inflate(R.layout.farm_details_header_item_layout, container, false);
//        container.addView(view);
//        return view;
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((View) object);
//    }
//
//    @Override
//    public int getCount() {
//        return items.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
//    }
//
//    public void updateData(List<FarmDetailsHeaderItems> items) {
//        this.items.clear();
//        this.items.addAll(items);
//        notifyDataSetChanged();
//    }
//}