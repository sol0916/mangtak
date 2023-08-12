package com.delivery.springbootproject.prodAdmin.service;

import java.util.ArrayList;
import java.util.List;

import com.delivery.springbootproject.command.ProductVO;
import com.delivery.springbootproject.util.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProdAdminMapper {

	public ArrayList<ProductVO> getList01(@Param("prod_status")String prod_status,
										  @Param("cri") Criteria cri);

	public int getTotal(@Param("prod_status")String prod_status,
						@Param("cri") Criteria cri);

	public ArrayList<ProductVO> getList02(@Param("prod_status")String prod_status,
										  @Param("cri") Criteria cri);

	public ArrayList<ProductVO> getList03(@Param("prod_status")String prod_status,
										  @Param("cri") Criteria cri);

//   public ArrayList<ProductVO> getListArea(@Param("prod_status") String prod_status, 
//                                 @Param("check") ArrayList<String> check);

	public ArrayList<ProductVO> getDetail(String del_no);

	public int deleteData(String del_no);

}