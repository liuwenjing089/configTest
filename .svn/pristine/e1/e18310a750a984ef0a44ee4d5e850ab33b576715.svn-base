package com.andon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.andon.bean.SpotRule;

public interface SpotRuleDao {
	//新建规则
    public void insert(List<SpotRule> spotRule);

    public List<SpotRule> selectSpotRule(SpotRule spotRule);

    //规则列表
    public List<SpotRule> selectRuleList(SpotRule spotRule);    
    //删除规则
    public void delRule(SpotRule spotRule);   
	//一条规则详情
	public List<SpotRule> ruleDetail(SpotRule spotRule);
	
	//编辑时
	public void updateRulToDel(@Param("groupKey") String groupKey, @Param("isActive") int isActive);
	
	//效验重复
	public int validation(SpotRule spotRule);
	
	//检索全部规则
    public List<SpotRule> selectSpotRuleByAll(SpotRule spotRule);

}
