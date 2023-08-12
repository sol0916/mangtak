package com.delivery.springbootproject.controller;

import com.delivery.springbootproject.command.CategoryVO;
import com.delivery.springbootproject.command.UserVO;
import com.delivery.springbootproject.user.service.UserService;
import com.delivery.springbootproject.util.Criteria;
import com.delivery.springbootproject.util.Criteria2;
import com.delivery.springbootproject.util.PageVO;
import com.delivery.springbootproject.util.PageVO2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    @Qualifier("userService")
    UserService userService;



    @GetMapping("/userReg")
    public String userReg(Model model,
                          CategoryVO vo) {

        ArrayList<CategoryVO> user_area_list = userService.getUser_area();
        model.addAttribute("user_area_list", user_area_list);

        return "user/userReg";
    }

    @GetMapping("getCategory/{a}/{b}/{c}")
    public ResponseEntity<ArrayList<CategoryVO>> getCategory(@PathVariable("a") String group_id,
                                                             @PathVariable("b") int category_lv,
                                                             @PathVariable("c") int category_detail_lv) {



        CategoryVO vo = CategoryVO.builder()
                .group_id(group_id)
                .category_lv(category_lv)
                .category_detail_lv(category_detail_lv)
                .build();

        ArrayList<CategoryVO> list = userService.getUser_area_detail(vo);

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/registForm")
    public String resist(UserVO vo,
                         Model model,
                         RedirectAttributes ra,
                         @RequestParam("user_area") String user_area,
                         @RequestParam(value="user_area_detail", required=false) String user_area_detail) {


        if(vo.getUser_type().equals("관리자")) {

            int index = userService.getAdminUser_no();

            if(index>99) {

                vo.setUser_no(index +1 );
                vo.setUser_id("admin" + (index + 1) );
                vo.setUser_name("admin" + (index + 1) );
                vo.setUser_pw("admin");
                vo.setUser_area("null");
                vo.setUser_jobassign("관리");

                int result = userService.adminRegist(vo);

                String msg = result == 1 ? "새로운 관리자가 등록되었습니다\n 최초의 비밀번호는 'admin'입니다.\n 바로 수정해주십시오" : "등록이 되지 않았습니다. 다시 시도해주십시오";
                ra.addFlashAttribute("msg", msg);
                System.out.println("실행되니?");

            }
            return "redirect:/user/adminList";

        } else {

            String pw = vo.getUser_phone().substring(vo.getUser_phone().length() - 4);
            vo.setUser_pw(pw);
            vo.setUser_area(user_area+" "+user_area_detail);

            int result = userService.deliverRegist(vo);

            String msg = result == 1 ? "새로운 배송기사가 등록되었습니다\n 최초의 비밀번호는 핸드폰 번호 뒷 4자리 입니다.\n 바로 수정해주십시오" : "등록이 되지 않았습니다. 다시 시도해주십시오";
            ra.addFlashAttribute("msg", msg);

            return "redirect:/user/deliverList";
        }

    }




    @GetMapping("/adminList")
    public String adminList(UserVO vo ,Model model, Criteria2 cri) {

        ArrayList<UserVO> list = userService.getAdminList(vo.getUser_type(), cri);

        int total = userService.getTotal(list.get(0).getUser_type(), cri);
        PageVO2 pageVO2 = new PageVO2(cri, total);

        model.addAttribute("list", list);
        model.addAttribute("pageVO", pageVO2);


        return "user/adminList";
    }

    @GetMapping("/deliverList")
    public String deliverList(UserVO vo, Model model, Criteria2 cri) {

        System.out.println("배송기사"+vo.getUser_type());
        ArrayList<UserVO> list = userService.getDeliverList(vo.getUser_type(), cri);

        int total = userService.getTotal(list.get(0).getUser_type(), cri);
        PageVO2 pageVO2 = new PageVO2(cri, total);

        model.addAttribute("list", list);
        model.addAttribute("pageVO", pageVO2);


        return "user/deliverList";
    }

    @GetMapping("/userModify")
    public String userModify(@RequestParam("user_no") int user_no,
                             @RequestParam("user_type") String user_type,
                             Model model){


        UserVO vo = userService.getInfo(user_no);
        model.addAttribute("vo", vo);

        //지역 나누기
        String[] arr = vo.getUser_area().split(" ");
        String user_area = arr[0];
        String user_area_detail = arr[1];
        model.addAttribute("user_area", user_area);
        model.addAttribute("user_area_detail", user_area_detail);


        ArrayList<CategoryVO> user_area_list = userService.getUser_area();
        model.addAttribute("user_area_list", user_area_list);

        return "user/userModify";
    }

    @PostMapping("/modifyForm")
    public String modifyUser(UserVO vo, RedirectAttributes ra,
                             @RequestParam("user_area") String user_area,
                             @RequestParam("user_area_detail") String user_area_detail) {

        vo.setUser_area(user_area+" "+user_area_detail);
        int result = userService.userModify(vo);
        String msg = result == 1 ? "수정되었습니다" : "수정 실패. 관리자에게 문의주십시오";

        ra.addFlashAttribute("msg", msg);

        System.out.println(vo.getUser_type());

        if(vo.getUser_type().equals("관리자")) {
            return "redirect:/user/adminList";
        } else {
            return "redirect:/user/deliverList";
        }


    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("user_no") int user_no,
                             @RequestParam("user_type") String user_type) {


        userService.deleteFK(user_no);
        userService.deleteUser(user_no);

        if(user_type.equals("관리자")) {
            return "redirect:/user/adminList";
        } else {
            return "redirect:/user/deliverList";
        }

    }
}
