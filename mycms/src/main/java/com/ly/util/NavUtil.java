package com.ly.util;

/**
 * ����������
 * @author user
 *���������ֱ������һ����
 */
public class NavUtil {
//ͨ������propertiesUtil
	/**
	 * ���������б���
	 * @param typeName
	 * @return
	 */
	public static String genArticleListNavigation(String typeName){
		//����Ǹ�arctypeҳ���õ�
		StringBuffer navCode=new StringBuffer();
//		append����ƴ��
		navCode.append("��ǰλ�ã�&nbsp;&nbsp;");
		navCode.append("<a href='"+PropertiesUtil.getValue("host")+"'>��ҳ</a>&nbsp;&nbsp;>&nbsp;&nbsp;");
		//��ȡ��host����
		navCode.append(typeName);
		//�㵽���
		return navCode.toString();
	}
	
	/**
	 * ����������ϸҳ����
	 * @param typeName
	 * @param typeId
	 * @param articleTitle
	 * @return
	 */
	public static String genArticleNavigation(String typeName,Integer typeId,String articleTitle){
		//��articleҳ���õ�
		StringBuffer navCode=new StringBuffer();
		navCode.append("��ǰλ�ã�&nbsp;&nbsp;");
		navCode.append("<a href='"+PropertiesUtil.getValue("host")+"'>��ҳ</a>&nbsp;&nbsp;>&nbsp;&nbsp;");
		navCode.append("<a href='"+PropertiesUtil.getValue("host")+"/arcType/"+typeId+".html'>"+typeName+"</a>&nbsp;&nbsp;>&nbsp;&nbsp;"+articleTitle);
		return navCode.toString();
		//���������ֱ������һ���ַ���
	}
}
