package com.andon.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.andon.environment.AbstractEnvironment;

public class OSSURLUtilsEnvironment extends AbstractEnvironment {
	private static final String DEFAULT_ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
	private static final String DEFAULT_INTERNAL_ENDPOINT = "http://oss-cn-beijing-internal.aliyuncs.com";
	private static final String ENDPOINT;
	private static final String INTERNAL_ENDPOINT;
	/** OSS Property配置文件的位置。 */
	private static final String OSS_PROPERTIES_PATH = "/properties/oss.properties";
	
	private static String BEIJING_ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
	private static String BEIJING_INTERNAL_ENDPOINT = "http://oss-cn-beijing-internal.aliyuncs.com";
	private static String QINGDAO_ENDPOINT = "http://oss-cn-qingdao.aliyuncs.com";
	private static String QINGDAO_INTERNAL_ENDPOINT = "http://oss-cn-qingdao-internal.aliyuncs.com";
	private static String HANGZHOU_ENDPOINT = "http://oss-cn-hangzhou.aliyuncs.com";
	private static String HANGZHOU_INTERNAL_ENDPOINT = "http://oss-cn-hangzhou-internal.aliyuncs.com";
	private static String HONGKONG_ENDPOINT = "http://oss-cn-hongkong.aliyuncs.com";
	private static String HONGKONG_INTERNAL_ENDPOINT = "http://oss-cn-hongkong-internal.aliyuncs.com";
	
	private static final String BEIJING = "beijing";
	private static final String QINGDAO = "qingdao";
	private static final String HANGZHOU = "hangzhou";
	private static final String HONGKONG = "hongkong";
	
	static {
		Properties props = PropertiesFileUtils.loadPropertiesFileToProperties(OSS_PROPERTIES_PATH);
		//获得位置。
		String location= props.getProperty("location", BEIJING);
		switch (location) {
		case BEIJING:
			ENDPOINT=BEIJING_ENDPOINT;
			INTERNAL_ENDPOINT=BEIJING_INTERNAL_ENDPOINT;
			break;
		case QINGDAO:
			ENDPOINT=QINGDAO_ENDPOINT;
			INTERNAL_ENDPOINT=QINGDAO_INTERNAL_ENDPOINT;
			break;
		case HANGZHOU:
			ENDPOINT=HANGZHOU_ENDPOINT;
			INTERNAL_ENDPOINT=HANGZHOU_INTERNAL_ENDPOINT;
			break;
		case HONGKONG:
			ENDPOINT=HONGKONG_ENDPOINT;
			INTERNAL_ENDPOINT=HONGKONG_INTERNAL_ENDPOINT;
			break;
		default:
			ENDPOINT=DEFAULT_ENDPOINT;
			INTERNAL_ENDPOINT=DEFAULT_INTERNAL_ENDPOINT;
			break;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(ENDPOINT);
		System.out.println(INTERNAL_ENDPOINT);
	}
	
	/**
	 * 加载OSS配置文件
	 * 
	 * @return
	 */
//	private static Properties loadOSSProperties() {
//		final String path = OSS_PROPERTIES_PATH;
//		Properties props = new Properties();
//		InputStream in = OSSUtils.class.getResourceAsStream(path);
//		try {
//			props.load(in);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return props;
//	}

	@Override
	public Object handleForDevelop(Object obj) {
		return getResult();
	}

	@Override
	public Object handleForTest(Object obj) {
		return getResult();
	}

	@Override
	public Object handleForPreproduction(Object obj) {
		return getResult();
	}

	@Override
	public Object handleForProduct(Object obj) {
		return getResult();
	}

	@Override
	public Object handleForDefault(Object obj) {
		return getResult();
	}
	
	
	private static Map<String,URI>  getResult(){
		Map<String,URI> result=new HashMap<String, URI>();
		try {
			result.put("endpoint", new URI(ENDPOINT));
			result.put("internal_endpoint", new URI(INTERNAL_ENDPOINT));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
