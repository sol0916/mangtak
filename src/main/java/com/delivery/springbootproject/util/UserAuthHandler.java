package com.delivery.springbootproject.util;

import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class UserAuthHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("userAuthHandler동작함");
        System.out.println("==============================");

        //세션검사 or 파라미터 검사
        HttpSession session = request.getSession();

        if(session.getAttribute("userId") != null) {  //로그인이 된 사람
            return true;
        } else {    //로그인이 안된경우
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();

            String htmlcode = "<script> \r\n"
                    + "alert('잘못된 접근입니다. 로그인 하세요.'); \r\n"
                    + "location.href='/login'"
                    + "</script>";

            out.print(htmlcode);
            out.flush();
            return false;
        }
    }

}
