package com.andon.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import com.andon.bean.CommonResult;
import com.andon.bean.ExceptionMessage;
import com.andon.servlet.utils.ServletUtils;
import com.andon.utils.JsonUtil;

/**
 * Filter的公共类。
 * 
 * @author TM Tse
 * 
 */
@Component
public abstract class BaseFilter extends ServletUtils implements Filter {

	@Override
	public void destroy() {
		System.out.println("destroy is no-operation");
	}

	@Override
	public abstract void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException;

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("init is no-operation");
	}

	/**
	 * 判断访问地址是否在传入的地址Map中。如果访问地址在判断的地址中就返回true，否则返回false。
	 * 判断以map中URL开头的，或者等于的Map中URL的。
	 * 
	 * @param request
	 *            HttpServletRequest对象。
	 * @param urls
	 *            需要判断的地址Map。
	 * @return
	 */
	protected boolean isURL(HttpServletRequest request, Map<String, String> urls) {
		Set<Entry<String, String>> urlSet = urls.entrySet();
		for (Entry<String, String> urlEntry : urlSet) {
			if (request.getRequestURI().startsWith(
					request.getContextPath() + urlEntry.getValue())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 将ErrorMessage转换为json发送给发送给前端。
	 * 
	 * @param response
	 * @param responseObject
	 */
	/*
	 * protected void responseOutWithJson(HttpServletResponse response,
	 * List<ExceptionMessage> list) { if(list==null){ throw new
	 * IllegalArgumentException("Argument list isn't null"); } //
	 * 创建Gson对象。用于json反序列化。 final Gson gson = new Gson(); //
	 * 创建序列化的List<ShopLocationInfoForPush>的Type对象。 final Type collectionType =
	 * new TypeToken<List<ExceptionMessage>>() { }.getType();
	 * 
	 * 
	 * response.setCharacterEncoding("UTF-8");
	 * response.setContentType("application/json; charset=utf-8"); PrintWriter
	 * out = null; try { out = response.getWriter();
	 * out.append(gson.toJson(list, collectionType)); } catch (IOException e) {
	 * e.printStackTrace(); } finally { if (out != null) { out.close(); } }
	 * 
	 * }
	 */
	protected void responseOutWithJson(HttpServletResponse response,
			List<ExceptionMessage> list) {
		if (list == null) {
			throw new IllegalArgumentException("Argument list isn't null");
		}
		CommonResult result = new CommonResult();
		result.setRet(0);
		result.setData(list);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(JsonUtil.objectToJson(result));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}

	}

	/**
	 * 将json字符串发送给发送给前端。
	 * 
	 * @param response
	 * @param json
	 */
	protected void responseOutWithJson(HttpServletResponse response, String json) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.append(json);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

	
	/**
	 * 用国际化文件建立ExceptionMessage对象。(在filter对象中特意实现的方法，因为filter对象无法取到webApplicationContext环境)
	 * @param request
	 * @param ms
	 * @param messageCode
	 * @param message
	 * @param args
	 * @return
	 */
//	protected ExceptionMessage createExceptionMessage(HttpServletRequest request, MessageSource ms,String messageCode) {
//		return createExceptionMessage(request,ms,messageCode, null,null);
//	}
	
	/**
	 * 用国际化文件建立ExceptionMessage对象。(在filter对象中特意实现的方法，因为filter对象无法取到webApplicationContext环境)
	 * @param request
	 * @param ms
	 * @param messageCode
	 * @param message
	 * @param args
	 * @return
	 */
//	protected ExceptionMessage createExceptionMessage(
//			HttpServletRequest request, MessageSource ms, String messageCode,
//			String message, Object[] args) {
//		if (messageCode == null) {
//			throw new IllegalArgumentException("messageCode isn't null");
//		}
//		ExceptionMessage error = new ExceptionMessage();
//		error.setCode(messageCode);
//		error.setMessage(ms.getMessage(messageCode, args, request.getLocale())
//				+ StringUtils.trimToEmpty(message));
//		return error;
//	}

}
