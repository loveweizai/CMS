package net.mingsoft.cms.mapper;

import net.mingsoft.cms.model.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * StudentMapper数据库操作接口类
 * 
 **/

public interface StudentMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	Student  selectByPrimaryKey(@Param("id") Integer id);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	int deleteByPrimaryKey(@Param("id") Integer id);

	/**
	 * 
	 * 添加
	 * 
	 **/
	int insert(Student record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(Student record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(Student record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(Student record);

	List<Student> listBySelective(Student record);

	int deleteBatchBySelective(List<Student> record);
}