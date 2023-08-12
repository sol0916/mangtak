package com.delivery.springbootproject.controller;

import com.delivery.springbootproject.command.NoticeVO;
import com.delivery.springbootproject.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Controller
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    @Qualifier("noticeService")
    private NoticeService noticeService;

    //공지등록 : 관리자에만 필요
    @GetMapping("/noticeReg")
    public String noticeReg() {
        return "/notice/noticeReg";
    }

    @RequestMapping("/noticeRegForm")
    public String noticeRegForm(NoticeVO vo,
                                RedirectAttributes ra){
        int result = noticeService.noticeReg(vo);
        String msg = result == 1 ? "등록이 완료되었습니다." : "등록에 실패하였습니다.";
        ra.addFlashAttribute("msg", msg);

        return "redirect:/notice/noticeListAdmin";
    }

    //공지조회 : 관리자, 택배기사 모두
    @GetMapping("/noticeListAdmin")
    public String noticeListAdmin(Model model) {
        ArrayList<NoticeVO> list = noticeService.getListAdmin();
        model.addAttribute("list", list);
        return "/notice/noticeListAdmin";
    }

    //공지조회 : 관리자, 택배기사 모두
    @GetMapping("/noticeList")
    public String noticeList(Model model) {
        ArrayList<NoticeVO> list = noticeService.getList();
        model.addAttribute("list", list);
        return "/notice/noticeList";
    }

    @RequestMapping("/noticeModify")
    public String noticeModify (@RequestParam("noti_no") int noti_no,
                                Model model){
        NoticeVO vo = noticeService.getDetail(noti_no);
        model.addAttribute("vo", vo);
        return"/notice/noticeModify";
    }


    @GetMapping("/noticeDetail")
    public String noticeDetail(@RequestParam("noti_no") int noti_no,
                               Model model){
        NoticeVO vo = noticeService.getDetail(noti_no);
        model.addAttribute("vo", vo);
        return "/notice/noticeDetail";
    }

    @RequestMapping("/noticeModifyForm")
    public String noticeModifyForm(NoticeVO vo,
                                   RedirectAttributes ra){

        int result = noticeService.Modify(vo);

        String msg = result == 1 ? "수정에 성공하셨습니다." : "수정에 실패하셨습니다. 관리자에게 문의해주세요.";
        ra.addFlashAttribute("msg", msg);

        return "redirect:/notice/noticeListAdmin";
    }

    @RequestMapping("/noticeTest")
    public String noticeTest(){
        return "/notice/noticeTest";
    }


}
