package com.adf.rest.response;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.adf.rest.models.Transaction;

public class GetFrom {
    private Long accountNumber;
    private String accountHolderName;
    private Double balance;
    private LocalDate dob;
    private String accountType;
    private String fromDate;
    private String toDate;
    private List<Transaction> transactions = new ArrayList<>();
    
    public Double getBalance() {
        return balance;
    }
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public Long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getAccountName() {
        return accountHolderName;
    }
    public void setAccountName(String accountName) {
        this.accountHolderName = accountName;
    }
    public LocalDate getDob() {
        return dob;
    }
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public String getFromDate() {
        return fromDate;
    }
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
    public String getToDate() {
        return toDate;
    }
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    public List<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
