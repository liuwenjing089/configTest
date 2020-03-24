package com.andon.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andon.bean.EquipSpot;
import com.andon.bean.Mould;
import com.andon.bean.SpotCheck;
import com.andon.bean.SpotDetail;
import com.andon.bean.SpotTask;
import com.andon.bean.SpotTaskCalender;
import com.andon.bean.SpotTaskResCalender;
import com.andon.bean.dto.TaskInput;
import com.andon.commons.Constant;
import com.andon.dao.EquipDao;
import com.andon.dao.MouldDao;
import com.andon.dao.SpotCheckDao;
import com.andon.dao.SpotDetailDao;
import com.andon.dao.SpotTaskDao;
import com.andon.exception.TaskDuplicationException;
import com.andon.service.SpotTaskService;
import com.andon.utils.DateUtils;

@Service
@Transactional
public class SpotTaskServiceImpl implements SpotTaskService {
    @Autowired
    private SpotTaskDao spotTaskDao;
    @Autowired
    private SpotDetailDao spotDetailDao;
    @Autowired
    private SpotCheckDao spotCheckDao;
    @Autowired
    private EquipDao equipDao;
    @Autowired
    private MouldDao mouldDao;

    @Override
    public void add(TaskInput taskInputs,String user, String[] ids, String factory) throws Exception{

        List<SpotTask> spotTasks = new ArrayList<SpotTask>();
        int times = taskInputs.getTimes();
        for(int i=0;i<times;i++){
            SpotTask spotTask = new SpotTask();
            spotTask.setSpotName(taskInputs.getSpotName());
            //得到每个顺延任务的开始时间
            String beginTime = getTimeToBegin(taskInputs.getSpotType(), taskInputs.getSpotInterval(),taskInputs.getBeginTime(),i, times, taskInputs.getYear());
            //验证相同设备相同周期的点检开始时间一样的未完成的记录
            spotTaskVerifyByBeginTime(taskInputs, ids, beginTime);

            spotTask.setBeginTime(beginTime);
            spotTask.setEndTime(getTimeToEnd(taskInputs.getSpotType(), taskInputs.getSpotInterval(),taskInputs.getBeginTime(),i+1, times, taskInputs.getYear()));
            spotTask.setSpotInterval(taskInputs.getSpotInterval());
            spotTask.setSpotType(taskInputs.getSpotType());
            spotTask.setCreateUser(user);
            spotTask.setUpdateUser(user);
            spotTask.setCreateTime(DateUtils.getNowDate());
            spotTask.setUpdateTime(DateUtils.getNowDate());
            spotTask.setIsActive(Constant.ACTIVE_VALID);
            spotTask.setState(0);
            spotTask.setFactory(factory);
            spotTasks.add(spotTask);
        }
        
        //插入点检详情表
        if(spotTasks != null || spotTasks.size() > 0){
        	       	       	
            spotTaskDao.insert(spotTasks);
            List<SpotDetail> spotDetailList = new ArrayList<SpotDetail>();
            
            for(SpotTask s: spotTasks){
            	for(int i=0; i<ids.length; i++){
            		SpotDetail spotDetail = new SpotDetail();
            		spotDetail.setTaskId(s.getId());
            		spotDetail.setType(s.getSpotType());
            		spotDetail.setDetailId(Integer.parseInt(ids[i]));
            		spotDetail.setState(Constant.SPOT_STATE_INCOMPLETE);
            		spotDetail.setConfirmState(0);
            		spotDetail.setCreateUser(user);
            		spotDetail.setUpdateUser(user);
            		spotDetail.setCreateTime(DateUtils.getNowDate());
            		spotDetail.setUpdateTime(DateUtils.getNowDate());
            		spotDetail.setIsActive(Constant.ACTIVE_VALID);
            		
            		spotDetailList.add(spotDetail);
            	}
            }
            
            if(spotDetailList != null || spotDetailList.size() > 0){
            	spotDetailDao.insert(spotDetailList);
            }
        }
    }
    
    
    @Override
    public void addTaskByOne(TaskInput taskInputs,String user, String[] ids, String factory) throws Exception{

        List<SpotTask> spotTasks = new ArrayList<SpotTask>();
        int times = taskInputs.getTimes();

        SpotTask spotTask = new SpotTask();
        spotTask.setSpotName(taskInputs.getSpotName());
        //得到每个顺延任务的开始时间
        String beginTime = getTimeToBegin(taskInputs.getSpotType(), taskInputs.getSpotInterval(),taskInputs.getBeginTime(), 0, times, taskInputs.getYear());
        //验证相同设备相同周期的点检开始时间一样的未完成的记录
        spotTaskVerifyByBeginTime(taskInputs, ids, beginTime);

        spotTask.setBeginTime(beginTime);
        spotTask.setEndTime(getTimeToEnd(taskInputs.getSpotType(), taskInputs.getSpotInterval(),taskInputs.getBeginTime(), 1, times, taskInputs.getYear()));
        spotTask.setSpotInterval(taskInputs.getSpotInterval());
        spotTask.setSpotType(taskInputs.getSpotType());
        spotTask.setCreateUser(user);
        spotTask.setUpdateUser(user);
        spotTask.setCreateTime(DateUtils.getNowDate());
        spotTask.setUpdateTime(DateUtils.getNowDate());
        spotTask.setIsActive(Constant.ACTIVE_VALID);
        spotTask.setState(0);
        spotTask.setFactory(factory);
        spotTasks.add(spotTask);

        
        //插入点检详情表
        if(spotTasks != null || spotTasks.size() > 0){
        	       	       	
            spotTaskDao.insert(spotTasks);
            List<SpotDetail> spotDetailList = new ArrayList<SpotDetail>();
            
            for(SpotTask s: spotTasks){
            	for(int i=0; i<ids.length; i++){
            		SpotDetail spotDetail = new SpotDetail();
            		spotDetail.setTaskId(s.getId());
            		spotDetail.setType(s.getSpotType());
            		spotDetail.setDetailId(Integer.parseInt(ids[i]));
            		spotDetail.setState(Constant.SPOT_STATE_INCOMPLETE);
            		spotDetail.setConfirmState(0);
            		spotDetail.setCreateUser(user);
            		spotDetail.setUpdateUser(user);
            		spotDetail.setCreateTime(DateUtils.getNowDate());
            		spotDetail.setUpdateTime(DateUtils.getNowDate());
            		spotDetail.setIsActive(Constant.ACTIVE_VALID);
            		
            		spotDetailList.add(spotDetail);
            	}
            }
            
            if(spotDetailList != null || spotDetailList.size() > 0){
            	spotDetailDao.insert(spotDetailList);
            }
        }
    }

