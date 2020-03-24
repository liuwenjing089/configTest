package com.andon.dao;

import com.andon.bean.MouldFaultParts;
import com.andon.bean.dto.MouldRepairHistory;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MouldFaultPartsDao {
    //添加部品品番
    public void insert(List<MouldFaultParts> parts);
    //获取部品品番
    public List<MouldFaultParts> selectParts(@Param("reId")int reId);
    //删除部品品番
    public void delete(@Param("reId")String reId);
    
    //获取部品品番
    public List<MouldFaultParts> selectPartsByMouldHistory(List<MouldRepairHistory> mouldRepairHistoryList);
    
}
