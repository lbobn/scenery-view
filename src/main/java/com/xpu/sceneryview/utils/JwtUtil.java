package com.xpu.sceneryview.utils;

import com.xpu.sceneryview.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description
 * @Author lubb
 * @create 2024-04-01 16:38
 */
public class JwtUtil {
    private static long time = 1000 * 60 * 60 * 24 * 7;
    private static String signature = "admimn";

    public static String createToken(String jsonString) {
        JwtBuilder jwtBuilder = Jwts.builder();
        Map<String,Object> m = new HashMap<>();
        m.put("user",jsonString);
        String jwtToken = jwtBuilder
                //header
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                //payload
                .addClaims(m)
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .setId(UUID.randomUUID().toString())
                //signature
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
        return jwtToken;
    }

    public static Claims parseToken(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signature)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
