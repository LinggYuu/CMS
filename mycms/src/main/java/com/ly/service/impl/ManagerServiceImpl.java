package com.ly.service.impl;

import javax.annotation.Resource;

import com.ly.dao.ManagerDao;
import com.ly.entity.Manager;
import org.springframework.stereotype.Service;

import com.ly.service.ManagerService;

/**
 * 管理员Service实现类
 * @author user
 *
 */
@Service("managerService")
public class ManagerServiceImpl implements ManagerService{

	@Resource
	private ManagerDao managerDao;

	public Manager getByUserName(String userName) {
		return managerDao.getByUserName(userName);
	}

	public Integer update(Manager manager) {
		return managerDao.update(manager);
	}
}
