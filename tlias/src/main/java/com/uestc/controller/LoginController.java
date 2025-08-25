package com.uestc.controller;

import com.uestc.pojo.Emp;
import com.uestc.pojo.LoginInfo;
import com.uestc.pojo.Result;
import com.uestc.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("用户登录，用户名：{}，密码：{}", emp.getUsername(), emp.getPassword());
        LoginInfo login = empService.login(emp);
        if (login == null) {
            return Result.error("用户名或密码错误");
        }
        return Result.success(login);
    }
}
