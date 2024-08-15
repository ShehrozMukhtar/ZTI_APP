package com.example.zti.adapters;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zti.R;
public class BankItemViewHolder extends RecyclerView.ViewHolder {
     TextView mEasy;


    public BankItemViewHolder(@NonNull View itemView) {
        super(itemView);
        mEasy = itemView.findViewById(R.id.easy);

    }

}

