package com.farmers.buyers.modules.home.view;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.farmDetail.FarmDetailActivity;
import com.farmers.buyers.modules.home.models.farmList.SubProductItemRecord;
import com.farmers.buyers.storage.Constant;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 21:53
 * mohammadsajjad679@gmail.com
 */

public class HomeItemsViewHolder extends BaseViewHolder {
    private CardView cardView;
    private ImageView saveImage, savedImage,home_list_item_img;
    private TextView home_list_item_layout_farmName,home_list_item_layout_distance_tv,customer_home_parlour_view_holder_rating_tv;
    private CircleImageView circleImageView;
    public HomeItemsViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.home_list_item_layout));
        cardView = itemView.findViewById(R.id.home_item_card);
        saveImage = itemView.findViewById(R.id.home_list_save_image);
        savedImage = itemView.findViewById(R.id.home_list_saved_image);
        home_list_item_img = itemView.findViewById(R.id.home_list_item_img);
        circleImageView = itemView.findViewById(R.id.home_list_item_layout_farm_img);
        home_list_item_layout_farmName = itemView.findViewById(R.id.home_list_item_layout_farmName);
        home_list_item_layout_distance_tv = itemView.findViewById(R.id.home_list_item_layout_distance_tv);
        customer_home_parlour_view_holder_rating_tv = itemView.findViewById(R.id.customer_home_parlour_view_holder_rating_tv);

    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        final SubProductItemRecord item = (SubProductItemRecord) items;
        home_list_item_layout_farmName.setText(item.getFarmName());
        home_list_item_layout_distance_tv.setText(item.getFarmDeliveryRadiusText());
        customer_home_parlour_view_holder_rating_tv.setText(String.valueOf(item.getRatingAvg()));
        Glide.with(itemView.getContext()).load(item.getFarmCoverPhoto()).into(home_list_item_img);
        Glide.with(itemView.getContext()).load(item.getFarmLogo()).into(circleImageView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(itemView.getContext(), FarmDetailActivity.class);
                intent.putExtra(Constant.SERIALIZABLE_INTENT,item);
                itemView.getContext().startActivity(intent);
            }
        });

    }
}
