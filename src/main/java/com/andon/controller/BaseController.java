package com.andon.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.andon.bean.CommonResult;
import com.andon.bean.ExceptionMessage;
import com.andon.commons.ExceptionCode;
import com.andon.servlet.utils.ServletUtils;
import com.andon.utils.JsonUtil;
import com.andon.utils.OSSURLUtils;
import com.andon.utils.OSSUtils.UploadFileInfo;

@Controller
public class BaseController extends ServletUtils {
	protected static final String REDIRECT_FLAG = "redirect:";

	public static final String UTF_8 = "UTF-8";
	private static Logger logger = LoggerFactory.getLogger(BaseController.class); 
	protected CommonResult resultHandler(Object obj) {
		CommonResult result = new CommonResult();
		if (obj != null) {
			if (obj instanceof List) {
				List<?> list = (List<?>) obj;
				if (!list.isEmpty()) {
					if (list.get(0) instanceof ExceptionMessage) {
						result.setRet(0);
					} else {
						result.setRet(1);
					}
				} else {
					result.setRet(1);
				}
			} else if (obj instanceof ExceptionMessage) {
				result.setRet(0);
			} else {
				result.setRet(1);
			}
		} else {
			result.setRet(1);
		}
		result.setData(obj);
		return result;
	}

	/**
	 * 接口参数的check
	 * 
	 * @param result
	 * @return errorMap
	 */
	protected List<ExceptionMessage> convertErrors(BindingResult result) {
		// 得到错误的list列表
		List<ObjectError> errors = result.getAllErrors();
		// 返回重新解析的error信息
		List<ExceptionMessage> errorList = new ArrayList<ExceptionMessage>();
		for (ObjectError error : errors) {
			ExceptionMessage msg = new ExceptionMessage();
			msg.setCode(String.valueOf(ExceptionCode.ERROR_CODE));
			msg.setMessage(error.getDefaultMessage());
			logger.debug(error.getDefaultMessage());
			errorList.add(msg);
		}
		return errorList;
	}

	/**
	 * 异常处理方法
	 * 
	 * @param exceptionCode
	 * @return
	 */
	public List<ExceptionMessage> exceptionHandle(String exceptionCode) {
		return exceptionHandle(exceptionCode, null);
	}
	
	/**
	 * 异常处理方法
	 * 
	 * @param exceptionCode
	 * @return
	 */
	public List<ExceptionMessage> exceptionHandle(String exceptionCode,Object[] args) {
		// 返回的异常对象。
		List<ExceptionMessage> exceptions = new ArrayList<ExceptionMessage>();
		exceptions.add(createExceptionMessage(exceptionCode,args));
		return exceptions;
	}

	/**
	 * 解码oss上传文件后的返回信息转换json串，以存入数据库 {buckname:xxx,key:xxxx}
	 * 
	 * @param uploadFileInfo
	 * @return
	 */
	public static String decodeOSSURL(UploadFileInfo uploadFileInfo) {
		// 读取头像上传地址。
		Map<String, String> map = new HashMap<String, String>();
		map.put(OSSURLUtils.OSS_BUCKEYNAME, uploadFileInfo.getBucketName());
		map.put(OSSURLUtils.OSS_KEY, uploadFileInfo.getFileKey());
		return JsonUtil.mapToJson(map);
	}

	
	/**
	 * 将数据库存储的文件JSON串转换为网络URL。
	 * @param image
	 * @return
	 */
	public static String encodeOSSURL(String image) {
		Map<String, String> map = JsonUtil.jsonToMap(image);
		return OSSURLUtils.generateInternetURL(
				map.get(OSSURLUtils.OSS_BUCKEYNAME),
				map.get(OSSURLUtils.OSS_KEY));
	}
	
	
	

	// @ExceptionHandler
	// public @ResponseBody
	// Object exp(HttpServletRequest request, Exception ex) {
	// List<ExceptionMessage> err_list = new ArrayList<ExceptionMessage>();
	//
	// // 根据不同错误转向不同页面
	//
	// if (ex instanceof
	// org.springframework.web.multipart.MaxUploadSizeExceededException) {
	// err_list.add(createExceptionMessage("UploadMessage.Error001"));
	// return resultHandler(err_list);
	// }
	// if (ex instanceof org.springframework.web.multipart.MultipartException) {
	// err_list.add(createExceptionMessage("UploadMessage.Error002"));
	// return resultHandler(err_list);
	// } else {
	// // err_list.add(createExceptionMessage("UploadMessage.Error003"));
	// ex.printStackTrace();
	// return resultHandler(ex);
	// }
	// }
}
