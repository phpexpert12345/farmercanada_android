package com.farmers.buyers.modules.wallet;

import com.farmers.buyers.R;
import com.farmers.buyers.modules.wallet.model.WalletHistoryItems;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 15:06
 * mohammadsajjad679@gmail.com
 */

public class WalletTransformer {

    public static List<WalletHistoryItems> getWalletHistory() {
        List<WalletHistoryItems> item = new ArrayList<>();
        item.add(new WalletHistoryItems("Kin's Farm Market", 1, "29.89", "02:39 PM", R.drawable.ic_withdraw));
        item.add(new WalletHistoryItems("Kin's Farm Market", 0, "29.89", "02:39 PM", R.drawable.ic_wallet_deduction));
        item.add(new WalletHistoryItems("Kin's Farm Market", 1, "29.89", "02:39 PM", R.drawable.ic_withdraw));
        item.add(new WalletHistoryItems("Kin's Farm Market", 0, "29.89", "02:39 PM", R.drawable.ic_wallet_deduction));
        item.add(new WalletHistoryItems("Kin's Farm Market", 0, "29.89", "02:39 PM", R.drawable.ic_wallet_deduction));
        item.add(new WalletHistoryItems("Kin's Farm Market", 1, "29.89", "02:39 PM", R.drawable.ic_withdraw));
        return item;
    }

    public static List<WalletHistoryItems> getYesterdayHistory() {
        List<WalletHistoryItems> item = new ArrayList<>();
        item.add(new WalletHistoryItems("Kin's Farm Market", 0, "29.89", "02:39 PM", R.drawable.ic_wallet_deduction));
        item.add(new WalletHistoryItems("Kin's Farm Market", 1, "29.89", "02:39 PM", R.drawable.ic_withdraw));
        return item;
    }
}
