package com.springcloud.weibo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.springcloud.weibo.util.EncodingRequest;

public class EncodingInterceptor implements HandlerInterceptor {

    private final String TAG = EncodingInterceptor.class.getName();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String method = request.getMethod();
        System.out.println(TAG + "::preHandle:Method: " + method);
        if (method.equalsIgnoreCase("GET")) {
            EncodingRequest encodingRequest = new EncodingRequest(request, null);
            request = encodingRequest;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}
