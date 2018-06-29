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
@Entity
@Table(name="notice")
public class NoticeEntity extends BaseEntity {

    @Id
    @Column(name="notice_id")
    private int noticeId;

    /**
     * 公告名称
     **/
    @Column(name = "notice_name")
    private String noticeName;

    /**
     * 公告内容
     **/
    @Column(name = "notice_content")
    private Text noticeContent;

    /**
     * 公告浏览量
     **/
    @Column(name = "browse_volume")
    private int browseVolume;

}
