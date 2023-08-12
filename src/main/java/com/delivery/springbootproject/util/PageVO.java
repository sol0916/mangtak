package com.delivery.springbootproject.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data //getter, setter, toString
public class PageVO {
	
	private int start; //시작페이지 번호
	private int end; //끝페이지 네이션
	private boolean prev; //이전버튼 활성화 여부
	private boolean next; //다음버튼 활성화 여부
	private int total; // 전체 게시글개수 
	private int realEnd; //실제 보여지는 끝번호 
	
	private int page; //cri에 있는 현재 조회하는 페이지 
	private int amount; //cri에 있는 데이터개수 
	private Criteria cri; //페이지 기준 
	
	private int pnCount = 10; //페이지네이션 개수
	
	private List<Integer> pageList;
	
	//페이진이션 클래스는 cri와total을 매개변수로 받음 
	public PageVO(Criteria cri, int total) {
		
		this.cri = cri;
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.total = total;
	
	this.end = (int)(Math.ceil(this.page / (double)this.pnCount)) * this.pnCount ;

	this.start = this.end - this.pnCount + 1;

	this.realEnd = (int)(Math.ceil(this.total/(double)this.amount));
	
	
	if(this.end > this.realEnd) {
		
		this.end = this.realEnd;
		
	}
	
	this.prev = this.start > 1;
	

	this.next = this.realEnd > this.end;
	
	//타임리프 - list에 페이지네션으 담음
	this.pageList = IntStream.rangeClosed(this.start, this.end).boxed().collect(Collectors.toList());
	
	
	
	
	
	
	}
	
}

