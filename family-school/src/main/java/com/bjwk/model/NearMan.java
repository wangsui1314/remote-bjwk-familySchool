package com.bjwk.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class NearMan {
	@NotNull(message = "用户id值不能为空")
	private Integer userId;
	@NotBlank(message = "名称不能为空")
	private String name;
	@NotNull(message = "城市编码不能为空")
	private Integer cityCode;
	@NotNull(message = "地区编码不能为空")
	private Integer adCode;
	@NotNull(message = "经度不能为空")
	private Double longitude;
	@NotNull(message = "纬度不能为空")
	private Double latitude;
	//两经纬度点之间的距离
	private  Double distance;
	
	
	public NearMan(Integer userId,String name,Double distance){
		this.userId=userId;
		this.name=name;
		this.distance=distance;
	}
	public NearMan(){}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCityCode() {
		return cityCode;
	}
	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}
	public Integer getAdCode() {
		return adCode;
	}
	public void setAdCode(Integer adCode) {
		this.adCode = adCode;
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
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	@Override
	public String toString() {
		return "NearMan [userId=" + userId + ", name=" + name + ", cityCode=" + cityCode + ", adCode=" + adCode
				+ ", longitude=" + longitude + ", latitude=" + latitude + ", distance=" + distance + "]";
	}
	
	
}
