package com.bjwk.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class NearUsers {

	private String userId;
	private Double distance;
	
	private String userName; 
	
	private String sign;  //身份标识  0 学生 1家长，2老师
	
	private String headPortrait;//头像
	private Integer sex;//性别
	
	private String nickName;//用户昵称
	private  String styleSignTure;//个性签名
	
	private Date created; //创建时间
    
	List<HashMap<String,Object>> lableMapList;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getStyleSignTure() {
		return styleSignTure;
	}

	public void setStyleSignTure(String styleSignTure) {
		this.styleSignTure = styleSignTure;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public List<HashMap<String, Object>> getLableMapList() {
		return lableMapList;
	}

	public void setLableMapList(List<HashMap<String, Object>> lableMapList) {
		this.lableMapList = lableMapList;
	}

	@Override
	public String toString() {
		return "NearUsers [userId=" + userId + ", distance=" + distance + ", userName=" + userName + ", sign=" + sign
				+ ", headPortrait=" + headPortrait + ", sex=" + sex + ", nickName=" + nickName + ", styleSignTure="
				+ styleSignTure + ", created=" + created + ", lableMapList=" + lableMapList + "]";
	}
	
	
}
