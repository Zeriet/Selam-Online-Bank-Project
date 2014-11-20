/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.service;

import edu.mum.cs545.dao.CustomerDAO;
import edu.mum.cs545.dao.DAOFactory;
import java.util.List;

/**
 *
 * @author fjoseph1313
 */
public class CustInfoService 
{
    DAOFactory factory = DAOFactory.getFactory();
    
    public List customerFinder()
    {
        CustomerDAO custDao = factory.getCustomerDAO();
        custDao.beginTransaction();
        List customers = custDao.findAll(0, 100);
        custDao.commitTransaction();
        return customers;
    }
}
