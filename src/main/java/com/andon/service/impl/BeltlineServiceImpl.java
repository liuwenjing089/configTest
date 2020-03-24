package com.andon.service.impl;

import com.andon.bean.Beltline;
import com.andon.commons.Constant;
import com.andon.dao.BeltlineDao;
import com.andon.service.BeltlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BeltlineServiceImpl implements BeltlineService {
    @Autowired
    private BeltlineDao beltlineDao;

    @Override
    public void add(Beltline beltline) {
        beltlineDao.insert(beltline);
    }

    @Override
    public List<Beltline> getLine(int beginIndex, int pageSize, String factory) {
    	List<Beltline> beltlineList = beltlineDao.selectLine(beginIndex,pageSize, factory);
    	beltlineList = factoryToString(beltlineList);
        return beltlineList;
    }

    @Override
    public int getLineCount(String factory) {
        return beltlineDao.selectLineCount(factory);
    }

    @Override
    public List<Beltline> getLineByFirm(int lineType, String beltlineName, String beltlineDescription, int beginIndex, int pageSize, String factory) {
    	List<Beltline> beltlineList = beltlineDao.selectLineByFirm(lineType,beltlineName,beltlineDescription,beginIndex,pageSize, factory);
    	beltlineList = factoryToString(beltlineList);
        return beltlineList;
    }

    @Override
    public int getLineByFirmCount(int lineType, String beltlineName, String beltlineDescription, String factory) {
        return beltlineDao.selectLineByFirmCount(lineType, beltlineName,beltlineDescription, factory);
    }

    @Override
    public void updateLine(int id, int factory, String beltlineName, String beltlineDescription, String updateUser, Date updateTime,int lineType) {
        beltlineDao.updateLine(id, factory, beltlineName,beltlineDescription,updateUser,updateTime,lineType);
    }

    @Override
    public void deleteLine(int id) {
        beltlineDao.deleteLine(id);
    }

    @Override
    public Beltline getLineByid(int id) {
    	Beltline beltline = beltlineDao.selectLineByid(id);
    	
        return beltline;
    }

    //查询模具的成型机
	@Override
	public List<Beltline> selectLineByMould() {
		//2在生产线表中代表成型机
		List<Beltline> beltlineList = beltlineDao.selectLineByMould(Constant.ACTIVE_VALID, 2);
		factoryToString(beltlineList);
		
		return beltlineList;
	}

    @Override
    public List<Beltline> selectEquipLine(String factory) throws Exception {
        return beltlineDao.selectEquipLine(factory);
    }
    
    private List<Beltline> factoryToString(List<Beltline> beltlineList){
    	for(Beltline b: beltlineList){
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
    	
    	return beltlineList;
    }
}
