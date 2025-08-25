package com.uestc.controller;

import com.uestc.pojo.Emp;
import com.uestc.pojo.Result;
import com.uestc.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@Slf4j
public class UplodeController {

//    private static final String UPLOAD_DIR = "D:/Net/Java_Code/MAVEN/WEB02/tlias/images/";
//
//    @PostMapping("/uplode")
//    public Result uplode(String name , Integer age, MultipartFile file) throws Exception {
//        if (!file.isEmpty()) {
//            String originalFilename = file.getOriginalFilename();
//            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
//            String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + extName;
//
//            File targetFile = new File(UPLOAD_DIR + uniqueFileName);
//            if(!targetFile.getParentFile().exists()) {
//                targetFile.getParentFile().mkdirs(); // 创建目录
//            }
//            file.transferTo(targetFile);
//        }
//        return Result.success();
//    }


    @Autowired
    private AliyunOSSOperator ossOperator;
    @PostMapping("/uplode")
    public Result uplode(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return Result.error("文件不能为空");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            return Result.error("文件名不能为空");
        }

        log.info("文件上传成功: {}", file.getOriginalFilename());

        String url = ossOperator.upload(file.getBytes(), file.getOriginalFilename());

        log.info("文件上传到OSS成功: {}", url);

        return Result.success(url);
    }

}
