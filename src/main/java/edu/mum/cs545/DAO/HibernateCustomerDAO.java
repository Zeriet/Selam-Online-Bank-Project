/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.DAO;

import edu.mum.cs545.model.Customer;

/**
 *
 * @author fjoseph1313
 */
public class HibernateCustomerDAO extends HibernateDAO<Customer, Long> implements CustomerDAO
{
    public HibernateCustomerDAO()
	{
		super(Customer.class);
	}
}