	private void spotTaskVerifyByBeginTime(TaskInput taskInputs, String[] ids, String beginTime) {
		for(int j=0; j<ids.length; j++){
		    //查询是否已经存在相同设备相同周期开始时间为同1天的记录
		    SpotTask spotTaskVerify = new SpotTask();
		    spotTaskVerify.setBeginTime(beginTime);
		    spotTaskVerify.setSpotType(taskInputs.getSpotType());
		    spotTaskVerify.setSpotInterval(taskInputs.getSpotInterval());
		    spotTaskVerify.setState(Constant.SPOT_STATE_INCOMPLETE);
		    spotTaskVerify.setIsActive(Constant.ACTIVE_VALID);
		    spotTaskVerify.setDetailId(Integer.parseInt(ids[j]));
		    int count = spotTaskDao.selectTaskRepeat(spotTaskVerify);
		    if(count > 0){
		    	throw new TaskDuplicationException(ids[j] + "-" + taskInputs.getSpotType());
		    }
		}
	}

    @Override
    public List<SpotTask> selectTask(int spotType, int state, String spotInterval, String beginTime, String endTime,int beginIndex,int pageSize, String factory) {
        List<SpotTask> spotTasks = spotTaskDao.selectTask(spotType,state,spotInterval,beginTime,endTime,beginIndex,pageSize,factory);
        if(spotTasks.size()>0){
            for(SpotTask spotTask:spotTasks){
                //设备
                List<Integer> integerList = spotTaskDao.selectDivName(spotTask.getId(),spotTask.getSpotType());
                if(spotTask.getSpotType()==1){
                    if(integerList.size()>0){
                        List<String> stringList = equipDao.selectBatchName(integerList);
                        spotTask.setDivName(StringUtils.join(stringList.toArray(), ","));
                    }
                }else{
                    //模具
                    if(integerList.size()>0) {
                        Mould mould = mouldDao.selectMouldById(integerList.get(0),Constant.ACTIVE_VALID);
                        if(mould!= null){
                            spotTask.setDivName(mould.getFigureNumber());
                        }
                    }
                }
            }
        }
        return spotTasks;
    }

