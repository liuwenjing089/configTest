package com.andon.service;

import java.util.List;

import com.andon.bean.ComboxByTask;
import com.andon.bean.Equip;
import com.andon.bean.SpotRule;
import com.andon.bean.dto.EquipSeeOutput;


public interface SpotRuleService {
	
	//新建规则
    void add(List<SpotRule> spotRule, String userName) throws Exception;

    List<SpotRule> getSpotRule(SpotRule spotRule);
    
    //规则列表
    public List<SpotRule> selectRuleList(SpotRule spotRule) throws Exception;

    //删除规则
	void delRule(SpotRule spotRule) throws Exception;

	//一条规则详情
	List<SpotRule> ruleDetail(SpotRule spotRule) throws Exception;

	//编辑规则
	void edit(List<SpotRule> spotRule, String userName, String groupKey) throws Exception;

	//查询设备型号
	int selectEquipModel(String equipModel) throws Exception;

	int Validation(List<SpotRule> spotRule) throws Exception;

	List<EquipSeeOutput> selectEquipModelListByEquipModel(String equipModel, String[] ids, String factory) throws Exception;

	List<ComboxByTask> selectEquipModelListByEquipModelToTest(String equipModel, String[] ids, String factory)throws Exception;

	//根据设备型号模糊查询设备列表
	List<EquipSeeOutput> selectEquipModelList(Equip equip) throws Exception;
	//根据设备型号模糊查询设备列表数量
	int selectEquipModelCount(Equip equip) throws Exception;

}
