package com.andon.controller;

import com.andon.bean.Equip;
import com.andon.commons.Constant;
import com.andon.commons.ExceptionCode;
import com.andon.service.EquipService;
import com.andon.utils.DateUtils;
import com.andon.utils.PrintException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/equip")
public class EquipController extends BaseController {

    @Autowired
    private EquipService equipService;

    //添加设备
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public @ResponseBody Object add(HttpServletRequest request, HttpSession httpSession, Equip equip) {
        //获取session
        try {
            equip.setCreateUser(httpSession.getAttribute("username").toString());
            equip.setUpdateUser(httpSession.getAttribute("username").toString());
            equip.setCreateTime(DateUtils.getNowDate());
            equip.setUpdateTime(DateUtils.getNowDate());
            equip.setIsActive(Constant.ACTIVE_VALID);
            equipService.add(equip);
            return resultHandler(null);
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //设备一览
    @RequestMapping(value = "/getEquip.do", method = RequestMethod.POST)
    public @ResponseBody Object getEquip(HttpServletRequest request, int beginIndex, int pageSize) {
        try {
            return resultHandler(equipService.getEquip(beginIndex,pageSize));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    //分页用
    @RequestMapping(value = "/getEquipCount.do", method = RequestMethod.POST)
    public @ResponseBody Object getEquipCount(HttpServletRequest request) {

        try {
            return resultHandler(equipService.getEquipCount());
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    //检索
    @RequestMapping(value = "/getEquipByFrim.do", method = RequestMethod.POST)
    public @ResponseBody Object getEquipByFrim(HttpServletRequest request,int factory, String equipName,String equipDescription,String useBeginTime,String equipModel,String standard,int beginIndex, int pageSize) {
        try {
            return resultHandler(equipService.getEquipByFirm(factory,equipName,equipDescription,useBeginTime,equipModel,standard,beginIndex,pageSize));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    //分页用
    @RequestMapping(value = "/getEquipByFrimCount.do", method = RequestMethod.POST)
    public @ResponseBody Object getEquipByFrimCount(HttpServletRequest request,int factory,String equipName,String equipDescription,String useBeginTime,String equipModel,String standard) {
        try {
            return resultHandler(equipService.getEquipByFirmCount(factory,equipName,equipDescription,useBeginTime,equipModel,standard));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //设备信息
    @RequestMapping(value = "/getEquipByid.do", method = RequestMethod.POST)
    public @ResponseBody Object getEquip(HttpServletRequest request, int id) {
        try {
        	Equip equip = equipService.getByid(id);
        	
            return resultHandler(equip);
        } catch (Exception e) {
        	e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //修改
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public @ResponseBody Object update(HttpServletRequest request,HttpSession httpSession ,Equip equip) {
        try {
            equip.setUpdateUser(httpSession.getAttribute("username").toString());
            equip.setUpdateTime(DateUtils.getNowDate());
            equipService.update(equip);
            return resultHandler(null);
        } catch (Exception e) {
        	e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //删除
    @RequestMapping(value = "/deleteEq.do", method = RequestMethod.POST)
    public @ResponseBody Object deleteEq(HttpServletRequest request, int id) {

        try {
            equipService.deleteEq(id);
            return resultHandler(null);
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //删除生产线时清空设备lineId
    @RequestMapping(value = "/updateEqLine.do", method = RequestMethod.POST)
    public @ResponseBody Object updateEqLine(HttpServletRequest request, int id,int lineId) {

        try {
            equipService.updateEqLine(id,lineId);
            return resultHandler(null);
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    @RequestMapping(value = "/getEqBylineID.do", method = RequestMethod.POST)
    public @ResponseBody Object getEqBylineID(HttpServletRequest request,int lineId) {

        try {
            return resultHandler(equipService.getEqBylineID(lineId));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    @RequestMapping(value = "/deleteEqline.do", method = RequestMethod.POST)
    public @ResponseBody Object deleteEqline(HttpServletRequest request, @RequestBody List<Equip> equip) {

        try {
            equipService.deleteEqLine(equip);
            return resultHandler(null);
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }

    }

    //明细
    @RequestMapping(value = "/getEqSee.do", method = RequestMethod.POST)
    public @ResponseBody Object getEqSee(HttpServletRequest request,int id) {

        try {
            return resultHandler(equipService.getEquipSee(id));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
}
