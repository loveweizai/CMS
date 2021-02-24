package net.mingsoft.cms.mapper;

import net.mingsoft.cms.model.RecruitmentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * RecruitmentInfoMapper数据库操作接口类
 * 
 **/

public interface RecruitmentInfoMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	RecruitmentInfo  selectByPrimaryKey(@Param("id") Integer id);

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
	int insert(RecruitmentInfo record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	int insertSelective(RecruitmentInfo record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	int updateByPrimaryKeySelective(RecruitmentInfo record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	int updateByPrimaryKey(RecruitmentInfo record);

	List<RecruitmentInfo> listBySelective(RecruitmentInfo record);

	int deleteBatchBySelective(List<RecruitmentInfo> record);

	void deleteAll();

	int updateByCode(RecruitmentInfo recruitmentInfo);
}