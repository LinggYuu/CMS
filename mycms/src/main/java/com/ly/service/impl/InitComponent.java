package com.ly.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ly.entity.ArcType;
import com.ly.entity.Article;
import com.ly.entity.Link;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.ly.service.ArcTypeService;
import com.ly.service.ArticleService;
import com.ly.service.LinkService;
//所有最新的信息都是放在Init类中
/**
 * 初始化组件
 * @author user
 *
 */
@Component("initComponet")
public class InitComponent implements ApplicationContextAware,ServletContextListener{

	private static ApplicationContext applicationContext;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		this.applicationContext=applicationContext;
		System.out.println(this);
	}
	
	/**
	 * 刷新application缓存方法
	 * @param application
	 */
	public void refreshSystem(ServletContext application){

		//获取各类bean然后放到application的属性中
		//启动的时候就有值了

		LinkService linkService=(LinkService) applicationContext.getBean("linkService");
		//得到bean后调用service方法
		List<Link> linkList=linkService.list(null);
		application.setAttribute("linkList", linkList);

		ArcTypeService arcTypeService=(ArcTypeService) applicationContext.getBean("arcTypeService");
		List<ArcType> arcTypeList=arcTypeService.list(null);
		application.setAttribute("arcTypeList", arcTypeList);
		
		ArticleService articleService=(ArticleService) applicationContext.getBean("articleService");
		List<Article> newestArticleList=articleService.getNewest(); // 获取最新7条帖子
		application.setAttribute("newestArticleList", newestArticleList);
		
		List<Article> recommendArticleList=articleService.getRecommend(); 
		application.setAttribute("recommendArticleList", recommendArticleList);
		
		List<Article> slideArticleList=articleService.getSlide();
		application.setAttribute("slideArticleList", slideArticleList);
		
		List allIndexArticleList=new ArrayList();
		// 存储多个集合 每个集合里是指定类型的最新8个帖子
		if(arcTypeList!=null && arcTypeList.size()!=0){
			//				遍历所有内容类型
			for(int i=0;i<arcTypeList.size();i++){
				//get(i)方法用于取得list的第i个对象
				List<Article> subArticleList=articleService.getIndex(arcTypeList.get(i).getId());
				//遍历出的每条数据都add到allIndexArticle集合中
				allIndexArticleList.add(subArticleList);
			}
		}
		//将每个allindexarticlelist放到application中
		application.setAttribute("allIndexArticleList", allIndexArticleList);
	}

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext application=sce.getServletContext();
		//DEBUG原始端口号52914
		refreshSystem(application);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
