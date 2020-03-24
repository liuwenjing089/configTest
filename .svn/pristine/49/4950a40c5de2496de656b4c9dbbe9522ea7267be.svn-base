package com.andon.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.andon.bean.Equip;
import com.andon.bean.Mould;
import com.andon.bean.dto.TaskInput;
import com.andon.commons.ExceptionCode;
import com.andon.exception.TaskDuplicationException;
import com.andon.service.EquipService;
import com.andon.service.MouldService;
import com.andon.service.SpotTaskService;

@Controller
@RequestMapping("/task")
public class SpotTaskController extends BaseController {
    @Autowired
    private SpotTaskService spotTaskService;
    @Autowired
    private EquipService equipService;
    @Autowired
    private MouldService mouldService;

    //添加点检任务
    @RequestMapping(value = "/checkSpot.do", method = RequestMethod.POST)
    public @ResponseBody Object checkSpot(HttpServletRequest request, HttpSession httpSession, int spotType, int spotInterval, @RequestParam(value = "ids[]", required = false) String[] ids) {
        try {
//            String user = httpSession.getAttribute("username").toString();
//            String factory = httpSession.getAttribute("factory").toString();
            Map<String, Object> map = spotTaskService.checkSpot(spotType, spotInterval, ids);

            return resultHandler(map);
            
        }catch(TaskDuplicationException e){
        	//自定义异常处理
        	String message = CustomException(e);
        	
    		return resultHandler(message);  
        } catch (Exception e) {
        	e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    //添加点检任务
    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public @ResponseBody Object add(HttpServletRequest request, HttpSession httpSession, TaskInput taskInput, @RequestParam(value = "ids[]", required = false) String[] ids) {
        try {
            String user = httpSession.getAttribute("username").toString();
            String factory = httpSession.getAttribute("factory").toString();
            spotTaskService.add(taskInput,user, ids, factory);
            return resultHandler(null);
            
        }catch(TaskDuplicationException e){
        	//自定义异常处理
        	String message = CustomException(e);
        	
    		return resultHandler(message);  
        } catch (Exception e) {
        	e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    //添加点检任务单次
    @RequestMapping(value = "/addTaskByOne.do", method = RequestMethod.POST)
    public @ResponseBody Object addTaskByOne(HttpServletRequest request, HttpSession httpSession, TaskInput taskInput, @RequestParam(value = "ids[]", required = false) String[] ids) {
        try {
            String user = httpSession.getAttribute("username").toString();
            String factory = httpSession.getAttribute("factory").toString();
            spotTaskService.addTaskByOne(taskInput,user, ids, factory);
            return resultHandler(null);
            
        }catch(TaskDuplicationException e){
        	//自定义异常处理
        	String message = CustomException(e);
        	
    		return resultHandler(message);  
        } catch (Exception e) {
        	e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

	private String CustomException(TaskDuplicationException e) {
		String[] myExceptionString = e.getMessage().split("-");
		String message = "";
		
		if("1".equals(myExceptionString[1])){
			Equip equip = equipService.getByid(Integer.parseInt(myExceptionString[0]));
			message = "设备编号为："+ equip.getEquipNum() + "，设备名称为：" + equip.getEquipName() + "  已存在相同周期点检开始时间为同一天的点检任务";
		}else{
			Mould mould = mouldService.selectMouldById(Integer.parseInt(myExceptionString[0]));
			message = "车种为："+ mould.getVehicleType() + "，图号为：" + mould.getFigureNumber() + "  已存在相同周期点检开始时间为同一天的点检任务";
		}
		return message;
	}

    @RequestMapping(value = "/getTask.do", method = RequestMethod.POST)
    public @ResponseBody Object getTask(HttpServletRequest request, HttpSession httpSession, int spotType, int state, String spotInterval, String beginTime, String endTime,int beginIndex,int pageSize) {
        try {
        	String factory = httpSession.getAttribute("factory").toString();
            return resultHandler(spotTaskService.selectTask(spotType,state,spotInterval,beginTime,endTime,beginIndex,pageSize, factory));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    @RequestMapping(value = "/getTaskCount.do", method = RequestMethod.POST)
    public @ResponseBody Object getTaskCount(HttpServletRequest request, HttpSession httpSession, int spotType, int state, String spotInterval, String beginTime, String endTime) {
        try {
        	String factory = httpSession.getAttribute("factory").toString();
            return resultHandler(spotTaskService.selectTaskCount(spotType,state,spotInterval,beginTime,endTime, factory));
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    public @ResponseBody Object delete(HttpServletRequest request, HttpSession httpSession, int id) {
        try {
        	String userName = httpSession.getAttribute("username").toString();
            spotTaskService.deleteTask(id, userName);
            return resultHandler(null);
        } catch (Exception e) {
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
    
    //查询点检任务日历
    @RequestMapping(value = "/getTaskToCalender.do", method = RequestMethod.GET)
    public @ResponseBody Object getTaskToCalender(HttpServletRequest request, HttpSession httpSession) {
        try {
        	String factory = httpSession.getAttribute("factory").toString();
            Map<String, Object> map = spotTaskService.getTaskToCalender(factory);

        	
            return resultHandler(map);
        } catch (Exception e) {
        	e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }

    //修改任务开始时间
    @RequestMapping(value = "/updateTask.do", method = RequestMethod.POST)
    public @ResponseBody Object getTaskCount(HttpServletRequest request, HttpSession httpSession, int taskId, String beginTime,int deId) {
        String user = httpSession.getAttribute("username").toString();
        String factory = httpSession.getAttribute("factory").toString();
        spotTaskService.updateTask(taskId,beginTime,deId,user, factory);
        return resultHandler(null);
//        try {
//            return resultHandler(spotTaskService.selectTaskCount(spotType,state,spotInterval,beginTime,endTime));
//        } catch (Exception e) {
//            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
//        }
    }
    
    //批量删除
    @RequestMapping(value = "/batchDelete.do", method = RequestMethod.POST)
    public @ResponseBody Object batchDelete(HttpServletRequest request, HttpSession httpSession, String batchDeleteId) {
        try {
        	String userName = httpSession.getAttribute("username").toString();
            spotTaskService.batchDeleteTask(batchDeleteId, userName);
            return resultHandler(null);
        } catch (Exception e) {
        	e.printStackTrace();
            return resultHandler(exceptionHandle(ExceptionCode.ERROR_CODE));
        }
    }
}
