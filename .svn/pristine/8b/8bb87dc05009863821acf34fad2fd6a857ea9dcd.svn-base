package com.andon.controller;

import com.andon.commons.ExceptionCode;
import com.andon.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserContrtoller extends BaseController{
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getBaoquan.do", method = RequestMethod.POST)
    public @ResponseBody Object getBaoquan(HttpServletRequest request) {

        try {
            return resultHandler(userService.getUserBaoquan());
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    @RequestMapping(value = "/getDepartment.do", method = RequestMethod.POST)
    public @ResponseBody Object getDepartment(HttpServletRequest request,String username) {

        try {
            return resultHandler(userService.getDepartment(username));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    @RequestMapping(value = "/getUserRepairStauts.do", method = RequestMethod.GET)
    public @ResponseBody Object getUserRepairStauts(HttpServletRequest request) {

        try {
        	
        	List<Map<String, Object>> map = userService.getUserRepairStauts();
            return resultHandler(map);
        } catch (Exception e) {
        	e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

}
