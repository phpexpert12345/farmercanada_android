package com.farmers.buyers.modules.followers.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.followers.model.FollowersItems;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 18:54
 * mohammadsajjad679@gmail.com
 */

public class FollowersViewHolder extends BaseViewHolder {
    CircleImageView imageView;
    TextView nameTv, distanceTv, followTv;
    private FollowerListener listener;

    public FollowersViewHolder(@NonNull ViewGroup parent, FollowerListener listener) {
        super(Extensions.inflate(parent, R.layout.followers_item_layout));
        imageView = itemView.findViewById(R.id.followers_item_user_image);
                nameTv = itemView.findViewById(R.id.followers_item_name_tv);
        distanceTv = itemView.findViewById(R.id.followers_item_distance_tv);
                followTv = itemView.findViewById(R.id.followers_item_un_follow_tv);
                this.listener = listener;
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        FollowersItems item = (FollowersItems) items;

        Glide.with(itemView.getContext()).load(item.getImageUri()).into(imageView);
        nameTv.setText(item.getName());

        followTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onFollowUnFollowFarmListener(item.getFarmId(), "0", item.getFollowId());
            }
        });
    }

    public interface FollowerListener {
        void onFollowUnFollowFarmListener(String farmId, String Status, String followId);
    }
}
