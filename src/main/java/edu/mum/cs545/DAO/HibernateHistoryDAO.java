/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.cs545.DAO;

import edu.mum.cs545.model.History;

/**
 *
 * @author fjoseph1313
 */
public class HibernateHistoryDAO extends HibernateDAO<History, Long> implements HistoryDAO 
{
    public HibernateHistoryDAO()
    {
        super(History.class);
    }
}
