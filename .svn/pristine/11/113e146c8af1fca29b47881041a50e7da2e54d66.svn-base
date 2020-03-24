package com.andon.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;



/**
 * Properties文件工具类。
 * 
 * @author TM TSE
 * 
 */
public class PropertiesFileUtils {

	/**
	 * 从类路径中读取properties文件，并将结果转换为map。
	 * 注意：map为LinkedHashMap，有序。
	 * @param classPath  properties文件的类路径，例如：/aa/bb
	 * @return
	 */
	
	//TODO 此方法需要更改，无需依赖spring方法。
	public static Map<String, String> loadPropertiesFile(String classPath) {
		// 创建结果。
		Map<String, String> result = new LinkedHashMap<String, String>();
		try {
			// 加载properties文件。
			Properties pros = PropertiesLoaderUtils
					.loadProperties(new ClassPathResource(classPath));
			// 将Properties对象转换成Map对象。
			Set<String> keys = pros.stringPropertyNames();
			for (String key : keys) {
				result.put(StringUtils.trim(key),
						StringUtils.trim(pros.getProperty(key)));
			}

		} catch (IOException e) {
			System.err.println("properties文件加载失败:" + classPath);
			e.printStackTrace();
		}

		return result;
	}
	
	
	
	/**
	 * 从类路径中读取properties文件，并将结果转换为Properties。
	 * @param classPath properties文件的类路径，例如：/aa/bb
	 * @return
	 */
	public static Properties loadPropertiesFileToProperties(String classPath){
		Properties props = new Properties();
		InputStream in = PropertiesFileUtils.class.getResourceAsStream(classPath);
		try {
			props.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return props;
	}
	
	
}
