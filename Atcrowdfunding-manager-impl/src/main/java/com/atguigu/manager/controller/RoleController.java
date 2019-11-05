package com.atguigu.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.Permission;
import com.atguigu.bean.Role;
import com.atguigu.manager.service.PermissionService;
import com.atguigu.manager.service.RolePermissionService;
import com.atguigu.manager.service.RoleService;
import com.atguigu.utils.AjaxResult;
import com.atguigu.utils.Page;
import com.atguigu.vo.Data;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private RolePermissionService rolePermissionService;
	
	@RequestMapping("/index")
	public String index() {
		return "role/index";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "role/add";
	}
	
	@RequestMapping("/assignPermission")
	public String assignPermission() {
		return "role/assignPermission";
	}
	
	@RequestMapping("/update")
	public String update(Integer id,HttpServletRequest request) {
		Role role = roleService.selectRoleById(id);
		request.setAttribute("role", role);
		return "role/edit";
	}
	
	@ResponseBody
	@RequestMapping("/doIndex")
	public Object doIndex(Integer pageSize,Integer pageNo,String content) {
		AjaxResult ajax = new AjaxResult();
		try {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("pageNo", pageNo+1);
			map.put("pageSize", pageSize);
			if(content!=null) {
				if(content.contains("%")) {
					content = content.replace("%", "\\\\%");
				}
				map.put("content", content);
			}
			Page page = roleService.queryPage(map);
			ajax.setPage(page);
			ajax.setSuccess(true);
		}catch(Exception e) {
			ajax.setMessage("数据加载失败！");
			ajax.setSuccess(false);
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/deleteRole")
	public Object deleteRole(Integer id) {
		AjaxResult ajax = new AjaxResult();
		try {
			Integer count = roleService.deleteRole(id);
			ajax.setSuccess(count==1);
		}catch(Exception e) {
			ajax.setMessage("数据删除失败！");
			ajax.setSuccess(false);
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/delBatch")
	public Object delBatch(Integer[] id) {
		AjaxResult ajax = new AjaxResult();
		try {
			Integer count = roleService.delBatch(id);
			ajax.setSuccess(count==id.length);
		}catch(Exception e) {
			ajax.setMessage("数据删除失败！");
			ajax.setSuccess(false);
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/doAssignPermission")
	public Object doAssignPermission(Integer roleid,Data data) {
		AjaxResult ajax = new AjaxResult();
		try {
			int count = rolePermissionService.saveAll(roleid, data);
			ajax.setSuccess(count== data.getIds().size());
		}catch(Exception e) {
			ajax.setMessage("许可分配失败！");
			ajax.setSuccess(false);
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/loadData")
	public Object loadData(Integer roleid) {
		List<Permission> root = new ArrayList<Permission>();
		List<Permission> permissions = permissionService.queryAllPermission();
		List<Integer> ids = rolePermissionService.queryById(roleid);
		Map<Integer, Permission> map = new HashMap<Integer, Permission>();
		for(Permission permission:permissions) {
			map.put(permission.getId(), permission);
			if(ids.contains(permission.getId())) {
				permission.setChecked(true);
			}
		}
		for(Permission permission:permissions) {
			if(permission.getPid()==null) {
				root.add(permission);
			}else {
				map.get(permission.getPid()).getChildren().add(permission);
			}
		}
		return root;
	}
	
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(Role role) {
		AjaxResult ajax = new AjaxResult();
		try {
			int result = roleService.saveRole(role);
			ajax.setSuccess(result==1);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("新增角色信息出现问题！");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/doUpdate")
	public Object doUpdate(Role role) {
		AjaxResult ajax = new AjaxResult();
		try {
			int result = roleService.updateRole(role);
			ajax.setSuccess(result==1);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("修改角色信息出现问题！");
			e.printStackTrace();
		}
		return ajax;
	}
	
}
