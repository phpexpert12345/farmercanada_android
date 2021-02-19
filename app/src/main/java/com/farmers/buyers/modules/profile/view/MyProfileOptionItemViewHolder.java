package com.farmers.buyers.modules.profile.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.profile.extraItems.ProfileOptionsGridItem;
import com.farmers.buyers.modules.profile.model.MyProfileOptionMenuItems;

/**
 * created by Mohammad Sajjad
 * on 31-01-2021 at 04:31
 * mohammadsajjad679@gmail.com
 */

public class MyProfileOptionItemViewHolder extends BaseViewHolder {
    ImageView optionIconImg;
    TextView menuNameTv;
    LinearLayout menuOptionLL;
    OnProfileOptionsGridMenuClickedListener profileOptionsGridMenuClicked;


    public MyProfileOptionItemViewHolder(@NonNull ViewGroup parent, OnProfileOptionsGridMenuClickedListener profileOptionsGridMenuClicked) {
        super(Extensions.inflate(parent, R.layout.my_profile_option_menu_items_layout));
        optionIconImg = itemView.findViewById(R.id.my_profile_option_img_tv);
        menuNameTv = itemView.findViewById(R.id.my_profile_option_menu_name_tv);
        menuOptionLL = itemView.findViewById(R.id.menu_option_ll);

        this.profileOptionsGridMenuClicked = profileOptionsGridMenuClicked;
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

        final MyProfileOptionMenuItems item = (MyProfileOptionMenuItems) items;

        menuNameTv.setText(((MyProfileOptionMenuItems) items).getTitle());
        optionIconImg.setImageResource(((MyProfileOptionMenuItems) items).getIcon());
        //  menuOptionLL.setBackgroundColor(itemView.getContext().getResources().getColor(((MyProfileOptionMenuItems) items).getColor()));
        menuOptionLL.setBackgroundResource(((MyProfileOptionMenuItems) items).getColor());

        menuOptionLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileOptionsGridMenuClicked.onGridMenuClicked(item.getGridItem());
            }
        });

    }

    public interface OnProfileOptionsGridMenuClickedListener {
        void onGridMenuClicked(ProfileOptionsGridItem item);
    }
}
