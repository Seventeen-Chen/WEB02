package com.uestc.controller;

import com.uestc.pojo.ClazzOption;
import com.uestc.pojo.JobOption;
import com.uestc.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import com.uestc.service.ReportService;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("empGenderData")
    public Result empGenderData() {
        log.info("查询员工性别统计数据");
        List<Map<String, Object>> genderlist = reportService.getEmpGenderData();
        return Result.success(genderlist);
    }

    @GetMapping("empJobData")
    public Result empJobData() {
        log.info("查询员工职位统计数据");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/studentCountData")
    public Result studentCountData() {
        log.info("查询班级人数");
        ClazzOption clazzOption = reportService.getClazzCountData();
        return Result.success(clazzOption);
    }

    @GetMapping("/studentDegreeData")
    public Result studentDegreeData() {
        log.info("查询学生学位统计数据");
        List<Map<String, Object>> degreeList = reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }
}
