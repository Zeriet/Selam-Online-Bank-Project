/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.mum.cs545.service;

import edu.mum.cs545.dao.AccountDAO;
import edu.mum.cs545.dao.DAOFactory;
import edu.mum.cs545.model.Account;

/**
 *
 * @author zeriet
 */
public class ATMService
{
    DAOFactory factory = DAOFactory.getFactory();
    
    public String depositHelper(Long id, double amt)
    {
        //fetch in database to get account
        AccountDAO accDao = factory.getAccountDAO();
        accDao.beginTransaction();
        Account fetchedAcct = (Account)accDao.findByPrimaryKey(id);
        fetchedAcct.setBalance(fetchedAcct.getBalance()+amt);
        accDao.save(fetchedAcct);//save the retrieved and updated object
        accDao.commitTransaction();
        return "Deposit was Successfully!";
    }
    
    public String withdrawHelper(Long id, double amt, int pin)
    {
        AccountDAO accDao = factory.getAccountDAO();
        accDao.beginTransaction();
        Account fetchedAcct = (Account)accDao.findByPrimaryKey(id);
        //check pin correct
        String message = "";
        if(pin == fetchedAcct.getPIN())
        {
            //can meke withdrawal but first check balance
            if(amt < fetchedAcct.getBalance())
            {
                //allowed to deposit
                fetchedAcct.setBalance(fetchedAcct.getBalance()-amt);
                message = "Success, Withdrawal Made";
                accDao.commitTransaction();
            }
            else
            {
                message = "Not Enough Balance";
            }
        }
        else
        {
            message = "Incorect Pin";
        }
        return message;
    }
    
    
}
