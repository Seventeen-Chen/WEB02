package com.uestc.mapper;

import com.uestc.pojo.Emp;
import com.uestc.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    void insertBatch(List<EmpExpr> empList);


    void deleteByEmpIds(List<Integer> ids);

    List<EmpExpr> listByEmpId(Integer id);

}
