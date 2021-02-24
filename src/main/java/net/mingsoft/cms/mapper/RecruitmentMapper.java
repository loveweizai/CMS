package net.mingsoft.cms.mapper;

import net.mingsoft.cms.model.Recruitment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * RecruitmentMapper数据库操作接口类
 * 
 **/

public interface RecruitmentMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	Recruitment  selectByPrimaryKey(@Param("id") Integer id);

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
	int insert(Recruitment record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(Recruitment record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(Recruitment record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(Recruitment record);

	List<Recruitment> listBySelective(Recruitment record);

	int deleteBatchBySelective(List<Recruitment> record);
}