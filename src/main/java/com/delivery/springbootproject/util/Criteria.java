package com.delivery.springbootproject.util;
import java.util.ArrayList;

import lombok.Data;

@Data // getter, setter, toString
public class Criteria {

	private int page; //조회하는 페이지
	private int amount; //데이터개수

	private String searchKeyword;
	private ArrayList<String> check;

	public Criteria() {

		this.page = 1;
		this.amount = 10;

	}

	//기본 생성자가 아니면 값을  전달 받음
	public Criteria(int page, int amount) {

		this.page = page;
		this.amount = amount;

	}

	//페이지 시작을 지정하는 getter
	public int getPageStart() {

		return (page -1 ) * amount;
	}
//
//	public ArrayList<String> getCheck(ArrayList<String>check) {
//
//		this.check = check;
//
//		for(int i = 0; i < check.size(); i++) {
//
//			if(check.get(i).equals("전체")) {
//
//				check.remove(i);
//			}
//		}
//
//		return check;
//
//	}
//
//



}