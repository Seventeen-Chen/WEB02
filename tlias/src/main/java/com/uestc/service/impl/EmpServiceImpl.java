package com.uestc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.uestc.mapper.EmpExprMapper;
import com.uestc.mapper.EmpMapper;
import com.uestc.pojo.*;
import com.uestc.service.EmpLogService;
import com.uestc.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Map;
import com.uestc.utils.JwtUtils;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // 这里应该调用mapper层的分页查询方法
        // 由于没有提供mapper层的实现，这里暂时返回一个空的PageResult对象
//        Long total = empMapper.count();
//        List<Emp> list = empMapper.list((page-1)*pageSize, pageSize);
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        List<Emp> list = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>) list;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }


    // 这是事务管理的注解，表示这个方法是一个事务，sql里面可以用start transaction和commit,rollback等语句
    @Transactional
    @Override
    public void save(Emp emp) {
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            List<EmpExpr> exprList = emp.getExprList();

            if (!CollectionUtils.isEmpty(exprList)) {
                for (EmpExpr expr : exprList) {
                    expr.setEmpId(emp.getId());
                }
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工" + emp);
            empLogService.insertLog(empLog);
        }

    }

    @Override
    @Transactional(rollbackFor =  Exception.class)
    public void delete(List<Integer> ids) {
        // 删除员工信息
        empMapper.deleteByIds(ids);
        // 删除员工的工作经历
        empExprMapper.deleteByEmpIds(ids);

        EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "删除员工ID列表：" + ids);
        empLogService.insertLog(empLog);
    }

    @Override
    public Emp getById(Integer id) {
        // 查询员工信息
        Emp emp = empMapper.getById(id);
        if (emp != null) {
            // 查询员工的工作经历
            List<EmpExpr> exprList = empExprMapper.listByEmpId(id);
            emp.setExprList(exprList);
        }
        return emp;
    }

    @Override
    public Emp getInfo(Integer id) {
        // 查询员工信息

        return empMapper.getInfo(id);
    }

    @Override
    @Transactional
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        // 更新员工的工作经历
        empExprMapper.deleteByEmpIds(Collections.singletonList(emp.getId()));

        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            for (EmpExpr expr : exprList) {
                expr.setEmpId(emp.getId());
            }
            empExprMapper.insertBatch(exprList);
        }

        EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "更新员工信息：" + emp);
        empLogService.insertLog(empLog);
    }

    @Override
    public List<Emp> list() {
        // 查询所有员工信息
        return empMapper.listAll();
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp empLogin = empMapper.getByUsernameAndPassword(emp);
        if(empLogin != null) {
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("id", empLogin.getId());
            dataMap.put("username", empLogin.getUsername());

            String jwt = JwtUtils.generateJwt(dataMap);
            return new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), jwt);
        }
            return null;
    }
}
