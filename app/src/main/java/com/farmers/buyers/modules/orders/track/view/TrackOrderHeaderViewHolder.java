package com.farmers.buyers.modules.orders.track.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.orders.track.model.TrackOrderHeaderItems;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by Mohammad Sajjad
 * on 03-02-2021 at 13:32
 * mohammadsajjad679@gmail.com
 */

public class TrackOrderHeaderViewHolder extends BaseViewHolder {
    CircleImageView sub_order_item_farm_image;
    ImageView pickup_image_step1, pickup_image_step2, pickup_image_step3, pickup_image_step4, pickup_image_step5,
            delivery_image_step1, delivery_image_step2, delivery_image_step3, delivery_image_step4, delivery_image_step5, delivery_image_step6;
    View pickup_step_view_one, pickup_step_view_two, pickup_step_view_three, pickup_step_view_four,
            delivery_step_view_one, delivery_step_view_two, delivery_step_view_three, delivery_step_view_four, delivery_step_view_five;
    TextView tv_product_date_time, tv_product_name, tv_order_number, tv_estimate_delivery_date_time, tv_edit,
            tv_pickup_pending, tv_pickup_accepted, tv_pickup_processing, tv_pickup_picked, tv_pickup_cancel,
            tv_delivery_pending, tv_delivery_accepted, tv_delivery_processing, tv_delivery_shipped, tv_delivery_delivery, tv_delivery_cancel,track_order_header_estimate_tv,tv_farm_address;
    LinearLayout ll_pickup_text, ll_pickup_img, ll_delivery_text, ll_delivery_img;

    /*Pickup
     * Pending Accepted Processing Picked-up Cancelled*/

    /*Delivery case
     * Pending Accepted Processing Shipped Delivered Cancelled*/

