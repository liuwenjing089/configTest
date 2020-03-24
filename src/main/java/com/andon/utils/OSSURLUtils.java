package com.andon.utils;

import java.net.URI;
import java.util.Map;

import com.andon.environment.Environment;

public class OSSURLUtils {
//	阿里云上传文件标示。
	public static final String OSS_BUCKEYNAME = "bucketname";
//	阿里云上传文件标示
	public static final String OSS_KEY = "key";
	private static String BEIJING_ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
	private static String BEIJING_INTERNAL_ENDPOINT = "http://oss-cn-beijing-internal.aliyuncs.com";
	private static String QINGDAO_ENDPOINT = "http://oss-cn-qingdao.aliyuncs.com";
	private static String QINGDAO_INTERNAL_ENDPOINT = "http://oss-cn-qingdao-internal.aliyuncs.com";
	private static String HANGZHOU_ENDPOINT = "http://oss-cn-hangzhou.aliyuncs.com";
	private static String HANGZHOU_INTERNAL_ENDPOINT = "http://oss-cn-hangzhou-internal.aliyuncs.com";
	private static String HONGKONG_ENDPOINT = "http://oss-cn-hongkong.aliyuncs.com";
	private static String HONGKONG_INTERNAL_ENDPOINT = "http://oss-cn-hongkong-internal.aliyuncs.com";

	private static final String INTERNAL_FLAG = "-internal";

	/** OSS服务的区域。 */
	public static final String BEIJING = "beijing";
	public static final String QINGDAO = "qingdao";
	public static final String HANGZHOU = "hangzhou";
	public static final String HONGKONG = "hongkong";
	
	private static URI CURRENT_INTERNET_ENDPOINT;
	private static URI CURRENT_INTERNAL_ENDPOINT;
	
	private static final Environment ENVIRONMENT=new OSSURLUtilsEnvironment();  
	
	static{
			@SuppressWarnings("unchecked")
			Map<String, URI> map=(Map<String, URI>) ENVIRONMENT.handle(null);
			
			CURRENT_INTERNET_ENDPOINT=map.get("endpoint");
			CURRENT_INTERNAL_ENDPOINT=map.get("internal_endpoint");
	}
	
	public static void main(String[] args) {
		System.out.println(generateInternalURL("image", "sdfsffds"));
		
	}
	/**
	 * 生成文件URL内网地址。
	 * 
	 * @param bucketName
	 * @param endpoint
	 * @param fileName
	 * @return
	 */
	public static String generateInternalURL(String bucketName, URI endpoint,
			String fileName) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://");
		sb.append(bucketName);
		sb.append(".");
		String point = endpoint.toString().replaceFirst("http://", "");
		if (point.indexOf(INTERNAL_FLAG) != -1) {
			sb.append(point);
		} else {
			int local = point.indexOf(".");
			String first = point.substring(0, local);
			String last = point.substring(local, point.length());
			sb.append(first);
			sb.append(INTERNAL_FLAG);
			sb.append(last);
		}
		sb.append("/");
		sb.append(fileName);
		return sb.toString();
	}

	/**
	 * 生成文件URL内网地址。
	 * 
	 * @param bucketName
	 * @param endpoint
	 * @param fileName
	 * @return
	 */
	public static String generateInternalURL(String bucketName, String fileName) {
		return generateInternalURL(bucketName, CURRENT_INTERNAL_ENDPOINT, fileName);
	}

	/**
	 * 生成文件URL外网地址。
	 * 
	 * @param bucketName
	 * @param endpoint
	 * @param fileName
	 * @return
	 */
	public static String generateInternetURL(String bucketName, URI endpoint,
			String fileName) {
		StringBuilder sb = new StringBuilder();
		sb.append("http://");
		sb.append(bucketName);
		sb.append(".");
		String point = endpoint.toString().replaceFirst("http://", "");
		if (point.indexOf(INTERNAL_FLAG) == -1) {
			sb.append(point);
		} else {
			sb.append(point.replace(INTERNAL_FLAG, ""));
		}
		sb.append("/");
		sb.append(fileName);
		return sb.toString();
	}

	/**
	 * 生成文件URL外网地址。
	 * 
	 * @param bucketName
	 * @param endpoint
	 * @param fileName
	 * @return
	 */
	public static String generateInternetURL(String bucketName, String fileName) {
		return generateInternetURL(bucketName,CURRENT_INTERNET_ENDPOINT, fileName);
	}
	
	/**
	 * 将存入数据库中个OSS的json串转换为外网地址。
	 * @param oss
	 * @return
	 */
	public static String convertOSSToInternetURL(String oss){
		Map<String, String> map = JsonUtil.jsonToMap(oss);
		return OSSURLUtils.generateInternetURL(map.get(OSS_BUCKEYNAME),map.get(OSS_KEY));
	}

	public static String getBeijingEndpoint() {
		return BEIJING_ENDPOINT;
	}

	public static void setBeijingEndpoint(String beijingEndpoint) {
		BEIJING_ENDPOINT = beijingEndpoint;
	}

	public static String getBeijingInternalEndpoint() {
		return BEIJING_INTERNAL_ENDPOINT;
	}

	public static void setBeijingInternalEndpoint(String beijingInternalEndpoint) {
		BEIJING_INTERNAL_ENDPOINT = beijingInternalEndpoint;
	}

	public static String getQingdaoEndpoint() {
		return QINGDAO_ENDPOINT;
	}

	public static void setQingdaoEndpoint(String qingdaoEndpoint) {
		QINGDAO_ENDPOINT = qingdaoEndpoint;
	}

	public static String getQingdaoInternalEndpoint() {
		return QINGDAO_INTERNAL_ENDPOINT;
	}

	public static void setQingdaoInternalEndpoint(String qingdaoInternalEndpoint) {
		QINGDAO_INTERNAL_ENDPOINT = qingdaoInternalEndpoint;
	}

	public static String getHangzhouEndpoint() {
		return HANGZHOU_ENDPOINT;
	}

	public static void setHangzhouEndpoint(String hangzhouEndpoint) {
		HANGZHOU_ENDPOINT = hangzhouEndpoint;
	}

	public static String getHangzhouInternalEndpoint() {
		return HANGZHOU_INTERNAL_ENDPOINT;
	}

	public static void setHangzhouInternalEndpoint(String hangzhouInternalEndpoint) {
		HANGZHOU_INTERNAL_ENDPOINT = hangzhouInternalEndpoint;
	}

	public static String getHongkongEndpoint() {
		return HONGKONG_ENDPOINT;
	}

	public static void setHongkongEndpoint(String hongkongEndpoint) {
		HONGKONG_ENDPOINT = hongkongEndpoint;
	}

	public static String getHongkongInternalEndpoint() {
		return HONGKONG_INTERNAL_ENDPOINT;
	}

	public static void setHongkongInternalEndpoint(String hongkongInternalEndpoint) {
		HONGKONG_INTERNAL_ENDPOINT = hongkongInternalEndpoint;
	}
}
