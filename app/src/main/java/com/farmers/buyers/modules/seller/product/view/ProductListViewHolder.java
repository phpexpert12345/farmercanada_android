package com.farmers.buyers.modules.seller.product.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.seller.product.models.ProductListItems;
import com.farmers.buyers.modules.seller.product.models.SubProductItemsRecordSeller;

import org.w3c.dom.Text;

/**
 * created by Mohammad Sajjad
 * on 08-02-2021 at 16:21
 * mohammadsajjad679@gmail.com
 */

public class ProductListViewHolder extends BaseViewHolder {
    private TextView productName, total, unitTv, categoryNameTv, couponAmountTv, quantityTv, couponCodeTv, descriptionTv, editTv, deleteTv;

    public ProductListViewHolder(@NonNull ViewGroup parent, ProductListItemClickListener listItemClickListener) {
        super(Extensions.inflate(parent, R.layout.product_list_item_layout));
        productName = itemView.findViewById(R.id.product_list_item_name_tv);
        total = itemView.findViewById(R.id.product_list_item_total_tv);
        unitTv = itemView.findViewById(R.id.product_list_item_per_unit_tv);
        categoryNameTv = itemView.findViewById(R.id.product_list_item_category_name_tv);
        couponAmountTv = itemView.findViewById(R.id.product_list_item_coupon_amount);
        quantityTv = itemView.findViewById(R.id.product_list_quantity_tv);
        couponCodeTv = itemView.findViewById(R.id.product_list_item_coupon_code);
        descriptionTv = itemView.findViewById(R.id.product_list_item_description_tv);
        deleteTv = itemView.findViewById(R.id.product_list_item_delete_btn);
        editTv = itemView.findViewById(R.id.product_list_item_edit_btn);

        deleteTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItemClickListener.onDeleteItemClickListener(getAdapterPosition());
            }
        });

        editTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItemClickListener.onEditItemClickListener(getAdapterPosition());
            }
        });


    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        ProductListItems item = (ProductListItems) items;

        productName.setText(item.getProductName());
        total.setText(item.getListTotal());
        unitTv.setText(item.getPerUnitPrice());
        categoryNameTv.setText(item.getCategory());
        couponAmountTv.setText(item.getCouponAmount());
        quantityTv.setText(item.getQuantity());
        couponCodeTv.setText(item.getCouponCode());
        descriptionTv.setText(item.getDescription());
    }

    public interface ProductListItemClickListener {
        void onDeleteItemClickListener(int position);

        void onEditItemClickListener(int position);
    }
}
