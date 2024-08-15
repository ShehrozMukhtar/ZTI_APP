package com.example.zti.adapters.deposit;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zti.R;
import com.example.zti.model.network.response.FetchNotificationResponse;
import com.example.zti.model.network.response.FetchUserDepositResponse;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationHolder> {
      List<FetchNotificationResponse.Data.UserNotifications> userNotifications;

    public NotificationAdapter(List<FetchNotificationResponse.Data.UserNotifications> userNotifications) {
        this.userNotifications = userNotifications;
    }

    @NonNull
    @Override
    public NotificationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationHolder holder, int position) {
        FetchNotificationResponse.Data.UserNotifications userNotification = userNotifications.get(position) ;
        holder.depositWithdraw.setText(userNotifications.get(position).notificationType);
        holder.depositApproval.setText(userNotifications.get(position).depositPayload.depositStatus);
        holder.depositDate.setText(userNotifications.get(position).createdAt);
    }
    public void setUserNotificationsModels(List<FetchNotificationResponse.Data.UserNotifications> userNotifications ) {
        this.userNotifications = userNotifications;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userNotifications.size();
    }
}
