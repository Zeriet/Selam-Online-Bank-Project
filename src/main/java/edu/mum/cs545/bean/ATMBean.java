/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.cs545.bean;

import edu.mum.cs545.model.Account;
import edu.mum.cs545.service.ATMService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author zeriet
 */
@Named("atm")
@SessionScoped
public class ATMBean implements Serializable
{
    private Long acct;
    private double amount;
    private int pin;
    private String status;
    private ATMService atmServ = new ATMService();

    public Long getAcct() {
        return acct;
    }

    public void setAcct(Long acct) {
        this.acct = acct;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String makeWithdraw()
    {
        //pass an acct to the service, finds the object and make withdraw
        status = atmServ.withdrawHelper(acct, amount, pin);
        return "";
    }
    public String makeDeposit()
    {
        status = atmServ.depositHelper(acct, amount);
        
        return "faces/welcome";
    }
}
