package com.farmers.buyers.modules.address.view;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.cart.checkout.model.CheckOutCartAddressItems;

public class MyAddressListViewHolder extends BaseViewHolder {

    TextView addressTv;
    TextView addressDetailTv;
    TextView addressTypeTv;
    TextView changeAddress;
    LinearLayout addressCard;
    AddressItemClickListener addressItemClickListener;

    public MyAddressListViewHolder(@NonNull ViewGroup parent, final AddressItemClickListener addressItemClickListener) {
        super(Extensions.inflate(parent, R.layout.check_out_from_cart_address_holder_layout));
        this.addressItemClickListener = addressItemClickListener;
        addressTv = itemView.findViewById(R.id.my_address_tv);
        addressDetailTv = itemView.findViewById(R.id.my_address_detail_tv);
        addressTypeTv = itemView.findViewById(R.id.my_address_type_tv);
        addressCard = itemView.findViewById(R.id.my_address_layout);
        changeAddress = itemView.findViewById(R.id.address_change_tv);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void bindView(RecyclerViewListItem items) {
        CheckOutCartAddressItems item = (CheckOutCartAddressItems) items;
        addressTv.setText(item.getAddress());
        addressDetailTv.setText(item.getPhoneNumber());
        addressTypeTv.setText(item.getAddressTitle());

        addressCard.setOnClickListener(view -> addressItemClickListener.onAddressItemClicked(item));

//        addressCard.setOnTouchListener((arg0, arg1) -> {
//            addressItemClickListener.onAddressItemClicked(item);
//            return false;
//        });

     /*   if (item.getSelected()) {
            addressCard.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.light_red_border_bg));
        } else {
            addressCard.setBackground(null);
        }

        if (item.getCanChange()) {
            changeAddress.setVisibility(View.VISIBLE);
        } else {
            changeAddress.setVisibility(View.GONE);
        }*/

    }

    public interface AddressItemClickListener {
        void onAddressItemClicked(CheckOutCartAddressItems addressId);
    }
}
