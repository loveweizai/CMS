package net.mingsoft.cms.mapper;

import net.mingsoft.cms.model.CompanyStructure;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * CompanyStructureMapper数据库操作接口类
 * 
 **/

public interface CompanyStructureMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CompanyStructure  selectByPrimaryKey(@Param("id") Integer id);

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
	int insert(CompanyStructure record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(CompanyStructure record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(CompanyStructure record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(CompanyStructure record);

	List<CompanyStructure> listBySelective(CompanyStructure record);

    int deleteBatchBySelective(List<CompanyStructure> record);
}