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
//�������µ���Ϣ���Ƿ���Init����
/**
 * ��ʼ�����
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
	 * ˢ��application���淽��
	 * @param application
	 */
	public void refreshSystem(ServletContext application){

		//��ȡ����beanȻ��ŵ�application��������
		//������ʱ�����ֵ��

		LinkService linkService=(LinkService) applicationContext.getBean("linkService");
		//�õ�bean�����service����
		List<Link> linkList=linkService.list(null);
		application.setAttribute("linkList", linkList);

		ArcTypeService arcTypeService=(ArcTypeService) applicationContext.getBean("arcTypeService");
		List<ArcType> arcTypeList=arcTypeService.list(null);
		application.setAttribute("arcTypeList", arcTypeList);
		
		ArticleService articleService=(ArticleService) applicationContext.getBean("articleService");
		List<Article> newestArticleList=articleService.getNewest(); // ��ȡ����7������
		application.setAttribute("newestArticleList", newestArticleList);
		
		List<Article> recommendArticleList=articleService.getRecommend(); 
		application.setAttribute("recommendArticleList", recommendArticleList);
		
		List<Article> slideArticleList=articleService.getSlide();
		application.setAttribute("slideArticleList", slideArticleList);
		
		List allIndexArticleList=new ArrayList();
		// �洢������� ÿ����������ָ�����͵�����8������
		if(arcTypeList!=null && arcTypeList.size()!=0){
			//				����������������
			for(int i=0;i<arcTypeList.size();i++){
				//get(i)��������ȡ��list�ĵ�i������
				List<Article> subArticleList=articleService.getIndex(arcTypeList.get(i).getId());
				//��������ÿ�����ݶ�add��allIndexArticle������
				allIndexArticleList.add(subArticleList);
			}
		}
		//��ÿ��allindexarticlelist�ŵ�application��
		application.setAttribute("allIndexArticleList", allIndexArticleList);
	}

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext application=sce.getServletContext();
		//DEBUGԭʼ�˿ں�52914
		refreshSystem(application);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
