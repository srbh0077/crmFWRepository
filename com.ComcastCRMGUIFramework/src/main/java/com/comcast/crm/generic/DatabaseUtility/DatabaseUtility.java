package com.comcast.crm.generic.DatabaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

/** This Class consists of method related to Database
 * @author Amit
 */
public class DatabaseUtility 
{
	Connection conn = null;
	
	/**
	 * This method is used to connect any Database provided url, username, password
	 * @param url
	 * @param username
	 * @param password
	 * @throws Throwable
	 */
	public void getDBconnection(String url, String username, String password) throws Throwable
	{
		try 
		{
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(url, username, password);
		}
		catch (Exception e) 
		{}
	}
	
	/**
	 * This method is used to connect given Database 
	 * @throws Throwable
	 */
	public void getDBconnection() throws Throwable
	{
		try 
		{
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "root");
		}
		catch (Exception e) 
		{}
	}
	
	/**
	 * This method is used to disconnect Database
	 * @throws Throwable
	 */
	public void closeDBconnection() throws Throwable 
	{
		try
		{
			conn.close();
		}
		catch (Exception e) 
		{}
	}
	
	/**
	 * This method is used to execute Select query in Database provided query
	 * @param query
	 * @return
	 */
	public ResultSet toExecuteSelectQuery(String query) 
	{
		ResultSet res = null;
		try
		{
			Statement stat = conn.createStatement();
			res = stat.executeQuery(query);
		}
		catch (Exception e) 
		{}
		return res;
	}
	
	/**
	 * This method is used to execute Non-Select query in Database provided query
	 * @param query
	 * @return
	 */
	public int toExecuteNonSelectQuery(String query) 
	{
		int res = 0;
		try
		{
			Statement stat = conn.createStatement();
			res = stat.executeUpdate(query);
		}
		catch (Exception e) 
		{}
		return res;
	}
}