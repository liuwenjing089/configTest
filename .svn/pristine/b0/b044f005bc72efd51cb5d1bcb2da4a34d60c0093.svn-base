package com.andon.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andon.bean.CodeList;
import com.andon.bean.Mould;
import com.andon.bean.MouldFaultParts;
import com.andon.bean.dto.MouldRepairHistory;
import com.andon.commons.Constant;
import com.andon.dao.CodeListDao;
import com.andon.dao.MouldDao;
import com.andon.dao.MouldFaultPartsDao;
import com.andon.service.MouldService;
import com.andon.utils.DateUtils;

@Service
@Transactional
public class MouldServiceImpl implements MouldService {
    @Autowired
    private MouldDao mouldDao;
    
    @Autowired
    private CodeListDao codeListDao;
    
    @Autowired
    private MouldFaultPartsDao mouldFaultPartsDao;

	/**
	 * 根据条件检索模具表数量
	 * @param Mould
	 * @return int
	 */
	@Override
	public int getMouldCount(Mould mould) throws Exception{
		mould.setIsActive(Constant.ACTIVE_VALID);
		return mouldDao.getMouldCount(mould);
	}

	/**
	 * 根据条件检索模具表列表
	 * @param Mould 
	 * @return List
	 */	
	@Override
	public List<Mould> getMouldList(Mould mould) throws Exception{
		mould.setIsActive(Constant.ACTIVE_VALID);
		
		List<Mould> mouldList = mouldDao.getMouldList(mould);
		//List<Mould> mouldList = mouldDao.getMouldListNew(mould);
		
    	for(Mould b: mouldList){
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
		return mouldList;
	}

	/**
	 * 新建模具
	 * 
	 * @param Mould
	 */
	@Override
	public void insertMould(Mould mould) {
		mould.setCreateTime(DateUtils.getNowDate());
		mould.setUpdateTime(DateUtils.getNowDate());
		mould.setIsActive(Constant.ACTIVE_VALID);
		mouldDao.insertMould(mould);
		
	}

	/**
	 * 效验名是否重复
	 * 
	 * @param Mould
	 */
	@Override
	public Integer checkForRecurrence(Mould mould) {
		mould.setIsActive(Constant.ACTIVE_VALID);
		return mouldDao.checkForRecurrence(mould);
	}

	/**
	 * 根据模具表Id查询该条记录所有信息
	 * @param id
	 */
	@Override
	public Mould selectMouldById(int id) {

		return mouldDao.selectMouldById(id, Constant.ACTIVE_VALID);
	}

	
	/**
	 * 更新模具
	 * 
	 * @param Mould
	 */
	@Override
	public void updateMouldById(Mould mould) throws Exception{
		mould.setUpdateTime(DateUtils.getNowDate());
		mould.setIsActive(Constant.ACTIVE_VALID);
		mouldDao.updateMouldById(mould);
		
	}

    //根据模具的车种和图号搜索唯一的 车种记录
	@Override
	public List<Mould> selectMouldByVehicleTypeAndFigureNumber(Mould mould) throws Exception{
		mould.setIsActive(Constant.ACTIVE_VALID);
		List<Mould> mouldToViews = mouldDao.selectMouldByVehicleTypeAndFigureNumber(mould);
		
		if(mouldToViews != null && mouldToViews.size() > 0){
			for(Mould mouldToView : mouldToViews){
				switch(mouldToView.getFactory()){
				case 1: mouldToView.setFactoryName(Constant.MANUFACTURER_NAME_1);
					break;
				case 2: mouldToView.setFactoryName(Constant.MANUFACTURER_NAME_2);
					break;
				case 3: mouldToView.setFactoryName(Constant.MANUFACTURER_NAME_3);
					break;
				default:
					mouldToView.setFactoryName("");
				}
			}
			

		}

		return mouldToViews;
	}

	/**
	 * 根据模具id查询维修记录
	 * 
	 * @param MouldId
	 */
	@Override
	public List<MouldRepairHistory> mouldRepairHistoryList(int id) {
		
		List<MouldRepairHistory> mouldRepairHistoryList = mouldDao.mouldRepairHistoryList(id);
		
		//查询维修记录中的部品信息
		if(mouldRepairHistoryList!=null && mouldRepairHistoryList.size() > 0){
			 List<MouldFaultParts> mouldFaultPartsList = mouldFaultPartsDao.selectPartsByMouldHistory(mouldRepairHistoryList);	
			 if(mouldFaultPartsList !=null && mouldFaultPartsList.size() > 0){
				 
				 for(MouldRepairHistory mrh: mouldRepairHistoryList){
					 
					 List<MouldFaultParts> mouldFaultPartsHistory = new ArrayList<MouldFaultParts>();
					 for(MouldFaultParts mfp: mouldFaultPartsList){
						 
						 if(mrh.getRepairId().equals(mfp.getReId())){
							 mouldFaultPartsHistory.add(mfp);
						 }
						 
					 }
					 mrh.setMouldFaultPartsList(mouldFaultPartsHistory);
				 }
			 }
		}
		
		return mouldRepairHistoryList;
	}

	@Override
	public List<Mould> getMouldLists(Mould mould) {
		mould.setIsActive(Constant.ACTIVE_VALID);
		List<Mould> mouldList = mouldDao.getMouldListNew(mould);
		
		List<CodeList> codeList = codeListDao.selectByType(Constant.CODE_LIST_TYPE_7, 1);
		
		for(Mould m : mouldList){
			for(CodeList c : codeList){
				if(m.getDrawingDeposit().equals(c.getId())){
					m.setTypeName(c.getCodeName());
				}
			}
		}
		
		
		
    	for(Mould b: mouldList){
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
		return mouldList;
	}

}
