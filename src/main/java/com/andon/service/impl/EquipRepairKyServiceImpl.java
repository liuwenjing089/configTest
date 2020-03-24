package com.andon.service.impl;

import com.andon.bean.EquipRepairKy;
import com.andon.bean.dto.WorkNameOutput;
import com.andon.dao.EquipRepairKyDao;
import com.andon.service.EquipRepairKyService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EquipRepairKyServiceImpl implements EquipRepairKyService {
    @Autowired
    private EquipRepairKyDao equipRepairKyDao;

    //添加kycheckList
    @Override
    public void add(EquipRepairKy equipRepairKy) throws Exception{
        equipRepairKyDao.insert(equipRepairKy);
    }

    //获取ky作业名  生产线名称-设备名称-故障现象
    @Override
    public WorkNameOutput selectWorkName(int id) {
        return equipRepairKyDao.selectWorkName(id);
    }
    //获取ky详情
	@Override
	public Map<String, Object> getKyDetail (int id)throws Exception {
		Map<String, Object> map = equipRepairKyDao.getKyDetail(id);
		return map;
	}

	//修改ky
	@Override
	public void update(EquipRepairKy equipRepairKy) throws Exception {
		equipRepairKyDao.update(equipRepairKy);
		
	}
}
