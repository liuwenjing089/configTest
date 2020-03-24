package com.andon.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import net.sf.json.JSONObject;

import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.OSSException;
import com.aliyun.openservices.oss.model.ListObjectsRequest;
import com.aliyun.openservices.oss.model.OSSObject;
import com.aliyun.openservices.oss.model.OSSObjectSummary;
import com.aliyun.openservices.oss.model.ObjectListing;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.aliyun.openservices.oss.model.PutObjectResult;
import com.andon.environment.Environment;

public class OSSUtils {

	// 生产环境时候替换实际的值
//	private static final String KEY /* = "a14shUJD1uOYRrTG" */;
//	private static final String SECRET /* = "MEbO1pNarBxXZapk6hGzl9RhfSPXwR" */;

	private static final String BEIJING_ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";
	private static final String BEIJING_INTERNAL_ENDPOINT = "http://oss-cn-beijing-internal.aliyuncs.com";
	private static final String QINGDAO_ENDPOINT = "http://oss-cn-qingdao.aliyuncs.com";
	private static final String QINGDAO_INTERNAL_ENDPOINT = "http://oss-cn-qingdao-internal.aliyuncs.com";
	private static final String HANGZHOU_ENDPOINT = "http://oss-cn-hangzhou.aliyuncs.com";
	private static final String HANGZHOU_INTERNAL_ENDPOINT = "http://oss-cn-hangzhou-internal.aliyuncs.com";
	private static final String HONGKONG_ENDPOINT = "http://oss-cn-hongkong.aliyuncs.com";
	private static final String HONGKONG_INTERNAL_ENDPOINT = "http://oss-cn-hongkong-internal.aliyuncs.com";

	/** OSS Property配置文件的位置。 */
//	private static final String OSS_PROPERTIES_PATH = "/properties/oss.properties";
	private static final String BUCKETNAME_PROPERTIES_PATH = "/properties/bucketName.properties";
//	private static final String INTERNAL_FLAG = "-internal";

	/** OSS服务的区域。 */
	public static final String BEIJING = "beijing";
	public static final String QINGDAO = "qingdao";
	public static final String HANGZHOU = "hangzhou";
	public static final String HONGKONG = "hongkong";
	/** 内网 */
//	private static final String INTERNAL = "internal";
	/** 外网 */
//	private static final String INTERNET = "internet";
	/** 短地址占位符 */
	public static final Map<String, String> SHORT_URL_PLACEHOLDER = new HashMap<String, String>();
	private static OSSClient client;
	
	private static final Environment ENVIRONMENT =new OSSUtilsEnvironment();

	static {
		// 加载OSS property文件，读取OSS参数。
		// Date start=new Date();
//		Properties props = loadOSSProperties();
		// Date end=new Date();
		// System.out.println(end.getTime()-start.getTime());
//		KEY = props.getProperty("key");
//		SECRET = props.getProperty("secret");
		
		
		// 加载bucketName property文件，读取bucketname。
//		Properties bucketNames = loadBucketNameProperties();
//		Enumeration<Object> keys = bucketNames.keys();
//		// 将bucketName 写入短地址映射中。
//		while (keys.hasMoreElements()) {
//			String key = (String) keys.nextElement();
//			// 拼接占位符
//			SHORT_URL_PLACEHOLDER.put(nest(key), bucketNames.getProperty(key));
//		}
		
		
		// 将地域endpoint添加进短地址映射中。
//		addEndpointToplaceholder();

		// 默认是北京外网节点。TODO 此处需要测试。
		client = (OSSClient) ENVIRONMENT.handle(null);
	}

