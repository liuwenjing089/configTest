package com.andon.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.andon.bean.User;
import com.andon.dao.UserDao;
import com.andon.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User getUser(String username, String password) throws Exception{
        return userDao.selectUser(username,password);
    }

    @Override
    public List<User> getUserBaoquan() {
    	List<User> user = userDao.selectUserBaoquan();
        return user;
    }

    @Override
    public User getDepartment(String username) {
        return userDao.selectDepartment(username);
    }

    
    //获取维修人员状态
	@Override
	public List<Map<String, Object>> getUserRepairStauts() throws Exception{
		// TODO Auto-generated method stub
		return userDao.getUserRepairStauts();
	}
}
