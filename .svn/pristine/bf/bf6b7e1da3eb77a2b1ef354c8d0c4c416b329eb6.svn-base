package com.andon.service.impl;

import java.util.*;

import com.andon.bean.Picture;
import com.andon.dao.PictureDao;
import com.andon.utils.UUIDString;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andon.bean.SpotCheck;
import com.andon.bean.SpotRule;
import com.andon.commons.Constant;
import com.andon.dao.SpotCheckDao;
import com.andon.dao.SpotRuleDao;
import com.andon.service.SpotCheckService;
import com.andon.utils.DateUtils;

import net.sf.json.JSONArray;

@Service
@Transactional
public class SpotCheckServiceImpl implements SpotCheckService {
    @Autowired
    private SpotCheckDao spotCheckDao;

    @Autowired
	private SpotRuleDao spotRuleDao;

    @Autowired
	private PictureDao pictureDao;
	@Override
	public void add(List<SpotCheck> spotCheck, String userName) {
		spotCheckDao.insert(spotCheck);
	}

	@Override
	public List<SpotCheck> getSpotCheck(SpotCheck spotCheck,String userName) {
		List<SpotCheck> spotChecks = spotCheckDao.selectSpotCheck(spotCheck);

		//点检开始做的标识
		Boolean flag = true;

		//上传图片地址
		String iploadUrls = null;
		String eploadUrls = null;
		String groupKey = null;
		if(spotChecks.size()>0){
			for(SpotCheck spotCheck1:spotChecks){
				if (spotCheck1.getUploadUrl() != null && !spotCheck1.getUploadUrl().isEmpty()) {
					List<String> strings = new ArrayList<>(Arrays.asList(spotCheck1.getUploadUrl().split(",")));
					List<Picture> selectPicture = pictureDao.selectPicture(strings);
					if (selectPicture.size() > 0) {
						List<String> up = new ArrayList<String>();
						for (Picture picture : selectPicture) {
							up.add(picture.getUrl());
						}
						iploadUrls = StringUtils.join(up.toArray(), ",");
						eploadUrls = spotCheck1.getUploadUrl();
					}
				}
				if(spotCheck1.getSpotState()!=0 && (spotCheck1.getSpotTime()!=null && !spotCheck1.getSpotTime().isEmpty())){
					flag = false;
				}

			}
		}
		if((spotChecks.size()==0||spotCheck.getState()==0)&& flag){
			SpotRule spotRule = new SpotRule();
			spotRule.setType(spotCheck.getType());
			spotRule.setClassification(spotCheck.getPrincipalNumbe());
			spotRule.setCycle(spotCheck.getSpotInterval());
			List<SpotRule>	spotRules = spotRuleDao.selectSpotRule(spotRule);
			if(spotRules.size()>0 && spotCheck.getState()==0) {
				//删除老规则点检
				List<Integer> integers = new ArrayList<>();
				integers.add(spotCheck.getSpotDetailId());
				spotCheckDao.deleteCheckList(integers, userName);

				//插入新的规则点检
				List<SpotCheck> checkList = new ArrayList<SpotCheck>();
				for (SpotRule spot : spotRules) {
					SpotCheck check = new SpotCheck();
					check.setSpotDetailId(spotCheck.getSpotDetailId());
					check.setType(spotCheck.getType());
					check.setSpotInterval(spotCheck.getSpotInterval());
					check.setPrincipalNumbe(spotCheck.getPrincipalNumbe());
					check.setPrincipalName(spotCheck.getPrincipalName());
					check.setSpotState(spotCheck.getSpotState());
					check.setDepartment(spotCheck.getDepartment());
					check.setSpotTypeName(spot.getSpotTypeName());
					check.setSpotInspection(spot.getSpotInspection());
					check.setSpotPosition(spot.getSpotPosition());
					check.setCheckProject(spot.getCheckProject());
					check.setCheckMethod(spot.getCheckMethod());
					check.setRemarks(spot.getRemarks());
					check.setCreateUser(userName);
					check.setUpdateUser(userName);
					check.setCreateTime(DateUtils.getNowDate());
					check.setUpdateTime(DateUtils.getNowDate());
					check.setIsActive(Constant.ACTIVE_VALID);

					//查询图片路径
					//获取图片地址
					String urls = null;
					if (spot.getPrictureUrl() != null && !spot.getPrictureUrl().isEmpty()) {
						List<String> stringList = new ArrayList<>(Arrays.asList(spot.getPrictureUrl().split(",")));
						List<Picture> pictures = pictureDao.selectPicture(stringList);
						if (pictures.size() > 0) {
							List<String> pp = new ArrayList<String>();
							for (Picture picture : pictures) {
								pp.add(picture.getUrl());
							}
							urls = StringUtils.join(pp.toArray(), ",");
						}
					}
					check.setPrictureUrl(urls);
					check.setUploadUrl(eploadUrls);
					//总图
					List<String> ruleList = new ArrayList<>();
					groupKey = spot.getGroupKey();
					ruleList.add(spot.getGroupKey());
					List<Picture> rulePicture = pictureDao.selectPicture(ruleList);
					if (rulePicture.size() > 0) {
						check.setMouldRuleUrl(rulePicture.get(0).getUuid());
					}

					checkList.add(check);
				}
				spotCheckDao.insert(checkList);
			}
			spotChecks = spotCheckDao.selectSpotCheck(spotCheck);
		}
		if(spotChecks.size()>0){
			for(SpotCheck spotCheck1:spotChecks){
				if (spotCheck1.getUploadUrl() != null && !spotCheck1.getUploadUrl().isEmpty()) {
					List<String> strings = new ArrayList<>(Arrays.asList(spotCheck1.getUploadUrl().split(",")));
					List<Picture> selectPicture = pictureDao.selectPicture(strings);
					if (selectPicture.size() > 0) {
						List<String> up = new ArrayList<String>();
						for (Picture picture : selectPicture) {
							up.add(picture.getUrl());
						}
						spotCheck1.setUploadUrl(StringUtils.join(up.toArray(), ","));
					}
				}
				List<String> gg = new ArrayList<>();
				gg.add(spotCheck1.getMouldRuleUrl());
				List<Picture> rulePicture = pictureDao.selectPicture(gg);
				if (rulePicture.size() > 0) {
					spotCheck1.setMouldRuleUrl(rulePicture.get(0).getUrl());
				}
			}
		}
		return spotChecks;
	}

