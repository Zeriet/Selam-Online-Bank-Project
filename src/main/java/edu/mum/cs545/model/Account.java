/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.cs545.model;

import java.util.*;
import javax.persistence.*;

/**
 *
 * @author ZWoldeab
 */
@Entity
public class Account {
    
    private Long accountNumber;
    private String accountType ="Checking";
    private double balance;
    private int PIN;
    private int cardNumber;
    private Customer customer;
    private List<Transfer> transfers = new Vector<Transfer>();
    private List<Transfer> transfersTo = new Vector<Transfer>();


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
    
    @OneToMany(mappedBy="accountFrom", targetEntity=Transfer.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<Transfer> transfers) {
        this.transfers = transfers;
    }

    @OneToMany(mappedBy="accountTo", targetEntity=Transfer.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    public List<Transfer> getTransfersTo() {
        return transfersTo;
    }

    public void setTransfersTo(List<Transfer> transfersTo) {
        this.transfersTo = transfersTo;
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
    
}
