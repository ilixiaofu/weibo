package com.springcloud.weibo.interceptor;

import com.springcloud.weibo.constant.ViewResourceConstant;
import com.springcloud.weibo.pojo.Admin;
import com.springcloud.weibo.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckLoginInterceptor implements HandlerInterceptor {

    private final String TAG = CheckLoginInterceptor.class.getName();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println(TAG + "::preHandle");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Admin admin = (Admin) session.getAttribute("admin");
        if (user != null || admin != null) {
            return true;
        } else {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/" + ViewResourceConstant.HOME_PAGE);
            return false;
        }
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