    @Override
    public int selectTaskCount(int spotType, int state, String spotInterval, String beginTime, String endTime, String factory) {
        return spotTaskDao.selectTaskCount(spotType,state,spotInterval,beginTime,endTime, factory);
    }

    @Override
    public void deleteTask(int id, String userName) {
        List<Integer> integers = new ArrayList<>();
        List<SpotDetail> spotDetails =  spotDetailDao.selectDeleteDetail(id);
        if(spotDetails!=null&&spotDetails.size()>0){
            for(SpotDetail spotDetail:spotDetails){
                integers.add(spotDetail.getId());
             }
            spotCheckDao.deleteCheckList(integers, userName);
        }
        spotDetailDao.deleteTaskDetail(id, userName);
        //删除主表
        spotTaskDao.deleteTask(id, userName);
    }

    //计算开始时间
    private String getTimeToBegin(int type, String interval, String date,int index, int times, int year){
        Date date1 = DateUtils.stringToDate(date,DateUtils.DATE_FORMAT);
        if(type == 1){
            if(interval.equals("1")){
                return DateUtils.dateToString(DateUtils.addMonth(date1,1*index));
            }else if(interval.equals("3")){
                return DateUtils.dateToString(DateUtils.addMonth(date1,3*index));
            } else if(interval.equals("6")){
                return DateUtils.dateToString(DateUtils.addMonth(date1,6*index));
            }else {
                return DateUtils.dateToString(DateUtils.addYear(date1,1*index));
            }
        }else {
        	//计算从开始时间到点检结束日期一共有多少天
        	Date wl = DateUtils.addYear(date1, year);
        	long d = DateUtils.getDayByMinusDate(date1,wl);
        	String startTime = DateUtils.dateToString(DateUtils.addDay(date1, ((int)d/times)*index));
//        	if(index > 0){
//        		startTime = DateUtils.dateToString(DateUtils.addDay(DateUtils.addDay(date1, ((int)d/times)*index), 1));
//        	}
        	
        	return startTime;
        }

    }
    
    //计算结束时间
    private String getTimeToEnd(int type, String interval, String date,int index, int times, int year){
        Date date1 = DateUtils.stringToDate(date,DateUtils.DATE_FORMAT);
        if(type == 1){
            if(interval.equals("1")){
                return DateUtils.dateToString(DateUtils.addDay(DateUtils.addMonth(date1,1*index), -1));
            }else if(interval.equals("3")){
                return DateUtils.dateToString(DateUtils.addDay(DateUtils.addMonth(date1,3*index), -1));
            } else if(interval.equals("6")){
                return DateUtils.dateToString(DateUtils.addDay(DateUtils.addMonth(date1,6*index), -1));
            }else {
                return DateUtils.dateToString(DateUtils.addDay(DateUtils.addYear(date1,1*index), -1));
            }
        }else {
        	
        	//计算从开始时间到点检结束日期一共有多少天
        	Date wl = DateUtils.addYear(date1, year);
        	long d = DateUtils.getDayByMinusDate(date1,wl);
        	String endTime = DateUtils.dateToString(DateUtils.addDay(DateUtils.addDay(date1, ((int)d/times)*index), -1));
//        	if(index > 0){
//        		endTime = DateUtils.dateToString(DateUtils.addDay(date1, ((int)d/times)*index));
//        	}
        	return endTime;
        }

    }
    
