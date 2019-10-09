package com.atguigu.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.Permission;
import com.atguigu.manager.service.PermissionService;
import com.atguigu.utils.AjaxResult;

@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping("/index")
	public String index() {
		return "permission/index";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "permission/add";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer id,Map<String,Permission> map) {
		Permission permission = permissionService.queryPermissionById(id);
		map.put("permission", permission);
		return "permission/edit";
	}
	
	
	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(Permission permission) {
		AjaxResult ajax = new AjaxResult();
		try {
			int count = permissionService.savePermission(permission);
			ajax.setSuccess(count == 1);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("许可树新增失败了！");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/doEdit")
	public Object doEdit(Permission permission) {
		AjaxResult ajax = new AjaxResult();
		try {
			int count = permissionService.editPermission(permission);
			ajax.setSuccess(count == 1);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("许可树修改失败了！");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/deleteNode")
	public Object deleteNode(Integer id) {
		AjaxResult ajax = new AjaxResult();
		try {
			int count = permissionService.deletePermission(id);
			ajax.setSuccess(count == 1);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("删除许可树节点出现问题！");
			e.printStackTrace();
		}
		return ajax;
	}
	
	//第六版
	@ResponseBody
	@RequestMapping("/loadData")
	public Object loadData() {
		AjaxResult ajax = new AjaxResult();
		try {
			List<Permission> root = new ArrayList<Permission>();
			List<Permission> permissions = permissionService.queryAllPermission();
			Map<Integer, Permission> map = new HashMap<Integer, Permission>();
			for(Permission permission:permissions) {
				map.put(permission.getId(), permission);
			}
			for(Permission permission:permissions) {
				if(permission.getPid()==null) {
					root.add(permission);
				}else {
					map.get(permission.getPid()).getChildren().add(permission);
				}
			}
			ajax.setDatas(root);
			ajax.setSuccess(true);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("许可树加载失败了！");
			e.printStackTrace();
		}
		return ajax;
	}
	
	//第五版
//	@ResponseBody
//	@RequestMapping("/loadData")
//	public Object loadData() {
//		AjaxResult ajax = new AjaxResult();
//		try {
//			List<Permission> root = new ArrayList<Permission>();
//			List<Permission> permissions = permissionService.queryAllPermission();
//			for(Permission permission:permissions) {
//				Permission children = permission;
//				if(permission.getPid()==null) {
//					root.add(children);
//				}else {
//					for(Permission innerPermission:permissions) {
//						if(children.getPid().equals(innerPermission.getId())) {
//							innerPermission.getChildren().add(children);
//							innerPermission.setOpen(true);
//							break;
//						}
//					}
//				}
//			}
//			ajax.setDatas(root);
//			ajax.setSuccess(true);
//		}catch(Exception e) {
//			ajax.setSuccess(false);
//			ajax.setMessage("许可树加载失败！");
//			e.printStackTrace();
//		}
//		return ajax;
//	}
//	
	//第四版
//	@ResponseBody
//	@RequestMapping("/loadData")
//	public Object loadData() {
//		AjaxResult ajax = new AjaxResult();
//		try {
//			List<Permission> root = new ArrayList<Permission>();
//			Permission permission = permissionService.getRootPermission();
//			permission.setOpen(true);
//			root.add(permission);
//			queryChildren(permission);
//			ajax.setDatas(root);
//			ajax.setSuccess(true);
//		}catch(Exception e) {
//			ajax.setSuccess(false);
//			ajax.setMessage("许可树加载失败！");
//			e.printStackTrace();
//		}
//		return ajax;
//	}
//	private void queryChildren(Permission permission) {
//		List<Permission> children = permissionService.queryByPid(permission.getId());
//		permission.setChildren(children);
//		permission.setOpen(true);
//		
//		for(Permission permission1: children) {
//			queryChildren(permission1);
//		}
//		
//	}
//	//第三版
//	@ResponseBody
//	@RequestMapping("/loadData")
//	public Object loadData() {
//		AjaxResult ajax = new AjaxResult();
//		try {
//			List<Permission> root = new ArrayList<Permission>();
//			Permission permission = permissionService.getRootPermission();
//			permission.setOpen(true);
//			root.add(permission);
//			
//			List<Permission> children = permissionService.queryByPid(permission.getId());
//			permission.setChildren(children);
//			
//			for(Permission permission1: children) {
//				List<Permission> children1 = permissionService.queryByPid(permission1.getId());
//				permission1.setOpen(true);
//				permission1.setChildren(children1);
//			}
//			ajax.setDatas(root);
//			ajax.setSuccess(true);
//		}catch(Exception e) {
//			ajax.setSuccess(false);
//			ajax.setMessage("许可树加载失败！");
//			e.printStackTrace();
//		}
//		return ajax;
//	}
	
	//第二版
//	@ResponseBody
//	@RequestMapping("/loadData")
//	public Object loadData() {
//		AjaxResult ajax = new AjaxResult();
//		try {
//			List<Permission> root = new ArrayList<Permission>();
//			Permission permission = permissionService.queryById(1);
//			permission.setOpen(true);
//			root.add(permission);
//			
//			List<Permission> children = permissionService.queryByPid(permission.getId());
//			permission.setChildren(children);
//			
//			for(Permission permission1: children) {
//				List<Permission> children1 = permissionService.queryByPid(permission1.getId());
//				permission1.setChildren(children1);
//			}
//			ajax.setDatas(root);
//			ajax.setSuccess(true);
//		}catch(Exception e) {
//			ajax.setSuccess(false);
//			ajax.setMessage("许可树加载失败！");
//		}
//		return ajax;
//	}
//	
	
	//第一版
//	@ResponseBody
//	@RequestMapping("/loadData")
//	public Object loadData() {
//		AjaxResult ajax = new AjaxResult();
//		try {
//			List<Permission> root = new ArrayList<Permission>();
//			Permission permission = new Permission();
//			permission.setName("系统权限菜单");
//			permission.setOpen(true);
//			root.add(permission);
//			
//			Permission permission1 = new Permission();
//			permission1.setName("控制面板");
//			Permission permission2 = new Permission();
//			permission2.setName("权限管理");
//			
//			List<Permission> children = new ArrayList<Permission>();
//			children.add(permission1);
//			children.add(permission2);
//			permission.setChildren(children);
//			ajax.setDatas(root);
//			ajax.setSuccess(true);
//		}catch(Exception e) {
//			ajax.setSuccess(false);
//			ajax.setMessage("许可树加载失败！");
//		}
//		return ajax;
//	}
}
