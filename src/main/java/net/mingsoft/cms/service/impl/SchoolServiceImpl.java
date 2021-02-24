package net.mingsoft.cms.service.impl;

import net.mingsoft.cms.mapper.SchoolMapper;
import net.mingsoft.cms.model.School;
import net.mingsoft.cms.service.SchoolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService{

	@Autowired
	private SchoolMapper schoolMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return schoolMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(School record) {
		// TODO Auto-generated method stub
		return schoolMapper.insert(record);
	}

	@Override
	public int insertSelective(School record) {
		// TODO Auto-generated method stub
		return schoolMapper.insertSelective(record);
	}

	@Override
	public School selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return schoolMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(School record) {
		// TODO Auto-generated method stub
		return schoolMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(School record) {
		// TODO Auto-generated method stub
		return schoolMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<School> listBySelective(School record) {
		// TODO Auto-generated method stub
		return schoolMapper.listBySelective(record);
	}

	@Override
	public int deleteBatchBySelective(List<School> record) {
		// TODO Auto-generated method stub
		return schoolMapper.deleteBatchBySelective(record);
	}
}