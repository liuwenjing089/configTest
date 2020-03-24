package com.andon.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andon.bean.SpotDetail;
import com.andon.bean.dto.SpotDetailEquip;
import com.andon.bean.dto.SpotDetailMould;
import com.andon.commons.ExceptionCode;
import com.andon.service.SpotDetailService;

@Controller
@RequestMapping("/spotDetail")
public class SpotDetailController extends BaseController{
    @Autowired
    private SpotDetailService spotDetailService;

    //获取模具
    @RequestMapping(value = "/getDetailMould.do", method = RequestMethod.POST)
    public @ResponseBody Object getDetailMould(HttpServletRequest request, SpotDetailMould spotDetailMould) {

        try {
            return resultHandler(spotDetailService.getSpotDetailMould(spotDetailMould));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //获取模具
    @RequestMapping(value = "/getDetailMouldCount.do", method = RequestMethod.POST)
    public @ResponseBody Object getDetailMouldCount(HttpServletRequest request, SpotDetailMould spotDetailMould) {

        try {
            return resultHandler(spotDetailService.getSpotDetailMouldCount(spotDetailMould));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //获取设备
    @RequestMapping(value = "/getDetailEquip.do", method = RequestMethod.POST)
    public @ResponseBody Object getDetailEquip(HttpServletRequest request, SpotDetailEquip spotDetailEquip) {

        try {
            return resultHandler(spotDetailService.getSpotDetailEquip(spotDetailEquip));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //获取设备
    @RequestMapping(value = "/getDetailEquipCount.do", method = RequestMethod.POST)
    public @ResponseBody Object getDetailEquipCount(HttpServletRequest request, SpotDetailEquip spotDetailEquip) {

        try {
            return resultHandler(spotDetailService.getSpotDetailEquipCount(spotDetailEquip));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //更新状态
    @RequestMapping(value = "/updateState.do", method = RequestMethod.POST)
    public @ResponseBody Object updateState(HttpServletRequest request, int id,String spotMan) {

        try {
            spotDetailService.updateState(id,spotMan);
            return resultHandler(null);
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //更新确认状态
    @RequestMapping(value = "/updateConfirmState.do", method = RequestMethod.POST)
    public @ResponseBody Object updateConfirmState(HttpServletRequest request, SpotDetail spotDetail) {
        try {
            spotDetailService.updateConfirmState(spotDetail);
            return resultHandler(null);
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //id为条件检索
    @RequestMapping(value = "/getDetailById.do", method = RequestMethod.POST)
    public @ResponseBody Object getDetailById(HttpServletRequest request, int id) {
        try {
            return resultHandler(spotDetailService.getSpotDetailById(id));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //日历获取模具
    @RequestMapping(value = "/getDateDetailMould.do", method = RequestMethod.POST)
    public @ResponseBody Object getDateDetailMould(HttpServletRequest request, SpotDetailMould spotDetailMould) {

        try {
            return resultHandler(spotDetailService.getDateSpotDetailMould(spotDetailMould));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //日历获取模具
    @RequestMapping(value = "/getDateDetailMouldCount.do", method = RequestMethod.POST)
    public @ResponseBody Object getDateDetailMouldCount(HttpServletRequest request, SpotDetailMould spotDetailMould) {

        try {
            return resultHandler(spotDetailService.getDateSpotDetailMouldCount(spotDetailMould));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //日历获取设备
    @RequestMapping(value = "/getDateDetailEquip.do", method = RequestMethod.POST)
    public @ResponseBody Object getDateDetailEquip(HttpServletRequest request, SpotDetailEquip spotDetailEquip) {

        try {
            return resultHandler(spotDetailService.getDateSpotDetailEquip(spotDetailEquip));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //日历获取设备
    @RequestMapping(value = "/getDateDetailEquipCount.do", method = RequestMethod.POST)
    public @ResponseBody Object getDateDetailEquipCount(HttpServletRequest request, SpotDetailEquip spotDetailEquip) {

        try {
            return resultHandler(spotDetailService.getDateSpotDetailEquipCount(spotDetailEquip));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
}
