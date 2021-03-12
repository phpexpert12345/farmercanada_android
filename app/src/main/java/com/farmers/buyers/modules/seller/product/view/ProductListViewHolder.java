package com.farmers.buyers.modules.seller.product.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.farmers.buyers.R;
import com.farmers.buyers.app.AppController;
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
    private ImageView product_list_item_img;
    private TextView product_list_add_stock_btn;
    private ProductListItems item;

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
        product_list_item_img = itemView.findViewById(R.id.product_list_item_img);
        product_list_add_stock_btn = itemView.findViewById(R.id.product_list_add_stock_btn);

        deleteTv.setOnClickListener(v -> listItemClickListener.onDeleteItemClickListener(getAdapterPosition()));

        editTv.setOnClickListener(v -> listItemClickListener.onEditItemClickListener(getAdapterPosition()));

        product_list_add_stock_btn.setOnClickListener(view -> listItemClickListener.onAddStockItemClickListener(item));
    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        ProductListItems item = (ProductListItems) items;
        this.item = item;
        Glide.with(itemView.getContext())
                .load(((ProductListItems) items).product_images)
                .placeholder(R.drawable.fruit_two)
                .into(product_list_item_img);

        productName.setText(item.product_name);
        total.setText(AppController.get().getCurrencyCodeKey() + item.product_unit_price);
        unitTv.setText(item.product_sales_price + "/");
        categoryNameTv.setText(item.product_category_Name);
        // couponAmountTv.setText(item.getCouponAmount());
        quantityTv.setText(item.product_stock);
        // couponCodeTv.setText(item.getCouponCode());
        descriptionTv.setText(item.product_description);
    }

    public interface ProductListItemClickListener {
        void onDeleteItemClickListener(int position);

        void onEditItemClickListener(int position);

        void onAddStockItemClickListener(ProductListItems item);
    }
}
