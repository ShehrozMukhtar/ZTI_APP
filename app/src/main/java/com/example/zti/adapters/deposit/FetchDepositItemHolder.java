package com.example.zti.adapters.deposit;

import android.view.View;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zti.R;

public class FetchDepositItemHolder extends RecyclerView.ViewHolder {
    TextView mText,mRs;
    public FetchDepositItemHolder(@NonNull View itemView) {
        super(itemView);
        mText = itemView.findViewById(R.id.bank_name);
        mRs = itemView.findViewById(R.id.rupee);
    }
}
