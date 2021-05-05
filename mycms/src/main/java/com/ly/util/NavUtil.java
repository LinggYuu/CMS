package com.ly.util;

/**
 * 导航工具类
 * @author user
 *传入参数，直接生成一个串
 */
public class NavUtil {
//通过调用propertiesUtil
	/**
	 * 生成帖子列表导航
	 * @param typeName
	 * @return
	 */
	public static String genArticleListNavigation(String typeName){
		//这个是给arctype页面用的
		StringBuffer navCode=new StringBuffer();
//		append用于拼接
		navCode.append("当前位置：&nbsp;&nbsp;");
		navCode.append("<a href='"+PropertiesUtil.getValue("host")+"'>主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;");
		//读取了host属性
		navCode.append(typeName);
		//搞到类别
		return navCode.toString();
	}
	
	/**
	 * 生成帖子详细页导航
	 * @param typeName
	 * @param typeId
	 * @param articleTitle
	 * @return
	 */
	public static String genArticleNavigation(String typeName,Integer typeId,String articleTitle){
		//给article页面用的
		StringBuffer navCode=new StringBuffer();
		navCode.append("当前位置：&nbsp;&nbsp;");
		navCode.append("<a href='"+PropertiesUtil.getValue("host")+"'>主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;");
		navCode.append("<a href='"+PropertiesUtil.getValue("host")+"/arcType/"+typeId+".html'>"+typeName+"</a>&nbsp;&nbsp;>&nbsp;&nbsp;"+articleTitle);
		return navCode.toString();
		//传入参数，直接生成一个字符串
	}
}
