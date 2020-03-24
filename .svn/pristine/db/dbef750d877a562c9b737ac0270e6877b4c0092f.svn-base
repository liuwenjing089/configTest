package com.andon.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andon.bean.MouldTaskTime;
import com.andon.bean.Repair;
import com.andon.bean.RepairPrevention;
import com.andon.bean.dto.RepairMouldState;
import com.andon.commons.Constant;
import com.andon.dao.MouldTaskTimeDao;
import com.andon.dao.RepairDao;
import com.andon.dao.TestMouldDao;
import com.andon.service.MouldTaskTimeService;
import com.andon.utils.DateUtils;

@Service
@Transactional
public class MouldTaskTimeServiceImpl implements MouldTaskTimeService {

    @Autowired
    private MouldTaskTimeDao mouldTaskTimeDao;
    
    @Autowired
    private RepairDao repairDao;
    
    @Autowired
    private TestMouldDao testMouldDao;

	@Override
	public void insert(MouldTaskTime mouldTaskTime) throws Exception{
		//查询该维修记录中，是否存在只有开始时间,没有结束时间的记录
		MouldTaskTime result = mouldTaskTimeDao.selectMouldTaskTime(mouldTaskTime);
		mouldTaskTime.setCreateTime(DateUtils.getNowDate());
		mouldTaskTime.setUpdateTime(DateUtils.getNowDate());
		//如果没有新插一个条数据，如果有则更新该记录
		if(result == null){
					
			mouldTaskTime.setTaskBeginTime(DateUtils.getCurrentDateMinute());
			mouldTaskTime.setTaskEndTime("");
			mouldTaskTimeDao.insert(mouldTaskTime);
		}else{
			mouldTaskTime.setUuid(result.getUuid());
			mouldTaskTime.setTaskEndTime(DateUtils.getCurrentDateMinute());
			mouldTaskTimeDao.update(mouldTaskTime);
			
		}

	}

	@Override
	public void update(MouldTaskTime mouldTaskTime)throws Exception {
		mouldTaskTimeDao.update(mouldTaskTime);
		
	}

	@Override
	public MouldTaskTime selectMouldTaskTime(MouldTaskTime mouldTaskTime) throws Exception{
		MouldTaskTime result = mouldTaskTimeDao.selectMouldTaskTime(mouldTaskTime);
		return result;
	}

	@Override
	public int selectCount(MouldTaskTime mouldTaskTime) throws Exception{
		int count = mouldTaskTimeDao.selectCount(mouldTaskTime);
		return count;
	}

	//查询该报修记录的各种状态用于在页面上展示不同的按钮形态
	@Override
	public int selectMouldTaskTimeStauts(MouldTaskTime mouldTaskTime) throws Exception{
		int cssNum = 0;
		
		//查询该报修记录中维修状态
		Repair repair = repairDao.selectRepairDetail(Integer.valueOf(mouldTaskTime.getReId()), Constant.ACTIVE_VALID);
		int repairState = repair.getState();
		//查询该报修的开始结束条目数
		int mouldTaskTimeCount = mouldTaskTimeDao.selectCount(mouldTaskTime);
		//查询该报修的开始结束条目中有无作业完成时间为空的记录
		MouldTaskTime mouldTaskTimeResult = mouldTaskTimeDao.selectMouldTaskTime(mouldTaskTime);
		//查询该报修的试模记录次数和试模结果
		int testMouldCount = testMouldDao.getTestMouldCount(Integer.valueOf(mouldTaskTime.getReId()), Constant.ACTIVE_VALID);
		
		int testMouldToSucceed = testMouldDao.selectTestMouldToSucceedById(mouldTaskTime.getReId(), Constant.ACTIVE_VALID);
		
		
		//逻辑判断
		if(repairState == 1){
			//1.repair表的state 为1 的时候
			cssNum = Constant.MOULD_REPAIR_CSS_6;
		}else if(repairState == 2 && testMouldToSucceed >= 1){
			//2.repair表的state 为2 的时候, 试模记录中有成功记录时
			cssNum = Constant.MOULD_REPAIR_CSS_4;
		}else if(repairState == 3 || repairState == 4 || repairState == 7){	
		
			//3.repair表state 为3,4,7的时候
			cssNum = Constant.MOULD_REPAIR_CSS_5;
			
		}else if(repairState == 2 && mouldTaskTimeCount == 0){
			//4.repair表的state 为2 的时候, mould_task_time的记录为0时
			cssNum = Constant.MOULD_REPAIR_CSS_1;
			
		}else if(repairState == 2 && mouldTaskTimeCount == 1 && mouldTaskTimeResult != null){
			//5.repair表的state 为2 的时候, mould_task_time的记录为1时, 且作业完成时间为空时
			cssNum = Constant.MOULD_REPAIR_CSS_2;
			
		}else if(repairState == 2 && mouldTaskTimeCount == 1 && mouldTaskTimeResult == null && testMouldCount == 0){
			//6.repair表的state 为2 的时候, mould_task_time的记录为1时 , 记录都完整,且试模记录为0时
			cssNum = Constant.MOULD_REPAIR_CSS_3;
			
		}else if(repairState == 2 && mouldTaskTimeCount == 1 && mouldTaskTimeResult == null && testMouldCount != 0 && testMouldToSucceed == 0){
			//7.repair表的state 为2 的时候, mould_task_time的记录为1时 , 记录都完整,    且试模记录为大于等于1时同时没有成功记录
			cssNum = Constant.MOULD_REPAIR_CSS_3;
			
		}else if(repairState == 2 && mouldTaskTimeCount > 1  && mouldTaskTimeResult != null){
			//8.repair表的state 为2 的时候, mould_task_time的记录大于1时 , 且存在作业完成时间为空的记录时 
			cssNum = Constant.MOULD_REPAIR_CSS_2;
		}else if(repairState == 2 && mouldTaskTimeCount > 1 && mouldTaskTimeResult == null ){			
			//9.repair表的state 为2 的时候, mould_task_time的记录大于1时 , 记录完整
			cssNum = Constant.MOULD_REPAIR_CSS_3;
		}
		
		return cssNum;
	}

