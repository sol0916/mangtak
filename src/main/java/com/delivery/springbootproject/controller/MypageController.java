package com.delivery.springbootproject.controller;

import com.delivery.springbootproject.command.MypageVO;
import com.delivery.springbootproject.mypage.service.MypageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/mypage")
public class MypageController {
    @Autowired
    @Qualifier("mypageService")
    private MypageService mypageService;

    @RequestMapping("/mypageInfo")
    public String mypageInfo(Model model, HttpSession session) {
        System.out.println(session.getAttribute("userId"));
        String id = String.valueOf(session.getAttribute("userId"));
        MypageVO vo = mypageService.getInfo(id);
        model.addAttribute("vo", vo);
        return "/mypage/mypageInfo";
    }

    @RequestMapping("/mypageInfo2")
    public String mypageInfo2(Model model, HttpSession session) {
        System.out.println(session.getAttribute("userId"));
        String id = String.valueOf(session.getAttribute("userId"));
        MypageVO vo = mypageService.getInfo(id);
        model.addAttribute("vo", vo);
        return "/mypage/mypageInfo2";
    }


    @RequestMapping("/mypageModify") // 정보수정
    public String modify(MypageVO vo, Model model) {
        model.addAttribute("vo", vo);

        return "/mypage/mypageModify";
    }

    @RequestMapping("/mypageModify2") // 정보수정
    public String modify2(MypageVO vo, Model model) {
        model.addAttribute("vo", vo);

        return "/mypage/mypageModify2";
    }

    @RequestMapping("/modifyForm")
    public String modifyForm(@Valid @ModelAttribute("vo") MypageVO vo, Errors errors,
                             RedirectAttributes ra,
                             Model model) {
        if (errors.hasErrors()) {
            List<FieldError> list = errors.getFieldErrors();
            for (FieldError err : list) {
                if (err.isBindingFailure()) {
                    model.addAttribute("valid_" + err.getField(), "잘못된 값 입력입니다.");
                } else {
                    model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
                }
            }
            return "mypage/mypageModify"; // 실패시 원래 화면으로
        }
        int result = mypageService.InfoModify(vo);
        String msg = result == 1 ? "정보 변경에 성공하셨습니다." : "정보 변경에 실패하셨습니다.";

        ra.addFlashAttribute("msg", msg);
        return "redirect:/mypage/mypageInfo";
    }

    @RequestMapping("/modifyForm2")
    public String modifyForm2(@Valid @ModelAttribute("vo") MypageVO vo, Errors errors,
                             RedirectAttributes ra,
                             Model model) {
        if (errors.hasErrors()) {
            List<FieldError> list = errors.getFieldErrors();
            for (FieldError err : list) {
                if (err.isBindingFailure()) {
                    model.addAttribute("valid_" + err.getField(), "잘못된 값 입력입니다.");
                } else {
                    model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
                }
            }
            return "mypage/mypageModify2"; // 실패시 원래 화면으로
        }
        int result = mypageService.InfoModify(vo);
        String msg = result == 1 ? "정보 변경에 성공하셨습니다." : "정보 변경에 실패하셨습니다.";

        ra.addFlashAttribute("msg", msg);
        return "redirect:/mypage/mypageInfo2";
    }

}
