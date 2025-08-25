package com.uestc.service;

import com.uestc.pojo.OperateLog;
import com.uestc.pojo.PageResult;

public interface LogService {
    PageResult<OperateLog> page(Integer page, Integer pageSize);
}