	/**
	 * 将endpoint添加进短地址映射中。
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private static void addEndpointToplaceholder() {
		SHORT_URL_PLACEHOLDER.put(nest("e1"),
				BEIJING_ENDPOINT.replaceFirst("http://", ""));
		SHORT_URL_PLACEHOLDER.put(nest("e2"),
				BEIJING_INTERNAL_ENDPOINT.replaceFirst("http://", ""));
		SHORT_URL_PLACEHOLDER.put(nest("e3"),
				QINGDAO_ENDPOINT.replaceFirst("http://", ""));
		SHORT_URL_PLACEHOLDER.put(nest("e4"),
				QINGDAO_INTERNAL_ENDPOINT.replaceFirst("http://", ""));
		SHORT_URL_PLACEHOLDER.put(nest("e5"),
				HANGZHOU_ENDPOINT.replaceFirst("http://", ""));
		SHORT_URL_PLACEHOLDER.put(nest("e6"),
				HANGZHOU_INTERNAL_ENDPOINT.replaceFirst("http://", ""));
		SHORT_URL_PLACEHOLDER.put(nest("e7"),
				HONGKONG_ENDPOINT.replaceFirst("http://", ""));
		SHORT_URL_PLACEHOLDER.put(nest("e8"),
				HONGKONG_INTERNAL_ENDPOINT.replaceFirst("http://", ""));
	}

	public static void main(String[] args) {
		// changeOSSClient(BEIJING, INTERNAL);
		// System.out.println(client.generatePresignedUrl("be-elegant-test",
		// KEY,
		// new Date()));
		// changeOSSClient(BEIJING, INTERNAL);
		// System.out.println(client.generatePresignedUrl("be-elegant-test",
		// KEY,
		// new Date()));
		//
		// String aaa=client.generatePresignedUrl("be-elegant-test", KEY,
		// new Date()).toString();
		//
		// System.out.println(generateShortURLPlaceholder());
		// String url=changeToShortURL(aaa);
		// System.out.println(url);
		// System.out.println(changeToOriginalURL(url));
		// System.out.println(aaa.equals(changeToOriginalURL(url)));

		// File file=new File("C:\\image resizer\\6ce39e9e78517f000033.jpg");
		// try {
		// uploadFile(file, MIME.JPG, "be-elegant-test");
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }

		// File aa=new File("C:\\abcdef.txt");
		// try {
		// UploadFileInfo
		// info=OSSUtils.uploadFile(aa,MIME.TXT,"be-elegant-test");
		// System.out.println();
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// }
		String bucketName="weitucar-excel";
		String key="consumeOrderCount/2015/10/2015-10-14.xlsx";
		
//		List<ObjectInfo> list=getObjectAndFolder(bucketName, prefix);
//		System.out.println(list);
		OSSObject obj=getObject(bucketName, key);
		System.out.println(obj==null);
	}

	/**
	 * 添加嵌套符号。
	 * 
	 * @param input
	 * @return
	 */
	private static String nest(String input) {
		if (input != null) {
			return "<" + input + ">";
		} else {
			throw new IllegalArgumentException("input isn't null");
		}
	}

	/**
	 * 改变OSS存储地域，和内外网。
	 * 
	 * @param region
	 *            地域
	 * @param flag
	 *            内网、外网
	 */
//	private static void changeOSSClient(String region, String flag) {
//		if (flag == null || region == null) {
//			throw new IllegalArgumentException("region and flag is not null");
//		}
//		if (BEIJING.equals(region)) {
//			if (INTERNAL.equals(flag))
//				client = new OSSClient(BEIJING_INTERNAL_ENDPOINT, KEY, SECRET);
//			else if (INTERNET.equals(flag)) {
//				client = new OSSClient(BEIJING_ENDPOINT, KEY, SECRET);
//			} else {
//				throw new IllegalArgumentException("flag must be " + INTERNAL
//						+ " or " + INTERNET);
//			}
//		} else if (HANGZHOU.equals(region)) {
//			if (INTERNAL.equals(flag))
//				client = new OSSClient(HANGZHOU_INTERNAL_ENDPOINT, KEY, SECRET);
//			else if (INTERNET.equals(flag)) {
//				client = new OSSClient(HANGZHOU_ENDPOINT, KEY, SECRET);
//			} else {
//				throw new IllegalArgumentException("flag must be " + INTERNAL
//						+ " or " + INTERNET);
//			}
//		} else if (QINGDAO.equals(flag)) {
//			if (INTERNAL.equals(flag))
//				client = new OSSClient(QINGDAO_INTERNAL_ENDPOINT, KEY, SECRET);
//			else if (INTERNET.equals(flag)) {
//				client = new OSSClient(QINGDAO_ENDPOINT, KEY, SECRET);
//			} else {
//				throw new IllegalArgumentException("flag must be " + INTERNAL
//						+ " or " + INTERNET);
//			}
//		} else if (HONGKONG.equals(flag)) {
//			if (INTERNAL.equals(flag))
//				client = new OSSClient(HONGKONG_INTERNAL_ENDPOINT, KEY, SECRET);
//			else if (INTERNET.equals(flag)) {
//				client = new OSSClient(HONGKONG_ENDPOINT, KEY, SECRET);
//			} else {
//				throw new IllegalArgumentException("flag must be " + INTERNAL
//						+ " or " + INTERNET);
//			}
//		} else {
//			throw new IllegalArgumentException("region must be one of "
//					+ BEIJING + "," + HANGZHOU + "," + QINGDAO + "," + HONGKONG);
//		}
//	}

