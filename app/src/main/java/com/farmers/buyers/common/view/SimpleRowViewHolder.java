package com.farmers.buyers.common.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.common.model.SimpleRowListItem;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.profile.extraItems.ProfileItem;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 13:28
 * mohammadsajjad679@gmail.com
 */

public class SimpleRowViewHolder extends BaseViewHolder {
    private ImageView leftImage;
    private ImageView rightImage;
    private TextView titleTv;
    private LinearLayout rowItemLl;
    private View dividerView;
    private RelativeLayout simpleLayout;
    OnSimpleRowItemClickedListener rowItemClickedListener;

    public SimpleRowViewHolder(@NonNull ViewGroup parent, OnSimpleRowItemClickedListener rowItemClickedListener) {
        super(Extensions.inflate(parent, R.layout.simple_row_layout));
        this.rowItemClickedListener = rowItemClickedListener;
        rowItemLl = itemView.findViewById(R.id.row_item_ll);

    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        final SimpleRowListItem item = (SimpleRowListItem) items;

        for (int i = 0 ; i< ((SimpleRowListItem)items).getItem().size() ; i++) {

            View view = LayoutInflater.from(itemView.getContext()).inflate(R.layout.simple_row_item_layout, null);
            leftImage = view.findViewById(R.id.simple_row_item_left_imageView);
            rightImage = view.findViewById(R.id.simple_row_item_right_imageView);
            titleTv = view.findViewById(R.id.simple_row_item_title_tv);
            simpleLayout = view.findViewById(R.id.simple_row_layout);
            dividerView = view.findViewById(R.id.simple_row_item_divider_item);
            leftImage.setImageResource(((SimpleRowListItem)items).getItem().get(i).getLeftImageUri());
            rightImage.setImageResource(((SimpleRowListItem)items).getItem().get(i).getRightImageUri());
            titleTv.setText(((SimpleRowListItem)items).getItem().get(i).getTitle());

//            if (item.getItem().size()-1 == i ){
//                dividerView.setVisibility(View.GONE);
//            }
//            else {
//                dividerView.setVisibility(View.VISIBLE);
//
//            }
            rowItemLl.addView(view);

            final int finalI = i;
            simpleLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    rowItemClickedListener.onSimpleRowItemClicked(item.getItem().get(finalI).getItemType());
                }
            });

        }
    }

    public interface OnSimpleRowItemClickedListener {
        void onSimpleRowItemClicked(ProfileItem item);
    }
}
