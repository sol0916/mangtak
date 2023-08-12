package com.delivery.springbootproject.user.service;

import com.delivery.springbootproject.command.CategoryVO;
import com.delivery.springbootproject.command.UserVO;
import com.delivery.springbootproject.util.Criteria;
import com.delivery.springbootproject.util.Criteria2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public String findPw(String id) {
        return userMapper.findPw(id);
    }

    @Override
    public void updatePw(String pw, String mail) {
        ArrayList<String> list = new ArrayList<>();
        list.add(pw);
        list.add(mail);
        userMapper.updatePw(list);
    }

    @Override
    public String findMail(String mail) {
        return userMapper.findMail(mail);
    }


    //관리자 등록 전 user_no 불러오기
    @Override
    public int getAdminUser_no() {
        return userMapper.getAdminUser_no();
    }

    //관리자 등록
    @Override
    public int adminRegist(UserVO vo) {
        return userMapper.adminRegist(vo);
    }

    //배송기사 등록
    @Override
    public int deliverRegist(UserVO vo) {
        return userMapper.deliverRegist(vo);
    }

    //관리자 목록
    @Override
    public ArrayList<UserVO> getAdminList(String user_type, Criteria2 cri) {
        return userMapper.getAdminList(user_type, cri);
    }

    //배송기사 목록
    @Override
    public ArrayList<UserVO> getDeliverList(String user_type, Criteria2 cri) {
        return userMapper.getDeliverList(user_type, cri);
    }

    //전체 페이지 조회
    @Override
    public int getTotal(String user_type, Criteria2 cri) {
        return userMapper.getTotal(user_type, cri);
    }


    //user_area 대분류 불러오기
    @Override
    public ArrayList<CategoryVO> getUser_area() {
        return userMapper.getUser_area();
    }

    //user_area 중분류 불러오기
    @Override
    public ArrayList<CategoryVO> getUser_area_detail(CategoryVO vo) {
        return userMapper.getUser_area_detail(vo);
    }



    //정보 불러오기
    @Override
    public UserVO getInfo(int user_no) {
        return userMapper.getInfo(user_no);
    }

    //유저 수정
    @Override
    public int userModify(UserVO vo) {
        return userMapper.userModify(vo);
    }


    //유저 삭제
    @Override
    public void deleteUser(int user_no) {
        userMapper.deleteUser(user_no);
    }

    //유저 삭제 전 fk 지우기
    @Override
    public void deleteFK(int user_no) {
        userMapper.deleteFK(user_no);
    }
}
