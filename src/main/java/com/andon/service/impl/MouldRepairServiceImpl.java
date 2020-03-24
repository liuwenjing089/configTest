package com.andon.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.andon.bean.MouldFaultParts;
import com.andon.bean.MouldPartsInfo;
import com.andon.bean.MouldPreventionType;
import com.andon.bean.MouldRepair;
import com.andon.bean.Picture;
import com.andon.bean.Repair;
import com.andon.bean.RepairPrevention;
import com.andon.bean.dto.TestMouldRepair;
import com.andon.commons.Constant;
import com.andon.dao.MouldFaultPartsDao;
import com.andon.dao.MouldPartsInfoDao;
import com.andon.dao.MouldPreventionTypeDao;
import com.andon.dao.MouldRepairDao;
import com.andon.dao.PictureDao;
import com.andon.dao.RepairDao;
import com.andon.dao.RepairPreventionDao;
import com.andon.service.MouldRepairService;
import com.andon.utils.CreateNumber;
import com.andon.utils.DateUtils;
import com.andon.utils.UUIDString;

@Service
@Transactional
public class MouldRepairServiceImpl implements MouldRepairService {
    @Autowired
    private MouldRepairDao mouldRepairDao;
    @Autowired
    private RepairDao repairDao;
    @Autowired
    private PictureDao pictureDao;
    @Autowired
    private MouldPreventionTypeDao mouldPreventionTypeDao;
    @Autowired
    private MouldPartsInfoDao mouldPartsInfoDao;
    @Autowired
    private MouldFaultPartsDao mouldFaultPartsDao;
    @Autowired
    private RepairPreventionDao repairPreventionDao;
    
    
	/**
	 * 新建模具报修
	 * 
	 * @param TestMouldRepair
	 */
	@Override
	public void insertMouldRepair(MouldRepair mouldRepair) throws Exception{
		//插入维修主表
		Repair repair = new Repair();
		
		//获取报修表中报修编号是当天最大的一条
		Map<String, Object> map = repairDao.selectRepairNum();
		Integer num = null;
		if(map != null){
			num = Integer.valueOf(map.get("num").toString());
		}
		
		//获取报修编号
		String repairNumber = CreateNumber.createEquiqNumber(num);
		repair.setRepairNumber(repairNumber);
		
        repair.setDetailId(mouldRepair.getMouldId());
        repair.setType(Constant.TYPE_MOULD);
        repair.setState(Constant.NEW_REPAIR);
        repair.setApplicant(mouldRepair.getApplicant());
		repair.setReportRepairTime(DateUtils.getCurrentDateMinute());
		repair.setCreateUser(mouldRepair.getCreateUser());
		repair.setCreateTime(DateUtils.getNowDate());
		repair.setUpdateUser(mouldRepair.getUpdateUser());
		repair.setUpdateTime(DateUtils.getNowDate());
		repair.setIsActive(Constant.ACTIVE_VALID);
		repairDao.insert(repair);
		
		//插入模具维修表
		MouldRepair sql = mouldRepair;
		sql.setReId(String.valueOf(repair.getId()));
		sql.setCreateTime(DateUtils.getNowDate());
		sql.setUpdateTime(DateUtils.getNowDate());
		sql.setIsActive(Constant.ACTIVE_VALID);
		mouldRepairDao.insertMouldRepair(sql);
		
	}

	/**
	 * 根据模具报修表Id查询该条记录所有信息
	 * @param id
	 */
	@Override
	public MouldRepair selectMouldRepairById(int id) throws Exception{
		MouldRepair mouldRepair = mouldRepairDao.selectMouldRepairById(id, Constant.ACTIVE_VALID);
		//把图片uuid转化为图片
		String faultLocationUuid = mouldRepair.getFaultLocationUrl();
		String faultLocationUrls = (String)uuidToPicturUrl(faultLocationUuid).get("urls");
		mouldRepair.setFaultLocationUrl(faultLocationUrls);
		
		String productUuid = mouldRepair.getProductUrl();
		String productUrls = (String)uuidToPicturUrl(productUuid).get("urls");
		mouldRepair.setProductUrl(productUrls);
		
		String formingMachineUuid = mouldRepair.getFormingMachineUrl();
		String formingMachineUrls = (String)uuidToPicturUrl(formingMachineUuid).get("urls");
		mouldRepair.setFormingMachineUrl(formingMachineUrls);
		
		String maintenanceCompletedUuid = mouldRepair.getMaintenanceCompletedUrl();
		String maintenanceCompletedUrls = (String)uuidToPicturUrl(maintenanceCompletedUuid).get("urls");
		mouldRepair.setMaintenanceCompletedUrl(maintenanceCompletedUrls);
		
		
		//查询该维修记录在模具报修类型里是否存在,如果存在赋值
		MouldPreventionType sql = new MouldPreventionType();
		sql.setReId(String.valueOf(mouldRepair.getReId()));
		sql.setIsActive(Constant.ACTIVE_VALID);
		MouldPreventionType mouldPreventionType = mouldPreventionTypeDao.selectMouldPreventionTypeByReId(sql);
		if(mouldPreventionType != null){
			mouldRepair.setTypeGroupKey(mouldPreventionType.getGroupKey());
		}
		
		return mouldRepair;
	}

