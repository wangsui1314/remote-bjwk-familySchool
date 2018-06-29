package com.bjwk.model.notice;

import com.bjwk.model.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.soap.Text;
import java.util.Date;

/**
 * @Description :
 * @Author : wangsui
 * @Date : 14:50 2018/6/29
 **/

@Data
@NoArgsConstructor
public class NoticeEntity extends BaseEntity {

    private int noticeId;

    /**
     * 公告名称
     **/
    private String noticeName;

    /**
     * 公告内容
     **/
    private Text noticeContent;

    /**
     * 公告浏览量
     **/
    private int browseVolume;

}
