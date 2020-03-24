package com.andon.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;

public class PrintException {

	public static PrintStream printException() {
		//File file = new File("C:\\baoquanException.txt");
		Map<String, String> map = PropertiesFileUtils.loadPropertiesFile("properties/config.properties");
		String exceptionPath = map.get("PRINT_EXCEPTION_PATH");
		
  	    String [] uploadPathSplit =  exceptionPath.split("/");
  	    exceptionPath = "";
  	    //因为linux系统路径以'/'开头，循环时从第2个开始
  	    for(int i = 0; i< uploadPathSplit.length; i++){
  	       if(!uploadPathSplit[i].equals("")){
  	    	 exceptionPath += File.separator + uploadPathSplit[i];
  	       }    		   
  	    }
  	    exceptionPath = exceptionPath +File.separator; 
		
		String fileName = DateUtils.DateToString("yyyy-MM-dd",DateUtils.getNowDate()).replace("-","");
		fileName = fileName + ".txt";
		File file = new File(exceptionPath+fileName);
		PrintStream stream = null;
		try {

			if(!file.exists()){
				file.createNewFile();
			}
			
			//打印一条
			stream = new PrintStream(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stream;
   }
	
	public static PrintStream printExceptionAdditional () {
		File file = new File("G:\\baoquanException.txt");
		PrintStream stream = null;
		try {

			if(!file.exists()){
				file.createNewFile();
			}
			
			//追加打印
			stream = new PrintStream(new FileOutputStream(file,true),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return stream;
   }
}