	//将维修记录中的图片uuid转化为url;
	private Map<String, Object> uuidToPicturUrl(String uuid){
		
		Map<String, Object> map = new HashMap<String, Object>();
		String prictureUrl = "";
		String prictureName = "";
		
		if(!StringUtils.isBlank(uuid)){
			String pictureUUids[] = uuid.split(",");
			List<String> uuids = Arrays.asList(pictureUUids);
			List<Picture> pictureUrls = pictureDao.selectPicture(uuids);
			

			for(Picture p: pictureUrls){
				if(prictureUrl == ""){
					prictureUrl += p.getUrl();
					prictureName += p.getPictureName();
				}else{
					prictureUrl += "," + p.getUrl();
					prictureName += "," + p.getPictureName();
				}
			}
		}

		map.put("urls", prictureUrl);
		map.put("names", prictureName);
		return map;
	}
	
	/**
	 * 更新模具报修表
	 * 
	 * @param TestMouldRepair
	 */
	@Override
	public void updateMouldRepairById(MouldRepair mouldRepair) throws Exception{
		//更新维修主表
		
		//判断此维修记录是普通维修还是再发预防
		boolean flag = true;
		try{
			Integer.valueOf(mouldRepair.getReId());
			
		}catch(Exception e){
			flag = false;
		}
		
		
		//flag 为true时更新维修主表，为false 更新预防再发维修表
		if(flag){
			Repair repair = new Repair();
			repair.setId(Integer.valueOf(mouldRepair.getReId()));
//			repair.setBeginTime(mouldRepair.getBeginTime());
			if(mouldRepair.getState() != Constant.STATE_IS_NULL){
				
				repair.setEndTime(DateUtils.getCurrentDateMinute());
				repair.setState(Constant.REPAIR_FINISH);		
			}else{
				repair.setState(Constant.STATE_IS_NULL);		
				
			}
				
			repair.setUpdateTime(DateUtils.getNowDate());
			repair.setUpdateUser(mouldRepair.getUpdateUser());
			repair.setIsActive(Constant.ACTIVE_VALID);
			repairDao.update(repair);
			
		}else{
			RepairPrevention repairPrevention = new RepairPrevention();
			repairPrevention.setUuid(mouldRepair.getReId());
//			repair.setBeginTime(mouldRepair.getBeginTime());
			if(mouldRepair.getState() != Constant.STATE_IS_NULL){
				
				repairPrevention.setEndTime(DateUtils.getCurrentDateMinute());
				repairPrevention.setState(Constant.REPAIR_FINISH);		
			}else{
				repairPrevention.setState(Constant.STATE_IS_NULL);		
				
			}
			
			repairPrevention.setUpdateTime(DateUtils.getNowDate());
			repairPrevention.setUpdateUser(mouldRepair.getUpdateUser());
			repairPrevention.setIsActive(Constant.ACTIVE_VALID);
			repairPreventionDao.update(repairPrevention);
		}

		
		//更新模具维修表
		List<Picture> pictureList = new ArrayList<Picture>();
		MouldRepair sql = mouldRepair;
		
		//模具不良照片url集合
		if(!StringUtils.isBlank(mouldRepair.getFaultLocationUrl())){
			String faultLocationUrls[] = mouldRepair.getFaultLocationUrl().split(",");
			//上传到图片表并返回图片uuid集合
			String faultLocationUuids = pictureToUuid(faultLocationUrls, pictureList);
			sql.setFaultLocationUrl(faultLocationUuids);
		}

		
		//制品不良照片url集合
		if(!StringUtils.isBlank(mouldRepair.getProductUrl())){
			
			String productUrl[] = mouldRepair.getProductUrl().split(",");
			//上传到图片表并返回图片uuid集合
			String productUuids = pictureToUuid(productUrl, pictureList);
			sql.setProductUrl(productUuids);
		}

		
		//维修照片url集合
		if(!StringUtils.isBlank(mouldRepair.getFormingMachineUrl())){
			
			String formingMachineUrl[] = mouldRepair.getFormingMachineUrl().split(",");
			//上传到图片表并返回图片uuid集合
			String formingMachineUuids = pictureToUuid(formingMachineUrl, pictureList);
			sql.setFormingMachineUrl(formingMachineUuids);
		}

		
		//维修完成照片url集合
		if(!StringUtils.isBlank(mouldRepair.getMaintenanceCompletedUrl())){
			String maintenanceCompletedUrl[] = mouldRepair.getMaintenanceCompletedUrl().split(",");
			//上传到图片表并返回图片uuid集合
			String maintenanceCompletedUuids = pictureToUuid(maintenanceCompletedUrl, pictureList);
			sql.setMaintenanceCompletedUrl(maintenanceCompletedUuids);
		}
		
		//插入图片表
		if(pictureList.size() > 0){
			pictureDao.insert(pictureList);
		}
		

		sql.setUpdateTime(DateUtils.getNowDate());
		sql.setIsActive(Constant.ACTIVE_VALID);
		mouldRepairDao.updateMouldRepairById(sql);
		
		
		//更新损坏部件表
		List<MouldFaultParts> partsList = mouldRepair.getPartsList();
		//先删除
		mouldFaultPartsDao.delete(String.valueOf(mouldRepair.getReId()));
		//再插入
        if(partsList != null && partsList.size() > 0){
        	for(MouldFaultParts m : partsList){
        		m.setCreateUser(mouldRepair.getCreateUser());
        		m.setCreateTime(DateUtils.getNowDate());
        		m.setUpdateUser(mouldRepair.getUpdateUser());
        		m.setUpdateTime(DateUtils.getNowDate());
        		m.setIsActive(Constant.ACTIVE_VALID);
        	}
        	
    		mouldFaultPartsDao.insert(partsList);   		    		
        }

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

	/**
	 * 班长确认
	 * @param id 
	 * 
	 * @param userName
	 */
	@Override
	public void mouldRepairByMonitorConfirm(int id, String userName, int userType) throws Exception{
		
		//更新维修主表状态
		Repair repair = new Repair();
		repair.setId(id);
		
		int start = 9;
		String time = DateUtils.getCurrentDateMinute();
		if(userType == 2){
			start = Constant.CONFIRM_FINISH;
			repair.setConfirmationTime(time);
		}else if(userType == 5){
			start = Constant.CONFIRM_XIZHANG;
			repair.setPreDepChiefConTime(time);
		}else if(userType == 6){
			start = Constant.CONFIRM_KEZHANG;
			repair.setPreSecChiefConTime(time);
		}else if(userType == 7){
			start = Constant.CONFIRM_PIBAO;
			repair.setQualityMonitorTime(time);
		}
		
		repair.setState(start);
		repair.setUpdateTime(DateUtils.getNowDate());
		repair.setUpdateUser(userName);
		repair.setIsActive(Constant.ACTIVE_VALID);
		repairDao.update(repair);
		
		//更新设备维修表
		MouldRepair mouldRepair = new MouldRepair();
		mouldRepair.setReId(String.valueOf(id));
		
		if(userType == 2){
			mouldRepair.setShiftLeader(userName);
		}else if(userType == 5){
			mouldRepair.setPreservationDepartment(userName);
		}else if(userType == 6){
			mouldRepair.setPreservationSectionChief(userName);
		}else if(userType == 7){
			mouldRepair.setMouldUser(userName);
		}
		
		mouldRepair.setUpdateUser(userName);
		mouldRepair.setUpdateTime(DateUtils.getNowDate());
		mouldRepair.setIsActive(Constant.ACTIVE_VALID);
		mouldRepairDao.MouldRepairConfirm(mouldRepair);
	}

	/**
	 * 根据模具报修表Id查询该条记录所有信息
	 * @param id
	 */
	@Override
	public TestMouldRepair selectMouldRepairByIds(int id) throws Exception{
		TestMouldRepair testMouldRepair = mouldRepairDao.selectMouldRepairByIds(id, Constant.ACTIVE_VALID);
		return testMouldRepair;
	}

	//各级别确认 + 插入模具报修种类
	@Override
	public void mouldRepairByMonitorConfirmAndAddType(int id, String userName, int userType, int mouldId)
			throws Exception {
		
		//更新维修主表状态
		Repair repair = new Repair();
		repair.setId(id);
		
		int start = 9;
		String time = DateUtils.getCurrentDateMinute();
		if(userType == 2){
			start = Constant.CONFIRM_FINISH;
			repair.setConfirmationTime(time);
		}else if(userType == 5){
			start = Constant.CONFIRM_XIZHANG;
			repair.setPreDepChiefConTime(time);
		}else if(userType == 6){
			start = Constant.CONFIRM_KEZHANG;
			repair.setPreSecChiefConTime(time);
		}else if(userType == 7){
			start = Constant.CONFIRM_PIBAO;
			repair.setQualityMonitorTime(time);
		}
		
		repair.setState(start);
		repair.setUpdateTime(DateUtils.getNowDate());
		repair.setUpdateUser(userName);
		repair.setIsActive(Constant.ACTIVE_VALID);
		repairDao.update(repair);
		
		//更新设备维修表
		MouldRepair mouldRepair = new MouldRepair();
		mouldRepair.setReId(String.valueOf(id));
		
		if(userType == 2){
			mouldRepair.setShiftLeader(userName);
		}else if(userType == 5){

			mouldRepair.setPreservationDepartment(userName);
			
			//插入模具报修类型表一条新记录
			inertMouldPreventionType(id, userName, mouldId);
			
		}else if(userType == 6){
			mouldRepair.setPreservationSectionChief(userName);
		}else if(userType == 7){
			mouldRepair.setMouldUser(userName);
		}
		
		mouldRepair.setUpdateUser(userName);
		mouldRepair.setUpdateTime(DateUtils.getNowDate());
		mouldRepair.setIsActive(Constant.ACTIVE_VALID);
		mouldRepairDao.MouldRepairConfirm(mouldRepair);
	}

	//插入模具报修类型表一条新记录
	private void inertMouldPreventionType(int id, String userName, int mouldId) {
		MouldPreventionType mouldPreventionType = new MouldPreventionType();			
		mouldPreventionType.setCreateUser(userName);
		mouldPreventionType.setUpdateUser(userName);
		mouldPreventionType.setCreateTime(DateUtils.getNowDate());
		mouldPreventionType.setUpdateTime(DateUtils.getNowDate());
		mouldPreventionType.setIsActive(Constant.ACTIVE_VALID);
		mouldPreventionType.setGroupKey(UUIDString.getPartsKeyByUUId());
		mouldPreventionType.setRemarks(UUIDString.getPartsKeyByUUId());
		mouldPreventionType.setRemarks(UUIDString.getPartsKeyByUUId());
		mouldPreventionType.setMouldId(mouldId);
		mouldPreventionType.setReId(String.valueOf(id));;
		mouldPreventionTypeDao.insertMouldPreventionType(mouldPreventionType);
	}

	/**
	 * //获取部品分类
	 * @param mouldPartsInfo 
	 * @throws Exception 
	 */
	@Override
	public List<MouldPartsInfo> selectMouldPartsInfoToPartsType(MouldPartsInfo mouldPartsInfo) throws Exception {
		mouldPartsInfo.setIsActive(Constant.ACTIVE_VALID);
		List<MouldPartsInfo> list = mouldPartsInfoDao.selectMouldPartsInfoToPartsType(mouldPartsInfo);
		return list;
	}

	/**
	 * //根据部品分类获取部品名称

	 * @param mouldPartsInfo 
	 * @throws Exception 
	 */
	@Override
	public List<MouldPartsInfo> selectMouldPartsInfoToPartsName(MouldPartsInfo mouldPartsInfo) throws Exception {
		mouldPartsInfo.setIsActive(Constant.ACTIVE_VALID);
		List<MouldPartsInfo> list = mouldPartsInfoDao.selectMouldPartsInfoToPartsName(mouldPartsInfo);
		return list;
	}
	
	/**
	 * //根据部品分类和部品名称获取部品品番或型号

	 * @param mouldPartsInfo 
	 * @throws Exception 
	 */
	@Override
	public List<MouldPartsInfo> selectMouldPartsInfoToPartsNum(MouldPartsInfo mouldPartsInfo) throws Exception {
		mouldPartsInfo.setIsActive(Constant.ACTIVE_VALID);
		List<MouldPartsInfo> list = mouldPartsInfoDao.selectMouldPartsInfoToPartsNum(mouldPartsInfo);
		return list;
	}


	/**
	 * //根据报修编号获取模具损坏部件维修详情
	 * @param reId
	 * @throws Exception 
	 */
	@Override
	public List<MouldFaultParts> selectMouldFaultPartsDetail(String reId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



}
