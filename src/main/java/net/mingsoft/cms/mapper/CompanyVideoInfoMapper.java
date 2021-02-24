package net.mingsoft.cms.mapper;

import net.mingsoft.cms.model.CompanyVideoInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * CompanyVideoInfoMapper数据库操作接口类
 * 
 **/

public interface CompanyVideoInfoMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	CompanyVideoInfo  selectByPrimaryKey(@Param("id") Integer id);

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
	int insert(CompanyVideoInfo record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(CompanyVideoInfo record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(CompanyVideoInfo record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(CompanyVideoInfo record);

	List<CompanyVideoInfo> listBySelective(CompanyVideoInfo record);

    int deleteBatchBySelective(List<CompanyVideoInfo> record);
}