package com.autodest.genericUtils;

import java.util.Date;
import java.util.Random;

/**
 *  This class contains java specific utilities , which is used to get the ranDom Data * date
 * @author Deepak
 *
 */
public class JavaUtility {
	
	/**
	 *   used to generate the random data with in 1000 limit
	 * @return random data in the from of String 
	 */
	public  String getRanDomData() {
		Random random = new Random();
		int ranInt = random.nextInt(1000);
		return ""+ranInt;
	}
	
	/**
	 * used to get the current system Data
	 * @return
	 */
	public  String getCurrentSystemDate() {
          Date date = new Date();
             String currentDate = date.toString();
		return currentDate;
	}
	
	

}
