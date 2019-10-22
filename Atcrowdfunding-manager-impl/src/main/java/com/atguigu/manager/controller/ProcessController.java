package com.atguigu.manager.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atguigu.utils.AjaxResult;
import com.atguigu.utils.Page;

@Controller
@RequestMapping("/process")
public class ProcessController {

	@Autowired
	private RepositoryService repositoryService;
	
	@RequestMapping("/index")
	public String param() {
		return "process/index";
	}	
	
	@RequestMapping("/showImg")
	public String showImg() {
		return "process/showImg";
	}	
	
	@ResponseBody
	@RequestMapping("/doIndex")
	public Object doIndex(Integer pageNo, Integer pageSize) {
		AjaxResult ajax = new AjaxResult();
		try {
			Page page = new Page(pageNo, pageSize);
			ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
			List<ProcessDefinition> listPage = query.listPage(page.getStartIndex(), pageSize);
			List<Map<String,Object>> datas = new ArrayList<Map<String,Object>>();
			for (ProcessDefinition processDefinition : listPage) {
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("id", processDefinition.getId());
				map.put("name", processDefinition.getName());
				map.put("key", processDefinition.getKey());
				map.put("version", processDefinition.getVersion());
				
				datas.add(map);
			}
			
			page.setDatas(datas);
			Long count = query.count();
			page.setTotalSize(count.intValue());
			
			ajax.setPage(page);
			ajax.setSuccess(true);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("查询流程定义出现异常！");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(String id) {
		AjaxResult ajax = new AjaxResult();
		try {
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
			repositoryService.deleteDeployment(processDefinition.getDeploymentId());
			ajax.setSuccess(true);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("删除流程定义出现异常！");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/upload")
	public Object upload(HttpServletRequest request) {
		AjaxResult ajax = new AjaxResult();
		try {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			MultipartFile file = multipartHttpServletRequest.getFile("processDefinitionFile");
			repositoryService.createDeployment().addInputStream(file.getOriginalFilename(), file.getInputStream()).deploy();
			
			ajax.setSuccess(true);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("流程定义部署出现异常！");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/doShowImg")
	public void doShowImg(String id,HttpServletResponse response) throws IOException {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(id).singleResult();
		InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getDiagramResourceName());
		ServletOutputStream outputStream = response.getOutputStream();
		IOUtils.copy(inputStream, outputStream);
	}
}
