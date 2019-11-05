package com.atguigu.manager.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.bean.Cert;
import com.atguigu.manager.service.AccountTypeCertService;
import com.atguigu.manager.service.CertService;

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
}
