package com.uestc.service.impl;

import com.uestc.service.EmpLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import  org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.uestc.mapper.EmpLogMapper;
import com.uestc.pojo.EmpLog;


@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    // requires_new表示开启一个新的事务，即使当前有事务存在，也会开启一个新的事务
    // 这样可以确保日志的插入操作不会受到其他事务的影响
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}