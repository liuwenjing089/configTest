package com.andon.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateNumber {

	private static int NUMBER = 1;
	
	private static String DATE = "20190101";
	
	public static String createEquiqNumber(Integer numBySql){
		

		 String num = "";
		 
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

         String currentDate = dateFormat.format( new Date() );
		 
		 //如果数据库里当天的还没有报修记录编号
		 if(numBySql == null){
			
	         if(!DATE.equals(currentDate)){
	        	 NUMBER = 1;
	         }
	         
	         String numberStr = String.valueOf(NUMBER);
	         
	         if(numberStr.length() == 1){
	        	 numberStr = "0" + numberStr;
	         }
	         
	         num = currentDate + numberStr;
	         	       	         
	         
		 }else{
			 //如果已经存在编号
	         String numberStr = String.valueOf(numBySql);
	         
	         if(numberStr.length() == 1){
	        	 numberStr = "0" + String.valueOf(numBySql + 1);;
	         }
			 
	         num = currentDate + numberStr;
	         NUMBER = numBySql;
		 }
		 
		 DATE = currentDate;
		 NUMBER++;
		
		return num;
	}
	
}
