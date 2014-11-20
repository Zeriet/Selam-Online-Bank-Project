/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.cs545.model;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author ZWoldeab
 */
@Entity
public class Transfer 
{
    private Long transferNumber;
    private Date transferDate;
    private double transferAmount;
    private Account accountFrom;
    private Account accountTo;
    private String transferDesc;
    
    
    @Id
    @GeneratedValue
    public Long getTransferNumber() {
        return transferNumber;
    }
    
    @ManyToOne
    @JoinColumn(name="acct_from")
    public Account getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(Account accountFrom) {
        this.accountFrom = accountFrom;
    }
    @ManyToOne
    @JoinColumn(name="accountTo")
    public Account getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(Account accountTo) {
        this.accountTo = accountTo;
    }
    public void setTransferNumber(Long transferNumber) {
        this.transferNumber = transferNumber;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public double getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    public String getTransferDesc() {
        return transferDesc;
    }

    public void setTransferDesc(String transferDesc) {
        this.transferDesc = transferDesc;
    }
    
}
