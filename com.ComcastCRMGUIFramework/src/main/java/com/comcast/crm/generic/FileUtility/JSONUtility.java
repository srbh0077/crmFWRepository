package com.comcast.crm.generic.FileUtility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/** This Class consists of method related to JSON File
 * @author Amit
 */
public class JSONUtility 
{
	/**
	 * This method is used to fetch common data from JSON File provided key
	 * @param key
	 * @return
	 * @throws ParseException 
	 * @throws IOException
	 */
	public String getDataFromJSONFile(String key) throws IOException, ParseException
	{
		FileReader fr = new FileReader("./configurationData/jsonData.json");
		JSONParser jsp = new JSONParser();
		Object obj = jsp.parse(fr);
		JSONObject jsonObj = (JSONObject) obj;
		String value = (String) jsonObj.get(key);	//.toString()
		
		return value;
	}
}
