package com.andon.service;

import com.andon.bean.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User getUser(String username,String password) throws Exception;
    List<User> getUserBaoquan();
    User getDepartment(String username);
    
    //获取维修人员状态
	List<Map<String, Object>> getUserRepairStauts() throws Exception;
}
