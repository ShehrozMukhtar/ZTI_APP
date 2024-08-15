package com.example.zti.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zti.R;
import com.example.zti.adapters.BankItemViewHolder;

import java.util.List;

public class BankAdapter extends RecyclerView.Adapter<BankItemViewHolder> {
       List<String> banks ;
       BankAdapterEvent event;

    public BankAdapter(List<String> banks, BankAdapterEvent event) {
        this.banks = banks;
        this.event = event;
    }

    @NonNull
    @Override
    public BankItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BankItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bank,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BankItemViewHolder holder, int position) {
            String bankNames = banks.get(position);
            holder.mEasy.setText(bankNames);

            holder.mEasy.setOnClickListener(v -> {
                event.onClicked(position);
            });
    }

    @Override
    public int getItemCount() {
        return banks.size();
    }
}
