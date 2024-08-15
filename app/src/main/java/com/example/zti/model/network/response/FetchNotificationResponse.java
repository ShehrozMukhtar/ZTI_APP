package com.example.zti.model.network.response;

import com.airbnb.lottie.L;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchNotificationResponse {
    public String state;

    public FetchNotificationResponse.Data data;

    public static class Data {
        public Exceptions exceptions;
        @SerializedName("user_notifications")
        public List<UserNotifications> userNotifications;

        public static class Exceptions {
            @SerializedName("no_notification_found")
            public Boolean noNotificationFound;
        }

        public static class UserNotifications {
            @SerializedName("uid")
            public String uId;

            @SerializedName("notification_type")
            public String notificationType;

            @SerializedName("daily_task_payload")
            public Boolean dailyTaskPayload;

            @SerializedName("deposit_payload")
            public DepositPayload depositPayload;

            public class DepositPayload {
                @SerializedName("deposit_uid")
                public String depositUid;

                @SerializedName("bank_type")
                public String bankType;

                @SerializedName("account_holder_name")
                public String accountHolderName;

                @SerializedName("account_number")
                public String accountNumber;

                @SerializedName("original_invested_amount")
                public Integer originalInvestedAmount;

                @SerializedName("cut_percent")
                public Integer cutPercent;
                @SerializedName("investment_after_cut")
                public Integer investmentAfterCut;

                @SerializedName("deposit_status")
                public String depositStatus;

                @SerializedName("deposit_receipt")
                public String depositReceipt;

            }

            @SerializedName("withdraw_payload")
            public String withDrawPayload;

            @SerializedName("created_at")
            public String createdAt;

            @SerializedName("updated_at")
            public String updatedAt;
        }


    }


}
