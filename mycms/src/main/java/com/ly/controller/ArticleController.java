package com.ly.controller;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ly.entity.ArcType;
import com.ly.entity.Article;
import com.ly.service.ArticleService;
import com.ly.util.NavUtil;
import com.ly.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 帖子Controller层
 * @author user
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

	@Resource
	private ArticleService articleService;
	
	@RequestMapping("/{id}")
	//id，从路径里面去变量，就是下面的id被上面{id}获取了
	public ModelAndView details(@PathVariable("id") Integer id,HttpServletRequest request)throws Exception{
		ModelAndView mav=new ModelAndView();
		Article article=articleService.findById(id);
		String keyWords=article.getKeyWords();
		if(StringUtil.isNotEmpty(keyWords)){
			String arr[]=keyWords.split(" ");
			mav.addObject("keyWords", StringUtil.filterWhite(Arrays.asList(arr)));
		}else{
			mav.addObject("keyWords", null);
		}
		mav.addObject("article", article);
		//增加实体
		article.setClick(article.getClick()+1);
		//访问一次加一次访问量
		articleService.update(article);
		mav.addObject("pageCode", this.genUpAndDownPageCode(articleService.getLastArticle(id), articleService.getNextArticle(id), request.getServletContext().getContextPath()));
		ArcType arcType=article.getArcType();
		mav.addObject("navCode", NavUtil.genArticleNavigation(arcType.getTypeName(), arcType.getId(), article.getTitle()));
		mav.setViewName("article");
		//转发路径，不用全写，springmvc有设置
		return mav;
	}
	
	/**
	 * 获取下一篇帖子和上一篇帖子代码
	 * @param lastArticle
	 * @param nextArticle
	 * @param projectContext
	 * @return
	 */
	public String genUpAndDownPageCode(Article lastArticle,Article nextArticle,String projectContext){
		StringBuffer pageCode=new StringBuffer();
		if(lastArticle==null || lastArticle.getId()==null){
			pageCode.append("<p>上一篇：没有了</p>");
		}else{
			pageCode.append("<p>上一篇：<a href='"+projectContext+"/article/"+lastArticle.getId()+".html'>"+lastArticle.getTitle()+"</a></p>");
		}
		
		if(nextArticle==null || nextArticle.getId()==null){
			pageCode.append("<p>下一篇：没有了</p>");
		}else{
			pageCode.append("<p>下一篇：<a href='"+projectContext+"/article/"+nextArticle.getId()+".html'>"+nextArticle.getTitle()+"</a></p>");
		}
		return pageCode.toString();
	}
	
}
