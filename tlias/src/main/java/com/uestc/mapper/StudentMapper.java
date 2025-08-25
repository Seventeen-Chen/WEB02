package com.uestc.mapper;

import com.uestc.pojo.Student;
import com.uestc.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> list(StudentQueryParam studentQueryParam);

    void insert(Student student);

    Student getById(Long id);

    void update(Student student);

    void delete(List<Long> ids);

    void updateScore(Long id, Integer score);

    List<Map<String , Object>> getStudentDegreeData();
}
