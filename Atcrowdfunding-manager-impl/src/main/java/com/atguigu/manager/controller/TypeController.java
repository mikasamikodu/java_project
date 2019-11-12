package com.atguigu.manager.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.AccountTypeCert;
import com.atguigu.bean.Cert;
import com.atguigu.manager.service.AccountTypeCertService;
import com.atguigu.manager.service.CertService;
import com.atguigu.utils.AjaxResult;

@Controller
@RequestMapping("/type")
public class TypeController {

	@Autowired
	private CertService certService;
	
	@Autowired
	private AccountTypeCertService accountTypeCertService;
	
	@RequestMapping("/index")
	public String param(HttpServletRequest request) {
		List<Cert> certs = certService.findAll();
		request.setAttribute("certs", certs);
		List<Map<String,Object>> certTypes = accountTypeCertService.findAll();
		request.setAttribute("certTypes", certTypes);		
		return "type/index";
	}
	
	@ResponseBody
	@RequestMapping("/addTypeCert")
	public Object addTypeCert(AccountTypeCert accountTypeCert) {
		AjaxResult ajax = new AjaxResult();
		try {
			int result = accountTypeCertService.addACT(accountTypeCert);
			ajax.setSuccess(result==1);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("数据添加异常");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/deleteTypeCert")
	public Object deleteTypeCert(AccountTypeCert accountTypeCert) {
		AjaxResult ajax = new AjaxResult();
		try {
			int result = accountTypeCertService.deleteByACT(accountTypeCert);
			ajax.setSuccess(result==1);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("数据删除异常");
			e.printStackTrace();
		}
		return ajax;
	}	  
}
