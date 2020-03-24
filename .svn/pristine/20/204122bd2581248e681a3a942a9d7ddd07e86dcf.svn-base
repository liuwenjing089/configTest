package com.andon.utils;

import java.util.Properties;

import com.aliyun.openservices.oss.OSSClient;
import com.andon.environment.AbstractEnvironment;
public class OSSUtilsEnvironment extends AbstractEnvironment {
	/*
	 * private static final String BEIJING_ENDPOINT =
	 * "http://oss-cn-beijing.aliyuncs.com"; private static final String
	 * BEIJING_INTERNAL_ENDPOINT =
	 * "http://oss-cn-beijing-internal.aliyuncs.com"; private static final
	 * String QINGDAO_ENDPOINT = "http://oss-cn-qingdao.aliyuncs.com"; private
	 * static final String QINGDAO_INTERNAL_ENDPOINT =
	 * "http://oss-cn-qingdao-internal.aliyuncs.com"; private static final
	 * String HANGZHOU_ENDPOINT = "http://oss-cn-hangzhou.aliyuncs.com"; private
	 * static final String HANGZHOU_INTERNAL_ENDPOINT =
	 * "http://oss-cn-hangzhou-internal.aliyuncs.com"; private static final
	 * String HONGKONG_ENDPOINT = "http://oss-cn-hongkong.aliyuncs.com"; private
	 * static final String HONGKONG_INTERNAL_ENDPOINT =
	 * "http://oss-cn-hongkong-internal.aliyuncs.com";
	 */
	
	private static final String DEFAULT_ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
	private static final String ENDPOINT;

	private static final String KEY /* = "a14shUJD1uOYRrTG" */;
	private static final String SECRET /* = "MEbO1pNarBxXZapk6hGzl9RhfSPXwR" */;
	/** OSS Property配置文件的位置。 */
	private static final String OSS_PROPERTIES_PATH = "/properties/oss.properties";

	static {
		Properties props = PropertiesFileUtils.loadPropertiesFileToProperties(OSS_PROPERTIES_PATH);
		KEY = props.getProperty("key");
		SECRET = props.getProperty("secret");
		ENDPOINT = props.getProperty("endpoint", DEFAULT_ENDPOINT);
	}
	
	
	public static void main(String[] args) {
		System.out.println(KEY);
		System.out.println(SECRET);
		System.out.println(ENDPOINT);
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
		return new OSSClient(ENDPOINT, KEY, SECRET);
	}

	@Override
	public Object handleForTest(Object obj) {
		return new OSSClient(ENDPOINT, KEY, SECRET);
	}

	@Override
	public Object handleForPreproduction(Object obj) {
		return new OSSClient(ENDPOINT, KEY, SECRET);
	}

	@Override
	public Object handleForProduct(Object obj) {
		return new OSSClient(ENDPOINT, KEY, SECRET);
	}

	@Override
	public Object handleForDefault(Object obj) {
		return new OSSClient(ENDPOINT, KEY, SECRET);
	}

}
