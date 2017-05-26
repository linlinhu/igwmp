package com.emin.wxs.util;

import com.emin.base.util.CookieUtil;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AuthorWxRestaurantInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = Logger.getLogger(AuthorWxRestaurantInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {

        String url = request.getRequestURI();
        logger.info("微信餐厅拦截器请求路径=====" + url);
        String methodName = "";
        methodName = url.substring(url.lastIndexOf("/") + 1);
        logger.info("微信餐厅拦截器--执行方法：" + methodName);
        List<String> acceptMethods = new ArrayList<String>();
        acceptMethods.add("index.do");
        acceptMethods.add("login.do");
        acceptMethods.add("sendVerifyCode.do");

        if (!acceptMethods.contains(methodName)) {
            String res = CookieUtil.getValue(request, "restaurants");
            logger.info("Cookie中的值  restaurants===" + res);
            String value = CookieUtil.getValue(request, "restaurantLogin");
            if (StringUtils.isEmpty(value) || StringUtils.isEmpty(res)) {
                response.sendRedirect(request.getContextPath() + "/resLog/index.do");
                return false;
            }
        }

        return true;
    }

}
