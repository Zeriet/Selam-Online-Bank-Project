/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.service;

import edu.mum.cs545.dao.AccountDAO;
import edu.mum.cs545.dao.DAOFactory;
import edu.mum.cs545.dao.TransferDAO;
import edu.mum.cs545.model.Account;
import edu.mum.cs545.model.Transfer;
import java.util.Date;

/**
 *
 * @author fjoseph1313
 */
public class TransferService {

    DAOFactory factory = DAOFactory.getFactory();

    public String transferHelper(Long acct1, Long acct2, double amt, String desc)
    {
        AccountDAO accDao = factory.getAccountDAO();
        TransferDAO transDao = factory.getTransferDAO();
        transDao.beginTransaction();
        Account fetchedAcct1 = (Account) accDao.findByPrimaryKey(acct1);
        System.out.println("From Details: " + fetchedAcct1.getAccountType() + " " + fetchedAcct1.getBalance());

        if (fetchedAcct1.getBalance() > amt)
        {
            //transaction can be done
            Account fetchedAcct2 = (Account) accDao.findByPrimaryKey(acct2);
            System.out.println("To Details: " + fetchedAcct2.getAccountType() + " " + fetchedAcct2.getBalance());
            fetchedAcct1.setBalance(fetchedAcct1.getBalance() - amt);
            fetchedAcct2.setBalance(fetchedAcct2.getBalance() + amt);

            Transfer newTransfer = new Transfer();
            newTransfer.setAccountFrom(fetchedAcct1);
            newTransfer.setTransferAmount(amt);
            newTransfer.setAccountTo(fetchedAcct2);
            newTransfer.setTransferDate(new Date());
            newTransfer.setTransferDesc(desc);
            
            accDao.save(fetchedAcct1);
            accDao.save(fetchedAcct2);
            transDao.save(newTransfer);
            transDao.commitTransaction();
            
            return "success";
        }
        else
        {
            return "failure";
        }
    }
}
