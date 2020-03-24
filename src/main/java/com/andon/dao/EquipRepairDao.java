package com.andon.dao;

import com.andon.bean.EquipRepair;
import com.andon.bean.dto.EquipRepairOutput;
import com.andon.bean.dto.UpdateEquipRepair;
import org.apache.ibatis.annotations.Param;

public interface EquipRepairDao {
    //添加设备维修
    public void insert(EquipRepair equipRepair);
    //更新设备维修
    public void update(UpdateEquipRepair equipRepair);
    //获取设备维修
    public EquipRepairOutput selectEquipRepair(@Param("id")int id);
    //修改设备维修表确认状态
	public void updateDetailState(UpdateEquipRepair updateEquipRepair);
}
