package com.farmers.buyers.modules.home.view;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 13:20
 * mohammadsajjad679@gmail.com
 */

public class HomeHeaderViewHolder extends BaseViewHolder {
    private ImageView editAddressImage;
    private HeaderItemClickListener listener;
    private LinearLayout becomeSellerLL;

    public HomeHeaderViewHolder(@NonNull ViewGroup parent, final HeaderItemClickListener listener) {
        super(Extensions.inflate(parent, R.layout.home_header_item_view_holder_layout));
        editAddressImage = itemView.findViewById(R.id.home_header_edit_image);
        becomeSellerLL = itemView.findViewById(R.id.home_header_become_seller_ll);
        this.listener = listener;


    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        itemView.findViewById(R.id.home_header_edit_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("position", String.valueOf(getAdapterPosition()));
            }
        });

        becomeSellerLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onBecomeSellerClicked();
            }
        });
    }


    public interface HeaderItemClickListener {
        void onEditAddressClickListener(int position);
        void onBecomeSellerClicked();
    }

}


