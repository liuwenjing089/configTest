package com.andon.servlet.utils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;
import com.andon.bean.ExceptionMessage;
import com.andon.commons.ServletContextKeyConstant;
import com.andon.utils.PropertiesFileUtils;

@Controller
public class ServletUtils implements ServletContextAware {
	protected ServletContext servletContext;
	// @Autowired
	// protected HttpServletRequest request;
	@Autowired
	protected MessageSource msg;
	private static final Locale DEFAULT_LOCAL = Locale.CHINA;
	
	
	
	
	/**
	 * 得到访问地址，一直到项目路径。
	 * @param request
	 * @return
	 */
	protected static String getBasePath(HttpServletRequest request){
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+ request.getContextPath()+"/";
	}
			
	/**
	 * 读取properties文件转换成的Map对象（此对象是从servletContext读取的还是从直接从文件加载的对于用户透明）
	 * 
	 * @param nameInServletContext
	 * @param propertiesFilePath
	 * @return
	 */
	public final Map<String, String> getProperties(String nameInServletContext,
			String propertiesFilePath) {
		// TODO 是否需要添加同步方法，为了线程安全。

		if (nameInServletContext == null) {
			throw new IllegalArgumentException(
					"nameInServletContext can't be null");
		}

		if (propertiesFilePath == null) {
			throw new IllegalArgumentException(
					"propertiesFilePath can't be null");
		}
		if (servletContext == null) {
			Map<String, String> r = new HashMap<String, String>();
			r.put("error", "dirERROR");
			return r;
		}
		@SuppressWarnings("unchecked")
		Map<String, String> result = (Map<String, String>) servletContext
				.getAttribute(nameInServletContext);

		if (result == null) {
			// ServletContext没有值，就重新从properties文件中加载。
			result = PropertiesFileUtils.loadPropertiesFile(propertiesFilePath);
			// 将转换后的结果放入ServletContext中。
			servletContext.setAttribute(nameInServletContext, result);
			// 返回结果。
			return result;

		}
		// 直接返回ServletContext中的数据。
		return result;
	}

	/**
	 * 获得ErrorMessage的properties文件。
	 * 
	 * @param servletContext
	 * @return
	 */
	protected Map<String, String> getErrorMessagePros() {
		return getProperties(ServletContextKeyConstant.ERROR_MESSAGE_KEY,
				ServletContextKeyConstant.ERROR_MESSAGE_FILE_PATH);
	}

	/**
	 * 获得permission.properties文件。
	 * 
	 * @param servletContext
	 * @return
	 */
	protected Map<String, String> getPermissionProps() {
		return getProperties(ServletContextKeyConstant.PERMISSION_KEY,
				ServletContextKeyConstant.PERMISSION_FILE_PATH);
	}

//	/**
//	 * 得到友盟账号和密码。
//	 * 
//	 * @return
//	 */
//	protected Map<String, String> getUMAppKeyPros() {
//		return getProperties(ServletContextKeyConstant.UMAPPKEY_KEY,
//				ServletContextKeyConstant.UMAPPKEY_FILE_PATH);
//	}

	/**
	 * 得到手机端无需session就可以访问地址。
	 * 
	 * @return
	 */
	protected Map<String, String> getLoginFilterIgnorableURLProps() {
		return getProperties(
				ServletContextKeyConstant.LOGIN_FILTER_IGNORABLE_URL_KEY,
				ServletContextKeyConstant.LOGIN_FILTER_IGNORABLE_URL_FILE_PATH);
	}
	
//	
//	protected Map<String, String> getDeprecatedURLProps() {
//		return getProperties(
//				ServletContextKeyConstant.LOGIN_FILTER_IGNORABLE_URL_KEY,
//				ServletContextKeyConstant.LOGIN_FILTER_IGNORABLE_URL_FILE_PATH);
//	}
	
	
//	/**
//	 * 得到服务城市
//	 * @return
//	 */
//	protected Map<String, String> getCityProps() {
//		return getProperties(
//				ServletContextKeyConstant.CITY_URL_KEY,
//				ServletContextKeyConstant.CITY_FILE_PATH);
//	}

	/**
	 * 得到网页端访问地址。
	 * 
	 * @return
	 */
	protected Map<String, String> getWebPageURLProps() {
		return getProperties(ServletContextKeyConstant.WEB_PAGE_URL_KEY,
				ServletContextKeyConstant.WEB_PAGE_URL_FILE_PATH);
	}

