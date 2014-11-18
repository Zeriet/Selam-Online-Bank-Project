package edu.mum.cs545.dao;

import edu.mum.cs545.model.*;
import java.util.Date;

public class BankingManager
{
	public static void main(String[] Args)
	{
		DAOFactory factory = DAOFactory.getFactory();
		CustomerDAO custDao = factory.getCustomerDAO();
		AccountDAO acctDao = factory.getAccountDAO();
                TransferDAO histDao = factory.getHistoryDAO();
		
		custDao.beginTransaction();
		
		Customer cust1 = new Customer();
		cust1.setFirstName("Francis");
		cust1.setLastName("Josep");
		cust1.setEmail("cesc.joseph@gmail.com");
		cust1.setZipCode(52557);
		cust1.setState("Iowa");
                cust1.setGender("M");
		
		Account acct = new Account();
		acct.setAccountNumber(new Long(147258));
		acct.setAccountType("CheckingAccount");
		acct.setBalance(10000.00);
		acct.setPIN(147852);
		acct.setCardNumber(137);
		//cust1.getAccounts().add(acct);
		acct.setCustomer(cust1);
                
                Transfer transfer = new Transfer();
                transfer.setTransferDate(new Date());
                transfer.setTransferAmount(5000);
                transfer.setAccountTo(147852);
                transfer.setAccount(acct);
		
		custDao.save(cust1);
                System.out.println(".........KKKKKKKKKKKKKKKKKKKK......"+cust1.getCustomerId()+cust1.getLastName());
		acctDao.save(acct);
                histDao.save(transfer);
                
                
		
		custDao.commitTransaction();
	}
}