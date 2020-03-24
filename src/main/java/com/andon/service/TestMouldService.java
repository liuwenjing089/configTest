package com.andon.service;

import java.util.List;

import com.andon.bean.TestMould;

public interface TestMouldService {
	
	/**
	 * 试模列表
	 * @param TestMould 
	 * @return List
	 */	
	public List<TestMould> getTestMouldList(String deId) throws Exception;
	
	
	/**
	 * 新建试模
	 * 
	 * @param TestMould
	 */
	public void insertTestMould(TestMould testMould) throws Exception;

	
	
	/**
	 * 根据模具表Id查询该条记录所有信息
	 * @param id
	 */
	public TestMould selectTestMouldById(String id) throws Exception;
	
	/**
	 * 查询试模记录中是否已经存在成功的记录
	 * 
	 * @param TestMould
	 */
	public int selectTestMouldToSucceedById(String deId);

}
