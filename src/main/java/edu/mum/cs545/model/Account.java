/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.cs545.model;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author ZWoldeab
 */
@Entity
public class Account {
    
    private Long accountNumber;
    private String accountType;
    private double balance;
    private int PIN;
    private int cardNumber;
    private Customer customer;
    private List<Transfer> transfers;
    private boolean status=true;
    
    

    @Id
    public Long getAccountNumber() {
        return accountNumber;
    }

    @ManyToOne
    @JoinColumn(name="cust_Id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    @OneToMany(mappedBy="account", targetEntity=Transfer.class, cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }
    
    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getPIN() {
        return PIN;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
