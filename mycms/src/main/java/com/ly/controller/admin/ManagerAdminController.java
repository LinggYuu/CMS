package com.ly.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ly.entity.Manager;
import com.ly.service.ManagerService;
import com.ly.util.Md5Util;
import com.ly.util.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.json.JSONObject;

/**
 * ����ԱController��
 * @author user
 *
 */
@Controller
@RequestMapping("/admin/manager")
public class ManagerAdminController {

	@Resource
	private ManagerService managerSerivce;
	
	/**
	 * �޸Ĺ���Ա����
	 * @param newPassword
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifyPassword")
	public String modifyPassword(String newPassword,HttpServletResponse response)throws Exception{
		Manager manager=new Manager();
		manager.setPassword(Md5Util.md5(newPassword, Md5Util.SALT));
		int resultTotal=managerSerivce.update(manager);
		JSONObject result=new JSONObject();
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * ע��
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/logout")
	public String logout()throws Exception{
		SecurityUtils.getSubject().logout();
		return "redirect:/login.jsp";
	}
	
	
}
