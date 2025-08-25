package com.uestc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uestc.mapper.OperateLogMapper;
import com.uestc.pojo.OperateLog;
import com.uestc.pojo.PageResult;
import com.uestc.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LogServiceimpl implements LogService {


    @Autowired
    private OperateLogMapper operateLogMapper;
    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {
        log.info("分页查询操作日志：{} {}", page, pageSize);
        PageHelper.startPage(page, pageSize);
        List<OperateLog> page1 = operateLogMapper.page();
        Page<OperateLog> p = (Page<OperateLog>) page1;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

}
