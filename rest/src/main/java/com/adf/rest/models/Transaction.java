package com.adf.rest.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Transaction {
    @ManyToOne
    private BankAccount account;
    @Id
    private Long TransactionId;
    private Double TransactionAmount;
    private String TransactionType;
    @CreationTimestamp
    private LocalDateTime TransactionDate;
    private String TransactionStatus;
    @CreationTimestamp
    private LocalDateTime CreatedAt;
    @UpdateTimestamp
    private LocalDateTime UpdatedAt;
    
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
    public Double getTransactionAmount() {
        return TransactionAmount;
    }
    public void setTransactionAmount(Double transactionAmount) {
        TransactionAmount = transactionAmount;
    }
    public String getTransactionType() {
        return TransactionType;
    }
    public void setTransactionType(String transactionType) {
        TransactionType = transactionType;
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
