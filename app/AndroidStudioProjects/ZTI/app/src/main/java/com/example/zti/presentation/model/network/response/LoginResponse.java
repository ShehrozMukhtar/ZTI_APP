package com.example.zti.presentation.model.network.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    public String state;
    public LoginResponse.Data data ;

    public static class Data {

        public Exceptions exceptions;
        public User user;
        public static class Exceptions{
            @SerializedName("invalid_value_of_param")
            public String  invalidValueOfParam;
            @SerializedName("no_user_found")
            public String noUserFound;
            @SerializedName("invalid_password")
            public String invalidPassword;
        }


        public static class User {

            public String uid;
            @SerializedName("full_name")
            public String fullName;
            public String email;

            @SerializedName("uplink_referral_no")
            public String upLinkReferralNo;

            @SerializedName("referral_no")
            public String referralNo;
            public String avatar;

            @SerializedName("is_account_verified")
            public Boolean isAccountVerified;

            @SerializedName(" is_verification_mail_Sent")
            public Boolean isVerificationMailSent;
        }
    }

}
