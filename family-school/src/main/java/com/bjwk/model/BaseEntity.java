package com.bjwk.model;

import com.bjwk.model.listener.BaseEntityListener;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description :
 * @Author : wangsui
 * @Date : 16:57 2018/6/29
 **/

@Data
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
public abstract class BaseEntity implements Serializable {

    /**
     * 首次插入时间
     */
    @Column(name = "create_time", nullable = false, columnDefinition = "DATE COMMENT '插入时间'")
    protected Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "update_time", columnDefinition = "DATE COMMENT '最后修改时间'")
    protected Date updateTime;


}