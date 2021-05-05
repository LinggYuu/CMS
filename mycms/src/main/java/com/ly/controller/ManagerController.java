package com.ly.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ly.entity.Manager;
import com.ly.util.Md5Util;
import com.ly.util.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ly.service.ManagerService;

import net.sf.json.JSONObject;

/**
 * 管理员Controller层
 * @author user
 *
 */
@Controller
@RequestMapping("/manager2")
//如果/manager的话会和tomcat某个请求冲突
public class ManagerController {

	@Resource
	private ManagerService managerService;
	
	/**
	 * 用户登录
	 * @param manager
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(Manager manager, HttpServletResponse response)throws Exception{
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(manager.getUserName(), Md5Util.md5(manager.getPassword(), Md5Util.SALT));
		JSONObject result=new JSONObject();
		try{
			subject.login(token);	
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
			result.put("errorInfo", "用户名或者密码错误！");
			e.printStackTrace();
		}
		ResponseUtil.write(response, result);
		return null;
	}
}