	/**
	 * 上传文件。
	 * 
	 * @param file
	 * @param contentType
	 * @param bucketName
	 * @return
	 * @throws FileNotFoundException
	 * @throws FileUploadFailedException
	 */
	public static UploadFileInfo uploadFile(File file, String contentType,
			String bucketName) throws FileNotFoundException,
			FileUploadFailedException {

		// if (file == null || !file.canRead()) {
		// throw new IllegalArgumentException(
		// "The file is not null and must be readable!");
		// }
		// if (contentType == null) {
		// throw new IllegalArgumentException("contentType is not null!");
		// }
		// if (bucketName == null) {
		// throw new IllegalArgumentException("bucketName is not null!");
		// }
		// if (!com.aliyun.openservices.oss.internal.OSSUtils
		// .validateBucketName(bucketName)) {
		// throw new IllegalArgumentException("bucketName is not validate!");
		// }
		//
		// // 创建上传Object的Metadata
		// ObjectMetadata meta = new ObjectMetadata();
		// // 必须设置ContentLength
		// meta.setContentLength(file.length());
		//
		// meta.setContentType(contentType);
		//
		// // 输入流不用关闭，执行完putObject方法后自动关闭。
		// InputStream content = new FileInputStream(file);
		//
		// String name = file.getName();
		//
		// // 上传Object.
		// PutObjectResult result = client.putObject(bucketName, name, content,
		// meta);
		//
		// UploadFileInfo info = new UploadFileInfo();
		// info.setFileName(name);
		// info.setFileMD5(result.getETag());
		// info.setFileInternalURL(generateInternalURL(bucketName,
		// client.getEndpoint(), name));
		// info.setFileInternetURL(generateInternetURL(bucketName,
		// client.getEndpoint(), name));
		// return info;

		return uploadFile(file, null, contentType, bucketName, null);
	}

	/**
	 * 上传文件。
	 * 
	 * @param file
	 * @param path
	 *            文件上传后的路径，必须符合URL路径规则（例如： "aa/", "aa/bb/"）
	 * @param contentType
	 * @param bucketName
	 * @param userMetadata
	 * @return
	 * @throws FileNotFoundException
	 * @throws FileUploadFailedException
	 */
	public static UploadFileInfo uploadFile(File file, String path,
			String contentType, String bucketName,
			Map<String, String> userMetadata) throws FileNotFoundException,
			FileUploadFailedException {

		return uploadFile(file, path, null, contentType, bucketName,
				userMetadata);
	}

