package com.adf.rest.request;

import java.time.LocalDate;

public class CreateRequest {
    private String account_holder_name;
    private LocalDate dob;
    private String account_type;
    private Double initial_deposit = 0.0;
    
    public String getAccount_holder_name() {
        return account_holder_name;
    }
    public void setAccount_holder_name(String account_holder_name) {
        this.account_holder_name = account_holder_name;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public String getAccount_type() {
        return account_type;
    }
    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }
    public Double getInitial_deposit() {
        return initial_deposit;
    }
    public void setInitial_deposit(Double initial_deposit) {
        this.initial_deposit = initial_deposit;
    }
    
}
