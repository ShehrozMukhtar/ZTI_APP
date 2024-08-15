package com.example.zti.model.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchUserDepositResponse {
    public String state;

    public  FetchUserDepositResponse.Data data;

    public static class Data {

        public  Exceptions exceptions;

        @SerializedName("user_deposits")
        public List<UserDeposits> userDeposits;

        public static class Exceptions{
            @SerializedName("missing_param")
            public String  missingParam;
            @SerializedName("no_deposit_transaction_found")
            public boolean noDepositTransactionFound;
        }

        public static class UserDeposits{
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

            @SerializedName("deposit_status")
            public String depositStatus;

            @SerializedName("deposit_receipt")
            public String depositReceipt;
        }

    }
}