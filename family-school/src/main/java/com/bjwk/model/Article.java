package com.bjwk.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/** 
* @Description: 美文阅读实体类
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年4月29日 下午5:29:35 
* @version 1.0  
*/
public class Article implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int articleId;
	
	private String articleTitle;
	
	private String articleContent;
	
	private String articleAuthor;
	
	private String articleFrom;
	
	private Date createTime;
	
	private Date updateTime;
	
	private int articleDownload;
	
	private int browseVolume;
	
	private String categoryType;

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getArticleAuthor() {
		return articleAuthor;
	}

	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
	}

	public String getArticleFrom() {
		return articleFrom;
	}

	public void setArticleFrom(String articleFrom) {
		this.articleFrom = articleFrom;
	}

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	public int getArticleDownload() {
		return articleDownload;
	}

	public void setArticleDownload(int articleDownload) {
		this.articleDownload = articleDownload;
	}

	public int getBrowseVolume() {
		return browseVolume;
	}

	public void setBrowseVolume(int browseVolume) {
		this.browseVolume = browseVolume;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	@Override
	public String toString() {
		return "Article [articleTitle=" + articleTitle + ", articleContent=" + articleContent + ", articleAuthor="
				+ articleAuthor + ", articleFrom=" + articleFrom + ", categoryType=" + categoryType + "]";
	}
	
	
	
	
	
}
