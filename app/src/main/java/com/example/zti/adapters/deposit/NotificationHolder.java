package com.example.zti.adapters.deposit;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zti.R;

public class NotificationHolder  extends RecyclerView.ViewHolder {
    TextView depositWithdraw,depositApproval,depositDate;

    public NotificationHolder(@NonNull View itemView) {
        super(itemView);
        depositWithdraw = itemView.findViewById(R.id.deposit_withdraw);
        depositApproval = itemView.findViewById(R.id.approval_text);
        depositDate = itemView.findViewById(R.id.date_approval);
    }
}
