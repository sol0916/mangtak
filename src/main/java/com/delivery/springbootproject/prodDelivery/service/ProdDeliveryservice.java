package com.delivery.springbootproject.prodDelivery.service;

import com.delivery.springbootproject.command.ProductVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface ProdDeliveryservice {
    public ArrayList<ProductVO> getList(String id);
    public ArrayList<ProductVO> clearList(String id);
    public int statusModify(String del_no);
    public int imgReg(String img_del_no, MultipartFile file);
    public ProductVO getDetail(String del_no);
    public int defectiveModify(String del_no);
    public void defectiveModifyContent(String del_no, String d_content);

    public int defectiveImg(String del_no, MultipartFile file);
    public int cancelForm(String prod_del_no);
    public void deleteImg(String prod_del_no);
    public ArrayList<ProductVO> defectiveList(String id);
    public void defectiveDeleteImg(String prod_del_no);
    public int defectiveForm(String prod_del_no);
}
