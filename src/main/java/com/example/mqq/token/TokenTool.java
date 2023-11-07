package com.example.mqq.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.mqq.entity.User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TokenTool {
    public final static String SECRET = "authByZhuang";
    public static String getToken(User userInfo){

        String token = "";
        token = JWT.create()
//                .withClaim("id",userInfo.getId())
                .withClaim("username",userInfo.getUsername())
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    public static Map<String, Claim> verifyToken(String token){
        DecodedJWT jwt;
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        jwt = verifier.verify(token);
        return jwt.getClaims();
    }

    public static String getId(String token){
        Map<String, Claim> claimMap = verifyToken(token);
        Claim id_claim = claimMap.get("id");
        System.out.println(token);
        return id_claim.asString();
    }
//    public static String getNickName(String token){
//        Map<String, Claim> stringClaimMap = verifyToken(token);
//        Claim nickname = stringClaimMap.get("nickname");
//        return nickname.asString();
//    }

    public static String getUsername(String token){
        Map<String, Claim> stringClaimMap = verifyToken(token);
        Claim username = stringClaimMap.get("username");
        return username.asString();
    }

}
