/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.service;

import edu.mum.cs545.bean.*;
import edu.mum.cs545.dao.CustomerDAO;
import edu.mum.cs545.dao.DAOFactory;
import edu.mum.cs545.model.Customer;

/**
 *
 * @author zeriet
 */
public class CustomerService {

    DAOFactory factory = DAOFactory.getFactory();
    CustomerDAO custDao = factory.getCustomerDAO();

    public void save(Customer customer) {

        custDao.beginTransaction();
        custDao.save(customer);
        custDao.commitTransaction();
    }
}
