package com.ly.controller.admin;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ly.entity.Article;
import com.ly.entity.PageBean;
import com.ly.service.ArticleService;
import com.ly.service.impl.InitComponent;
import com.ly.util.DateUtil;
import com.ly.util.ResponseUtil;
import com.ly.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 帖子后台管理Controller层
 * @author user
 *
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleAdminController {

	@Resource
	private ArticleService articleService;
	
	@Resource
	private InitComponent initComponent;
	
	/**
	 * 添加或者修改帖子信息
	 * @param article
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(@RequestParam("slideImageFile") MultipartFile slideImageFile, Article article, HttpServletResponse response, HttpServletRequest request)throws Exception{
		if(!slideImageFile.isEmpty()){
			//传图
			String filePath=request.getServletContext().getRealPath("/");
			//通过request获取绝对路径
			String imageName= DateUtil.getCurrentDateStr()+"."+slideImageFile.getOriginalFilename().split("\\.")[1];
			//imagename是通过dateutil获取的日期
			System.out.println(imageName);
			System.out.println(filePath+"static/userImages/"+imageName);
			slideImageFile.transferTo(new File(filePath+"static/userImages/"+imageName));
			article.setSlideImage(imageName);
		}
		int resultTotal=0; // 操作的记录条数
		article.setPublishDate(new Date());
		if(article.getId()==null){ // 添加
			resultTotal=articleService.add(article);
		}else{ // 修改
			resultTotal=articleService.update(article);
		}
		StringBuffer result=new StringBuffer();
		if(resultTotal>0){
			initComponent.refreshSystem(ContextLoader.getCurrentWebApplicationContext().getServletContext());
			result.append("<script language='javascript'>alert('提交成功');</script>");
		}else{
			result.append("<script language='javascript'>alert('提交失败，请联系管理员');</script>");
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 根据条件分页查询帖子信息
	 * @param page
	 * @param rows
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,Article s_article,@RequestParam(value="rows",required=false)String rows,HttpServletResponse response)throws Exception{
		//RequstParam是页面传过来的
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("title", StringUtil.formatLike(s_article.getTitle()));
		map.put("start", pageBean.getStart());
		//起始页
		map.put("size", pageBean.getPageSize());
		//每页大小
		List<Article> articleList=articleService.list(map);
		//查询到一个帖子集合
		Long total=articleService.getTotal(map);
		//记录数
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray jsonArray=JSONArray.fromObject(articleList, jsonConfig);
		//转成jsonarray

		result.put("rows", jsonArray);
		result.put("total", total);
		ResponseUtil.write(response, result);
		//write到页面
		return null;
	}
	
	/**
	 * 通过ID查找实体
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findById")
	public String findById(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		Article article=articleService.findById(Integer.parseInt(id));
		JSONObject jsonObject=JSONObject.fromObject(article);
		ResponseUtil.write(response, jsonObject);
		return null;
	}
	
	/**
	 * 删除帖子信息
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids")String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			articleService.delete(Integer.parseInt(idsStr[i]));
		}
		initComponent.refreshSystem(ContextLoader.getCurrentWebApplicationContext().getServletContext());
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
}
