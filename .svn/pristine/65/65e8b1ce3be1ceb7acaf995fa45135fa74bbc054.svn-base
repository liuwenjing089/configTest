package com.andon.service.impl;

import com.andon.bean.SpotDetail;
import com.andon.bean.dto.SpotDetailEquip;
import com.andon.bean.dto.SpotDetailMould;
import com.andon.dao.SpotDetailDao;
import com.andon.dao.SpotTaskDao;
import com.andon.service.SpotDetailService;
import com.andon.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import java.util.List;

@Service
@Transactional
public class SpotDetailServiceImpl implements SpotDetailService {

    @Autowired
    private SpotDetailDao spotDetailDao;
    @Autowired
    private SpotTaskDao spotTaskDao;
    @Override
    public List<SpotDetailMould> getSpotDetailMould(SpotDetailMould mould) {
        return spotDetailDao.selectSpotDetailMould(mould);
    }

    @Override
    public int getSpotDetailMouldCount(SpotDetailMould mould) {
        return spotDetailDao.selectSpotDetailMouldCount(mould);
    }

    @Override
    public List<SpotDetailEquip> getSpotDetailEquip(SpotDetailEquip equip) {
        return spotDetailDao.selectSpotDetailEquip(equip);
    }

    @Override
    public int getSpotDetailEquipCount(SpotDetailEquip equip) {
        return spotDetailDao.selectSpotDetailEquipCount(equip);
    }

    @Override
    public void updateState(int id,String spotMan) {
        spotDetailDao.updateState(id,spotMan);
    }

    @Override
    public void updateConfirmState(SpotDetail spotDetail) {
        if(spotDetail.getConfirmCommander()!= null){
            spotDetail.setConfirmCommanderTime(DateUtils.getCurrentDateTime());
        }
        if(spotDetail.getConfirmChief()!= null){
            spotDetail.setConfirmChiefTime(DateUtils.getCurrentDateTime());
        }
        if(spotDetail.getClassConfirmMan()!= null){
            spotDetail.setClassConfirmTime(DateUtils.getCurrentDateTime());
        }
        spotDetail.setUpdateTime(DateUtils.getNowDate());
        spotDetailDao.updateConfirmState(spotDetail);

        //检索所有点检任务 若都已确认完 则更改主表状态
        int taskId = spotDetailDao.selectSpotDetailById(spotDetail.getId()).getTaskId();
        List<SpotDetail> spotDetails = spotDetailDao.selectSpotDetailByTaskId(taskId);
        if(spotDetails !=null && spotDetails.size()>0){
            boolean flag = true;
            for(SpotDetail sd:spotDetails){
            	//设备时检查保全系长确认，模具时检查现场班长确认
            	if(sd.getType() == 1){
	                if(StringUtils.isBlank(sd.getConfirmCommander())){
	                    flag = false;
	                }
            	}else{
	
	                if(StringUtils.isBlank(sd.getClassConfirmMan())){
	                    flag = false;
	                }
            	}
            		

            }
            //更新主表
            if(flag){
                spotTaskDao.updateState(taskId);
            }
        }
    }

    @Override
    public SpotDetail getSpotDetailById(int id) {
        return spotDetailDao.selectSpotDetailById(id);
    }

    @Override
    public List<SpotDetailMould> getDateSpotDetailMould(SpotDetailMould mould) {
        return spotDetailDao.selectDateSpotDetailMould(mould);
    }

    @Override
    public int getDateSpotDetailMouldCount(SpotDetailMould mould) {
        return spotDetailDao.selectDateSpotDetailMouldCount(mould);
    }

    @Override
    public List<SpotDetailEquip> getDateSpotDetailEquip(SpotDetailEquip equip) {
        return spotDetailDao.selectDateSpotDetailEquip(equip);
    }

    @Override
    public int getDateSpotDetailEquipCount(SpotDetailEquip equip) {
        return spotDetailDao.selectDateSpotDetailEquipCount(equip);
    }
}
