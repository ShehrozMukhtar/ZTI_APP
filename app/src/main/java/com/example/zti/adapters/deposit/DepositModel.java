package com.example.zti.adapters.deposit;

import android.widget.ImageView;

public class DepositModel {
    private  String name,rs;

    public DepositModel(String name, String rs) {
        this.name = name;
        this.rs = rs;
    }

    public String getName() {
        return name;
    }

    public String getRs() {
        return rs;
    }
}