	@Override
	public List<Repair> addMouldRepaitCss(List<Repair> getRepair) throws Exception{
		List<Integer> mouldRepairIds = new ArrayList<Integer>();
				
		for(Repair p: getRepair){
			if(p.getType() == 2){
				mouldRepairIds.add(p.getId());
			}			
		}
		
		if(mouldRepairIds != null && mouldRepairIds.size() > 0){
			//查询这些模具的各种状态
			List<RepairMouldState> cssStateList = new ArrayList<RepairMouldState>();
			cssStateList = mouldTaskTimeDao.selectRepairMouldState(mouldRepairIds);
			
			for(RepairMouldState r : cssStateList){
				int cssNum = 0;
				
				int repairState = r.getState();
				//查询该报修的开始结束条目数
				int mouldTaskTimeCount = r.getMouldTaskTimeCount();
				//查询该报修的开始结束条目中有无作业完成时间为空的记录
				int mouldTaskTimeResult = r.getMouldTaskTimeResult();
				//查询该报修的试模记录次数和试模结果
				int testMouldCount = r.getTestMouldCount();
				
				int testMouldToSucceed = r.getTestMouldToSucceed();
				
				
				//逻辑判断
				if(repairState == 1){
					//1.repair表的state 为1 的时候
					cssNum = Constant.MOULD_REPAIR_CSS_6;
				}else if(repairState == 2 && testMouldToSucceed >= 1){
					//2.repair表的state 为2 的时候, 试模记录中有成功记录时
					cssNum = Constant.MOULD_REPAIR_CSS_4;
				}else if(repairState == 3 || repairState == 4 || repairState == 7){	
				
					//3.repair表state 为3,4,7的时候
					cssNum = Constant.MOULD_REPAIR_CSS_5;
					
				}else if(repairState == 2 && mouldTaskTimeCount == 0){
					//4.repair表的state 为2 的时候, mould_task_time的记录为0时
					cssNum = Constant.MOULD_REPAIR_CSS_1;
					
				}else if(repairState == 2 && mouldTaskTimeCount == 1 && mouldTaskTimeResult > 0){
					//5.repair表的state 为2 的时候, mould_task_time的记录为1时, 且作业完成时间为空时
					cssNum = Constant.MOULD_REPAIR_CSS_2;
					
				}else if(repairState == 2 && mouldTaskTimeCount == 1 && mouldTaskTimeResult == 0 && testMouldCount == 0){
					//6.repair表的state 为2 的时候, mould_task_time的记录为1时 , 记录都完整,且试模记录为0时
					cssNum = Constant.MOULD_REPAIR_CSS_3;
					
				}else if(repairState == 2 && mouldTaskTimeCount == 1 && mouldTaskTimeResult == 0 && testMouldCount != 0 && testMouldToSucceed == 0){
					//7.repair表的state 为2 的时候, mould_task_time的记录为1时 , 记录都完整,    且试模记录为大于等于1时同时没有成功记录
					cssNum = Constant.MOULD_REPAIR_CSS_3;
					
				}else if(repairState == 2 && mouldTaskTimeCount > 1  && mouldTaskTimeResult > 0){
					//8.repair表的state 为2 的时候, mould_task_time的记录大于1时 , 且存在作业完成时间为空的记录时 
					cssNum = Constant.MOULD_REPAIR_CSS_2;
				}else if(repairState == 2 && mouldTaskTimeCount > 1 && mouldTaskTimeResult == 0 ){			
					//9.repair表的state 为2 的时候, mould_task_time的记录大于1时 , 记录完整
					cssNum = Constant.MOULD_REPAIR_CSS_3;
				}
				
				r.setCssNum(cssNum);
			}
			
			for(Repair p: getRepair){
				if(p.getType() == 2){
					for(RepairMouldState r: cssStateList){
						if(p.getId() == r.getId()){
							p.setMouldCssNum(r.getCssNum());
						}
					}
				}			
			}
			
		}

		
		
		return getRepair;
	}

