package com.example.zti.model.network.response;

import com.google.gson.annotations.SerializedName;

public class CBalanceResponse {
    public String state;

    public CBalanceResponse.Data data;

    public static  class Data{
        public Exceptions exceptions;

        public static class Exceptions{
            @SerializedName("missing_param")
            public String missingParam;
        }

        @SerializedName("user_balance")
        public UserBalance userBalance;

        public static class  UserBalance {
            @SerializedName("daily_task_profit")
            public Integer dailyTaskProfit;
            @SerializedName("referral_profit")
            public Integer referralProfit;
        }

    }
}
