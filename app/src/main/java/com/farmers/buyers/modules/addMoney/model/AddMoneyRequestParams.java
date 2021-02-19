package com.farmers.buyers.modules.addMoney.model;

import java.io.File;

/**
 * created by Mohammad Sajjad
 * on 05-02-2021 at 18:35
 * mohammadsajjad679@gmail.com
 */

public class AddMoneyRequestParams {
    String LoginId;
    String wallet_amount;
    String wallet_transation_id;
    String wallet_transation_status;
    String authKey;
    String account_name;
    String account_email;
    File file;

    public AddMoneyRequestParams(String loginId, String account_name, String account_email, File file, String authKey) {
        LoginId = loginId;
        this.account_name = account_name;
        this.account_email = account_email;
        this.file = file;
        this.authKey = authKey;
    }

    public AddMoneyRequestParams(String loginId, String wallet_amount, String wallet_transation_id, String wallet_transation_status, String authKey) {
        LoginId = loginId;
        this.wallet_amount = wallet_amount;
        this.wallet_transation_id = wallet_transation_id;
        this.wallet_transation_status = wallet_transation_status;
        this.authKey = authKey;
    }

    public String getAccount_name() {
        return account_name;
    }

    public String getAccount_email() {
        return account_email;
    }

    public File getFile() {
        return file;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public void setAccount_email(String account_email) {
        this.account_email = account_email;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setLoginId(String loginId) {
        LoginId = loginId;
    }

    public void setWallet_amount(String wallet_amount) {
        this.wallet_amount = wallet_amount;
    }

    public void setWallet_transation_id(String wallet_transation_id) {
        this.wallet_transation_id = wallet_transation_id;
    }

    public void setWallet_transation_status(String wallet_transation_status) {
        this.wallet_transation_status = wallet_transation_status;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getLoginId() {
        return LoginId;
    }

    public String getWallet_amount() {
        return wallet_amount;
    }

    public String getWallet_transation_id() {
        return wallet_transation_id;
    }

    public String getWallet_transation_status() {
        return wallet_transation_status;
    }

    public String getAuthKey() {
        return authKey;
    }
}
