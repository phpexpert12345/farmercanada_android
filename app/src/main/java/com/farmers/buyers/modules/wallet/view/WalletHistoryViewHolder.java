package com.farmers.buyers.modules.wallet.view;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.wallet.model.WalletHistoryItems;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 15:06
 * mohammadsajjad679@gmail.com
 */

public class WalletHistoryViewHolder extends BaseViewHolder {

    TextView nameTv;
    TextView paymentStatusTv;
    TextView amountTv;
    TextView timeTv;
    ImageView paymentStatusImg;

    public WalletHistoryViewHolder(@NonNull ViewGroup parent) {
        super(Extensions.inflate(parent, R.layout.wallet_history_item_layout));

        nameTv = itemView.findViewById(R.id.wallet_history_item_name_tv);
        paymentStatusTv = itemView.findViewById(R.id.wallet_history_item_status_tv);
        amountTv = itemView.findViewById(R.id.wallet_history_item_amount_tv);
        timeTv = itemView.findViewById(R.id.wallet_history_item_time_tv);
        paymentStatusImg = itemView.findViewById(R.id.wallet_history_item_status_image);

    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        WalletHistoryItems item = (WalletHistoryItems) items;
        nameTv.setText(item.getShopName());
        paymentStatusTv.setText(String.valueOf(item.getStatus()));
        amountTv.setText("+$"+item.getAmount());
        timeTv.setText(item.getDate()+", "+ConvertDateTolocal(item.getTime()));

        // paymentStatusImg.setImageResource();
    }
    private  String  ConvertDateTolocal(String serverDate){
        String  date_format="HH:mm:ss a";
        String  ourDate;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(date_format, Locale.UK);
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date value = formatter.parse(serverDate);
            TimeZone  timeZone=TimeZone.getTimeZone("Asia/Kolkata");
            SimpleDateFormat dateFormat = new SimpleDateFormat(date_format, Locale.UK);
            dateFormat.setTimeZone(timeZone);
            ourDate=dateFormat.format(value);

        }
        catch (Exception  e){
            e.printStackTrace();
            ourDate="00:00:00 am";
        }
        return ourDate;
    }
}
