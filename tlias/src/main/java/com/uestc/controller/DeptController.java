package com.uestc.controller;

import com.uestc.anno.LogOperation;
import com.uestc.pojo.Dept;
import com.uestc.pojo.Result;
import com.uestc.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping("/depts")
    public Result findAll() {
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }


    // 简单参数怎么接受，就是直接用?传递的数据
    // HttpServletRequest request这个对象会保存传递的所有参数，随后根据getParameter方法获取，都是string类型的数据，类型要转换

    // @RequestParam("id") Integer id 这个注解可以直接获取传递的参数,会自动进行类型转换
    // 这个时候参数必须传递不传递就会报错，可以加required = false, 这样就可以不传递参数了

    // 要是定义的形参和请求参数名相同，可以省略@RequestParam注解
    // 这里的@RequestParam("id")可以省略，直接用Integer id就可以了，因为前端传递的参数名就是id
    @LogOperation
    @DeleteMapping("/depts")
    public Result delete(@RequestParam(value = "id", required = false) Integer id) {
        deptService.deleteById(id);
        return Result.success();
    }

    @LogOperation
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept) {
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable("id") Integer id) {
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @LogOperation
    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept) {
        deptService.update(dept);
        return Result.success();
    }
}
