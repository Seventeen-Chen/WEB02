package com.uestc.mapper;

import org.apache.ibatis.annotations.*;
import com.uestc.pojo.EmpLog;

@Mapper
public interface EmpLogMapper {
    //插入日志
    @Insert("insert into emp_log (operate_time, info) values (#{operateTime}, #{info})")
    public void insert(EmpLog empLog);
}
