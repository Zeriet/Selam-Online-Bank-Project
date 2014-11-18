/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.service;

import edu.mum.cs545.bean.*;
import edu.mum.cs545.dao.AccountDAO;
import edu.mum.cs545.dao.DAOFactory;
import edu.mum.cs545.model.Account;

/**
 *
 * @author zeriet
 */
public class AccountService {
    DAOFactory factory = DAOFactory.getFactory();
    AccountDAO accDao = factory.getAccountDAO();

    public Long accountNumber() {

        return null;

    }

    public int generatePIN() {        
       //generate a 4 digit integer 1000 <10000
       return (int)(Math.random()*9000)+1000;

    }
    
    public void save(Account account) {

        accDao.beginTransaction();
        accDao.save(account);
        accDao.commitTransaction();
    }

}
