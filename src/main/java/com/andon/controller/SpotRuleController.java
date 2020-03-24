package com.andon.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andon.bean.CodeList;
import com.andon.bean.ComboxByTask;
import com.andon.bean.Equip;
import com.andon.bean.Mould;
import com.andon.bean.SpotRule;
import com.andon.bean.dto.EquipSeeOutput;
import com.andon.commons.ConstantCode;
import com.andon.commons.ExceptionCode;
import com.andon.service.CodeListService;
import com.andon.service.MouldService;
import com.andon.service.SpotRuleService;

@Controller
@RequestMapping("/spotRule")
public class SpotRuleController extends BaseController {
    @Autowired
    private SpotRuleService spotRuleService;
    
    @Autowired
    private MouldService mouldService;
    
    @Autowired
    private CodeListService codeListService;
    

    //添加规则
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public @ResponseBody Object add(HttpServletRequest request, HttpSession httpSession, @RequestBody List<SpotRule> spotRule) {
        try {
        	
        	//验重
        	int count = spotRuleService.Validation(spotRule);
        	if(count > 0){
        		return resultHandler(exceptionHandle(ExceptionCode.SPOT_RULE_REPEAT));
        	}
        	
			String userName = httpSession.getAttribute("username").toString();
        	spotRuleService.add(spotRule, userName);
            return resultHandler(null);
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    //编辑规则
    @RequestMapping(value = "/edit.do", method = RequestMethod.POST)
    public @ResponseBody Object edit(HttpServletRequest request, HttpSession httpSession, @RequestParam String groupKey, @RequestBody List<SpotRule> spotRule) {
        try {
			String userName = httpSession.getAttribute("username").toString();
        	spotRuleService.edit(spotRule, userName, groupKey);
            return resultHandler(null);
        } catch (Exception e) {
        	e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    //规则列表
    @RequestMapping(value = "/selectRuleList.do", method = RequestMethod.POST)
    public @ResponseBody Object selectRuleList(HttpServletRequest request, HttpSession httpSession, SpotRule spotRule) {
        try {
        	List<SpotRule> list = spotRuleService.selectRuleList(spotRule);
            return resultHandler(list);
        } catch (Exception e) {
        	e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    //规则列表
    @RequestMapping(value = "/delRule.do", method = RequestMethod.POST)
    public @ResponseBody Object delRule(HttpServletRequest request, HttpSession httpSession, SpotRule spotRule) {
        try {
			String userName = httpSession.getAttribute("username").toString();
			spotRule.setUpdateUser(userName);
        	spotRuleService.delRule(spotRule);
            return resultHandler(null);
        } catch (Exception e) {
        	
        	e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    //规则详情
    @RequestMapping(value = "/ruleDetail.do", method = RequestMethod.POST)
    public @ResponseBody Object ruleDetail(HttpServletRequest request, HttpSession httpSession, SpotRule spotRule) {
        try {

        	List<SpotRule> list = spotRuleService.ruleDetail(spotRule);
        	List<CodeList> codeList = codeListService.getByType(Integer.valueOf(ConstantCode.SPOT_TYPE_CODE),2);
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("spotRule", list);
        	map.put("codeList", codeList);
        	
            return resultHandler(map);
        } catch (Exception e) {
        	e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    //查询设备型号
    @RequestMapping(value = "/selectEquipModel.do", method = RequestMethod.POST)
    public @ResponseBody Object selectEquipModel(HttpServletRequest request, HttpSession httpSession, String equipModel) {
        try {

        	int count = spotRuleService.selectEquipModel(equipModel);

            return resultHandler(count);
        } catch (Exception e) {
        	e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    //查询设备型号
    @RequestMapping(value = "/selectEquipModelListByEquipModel.do", method = RequestMethod.POST)
    public @ResponseBody Object selectEquipModelListByEquipModel(HttpServletRequest request, HttpSession httpSession, 
    		String equipModel, @RequestParam(value = "ids[]", required = false) String[] ids) {
        try {
            String factory = httpSession.getAttribute("factory").toString();
        	List<EquipSeeOutput> EquipList = spotRuleService.selectEquipModelListByEquipModel(equipModel, ids, factory); 	
            return resultHandler(EquipList);
        } catch (Exception e) {
        	e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    //查询设备型号
    @RequestMapping(value = "/selectEquipModelListByEquipModelToTest.do", method = RequestMethod.POST)
    public @ResponseBody Object selectEquipModelListByEquipModelToTest(HttpServletRequest request, HttpSession httpSession, 
    		String equipModel, @RequestParam(value = "ids[]", required = false) String[] ids) {
        try {
            String factory = httpSession.getAttribute("factory").toString();
        	List<ComboxByTask> EquipList = spotRuleService.selectEquipModelListByEquipModelToTest(equipModel, ids, factory);     	
            return resultHandler(EquipList);
        } catch (Exception e) {
        	e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    //根据模具的车种和图号搜索唯一的 车种记录
    @RequestMapping(value = "/selectMouldByVehicleTypeAndFigureNumber.do", method = RequestMethod.POST)
    public @ResponseBody Object selectMouldById(HttpServletRequest request, HttpSession httpSession, Mould mould) {
		try {
            int factory = Integer.parseInt(httpSession.getAttribute("factory").toString());
            mould.setFactory(factory);
			List<Mould> mouldToView = mouldService.selectMouldByVehicleTypeAndFigureNumber(mould);
			return resultHandler(mouldToView);
			
		} catch (Exception e) {
            e.printStackTrace();
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    	
    }
    
    //根据设备型号模糊查询设备列表
    @RequestMapping(value = "/selectEquipModelList.do", method = RequestMethod.POST)
    public @ResponseBody Object selectEquipModelList(HttpServletRequest request, HttpSession httpSession, Equip equip) {
    	
		try {
        	int factory =  Integer.parseInt(httpSession.getAttribute("factory").toString());
        	equip.setFactory(factory);
			List<EquipSeeOutput> equipSeeOutputList = spotRuleService.selectEquipModelList(equip);
			return resultHandler(equipSeeOutputList);

		} catch (Exception e) {
			e.printStackTrace();
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    	
    }

	//根据设备型号模糊查询设备列表数量
    @RequestMapping(value = "/selectEquipModelCount.do", method = RequestMethod.POST)
    public @ResponseBody Object selectEquipModelCount(HttpServletRequest request, HttpSession httpSession, Equip equip) {
		try {

			int pageCount = 0;
        	int factory =  Integer.parseInt(httpSession.getAttribute("factory").toString());
        	equip.setFactory(factory);
			pageCount = spotRuleService.selectEquipModelCount(equip);

			return resultHandler(pageCount);
			
		} catch (Exception e) {
			e.printStackTrace();
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    //查询模具  点检类型下拉框
    @RequestMapping(value = "/selectCodeListBySpotType.do", method = RequestMethod.GET)
    public @ResponseBody Object selectCodeListBySpotType(HttpServletRequest request, HttpSession httpSession) {
        try {
        	List<CodeList> codeList = codeListService.getByType(Integer.valueOf(ConstantCode.SPOT_TYPE_CODE),2);
            return resultHandler(codeList);
        } catch (Exception e) {
        	e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
}
