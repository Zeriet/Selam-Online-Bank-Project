/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.DAO;

/**
 *
 * @author fjoseph1313
 */
public class HibernateDAOFactory extends DAOFactory
{
    public CustomerDAO getCustomerDAO()
    {
        return new HibernateCustomerDAO();
    }
    public AccountDAO getAccountDAO()
    {
        return new HibernateAccountDAO();
    }
    public HistoryDAO getHistoryDAO()
    {
        return new HibernateHistoryDAO();
    }
}
