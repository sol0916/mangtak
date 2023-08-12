package com.delivery.springbootproject.prodAdmin.service;

import java.util.ArrayList;

import com.delivery.springbootproject.command.ProductVO;
import com.delivery.springbootproject.prodAdmin.service.ProdAdminService;
import com.delivery.springbootproject.util.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ProdAdminService")
public class ProdAdminServiceImpl implements ProdAdminService {

	@Autowired
	private ProdAdminMapper ProdAdminMapper;

	@Override
	public ArrayList<ProductVO> getList01(String prod_status, Criteria cri) {

		return ProdAdminMapper.getList01(prod_status, cri);
	}

	@Override
	public int getTotal(String prod_status, Criteria cri) {
		return ProdAdminMapper.getTotal(prod_status, cri);
	}

	@Override
	public ArrayList<ProductVO> getList02(String prod_status, Criteria cri) {
		return ProdAdminMapper.getList02(prod_status, cri);
	}

	@Override
	public ArrayList<ProductVO> getList03(String prod_status, Criteria cri) {
		return ProdAdminMapper.getList03(prod_status, cri);
	}

	@Override
	public ArrayList<ProductVO> getDetail(String del_no) {
		return ProdAdminMapper.getDetail(del_no);
	}

	@Override
	public int deleteData(String del_no) {
		return ProdAdminMapper.deleteData(del_no);
	}

//   @Override
//   public ArrayList<ProductVO> getListArea(String prod_status,ArrayList<String> check) {
//      
//      return ProdAdminMapper.getListArea(prod_status,check);
//   }



}