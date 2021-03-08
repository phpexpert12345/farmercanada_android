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

    TextView tv_customer_name, tv_date, tv_order_number, tv_total_amount, tv_customer_address;
    CircleImageView civ_call, civ_eye, civ_farm_image;
    CardView messageCard;
    LinearLayout ll_order_accept, ll_order_reject;
    RecyclerView rv_sub_product_list;
    OurOrderListItem item;

    public OurOrderListViewHolder(@NonNull ViewGroup parent, final OurOrderItemClickListener ourOrderItemClickListener) {
        super(Extensions.inflate(parent, R.layout.our_order_list_item_layout));
        //messageCard = itemView.findViewById(R.id.message_item_card);
        tv_customer_name = itemView.findViewById(R.id.tv_customer_name);
        tv_date = itemView.findViewById(R.id.tv_date);
        tv_order_number = itemView.findViewById(R.id.tv_order_number);
        tv_total_amount = itemView.findViewById(R.id.tv_total_amount);
        civ_call = itemView.findViewById(R.id.civ_call);
        civ_eye = itemView.findViewById(R.id.civ_eye);
        tv_customer_address = itemView.findViewById(R.id.tv_customer_address);
        civ_farm_image = itemView.findViewById(R.id.civ_farm_image);
        rv_sub_product_list = itemView.findViewById(R.id.rv_sub_product_list);

        ll_order_accept = itemView.findViewById(R.id.ll_order_accept);
        ll_order_reject = itemView.findViewById(R.id.ll_order_reject);

      /*  messageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ourOrderItemClickListener.onOurOrderItemClicked(getAdapterPosition());
            }
        });*/

        ll_order_reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ourOrderItemClickListener.onOurOrderItemClicked(item);
            }
        });
        ll_order_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ourOrderItemClickListener.onOurOrderItemClicked(item);
            }
        });
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        OurOrderListItem item = (OurOrderListItem) items;
        this.item = item;
        Glide.with(itemView.getContext())
                .load(item.farm_logo)
                .placeholder(R.drawable.farm_image)
                .into(civ_farm_image);

        tv_customer_name.setText(item.customer_name);
        tv_date.setText(item.order_time);
        tv_order_number.setText(item.order_number);
        tv_total_amount.setText("$" + item.Total_amount);
        tv_customer_address.setText(item.farm_address);

        SubRecordAdapter subRecordAdapter = new SubRecordAdapter(item.OrderRecordList);
        rv_sub_product_list.setHasFixedSize(true);
        rv_sub_product_list.setLayoutManager(new LinearLayoutManager(itemView.getContext(),
                LinearLayoutManager.VERTICAL, false));
        rv_sub_product_list.setAdapter(subRecordAdapter);
        rv_sub_product_list.setNestedScrollingEnabled(false);
    }

    public interface OurOrderItemClickListener {
        void onOurOrderItemClicked(OurOrderListItem item);
    }
}
