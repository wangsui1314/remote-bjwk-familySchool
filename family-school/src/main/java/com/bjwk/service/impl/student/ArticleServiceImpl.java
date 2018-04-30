package com.bjwk.service.impl.student;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.bjwk.controller.student.article.ArticleController;
import com.bjwk.dao.ArticleDao;
import com.bjwk.model.Article;
import com.bjwk.service.student.article.ArticleService;
import com.bjwk.utils.CallStatusEnum;
import com.bjwk.utils.DataWrapper;
import com.bjwk.utils.DateUtils;
import com.bjwk.utils.Page;

/** 
 * @Description: 美文阅读实现类
 * @author  Desolation
 * @email:1071680460@qq.com
 * @date 创建时间：2018年4月29日 下午5:20:58 
 * @version 1.0  
 */
@Service
public class ArticleServiceImpl implements ArticleService{

	private static final Log _logger = LogFactory.getLog(ArticleServiceImpl.class);

	@Autowired
	private ArticleDao articleDao;


	/**
	 * 查询美文阅读实现方法
	 * @param type 类型
	 */
	@Override
	public DataWrapper<List<Article>> findArticle(String categoryType,int numberPerPage,int currentPage) {
		_logger.info("根据"+categoryType+"查询相关美文");
		DataWrapper<List<Article>> dataWrapper = new DataWrapper<List<Article>>();
		if(numberPerPage<=0 || currentPage<=0){
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			dataWrapper.setMsg("传递的数据有误");
			return dataWrapper;
		}
		Page page=new Page();
		page.setNumberPerPage(numberPerPage);
		page.setCurrentPage(currentPage);
		page.setCurrentPage(currentPage);
		List<Article> articleList = articleDao.findArticle(categoryType,page.getCurrentNumber(),page.getNumberPerPage());
		int totalNumber=articleDao.getCount(categoryType);
		System.out.println(totalNumber);
		if(!articleList.isEmpty()){
			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
			dataWrapper.setMsg("查询成功");
			dataWrapper.setData(articleList);
			
			dataWrapper.setPage(page, totalNumber);
		}else {
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			dataWrapper.setMsg("暂未有该数据");
		}
		return dataWrapper;
	}


	/**
	 * 获取美文详情实现方法
	 */
	@Override
	public DataWrapper<Article> getArticleDetails(int articleId) {
		_logger.info("获取美文详情");
		DataWrapper<Article> dataWrapper = new DataWrapper<Article>();

		Article articleDetails = articleDao.findArticleById(articleId);
		if(articleDetails != null){
			articleDao.addBrowseVolume(articleId);
			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
			dataWrapper.setMsg("获取美文成功");
			dataWrapper.setData(articleDetails);
		}else {
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			dataWrapper.setMsg("没有该美文");
		}
		return dataWrapper;
	}

	/**
	 * 添加美文
	 * @param article 美文实体类
	 */
	@Override
	public DataWrapper<Boolean> addArticle(Article article){
		_logger.info("添加美文");
		DataWrapper<Boolean> dataWrapper = new DataWrapper<Boolean>();
		_logger.info("添加的美文数据为："+article.toString());
		boolean addResult = articleDao.insertArticle(article);
		if(addResult){
			dataWrapper.setMsg("添加文章成功");
			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
			dataWrapper.setData(addResult);
		}else {
			dataWrapper.setMsg("添加文章失败");
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			dataWrapper.setData(addResult);
		}
		return dataWrapper;
	}


	/**
	 * 修改美文
	 * @param articleId 美文Id
	 */
	@Override
	public DataWrapper<Boolean> updateArticle(Article article) {
		_logger.info("修改美文");
		DataWrapper<Boolean> dataWrapper  = new DataWrapper<Boolean>();
		_logger.info("修改的美文的内容为："+article.toString());
		boolean updateResult = articleDao.updateArticleById(article);
		if(updateResult){
			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
			dataWrapper.setMsg("修改文章成功");
			dataWrapper.setData(updateResult);
		}else {
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			dataWrapper.setMsg("修改美文失败");
			dataWrapper.setData(updateResult);
		}
		return dataWrapper;
	}

	/**
	 * 删除指定文章
	 * @param articleId 文章Id
	 * @return
	 */
	@Override
	public DataWrapper<Boolean> deleteArticle(int articleId){
		_logger.info("删除指定美文");
		DataWrapper<Boolean> dataWrapper = new DataWrapper<Boolean>();
		_logger.info("删除的美文的Id为："+articleId);
		boolean deleteResult = articleDao.deleteArticleById(articleId);
		if(deleteResult){
			dataWrapper.setCallStatus(CallStatusEnum.SUCCEED);
			dataWrapper.setMsg("删除文章成功");
			dataWrapper.setData(deleteResult);
		}else {
			dataWrapper.setCallStatus(CallStatusEnum.FAILED);
			dataWrapper.setMsg("删除文章失败");
			dataWrapper.setData(deleteResult);
		}
		return dataWrapper;
	}


}
