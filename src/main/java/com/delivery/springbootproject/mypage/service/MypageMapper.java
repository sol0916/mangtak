package com.delivery.springbootproject.mypage.service;

import com.delivery.springbootproject.command.MypageVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MypageMapper {

    public MypageVO getInfo(String user_id); // 마이페이지 정보가져오기
    public int InfoModify(MypageVO vo);

}
