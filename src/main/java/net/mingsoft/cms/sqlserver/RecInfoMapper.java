package net.mingsoft.cms.sqlserver;

import net.mingsoft.cms.model.OrgView;
import net.mingsoft.cms.model.RecInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * OrgViewMapper数据库操作接口类
 * 
 **/

public interface RecInfoMapper {


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	RecInfo selectByPrimaryKey(@Param("id") String id);

	List<RecInfo> listBySelective(RecInfo record);
}