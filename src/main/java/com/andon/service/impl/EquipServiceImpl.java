package com.andon.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andon.bean.Equip;
import com.andon.bean.dto.EquipSeeOutput;
import com.andon.commons.Constant;
import com.andon.dao.EquipDao;
import com.andon.service.EquipService;

@Service
@Transactional
public class EquipServiceImpl implements EquipService {
    @Autowired
    private EquipDao equipDao;

    //添加设备
    @Override
    public void add(Equip equip) {
        equipDao.insert(equip);
    }

    //设备一览
    @Override
    public List<Equip> getEquip(int beginIndex, int pageSize) {
    	List<Equip> equipList = equipDao.selectEquip(beginIndex,pageSize);
    	factoryToString(equipList);
        return equipList;
    }

    //分页用
    @Override
    public int getEquipCount() {
        return equipDao.selectEquipCount();
    }

    //检索
    @Override
    public List<Equip> getEquipByFirm(int factory,String equipName, String equipDescription, String useBeginTime, String equipModel, String standard, int beginIndex, int pageSize) {
    	List<Equip> equipList = equipDao.selectEquipByFirm(factory,equipName,equipDescription,useBeginTime,equipModel,standard,beginIndex,pageSize);
    	factoryToString(equipList);
        return equipList;
    }

    @Override
    public int getEquipByFirmCount(int factory,String equipName, String equipDescription, String useBeginTime, String equipModel, String standard) {
        return equipDao.selectEquipByFirmCount(factory,equipName,equipDescription,useBeginTime,equipModel,standard);
    }

    @Override
    public Equip getByid(int id) {
        return equipDao.selectEquipByid(id);
    }

    @Override
    public void update(Equip equip) throws Exception{
        equipDao.update(equip);
    }
    @Override
    public void updateEqLine(int id, int lineId) {
        equipDao.updateEqLine(id,lineId);
    }

    @Override
    public void deleteEq(int id) {
        equipDao.deleteEq(id);
    }

    @Override
    public List<Equip> getEqBylineID(int lineId) {
        return equipDao.selectEqBylineID(lineId);
    }

    @Override
    public void deleteEqLine(List<Equip> equip) {
        equipDao.deleteEqLine(equip);
    }

    @Override
    public EquipSeeOutput getEquipSee(int id) {
        return equipDao.selectEquipSee(id);
    }
    
    private List<Equip> factoryToString(List<Equip> equipList){
    	for(Equip b: equipList){
    		switch (b.getFactory()) {
			case Constant.MANUFACTURER_ID_1:
				b.setFactoryName(Constant.MANUFACTURER_NAME_1);
				break;
			case Constant.MANUFACTURER_ID_2:
				b.setFactoryName(Constant.MANUFACTURER_NAME_2);
				break;
			case Constant.MANUFACTURER_ID_3:
				b.setFactoryName(Constant.MANUFACTURER_NAME_3);
				break;
			default: b.setFactoryName("");
				break;
    		}
    	}
    	
    	return equipList;
    }
}
