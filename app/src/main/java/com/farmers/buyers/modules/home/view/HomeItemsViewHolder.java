package com.farmers.buyers.modules.home.view;

import android.content.Intent;
import android.view.Gravity;
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
import com.farmers.buyers.common.utils.Helper;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.farmDetail.FarmDetailActivity;
import com.farmers.buyers.modules.home.models.HomeListItem;
import com.farmers.buyers.modules.home.models.farmList.SubProductItemRecord;
import com.farmers.buyers.storage.Constant;
import com.farmers.buyers.storage.GPSTracker;

import java.text.DecimalFormat;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by Mohammad Sajjad
 * on 25-01-2021 at 21:53
 * mohammadsajjad679@gmail.com
 */

public class HomeItemsViewHolder extends BaseViewHolder {
    private TextView home_list_item_layout_farmName,home_list_item_layout_distance_tv,customer_home_parlour_view_holder_rating_tv;
    private CircleImageView circleImageView, circleImageView2;
    private ImageView saveImage, savedImage, farmImage;
    private FarmItemClickListener farmItemClickListener;
    private RelativeLayout followFarmLayout, unFollowFarmLayout;
    private GPSTracker gpsTracker = new GPSTracker(itemView.getContext());

    public HomeItemsViewHolder(@NonNull ViewGroup parent, FarmItemClickListener farmItemClickListener) {
        super(Extensions.inflate(parent, R.layout.home_list_item_layout));
        saveImage = itemView.findViewById(R.id.home_list_save_image);
        savedImage = itemView.findViewById(R.id.home_list_saved_image);
        farmImage = itemView.findViewById(R.id.home_list_item_img);
        circleImageView = itemView.findViewById(R.id.home_list_item_layout_farm_img);
        circleImageView2 = itemView.findViewById(R.id.home_list_item_layout_farm_img2);
        home_list_item_layout_farmName = itemView.findViewById(R.id.home_list_item_layout_farmName);
        home_list_item_layout_distance_tv = itemView.findViewById(R.id.home_list_item_layout_distance_tv);
        customer_home_parlour_view_holder_rating_tv = itemView.findViewById(R.id.customer_home_parlour_view_holder_rating_tv);
        followFarmLayout = itemView.findViewById(R.id.follow_layout);
        unFollowFarmLayout = itemView.findViewById(R.id.unFollow_layout);
        this.farmItemClickListener = farmItemClickListener;

    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        final HomeListItem item = (HomeListItem) items;
        home_list_item_layout_farmName.setText(item.getFarmName());
        home_list_item_layout_distance_tv.setText(new DecimalFormat("##.##").format(Helper.getKmFromLatLong(gpsTracker.getLatitude(), gpsTracker.getLongitude(), item.getFarmLat(), item.getFarmLong()))+ " km away from you");
        customer_home_parlour_view_holder_rating_tv.setText(String.valueOf(item.getRating()));
        Glide.with(itemView.getContext()).load(item.getCoverImage()).placeholder(R.drawable.ic_sign_up_logo).into(farmImage);
        Glide.with(itemView.getContext()).load(item.getFarmImage()).placeholder(R.drawable.ic_sign_up_logo).into(circleImageView);
        Glide.with(itemView.getContext()).load(item.getFarmImage()).placeholder(R.drawable.ic_sign_up_logo).into(circleImageView2);
        farmImage.setOnClickListener(view -> {
            Intent intent= new Intent(itemView.getContext(), FarmDetailActivity.class);
//                intent.putExtra(Constant.SERIALIZABLE_INTENT,item);
            itemView.getContext().startActivity(intent);
        });

        if (item.getSaved().equals("Yes")) {
            savedImage.setVisibility(View.VISIBLE);
            saveImage.setVisibility(View.GONE);
        }
        else {
            savedImage.setVisibility(View.GONE);
            saveImage.setVisibility(View.VISIBLE);
        }


        try {//todo Please check sajjad
            if (item.getFarm_followed_status().equals("Yes")) {
                unFollowFarmLayout.setVisibility(View.VISIBLE);
                followFarmLayout.setVisibility(View.GONE);
            }
            else {
                unFollowFarmLayout.setVisibility(View.GONE);
                followFarmLayout.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){
            unFollowFarmLayout.setVisibility(View.GONE);
            followFarmLayout.setVisibility(View.VISIBLE);
        }


        farmImage.setOnClickListener(v -> farmItemClickListener.onFarmItemClicked(getAdapterPosition()));


        savedImage.setOnClickListener(v -> {
            saveImage.setVisibility(View.VISIBLE);
            savedImage.setVisibility(View.GONE);
            farmItemClickListener.onSaveFarmClicked(item.getId(), 0, item.getFavoriteId());
        });

        saveImage.setOnClickListener(v -> {
            saveImage.setVisibility(View.GONE);
            savedImage.setVisibility(View.VISIBLE);
            farmItemClickListener.onSaveFarmClicked(item.getId(), 1, item.getFavoriteId());
        });

        followFarmLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unFollowFarmLayout.setVisibility(View.VISIBLE);
                followFarmLayout.setVisibility(View.GONE);
                farmItemClickListener.onFollowFarmClicked(item.getId(), "1", item.getFollowed_id());
            }
        });

        unFollowFarmLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unFollowFarmLayout.setVisibility(View.GONE);
                followFarmLayout.setVisibility(View.VISIBLE);
                farmItemClickListener.onFollowFarmClicked(item.getId(), "0", item.getFollowed_id());
            }
        });



    }

    public interface FarmItemClickListener {
        void onSaveFarmClicked(String id, int status, String favoriteId);
        void onFollowFarmClicked(String id, String status, String followId);
        void onFarmItemClicked(int position);
    }
}

