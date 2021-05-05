package com.ly.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ly.dao.LinkDao;
import com.ly.entity.Link;
import com.ly.service.LinkService;
import org.springframework.stereotype.Service;

/**
 * 友情链接Service实现类
 * @author user
 *
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {

	@Resource
	private LinkDao linkDao;
	
	public List<Link> list(Map<String, Object> map) {
		return linkDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return linkDao.getTotal(map);
	}

	public Integer add(Link link) {
		return linkDao.add(link);
	}

	public Integer update(Link link) {
		return linkDao.update(link);
	}

	public Integer delete(Integer id) {
		return linkDao.delete(id);
	}

}
