package com.andon.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.andon.bean.TestMould;

public interface TestMouldDao {

		
	/**
	 * 试模列表
	 * @param TestMould 
	 * @return List
	 */	
	public List<TestMould> getTestMouldList(@Param("deId") String deId, @Param("isActive") int isActive);
	
	
	/**
	 * 新建试模
	 * 
	 * @param TestMould
	 */
	public void insertTestMould(TestMould testMould);

	
	
	/**
	 * 根据模具表Id查询该条记录所有信息
	 * @param id
	 */
	public TestMould selectTestMouldById(@Param("deId") String deId, @Param("isActive") int isActive);

	/**
	 * 修改试模
	 * 
	 * @param TestMould
	 */
	public void updateTestMould(TestMould testMould);
	
	/**
	 * 查询试模记录中是否已经存在成功的记录
	 * 
	 * @param TestMould
	 */
	public int selectTestMouldToSucceedById(@Param("deId") String deId, @Param("isActive") int isActive);
	
	
	/**
	 * 试模列表
	 * @param TestMould 
	 * @return List
	 */	
	public int getTestMouldCount(@Param("deId") int deId, @Param("isActive") int isActive);
	

}
