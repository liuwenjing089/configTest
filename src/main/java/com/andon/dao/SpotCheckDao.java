package com.andon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.andon.bean.SpotCheck;
import com.andon.bean.SpotDetail;

public interface SpotCheckDao {
    //插入点检规则明细
    public void insert(List<SpotCheck> spotCheck);
    //查询
    public List<SpotCheck> selectSpotCheck(SpotCheck spotCheck);
    //删除
    public void deleteCheckList(@Param("ids")List<Integer> ids, @Param("userName")String userName);
    //更新
    public void update(List<SpotCheck> spotCheck);
    
    //根据sopt_detail表id的集合查询对应spot_check表的记录
    public List<SpotCheck> selectChecksByTaskDetailIds(List<SpotDetail> spotDetail);
}
