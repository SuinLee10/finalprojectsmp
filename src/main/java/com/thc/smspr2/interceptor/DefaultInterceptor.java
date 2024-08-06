package com.thc.smspr2.interceptor;

import com.thc.smspr2.service.TbuserService;
import com.thc.smspr2.util.TokenFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.antlr.v4.runtime.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class DefaultInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //컨트롤러 진입 전에 호출되는 메서드
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getHeader("Authorization") != null && !request.getHeader("Authorization").isEmpty()){
            TokenFactory tokenFactory = new TokenFactory();
            String tbuserId = tokenFactory.verify(request.getHeader("Authorization"));
            request.setAttribute("reqTbuserId", tbuserId);
            logger.info("0-1: tbuserId [{}]", tbuserId);
        }

        /*
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();

        logger.info("0-1: requestURI [{}]", requestURI);
        logger.info("0-1: requestMethod [{}]", requestMethod);
        logger.info("0-1: REQUEST [{}]", request.getHeaderNames());
        */
        return true;
    }

    //컨트롤러 실행 후에 호출되는 메서드
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /*
        logger.info("1-1: REQUEST [{}]", request.getAttribute("hahaha1"));
        logger.info("1-2: RESPONSE [{}]", response.getHeader("hahaha2"));
        */
    }

    //모든것을 마친 후 실행되는 메서드
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        /*
        logger.info("2-1: REQUEST [{}]", request.getAttribute("hahaha1"));
        logger.info("2-2: RESPONSE [{}]", response.getHeader("hahaha2"));
         */
    }
}
