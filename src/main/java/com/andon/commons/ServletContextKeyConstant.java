package com.andon.commons;

public class ServletContextKeyConstant {
	// errorMessage.properties文件的路径。
	public static final String ERROR_MESSAGE_FILE_PATH = "/properties/message.properties";
	// errorMessage.properties文件在ServletContext中属性的key。
	public static final String ERROR_MESSAGE_KEY = "ERROR_MESSAGE";
	// permission.properties文件在ServletContext中属性的key。
	public static final String PERMISSION_KEY = "PERMISSION_KEY";
	// permission.properties文件的路径。
	public static final String PERMISSION_FILE_PATH = "/properties/permission.properties";

	// 用户访问接口生成Map在application域中的key
	public static final String USER_ACCESS_INTERFACE_APPLICATION_KEY = "userAccessInterfaceKey";
	// 用户访问接口在文件位置。
	public static final String USER_ACCESS_INTERFACE_FILE_PATH = "/properties/userAccessInterface.properties";

	// 登录过滤器忽视地址配置文件在ServletContext中的key
	public static final String LOGIN_FILTER_IGNORABLE_URL_KEY = "ignorableUrl";
	// 登录过滤器忽视地址配置文件的地址
	public static final String LOGIN_FILTER_IGNORABLE_URL_FILE_PATH = "/properties/loginFilterIgnorableURL.properties";

	// 强制更新过滤器忽视地址配置文件在ServletContext中的key
	public static final String WEB_PAGE_URL_KEY = "webPageURL";
	// 强制更新过滤器忽视地址配置文件的地址
	public static final String WEB_PAGE_URL_FILE_PATH = "/properties/webPageURL.properties";

	// 强制更新过滤器忽视地址配置文件在ServletContext中的key
	public static final String COMPOSITE_KEY = "composite";
	// 强制更新过滤器忽视地址配置文件的地址
	public static final String COMPOSITE_FILE_PATH = "/properties/composite.properties";
	// add by zhaoxinli start 20180920
	//添加standard.properties路径
	public static final String STANDARD_FILE_PATH = "/properties/standard.properties";
	// add by zhaoxinli end 20180920
	

}
