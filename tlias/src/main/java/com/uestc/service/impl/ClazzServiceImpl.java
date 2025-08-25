package com.uestc.service.impl;

import com.github.pagehelper.PageHelper;
import com.uestc.mapper.ClazzMapper;
import com.uestc.pojo.Clazz;
import com.uestc.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uestc.pojo.ClazzQueryParam;
import com.uestc.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;
import com.github.pagehelper.Page;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        List<Clazz> list = clazzMapper.list(clazzQueryParam);
        LocalDate now = LocalDate.now();
        for (Clazz clazz : list) {
            if (clazz.getStatus() == null) {
                if(now.isAfter(clazz.getEndDate()))
                    clazz.setStatus("已结课");
                else if(now.isBefore(clazz.getBeginDate()))
                    clazz.setStatus("未开课");
                else
                    clazz.setStatus("在读中");
            }
        }
        Page<Clazz> p = (Page<Clazz>) list;
        return new PageResult<Clazz>(p.getTotal(), p.getResult());
    }

    @Override
    public void addClazz(Clazz clazz) {
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void updateClazz(Clazz clazz) {
        clazzMapper.updateClazz(clazz);
    }

    @Override
    public void deleteClazz(Integer id) {
        if(clazzMapper.haveStudents(id))
            throw new IllegalStateException("对不起该班级下有学生，无法删除");
        clazzMapper.delete(id);
    }

    @Override
    public List<Clazz> listAll(){
        return clazzMapper.listAll();
    }
}
