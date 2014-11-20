/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.dao;

/**
 *
 * @author fjoseph1313
 */
public abstract class DAOFactory {

    public static final Class FACTORY_CLASS = edu.mum.cs545.dao.HibernateDAOFactory.class;

    public static DAOFactory getFactory() {
        try {
            return (DAOFactory) FACTORY_CLASS.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't create factory");
        }
    }

    public abstract UserDAO getUserDAO();

    public abstract CustomerDAO getCustomerDAO();

    public abstract AccountDAO getAccountDAO();

    public abstract TransferDAO getTransferDAO();
}
