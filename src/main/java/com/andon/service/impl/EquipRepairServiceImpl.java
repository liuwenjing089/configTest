package com.andon.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.andon.bean.EquipRepair;
import com.andon.bean.Picture;
import com.andon.bean.Repair;
import com.andon.bean.dto.EquipRepairOutput;
import com.andon.bean.dto.UpdateEquipRepair;
import com.andon.commons.Constant;
import com.andon.dao.EquipRepairDao;
import com.andon.dao.PictureDao;
import com.andon.dao.RepairDao;
import com.andon.service.EquipRepairService;
import com.andon.utils.DateUtils;
import com.andon.utils.UUIDString;

@Service
@Transactional
public class EquipRepairServiceImpl implements EquipRepairService {
    @Autowired
    private RepairDao repairDao;
	
    @Autowired
    private EquipRepairDao equipRepairDao;
    
    @Autowired
    private PictureDao pictureDao;
    
    
    @Override
    public void add(EquipRepair equipRepair) {
        equipRepairDao.insert(equipRepair);
    }

    @Override
    public void update(UpdateEquipRepair equipRepair) {
		List<Picture> pictureList = new ArrayList<Picture>();
    	
		if(!StringUtils.isBlank(equipRepair.getLocationUrl())){
			String faultLocationUrls[] = equipRepair.getLocationUrl().split(",");
			//上传到图片表并返回图片uuid集合
			String faultLocationUuids = pictureToUuid(faultLocationUrls, pictureList);
			equipRepair.setLocationUrl(faultLocationUuids);
		}
		//插入图片表
		if(pictureList.size() > 0){
			pictureDao.insert(pictureList);
		}
    	
        equipRepairDao.update(equipRepair);
    }
    
	//存picture表并获取图片uuid
	private String pictureToUuid(String[] urls, List<Picture> pictureList) {
		String prictureUUIDs = "";

		for(String pictureUrl:  urls){
			Picture picture = new Picture();
			String pictureUUID = UUIDString.getPartsKeyByUUId();
			if(prictureUUIDs == ""){
				prictureUUIDs += pictureUUID; 
			}else{
				prictureUUIDs += "," + pictureUUID; 
			}
			
			picture.setUuid(pictureUUID);
			picture.setUrl(pictureUrl);
            //获取图片名称				
			String pictureName = getPictureName(pictureUrl);
			picture.setPictureName(pictureName);
			
			pictureList.add(picture);
		}
		return prictureUUIDs;
	}
	
	//获取图片名称
	private String getPictureName(String pictureUrl) {
		int index = pictureUrl.lastIndexOf("/");  
		String pictureName  = pictureUrl .substring(index + 1, pictureUrl.length());
		return pictureName;
	}

    @Override
    public EquipRepairOutput getByid(int id) {
        return equipRepairDao.selectEquipRepair(id);
    }

    //修改设备维修表确认状态
	@Override
	public void updateDetailState(UpdateEquipRepair updateEquipRepair) {
	    Repair repair = new Repair();
	    repair.setId(updateEquipRepair.getId());
	    repair.setState(updateEquipRepair.getState());
        //更新状态班长系长科长确认时间
        if(repair.getState()==4){
            repair.setConfirmationTime(DateUtils.getCurrentDateMinute());
        }else if(repair.getState()==5){
            repair.setPreDepChiefConTime(DateUtils.getCurrentDateMinute());
        }else if(repair.getState()==6){
            repair.setPreSecChiefConTime(DateUtils.getCurrentDateMinute());
        }
	    repair.setUpdateTime(updateEquipRepair.getUpdateTime());
	    repair.setUpdateUser(updateEquipRepair.getUpdateUser());
	    repair.setIsActive(Constant.ACTIVE_VALID);
	    repairDao.update(repair);
		
        equipRepairDao.updateDetailState(updateEquipRepair);	
	}
}
