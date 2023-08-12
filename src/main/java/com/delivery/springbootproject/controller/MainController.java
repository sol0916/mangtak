package com.delivery.springbootproject.controller;

import com.delivery.springbootproject.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class MainController {

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    private JavaMailSender javaMailSender;

    //로그인 화면
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //택배기사 메인 페이지
    @GetMapping("/main1")
    public String main1_deliver() { return "mainpage1";}

    //관리자 메인 페이지
    @GetMapping("/main2")
    public String main2_admin() { return "mainpage2";}

    //로그인 확인 메소드
    @PostMapping("loginForm")
    public String loginForm(HttpSession session,
                            @RequestParam("userId") String userId,
                            @RequestParam("userPw") String userPw,
                            RedirectAttributes ra) {
        String pw = userService.findPw(userId);

        if(userPw.equals(pw) && !userPw.equals(null)) {
            session.setAttribute("userId", userId);
            if(userId.contains("admin")) return "redirect:/main2";
            else return "redirect:/main1";
        } else {
            ra.addFlashAttribute("msg", "입력한 정보가 맞지 않습니다");
            return "redirect:/login";
        }
    }

    //비밀번호 초기화 메소드
    @PostMapping("pwReset")
    public String pwReset(@RequestParam("mail") String mail, RedirectAttributes ra) {

        if(userService.findMail(mail) == null){
            ra.addFlashAttribute("msg", "회원에 포함되지 않는 메일 주소입니다.");
        } else {
            ra.addFlashAttribute("msg", "메일함을 확인하세요");
            Random random = new Random();
            String newpw = Integer.toString(100000 + random.nextInt(99999));

            userService.updatePw(newpw, mail);

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(mail);
            simpleMailMessage.setSubject("[맹택택배서비스] 비밀번호 초기화");
            simpleMailMessage.setText("비밀번호가 " + newpw +"로 초기화되었습니다.");
            javaMailSender.send(simpleMailMessage);
        }

        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null) {
            session.invalidate();;
        }
        return "redirect:/login";
    }

}
