package com.andon.dao;

import com.andon.bean.Equip;
import com.andon.bean.EquipSpot;
import com.andon.bean.dto.EquipSeeOutput;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EquipDao {
    public void insert(Equip equip);
    /**
     * 设备列表
     * @param beginIndex
     * @param pageSize
     * @return
     */
    public List<Equip> selectEquip(@Param("beginIndex")int beginIndex, @Param("pageSize")int pageSize);
    //分页用
    public int selectEquipCount();

    /**
     * 检索
     * @param equipName
     * @param equipDescription
     * @param useBeginTime
     * @param equipModel
     * @param standard
     * @param beginIndex
     * @param pageSize
     * @return
     */
    public List<Equip> selectEquipByFirm(@Param("factory")int factory,@Param("equipName")String equipName,@Param("equipDescription")String equipDescription,@Param("useBeginTime")String useBeginTime,@Param("equipModel")String equipModel,@Param("standard")String standard,@Param("beginIndex")int beginIndex, @Param("pageSize")int pageSize);
    //分页用
    public int selectEquipByFirmCount(@Param("factory")int factory,@Param("equipName")String equipName,@Param("equipDescription")String equipDescription,@Param("useBeginTime")String useBeginTime,@Param("equipModel")String equipModel,@Param("standard")String standard);
    //详情
    public Equip selectEquipByid(@Param("id")int id);
    //修改
    public void update(Equip equip);
    //删除
    public void deleteEq(@Param("id")int id);
    //清空lineId
    public void updateEqLine(@Param("id")int id,@Param("lineId")int lineId);
    List<Equip> selectEqBylineID(@Param("lineId")int lineId);
    void deleteEqLine(List<Equip> equip);
    EquipSeeOutput selectEquipSee(@Param("id")int id);
    
    //查询设备型号
	public int selectEquipModel(@Param("equipModel")String equipModel, @Param("isActive") int isActive);

	//根据设备型号查询设备列表
	public List<EquipSeeOutput> selectEquipModelListByEquipModel(@Param("equipModel")String equipModel, @Param("factory")String factory, @Param("isActive") int activeValid);
	
	//根据设备型号模糊查询设备列表
	public List<EquipSeeOutput> selectEquipModelList(Equip equip);
	//根据设备型号模糊查询设备列表数量
	public int selectEquipModelCount(@Param("equipModel")String equipModel, @Param("factory") int factory, @Param("isActive") int activeValid);

	//批量查询eqName
    public List<String> selectBatchName(List<Integer> integers);
    
    //根据多个id查询设备详情
	public List<EquipSpot> selectDetailByIds(List<String> allIds);
}
