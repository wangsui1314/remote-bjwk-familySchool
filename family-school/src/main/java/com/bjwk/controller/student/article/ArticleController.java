package com.bjwk.controller.student.article;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bjwk.model.Article;
import com.bjwk.service.student.article.ArticleService;
import com.bjwk.utils.DataWrapper;

/** 
* @Description: 美文阅读Controller层
* @author  Desolation
* @email:1071680460@qq.com
* @date 创建时间：2018年4月29日 下午5:15:32 
* @version 1.0  
*/
@Controller
@RequestMapping("api/student/article")
public class ArticleController {

	private static final Log _logger = LogFactory.getLog(ArticleController.class);
	
	@Autowired
	private ArticleService articleService;
	
	/**
	 * 查询指定条件的所有美文
	 * @param grade_id 年级id
	 * @param type_id 类型id
	 * @return
	 */
	@RequestMapping(value="_findArticle")
	@ResponseBody
	public DataWrapper<List<Article>> findArticle(int gradeId,int typeId,int numberPerPage,int currentPage){
		_logger.info("查询指定条件的所有美文");
		_logger.info("查询年级id为："+gradeId+"，类型id为："+typeId+"的所有美文");
		String categoryType = gradeId+","+typeId;
		return articleService.findArticle(categoryType,numberPerPage,currentPage); 
	}
	
	/**
	 * 获取美文详情
	 * @param articleId 美文id
	 * @return
	 */
	@RequestMapping(value="_getArticleDetails")
	@ResponseBody
	public DataWrapper<Article> getArticleDetails(int articleId){
		_logger.info("获取美文详情，接收的美文Id为："+articleId);
		
		return articleService.getArticleDetails(articleId);
	}
	
	/**
	 * 添加美文
	 * @param article 美文实体类
	 * @return
	 */
	@RequestMapping(value="_addArticle")
	@ResponseBody
	public DataWrapper<Boolean> addArticle(@ModelAttribute(value="article") Article article){
		_logger.info("添加文章，数据为："+article.toString());
		return articleService.addArticle(article);
	}
	
	/**
	 * 修改美文
	 * @param article 美文实体类
	 * @return
	 */
	@RequestMapping(value="_updateArticle")
	@ResponseBody
	public DataWrapper<Boolean> updateArticle(@ModelAttribute(value="article") Article article){
		_logger.info("修改美文，数据为："+article.toString());
		return articleService.updateArticle(article);
	}
	
	/**
	 * 删除美文
	 * @param articleId 美文Id
	 * @return
	 */
	@RequestMapping(value="_deleteArticle")
	@ResponseBody
	public DataWrapper<Boolean> deleteArticle(int articleId){
		_logger.info("删除美文 ，删除的Id为："+articleId);
		return articleService.deleteArticle(articleId);
	}
}
