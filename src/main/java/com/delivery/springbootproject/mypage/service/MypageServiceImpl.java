package com.delivery.springbootproject.mypage.service;

import com.delivery.springbootproject.command.MypageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mypageService")
public class MypageServiceImpl implements MypageService{

    @Autowired
    private MypageMapper mypageMapper;

    @Override
    public MypageVO getInfo(String user_id) {
        return mypageMapper.getInfo(user_id);
    }

    @Override
    public int InfoModify(MypageVO vo) {
        return mypageMapper.InfoModify(vo);
    }
}
