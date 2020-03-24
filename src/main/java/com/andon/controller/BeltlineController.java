package com.andon.controller;


import com.andon.bean.Beltline;
import com.andon.commons.Constant;
import com.andon.commons.ExceptionCode;
import com.andon.service.BeltlineService;
import com.andon.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/line")
public class BeltlineController extends BaseController{

    @Autowired
    private BeltlineService beltlineService;

    //添加生产线
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public @ResponseBody Object add(HttpServletRequest request, HttpSession session, Beltline beltline) {
        try {
            String userName = session.getAttribute("username").toString();
            beltline.setCreateUser(userName);
            beltline.setUpdateUser(userName);
            beltline.setCreateTime(DateUtils.getNowDate());
            beltline.setUpdateTime(DateUtils.getNowDate());
            beltline.setIsActive(Constant.ACTIVE_VALID);
            beltlineService.add(beltline);
            return resultHandler(null);
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    //生产线一览
    @RequestMapping(value = "/getLine.do", method = RequestMethod.POST)
    public @ResponseBody
    Object getLine(HttpServletRequest request, HttpSession session, int beginIndex, int pageSize) {
        try {
        	
            String factory = session.getAttribute("factory").toString();
            return resultHandler(beltlineService.getLine(beginIndex,pageSize, factory));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //分页用
    @RequestMapping(value = "/getLineCount.do", method = RequestMethod.POST)
    public @ResponseBody Object getLineCount(HttpServletRequest request, HttpSession session) {
        try {
            String factory = session.getAttribute("factory").toString();
            return resultHandler(beltlineService.getLineCount(factory));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //检索
    @RequestMapping(value = "/getLineByFirm.do", method = RequestMethod.POST)
    public @ResponseBody
    Object getLineByFirm(HttpServletRequest request, HttpSession session, int lineType, String beltlineName,String beltlineDescription, int beginIndex, int pageSize) {
        try {
        	String factory = session.getAttribute("factory").toString();
            return resultHandler(beltlineService.getLineByFirm(lineType,beltlineName,beltlineDescription,beginIndex,pageSize, factory));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //分页用
    @RequestMapping(value = "/getLineByFirmCount.do", method = RequestMethod.POST)
    public @ResponseBody Object getLineByFirmCount(HttpServletRequest request, HttpSession session, int lineType, String beltlineName,String beltlineDescription) {
        try {
            String factory = session.getAttribute("factory").toString();
            return resultHandler(beltlineService.getLineByFirmCount(lineType, beltlineName,beltlineDescription, factory));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //修改
    @RequestMapping(value = "/updateLine.do", method = RequestMethod.POST)
    public @ResponseBody Object updateLine(HttpServletRequest request, HttpSession session,int id, int factory ,String beltlineName,String beltlineDescription, String updateUser, Date updateTime,int lineType) {
        try {
            String userName = session.getAttribute("username").toString();
            beltlineService.updateLine(id, factory, beltlineName,beltlineDescription,userName,DateUtils.getNowDate(),lineType);
            return resultHandler(null);
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }

    }
    //删除
    @RequestMapping(value = "/deleteLine.do", method = RequestMethod.POST)
    public @ResponseBody Object deleteLine(HttpServletRequest request, int id) {
        try {
            beltlineService.deleteLine(id);
            return resultHandler(null);
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }

    }
    @RequestMapping(value = "/getLineByid.do", method = RequestMethod.POST)
    public @ResponseBody
    Object getLineByid(HttpServletRequest request, int id) {
        try {
            return resultHandler(beltlineService.getLineByid(id));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    //生产线一览
    @RequestMapping(value = "/selectLineByMould.do", method = RequestMethod.GET)
    public @ResponseBody
    Object selectLineByMould(HttpServletRequest request) {
        try {
            return resultHandler(beltlineService.selectLineByMould());
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //设备生产线一览
    @RequestMapping(value = "/selectEquipLine.do", method = RequestMethod.POST)
    public @ResponseBody Object selectEquipLine(HttpServletRequest request, HttpSession session) {
        try {
        	String factory = session.getAttribute("factory").toString();
            return resultHandler(beltlineService.selectEquipLine(factory));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
}
