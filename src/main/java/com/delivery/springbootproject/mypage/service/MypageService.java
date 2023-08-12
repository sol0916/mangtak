package com.delivery.springbootproject.mypage.service;

import com.delivery.springbootproject.command.MypageVO;

public interface MypageService {

    public MypageVO getInfo(String user_id);
    public int InfoModify(MypageVO vo);
}