    //查询点检任务日历
	@Override
	public Map<String, Object> getTaskToCalender(String factory) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<SpotTaskCalender> spotTaskCalenderList = new ArrayList<SpotTaskCalender>();
		
		List<SpotTaskResCalender> spotTaskResCalenderList = new ArrayList<SpotTaskResCalender>();
		
		SpotTaskResCalender src1 = new SpotTaskResCalender();
		src1.setId("a");
		src1.setTitle("Auditorium A");
		src1.setOccupancy(40);
		
		SpotTaskResCalender src2 = new SpotTaskResCalender();
		src2.setId("b");
		src2.setTitle("Auditorium B");
		src2.setOccupancy(40);
		
		spotTaskResCalenderList.add(src1);
		spotTaskResCalenderList.add(src2);
		
		List<SpotTask> spotTaskList = spotTaskDao.getTaskToCalender(factory, Constant.ACTIVE_VALID);
		if(spotTaskList != null && spotTaskList.size() > 0){
			for(int i=0; i< spotTaskList.size(); i++){
				SpotTask spotTask = spotTaskList.get(i);
				
				SpotTaskCalender spotTaskCalender = new SpotTaskCalender();

				
				spotTaskCalender.setId(String.valueOf(i+1));
				spotTaskCalender.setStart(spotTask.getBeginTime());
				spotTaskCalender.setEnd("");

				if(spotTask.getSpotType() == 1){

					spotTaskCalender.setBackgroundColor("green");
					spotTaskCalender.setTitle("设备");
				}else{

					spotTaskCalender.setBackgroundColor("blue");
					spotTaskCalender.setTitle("模具");
				}

				spotTaskCalenderList.add(spotTaskCalender);

			}
		}
		map.put("dealerCalenders", spotTaskResCalenderList);
		map.put("scheduleCalendars", spotTaskCalenderList);
		
