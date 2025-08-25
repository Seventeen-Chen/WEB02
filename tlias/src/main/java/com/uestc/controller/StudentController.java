package com.uestc.controller;

import com.uestc.pojo.Result;
import com.uestc.pojo.Student;
import com.uestc.pojo.StudentQueryParam;
import com.uestc.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("分页查询学生信息：{}", studentQueryParam);
        return Result.success(studentService.page(studentQueryParam));
    }

    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("添加学生信息");
        studentService.addStudent(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable("id") Long id) {
        log.info("查询学生信息，id: {}", id);
        Student student = studentService.getById(id);
        if (student != null) {
            return Result.success(student);
        } else {
            return Result.error("学生信息不存在");
        }
    }

    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("更新学生信息，id: {}", student.getId());
        studentService.updateStudent(student); // Assuming addStudent also handles updates
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Long> ids) {
        log.info("删除学生信息，id: {}", ids);
        studentService.deleteStudent(ids); // Assuming deleteStudent is implemented in StudentService
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result updateScore(@PathVariable("id") Long id, @PathVariable("score") Integer score) {
        log.info("更新学生分数，id: {}, score: {}", id, score);
        studentService.updateScore(id, score);
        return Result.success();
    }
}
