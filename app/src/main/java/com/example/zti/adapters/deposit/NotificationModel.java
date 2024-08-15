package com.example.zti.adapters.deposit;

public class NotificationModel {
    private String depositName,depositApproval,depositDate;

    public NotificationModel(String depositName, String depositApproval, String depositDate) {
        this.depositName = depositName;
        this.depositApproval = depositApproval;
        this.depositDate = depositDate;
    }

    public String getDepositName() {
        return depositName;
    }

    public String getDepositApproval() {
        return depositApproval;
    }

    public String getDepositDate() {
        return depositDate;
    }
}
