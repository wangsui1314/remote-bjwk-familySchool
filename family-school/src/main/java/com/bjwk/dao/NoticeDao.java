package com.bjwk.dao;

import com.bjwk.model.notice.NoticeEntity;

import java.util.List;

/**
 * @Description :
 * @Author : wangsui
 * @Date : 17:12 2018/6/29
 **/
public interface NoticeDao {

    /**
     * 插入公告
     **/
    NoticeEntity insertNotice(NoticeEntity noticeEntity);

    /**
     * 删除指定公告
     **/
    boolean deleteNoticeById(int noticeId);

    /**
     * 查询所有公告
     **/
    List<NoticeEntity> findAllNotice();

    /**
     * 根据id更改公告信息
     **/
    boolean updateNoticeById(NoticeEntity noticeEntity);

    /**
     * 查询公告详情
     **/
    NoticeEntity findNoticeById(int noticeId);

    /**
     * 增加公告下载量
     **/
    void addBrowseVolume(int noticeId);
}
