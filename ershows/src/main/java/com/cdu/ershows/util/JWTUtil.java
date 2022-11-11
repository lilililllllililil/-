package com.cdu.ershows.util;

import com.cdu.ershows.pojo.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.UUID;

public class JWTUtil {

   private static long time= 1000*60*60*24;
   private static String signature="admin-test";

   public static String creatToken(User user){
      JwtBuilder jwtBuilder= Jwts.builder();
      String jwtToken=jwtBuilder
            //header
            .setHeaderParam("typ","JWT")
            .setHeaderParam("alg","HS256")
            //payload
            .claim("email",user.getUsername())
            .claim("password",user.getPassword())
            .setSubject("admin-test")
            .setExpiration(new Date(System.currentTimeMillis()+time))
            //signature
            .setId(UUID.randomUUID().toString())
            .signWith(SignatureAlgorithm.HS256,signature)
            .compact();
      return jwtToken;
   }
}
