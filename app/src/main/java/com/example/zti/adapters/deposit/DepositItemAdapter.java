package com.example.zti.adapters.deposit;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.zti.R;
import com.example.zti.adapters.ItemModel;
import com.example.zti.model.network.response.FetchUserDepositResponse;

import java.util.List;



public class DepositItemAdapter extends RecyclerView.Adapter<FetchDepositItemHolder> {
   List<FetchUserDepositResponse.Data.UserDeposits> depositModels;

    public DepositItemAdapter(List<FetchUserDepositResponse.Data.UserDeposits> depositModels) {
        this.depositModels = depositModels;
    }

    @NonNull
    @Override
    public FetchDepositItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FetchDepositItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fetch_deposit,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FetchDepositItemHolder holder, int position) {
        FetchUserDepositResponse.Data.UserDeposits userDeposit = depositModels.get(position);


        // set this in deposited amount userDeposit.originalInvestedAmount
        // show this as bank type userDeposit.bankType

        holder.mText.setText(depositModels.get(position).accountHolderName);
        holder.mRs.setText(depositModels.get(position).originalInvestedAmount);

    }

    public void setDepositModels(List<FetchUserDepositResponse.Data.UserDeposits> depositModels) {
        this.depositModels = depositModels;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return depositModels.size();
    }
}
