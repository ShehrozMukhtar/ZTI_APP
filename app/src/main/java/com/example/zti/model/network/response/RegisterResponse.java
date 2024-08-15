package com.example.zti.model.network.response;
import com.google.gson.annotations.SerializedName;
public class RegisterResponse {
    public String state;
    public RegisterResponse.Data data;
    public static class Data {
        public Exceptions exceptions;
        public  User user;
        public static class Exceptions {
            @SerializedName("user_already_exist")
            public Boolean isUserAlreadyExist;
            @SerializedName("missing_param")
            public String missingParam;
        }
        public static class User {

            public String uid;
            @SerializedName("full_name")
            public String fullName;
            public String email;
            public String password;
            @SerializedName("uplink_referral_no")
            public String uplinkReferralNo;

            @SerializedName("referral_no")
            public String referralNo;
            public String avatar;
            @SerializedName(" is_verification_mail_Sent")
            public Boolean isVerificationMailSent;
        }
    }
}
