package com.farmers.buyers.modules.farmDetail.view;

import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.farmDetail.adapter.FarmDetailHeaderAdapter;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailHeaderListItem;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 14:40
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailHeaderViewHolder extends BaseViewHolder {
    private ImageView backImage;
    private TextView tv_address, tv_follow_status;
    private ImageView farm_Detail_image;
    private CircleImageView img_logo;
    private LinearLayout ll_follow;
    private FarmDetailHeaderListItem item;

    public FarmDetailHeaderViewHolder(@NonNull ViewGroup parent, final FarmHeaderClickListener listener) {
        super(Extensions.inflate(parent, R.layout.farm_detail_header_layout));
        tv_address = itemView.findViewById(R.id.tv_address);
        backImage = itemView.findViewById(R.id.farm_detail_header_back_img);
        farm_Detail_image = itemView.findViewById(R.id.farm_Detail_image);
        img_logo = itemView.findViewById(R.id.img_logo);
        ll_follow = itemView.findViewById(R.id.ll_follow);
        tv_follow_status = itemView.findViewById(R.id.tv_follow_status);

        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onOnBackClickListener();
            }
        });
        ll_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onFollowClickListener(item.getFollowStatus(), item.getFollowed_id());
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        FarmDetailHeaderListItem item = (FarmDetailHeaderListItem) items;
        this.item = item;
        tv_address.setText(item.getAddress());
        Glide.with(itemView.getContext())
                .load(item.getImage())
                .placeholder(R.drawable.ic_sign_up_logo)
                .into(img_logo);

        Glide.with(itemView.getContext())
                .load(item.getCoverImage())
                .placeholder(R.drawable.ic_sign_up_logo)
                .into(farm_Detail_image);

        if (item.getFollowStatus().equals("No")) {
            tv_follow_status.setText("Follow this farm");
        } else {
            tv_follow_status.setText("Unfollow this farm");
        }
    }

    public interface FarmHeaderClickListener {
        void onOnBackClickListener();

        void onFollowClickListener(String followStatus, String id);
    }
}