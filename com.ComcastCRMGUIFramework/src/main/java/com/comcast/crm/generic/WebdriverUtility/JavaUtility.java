package com.comcast.crm.generic.WebdriverUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility 
{
	/**
	 * 
	 * @return
	 */
	public int toGetRandomNumber() 
	{
		Random rn = new Random();
		int genNum = rn.nextInt(5000);
		
		return genNum;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSystemDateInDDMMYYYY() 
	{
		Date date = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		String dateFormat = sdf.format(date);
		
		return dateFormat;
	}
	
	/**
	 * 
	 * @param days
	 * @return
	 */
	public String getRequiredDateInDDMMYYYY(int days) 
	{
		Date date = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		sdf.format(date);
		
		Calendar cal = sdf.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		String reqDate = sdf.format(cal.getTime());
		
		return reqDate;
	}
	
}
