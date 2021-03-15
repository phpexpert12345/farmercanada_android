package com.farmers.buyers.modules.farmDetail.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.common.utils.Helper;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.farmDetail.model.FarmDetailItems;
import com.farmers.buyers.modules.home.view.HomeDeliveryTypeViewHolder;
import com.farmers.buyers.storage.GPSTracker;
import com.farmers.buyers.storage.SharedPreferenceManager;

import java.text.DecimalFormat;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * created by Mohammad Sajjad
 * on 28-01-2021 at 11:58
 * mohammadsajjad679@gmail.com
 */

public class FarmDetailViewHolder extends BaseViewHolder {
    TextView farm_detail_item_farm_name_tv, tv_distance, tv_opening_time,
            tv_rating, tv_open_status, tv_hosted_by, tv_hosted_by_name, reviewTv;
    CircleImageView civ_farm_image;
    private SwitchCompat toggle;
    private TextView deliveryTv, pickUpTv;
    private FarmDetailItemClickListener farmDetailItemClickListener;
    String pickup_available,delivery_available;
    TextView tv_address;
    private GPSTracker gpsTracker = new GPSTracker(itemView.getContext());
    HomeDeliveryTypeViewHolder.DeliveryTypeCheckedChangeListener deliveryTypeCheckedChangeListener;

    public FarmDetailViewHolder(@NonNull ViewGroup parent, HomeDeliveryTypeViewHolder.DeliveryTypeCheckedChangeListener deliveryTypeCheckedChangeListener, FarmDetailItemClickListener farmDetailItemClickListener,String pickup_available,String delivery_available) {
        super(Extensions.inflate(parent, R.layout.farm_detail_item_layout));
this.deliveryTypeCheckedChangeListener=deliveryTypeCheckedChangeListener;
        farm_detail_item_farm_name_tv = itemView.findViewById(R.id.farm_detail_item_farm_name_tv);
        tv_distance = itemView.findViewById(R.id.tv_distance);
        tv_opening_time = itemView.findViewById(R.id.tv_opening_time);
        tv_rating = itemView.findViewById(R.id.tv_rating);
        tv_rating = itemView.findViewById(R.id.tv_rating);
        toggle = itemView.findViewById(R.id.toggleButton1);
        tv_open_status = itemView.findViewById(R.id.tv_open_status);
        tv_hosted_by = itemView.findViewById(R.id.tv_hosted_by);
        tv_hosted_by_name = itemView.findViewById(R.id.tv_hosted_by_name);
        deliveryTv = itemView.findViewById(R.id.app_toggle_delivery_tv);
        pickUpTv = itemView.findViewById(R.id.app_toggle_pickup_tv);
        deliveryTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primary_button_color));
        civ_farm_image = itemView.findViewById(R.id.civ_farm_image);
        reviewTv = itemView.findViewById(R.id.farm_detail_item_review_tv);
        tv_address=itemView.findViewById(R.id.tv_address);
        this.farmDetailItemClickListener = farmDetailItemClickListener;
        this.pickup_available=pickup_available;
        this.delivery_available=delivery_available;
if(delivery_available.equalsIgnoreCase("Yes")) {
    if (String.valueOf(SharedPreferenceManager.getInstance().getSharedPreferences("SERVICE_TYPE", "")).equals("0")) {
        deliveryTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primary_button_color));
        pickUpTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
        toggle.setChecked(false);
    } else {
        pickUpTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primary_button_color));
        deliveryTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
        toggle.setChecked(true);
    }
}
else if(pickup_available.equalsIgnoreCase("Yes")){
    pickUpTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primary_button_color));
    deliveryTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
    toggle.setChecked(true);
}



    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        FarmDetailItems item = (FarmDetailItems) items;

        Glide.with(itemView.getContext())
                .load(item.getFarmImage())
                .centerCrop()
                .placeholder(R.drawable.ic_sign_up_logo)
                .into(civ_farm_image);

        farm_detail_item_farm_name_tv.setText(item.getFarmName());
        tv_address.setText(item.getFarmAddress());
        tv_distance.setText(new DecimalFormat("##.##").format(Helper.getKmFromLatLong(gpsTracker.getLatitude(), gpsTracker.getLongitude(), item.getFarmLat(), item.getFarmLong()))+ " km away from you");
//        tv_distance.setText(item.getFarm_delivery_radius_text());
        tv_opening_time.setText(item.getFarm_estimate_delivery_time());
        tv_rating.setText(item.getRating());
        //  tv_open_status.setText(item.getFarm_followed_status());
        tv_hosted_by.setText(item.getFarm_opening_hours());
        tv_hosted_by_name.setText("Hosted By " + item.getHostedBy());

        toggle.setOnCheckedChangeListener((compoundButton, b) -> {
            if(item.getDelivery_available().equalsIgnoreCase("no")){
                toggle.setChecked(true);
                pickUpTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primary_button_color));
                deliveryTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
                Toast.makeText(itemView.getContext(), "Sorry we are not Providing delivery currently!!", Toast.LENGTH_SHORT).show();

            }
            else if(item.getPickup_available().equalsIgnoreCase("no")){
                toggle.setChecked(false);
                deliveryTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primary_button_color));
                pickUpTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
                Toast.makeText(itemView.getContext(), "Sorry we are not Providing pickup currently!!", Toast.LENGTH_SHORT).show();
            }
            else if(item.getPickup_available().equalsIgnoreCase("Yes")){
                if(b){
                    pickUpTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primary_button_color));
                    deliveryTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
                    deliveryTypeCheckedChangeListener.onDeliveryTypeCheckedChangeListener(1);
                }
                else if(item.getDelivery_available().equalsIgnoreCase("Yes")){
                    deliveryTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primary_button_color));
                    pickUpTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
                    deliveryTypeCheckedChangeListener.onDeliveryTypeCheckedChangeListener(0);
                }
            }
//            if (b) {
//                pickUpTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primary_button_color));
//                deliveryTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
//                deliveryTypeCheckedChangeListener.onDeliveryTypeCheckedChangeListener(1);
//            } else {
//                deliveryTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primary_button_color));
//                pickUpTv.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
//                deliveryTypeCheckedChangeListener.onDeliveryTypeCheckedChangeListener(0);
//            }
        });

        reviewTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                farmDetailItemClickListener.onFarmDetailItemClicked(item.getFarmId());
            }
        });
    }

    public interface FarmDetailItemClickListener {
        void onFarmDetailItemClicked(String id);
    }
}