	/**
	 * 得到所有用户访问接口。
	 * 
	 * @return
	 */
	protected Map<String, String> getUserAccessInterfaceProps() {
		return getProperties(
				ServletContextKeyConstant.USER_ACCESS_INTERFACE_APPLICATION_KEY,
				ServletContextKeyConstant.USER_ACCESS_INTERFACE_FILE_PATH);
	}

//	protected Map<String, String> getShopAccessInterfaceProps() {
//		return getProperties(
//				ServletContextKeyConstant.SHOP_ACCESS_INTERFACE_APPLICATION_KEY,
//				ServletContextKeyConstant.SHOP_ACCESS_INTERFACE_FILE_PATH);
//	}

	/**
	 * 生成一个ExceptionMessage对象。
	 * 
	 * @param messageCode
	 * @return
	 */
	protected ExceptionMessage createExceptionMessage(String messageCode) {
		return createExceptionMessage(null,messageCode, null, null, null);
	}

	/**
	 * 生成一个ExceptionMessage对象。
	 * 
	 * @param messageCode
	 * @param extraMessage 
	 * @return
	 */
	protected ExceptionMessage createExceptionMessage(String messageCode,
			String extraMessage) {
		return createExceptionMessage(null,messageCode, extraMessage, null, null);
	}

	/**
	 * 生成一个ExceptionMessage对象。
	 * 
	 * @param messageCode
	 * @param extra
	 *            额外的信息。
	 * @return
	 */
	protected ExceptionMessage createExceptionMessage(String messageCode,
			Object extra) {
		return createExceptionMessage(null,messageCode, null, null, null);
	}

	/**
	 * 生成一个ExceptionMessage对象。
	 * 
	 * @param messageCode
	 * @param message
	 *            在codeMessage中加上 附加的message信息。
	 * @param extra
	 * @return
	 */
//	protected ExceptionMessage createExceptionMessage(String messageCode,
//			String message, Object[] args, Object extra) {
//		if (messageCode == null) {
//			throw new IllegalArgumentException("messageCode isn't null");
//		}
//		ExceptionMessage error = new ExceptionMessage();
//		error.setCode(messageCode);
//		// error.setMessage(StringUtils.trimToEmpty(getErrorMessagePros().get(
//		// messageCode))
//		// + StringUtils.trimToEmpty(message));
//
//		RequestContext requestContext = new RequestContext(
//				((ServletRequestAttributes) RequestContextHolder
//						.getRequestAttributes()).getRequest());
//		if (args != null) {
//			error.setMessage(requestContext.getMessage(messageCode, args)
//					+ StringUtils.trimToEmpty(message));
//		} else {
//			error.setMessage(requestContext.getMessage(messageCode)
//					+ StringUtils.trimToEmpty(message));
//		}
//		error.setExtra(extra);
//		return error;
//	}

	protected ExceptionMessage createExceptionMessage(String messageCode,Object[] args){
		return createExceptionMessage(null, messageCode, null, args, null);
	}
	
	/**
	 * 以国际化的方式产生ExceptionMessage
	 * @param request 用来得到国际化local参数。
	 * @param messageCode 
	 * @param extraMessage 额外的message和从国际化Message文件中得到拼接在一起。
	 * @param args 国际化Message文件中可以传入参数
	 * @param extra ExceptionMessage对象中额外的错误对象信息。
	 * @return
	 */
	protected ExceptionMessage createExceptionMessage(
			HttpServletRequest request, String messageCode, String extraMessage,
			Object[] args, Object extra) {
		if (messageCode == null) {
			throw new IllegalArgumentException("messageCode isn't null");
		}

		ExceptionMessage error = new ExceptionMessage();
		error.setCode(messageCode);
		if (request != null) {
			error.setMessage(msg.getMessage(messageCode, args,
					request.getLocale())+splitExtraMessage(extraMessage));
		} else {
			error.setMessage(msg.getMessage(messageCode, args, DEFAULT_LOCAL)+splitExtraMessage(extraMessage));

		}
		if(extra!=null){
			error.setExtra(extra);
		}
		return error;
	}
	
	
	/**
	 * 对Spring国际化方法的一个简单的封装。
	 * @param code
	 * @return
	 */
	protected String getMessage(String code){
		return msg.getMessage(code, null, DEFAULT_LOCAL);
	}
	/**
	 * 拼接extraMessage
	 * @param extraMessage
	 * @return
	 */
	private  static String splitExtraMessage(String extraMessage){
		if(extraMessage!=null){
			return "["+extraMessage+"]";
		}else{
			return "";
		}
		
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * 通过名字找相应的cookie。
	 * 
	 * @param name
	 * @return
	 */
	protected Cookie getCookieByName(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (name.equalsIgnoreCase(cookie.getName())) {
					return cookie;
				}
			}
		}
		return null;
	}
	/**
	 * 得到大盘信息prop。
	 * 
	 * @return
	 */
	public Map<String, String> getCompositeProps() {
		return getProperties(ServletContextKeyConstant.COMPOSITE_KEY,
				ServletContextKeyConstant.COMPOSITE_FILE_PATH);
	}
}
