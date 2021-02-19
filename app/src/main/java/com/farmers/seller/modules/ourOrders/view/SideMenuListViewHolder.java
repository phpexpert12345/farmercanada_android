package com.farmers.seller.modules.ourOrders.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.myCart.model.MyCartItem;
import com.farmers.seller.modules.ourOrders.model.SideMenuListItem;

public class SideMenuListViewHolder extends BaseViewHolder {

    LinearLayout ll_item;
    TextView tv_item_name;
    ImageView img_icon;
    SideMenuListItem item;

    public SideMenuListViewHolder(@NonNull ViewGroup parent, final SideMenuItemClickListener sideMenuItemClickListener) {
        super(Extensions.inflate(parent, R.layout.layout_side_menu_item));
        ll_item = itemView.findViewById(R.id.ll_item);
        tv_item_name = itemView.findViewById(R.id.tv_item_name);
        img_icon = itemView.findViewById(R.id.img_icon);


        ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sideMenuItemClickListener.onSideMenuItemClicked(item);
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        SideMenuListItem item = (SideMenuListItem) items;
        this.item = item;
        tv_item_name.setText(item.getName());
        img_icon.setImageResource(item.getIcon());
    }

    public interface SideMenuItemClickListener {
        void onSideMenuItemClicked(SideMenuListItem item);
    }
}
