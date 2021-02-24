package net.mingsoft.cms.mapper;

import java.util.List;

import net.mingsoft.cms.model.Adpage;

public interface AdpageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Adpage record);

    int insertSelective(Adpage record);

    Adpage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Adpage record);

    int updateByPrimaryKey(Adpage record);

	List<Adpage> listBySelective(Adpage record);
	
	int deleteBatchBySelective(List<Adpage> id);
}