package com.delivery.springbootproject.notice.service;

import com.delivery.springbootproject.command.NoticeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public ArrayList<NoticeVO> getList() {
        return noticeMapper.getList();
    }

    @Override
    public NoticeVO getDetail(int noti_no) {
        return noticeMapper.getDetail(noti_no);
    }

    @Override
    public int Modify(NoticeVO vo) {
        return noticeMapper.Modify(vo);
    }

    @Override
    public int noticeReg(NoticeVO vo) {
        return noticeMapper.noticeReg(vo);
    }

    @Override
    public ArrayList<NoticeVO> getListAdmin() {
        return noticeMapper.getListAdmin();
    }
}
