package com.lxy.config.shiro;


import com.lxy.utils.JwtUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;


public class JwtFilter extends AccessControlFilter {


    private static final String ERROR_MSG_KEY = "shiroJwtErrorMsg";


    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        Cookie[] cookies = httpServletRequest.getCookies();

        if (cookies == null) {
            setErrorMsg(servletRequest, "token都不带你是傻逼妈\uD83E\uDD19");
            return false;
        }

        for (Cookie cookie : cookies) {
            if ("token".equals(cookie.getName())) {
                boolean validate = JwtUtils.isValidate(cookie.getValue());
                long expireTime = JwtUtils.getExpireTime(cookie.getValue());
//                System.out.println("过期时间为"+ Date.from(new Date().toInstant().plusMillis(expireTime)));
                System.out.println("expireTime = " + expireTime);
                System.out.println("validate = " + validate);



//                long timestamp = 1705846315000L;

                // 创建一个Date对象并设置其时间戳
                Date date = new Date(expireTime);

                // 创建一个SimpleDateFormat对象，定义日期格式
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                // 使用SimpleDateFormat将Date对象格式化为日期字符串
                String formattedDate = dateFormat.format(date);


                System.out.println("日期字符串：" + formattedDate);
                return JwtUtils.isValidate(cookie.getValue());
            }
        }


        return false;
    }


    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        setErrorMsg(servletRequest, "😠滚蛋没登陆你在这测你妈呢");

        HttpServletResponse httpServletResponse = WebUtils.toHttp(servletResponse);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write("{\"code\":401,\"msg\":\"" + getErrorMsg(servletRequest) + "\"}");

        return false;
    }

    private void setErrorMsg(ServletRequest servletRequest, String errorMsg) {
        servletRequest.setAttribute(ERROR_MSG_KEY, errorMsg);
    }

    private String getErrorMsg(ServletRequest servletRequest) {
        return (String) servletRequest.getAttribute(ERROR_MSG_KEY);
    }
}
