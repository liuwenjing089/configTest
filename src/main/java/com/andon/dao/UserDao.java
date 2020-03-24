package com.andon.dao;

import com.andon.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public User selectUser(@Param("username") String username, @Param("password") String password);
    List<User> selectUserBaoquan();
    User selectDepartment(@Param("username") String username);
    
    //获取维修人员状态
	public List<Map<String, Object>> getUserRepairStauts();
}
