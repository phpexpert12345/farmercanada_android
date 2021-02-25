package com.farmers.buyers.modules.cart.checkout.view;

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

/**
 * created by Mohammad Sajjad
 * on 30-01-2021 at 11:13
 * mohammadsajjad679@gmail.com
 */

public class CheckOutFromCartAddressViewHolder extends BaseViewHolder {

    TextView addressTv;
    TextView addressDetailTv;
    TextView addressTypeTv;
    TextView changeAddress;
    LinearLayout addressCard;
    ChangeAddressCallback addressCallback;

    //String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("SERVICE_TYPE", ""))
    public CheckOutFromCartAddressViewHolder(@NonNull ViewGroup parent, final ChangeAddressCallback addressCallback1) {
        super(Extensions.inflate(parent, R.layout.check_out_from_cart_address_holder_layout));
        addressTv = itemView.findViewById(R.id.my_address_tv);
        addressDetailTv = itemView.findViewById(R.id.my_address_detail_tv);
        addressTypeTv = itemView.findViewById(R.id.my_address_type_tv);
        addressCard = itemView.findViewById(R.id.my_address_layout);
        changeAddress = itemView.findViewById(R.id.address_change_tv);
        addressCallback = addressCallback1;
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        CheckOutCartAddressItems item = (CheckOutCartAddressItems) items;
        addressTv.setText(item.getAddress());
        addressDetailTv.setText(item.getPhoneNumber());
        addressTypeTv.setText(item.getAddressTitle());
        changeAddress.setVisibility(View.VISIBLE);

        changeAddress.setOnClickListener(view -> addressCallback.onEditAddressClicked(item));
    }

    public interface ChangeAddressCallback {
        void onEditAddressClicked(CheckOutCartAddressItems addressDetail);
    }
}
