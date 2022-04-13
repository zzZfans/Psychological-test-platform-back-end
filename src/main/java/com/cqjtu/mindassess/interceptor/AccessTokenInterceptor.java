package com.cqjtu.mindassess.interceptor;

import cn.dev33.satoken.stp.StpUtil;
import com.cqjtu.mindassess.common.ApiResponse;
import com.cqjtu.mindassess.controller.SysUserController;
import com.cqjtu.mindassess.enums.AccessTokenName;
import com.cqjtu.mindassess.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhangning
 * <p>
 * 访问令牌拦截器，对于要访问对应的接口必须携带token才能访问。
 */
@Deprecated
@Slf4j
public class AccessTokenInterceptor implements HandlerInterceptor {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 在Redis中存储的SaToken中间字符串
     */
    public static final String REDIS_SA_TOKEN_MID_STR = ":login:token:";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //TODO 完成项目时，删除
        if (StpUtil.isLogin()) {
            return true;
        }
        String accessToken = request.getHeader(AccessTokenName.ACCESS_TOKEN.tokenName);
        if (accessToken == null) {
            accessToken = request.getParameter(AccessTokenName.ACCESS_TOKEN.tokenName);
        }

        if (accessToken == null) {
            standardJsonMessage(response, "请携带accessToken");
            return false;
        }

        Claims claims = JWTUtil.verifyJwt(accessToken, JWTUtil.DEFAULT_SALT);
        if (claims == null) {
            standardJsonMessage(response, "请携带合法的accessToken");
            return false;
        }

        // 判断Redis中还存在该Token
        String saTokenName = (String) claims.get(SysUserController.JWT_CLAIM_TOKEN_NAME);
        String saTokenValue = (String) claims.get(SysUserController.JWT_CLAIM_TOKEN_VALUE);

        String key = saTokenName + REDIS_SA_TOKEN_MID_STR + saTokenValue;
        String value = stringRedisTemplate.opsForValue().get(key);
        if (value == null || value.equals("-4")) {
            standardJsonMessage(response, "请携带未过期的accessToken");
            return false;
        }
        return true;
    }

    private void standardJsonMessage(HttpServletResponse response, String message) {
        PrintWriter writer = null;
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            writer = response.getWriter();
            ObjectMapper objectMapper = new ObjectMapper();
            String msg = objectMapper.writeValueAsString(ApiResponse.fail(200, message, null));
            writer.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
