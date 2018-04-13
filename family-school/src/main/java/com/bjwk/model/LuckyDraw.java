package com.bjwk.model;

import java.io.Serializable;

/** 
* @Description: 奖项model层
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年4月2日 下午10:43:58 
* @version 1.0  
*/
public class LuckyDraw implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int prizeId;
	private String prizeName;
	private String prizeGrade;
	private double prizeProbability;
	
	
	public int getPrizeId() {
		return prizeId;
	}
	public void setPrizeId(int prizeId) {
		this.prizeId = prizeId;
	}
	public String getPrizeName() {
		return prizeName;
	}
	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}
	public String getPrizeGrade() {
		return prizeGrade;
	}
	public void setPrizeGrade(String prizeGrade) {
		this.prizeGrade = prizeGrade;
	}
	public double getPrizeProbability() {
		return prizeProbability;
	}
	public void setPrizeProbability(double prizeProbability) {
		this.prizeProbability = prizeProbability;
	}
	
}
