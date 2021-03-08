package com.farmers.buyers.modules.seller.addProduct.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.farmers.buyers.R;
import com.farmers.buyers.modules.seller.addProduct.model.CategoryItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammad sajjad on 06-03-2021.
 * mohammadsajjad679@gmail.com
 */
public class CategoryItemAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflter;
    private List<CategoryItem> items;

    public CategoryItemAdapter(Context applicationContext, List<CategoryItem> items) {
        this.context = applicationContext;
        this.items = items;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.spinner_item_layout, null);
        TextView names = (TextView) view.findViewById(R.id.spinner_item_tv);
        names.setText(items.get(i).getName());

        if (i == 0) {
            names.setEnabled(false);
//            names.setBackgroundColor(context.getResources().getColor(R.color.light_gray));
 //           names.setTextColor(context.getResources().getColor(R.color.white));
        }
        else {
            names.setEnabled(true);
            names.setBackground(null);
            names.setTextColor(context.getResources().getColor(R.color.primaryTextColor));
        }

        return view;
    }
}
