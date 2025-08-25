package com.uestc.service;
import java.util.List;
import java.util.Map;

import com.uestc.pojo.ClazzOption;
import com.uestc.pojo.JobOption;

public interface ReportService {
    /**
     * 统计员工性别信息
     */
    List<Map<String, Object>> getEmpGenderData();
    /**
     * 统计各个职位的员工人数
     * @return
     */
    JobOption getEmpJobData();

    ClazzOption getClazzCountData();

    List<Map<String, Object>> getStudentDegreeData();

}
