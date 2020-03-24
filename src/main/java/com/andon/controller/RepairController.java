package com.andon.controller;


import java.text.ParseException;
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

import com.andon.bean.EquipRepair;
import com.andon.bean.MouldTaskTime;
import com.andon.bean.Repair;
import com.andon.bean.dto.RepairInput;
import com.andon.commons.Constant;
import com.andon.commons.ExceptionCode;
import com.andon.service.EquipRepairService;
import com.andon.service.MouldTaskTimeService;
import com.andon.service.RepairService;
import com.andon.utils.CreateNumber;
import com.andon.utils.DateUtils;
import com.andon.utils.PrintException;
import com.andon.validateInterface.First;

@Controller
@RequestMapping("/repair")
public class RepairController extends BaseController{

    @Autowired
    private RepairService repairService;

    @Autowired
    private EquipRepairService equipRepairService;
    
    @Autowired
    private MouldTaskTimeService mouldTaskTimeService;
    
    //添加修理
    @RequestMapping(value = "/addRepair.do", method = RequestMethod.POST)
    public @ResponseBody Object addRepair(@Validated({First.class}) RepairInput repairInput , BindingResult result, HttpServletRequest request, HttpSession httpSession) {
		
    	//校验转型	
		if (result.hasErrors()) {
            return resultHandler(convertErrors(result));     		
		}		
        try {
            String user = null;
            user = httpSession.getAttribute("username").toString();
            Repair repair = new Repair();
            
            
    		//获取报修表中报修编号是当天最大的一条
    		Map<String, Object> map = repairService.selectRepairNum();
    		Integer num = null;
    		if(map != null){
    			num = Integer.valueOf(map.get("num").toString());
    		}
    		
    		
    		//获取报修编号
    		String repairNumber = CreateNumber.createEquiqNumber(num);

    		repair.setRepairNumber(repairNumber);
            
            repair.setDetailId(repairInput.getDetailId());
            repair.setApplicant(user);
            repair.setType(Constant.TYPE_EQUIP);
            repair.setState(Constant.NEW_REPAIR);
            repair.setReportRepairTime(DateUtils.getCurrentDateMinute());
            repair.setCreateUser(user);
            repair.setUpdateUser(user);
            repair.setCreateTime(DateUtils.getNowDate());
            repair.setUpdateTime(DateUtils.getNowDate());
            repair.setIsActive(Constant.ACTIVE_VALID);
            //返回主表id
            int id = repairService.add(repair);
            EquipRepair equipRepair = new EquipRepair();
            equipRepair.setReId(id);
            equipRepair.setOperator(user);
            equipRepair.setAppearance(repairInput.getAppearance());
            equipRepair.setCreateUser(user);
            equipRepair.setUpdateUser(user);
            equipRepair.setCreateTime(DateUtils.getNowDate());
            equipRepair.setUpdateTime(DateUtils.getNowDate());
            equipRepair.setIsActive(Constant.ACTIVE_VALID);
            equipRepairService.add(equipRepair);
            return resultHandler(null);
        } catch (Exception e) {
        	e.printStackTrace();e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //维修一览
	@RequestMapping(value = "/getRepair.do", method = RequestMethod.POST)
    public @ResponseBody Object getRepair(HttpServletRequest request, HttpSession session, int type,int state,String beginTime,String endTime,String createUser,String workMan, String repairNumber, int beginIndex,int pageSize) {
        try {
        	String factory = session.getAttribute("factory").toString();
        	String userClass = session.getAttribute("userClass").toString();
        	List<Repair> getRepair = repairService.getRepair(factory, type,state,beginTime,endTime,createUser,workMan, repairNumber, userClass, beginIndex,pageSize);
        	
        	if(getRepair != null && getRepair.size() > 0){
            	//处理模具显示
            	getRepair = mouldTaskTimeService.addMouldRepaitCss(getRepair);
        	}

            return resultHandler(getRepair);
        } catch (Exception e) {
        	e.printStackTrace(PrintException.printException());
        	e.printStackTrace();e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    //分页用
    @RequestMapping(value = "/getRepairCount.do", method = RequestMethod.POST)
    public @ResponseBody Object getRepairCount(HttpServletRequest request, HttpSession session, int type,int state,String beginTime,String endTime,String createUser,String workMan, String repairNumber) {
        try {
        	String factory = session.getAttribute("factory").toString();
        	String userClass = session.getAttribute("userClass").toString();
            return resultHandler(repairService.getRepairCount(factory, type,state,beginTime,endTime,createUser,workMan, repairNumber, userClass));
        } catch (Exception e) {
        	e.printStackTrace();e.printStackTrace(PrintException.printException());
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //更新
    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public @ResponseBody Object update(HttpServletRequest request,HttpSession httpSession, Repair repair) {

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
            repairService.update(repair);
            return resultHandler(null);
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    
    //插入
    @RequestMapping(value = "/insertMouldTaskTime.do", method = RequestMethod.POST)
    @ResponseBody
    public Object insertMouldTaskTime(HttpServletRequest request, MouldTaskTime mouldTaskTime, HttpSession session)throws ParseException {
		try {

			String userName = session.getAttribute("username").toString();
			mouldTaskTime.setCreateUser(userName);
			mouldTaskTime.setUpdateUser(userName);

			mouldTaskTimeService.insert(mouldTaskTime);
			return resultHandler(null);

		} catch (Exception e) {
			e.printStackTrace();e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
    
    
    //查询该报修记录的各种状态用于在页面上展示不同的按钮形态
    @RequestMapping(value = "/selectMouldTaskTimeStauts.do", method = RequestMethod.POST)
    @ResponseBody
    public Object selectMouldTaskTimeStauts(HttpServletRequest request, MouldTaskTime mouldTaskTime, HttpSession session)throws ParseException {
		try {
			int cssNum = mouldTaskTimeService.selectMouldTaskTimeStauts(mouldTaskTime);
            
			return resultHandler(cssNum);

		} catch (Exception e) {
			e.printStackTrace();e.printStackTrace(PrintException.printException());
			return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
		}
    }
}
