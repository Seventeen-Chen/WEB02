package com.uestc.controller;

import com.uestc.anno.LogOperation;
import com.uestc.pojo.*;
import com.uestc.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("分页查询：{}", clazzQueryParam);
        PageResult<Clazz> page = clazzService.page(clazzQueryParam);
        return Result.success(page);
    }

    @LogOperation
    @PostMapping
    public Result addClazz(@RequestBody Clazz clazz) {
        log.info("新增班级：{}", clazz);
        clazz.setUpdateTime(LocalDateTime.now());
        clazz.setCreateTime(LocalDateTime.now());
        clazzService.addClazz(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询班级信息：{}", id);
        Clazz clazz = clazzService.getById(id);
        if (clazz == null) {
            return Result.error("班级不存在");
        }
        return Result.success(clazz);
    }

    @LogOperation
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("更新班级信息：{}", clazz);
        clazz.setUpdateTime(LocalDateTime.now());
        clazzService.updateClazz(clazz);
        return Result.success();
    }

    @LogOperation
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级：{}", id);
        clazzService.deleteClazz(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result listAll(){
        log.info("查询所有班级");
        List<Clazz> clazzs = clazzService.listAll();
        return Result.success(clazzs);
    }

}
