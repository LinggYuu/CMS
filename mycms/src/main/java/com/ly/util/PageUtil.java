package com.ly.util;

/**
 * ��ҳ������
 * @author user
 *
 */
public class PageUtil {

	/**
	 * ��������ҳ����
	 * @param totalNum
	 * @param currentPage
	 * @param pageSize
	 * @param typeId
	 * @return
	 */
	public static String genUpAndDownPagation(int totalNum,int currentPage,int pageSize,String typeId){
		//�ܼ�¼����ÿҳ��Сȡ��Ϊ0�Ļ�������ô��ҳ�������ټ�һҳ
		int totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		//totalPage�õ��ܹ��м�ҳ
		StringBuffer pageCode=new StringBuffer();
		if(currentPage==1){
			pageCode.append("<a>��һҳ</a>");
			//����ǵ�һҳ��û�г����ӣ�Ҳ�͵㲻��
		}else{
			pageCode.append("<a href='"+PropertiesUtil.getValue("host")+"/arcType/"+typeId+".html?page="+(currentPage-1)+"'>��һҳ</a>");
		}
		pageCode.append("&nbsp;&nbsp;");
		if(currentPage==totalPage){
			pageCode.append("<a>��һҳ</a>");
		}else{
			pageCode.append("<a href='"+PropertiesUtil.getValue("host")+"/arcType/"+typeId+".html?page="+(currentPage+1)+"'>��һҳ</a>");
		}
		return pageCode.toString();
	}
}
