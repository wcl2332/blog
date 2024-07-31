package com.wangchenglong.myblog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangchenglong.myblog.constant.enums.ErrorCode;
import com.wangchenglong.myblog.model.entity.User;
import com.wangchenglong.myblog.model.vo.Result;
import com.wangchenglong.myblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Wangchenglong
 * @Date: 2023/7/26 20:53
 * @Description: TODO
 */
public class JWTUtils {
    private static final String JWTKEY = "123456!@###$$";
    private static final long TIME_UNIT = 1000;

    @Resource
    UserService userService;
    /**
     * 创建token
     *
     * @param user
     * @return
     */
    public static String createToken(User user) {
        String token = JWT.create()
                .withClaim("userid", user.getId())
                .withClaim("account", user.getAccount())
                .withExpiresAt(new Date(System.currentTimeMillis() + TIME_UNIT * 60 * 60 * 24 *7))
                .sign(Algorithm.HMAC256(JWTKEY));
        return token;
    }

    /**
     * @param token
     * @return
     */
    public static DecodedJWT verify(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JWTKEY)).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        return verify;
    }

    /**
     * @param token
     * @return
     */
    public static Long getUserInfo(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JWTKEY)).build();
        DecodedJWT verify = jwtVerifier.verify(token);
        Claim claim = verify.getClaim("userid");
        Long userid = claim.asLong();
        return userid;
    }


    public static void main(String[] args) throws JsonProcessingException {
        User user =new User();
        user.setId(Long.valueOf("1"));
        user.setAccount("18855073372");
        String token = createToken(user);
        System.out.println(token);
        System.out.println(verify(token));
        System.out.println(getUserInfo(token));
    }

}