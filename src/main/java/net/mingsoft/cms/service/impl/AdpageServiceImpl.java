package net.mingsoft.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mingsoft.cms.mapper.AdpageMapper;
import net.mingsoft.cms.model.Adpage;
import net.mingsoft.cms.service.AdpageService;

@Service
public class AdpageServiceImpl implements AdpageService{

	@Autowired
	private AdpageMapper adpageMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return adpageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Adpage record) {
		// TODO Auto-generated method stub
		return adpageMapper.insert(record);
	}

	@Override
	public int insertSelective(Adpage record) {
		// TODO Auto-generated method stub
		return adpageMapper.insertSelective(record);
	}

	@Override
	public Adpage selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return adpageMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Adpage record) {
		// TODO Auto-generated method stub
		return adpageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Adpage record) {
		// TODO Auto-generated method stub
		return adpageMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Adpage> listBySelective(Adpage record) {
		// TODO Auto-generated method stub
		return adpageMapper.listBySelective(record);
	}

	@Override
	public int deleteBatchBySelective(List<Adpage> record) {
		// TODO Auto-generated method stub
		return adpageMapper.deleteBatchBySelective(record);
	}
}