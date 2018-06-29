package com.bjwk.service.student.article;

import java.util.List;

import com.bjwk.model.Article;
import com.bjwk.utils.DataWrapper;
import com.github.pagehelper.PageInfo;

/** 
* @Description: 美文阅读实现类接口
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年4月29日 下午5:19:47 
* @version 1.0  
*/
public interface ArticleService {
	
	public DataWrapper<PageInfo<Article>> findArticle(String categoryType, int numberPerPage, int currentPage);

	public DataWrapper<Article> getArticleDetails(int articleId);
	
	public DataWrapper<Boolean> addArticle(Article article);
	
	public DataWrapper<Boolean> updateArticle(Article article);
	
	public DataWrapper<Boolean> deleteArticle(int articleId);

}