	@Override
	public void updateSpotCheck(List<SpotCheck> listJson, String name) {
		JSONArray jsonArray=JSONArray.fromObject(listJson);
		@SuppressWarnings("rawtypes")
		Collection collection  = JSONArray.toCollection(jsonArray, SpotCheck.class);
		@SuppressWarnings("rawtypes")
		Iterator it = collection.iterator();
		while (it.hasNext()) {
            List<SpotCheck> selectedQueryJudgement = new ArrayList<SpotCheck>();
			SpotCheck q = (SpotCheck) it.next();
			q.setUpdateUser(name);
			q.setUpdateTime(DateUtils.getNowDate());
			String upUrl = q.getUploadUrl();

			List<String> resStr = new ArrayList<>();
			if(upUrl!=null&& !upUrl.isEmpty()){
				List<String> stringList = Arrays.asList(upUrl.split(","));
				List<Picture> pictures = new ArrayList<>();
				for(String s:stringList) {
					String uuid = UUIDString.getPartsKeyByUUId();
					//存入picture表
					Picture picture = new Picture();
					picture.setUuid(uuid);
					picture.setUrl(s);
					picture.setPictureName(UUIDString.getPartsKeyByUUId());
					pictures.add(picture);
					resStr.add(uuid);
				}
				pictureDao.insert(pictures);
			}
			q.setUploadUrl(StringUtils.join(resStr.toArray(), ","));
			selectedQueryJudgement.add(q);
            spotCheckDao.update(selectedQueryJudgement);
		}
	}
}
