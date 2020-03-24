package com.andon.dao;

import com.andon.bean.SpotDetail;
import java.util.List;
import com.andon.bean.dto.SpotDetailEquip;
import com.andon.bean.dto.SpotDetailMould;
import org.apache.ibatis.annotations.Param;

public interface SpotDetailDao {
    //获取模具点检明细
    public List<SpotDetailMould> selectSpotDetailMould(SpotDetailMould mould);
    //新建模具点检明细
    public int selectSpotDetailMouldCount(SpotDetailMould mould);
    //获取设备点检明细
    public List<SpotDetailEquip> selectSpotDetailEquip(SpotDetailEquip equip);
    public int selectSpotDetailEquipCount(SpotDetailEquip equip);
    //删除点检明细
    public void deleteTaskDetail(@Param("id") int id,@Param("userName") String userName);
    public List<SpotDetail> selectDeleteDetail(@Param("id") int id);

    //更新状态
    public void updateState(@Param("id") int id,@Param("spotMan") String spotMan);
    public void updateConfirmState(SpotDetail spotDetail);
    //批量插入点检详情表
    public void insert(List<SpotDetail> spotDetail);

    //根据id获取明细
    public SpotDetail selectSpotDetailById(@Param("id") int id);
    public List<SpotDetail> selectSpotDetailByTaskId(@Param("id") int id);

    //日历
    public List<SpotDetailMould> selectDateSpotDetailMould(SpotDetailMould mould);
    public int selectDateSpotDetailMouldCount(SpotDetailMould mould);
    public List<SpotDetailEquip> selectDateSpotDetailEquip(SpotDetailEquip equip);
    public int selectDateSpotDetailEquipCount(SpotDetailEquip equip);

    public void updateDetailTaskId(SpotDetail spotDetail);
    
    //根据任务id集合批量查询出对应的spotDetail的记录
	public List<SpotDetail> selectDetailsByTaskIds(String[] taskIds);
	
	//根据任务id集合批量删除出对应的spotDetail的记录
	public void batchDeleteTaskDetail(@Param("ids")String[] taskIds, @Param("userName")String userName);
}
