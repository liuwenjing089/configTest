package com.andon.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.andon.utils.DateUtils;

public class Ceshi {
    @Test
	public void dayin() {
		String path = "E:\\wow.txt";
		File file = new File(path);

		//引入输出流
		FileWriter writer = null;
		try{
			//如果文件不存在，则自动生成文件；
			if(!file.exists()){
				file.createNewFile();
			}
			
			StringBuilder stringBuilder = new StringBuilder();//使用长度可变的字符串对象；
			stringBuilder.append("文件内容");//追加文件内容
	
			writer = new FileWriter(file, true);
			writer.append(stringBuilder);
            writer.flush();
		}catch(Exception e){
			e.printStackTrace();
		}

	}
    
    @Test
    public void ca(){
    	String num = "4.000000000000000";
    	
    	BigDecimal a = new BigDecimal(num.replace("%","").replace("(","").replace(")",""));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        String datestr = df.format(new Date());
    	System.out.println(datestr);
    }
    
    
    @Test
    public void cv(){
      String a= "2010-07-01";
      Pattern p = Pattern.compile("^\\d{4}\\-\\d{1,2}\\-\\d{1,2}$");
      Matcher m = p.matcher(a);
      
      if(m.matches()){
    	  System.out.println(1);
      }else{
    	  System.out.println(2);
      }
      
      Date v = DateUtils.stringToDate(a);
      String c= DateUtils.dateToString(v);
      System.out.println(c);
    }
}