	/**
	 * 上传文件。
	 * 
	 * @param file
	 * @param path
	 *            文件上传后的路径，必须符合URL路径规则（例如： "aa/", "aa/bb/"）
	 * @param filename
	 *            文件名称。
	 * @param contentType
	 * @param bucketName
	 * @param userMetadata <可为Null>
	 * @return
	 * @throws FileNotFoundException
	 * @throws FileUploadFailedException
	 */
	public static UploadFileInfo uploadFile(File file, String path,
			String filename, String contentType, String bucketName,
			Map<String, String> userMetadata) throws FileNotFoundException,
			FileUploadFailedException {
		if (file == null || !file.canRead()) {
			throw new IllegalArgumentException(
					"The file is not null and must be readable!");
		}
		if (contentType == null) {
			throw new IllegalArgumentException("contentType is not null!");
		}
		if (bucketName == null) {
			throw new IllegalArgumentException("bucketName is not null!");
		}
		// if (!com.aliyun.openservices.oss.internal.OSSUtils
		// .validateBucketName(bucketName)) {
		// throw new IllegalArgumentException("bucketName is not validate!");
		// }

		// 创建上传Object的Metadata
		ObjectMetadata meta = new ObjectMetadata();
		// 必须设置ContentLength
		meta.setContentLength(file.length());
		meta.setContentType(contentType);
		// 设置用户自定义的meta值。
		if (userMetadata != null && !userMetadata.isEmpty()) {
			meta.setUserMetadata(userMetadata);
		}

		// 输入流不用关闭，执行完putObject方法后自动关闭。
		InputStream content = new FileInputStream(file);

		String fileKey = "";

		if (filename != null && !filename.trim().isEmpty()) {
			// 如果定义了文件名,就用这个文件名称做上传文件的文件名。
			fileKey = (path == null ? "" : path).trim() + filename;
		} else {
			// 如果没有定义文件名，就用将要上传文件的原始文件名做上传文件的文件名。
			fileKey = (path == null ? "" : path).trim() + file.getName();
		}

		// 上传Object.
		PutObjectResult result = null;
		// 初始化返回结果
		UploadFileInfo info = new UploadFileInfo();
		try {
			// 上传。
			result = client.putObject(bucketName, fileKey, content, meta);

			// 设置返回结果。
			info.setFileName(file.getName());
			info.setBucketName(bucketName);
			info.setFileKey(fileKey);
			info.setFileMD5(result.getETag());
			info.setFileInternalURL(OSSURLUtils.generateInternalURL(bucketName,
					client.getEndpoint(), fileKey));
			info.setFileInternetURL(OSSURLUtils.generateInternetURL(bucketName,
					client.getEndpoint(), fileKey));
		} catch (OSSException e) {
			// TODO 生产环境将此错误写入日志。错误返回参数详情查询ossapi文档。
			String message = "Code:" + e.getErrorCode() + "," + "Message:"
					+ e.getMessage() + "," + "RequestId:" + e.getRequestId();
			System.out.println(message);
			e.printStackTrace();
			throw new FileUploadFailedException();

		} catch (ClientException e) {
			e.printStackTrace();
			throw new FileUploadFailedException();
		}

		return info;
	}

	/**
	 * 删除文件。
	 * 
	 * @param bucketName
	 * @param key
	 */
	public static void deleteFile(String bucketName, String key) {
		// 删除Object
		client.deleteObject(bucketName, key);
	}
	
	
	
	/**
	 * 返回OSS相应的bucketName下，相应前缀下的文件和子文件夹。
	 * @param bucketName
	 * @param prefix 文件前缀，例如  "aa/","aa/bb/"。
	 * @return
	 */
	public static List<ObjectInfo> getObjectAndFolder(String bucketName,String prefix){
		List<ObjectInfo> result=new ArrayList<ObjectInfo>();
		// 构造ListObjectsRequest请求
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
		listObjectsRequest.setDelimiter("/");
		//处理前缀
		String temp=handlePrefix(prefix);
		if(temp!=null){
			listObjectsRequest.setPrefix(temp);
		}
		// List Objects
		ObjectListing listing = client.listObjects(listObjectsRequest);

		// 遍历所有Object 文件
		for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
			//去除和前缀同名的object，剩下的都是文件。
			if(!objectSummary.getKey().equals(temp)){
				ObjectInfo oi=new ObjectInfo();
				oi.setKey(objectSummary.getKey());
				oi.setType(ObjectInfo.TYPE_FILE);
				result.add(oi);
			}
		}

