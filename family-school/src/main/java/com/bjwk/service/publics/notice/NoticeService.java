package com.bjwk.service.publics.notice;

import com.bjwk.model.notice.NoticeEntity;

/**
 * @Description :
 * @Author : wangsui
 * @Date : 17:49 2018/6/29
 **/
public interface NoticeService {

    NoticeEntity findNoticeDetails(int noticeId);
}
