package com.farmers.buyers.modules.support.adapter;
import com.farmers.buyers.core.BaseAdapter;
import com.farmers.buyers.modules.support.view.SupportItemDelegate;
import com.farmers.buyers.storage.CardConstant;

public class SupportAdapter extends BaseAdapter {

    public SupportAdapter() {
        super();
        this.initDelegate();
    }

    @Override
    public void initDelegate() {
        delegates.put(CardConstant.SUPPORT_ITEMS_ADAPTER, new SupportItemDelegate());
    }
}
