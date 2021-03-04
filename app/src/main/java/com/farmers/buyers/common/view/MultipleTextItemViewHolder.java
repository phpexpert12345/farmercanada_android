package com.farmers.buyers.common.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.farmers.buyers.R;
import com.farmers.buyers.common.Extensions;
import com.farmers.buyers.common.model.MultipleTextItems;
import com.farmers.buyers.core.BaseViewHolder;
import com.farmers.buyers.core.RecyclerViewListItem;
import com.farmers.buyers.modules.home.adapter.HomeFilterTypeAdapter;

/**
 * created by Mohammad Sajjad
 * on 27-01-2021 at 11:09
 * mohammadsajjad679@gmail.com
 */

public class MultipleTextItemViewHolder extends BaseViewHolder {

    private TextView title;
    private int selectedPosition = -1;
    private FilterItemClickListener listener;

    public MultipleTextItemViewHolder(@NonNull ViewGroup parent, final FilterItemClickListener listener) {
        super(Extensions.inflate(parent, R.layout.multiple_text_item_layout));
        this.listener = listener;
        title = itemView.findViewById(R.id.multiple_text_item_name_tv);


    }

    @Override
    public void bindView(RecyclerViewListItem items) {
        MultipleTextItems item = (MultipleTextItems) items;
        title.setText(item.getTitle());

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = getAdapterPosition();
                listener.onFilterItemClicked(getAdapterPosition());
            }
        });

        if (selectedPosition == getAdapterPosition()) {
            selectedPosition = getOldPosition();
            title.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.select_bg));
            title.setTextColor(itemView.getContext().getResources().getColor(R.color.secondaryTextColor));
        }
        else {
            title.setBackground(itemView.getContext().getResources().getDrawable(R.drawable.gray_round_border_bg));
            title.setTextColor(itemView.getContext().getResources().getColor(R.color.primaryTextColor));
        }
    }

    public interface FilterItemClickListener {
        void onFilterItemClicked(int position);
    }
}
