package com.atguigu.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.Role;
import com.atguigu.bean.User;
import com.atguigu.manager.service.UserService;
import com.atguigu.utils.AjaxResult;
import com.atguigu.utils.Page;
import com.atguigu.utils.StringUtil;
import com.atguigu.vo.Data;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
//	@RequestMapping("/index")
//	public String index(@RequestParam(value="pageNo",required=false,defaultValue="1") Integer pageNo,
//						@RequestParam(value="pageSize",required=false,defaultValue="10")Integer pageSize,
//						Map<String, Page> map) {
//		Page page = userService.queryPage(pageNo, pageSize);
//		map.put("page", page);
//		return "user/index";
//	}
//	
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/doIndex") public Object index(Integer pageNo, Integer
	 * pageSize) { AjaxResult ajax = new AjaxResult(); try { Page page =
	 * userService.queryPage(pageNo, pageSize); ajax.setSuccess(true);
	 * ajax.setPage(page); }catch(Exception e) { ajax.setSuccess(false);
	 * ajax.setMessage("数据加载失败"); e.printStackTrace(); } return ajax; }
	 */
	@RequestMapping("/index")
	public String index() {
		return "user/index";
	}
	
	@ResponseBody
	@RequestMapping("/doIndex")
	public Object index(Integer pageNo, Integer pageSize,String input) {
		AjaxResult ajax = new AjaxResult();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("pageNo", pageNo+1);
			map.put("pageSize", pageSize);
			if(StringUtil.isNotEmpty(input)) {
				if(input.contains("%")) {
					input = input.replace("%", "\\\\%");
				}					
				map.put("input", input);
			}
			
			Page page = userService.queryPage(map);
			ajax.setSuccess(true);
			ajax.setPage(page);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("数据加载失败");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@RequestMapping("/toAdd")
	public String toAdd() {
		return "user/add";
	}
	
	
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(User user) {
		AjaxResult ajax = new AjaxResult();
		try {
			int result = userService.saveUser(user);
			ajax.setSuccess(result == 1);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("新增用户失败");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(Integer id,HttpSession session,HttpServletRequest request) {
		User user = userService.findById(id);
		request.setAttribute("user", user);
		return "user/edit";
	}
	
	@ResponseBody
	@RequestMapping("/doEdit")
	public Object doEdit(User user) {
		AjaxResult ajax = new AjaxResult();
		try {
			int result = userService.updateUser(user);
			ajax.setSuccess(result == 1);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("修改用户失败");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/deleteUserById")
	public Object deleteUserById(Integer id) {
		AjaxResult ajax = new AjaxResult();
		try {
			int result = userService.deleteUserById(id);
			ajax.setSuccess(result == 1);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("删除用户失败");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/delBatch")
	public Object delBatch(Data data) {
		AjaxResult ajax = new AjaxResult();
		try {
			int result = userService.deleteBatchUser(data);
			ajax.setSuccess(result == data.getDatas().size());
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("批量删除失败");
			e.printStackTrace();
		}
		return ajax;
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping("/delBatch") public Object delBatch(Integer[] id) {
	 * AjaxResult ajax = new AjaxResult(); try { int result =
	 * userService.deleteUser(id); ajax.setSuccess(result == id.length);
	 * }catch(Exception e) { ajax.setSuccess(false); ajax.setMessage("批量删除失败");
	 * e.printStackTrace(); } return ajax; }
	 */	
	@RequestMapping("/assignRole")
	public String assignRole(Integer id,Map<String, Object> map) {
		List<Role> roles = userService.queryAllRole();
		List<Integer> ids = userService.queryRoleByUserId(id);
		
		List<Role> leftRoles  = new ArrayList<Role>();
		List<Role> rightRoles = new ArrayList<Role>();
		
		for(Role role: roles) {
			if(ids.contains(role.getId())) {
				rightRoles.add(role);
			}else {
				leftRoles.add(role);				
			}
		}
		
		map.put("left", leftRoles);
		map.put("right", rightRoles);
		return "user/assignRole";
	}

	@ResponseBody
	@RequestMapping("/addRole")
	public Object addRole(Integer userid,Data data) {
		AjaxResult ajax = new AjaxResult();
		try {
			userService.saveUserRole(userid, data);
			ajax.setSuccess(true);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("分配失败");
			e.printStackTrace();
		}
		return ajax;
	}
	
	
	  @ResponseBody
	  @RequestMapping("/removeRole") 
	  public Object removeRole(Integer userid, Data data) {
	  AjaxResult ajax = new AjaxResult(); 
	  try { 
		  userService.removeUserRole(userid, data); 
		  ajax.setSuccess(true); 
	  }catch(Exception e) { 
		  ajax.setSuccess(false);
		  ajax.setMessage("解除分配失败"); 
		  e.printStackTrace(); 
	  } 
	  return ajax; 
	}
}
