package com.andon.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andon.bean.User;
import com.andon.bean.UserBaseInfo;
import com.andon.bean.UserCallInterface;
import com.andon.commons.ExceptionCode;
import com.andon.service.UserService;
import com.andon.service.UtilsService;
import com.andon.utils.PrintException;
import com.andon.utils.PropertiesFileUtils;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;
	@Autowired
	private UtilsService utilsService;
    
    //存储用户信息到session
    @ResponseBody
    @RequestMapping(value = "/loginIn.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Object loginIn(HttpServletRequest request, HttpSession httpSession) {
        try {
            String loginuser = null;
            loginuser = request.getParameter("username");
            String pwd = request.getParameter("password");

            //获取用户认证接口
    		Map<String, String> pMap = PropertiesFileUtils.loadPropertiesFile("properties/config.properties");
    		String urlPreposition = pMap.get("UrlPreposition");
    		String userAuthentication = pMap.get("UserAuthentication");
    		String authUrl = urlPreposition + userAuthentication;
            
    		//创建个MAP 包含url和TfdaToUserCallInterface两个对象，url是读取配置文件，user是TfdaToUserCallInterface对象（可根据需要增加属性）
			Map<String, Object> intfaceMapToPost = new HashMap<String, Object>();
			UserCallInterface userInterface = new UserCallInterface();
			userInterface.setUsername(loginuser);
			userInterface.setPassword(pwd);
			intfaceMapToPost.put("url", authUrl);
			intfaceMapToPost.put("user", userInterface);
			//调用POST方法
			Map<String, Object> resultMap = utilsService.callToPost(intfaceMapToPost);
			
			//获取code
			String code = resultMap.get("code").toString();
			User user = null;

			
			//截取工号
			String hguid = loginuser.substring(loginuser.indexOf("A")+1);
			
			//调用api 存储session   如果是200只需去本地验证用户名是否存在，如果返回0是发生异常，如果是其他去本地验证用户名和密码
			if("200".equals(code)){
				user = userService.getUser(hguid,"");
			}else if("0".equals(code)){
				return resultHandler(exceptionHandle(ExceptionCode.USER_INTERFACE_EXCEPTION));
			}else {
				user = userService.getUser(hguid,pwd);
			}

            if(user==null){
                return resultHandler(exceptionHandle(ExceptionCode.ERROR_USERNAME_OR_PWD));
            }
            
            UserBaseInfo userBaseInfo = new UserBaseInfo();
            userBaseInfo.setUsername(loginuser);
            userBaseInfo.setPassword(pwd);
            userBaseInfo.setUserType(user.getUserType());
            // 将用户保存到session内
            httpSession.setAttribute("username", user.getUsername());
            httpSession.setAttribute("password", pwd);
            httpSession.setAttribute("userType",user.getUserType());
            httpSession.setAttribute("factory",user.getFactory());
            httpSession.setAttribute("employeeName", user.getEmployeeName());
            httpSession.setAttribute("userClass", user.getUserClass());
            
            
			//获取tokken
//			String token = resultMap.get("value").toString(); 
//            List<UserCallInterface> userList = null;
//			//获取全部员工的列表
//            if(!StringUtils.isBlank(token)){
//				//返回结果转为对象
//		        JSONObject jsonObject=JSONObject.fromObject(token);
//		        Tokens tfdaToToken=(Tokens)JSONObject.toBean(jsonObject, Tokens.class);
//		        String tokenString  = tfdaToToken.getToken();
//	            //从配置文件读取调用接口的url
//	            String staffEmployeeAgreement = pMap.get("StaffEmployeeAgreement");
//	            String staffUrl = urlPreposition + staffEmployeeAgreement;	
//	    		//创建个MAP 包含url和token 其中url根据需要拼接参数
//				Map<String, Object> intfaceMapToGets = new HashMap<String, Object>();
//				intfaceMapToGets.put("url", staffUrl);
//				intfaceMapToGets.put("token", tokenString);
//				String userInfoStringList = utilsService.callToGET(intfaceMapToGets);
//				userList = JsonUtils.jsonToList(userInfoStringList, UserCallInterface.class);
//				httpSession.setAttribute("userList", userList);
//            }

			
            return resultHandler(null);
        } catch (Exception e) {
        	e.printStackTrace();
        	e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }

    }

    //获取用户session信息
    @ResponseBody
    @RequestMapping(value = "/getUserSession.do", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Object getUserSession(HttpServletRequest request, HttpSession httpSession) {

        try {
//            ServletContext application = httpSession.getServletContext();
//            Map<String, Object> loginMap = (Map<String, Object>) application.getAttribute("loginMap");
//            for (String key : loginMap.keySet()) {
//                if (httpSession.getAttribute("username").toString().equals(key)) {
//                    if (!httpSession.getId().equals(loginMap.get(key))) {
//                        return resultHandler(exceptionHandle(ExceptionCode.LOGIN_EARE_ERROR));
//                    }
//                }
//            }
            UserBaseInfo userBaseInfo = null;
            userBaseInfo = new UserBaseInfo();
            if (httpSession != null) {
                userBaseInfo.setUsername(httpSession.getAttribute("username").toString());
                userBaseInfo.setPassword(httpSession.getAttribute("password").toString());
                userBaseInfo.setUserType(Integer.parseInt(httpSession.getAttribute("userType").toString()));
                userBaseInfo.setEmployeeName(httpSession.getAttribute("employeeName").toString());
                userBaseInfo.setPower((Integer)httpSession.getAttribute("userClass"));
            }
            return resultHandler(userBaseInfo);
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

//    //退登陆
//    @ResponseBody
//    @RequestMapping(value = "/logout.do", method = RequestMethod.POST)
//    public Object logout(HttpServletRequest request, HttpSession httpSession) {
//
//        String loginuser = request.getParameter("username");
//        String pwd = request.getParameter("password");
//        //TODO
//        //调用api 存储session
//        User user = userService.getUser(loginuser,pwd);
//
//        HttpSession session = request.getSession();
//        String user_id = request.getParameter(String.valueOf(user.getId()));
//        if (session != null) {
//            try {
//                session.removeAttribute("user");
//                ServletContext application = session.getServletContext();
//                application.removeAttribute("loginMap");
//            } catch (Exception e) {
//                return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
//            }
//        }
//        return resultHandler(null);
//    }


}


