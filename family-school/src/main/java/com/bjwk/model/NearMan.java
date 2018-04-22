package com.bjwk.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class NearMan {
	@NotNull(message = "用户id值不能为空")
	private String userId;
	@NotNull(message = "经度不能为空")
	private Double longitude;
	@NotNull(message = "纬度不能为空")
	private Double latitude;

	//赛选条件
	private String screen;

	/*
	 * {
        "sex":"1",
        "other":"1,3,2,4,5,6,7"
       }
	 */
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	@Override
	public String toString() {
		return "NearMan [userId=" + userId + ", longitude=" + longitude + ", latitude=" + latitude + "]";
	}


}
