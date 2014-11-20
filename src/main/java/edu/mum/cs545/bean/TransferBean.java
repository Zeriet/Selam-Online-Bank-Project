/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.bean;

import edu.mum.cs545.model.Transfer;
import edu.mum.cs545.service.TransferService;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author fjoseph1313
 */
@Named("transfer")
@SessionScoped
public class TransferBean implements Serializable
{
    private Long acctFrom;
    private Long acctTo;
    private double amount;
    private String description;
    private TransferService trans = new TransferService();
    private String output;

    public Long getAcctFrom() {
        return acctFrom;
    }

    public void setAcctFrom(Long acctFrom) {
        this.acctFrom = acctFrom;
    }

    public Long getAcctTo() {
        return acctTo;
    }

    public void setAcctTo(Long acctTo) {
        this.acctTo = acctTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
    
    public String makeTransfer()
    {
        //create Transfer object and populate all the fields
        output = trans.transferHelper(acctFrom, acctTo, amount, description);
        return "faces/transfer";
    }
}
