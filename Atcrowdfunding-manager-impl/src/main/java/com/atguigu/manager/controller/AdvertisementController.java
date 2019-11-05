package com.atguigu.manager.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atguigu.bean.Advertisement;
import com.atguigu.bean.User;
import com.atguigu.manager.service.AdvertisementService;
import com.atguigu.utils.AjaxResult;
import com.atguigu.utils.Const;
import com.atguigu.utils.Page;

@Controller
@RequestMapping("/advertisement")
public class AdvertisementController {

	@Autowired
	private AdvertisementService advertisementService;
	
	@RequestMapping("/index")
	public String param() {
		return "advertisement/index";
	}
	
	@RequestMapping("/add")
	public String add() {
		return "advertisement/add";
	}	
	
	@RequestMapping("/edit")
	public String edit(Integer id,HttpServletRequest request) {
		Advertisement advertisement = advertisementService.findById(id);
		request.setAttribute("advertisement", advertisement);
		return "advertisement/edit";
	}	  
	
	@ResponseBody
	@RequestMapping("/upload")
	public Object upload(HttpServletRequest request, Advertisement advertisement,HttpSession session) {
		AjaxResult ajax = new AjaxResult();
		try {
			MultipartHttpServletRequest mul = (MultipartHttpServletRequest) request;
			MultipartFile file = mul.getFile("advpic");
			String fileName = file.getOriginalFilename();
			//String extendName = fileName.substring(fileName.lastIndexOf("."));//返回 .jpg
			
			String iconName = UUID.randomUUID().toString()+"_"+fileName;
//			String iconName = UUID.randomUUID().toString()+extendName;
			ServletContext context = session.getServletContext();
			String realPath = context.getRealPath("/pictures");
			String path = realPath+"\\advertisement\\"+iconName; 
			File file2 = new File(path);
			if(!file2.exists()) {
				file2.mkdirs();
			}
			file.transferTo(file2);
			
			User user = (User) session.getAttribute(Const.LOGIN_USER);
			advertisement.setUserid(user.getId());
			advertisement.setStatus("1");
			advertisement.setIconpath(iconName);
			int count = advertisementService.saveAdvertisement(advertisement);
			ajax.setSuccess(count==1);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("广告信息添加失败");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/doIndex")
	public Object doIndex(Integer pageNo,Integer pageSize,String input) {
		AjaxResult ajax = new AjaxResult();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("pageNo", pageNo);
			map.put("pageSize", pageSize);
			if(input!=null) {
				if(input.contains("%")) {
					input = input.replace("%", "\\\\%");
				}
				map.put("input", input);
			}
			Page page = advertisementService.queryPage(map);
			ajax.setPage(page);
			ajax.setSuccess(true);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("数据加载出现异常！");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/doDelete")
	public Object doDelete(Integer id) {
		AjaxResult ajax = new AjaxResult();
		try {
			advertisementService.deleteById(id);
			ajax.setSuccess(true);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("数据删除出现异常！");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/doDeleteBatch")
	public Object doDeleteBatch(Integer[] id) {
		AjaxResult ajax = new AjaxResult();
		try {
			advertisementService.deleteBatchByIds(id);
			ajax.setSuccess(true);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("数据删除出现异常！");
			e.printStackTrace();
		}
		return ajax;
	}
	
	
}