		// 遍历所有CommonPrefix 子文件夹
		for (String commonPrefix : listing.getCommonPrefixes()) {
			ObjectInfo oi=new ObjectInfo();
			oi.setKey(commonPrefix);
			oi.setType(ObjectInfo.TYPE_FOLDER);
			result.add(oi);
		}
		
		
		return result;
	}
	
	
	
	/**
	 * 得到OSS中存储的对象。
	 * @param bucketName
	 * @param key
	 * @return 没有对应的文件就返回null
	 */
	public static OSSObject getObject(String bucketName,String key){
		try {
			return client.getObject(bucketName, key);
		} catch (OSSException e) {
			
			// 没有对应的文件就返回null。
			return null;
		} 
	}
	
	
	
	
	
	/**
	 * 保证文件前缀前面没有/,结尾处有一个/
	 * @param prefix
	 * @return
	 */
	private static String handlePrefix(String prefix){
		String result=null;
		if(prefix!=null){
			//去掉字符串前后所有的"/"。
			result=prefix.replaceAll("(^\\s*)|(\\s*$)", "").replaceAll("^(/+)|(/+)$", "");  
			result+="/";
		}
		return result;
	}

	

	/**
	 * 生成文件URL内网地址。
	 * 已经迁移到OSSURLUtils
	 * @param bucketName
	 * @param endpoint
	 * @param fileName
	 * @return
	 */
//	private static String generateInternalURL(String bucketName, URI endpoint,
//			String fileName) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("http://");
//		sb.append(bucketName);
//		sb.append(".");
//		String point = endpoint.toString().replaceFirst("http://", "");
//		if (point.indexOf(INTERNAL_FLAG) != -1) {
//			sb.append(point);
//		} else {
//			int local = point.indexOf(".");
//			String first = point.substring(0, local);
//			String last = point.substring(local, point.length());
//			sb.append(first);
//			sb.append(INTERNAL_FLAG);
//			sb.append(last);
//		}
//		sb.append("/");
//		sb.append(fileName);
//		return sb.toString();
//	}

	/**
	 * 生成文件URL内网地址。
	 * 已经迁移到OSSURLUtils
	 * @param bucketName
	 * @param endpoint
	 * @param fileName
	 * @return
	 */
//	private static String generateInternalURL(String bucketName, String fileName) {
//		return generateInternalURL(bucketName, client.getEndpoint(), fileName);
//	}

	/**
	 * 生成文件URL外网地址。
	 * 已经迁移到OSSURLUtils
	 * @param bucketName
	 * @param endpoint
	 * @param fileName
	 * @return
	 */
//	private static String generateInternetURL(String bucketName, URI endpoint,
//			String fileName) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("http://");
//		sb.append(bucketName);
//		sb.append(".");
//		String point = endpoint.toString().replaceFirst("http://", "");
//		if (point.indexOf(INTERNAL_FLAG) == -1) {
//			sb.append(point);
//		} else {
//			sb.append(point.replace(INTERNAL_FLAG, ""));
//		}
//		sb.append("/");
//		sb.append(fileName);
//		return sb.toString();
//	}

	/**
	 * 生成文件URL外网地址。
	 * 已经迁移到OSSURLUtils
	 * @param bucketName
	 * @param endpoint
	 * @param fileName
	 * @return
	 */
