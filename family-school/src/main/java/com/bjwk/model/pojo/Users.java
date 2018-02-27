package com.bjwk.model.pojo;

public class Users {
  
	private String userName; 
	private  String passWord; 
	private String email; 
	private String sign;
	private String created;
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
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	@Override
	public String toString() {
		return "Users [userName=" + userName + ", passWord=" + passWord + ", email=" + email + ", sign=" + sign
				+ ", created=" + created + "]";
	}
	
}
