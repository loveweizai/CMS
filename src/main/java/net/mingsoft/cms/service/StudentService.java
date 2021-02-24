package net.mingsoft.cms.service;

import net.mingsoft.cms.model.Student;

import java.util.List;

public interface StudentService {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    List<Student> listBySelective(Student record); 
    
    int deleteBatchBySelective(List<Student> record);
}