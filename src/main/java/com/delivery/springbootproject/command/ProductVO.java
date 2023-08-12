package com.delivery.springbootproject.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductVO {

	private Integer prod_no;
	private String prod_regdate;
	private String prod_del_no;
	private String prod_type;
	private String prod_name;
	private String prod_deliver;
	private String prod_area;
	private String shipdate;
	private String prod_status;
	private String prod_location;
	private String prod_bad_yn;
	private String area_category;
	private String prod_msg;
	private Integer user_no;


	//join결과
	private String user_name;
	private String user_area;
	private String user_phone;
	private String cus_phone;
	private String cus_addr;
	private String cus_product;
	private String cus_name;
	private String cus_type;
	private int img_no; // pk
	private String img_name; // 실제 파일명
	private String img_path; // 폴더명
	private String img_uuid; // 난수값
	private LocalDateTime img_regdate;
	private String img_type;
	private String img_del_no;
	private String d_content;



}
