package com.andon.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.andon.bean.Combox;
import com.andon.commons.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andon.bean.SpotCheck;
import com.andon.commons.ExceptionCode;
import com.andon.service.SpotCheckService;

@Controller
@RequestMapping("/spotCheck")
public class SpotCheckController extends BaseController {
    @Autowired
    private SpotCheckService spotCheckService;

    //添加
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public @ResponseBody Object add(HttpServletRequest request, HttpSession httpSession, @RequestBody List<SpotCheck> spotCheck) {
        try {
			String userName = httpSession.getAttribute("username").toString();
        	spotCheckService.add(spotCheck, userName);
            return resultHandler(null);
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //获取checklist
    @RequestMapping(value = "/getCheck.do", method = RequestMethod.POST)
    public @ResponseBody Object getCheck(HttpServletRequest request, HttpSession httpSession,SpotCheck spotCheck) {
        try {
            String userName = httpSession.getAttribute("username").toString();
            return resultHandler(spotCheckService.getSpotCheck(spotCheck,userName));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //更新checklist
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public @ResponseBody Object update(HttpServletRequest request,HttpSession httpSession,@RequestBody List<SpotCheck>  listString) {

        try {
            String userName = httpSession.getAttribute("username").toString();
            spotCheckService.updateSpotCheck(listString,userName);
            return resultHandler(null);
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    /**
     * 查询所有下拉框
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/initCombox.do", method = RequestMethod.GET)
    public @ResponseBody Object initCombox(HttpServletRequest request) {

        //点检状态combox
        List<Combox> manufacturerCombox = new ArrayList<Combox>();
        Combox c1 = new Combox();
        c1.setId(Constant.SPOT_ID_1);
        c1.setName(Constant.SPOT_NAME_1);
        manufacturerCombox.add(c1);
        Combox c2 = new Combox();
        c2.setId(Constant.SPOT_ID_2);
        c2.setName(Constant.SPOT_NAME_2);
        manufacturerCombox.add(c2);
        Combox c3 = new Combox();
        c3.setId(Constant.SPOT_ID_3);
        c3.setName(Constant.SPOT_NAME_3);
        manufacturerCombox.add(c3);
        Combox c4 = new Combox();
        c4.setId(Constant.SPOT_ID_4);
        c4.setName(Constant.SPOT_NAME_4);
        manufacturerCombox.add(c3);
        return resultHandler(manufacturerCombox);
    }
}
