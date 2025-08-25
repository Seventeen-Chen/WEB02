package com.uestc.service;

import com.uestc.pojo.Clazz;
import com.uestc.pojo.ClazzQueryParam;
import com.uestc.pojo.PageResult;

import java.util.List;

public interface ClazzService {

    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void addClazz(Clazz clazz);

    Clazz getById(Integer id);

    void updateClazz(Clazz clazz);

    void deleteClazz(Integer id);

    List<Clazz> listAll();
}
