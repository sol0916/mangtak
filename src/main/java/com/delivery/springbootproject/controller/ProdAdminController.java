package com.delivery.springbootproject.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.delivery.springbootproject.command.ProductVO;
import com.delivery.springbootproject.prodAdmin.service.ProdAdminService;
import com.delivery.springbootproject.user.service.UserService;
import com.delivery.springbootproject.util.Criteria;
import com.delivery.springbootproject.util.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/prodAdmin")
public class ProdAdminController {

    @Autowired
    @Qualifier("ProdAdminService")
    ProdAdminService ProdAdminService;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Value("${project.upload.path}")
    private String uploadPath;

    public String del_id;

    @GetMapping("/prodList1")
    public String prodManage01(String prod_status,
                               ProductVO vo,
                               Model model,
                               Criteria cri,
                               @RequestParam(name = "check", required = false) ArrayList<String> check){

        prod_status="배송중";

        if (check != null) {
            cri.setCheck(check);
        }

        model.addAttribute("check",check);

        ArrayList<ProductVO> list = ProdAdminService.getList01(prod_status,cri);

        model.addAttribute("vo", vo);
        model.addAttribute("list",list);

        int total = ProdAdminService.getTotal(prod_status, cri);
        PageVO pageVO = new PageVO(cri,total);

        model.addAttribute("pageVO", pageVO);

        return "prodAdmin/prodList1";
    }


    @GetMapping("/prodList2")
    public String ProdManage02(String prod_status,
                               ProductVO vo,
                               Model model,
                               Criteria cri,
                               @RequestParam(name = "check", required = false) ArrayList<String> check) {

        prod_status="배송완료";
        if (check != null) {
            cri.setCheck(check);
        }

        model.addAttribute("check",check);

        ArrayList<ProductVO> list = ProdAdminService.getList02(prod_status,cri);
        model.addAttribute("vo", vo);
        model.addAttribute("list",list);

        int total = ProdAdminService.getTotal(prod_status, cri);
        PageVO pageVO = new PageVO(cri,total);

        model.addAttribute("pageVO", pageVO);

        return "prodAdmin/prodList2";
    }


    //배송물품 상세 조회 - 관리자
    @GetMapping("/prodDetail")
    public String prodDetail(@RequestParam("prod_del_no") String prod_del_no,
                             Model model,
                             HttpSession session) {
        ArrayList<ProductVO> list = ProdAdminService.getDetail(prod_del_no);
        String id = String.valueOf(session.getAttribute("userId"));
        String pw = userService.findPw(id);

        del_id = prod_del_no;

        model.addAttribute("list", list);
        model.addAttribute("pw", pw);
        return "/prodAdmin/prodDetail";
    }

    //이미지 src값 응답하기
    @GetMapping("display")
    public ResponseEntity<byte[]> display(@RequestParam("filename") String filename,
                                          @RequestParam("filepath") String filepath,
                                          @RequestParam("uuid") String uuid) {
        System.out.println("넘어옴");
        String path = uploadPath + "/" + filepath + "/" + uuid + "_" + filename;
        File file = new File(path);
        byte[] arr = null;


        try {
            arr = FileCopyUtils.copyToByteArray(file);  // 파일 경로 기반으로 데이터를 구인
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(arr, HttpStatus.OK);
    }

    @GetMapping("/prodList3")
    public String ProdManage03(String prod_status,
                               ProductVO vo,
                               Model model,
                               Criteria cri,
                               @RequestParam(name = "check", required = false) ArrayList<String> check) {

        prod_status="Y";
        if (check != null) {
            cri.setCheck(check);
        }

        model.addAttribute("check",check);

        ArrayList<ProductVO> list = ProdAdminService.getList03(prod_status,cri);
        model.addAttribute("vo", vo);
        model.addAttribute("list",list);

        int total = ProdAdminService.getTotal(prod_status, cri);
        PageVO pageVO = new PageVO(cri,total);

        model.addAttribute("pageVO", pageVO);

        return "prodAdmin/prodList3";
    }

    @RequestMapping("deleteForm")
    public String deleteInfo(RedirectAttributes ra,
                             @RequestParam("page") int n) {

        int res = ProdAdminService.deleteData(del_id);
        if(res == 1) {
            ra.addFlashAttribute("msg", "삭제되었습니다!");
        } else {
            ra.addFlashAttribute("msg", "삭제실패");
        }

        if(n == 1) return "redirect:/prodAdmin/prodList1";
        else if(n == 2) return "redirect:/prodAdmin/prodList2";
        else return "redirect:/prodAdmin/prodList3";
    }

}





