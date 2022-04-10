package com.cqjtu.mindassess.util;

import cn.hutool.core.lang.Assert;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhangning
 *
 */
public abstract class JWTUtil {


    public static final String DEFAULT_SALT = "defaultSALT";

    public static final Long DEFAULT_TIMEOUT = 7 * 24 * 60 * 60 * 1000L;

    /**
     * 创建一个简单的JWT字符串
     * @param claims 负载
     * @param timeout 过期时间
     * @param salt 盐
     * @return - JWT字符串
     */
    public static String createSimpleJwtString(Map<String,Object> claims, Long timeout,String salt) {
        if( timeout <0 ){
            throw new RuntimeException("过期时间必须设置");
        }
        SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;

        long now = System.currentTimeMillis();
        long expire = now + timeout;

        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setExpiration(new Date(expire))
                .setIssuedAt(new Date(now))
                .signWith(hs256, salt);

        return jwtBuilder.compact();

    }


    /**
     * 验证JWT的合法性
     * @param jwtStr -jwt字符串
     * @param salt - 盐
     * @return - 合法返回Claims 不合法返回null
     */
    public static Claims verifyJwt(String jwtStr,String salt) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(salt)
                    .parseClaimsJws(jwtStr)
                    .getBody();
        }catch (Exception e){
            claims = null;
        }
        return claims;
    }

    public static void main(String[] args) {
        // test
        Map<String,Object> map = new HashMap<>();
        map.put("tokenName","X-Token");
        map.put("tokenValue","f1c42e97-dbc1-4c4c-a664-53dc9a10a8c5");

        String salt = "asd46ad";
        Long timeout = 100000000L;

        String accessToken = createSimpleJwtString(map, timeout, salt);
        System.out.println("生成的JWT 字符串 : " + accessToken);

        Claims claims = verifyJwt(accessToken, salt);
        if( EmptyChecker.isEmpty(claims) ){
            System.out.println("解析失败");
        }else{
            System.out.println("解析成功");
            String getTokenName = (String) claims.get("tokenName");
            String getTokenValue = (String) claims.get("tokenValue");
            Date expiration = claims.getExpiration();

            System.out.println("解析的载荷 tokenName: " + getTokenName);
            System.out.println("解析的载荷 tokenValue: " + getTokenValue);
            System.out.println("解析的载荷 expiration: " + expiration);
        }


    }
}
