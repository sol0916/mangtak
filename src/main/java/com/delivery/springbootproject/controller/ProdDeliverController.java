package com.delivery.springbootproject.controller;

import com.delivery.springbootproject.command.MypageVO;
import com.delivery.springbootproject.command.ProductVO;
import com.delivery.springbootproject.prodDelivery.service.ProdDeliveryMapper;
import com.delivery.springbootproject.prodDelivery.service.ProdDeliveryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/prodDeliver")
public class ProdDeliverController {

    @Autowired
    @Qualifier("proDeliveryService")
    private ProdDeliveryservice prodDeliveryservice;

    @Value("${project.upload.path}")
    private String uploadPath;

    ///////////////////////////////////////////////////////////////////////////////////////

    //배송할 물품 조회 - 택배 기사
    @GetMapping("/prodList1")
    public String prodList1(Model model, MypageVO vo,
                            HttpServletRequest request) {

        HttpSession session = request.getSession();
        String id = String.valueOf(session.getAttribute("userId"));
        ArrayList<ProductVO> list = prodDeliveryservice.getList(id);

        model.addAttribute("list", list);
        return "/prodDeliver/prodList1";
    }

    //배송완료 물품 조회 - 택배 기사
    @GetMapping("/prodList2")
    public String prodList2(Model model, MypageVO vo,
                            HttpServletRequest request) {

        HttpSession session = request.getSession();
        String id = String.valueOf(session.getAttribute("userId"));
        ArrayList<ProductVO> list = prodDeliveryservice.clearList(id);

        model.addAttribute("list", list);
        return "/prodDeliver/prodList2";
    }

    @GetMapping("/cancelForm")
    public String cancleForm(@RequestParam("prod_del_no") String prod_del_no,
                             RedirectAttributes ra){
        int result = prodDeliveryservice.cancelForm(prod_del_no);
        prodDeliveryservice.deleteImg(prod_del_no);
        String msg = result == 1 ? "물품의 배송완료가 철회되었습니다." : "물품 철회에 실패하셨습니다.";
        ra.addFlashAttribute("msg", msg);
        return "redirect:/prodDeliver/prodList2";
    }

    //불량신고 물품 조회 - 택배 기사
    @GetMapping("/prodList3")
    public String prodList3(Model model, MypageVO vo,
                            HttpServletRequest request) {

        HttpSession session = request.getSession();
        String id = String.valueOf(session.getAttribute("userId"));
        ArrayList<ProductVO> list = prodDeliveryservice.defectiveList(id);

        model.addAttribute("list", list);
        return "/prodDeliver/prodList3";
    }

    @GetMapping("/defectiveForm")
    public String defectiveForm(@RequestParam("prod_del_no")String prod_del_no,
                                RedirectAttributes ra){
        prodDeliveryservice.defectiveDeleteImg(prod_del_no);
        int result = prodDeliveryservice.defectiveForm(prod_del_no);
        String msg = result == 1 ? "정상적으로 철회되었습니다." : "철회에 실패하였습니다.";
        ra.addFlashAttribute("msg", msg);

        return "redirect:/prodDeliver/prodList3";
    }

    //배송물품 상세 조회 - 택배 기사
    @GetMapping("/prodDetail")
    public String prodDetail(@RequestParam("prod_del_no") String prod_del_no,
                             Model model) {
        ProductVO vo = prodDeliveryservice.getDetail(prod_del_no);
        model.addAttribute("vo", vo);

        return "/prodDeliver/prodDetail";
    }


    ///////////////////////////////////////////////////////////////////////////////////////


    //배송완료 처리
    @RequestMapping("/deliveryModifyForm") // 배송완료 상태로 변경, 이미지 업로드
    public String prodStatusModify(ProductVO vo,
                                   @RequestParam("file") MultipartFile file,
                                   RedirectAttributes ra,
                                   @RequestParam("del_no") String del_no) {
        System.out.println("상품번호 : " + del_no);

        int result = prodDeliveryservice.statusModify(del_no);
        String msg = result == 1 ? "배송완료 상태로 변경되었습니다." : "배송상태 변경에 실패하였습니다.";
        ra.addFlashAttribute("msg", msg);

        ////////////////////////////img upload

        if (file.getContentType().contains("image") == false) {
            ra.addFlashAttribute("msg", "jpg, png, jpeg 형식의 이미지 파일만 등록이 가능합니다.");
            return "redirect:/product/productList"; //이미지가 아니라면 list목록으로
        }

        int result2 = prodDeliveryservice.imgReg(del_no, file);


        return "redirect:/prodDeliver/prodList1";
    }

    @RequestMapping("/defectiveModifyForm")
    public String defectiveModifyForm(ProductVO vo,
                                      @RequestParam("file") MultipartFile file,
                                      RedirectAttributes ra,
                                      @RequestParam("del_no") String del_no,
                                      @RequestParam("d_content") String d_content) {
        System.out.println("상품번호 : " + del_no);
        prodDeliveryservice.defectiveModifyContent(del_no, d_content);
        int result = prodDeliveryservice.defectiveModify(del_no);
        String msg = result == 1 ? "불량상품으로 등록되었습니다." : "등록에 실패하였습니다.";
        ra.addFlashAttribute("msg", msg);

        ////////////////////////////img upload

        if (file.getContentType().contains("image") == false) {
            ra.addFlashAttribute("msg", "jpg, png, jpeg 형식의 이미지 파일만 등록이 가능합니다.");
            return "redirect:/product/productList"; //이미지가 아니라면 list목록으로
        }

        int result1 = prodDeliveryservice.defectiveImg(del_no, file);
        return "redirect:/prodDeliver/prodList1";
    }
}
