package net.mingsoft.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mingsoft.cms.mapper.RecruitmentMapper;
import net.mingsoft.cms.model.Recruitment;
import net.mingsoft.cms.service.RecruitmentService;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {

	@Autowired
	private RecruitmentMapper recruitmentMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return recruitmentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Recruitment record) {
		// TODO Auto-generated method stub
		return recruitmentMapper.insert(record);
	}

	@Override
	public int insertSelective(Recruitment record) {
		return recruitmentMapper.insertSelective(record);
	}

	@Override
	public Recruitment selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return recruitmentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Recruitment record) {
		// TODO Auto-generated method stub
		return recruitmentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Recruitment record) {
		// TODO Auto-generated method stub
		return recruitmentMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Recruitment> listBySelective(Recruitment record) {
		// TODO Auto-generated method stub
		return recruitmentMapper.listBySelective(record);
	}

	@Override
	public int deleteBatchBySelective(List<Recruitment> record) {
		// TODO Auto-generated method stub
		return recruitmentMapper.deleteBatchBySelective(record);
	}
}