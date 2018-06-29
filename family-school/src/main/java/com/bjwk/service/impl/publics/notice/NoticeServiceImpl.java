package com.bjwk.service.impl.publics.notice;

import com.bjwk.dao.NoticeDao;
import com.bjwk.model.notice.NoticeEntity;
import com.bjwk.service.publics.notice.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description :
 * @Author : wangsui
 * @Date : 17:50 2018/6/29
 **/
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    public NoticeEntity findNoticeDetails(int noticeId){
        return noticeDao.findNoticeById(noticeId);
    }
}
