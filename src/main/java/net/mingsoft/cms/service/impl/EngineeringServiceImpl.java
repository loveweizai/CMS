package net.mingsoft.cms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mingsoft.cms.mapper.EngineeringMapper;
import net.mingsoft.cms.model.Engineering;
import net.mingsoft.cms.service.EngineeringService;

@Service
public class EngineeringServiceImpl implements EngineeringService {

	@Autowired
	private EngineeringMapper engineeringMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return engineeringMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Engineering record) {
		// TODO Auto-generated method stub
		return engineeringMapper.insert(record);
	}

	@Override
	public int insertSelective(Engineering record) {
		return engineeringMapper.insertSelective(record);
	}

	@Override
	public Engineering selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return engineeringMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Engineering record) {
		// TODO Auto-generated method stub
		return engineeringMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Engineering record) {
		// TODO Auto-generated method stub
		return engineeringMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Engineering> listBySelective(Engineering record) {
		// TODO Auto-generated method stub
		return engineeringMapper.listBySelective(record);
	}

	@Override
	public int deleteBatchBySelective(List<Engineering> record) {
		// TODO Auto-generated method stub
		return engineeringMapper.deleteBatchBySelective(record);
	}

	@Override
	public int insertBatch(List<Engineering> record) {
		// TODO Auto-generated method stub
		return engineeringMapper.insertBatch(record);
	}

	@Override
	public Engineering selectBySelective(Engineering record) {
		// TODO Auto-generated method stub
		return engineeringMapper.selectBySelective(record);
	}

	@Override
	public List<Map> listCityByProvinceId(Engineering record) {
		// TODO Auto-generated method stub
		return engineeringMapper.listCityByProvinceId(record);
	}
	
}