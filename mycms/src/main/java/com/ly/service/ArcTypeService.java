package com.ly.service;

import java.util.List;
import java.util.Map;

import com.ly.entity.ArcType;

/**
 * �������Service�ӿ�
 */
public interface ArcTypeService {

	/**
	 * ����������ҳ��ѯ������𼯺�
	 * @param map
	 * @return
	 */
	public List<ArcType> list(Map<String,Object> map);
	
	/**
	 * ��ȡ�ܼ�¼��
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * ����id��ѯʵ��
	 * @param id
	 * @return
	 */
	public ArcType findById(Integer id);
	
	/**
	 * �����������
	 * @param arcType
	 * @return
	 */
	public Integer add(ArcType arcType);
	
	/**
	 * �޸��������
	 * @param arcType
	 * @return
	 */
	public Integer update(ArcType arcType);
	
	/**
	 * ɾ���������
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
}