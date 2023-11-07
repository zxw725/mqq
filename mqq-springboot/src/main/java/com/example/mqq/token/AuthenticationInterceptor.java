package com.example.mqq.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.mqq.entity.User;
import com.example.mqq.exception.MyTokenException;
import com.example.mqq.service.UserService;
//import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
//        token = StringUtils.substring(token,7);

        System.out.println(token);
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();
        System.out.println(token);
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
//        if (method.isAnnotationPresent(UserLoginToken.class)){
//            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
//
//        }
        if (token == null){
            System.out.println("无效token，请重新登录");
            throw new MyTokenException(400,"无效token，请重新登录");
        }
        if(token.startsWith("Bearer")){
            token = StringUtils.substring(token,7);
        }
        try {
            JWT.decode(token);
        } catch (JWTDecodeException j) {
            System.out.println("401");
            throw new MyTokenException(401,"无效token，请重新登录");
        }
//        String id = TokenTool.getId(token);
//        User userInfo = userService.getById(id);
//        System.out.println(TokenTool.getUsername(token));
//        if (userInfo == null){
//            System.out.println("token用户不存在，请重新登入");
//            throw new MyTokenException(402,"token用户不存在，请重新登入");
//        }

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(TokenTool.SECRET)).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            System.out.println("token密码错误，请重新登入");
            throw new MyTokenException(403,"token密码错误，请重新登入");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
