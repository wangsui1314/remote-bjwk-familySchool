package com.bjwk.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.bjwk.model.Article;

/** 
* @Description: TODO
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年4月29日 下午5:29:14 
* @version 1.0  
*/
public interface ArticleDao {
	
	List<Article> findArticle(@Param("categoryType") String categoryType,@Param("currentNumber") int currentNumber,@Param("numberPerPage") int numberPerPage);
	
	Article findArticleById(int articleId);

	void addBrowseVolume(int articleId);
	
	boolean insertArticle(Article article);
	
	boolean updateArticleById(Article article);

	boolean deleteArticleById(int articleId);
	
	int getCount(String categoryType);
}
