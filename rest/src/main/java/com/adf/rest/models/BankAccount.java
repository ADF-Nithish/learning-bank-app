package com.adf.rest.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue
    private Long AccountNumber;
    private String AccountHolderName;
    private LocalDate DateofBirth;
    private String AccountType;
    private Double Balance = 0.0;
    private Double TransactionFee = 0.0;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "account")
    private List<Transaction> transactions = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime CreatedAt;
    @UpdateTimestamp
    private LocalDateTime UpdatedAt;

    public List<Transaction> getTransaction() {
        return transactions;
    }
    public void setTransaction(List<Transaction> transaction) {
        this.transactions = transaction;
    }
    public Double getBalance() {
        return Balance;
    }
    public void setBalance(Double balance) {
        Balance = balance;
    }
    public Long getAccountNumber() {
        return AccountNumber;
    }
    public void setAccountNumber(Long accountNumber) {
        AccountNumber = accountNumber;
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
    public Double getTransactionFee() {
        return TransactionFee;
    }
    public void setTransactionFee(Double transactionFee) {
        TransactionFee = transactionFee;
    }
    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        CreatedAt = createdAt;
    }
    public LocalDateTime getUpdatedAt() {
        return UpdatedAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        UpdatedAt = updatedAt;
    }
    
}
