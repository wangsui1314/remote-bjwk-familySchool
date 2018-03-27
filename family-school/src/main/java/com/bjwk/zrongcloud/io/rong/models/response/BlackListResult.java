package com.bjwk.zrongcloud.io.rong.models.response;

import com.bjwk.zrongcloud.io.rong.models.Result;
import com.bjwk.zrongcloud.io.rong.models.User;
import com.bjwk.zrongcloud.io.rong.models.user.UserModel;
import com.bjwk.zrongcloud.io.rong.util.GsonUtil;

/**
 * @author RongCloud
 */
public class BlackListResult extends Result {
    /**
     * 黑名单用户列表
     */
    UserModel[] users;

    public BlackListResult(Integer code, String errorMessage) {
        super(code, errorMessage);
    }

    public BlackListResult(Integer code, String msg, UserModel[] users) {
        super(code, msg);
        this.users = users;
    }

    public BlackListResult(UserModel[] users) {
        this.users = users;
    }

    /**
     * 获取users
     *
     * @return User[]
     */
    public UserModel[] getUsers() {
        return this.users;
    }
    /**
     * 设置users
     *
     */
    public void setUsers(UserModel[] users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return GsonUtil.toJson(this, BlackListResult.class);
    }

}
