package com.ly.dao;

import java.util.List;
import java.util.Map;

import com.ly.entity.Link;

/**
 * 友情链接Dao接口
 * @author user
 *
 */
public interface LinkDao {

	/**
	 * 根据条件分页查询友情链接集合
	 * @param map
	 * @return
	 */
	public List<Link> list(Map<String,Object> map);
	//因为后台管理需要分页(前台不用)所以还是加个参数方便分页
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	
	/**
	 * 添加友情链接
	 * @param link
	 * @return
	 */
	public Integer add(Link link);
	
	/**
	 * 修改友情链接
	 * @param link
	 * @return
	 */
	public Integer update(Link link);
	
	/**
	 * 删除友情链接
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
}
