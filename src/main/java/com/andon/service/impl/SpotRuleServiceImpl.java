package com.andon.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.andon.bean.Combox;
import com.andon.bean.ComboxByTask;
import com.andon.bean.Equip;
import com.andon.bean.Picture;
import com.andon.bean.SpotRule;
import com.andon.bean.dto.EquipSeeOutput;
import com.andon.commons.Constant;
import com.andon.dao.EquipDao;
import com.andon.dao.PictureDao;
import com.andon.dao.SpotRuleDao;
import com.andon.service.SpotRuleService;
import com.andon.utils.DateUtils;
import com.andon.utils.UUIDString;


@Service
@Transactional
public class SpotRuleServiceImpl implements SpotRuleService {
    @Autowired
    private SpotRuleDao spotRuleDao;
    @Autowired
    private EquipDao equipDao;
    @Autowired
    private PictureDao pictureDao;

	@Override
	public void add(List<SpotRule> spotRule, String username) throws Exception{
		String groupKey = UUIDString.getPartsKeyByUUId();
		
		List<Picture> pictureList = new ArrayList<Picture>();
		
		for(int i = 0 ; i<spotRule.size(); i++){
			SpotRule s = spotRule.get(i);
			//获取模具点检规则总图
			if(i == 0){
				if(!StringUtils.isBlank(s.getMouldSpotRuleUrl())){
					Picture picture = new Picture();
					picture.setUuid(groupKey);
					picture.setUrl(s.getMouldSpotRuleUrl());
					//获取图片名称
					String pictureName = getPictureName(s.getMouldSpotRuleUrl());
					picture.setPictureName(pictureName);
					
					pictureList.add(picture);
				}
			}
			
			
			s.setGroupKey(groupKey);
			s.setCreateTime(DateUtils.getNowDate());
			s.setCreateUser(username);
			s.setUpdateTime(DateUtils.getNowDate());
			s.setUpdateUser(username);
			s.setIsActive(Constant.ACTIVE_VALID);
			
			String prictureUUIDs = "";		
			//插入图片表
			if(!StringUtils.isBlank(s.getPrictureUrl())){
				String pictureUrls[] = s.getPrictureUrl().split(",");
				
				for(String pictureUrl:  pictureUrls){
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
			}

			
			//重新给规则主表的url赋值
			s.setPrictureUrl(prictureUUIDs);
			
		}
		//插入规则主表
		spotRuleDao.insert(spotRule);
		//插入图片表
		
		if(pictureList.size() > 0){
			pictureDao.insert(pictureList);
		}

	}

	private String getPictureName(String pictureUrl) {
		int index = pictureUrl.lastIndexOf("/");  
		String pictureName  = pictureUrl .substring(index + 1, pictureUrl.length());
		return pictureName;
	}

	@Override
	public List<SpotRule> getSpotRule(SpotRule spotRule) {
		return spotRuleDao.selectSpotRule(spotRule);
	}

    //规则列表
	@Override
	public List<SpotRule> selectRuleList(SpotRule spotRule) throws Exception{
		spotRule.setIsActive(Constant.ACTIVE_VALID);
		return spotRuleDao.selectRuleList(spotRule);
	}


	//删除规则
	@Override
	public void delRule(SpotRule spotRule) throws Exception{
		
		//先删除图片表
		SpotRule delSpotRule = new SpotRule();
		delSpotRule.setGroupKey(spotRule.getGroupKey());
		delSpotRule.setIsActive(Constant.ACTIVE_VALID);		
		List<SpotRule> oldSpotRule = spotRuleDao.ruleDetail(delSpotRule);
		
		List<String> delPicture = new ArrayList<String>();
		delPicture.add(spotRule.getGroupKey());
		for(SpotRule s: oldSpotRule){
			if(!StringUtils.isBlank(s.getPrictureUrl())){
				String pictureUuis[] = s.getPrictureUrl().split(",");
				Collections.addAll(delPicture, pictureUuis);
			}

		}
		
		if(delPicture !=null && delPicture.size() > 0){
			pictureDao.batchDeletePicture(delPicture);
		}

		
		//再删除规则
		spotRule.setUpdateTime(DateUtils.getNowDate());
		spotRule.setIsActive(Constant.ACTIVE_DELETE);
		spotRuleDao.delRule(spotRule);
		
	}

	//一条规则详情
	@Override
	public List<SpotRule> ruleDetail(SpotRule spotRule) throws Exception {
		spotRule.setIsActive(Constant.ACTIVE_VALID);
		List<SpotRule> soptRuleList = spotRuleDao.ruleDetail(spotRule);
		
		//将字段图片uuid转换为url
		for(SpotRule spotRules: soptRuleList){
			if(!StringUtils.isBlank(spotRules.getPrictureUrl())){
				String pictureUUids[] = spotRules.getPrictureUrl().split(",");
				List<String> uuids = Arrays.asList(pictureUUids);
				List<Picture> pictureUrls = pictureDao.selectPicture(uuids);
				
				String prictureUrl = "";
				String prictureName = "";
				for(Picture p: pictureUrls){
					if(prictureUrl == ""){
						prictureUrl += p.getUrl();
						prictureName += p.getPictureName();
					}else{
						prictureUrl += "," + p.getUrl();
						prictureName += "," + p.getPictureName();
					}
				}
				spotRules.setPrictureUrl(prictureUrl);
				spotRules.setPrictureName(prictureName);
			}

		}
		
		//查询有没有模具总图
		String groupKey = soptRuleList.get(0).getGroupKey();
		@SuppressWarnings("serial")
		List<String> groupUuid = new ArrayList<String>(){{this.add(groupKey);}};
		List<Picture> groupUrl = pictureDao.selectPicture(groupUuid);
		
		if(groupUrl != null && groupUrl.size() > 0){
			soptRuleList.get(0).setMouldSpotRuleUrl(groupUrl.get(0).getUrl());
		}
		
		return soptRuleList;
	}

	//编辑规则
	@Override
	public void edit(List<SpotRule> spotRule, String userName, String groupKey) throws Exception {

		//先删除图片表
		SpotRule delSpotRule = new SpotRule();
		delSpotRule.setGroupKey(groupKey);
		delSpotRule.setIsActive(Constant.ACTIVE_VALID);		
		List<SpotRule> oldSpotRule = spotRuleDao.ruleDetail(delSpotRule);
		
		List<String> delPicture = new ArrayList<String>();
		delPicture.add(groupKey);
		for(SpotRule s: oldSpotRule){
			String pictureUuis[] = s.getPrictureUrl().split(",");
			Collections.addAll(delPicture, pictureUuis);
		}
		pictureDao.batchDeletePicture(delPicture);
		
		//再删除规则表
		spotRuleDao.updateRulToDel(groupKey, Constant.ACTIVE_VALID);
		
		//重新插入记录
		List<Picture> pictureList = new ArrayList<Picture>();
		
		for(int i = 0 ; i<spotRule.size(); i++){
			SpotRule s = spotRule.get(i);
			//获取模具点检规则总图
			if(i == 0){
				if(!StringUtils.isBlank(s.getMouldSpotRuleUrl())){
					Picture picture = new Picture();
					picture.setUuid(groupKey);
					picture.setUrl(s.getMouldSpotRuleUrl());
					//获取图片名称
					String pictureName = getPictureName(s.getMouldSpotRuleUrl());
					picture.setPictureName(pictureName);
					
					pictureList.add(picture);
				}
			}
			
			
			s.setGroupKey(groupKey);
			s.setCreateTime(DateUtils.getNowDate());
			s.setCreateUser(userName);
			s.setUpdateTime(DateUtils.getNowDate());
			s.setUpdateUser(userName);
			s.setIsActive(Constant.ACTIVE_VALID);
			
			//插入图片表
			String prictureUUIDs = "";
			
			if(!StringUtils.isBlank(s.getPrictureUrl())){
				String pictureUrls[] = s.getPrictureUrl().split(",");
				for(String pictureUrl:  pictureUrls){
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
			}
			
			//重新给规则主表的url赋值
			s.setPrictureUrl(prictureUUIDs);
			
		}
		//插入规则主表
		spotRuleDao.insert(spotRule);
		//插入图片表
		if(pictureList.size() > 0){
			pictureDao.insert(pictureList);
		}
		
	}

	//查询设备型号
	@Override
	public int selectEquipModel(String equipModel) throws Exception{

		return equipDao.selectEquipModel(equipModel, Constant.ACTIVE_VALID);
	}

	//效验重复
	@Override
	public int Validation(List<SpotRule> spotRule) throws Exception{
		spotRule.get(0).setIsActive(Constant.ACTIVE_VALID);
		return spotRuleDao.validation(spotRule.get(0));
	}

	//根据设备型号查询设备列表
	@Override
	public List<EquipSeeOutput> selectEquipModelListByEquipModel(String equipModel, String[] ids, String factory) throws Exception {
		List<Combox> combox = new ArrayList<Combox>();
		List<EquipSeeOutput> equipList = equipDao.selectEquipModelListByEquipModel(equipModel, factory, Constant.ACTIVE_VALID);		
		List<EquipSeeOutput> equipList2 = new ArrayList<EquipSeeOutput>();
		
		if((equipList != null && equipList.size() > 0) && (ids == null || ids.length == 0)){
			
			equipList2 = equipList;
			
		}else if((equipList != null && equipList.size() > 0) && (ids != null && ids.length > 0)){
			
			List<String> eIds = new ArrayList<String>();
			for(int i =0; i< ids.length; i++){
				eIds.add(ids[i]);
			}
			
			equipList2 = equipList.stream().filter((EquipSeeOutput a) -> !eIds.contains(String.valueOf(a.getId()))).distinct().collect(Collectors.toList());
		}
		
		return equipList2;
	}
	
	//根据设备型号查询设备列表
	@Override
	public List<ComboxByTask> selectEquipModelListByEquipModelToTest(String equipModel, String[] ids, String factory) throws Exception {
		List<EquipSeeOutput> equipList = equipDao.selectEquipModelListByEquipModel(equipModel, factory, Constant.ACTIVE_VALID);		
		List<EquipSeeOutput> equipList2 = new ArrayList<EquipSeeOutput>();
		
		if((equipList != null && equipList.size() > 0) && (ids == null || ids.length == 0)){
			
			equipList2 = equipList;
			
		}else if((equipList != null && equipList.size() > 0) && (ids != null && ids.length > 0)){
			
			List<String> eIds = new ArrayList<String>();
			for(int i =0; i< ids.length; i++){
				eIds.add(ids[i]);
			}
			
			equipList2 = equipList.stream().filter((EquipSeeOutput a) -> !eIds.contains(String.valueOf(a.getId()))).distinct().collect(Collectors.toList());
		}
		List<ComboxByTask> list = new ArrayList<ComboxByTask>();
        if(equipList2 != null && equipList2.size() > 0){
    		for(EquipSeeOutput e : equipList2){
    			ComboxByTask c = new ComboxByTask();
    			
    			c.setId(String.valueOf(e.getId()));
    			c.setName(e.getEquipName());
    			c.setNum(e.getEquipNum());
    			c.setModel(e.getEquipModel());
                c.setFlag(false);
                list.add(c);
    		}
        }
	
		return list;
	}

	private String formatPartStringTO1(String value) {
		int totalLen = 20;
		int len = strlen(value);
		
		if(len < totalLen){
			for(int i = 0; i< (totalLen - len); i++){
				value += "&ensp;";
			}
		}
	    return value;
	}
	private String formatPartStringTO2(String value) {
		int totalLen = 20;
		int len = strlen(value);
		
		if(len < totalLen){
			for(int i = 0; i< (totalLen - len); i++){
				value += "&ensp;";
			}
		}
	    return value;
	}
	private String formatPartStringTO3(String value) {
		int totalLen = 24;
		int len = strlen(value);
		
		if(len < totalLen){
			for(int i = 0; i< (totalLen - len); i++){
				value += "&ensp;";
			}
		}
	    return value;
	}

	
	private int strlen(String value){
	    int len = 0;
	    for (int i=0; i<value.length(); i++) { 
	     char c = value.charAt(i); 
	    //单字节加1 
	     if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) { 
	       len++; 
	     } 
	     else { 
	      len+=2; 
	     } 
	    } 
	    return len;
	}
	
	
	private String formatPartString1(String data){
		if(data.length() < 22){
			int l = data.length();
			for(int i = 0; i< (22 - l); i++){
				data += "&ensp;";
			}
		}
		return data;
	}

	//根据设备型号模糊查询设备列表
	@Override
	public List<EquipSeeOutput> selectEquipModelList(Equip equip) throws Exception {
		
		equip.setIsActive(Constant.ACTIVE_VALID);
		List<EquipSeeOutput> equipSeeOutput = equipDao.selectEquipModelList(equip);
		
		SpotRule sql = new SpotRule();
		sql.setType(1);
		sql.setIsActive(Constant.ACTIVE_VALID);
		List<SpotRule> spotRule = spotRuleDao.selectSpotRuleByAll(sql);
		
		if(equipSeeOutput != null && equipSeeOutput.size() > 0){
			if(spotRule != null && spotRule.size() > 0){
				for(int i = 0; i < equipSeeOutput.size(); i++){
					
					for(int j = 0; j < spotRule.size(); j++){
						
						if(equipSeeOutput.get(i).getEquipModel().equals(spotRule.get(j).getClassification())){
							
							String cycleString = equipSeeOutput.get(i).getCycleString() == null? "": equipSeeOutput.get(i).getCycleString();
							
							if(StringUtils.isBlank(cycleString)){
								cycleString += cycleToString(spotRule.get(j).getCycle());
							}else{
								cycleString += "," + cycleToString(spotRule.get(j).getCycle());
							}
							
							equipSeeOutput.get(i).setCycleString(cycleString);
						}
					}
					
				}
			}
		}
		return equipSeeOutput;
	}


	//根据设备型号模糊查询设备列表数量
	@Override
	public int selectEquipModelCount(Equip equip) throws Exception {

		return equipDao.selectEquipModelCount(equip.getEquipModel(), equip.getFactory(), Constant.ACTIVE_VALID);
	}
	
	private String cycleToString(String cycle){
		String cycleString = "";
		switch(cycle){
		case "1": cycleString = "月检";
			break;
		case "3": cycleString = "季度检";
			break;
		case "6": cycleString = "半年检";
			break;
		case "12": cycleString = "年检";
			break;
		default: cycleString = "";
		
		}
		return cycleString;
	}
}
