package com.andon.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andon.bean.MouldFaultParts;
import com.andon.bean.MouldPreventionType;
import com.andon.bean.MouldRepair;
import com.andon.bean.RepairPrevention;
import com.andon.bean.SameRecurrenceRapir;
import com.andon.bean.TestMould;
import com.andon.bean.dto.TestMouldRepair;
import com.andon.commons.Constant;
import com.andon.commons.ExceptionCode;
import com.andon.service.MouldFaultPartsService;
import com.andon.service.MouldPreventionTypeService;
import com.andon.service.MouldRepairPreventionService;
import com.andon.service.MouldTaskTimeService;
import com.andon.service.RepairPreventionService;
import com.andon.service.RepairService;
import com.andon.service.TestMouldService;
import com.andon.utils.DateUtils;
import com.andon.utils.PrintException;
import com.andon.validateInterface.First;

@Controller
@RequestMapping("/mouldPreventionType")
public class MouldPreventionTypeController extends BaseController{

    @Autowired
    private RepairService repairService;
    
    @Autowired
    private RepairPreventionService repairPreventionService;
    
    @Autowired
    private MouldRepairPreventionService mouldRepairPreventionService;
    
    @Autowired
    private MouldTaskTimeService mouldTaskTimeService;
    
    @Autowired
    private TestMouldService testMouldService;
    
    @Autowired
	private MouldFaultPartsService mouldFaultPartsService;
    
    @Autowired
    private MouldPreventionTypeService mouldPreventionTypeService;