		return map;
	}

    @Override
    public void updateTask(int taskId, String beginTime,int deId,String user, String factory) {
        List<SpotDetail> spotDetails = spotDetailDao.selectSpotDetailByTaskId(taskId);
        Date date1 = DateUtils.stringToDate(beginTime,DateUtils.DATE_FORMAT);
        String endTime = "";
        SpotTask spotTask = spotTaskDao.selectTaskById(taskId);
        String interval = spotTask.getSpotInterval();
        if(spotDetails.size()==1 ||spotTask.getSpotType()==2){
            //直接更新开始结束时间
            if(interval.equals("1")){
                endTime = DateUtils.dateToString(DateUtils.addDay(DateUtils.addMonth(date1,1), -1));
            }else if(interval.equals("3")){
                endTime = DateUtils.dateToString(DateUtils.addDay(DateUtils.addMonth(date1,3), -1));
            } else if(interval.equals("6")){
                endTime = DateUtils.dateToString(DateUtils.addDay(DateUtils.addMonth(date1,6), -1));
            }else {
                int year = DateUtils.getYearByMinusDate(DateUtils.stringToDate(spotTask.getBeginTime(),DateUtils.DATE_FORMAT),DateUtils.stringToDate(spotTask.getEndTime(),DateUtils.DATE_FORMAT));
                endTime = DateUtils.dateToString(DateUtils.addDay(DateUtils.addYear(date1,year), -1));
            }
            spotTaskDao.updateTaskTime(taskId,beginTime,endTime);

        }else if(spotDetails.size()>1){
            //新增一条更改后的任务 原任务保持不变
            List<SpotTask> spotTasks = new ArrayList<SpotTask>();
            SpotTask task = new SpotTask();
            task.setSpotName("");
            task.setBeginTime(beginTime);
            if(interval.equals("1")){
                endTime = DateUtils.dateToString(DateUtils.addDay(DateUtils.addMonth(date1,1), -1));
            }else if(interval.equals("3")){
                endTime = DateUtils.dateToString(DateUtils.addDay(DateUtils.addMonth(date1,3), -1));
            } else if(interval.equals("6")){
                endTime = DateUtils.dateToString(DateUtils.addDay(DateUtils.addMonth(date1,6), -1));
            }else {
                int year = DateUtils.getYearByMinusDate(DateUtils.stringToDate(spotTask.getBeginTime(),DateUtils.DATE_FORMAT),DateUtils.stringToDate(spotTask.getEndTime(),DateUtils.DATE_FORMAT));
                endTime = DateUtils.dateToString(DateUtils.addDay(DateUtils.addYear(date1,year), -1));
            }
            task.setEndTime(endTime);
            task.setSpotInterval(spotTask.getSpotInterval());
            task.setSpotType(spotTask.getSpotType());
            task.setCreateUser(user);
            task.setUpdateUser(user);
            task.setFactory(factory);
            task.setCreateTime(DateUtils.getNowDate());
            task.setUpdateTime(DateUtils.getNowDate());
            task.setIsActive(Constant.ACTIVE_VALID);
            task.setState(0);
            spotTasks.add(task);
            spotTaskDao.insert(spotTasks);

            //更新该条的taskId
            SpotDetail spotDetail = new SpotDetail();
            spotDetail.setId(deId);
            spotDetail.setTaskId(task.getId());
            spotDetail.setUpdateUser(user);
            spotDetail.setUpdateTime(DateUtils.getNowDate());
            spotDetailDao.updateDetailTaskId(spotDetail);
        }
    }

    //验证要点检的设备是否都存在指定周期的规则
	@Override
	public Map<String, Object> checkSpot(int spotType, int spotInterval, String[] ids) throws Exception{
		
		//根据选择的id到设备点检视图中筛选符合条件的记录
		List<EquipSpot> equipSpotList = spotTaskDao.checkSpot(spotInterval, ids);
		
		//定义返回结果
		List<EquipSpot> result = null;
		
		List<String> allIds  = new ArrayList<String>();
		for(int i=0; i<ids.length; i++){
			allIds.add(ids[i]);
		}

		if(equipSpotList == null || equipSpotList.size() == 0){
			
			result = equipDao.selectDetailByIds(allIds);
			
		}else{
		
			List<String> existingIds = new ArrayList<String>();
			for(EquipSpot e : equipSpotList){
				
				existingIds.add(e.getId());
				
			}
			
            allIds.removeAll(existingIds);
            if(!(allIds == null || allIds.size() == 0)){
            	result = equipDao.selectDetailByIds(allIds);
            }
            
		}
		
		//resutl返回不符合条件设备记录的详情，如果为null是合理状态，如果有数据则将数据返回给前台显示
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(result == null){
			map.put("code", true);
			
		}else{
			map.put("code", false);
			map.put("equipList", result);
		}
		return map;
	}
	
    //批量删除
	@Override
	public void batchDeleteTask(String batchDeleteId, String userName) throws Exception {
		String[] taskIds = batchDeleteId.split(",");
		
		//批量查询出要删除的soptDetail表的记录
		List<SpotDetail> spotDetailsList = spotDetailDao.selectDetailsByTaskIds(taskIds);
		
		List<Integer> integers = new ArrayList<>();
        if(spotDetailsList != null && spotDetailsList.size() > 0){
            for(SpotDetail spotDetail: spotDetailsList){
                integers.add(spotDetail.getId());
             }
            //批量删除check表
            spotCheckDao.deleteCheckList(integers, userName);
        }
        
        //根据spotTask的ids批量删除spot_detail表
        spotDetailDao.batchDeleteTaskDetail(taskIds, userName);
        
        //批量删除task主表
        spotTaskDao.batchDeleteTask(taskIds, userName);
		
		
	}
}
