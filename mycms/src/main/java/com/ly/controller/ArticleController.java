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
 * ����Controller��
 * @author user
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

	@Resource
	private ArticleService articleService;
	
	@RequestMapping("/{id}")
	//id����·������ȥ���������������id������{id}��ȡ��
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
		//����ʵ��
		article.setClick(article.getClick()+1);
		//����һ�μ�һ�η�����
		articleService.update(article);
		mav.addObject("pageCode", this.genUpAndDownPageCode(articleService.getLastArticle(id), articleService.getNextArticle(id), request.getServletContext().getContextPath()));
		ArcType arcType=article.getArcType();
		mav.addObject("navCode", NavUtil.genArticleNavigation(arcType.getTypeName(), arcType.getId(), article.getTitle()));
		mav.setViewName("article");
		//ת��·��������ȫд��springmvc������
		return mav;
	}
	
	/**
	 * ��ȡ��һƪ���Ӻ���һƪ���Ӵ���
	 * @param lastArticle
	 * @param nextArticle
	 * @param projectContext
	 * @return
	 */
	public String genUpAndDownPageCode(Article lastArticle,Article nextArticle,String projectContext){
		StringBuffer pageCode=new StringBuffer();
		if(lastArticle==null || lastArticle.getId()==null){
			pageCode.append("<p>��һƪ��û����</p>");
		}else{
			pageCode.append("<p>��һƪ��<a href='"+projectContext+"/article/"+lastArticle.getId()+".html'>"+lastArticle.getTitle()+"</a></p>");
		}
		
		if(nextArticle==null || nextArticle.getId()==null){
			pageCode.append("<p>��һƪ��û����</p>");
		}else{
			pageCode.append("<p>��һƪ��<a href='"+projectContext+"/article/"+nextArticle.getId()+".html'>"+nextArticle.getTitle()+"</a></p>");
		}
		return pageCode.toString();
	}
	
}
