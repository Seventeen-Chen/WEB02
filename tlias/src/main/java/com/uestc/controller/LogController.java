package com.uestc.controller;

import com.uestc.pojo.OperateLog;
import com.uestc.pojo.PageResult;
import com.uestc.pojo.Result;
import com.uestc.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogController {
    @Autowired
    private LogService logService; // 假设有一个LogService来处理日志相关的业务逻辑


    @GetMapping("/log/page")
    public Result logPage(@RequestParam int page, @RequestParam int pageSize) {
        log.info("访问日志页面");
        PageResult<OperateLog> page1 = logService.page(page, pageSize);
        return Result.success(page1);
    }
}