    //维修一览
	@RequestMapping(value = "/getRepairToMouldPreventionList.do", method = RequestMethod.POST)
    public @ResponseBody Object getRepair(HttpServletRequest request, HttpSession session, int type,int state,String beginTime,String endTime,String createUser,String workMan,
    		String groupKey,int beginIndex,int pageSize) {
        try {
        	String factory = session.getAttribute("factory").toString();
        	String userClass = session.getAttribute("userClass").toString();
        	List<SameRecurrenceRapir> getRepair = repairService.getRepairToMouldPrevention(factory, type,state,beginTime,endTime,createUser,workMan,  userClass, groupKey, beginIndex,pageSize);

            return resultHandler(getRepair);
        } catch (Exception e) {
        	e.printStackTrace();e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    //分页用
    @RequestMapping(value = "/getRepairToMouldPreventionCount.do", method = RequestMethod.POST)
    public @ResponseBody Object getRepairCount(HttpServletRequest request, HttpSession session, int type,int state,String beginTime,String endTime,String createUser,String workMan
    		,String groupKey) {
        try {
        	String factory = session.getAttribute("factory").toString();
        	String userClass = session.getAttribute("userClass").toString();
        	int count = repairService.getRepairCountToMouldPrevention(factory, type,state,beginTime,endTime,createUser,workMan, userClass, groupKey);
            return resultHandler(count);
        } catch (Exception e) {
        	e.printStackTrace();e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //再发预防报修报修
    @RequestMapping(value = "/repairMouldToPreventive.do", method = RequestMethod.POST)
    public @ResponseBody Object repairMould(@Validated({First.class}) MouldRepair mouldRepair, BindingResult result, HttpServletRequest request,  HttpSession session) {
    	
		//校验转型	
		if (result.hasErrors()) {
            return resultHandler(convertErrors(result));
        }
		
		try {
			String userName = session.getAttribute("username").toString();
			mouldRepair.setCreateUser(userName);
			mouldRepair.setUpdateUser(userName);
			mouldRepairPreventionService.insertMouldRepair(mouldRepair);
			
			return resultHandler(null);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    
    //当前模具再发预防维修一览
	@RequestMapping(value = "/getRepairPrevention.do", method = RequestMethod.POST)
    public @ResponseBody Object getRepairPrevention(HttpServletRequest request, HttpSession session, int type,int state,String beginTime,String endTime,String createUser,String workMan, String repairNumber, int mouldId, 
    		String reId, int beginIndex,int pageSize) {
        try {
        	String factory = session.getAttribute("factory").toString();
        	String userClass = session.getAttribute("userClass").toString();
        	List<RepairPrevention> getRepair = repairPreventionService.getRepair(factory, type,state,beginTime,endTime,createUser,workMan, repairNumber, userClass, mouldId, reId, beginIndex,pageSize);
        	
        	if(getRepair != null && getRepair.size()>0){
            	//处理模具显示
            	getRepair = mouldTaskTimeService.addMouldRepaitPreventionCss(getRepair);
        	}

            return resultHandler(getRepair);
        } catch (Exception e) {
        	e.printStackTrace();e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
	
    //当前模具再发预防维修一览页数
    @RequestMapping(value = "/getRepairPreventionCount.do", method = RequestMethod.POST)
    public @ResponseBody Object getRepairPreventionCount(HttpServletRequest request, HttpSession session, int type,int state,String beginTime,String endTime,String createUser,String workMan, String repairNumber,
    		int mouldId, String reId) {
        try {
        	String factory = session.getAttribute("factory").toString();
        	String userClass = session.getAttribute("userClass").toString();
            return resultHandler(repairPreventionService.getRepairCount(factory, type,state,beginTime,endTime,createUser,workMan, repairNumber, userClass, mouldId, reId));
        } catch (Exception e) {
        	e.printStackTrace();e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    
    //更新
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public @ResponseBody Object update(HttpServletRequest request,HttpSession httpSession, RepairPrevention repair) {

        try {
            // 设置接单时间
            if(repair.getOrderTime() != null && repair.getOrderTime().length() != 0){
                if(repair.getOrderTime().equals(Constant.ORDER_TIME)){
                    repair.setOrderTime(DateUtils.getCurrentDateMinute());
                }
            }
            //设置维修开始时间
            if(repair.getBeginTime() != null && repair.getBeginTime().length() != 0){
                if(repair.getBeginTime().equals(Constant.BEGIN_TIME)){
                    repair.setBeginTime(DateUtils.getCurrentDateMinute());
                }
            }
            //更新状态班长系长科长确认时间
            if(repair.getState()==4){
                repair.setConfirmationTime(DateUtils.getCurrentDateMinute());
            }else if(repair.getState()==5){
                repair.setPreDepChiefConTime(DateUtils.getCurrentDateMinute());
            }else if(repair.getState()==6){
                repair.setPreSecChiefConTime(DateUtils.getCurrentDateMinute());
            }
            String user = httpSession.getAttribute("username").toString();
            repair.setUpdateUser(user);
            repair.setUpdateTime(DateUtils.getNowDate());
            repair.setIsActive(Constant.ACTIVE_VALID);
            repairPreventionService.update(repair);
            return resultHandler(null);
        } catch (Exception e) {
        	e.printStackTrace();e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    //查询模具报修一条记录详情
    @RequestMapping(value = "/selectRepairMouldByRepairIds.do", method = RequestMethod.POST)
    public @ResponseBody Object selectRepairMouldByRepairIds(String deId, HttpServletRequest request,  HttpSession session) {
    	
		try {
             
			TestMouldRepair testMouldRepair = repairPreventionService.selectMouldRepairByIds(deId);
			

			return resultHandler(testMouldRepair);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    
    //模具维修记录填写，补全
    @RequestMapping(value = "/selectRepairMouldByRepairId.do", method = RequestMethod.POST)
    public @ResponseBody Object selectRepairMouldByRepairId(String uuid, HttpServletRequest request,  HttpSession session) {
    	
		try {
             
			MouldRepair mouldRepair = mouldRepairPreventionService.selectMouldRepairById(uuid);
			
			//查询试模记录中是否已经存在成功的记录
			int count = testMouldService.selectTestMouldToSucceedById(uuid);
			if(count > 0){
				mouldRepair.setFlag(true);
			}else{
				mouldRepair.setFlag(false);
			}
			//部品品番赋值
			List<MouldFaultParts> outparts = new ArrayList<>();
			List<MouldFaultParts> partsList = mouldFaultPartsService.selectParts(String.valueOf(mouldRepair.getReId()));
			if(partsList != null && partsList.size()>0){
				for (MouldFaultParts parts:partsList){
					outparts.add(parts);
				}
				mouldRepair.setPartsList(outparts);
			}

			return resultHandler(mouldRepair);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    
    //模具维修记录详情页面
    @RequestMapping(value = "/selectRepairMouldByConfirm.do", method = RequestMethod.POST)
    public @ResponseBody Object selectRepairMouldByConfirm(String id, HttpServletRequest request,  HttpSession session) {
    	
		try {
             
			MouldRepair mouldRepair = mouldRepairPreventionService.selectMouldRepairById(id);

			//部品品番赋值
			List<MouldFaultParts> outparts = new ArrayList<>();
			List<MouldFaultParts> partsList = mouldFaultPartsService.selectParts(mouldRepair.getReId());
			if(partsList != null && partsList.size()>0){
				for (MouldFaultParts parts:partsList){
					outparts.add(parts);
				}
				mouldRepair.setPartsList(outparts);
			}

			//查询试模记录
			List<TestMould> testMouldList = testMouldService.getTestMouldList(String.valueOf(id));
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("MouldRepair", mouldRepair);
			map.put("TestMouldList", testMouldList);
			
			return resultHandler(map);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    
    //查询模具故障类型
    @RequestMapping(value = "/getMouldPreventionTypeList.do", method = RequestMethod.POST)
    public @ResponseBody Object getMouldPreventionTypeList(String reId, HttpServletRequest request,  HttpSession session) {
    	
		try {

			List<MouldPreventionType> mouldPreventionTypeList = mouldPreventionTypeService.getMouldPreventionTypeListToPreventionRepair(reId);
			
			return resultHandler(mouldPreventionTypeList);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    //各级别确认
    @RequestMapping(value = "/mouldRepairByMonitorConfirm.do", method = RequestMethod.GET)
    public @ResponseBody Object mouldRepairByMonitorConfirm(String id, HttpServletRequest request,  HttpSession session) {
    	
		try {
			String userName = session.getAttribute("username").toString();
			int userType = Integer.parseInt(session.getAttribute("userType").toString());

			mouldRepairPreventionService.mouldRepairByMonitorConfirm(id, userName, userType);
			
			return resultHandler(null);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    //各级别确认 + 插入模具报修种类
    @RequestMapping(value = "/mouldRepairByMonitorConfirmAndAddType.do", method = RequestMethod.GET)
    public @ResponseBody Object mouldRepairByMonitorConfirmAndAddType(String id, int mouldId, HttpServletRequest request,  HttpSession session) {
    	
		try {
			String userName = session.getAttribute("username").toString();
			int userType = Integer.parseInt(session.getAttribute("userType").toString());

			mouldRepairPreventionService.mouldRepairByMonitorConfirmAndAddType(id, userName, userType, mouldId);
			
			return resultHandler(null);
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
}
