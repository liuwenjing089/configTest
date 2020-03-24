package com.andon.dao;

import com.andon.bean.Beltline;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BeltlineDao {
    //添加生产线
    public void insert(Beltline beltline);
    public List<Beltline> selectLine(@Param("beginIndex")int beginIndex, @Param("pageSize")int pageSize, @Param("factory")String factory);
    public int selectLineCount(@Param("factory") String factory);
    //检索
    public List<Beltline> selectLineByFirm(@Param("lineType")int lineType,@Param("beltlineName")String beltlineName,@Param("beltlineDescription")String beltlineDescription,@Param("beginIndex")int beginIndex, @Param("pageSize")int pageSize, @Param("factory")String factory);
    public int selectLineByFirmCount(@Param("lineType")int lineType, @Param("beltlineName")String beltlineName,@Param("beltlineDescription")String beltlineDescription, @Param("factory")String factory);
    //更新
    public void updateLine(@Param("id")int id, @Param("factory")int factory, @Param("beltlineName")String beltlineName,@Param("beltlineDescription")String beltlineDescription,@Param("updateUser")String updateUser,@Param("updateTime") Date updateTime,@Param("lineType") int lineType);
    //删除
    public void deleteLine(@Param("id")int id);
    public Beltline selectLineByid(@Param("id")int id);
    
    //查询模具的成型机
    public List<Beltline> selectLineByMould(@Param("isActive") int isActive, @Param("lineType")int lineType);

    //检索设备生产线
    public List<Beltline> selectEquipLine(@Param("factory")String factory);
}
