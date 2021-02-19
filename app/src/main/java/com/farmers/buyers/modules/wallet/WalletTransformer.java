package com.farmers.buyers.modules.wallet;

import com.farmers.buyers.R;
import com.farmers.buyers.modules.home.models.AllDataModel;
import com.farmers.buyers.modules.wallet.model.WalletHistoryItems;

import java.util.ArrayList;
import java.util.List;

/**
 * created by Mohammad Sajjad
 * on 01-02-2021 at 15:06
 * mohammadsajjad679@gmail.com
 */

public class WalletTransformer {

    public static List<WalletHistoryItems> getWalletHistory(List<AllDataModel.BannerData> walletList) {
        List<WalletHistoryItems> item = new ArrayList<>();
        for (int i = 0; i < walletList.size(); i++) {
            item.add(new WalletHistoryItems(walletList.get(i).getWallet_message(), walletList.get(i).getTransaction_status(),
                    walletList.get(i).getWallet_amount(), walletList.get(i).getAdded_date() + "," + walletList.get(i).getWalletAddedTime(),
                    R.drawable.ic_withdraw));
        }
        return item;
    }

    public static List<WalletHistoryItems> getWalletHistory() {
        List<WalletHistoryItems> item = new ArrayList<>();
        item.add(new WalletHistoryItems("Kin's Farm Market", "Paid", "29.89", "02:39 PM", R.drawable.ic_withdraw));
        item.add(new WalletHistoryItems("Kin's Farm Market", "Paid", "29.89", "02:39 PM", R.drawable.ic_wallet_deduction));
        item.add(new WalletHistoryItems("Kin's Farm Market", "Paid", "29.89", "02:39 PM", R.drawable.ic_withdraw));
        item.add(new WalletHistoryItems("Kin's Farm Market", "Paid", "29.89", "02:39 PM", R.drawable.ic_wallet_deduction));
        item.add(new WalletHistoryItems("Kin's Farm Market", "Paid", "29.89", "02:39 PM", R.drawable.ic_wallet_deduction));
        item.add(new WalletHistoryItems("Kin's Farm Market", "Paid", "29.89", "02:39 PM", R.drawable.ic_withdraw));
        return item;
    }

    public static List<WalletHistoryItems> getYesterdayHistory() {
        List<WalletHistoryItems> item = new ArrayList<>();
        item.add(new WalletHistoryItems("Kin's Farm Market", "Succes", "29.89", "02:39 PM", R.drawable.ic_wallet_deduction));
        item.add(new WalletHistoryItems("Kin's Farm Market", "Paid", "29.89", "02:39 PM", R.drawable.ic_withdraw));
        return item;
    }
}
