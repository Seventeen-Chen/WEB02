package com.uestc.service;

import com.uestc.pojo.EmpQueryParam;
import com.uestc.pojo.LoginInfo;
import com.uestc.pojo.PageResult;
import com.uestc.pojo.Emp;

import java.util.List;

public interface EmpService {
    /**
     * 分页查询员工信息
     *
     * @return 分页结果
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工信息
     *
     * @param emp 员工信息
     */
    void save(Emp emp);

    /**
     * 删除员工信息
     *
     * @param ids 员工ID列表
     */
    void delete(List<Integer> ids);

    Emp getById(Integer id);

    Emp getInfo(Integer id);

    void update(Emp emp);

    List<Emp> list();

    LoginInfo login(Emp emp);
}
