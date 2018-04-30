package com.bjwk.model.pojo;


public class UserLable {
	private Integer id;
	private Integer userId;
	private Integer lableId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getLableId() {
		return lableId;
	}
	public void setLableId(Integer lableId) {
		this.lableId = lableId;
	}
	@Override
	public String toString() {
		return "UserLable [id=" + id + ", userId=" + userId + ", lableId=" + lableId + "]";
	}
}
