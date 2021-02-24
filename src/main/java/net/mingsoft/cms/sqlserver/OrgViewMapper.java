package net.mingsoft.cms.sqlserver;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.mingsoft.cms.model.OrgView;

/**
 * 
 * OrgViewMapper数据库操作接口类
 * 
 **/

public interface OrgViewMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	OrgView  selectByPrimaryKey(@Param("id") Integer id);

	List<OrgView> listBySelective(OrgView record);
}