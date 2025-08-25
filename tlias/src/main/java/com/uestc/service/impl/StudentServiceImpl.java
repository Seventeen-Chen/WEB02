package com.uestc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uestc.mapper.StudentMapper;
import com.uestc.pojo.PageResult;
import com.uestc.pojo.Student;
import com.uestc.pojo.StudentQueryParam;
import com.uestc.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        log.info("分页查询学生信息：{}", studentQueryParam);
        List<Student> list = studentMapper.list(studentQueryParam);
        Page<Student> p = (Page<Student>) list;
        return new PageResult<Student>(p.getTotal(), p.getResult());
    }

    @Override
    public void addStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public Student getById(Long id) {
        log.info("查询学生信息，id: {}", id);
        return studentMapper.getById(id);
    }

    @Override
    public void updateStudent(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        log.info("更新学生信息，id: {}", student.getId());
        studentMapper.update(student); // Assuming insert handles both add and update
    }

    @Override
    public void deleteStudent(List<Long> ids) {
        // Assuming there's a delete method in the mapper
        studentMapper.delete(ids);
    }

    @Override
    public void updateScore(Long id, Integer score) {
        studentMapper.updateScore(id, score);
    }
}