//	private static String generateInternetURL(String bucketName, String fileName) {
//		return generateInternetURL(bucketName, client.getEndpoint(), fileName);
//	}

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
//			throw new RuntimeException(path + " don't find");
//		}
//		return props;
//	}

	/**
	 * 加载bucketName的映射文件。 
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private static Properties loadBucketNameProperties() {
		final String path = BUCKETNAME_PROPERTIES_PATH;
		Properties props = new Properties();
		InputStream in = OSSUtils.class.getResourceAsStream(path);
		try {
			props.load(in);
		} catch (IOException e) {
			throw new RuntimeException(path + " don't find");
		}
		return props;
	}

	/**
	 * 转换成短地址。
	 * 
	 * @param url
	 *            如果url等于null时，返回空。
	 * @return
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private static String changeToShortURL(String url) {
		String result = url;
		if (result != null) {
			int amount = SHORT_URL_PLACEHOLDER.size();
			String[] replaces = new String[amount];
			String[] replaceholders = new String[amount];
			Set<String> keys = SHORT_URL_PLACEHOLDER.keySet();
			int i = 0;
			int j = 0;
			for (String key : keys) {
				replaceholders[i++] = key;
				replaces[j++] = SHORT_URL_PLACEHOLDER.get(key);
			}

			for (int k = 0; k < amount; k++) {
				result = result.replaceAll(replaces[k], replaceholders[k]);
			}
			return result;
		} else {
			return "";
		}
	}

	/**
	 * 将短地址转换成原始地址。
	 * 
	 * @param url
	 *            如果url等于null时，返回空。
	 * @return
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private static String changeToOriginalURL(String url) {
		String result = url;
		if (result != null) {
			int amount = SHORT_URL_PLACEHOLDER.size();
			String[] replaces = new String[amount];
			String[] replaceholders = new String[amount];
			Set<String> keys = SHORT_URL_PLACEHOLDER.keySet();
			int i = 0;
			int j = 0;
			for (String key : keys) {
				replaceholders[i++] = key;
				replaces[j++] = SHORT_URL_PLACEHOLDER.get(key);
			}
			// result=result.replaceAll("[", "a");
			// result=result.replaceAll("\\]", "\\\\\\]");
			for (int k = 0; k < amount; k++) {
				result = result.replaceAll(replaceholders[k], replaces[k]);
			}
			return result;
		} else {
			return "";
		}
	}

	/**
	 * 返回短地址映射的json格式字符串。
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private static String generateShortURLPlaceholder() {
		JSONObject json = new JSONObject();
		Set<String> keys = SHORT_URL_PLACEHOLDER.keySet();
		for (String key : keys) {
			json.put(key, SHORT_URL_PLACEHOLDER.get(key));
		}
		return json.toString();
	}

	/**
	 * 当bucketName为私有访问时，生成可访问的URL外链接。
	 * 
	 * @param bucketName
	 * @param key
	 * @param expiration
	 *            访问地址过期时间，单位分钟，表示多长时间之后地址不能访问。
	 * @return
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private static String generateURL(String bucketName, String key,
			int expiration) {
		Date date = new Date(new Date().getTime() + expiration * 60 * 1000);
		return client.generatePresignedUrl(bucketName, key, date).toString();
	}

	/**
	 * 生成以当前时间为标准的文件路径。
	 * 
	 * @return
	 */
	public static String generateFilePathByDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "/"
				+ calendar.get(Calendar.DAY_OF_MONTH) + "/";
	}

	/**
	 * 得到endpoint。
	 * 
	 * @return
	 */
	public static URI getEndPoint() {
		return client.getEndpoint();
	}
	
	
	
	/**
	 * oss文件列表结构
	 * @author TM TSE
	 *
	 */
	public static class ObjectInfo{
		public static final int TYPE_FOLDER=0;
		public static final int TYPE_FILE=1;
		private String key;
		private int type;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
	}

	public static class UploadFileInfo {
		private String fileName;
		private String fileKey;
		private String fileMD5;
		private String bucketName;
		private String fileInternalURL;
		private String fileInternetURL;

		public UploadFileInfo() {

		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getFileMD5() {
			return fileMD5;
		}

		public void setFileMD5(String fileMD5) {
			this.fileMD5 = fileMD5;
		}

		public String getFileInternalURL() {
			return fileInternalURL;
		}

		public void setFileInternalURL(String fileInternalURL) {
			this.fileInternalURL = fileInternalURL;
		}

		public String getFileInternetURL() {
			return fileInternetURL;
		}

		public void setFileInternetURL(String fileInternetURL) {
			this.fileInternetURL = fileInternetURL;
		}

		public String getFileKey() {
			return fileKey;
		}

		public void setFileKey(String fileKey) {
			this.fileKey = fileKey;
		}

		public String getBucketName() {
			return bucketName;
		}

		public void setBucketName(String bucketName) {
			this.bucketName = bucketName;
		}

	}
	public static class FileUploadFailedException extends Exception {

		private static final long serialVersionUID = 1L;
		
		public FileUploadFailedException(String msg) {
			super(msg);
		}
		public FileUploadFailedException(){
			
		}
	}
}
