package com.adf.rest.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Transaction {
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="AccountNumber")
    private BankAccount account;
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long TransactionId;
    private Double OldBalance;
    private Double NewBalance;
    @CreationTimestamp
    private LocalDateTime TransactionDate;
    private String TransactionStatus;
    @CreationTimestamp
    private LocalDateTime CreatedAt;
    @UpdateTimestamp
    private LocalDateTime UpdatedAt;
    private Double amount;
    private String type;
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
    public Double getOldBalance() {
        return OldBalance;
    }
    public void setOldBalance(Double oldBalance) {
        OldBalance = oldBalance;
    }
    public Double getNewBalance() {
        return NewBalance;
    }
    public void setNewBalance(Double newBalance) {
        NewBalance = newBalance;
    }
    public BankAccount getAccount() {
        return account;
    }
    public void setAccount(BankAccount account) {
        this.account = account;
    }
    public Long getTransactionId() {
        return TransactionId;
    }
    public void setTransactionId(Long transactionId) {
        TransactionId = transactionId;
    }
    
    public LocalDateTime getTransactionDate() {
        return TransactionDate;
    }
    public void setTransactionDate(LocalDateTime transactionDate) {
        TransactionDate = transactionDate;
    }
    public String getTransactionStatus() {
        return TransactionStatus;
    }
    public void setTransactionStatus(String transactionStatus) {
        TransactionStatus = transactionStatus;
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
