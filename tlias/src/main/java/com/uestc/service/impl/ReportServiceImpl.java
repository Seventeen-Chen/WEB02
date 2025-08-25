package com.uestc.service.impl;

import com.uestc.mapper.ClazzMapper;
import com.uestc.mapper.EmpMapper;
import com.uestc.mapper.StudentMapper;
import com.uestc.pojo.ClazzOption;
import com.uestc.pojo.JobOption;
import com.uestc.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ReportServiceImpl implements ReportService {

    @Autowired
    EmpMapper empMapper;
    @Autowired
    ClazzMapper clazzMapper;
    @Autowired
    StudentMapper studentMapper;

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> jobData = empMapper.countEmpJobData();
        List<Object> pos = jobData.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> count = jobData.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(pos, count);
    }

    @Override
    public ClazzOption getClazzCountData() {
        List<Map<String, Object>> clazzData = clazzMapper.countClazzData();
        List<Object> clazzNames = clazzData.stream().map(dataMap -> dataMap.get("clazzName")).toList();
        List<Object> studentCounts = clazzData.stream().map(dataMap -> dataMap.get("studentCount")).toList();
        return new ClazzOption(clazzNames, studentCounts);
    }

    @Override
    public List<Map<String , Object>> getStudentDegreeData() {
        return studentMapper.getStudentDegreeData();
    }

}
