package com.andon.service;

import com.andon.bean.EquipRepair;
import com.andon.bean.dto.EquipRepairOutput;
import com.andon.bean.dto.UpdateEquipRepair;

public interface EquipRepairService {
    void add(EquipRepair equipRepair) throws Exception;
    void update(UpdateEquipRepair equipRepair) throws Exception;
    EquipRepairOutput getByid(int id);
    //修改设备维修表确认状态
	void updateDetailState(UpdateEquipRepair updateEquipRepair) throws Exception;
}
