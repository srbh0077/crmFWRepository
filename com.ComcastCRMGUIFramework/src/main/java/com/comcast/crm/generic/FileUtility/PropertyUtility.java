package com.comcast.crm.generic.FileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** This Class consists of method related to Property File
 * @author Amit
 */
public class PropertyUtility 
{
	/**
	 * This methhod is used to Fetch data from Property File provided key
	 * @param key
	 * @return
	 * @throws IOException
	 */
	public String getDataFromPropertyFile(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream("./configurationData/commonData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String value = prop.getProperty(key);
		
		return value;
	}
}
