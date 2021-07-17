package com.adf.rest.request;

import java.time.LocalDate;

public class CreateRequest {
    private String accountHolderName;
    private LocalDate dob;
    private String accountType;
    private Double initialDeposit = 0.0;
    
    
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public String getAccountHolderName() {
        return accountHolderName;
    }
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public Double getInitialDeposit() {
        return initialDeposit;
    }
    public void setInitialDeposit(Double initialDeposit) {
        this.initialDeposit = initialDeposit;
    }
    
    
}
