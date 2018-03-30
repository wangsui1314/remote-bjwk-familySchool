package com.bjwk.model.pojo;


import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author liqitian
 * @version 1.0
 * @describe 
 * 2018年3月22日 下午4:15:47
 */
public class Users {
    
	private Integer userId;
	@NotBlank(message = "用户名不能为空")
	private String userName; 
	@NotBlank(message = "用户密码不能为空")
	private  String passWord; 
	private String phone;
	private String email;
	@NotBlank(message = "用户身份不能为空或空串")
	private String sign;  //身份标识  0 学生 1家长，2老师
	private  int coin;//优币
	private String headPortrait;//头像
	private Integer sex;//性别
	private String nickName;//用户昵称
	private Integer professionId;//职业id
	private String background;//背景墙
	private  String styleSignTure;//个性签名
	private  String gesturePassWord;//手势密码
	private  int gestrueIsOk; //是否启用手势密码
	private Date created; //创建时间
	private Date updateTime; //修改个人资料时间


	
	
	
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public Integer getProfessionId() {
		return professionId;
	}
	public void setProfessionId(Integer professionId) {
		this.professionId = professionId;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	public String getStyleSignTure() {
		return styleSignTure;
	}
	public void setStyleSignTure(String styleSignTure) {
		this.styleSignTure = styleSignTure;
	}
	public String getGesturePassWord() {
		return gesturePassWord;
	}
	public void setGesturePassWord(String gesturePassWord) {
		this.gesturePassWord = gesturePassWord;
	}
	public int getGestrueIsOk() {
		return gestrueIsOk;
	}
	public void setGestrueIsOk(int gestrueIsOk) {
		this.gestrueIsOk = gestrueIsOk;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	 @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", passWord=" + passWord + ", phone=" + phone
				+ ", email=" + email + ", sign=" + sign + ", coin=" + coin + ", headPortrait=" + headPortrait + ", sex="
				+ sex + ", nickName=" + nickName + ", professionId=" + professionId + ", background=" + background
				+ ", styleSignTure=" + styleSignTure + ", gesturePassWord=" + gesturePassWord + ", gestrueIsOk="
				+ gestrueIsOk + ", created=" + created + ", updateTime=" + updateTime + "]";
	}
	
	
}
