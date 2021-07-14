package com.adf.rest.request;

import java.time.LocalDate;

public class CreateRequest {
    private String AccountHolderName;
    private LocalDate DateofBirth;
    private String AccountType;
    private Double InitialDeposit = 0.0;
    
    public Double getInitialDeposit() {
        return InitialDeposit;
    }
    public void setInitialDeposit(Double initialDeposit) {
        InitialDeposit = initialDeposit;
    }
    public String getAccountHolderName() {
        return AccountHolderName;
    }
    public void setAccountHolderName(String accountHolderName) {
        AccountHolderName = accountHolderName;
    }
    public LocalDate getDateofBirth() {
        return DateofBirth;
    }
    public void setDateofBirth(LocalDate dateofBirth) {
        DateofBirth = dateofBirth;
    }
    public String getAccountType() {
        return AccountType;
    }
    public void setAccountType(String accountType) {
        AccountType = accountType;
    }
    
}
