package com.uestc.controller;

import com.uestc.anno.LogOperation;
import com.uestc.pojo.Emp;
import com.uestc.pojo.EmpQueryParam;
import com.uestc.pojo.PageResult;
import com.uestc.pojo.Result;
import com.uestc.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize) {
//        log.info("分页查询：{} {}", page, pageSize);
//        PageResult<Emp> pageResult = empService.page(page, pageSize);
//        return Result.success(pageResult);
//    }
    /**
     * 分页查询员工信息
     * @param empQueryParam 查询参数
     * @return 分页结果
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询：{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @LogOperation
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工：{}", emp);
        empService.save(emp);
        return Result.success();
    }

    @LogOperation
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

//    @GetMapping("{id}")
//    public Result getById(@PathVariable Integer id) {
//        log.info("查询员工信息：{}", id);
//        Emp emp = empService.getById(id);
//        if (emp == null) {
//            return Result.error("员工不存在");
//        }
//        return Result.success(emp);
//    }
    @GetMapping("{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("查询员工信息：{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    @LogOperation
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("更新员工信息：{}", emp);
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list() {
        log.info("查询所有员工信息");
        List<Emp> emps = empService.list();
        return Result.success(emps);
    }
}
