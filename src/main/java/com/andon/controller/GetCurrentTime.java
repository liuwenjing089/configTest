package com.andon.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andon.commons.ExceptionCode;

@Controller
@RequestMapping("/getCurrentTime")
public class GetCurrentTime extends BaseController{
    //维修一览
    @RequestMapping(value = "/getTime.do", method = RequestMethod.POST)
    public @ResponseBody Object getTime(HttpServletRequest request) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
            System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
            String data = df.format(new Date());
            
            return resultHandler(data);
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
}
