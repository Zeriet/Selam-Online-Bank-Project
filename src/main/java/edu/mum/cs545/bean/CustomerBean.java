/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.bean;

import edu.mum.cs545.dao.*;
import edu.mum.cs545.model.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author zeriet
 */
@Named
@SessionScoped

public class CustomerBean implements Serializable {

    DAOFactory factory = DAOFactory.getFactory();
    CustomerDAO custDao = factory.getCustomerDAO();

    private Customer customer = new Customer();

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String register() {
        System.out.println("register.....");
        //store data in DB
        custDao.save(customer);
        return "customerRgistrationSuccess";
    }

}
