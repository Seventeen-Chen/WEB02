package com.uestc.service;

import com.uestc.pojo.PageResult;
import com.uestc.pojo.Student;
import com.uestc.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void addStudent(Student student);

    Student getById(Long id);

    void updateStudent(Student student);

    void deleteStudent(List<Long> ids);

    void updateScore(Long id, Integer score);
}
