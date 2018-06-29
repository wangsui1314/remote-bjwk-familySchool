package com.bjwk.model.listener;

import com.bjwk.model.BaseEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * @Description :
 * @Author : wangsui
 * @Date : 16:59 2018/6/29
 **/
public class BaseEntityListener {

    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        Date updateTime = new Date();
        entity.setUpdateTime(updateTime);
    }

    @PrePersist
    public void prePersist(BaseEntity entity) {
        Date createTime = new Date();
        if (entity.getCreateTime() == null) {
            entity.setCreateTime(createTime);
        }
    }
}
