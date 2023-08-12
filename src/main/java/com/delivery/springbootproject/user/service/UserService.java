package com.delivery.springbootproject.user.service;

import com.delivery.springbootproject.command.CategoryVO;
import com.delivery.springbootproject.command.UserVO;
import com.delivery.springbootproject.util.Criteria;
import com.delivery.springbootproject.util.Criteria2;

import java.util.ArrayList;

public interface UserService {

    //id에 해당하는 pw 불러오기 - 로그인 pw확인
    public String findPw(String id);

    //비밀번호 초기화하기
    public void updatePw(String pw, String mail);

    public String findMail(String mail);

    //유저 등록
    //관리자 등록 전 user_no 불러오기
    public int getAdminUser_no();
    //관리자 등록 user_no 1~100
    public int adminRegist(UserVO vo);
    //배송기사 등록 user_no 101~
    public int deliverRegist(UserVO vo);

    //유저 조회
    //관리자 조회
    public ArrayList<UserVO> getAdminList(String user_type, Criteria2 cri);
    //배송기사 조회
    public ArrayList<UserVO> getDeliverList(String user_type, Criteria2 cri);
    //전체 페이지 조회
    public int getTotal(String user_type, Criteria2 cri);

    //user_area (대분류)
    public ArrayList<CategoryVO> getUser_area();
    //user_area_detail (중분류)
    public ArrayList<CategoryVO> getUser_area_detail(CategoryVO vo);


    //정보 불러오기
    public UserVO getInfo(int user_no);
    //유저 수정
    public int userModify(UserVO vo);
    //유저 삭제
    public void deleteUser(int user_no);

    //유저 삭제 전 fk 지우기
    public void deleteFK(int user_no);


}
