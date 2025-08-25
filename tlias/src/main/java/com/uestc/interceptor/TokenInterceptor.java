package com.uestc.interceptor;

import com.uestc.utils.CurrentHolder;
import io.jsonwebtoken.Claims;
import  lombok.extern.slf4j.Slf4j;
import com.uestc.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
/**
 * 令牌校验拦截器
 */
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CurrentHolder.remove();
        // 可以在这里进行一些清理工作
        log.info("请求处理完成，清理工作完成");
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //3. 获取请求头中的令牌（token）。
        String jwt = request.getHeader("token");

        //4. 判断令牌是否存在，如果不存在，返回错误结果（未登录）。
        if(!StringUtils.hasLength(jwt)){ //jwt为空
            log.info("获取到jwt令牌为空, 返回错误结果");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return false;
        }

        //5. 解析token，如果解析失败，返回错误结果（未登录）。
        try {
            Claims claims = JwtUtils.parseJWT(jwt);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解析令牌失败, 返回错误结果");
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return false;
        }

        //6. 放行。
        log.info("令牌合法, 放行");
        return true;
    }

}
