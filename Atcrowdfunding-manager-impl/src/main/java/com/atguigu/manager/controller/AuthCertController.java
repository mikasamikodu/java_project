package com.atguigu.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.Member;
import com.atguigu.bean.Ticket;
import com.atguigu.potal.service.MemberCertService;
import com.atguigu.potal.service.MemberService;
import com.atguigu.potal.service.TicketService;
import com.atguigu.utils.AjaxResult;
import com.atguigu.utils.Page;

@Controller
@RequestMapping("/authcert")
public class AuthCertController {

	@Autowired
	private TaskService taskService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberCertService memberCertService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private RepositoryService repositoryService;
	
	@RequestMapping("/index")
	public String index() {
		return "authcert/index";
	}
	
	@ResponseBody
	@RequestMapping("/doIndex")
	public Object doIndex(Integer pageNo,Integer pageSize) {
		AjaxResult ajax = new AjaxResult();
		try {
			Page page = new Page(pageNo,pageSize);
			List<Task> tasks = taskService.createTaskQuery().processDefinitionKey("auth")
					   					  .taskCandidateGroup("backuser")
					   					  .listPage(page.getStartIndex(), pageSize);
			
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			for(Task task:tasks) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("taskId", task.getId());
				map.put("taskName", task.getName());
				
				ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
																		.processDefinitionId(task.getProcessDefinitionId())
																		.singleResult();
				map.put("proDefName", processDefinition.getName());
				map.put("proDefVersion", processDefinition.getVersion());
				Ticket ticket = ticketService.getTicketByPiid(task.getProcessInstanceId());
				Member member = memberService.getMemberById(ticket.getMemberid());
				map.put("member", member);
				list.add(map);
			}
			page.setDatas(list);
			page.setTotalSize(tasks.size());
			ajax.setPage(page);
			ajax.setSuccess(true);
		}catch(Exception e){
			ajax.setSuccess(false);
			ajax.setMessage("数据加载出现问题");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/refuse")
	public Object refuse(Integer memberid,String taskid) {
		AjaxResult ajax = new AjaxResult();
		try {
			taskService.setVariable(taskid, "flag", false);
			taskService.setVariable(taskid, "memberid", memberid);
			taskService.complete(taskid);
			ajax.setSuccess(true);
		}catch(Exception e){
			ajax.setSuccess(false);
			ajax.setMessage("流程审核出现问题");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/pass")
	public Object pass(Integer memberid,String taskid) {
		AjaxResult ajax = new AjaxResult();
		try {
			taskService.setVariable(taskid, "flag", true);
			taskService.setVariable(taskid, "memberid", memberid);
			taskService.complete(taskid);
			ajax.setSuccess(true);
		}catch(Exception e){
			ajax.setSuccess(false);
			ajax.setMessage("流程审核出现问题");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@RequestMapping("/doShow")
	public String doShow(Integer memberid,Map<String,Object> map) {
		Member member = memberService.getMemberById(memberid);
		List<Map<String,Object>> list = memberCertService.getCertByMemberId(memberid);
		map.put("member", member);
		map.put("certImgs", list);
		return "authcert/show";
	}
}
