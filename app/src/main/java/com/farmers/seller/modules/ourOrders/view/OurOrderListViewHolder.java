package com.farmers.seller.modules.ourOrders.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.seller.modules.ourOrders.adapter.SubRecordAdapter;
import com.farmers.seller.modules.ourOrders.model.OurOrderListItem;

import de.hdodenhof.circleimageview.CircleImageView;

public class OurOrderListViewHolder extends BaseViewHolder {

    TextView tv_customer_name, tv_date, tv_order_number, tv_total_amount, tv_customer_address, tv_order_type;
    CircleImageView civ_call, civ_eye, civ_farm_image;
    CardView messageCard;
    LinearLayout ll_order_details, ll_order_accept, ll_order_reject, ll_both_action;
    RecyclerView rv_sub_product_list;
    OurOrderListItem item;

    public OurOrderListViewHolder(@NonNull ViewGroup parent, final OurOrderItemClickListener ourOrderItemClickListener) {
        super(Extensions.inflate(parent, R.layout.our_order_list_item_layout));
        messageCard = itemView.findViewById(R.id.message_item_card);
        tv_customer_name = itemView.findViewById(R.id.tv_customer_name);
        tv_date = itemView.findViewById(R.id.tv_date);
        tv_order_number = itemView.findViewById(R.id.tv_order_number);
        tv_total_amount = itemView.findViewById(R.id.tv_total_amount);
        civ_call = itemView.findViewById(R.id.civ_call);
        civ_eye = itemView.findViewById(R.id.civ_eye);
        tv_customer_address = itemView.findViewById(R.id.tv_customer_address);
        civ_farm_image = itemView.findViewById(R.id.civ_farm_image);
        rv_sub_product_list = itemView.findViewById(R.id.rv_sub_product_list);
        tv_order_type = itemView.findViewById(R.id.tv_order_type);
        ll_both_action = itemView.findViewById(R.id.ll_both_action);

        ll_order_details = itemView.findViewById(R.id.ll_order_details);
        ll_order_accept = itemView.findViewById(R.id.ll_order_accept);
        ll_order_reject = itemView.findViewById(R.id.ll_order_reject);

        messageCard.setOnClickListener(view -> ourOrderItemClickListener.onOurOrderItemClicked(item));

        ll_order_reject.setOnClickListener(view -> ourOrderItemClickListener.onOurOrderItemDeclineClicked(item));

        ll_order_accept.setOnClickListener(view -> ourOrderItemClickListener.onOurOrderItemAcceptClicked(item));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        OurOrderListItem item = (OurOrderListItem) items;
        this.item = item;
        Glide.with(itemView.getContext())
                .load(item.login_photo)
                .placeholder(R.drawable.user_profile_icon)
                .into(civ_farm_image);

        if (item.order_pick.equals("0")) {
            ll_both_action.setVisibility(View.VISIBLE);
        } else {
            ll_both_action.setVisibility(View.GONE);
        }

        tv_customer_name.setText(item.customer_name);
        tv_date.setText(item.order_time + ", " + item.order_date);
        tv_order_number.setText(item.order_number);
        tv_total_amount.setText("$" + item.Total_amount);
        tv_customer_address.setText(item.farm_address);
        tv_order_type.setText(item.order_type);

        SubRecordAdapter subRecordAdapter = new SubRecordAdapter(item.OrderRecordList);
        rv_sub_product_list.setHasFixedSize(true);
        rv_sub_product_list.setLayoutManager(new LinearLayoutManager(itemView.getContext(),
                LinearLayoutManager.VERTICAL, false));
        rv_sub_product_list.setAdapter(subRecordAdapter);
        rv_sub_product_list.setNestedScrollingEnabled(false);
    }

    public interface OurOrderItemClickListener {
        void onOurOrderItemClicked(OurOrderListItem item);

        void onOurOrderItemAcceptClicked(OurOrderListItem item);

        void onOurOrderItemDeclineClicked(OurOrderListItem item);
    }
}
