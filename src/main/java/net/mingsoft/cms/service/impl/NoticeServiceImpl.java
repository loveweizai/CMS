package net.mingsoft.cms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mingsoft.cms.mapper.NoticeMapper;
import net.mingsoft.cms.model.Notice;
import net.mingsoft.cms.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return noticeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Notice record) {
		// TODO Auto-generated method stub
		return noticeMapper.insert(record);
	}

	@Override
	public int insertSelective(Notice record) {
		// TODO Auto-generated method stub
		return noticeMapper.insertSelective(record);
	}

	@Override
	public Notice selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return noticeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Notice record) {
		// TODO Auto-generated method stub
		return noticeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Notice record) {
		// TODO Auto-generated method stub
		return noticeMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Notice> listBySelective(Notice record) {
		// TODO Auto-generated method stub
		return noticeMapper.listBySelective(record);
	}

	@Override
	public int deleteBatchBySelective(List<Notice> record) {
		// TODO Auto-generated method stub
		return noticeMapper.deleteBatchBySelective(record);
	}
}