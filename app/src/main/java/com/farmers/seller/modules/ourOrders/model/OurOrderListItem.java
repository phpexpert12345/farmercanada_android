package com.farmers.seller.modules.ourOrders.model;

import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.storage.CardConstant;

import java.util.List;

public class OurOrderListItem implements RecyclerViewListItem {

    public String order_id;
    public String customer_name;
    public String order_date;
    public String order_time;
    public String Total_amount;
    public String order_type;
    public String review_status;
    public String order_status_msg;
    public String order_status_close;
    public String farm_name;
    public String farm_logo;
    public String order_number;
    public String farm_address;
    public String login_photo;
    public String order_pick;
    public String order_closed;
    public List<RecordList> OrderRecordList;

    public OurOrderListItem() {
    }

    public OurOrderListItem(String order_id, String customer_name, String order_date,
                            String order_time, String total_amount, String order_type, String review_status,
                            String order_status_msg, String order_status_close, String farm_name, String farm_logo,
                            String order_number, String farm_address, String login_photo, String order_pick, String order_closed, List<RecordList> OrderRecordList) {
        this.order_id = order_id;
        this.customer_name = customer_name;
        this.order_date = order_date;
        this.order_time = order_time;
        Total_amount = total_amount;
        this.order_type = order_type;
        this.review_status = review_status;
        this.order_status_msg = order_status_msg;
        this.order_status_close = order_status_close;
        this.farm_name = farm_name;
        this.farm_logo = farm_logo;
        this.order_number = order_number;
        this.farm_address = farm_address;
        this.login_photo = login_photo;
        this.order_pick = order_pick;
        this.order_closed = order_closed;
        this.OrderRecordList = OrderRecordList;
    }

    @Override
    public int getViewType() {
        return CardConstant.OUR_ORDER_LIST_ADAPTER;
    }

    @Override
    public Object getUnique() {
        return this;
    }
}