	@Override
	public List<RepairPrevention> addMouldRepaitPreventionCss(List<RepairPrevention> getRepair) throws Exception {
		List<String> mouldRepairIds = new ArrayList<String>();
		
		for(RepairPrevention p: getRepair){
			if(p.getType() == 2){
				mouldRepairIds.add(p.getUuid());
			}			
		}
		
		//查询这些模具的各种状态
		List<RepairMouldState> cssStateList = new ArrayList<RepairMouldState>();
		cssStateList = mouldTaskTimeDao.selectRepairPreventionMouldState(mouldRepairIds);
		
		for(RepairMouldState r : cssStateList){
			int cssNum = 0;
			
			int repairState = r.getState();
			//查询该报修的开始结束条目数
			int mouldTaskTimeCount = r.getMouldTaskTimeCount();
			//查询该报修的开始结束条目中有无作业完成时间为空的记录
			int mouldTaskTimeResult = r.getMouldTaskTimeResult();
			//查询该报修的试模记录次数和试模结果
			int testMouldCount = r.getTestMouldCount();
			
			int testMouldToSucceed = r.getTestMouldToSucceed();
			
			
			//逻辑判断
			if(repairState == 1){
				//1.repair表的state 为1 的时候
				cssNum = Constant.MOULD_REPAIR_CSS_6;
			}else if(repairState == 2 && testMouldToSucceed >= 1){
				//2.repair表的state 为2 的时候, 试模记录中有成功记录时
				cssNum = Constant.MOULD_REPAIR_CSS_4;
			}else if(repairState == 3 || repairState == 4 || repairState == 7){	
			
				//3.repair表state 为3,4,7的时候
				cssNum = Constant.MOULD_REPAIR_CSS_5;
				
			}else if(repairState == 2 && mouldTaskTimeCount == 0){
				//4.repair表的state 为2 的时候, mould_task_time的记录为0时
				cssNum = Constant.MOULD_REPAIR_CSS_1;
				
			}else if(repairState == 2 && mouldTaskTimeCount == 1 && mouldTaskTimeResult > 0){
				//5.repair表的state 为2 的时候, mould_task_time的记录为1时, 且作业完成时间为空时
				cssNum = Constant.MOULD_REPAIR_CSS_2;
				
			}else if(repairState == 2 && mouldTaskTimeCount == 1 && mouldTaskTimeResult == 0 && testMouldCount == 0){
				//6.repair表的state 为2 的时候, mould_task_time的记录为1时 , 记录都完整,且试模记录为0时
				cssNum = Constant.MOULD_REPAIR_CSS_3;
				
			}else if(repairState == 2 && mouldTaskTimeCount == 1 && mouldTaskTimeResult == 0 && testMouldCount != 0 && testMouldToSucceed == 0){
				//7.repair表的state 为2 的时候, mould_task_time的记录为1时 , 记录都完整,    且试模记录为大于等于1时同时没有成功记录
				cssNum = Constant.MOULD_REPAIR_CSS_3;
				
			}else if(repairState == 2 && mouldTaskTimeCount > 1  && mouldTaskTimeResult > 0){
				//8.repair表的state 为2 的时候, mould_task_time的记录大于1时 , 且存在作业完成时间为空的记录时 
				cssNum = Constant.MOULD_REPAIR_CSS_2;
			}else if(repairState == 2 && mouldTaskTimeCount > 1 && mouldTaskTimeResult == 0 ){			
				//9.repair表的state 为2 的时候, mould_task_time的记录大于1时 , 记录完整
				cssNum = Constant.MOULD_REPAIR_CSS_3;
			}
			
			r.setCssNum(cssNum);
		}
		
		for(RepairPrevention p: getRepair){
			if(p.getType() == 2){
				for(RepairMouldState r: cssStateList){
					if(p.getUuid().equals(r.getUuid())){
						p.setMouldCssNum(r.getCssNum());
					}
				}
			}			
		}
		
		
		return getRepair;
	}


}
