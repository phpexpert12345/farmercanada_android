package com.farmers.buyers.modules.home.view;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.farmDetail.FarmDetailActivity;
import com.farmers.buyers.modules.home.models.HomeListItem;
import com.farmers.buyers.modules.home.models.farmList.SubProductItemRecord;
import com.farmers.buyers.storage.Constant;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 21:53
 * mohammadsajjad679@gmail.com
 */

public class HomeItemsViewHolder extends BaseViewHolder {
    private TextView home_list_item_layout_farmName,home_list_item_layout_distance_tv,customer_home_parlour_view_holder_rating_tv;
    private CircleImageView circleImageView;
    private ImageView saveImage, savedImage, farmImage;
    private FarmItemClickListener farmItemClickListener;
    private RelativeLayout followFarmLayout;

    public HomeItemsViewHolder(@NonNull ViewGroup parent, FarmItemClickListener farmItemClickListener) {
        super(Extensions.inflate(parent, R.layout.home_list_item_layout));
        saveImage = itemView.findViewById(R.id.home_list_save_image);
        savedImage = itemView.findViewById(R.id.home_list_saved_image);
        farmImage = itemView.findViewById(R.id.home_list_item_img);
        circleImageView = itemView.findViewById(R.id.home_list_item_layout_farm_img);
        home_list_item_layout_farmName = itemView.findViewById(R.id.home_list_item_layout_farmName);
        home_list_item_layout_distance_tv = itemView.findViewById(R.id.home_list_item_layout_distance_tv);
        customer_home_parlour_view_holder_rating_tv = itemView.findViewById(R.id.customer_home_parlour_view_holder_rating_tv);
        followFarmLayout = itemView.findViewById(R.id.follow_layout);
        this.farmItemClickListener = farmItemClickListener;

    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        final HomeListItem item = (HomeListItem) items;
        home_list_item_layout_farmName.setText(item.getFarmName());
        home_list_item_layout_distance_tv.setText(item.getDistance());
        customer_home_parlour_view_holder_rating_tv.setText(String.valueOf(item.getRating()));
        Glide.with(itemView.getContext()).load(item.getCoverImage()).into(farmImage);
        Glide.with(itemView.getContext()).load(item.getFarmImage()).into(circleImageView);
        farmImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(itemView.getContext(), FarmDetailActivity.class);
//                intent.putExtra(Constant.SERIALIZABLE_INTENT,item);
                itemView.getContext().startActivity(intent);
            }
        });

        if (item.getSaved().equals("Yes")) {
            savedImage.setVisibility(View.VISIBLE);
            saveImage.setVisibility(View.GONE);
        }
        else {
            savedImage.setVisibility(View.GONE);
            saveImage.setVisibility(View.VISIBLE);
        }


        farmImage.setOnClickListener(view -> itemView.getContext().startActivity( new Intent(itemView.getContext(), FarmDetailActivity.class)));


        savedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage.setVisibility(View.VISIBLE);
                savedImage.setVisibility(View.GONE);
                farmItemClickListener.onSaveFarmClicked(item.getId(), 0);
            }
        });

        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage.setVisibility(View.GONE);
                savedImage.setVisibility(View.VISIBLE);
                farmItemClickListener.onSaveFarmClicked(item.getId(), 1);
            }
        });

        followFarmLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                farmItemClickListener.onFollowFarmClicked(item.getId(), "1");
            }
        });


    }

    public interface FarmItemClickListener {
        void onSaveFarmClicked(String id, int status);
        void onFollowFarmClicked(String id, String status);
    }
}

