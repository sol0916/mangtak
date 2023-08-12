package com.delivery.springbootproject.prodDelivery.service;

import com.delivery.springbootproject.command.ProductImgVO;
import com.delivery.springbootproject.command.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Mapper
public interface ProdDeliveryMapper {

    public ArrayList<ProductVO> getList(String id);
    public int statusModify(String del_no);
    public int imgReg(ProductImgVO vo);
    public ProductVO getDetail(String del_no);
    public int defectiveModify(String del_no );
    public void defectiveModifyContent(@Param("del_no")String del_no, @Param("d_content") String d_content);

    public int defectiveImg(String del_no, MultipartFile file);
    public int cancelForm(String prod_del_no);
    public ArrayList<ProductVO> clearList(String id);
    public void deleteImg(String prod_del_no);
    public ArrayList<ProductVO> defectiveList(String id);

    public int defectiveForm(String prod_del_no);
    public void defectiveDeleteImg(String prod_del_no);


}