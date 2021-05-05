package com.ly.util;

/**
 * 分页工具类
 * @author user
 *
 */
public class PageUtil {

	/**
	 * 生成上下页代码
	 * @param totalNum
	 * @param currentPage
	 * @param pageSize
	 * @param typeId
	 * @return
	 */
	public static String genUpAndDownPagation(int totalNum,int currentPage,int pageSize,String typeId){
		//总记录数与每页大小取余为0的话正好这么多页，否则再加一页
		int totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		//totalPage得到总共有几页
		StringBuffer pageCode=new StringBuffer();
		if(currentPage==1){
			pageCode.append("<a>上一页</a>");
			//如果是第一页就没有超链接，也就点不了
		}else{
			pageCode.append("<a href='"+PropertiesUtil.getValue("host")+"/arcType/"+typeId+".html?page="+(currentPage-1)+"'>上一页</a>");
		}
		pageCode.append("&nbsp;&nbsp;");
		if(currentPage==totalPage){
			pageCode.append("<a>下一页</a>");
		}else{
			pageCode.append("<a href='"+PropertiesUtil.getValue("host")+"/arcType/"+typeId+".html?page="+(currentPage+1)+"'>下一页</a>");
		}
		return pageCode.toString();
	}
}
