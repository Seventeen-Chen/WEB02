package com.uestc.mapper;

import com.uestc.pojo.Clazz;
import com.uestc.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzMapper {
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    void insert(Clazz clazz);

    Clazz getById(Integer id);

    void updateClazz(Clazz clazz);

    void delete(Integer id);

    boolean haveStudents(Integer id);

    List<Clazz> listAll();

    List<Map<String, Object>> countClazzData();
}
