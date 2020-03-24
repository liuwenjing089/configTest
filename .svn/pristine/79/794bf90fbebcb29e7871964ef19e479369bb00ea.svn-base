package com.andon.controller;

import com.andon.bean.EquipRepairKy;
import com.andon.bean.MouldRepairKy;
import com.andon.commons.Constant;
import com.andon.commons.ExceptionCode;
import com.andon.service.EquipRepairKyService;
import com.andon.service.MouldRepairKyService;
import com.andon.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/kyList")
public class KyController extends BaseController{
    @Autowired
    private EquipRepairKyService equipRepairKyServicce;
    @Autowired
    private MouldRepairKyService mouldRepairKyServicce;

    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public @ResponseBody Object add(HttpServletRequest request,  HttpSession session, EquipRepairKy equipRepairKy) {
        try {
			String userName = session.getAttribute("username").toString();
			equipRepairKy.setCreateUser(userName);
			equipRepairKy.setUpdateUser(userName);
			equipRepairKy.setCreateTime(DateUtils.getNowDate());
			equipRepairKy.setUpdateTime(DateUtils.getNowDate());
			equipRepairKy.setIsActive(Constant.ACTIVE_VALID);
			equipRepairKyServicce.add(equipRepairKy);
			return resultHandler(null);
		} catch (Exception e) {
			e.printStackTrace();
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public @ResponseBody Object update(HttpServletRequest request,  HttpSession session, EquipRepairKy equipRepairKy) {
        try {
			String userName = session.getAttribute("username").toString();
			equipRepairKy.setCreateUser(userName);
			equipRepairKy.setUpdateUser(userName);
			equipRepairKy.setCreateTime(DateUtils.getNowDate());
			equipRepairKy.setUpdateTime(DateUtils.getNowDate());
			equipRepairKy.setIsActive(Constant.ACTIVE_VALID);
			equipRepairKyServicce.update(equipRepairKy);
			return resultHandler(null);
		} catch (Exception e) {
			e.printStackTrace();
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    @RequestMapping(value = "/mouldKyAdd.do", method = RequestMethod.POST)
    public @ResponseBody Object mouldKyAdd(HttpServletRequest request,  HttpSession session, MouldRepairKy mouldRepairKy) {
    	try {
			String userName = session.getAttribute("username").toString();
			mouldRepairKy.setCreateUser(userName);
			mouldRepairKy.setUpdateUser(userName);
			mouldRepairKy.setCreateTime(DateUtils.getNowDate());
			mouldRepairKy.setUpdateTime(DateUtils.getNowDate());
			mouldRepairKy.setIsActive(Constant.ACTIVE_VALID);
			mouldRepairKyServicce.add(mouldRepairKy);
			return resultHandler(null);
		} catch (Exception e) {

			e.printStackTrace();
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }

    //获取ky作业名
	@RequestMapping(value = "/getWorkName.do", method = RequestMethod.POST)
	public @ResponseBody Object getWorkName(HttpServletRequest request, int id) {
		try {
			return resultHandler(equipRepairKyServicce.selectWorkName(id));
		} catch (Exception e) {
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
	}
	
    //获取ky详情
	@RequestMapping(value = "/getKyDetail.do", method = RequestMethod.POST)
	public @ResponseBody Object getKyDetail(HttpServletRequest request, int id) {
		try {
			return resultHandler(equipRepairKyServicce.getKyDetail(id));
		} catch (Exception e) {
			e.printStackTrace();
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
	}
}
