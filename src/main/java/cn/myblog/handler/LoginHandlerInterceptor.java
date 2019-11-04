package cn.myblog.handler;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Object user = request.getSession().getAttribute("user");
//        if (user == null) {
//            request.setAttribute("msg","请先登录");
//            request.getRequestDispatcher("/admin").forward(request,response);
//            return false;
//        }
//
//        return true;

        String token = request.getHeader("token");
        if (token != null) {

        }

        request.setAttribute("msg","请先登录");
        request.getRequestDispatcher("/admin").forward(request,response);
        return false;
    }
}
