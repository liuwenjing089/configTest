package com.andon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andon.bean.MouldRepairKy;
import com.andon.bean.Repair;
import com.andon.commons.Constant;
import com.andon.dao.MouldRepairKyDao;
import com.andon.dao.RepairDao;
import com.andon.service.MouldRepairKyService;
import com.andon.utils.DateUtils;

@Service
@Transactional
public class MouldRepairKyServiceImpl implements MouldRepairKyService {
    @Autowired
    private MouldRepairKyDao mouldRepairKyDao;
    @Autowired
    private RepairDao repairDao;

	public void add(MouldRepairKy mouldRepairKy) throws Exception {
		
		//插入ky表记录
		mouldRepairKyDao.insert(mouldRepairKy);
		
		//更新模具维修表，插入维修开始时间
		Repair repair = new Repair();
		repair.setId(mouldRepairKy.getReId());
		repair.setState(Constant.STATE_IS_NULL);
		repair.setBeginTime(DateUtils.getCurrentDateMinute());
        repair.setUpdateTime(DateUtils.getNowDate());
        repair.setUpdateUser(mouldRepairKy.getUpdateUser());
		repair.setIsActive(Constant.ACTIVE_VALID);
		repairDao.update(repair);
		
	}
}