    public TrackOrderHeaderViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.track_order_header_item_layout));
        pickup_image_step1 = itemView.findViewById(R.id.pickup_image_step1);
        pickup_image_step2 = itemView.findViewById(R.id.pickup_image_step2);
        pickup_image_step3 = itemView.findViewById(R.id.pickup_image_step3);
        pickup_image_step4 = itemView.findViewById(R.id.pickup_image_step4);
        pickup_image_step5 = itemView.findViewById(R.id.pickup_image_step5);

        delivery_image_step1 = itemView.findViewById(R.id.delivery_image_step1);
        delivery_image_step2 = itemView.findViewById(R.id.delivery_image_step2);
        delivery_image_step3 = itemView.findViewById(R.id.delivery_image_step3);
        delivery_image_step4 = itemView.findViewById(R.id.delivery_image_step4);
        delivery_image_step5 = itemView.findViewById(R.id.delivery_image_step5);
        delivery_image_step6 = itemView.findViewById(R.id.delivery_image_step6);

        pickup_step_view_one = itemView.findViewById(R.id.pickup_step_view_one);
        pickup_step_view_two = itemView.findViewById(R.id.pickup_step_view_two);
        pickup_step_view_three = itemView.findViewById(R.id.pickup_step_view_three);
        pickup_step_view_four = itemView.findViewById(R.id.pickup_step_view_four);

        delivery_step_view_one = itemView.findViewById(R.id.delivery_step_view_one);
        delivery_step_view_two = itemView.findViewById(R.id.delivery_step_view_two);
        delivery_step_view_three = itemView.findViewById(R.id.delivery_step_view_three);
        delivery_step_view_four = itemView.findViewById(R.id.delivery_step_view_four);
        delivery_step_view_five = itemView.findViewById(R.id.delivery_step_view_five);

        tv_pickup_pending = itemView.findViewById(R.id.tv_pickup_pending);
        tv_pickup_accepted = itemView.findViewById(R.id.tv_pickup_accepted);
        tv_pickup_processing = itemView.findViewById(R.id.tv_pickup_processing);
        tv_pickup_picked = itemView.findViewById(R.id.tv_pickup_picked);
        tv_pickup_cancel = itemView.findViewById(R.id.tv_pickup_cancel);

        tv_delivery_pending = itemView.findViewById(R.id.tv_delivery_pending);
        tv_delivery_accepted = itemView.findViewById(R.id.tv_delivery_accepted);
        tv_delivery_processing = itemView.findViewById(R.id.tv_delivery_processing);
        tv_delivery_shipped = itemView.findViewById(R.id.tv_delivery_shipped);
        tv_delivery_delivery = itemView.findViewById(R.id.tv_delivery_delivery);
        tv_delivery_cancel = itemView.findViewById(R.id.tv_delivery_cancel);

        ll_pickup_text = itemView.findViewById(R.id.ll_pickup_text);
        ll_pickup_img = itemView.findViewById(R.id.ll_pickup_img);
        ll_delivery_text = itemView.findViewById(R.id.ll_delivery_text);
        ll_delivery_img = itemView.findViewById(R.id.ll_delivery_img);

        sub_order_item_farm_image = itemView.findViewById(R.id.sub_order_item_farm_image);

        tv_product_date_time = itemView.findViewById(R.id.tv_product_date_time);
        tv_product_name = itemView.findViewById(R.id.tv_product_name);
        tv_order_number = itemView.findViewById(R.id.tv_order_number);
        tv_estimate_delivery_date_time = itemView.findViewById(R.id.tv_estimate_delivery_date_time);
        tv_edit = itemView.findViewById(R.id.tv_edit);
        track_order_header_estimate_tv=itemView.findViewById(R.id.track_order_header_estimate_tv);
        tv_farm_address=itemView.findViewById(R.id.tv_farm_address);
    }

    @Override
    public void bindView(RecyclerViewListItem items) {

        TrackOrderHeaderItems item = (TrackOrderHeaderItems) items;
        tv_product_date_time.setText(item.getDate());
        tv_product_name.setText(item.getName());
        tv_order_number.setText(item.getOrderId());
        tv_estimate_delivery_date_time.setText(item.getDeliveryTime());
        track_order_header_estimate_tv.setText("Estimated "+item.order_type+" Time");
        tv_farm_address.setText(item.getFarm_address());
        Glide.with(itemView.getContext())
                .load(item.getLogo())
                .placeholder(R.drawable.ic_sign_up_logo)
                .into(sub_order_item_farm_image);

        if (item.order_type.equalsIgnoreCase("Pickup")) {
            ll_pickup_text.setVisibility(View.VISIBLE);
            ll_pickup_img.setVisibility(View.VISIBLE);
            ll_delivery_text.setVisibility(View.GONE);
            ll_delivery_img.setVisibility(View.GONE);
        } else {

            ll_pickup_text.setVisibility(View.GONE);
            ll_pickup_img.setVisibility(View.GONE);
            ll_delivery_text.setVisibility(View.VISIBLE);
            ll_delivery_img.setVisibility(View.VISIBLE);
        }

        switch (item.order_status_msg) {
            case "Pending": {
                pickup_image_step1.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                pickup_step_view_one.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_pickup_pending.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));

                delivery_image_step1.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_one.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_pending.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                break;
            }
            case "Accepted": {
                pickup_image_step1.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                pickup_step_view_one.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                pickup_image_step2.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                pickup_step_view_two.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_pickup_pending.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_pickup_accepted.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));

                delivery_image_step1.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_one.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                delivery_image_step2.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_two.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_pending.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_accepted.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                break;
            }
            case "Processing": {
                pickup_image_step1.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                pickup_step_view_one.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                pickup_image_step2.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                pickup_step_view_two.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                pickup_image_step3.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                pickup_step_view_three.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_pickup_pending.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_pickup_accepted.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_pickup_processing.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));

                delivery_image_step1.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_one.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                delivery_image_step2.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_two.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                delivery_image_step3.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_three.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_pending.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_accepted.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_processing.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                break;
            }
            case "Picked-up": {
                pickup_image_step1.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                pickup_step_view_one.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                pickup_image_step2.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                pickup_step_view_two.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                pickup_image_step3.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                pickup_step_view_three.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                pickup_image_step4.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                pickup_step_view_four.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_pickup_pending.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_pickup_accepted.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_pickup_processing.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_pickup_picked.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                break;
            }
            case "Cancelled": {
                pickup_image_step1.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                pickup_step_view_one.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                pickup_image_step2.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                pickup_step_view_two.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                pickup_image_step3.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                pickup_step_view_three.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                pickup_image_step4.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                pickup_image_step5.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                pickup_step_view_four.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_pickup_pending.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_pickup_accepted.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_pickup_processing.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_pickup_picked.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_pickup_cancel.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));


                delivery_image_step1.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_one.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                delivery_image_step2.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_two.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                delivery_image_step3.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_three.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                delivery_image_step4.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_image_step5.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_four.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_pending.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_accepted.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_processing.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_shipped.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_cancel.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));

                break;
            }
            case "Shipped": {

                delivery_image_step1.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_one.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                delivery_image_step2.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_two.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                delivery_image_step3.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_three.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                delivery_image_step4.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_image_step5.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_four.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_pending.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_accepted.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_processing.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_shipped.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_cancel.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                delivery_image_step4.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_four.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_shipped.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                break;
            }
            case "Delivered": {
                delivery_image_step1.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_one.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                delivery_image_step2.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_two.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                delivery_image_step3.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_three.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                delivery_image_step4.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_image_step5.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_four.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_pending.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_accepted.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_processing.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_shipped.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_cancel.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                delivery_image_step4.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                delivery_step_view_four.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                delivery_image_step6.setImageDrawable(itemView.getResources().getDrawable(R.drawable.ic_green_check));
                tv_delivery_shipped.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                delivery_step_view_five.setBackgroundColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                tv_delivery_delivery.setTextColor(itemView.getResources().getColor(R.color.secondaryBtnColor));
                break;
            }
        }
    }
}
