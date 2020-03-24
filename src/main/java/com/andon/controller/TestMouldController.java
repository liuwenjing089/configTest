package com.andon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andon.bean.TestMould;
import com.andon.commons.ExceptionCode;
import com.andon.service.TestMouldService;

@Controller
@RequestMapping("/testMould")
public class TestMouldController extends BaseController {

    @Autowired
    private TestMouldService testMouldService;
    
    //试模列表信息
    @RequestMapping(value = "/getTestMouldList.do", method = RequestMethod.GET)
    public @ResponseBody Object getTestMouldList(HttpServletRequest request, String deId) {
    	
		try {

			List<TestMould> testMouldList = testMouldService.getTestMouldList(deId);
			TestMould testMould = testMouldService.selectTestMouldById(deId);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("TestMouldList", testMouldList);
			map.put("TestMould", testMould);
			
			return resultHandler(map);
			
		} catch (Exception e) {
			e.printStackTrace();
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    	
    }
    
    //保存
    @RequestMapping(value = "/updateTestMould.do", method = RequestMethod.POST)
    public @ResponseBody Object repairMould(TestMould testMould, HttpServletRequest request,  HttpSession session) {
    	
		try {
			String userName = session.getAttribute("username").toString();
			testMould.setCreateUser(userName);
			testMould.setUpdateUser(userName);
			testMouldService.insertTestMould(testMould);
			
			return resultHandler(null);
		} catch (Exception e) {
			e.printStackTrace();
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
}
