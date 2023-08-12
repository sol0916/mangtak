package com.delivery.springbootproject.notice.service;

import com.delivery.springbootproject.command.NoticeVO;

import java.util.ArrayList;

public interface NoticeService {

    public ArrayList<NoticeVO> getList();
    public NoticeVO getDetail(int noti_no);
    public int Modify(NoticeVO vo);
    public int noticeReg(NoticeVO vo);

    public ArrayList<NoticeVO> getListAdmin();
}
