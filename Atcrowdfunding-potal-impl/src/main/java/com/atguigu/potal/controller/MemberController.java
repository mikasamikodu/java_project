package com.atguigu.potal.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.atguigu.bean.Cert;
import com.atguigu.bean.Member;
import com.atguigu.bean.MemberCert;
import com.atguigu.bean.Ticket;
import com.atguigu.manager.service.CertService;
import com.atguigu.potal.listener.PassListener;
import com.atguigu.potal.listener.RefuseListener;
import com.atguigu.potal.service.MemberCertService;
import com.atguigu.potal.service.MemberService;
import com.atguigu.potal.service.TicketService;
import com.atguigu.utils.AjaxResult;
import com.atguigu.utils.Const;
import com.atguigu.vo.Data;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private TicketService ticketService;
	@Autowired
	private CertService certService;
	@Autowired
	private MemberCertService memberCertService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	
	@RequestMapping("/index")
	public String index() {
		return "member/index";
	}
	
	@RequestMapping("/accttype")
	public String accttype(HttpSession session) {
		Member member = (Member)session.getAttribute(Const.LOGIN_MEMBER);
		Ticket ticket = ticketService.getTicket(member.getId());
		if(ticket==null) {
			ticket = new Ticket();
			ticket.setMemberid(member.getId());
			ticket.setStatus("0");
			ticket.setPstep("apply");
			ticketService.saveTicket(ticket);
		}else {
			String pstep = ticket.getPstep();
			if("accttype".equals(pstep)) {
				return "redirect:/member/basicinfo.htm";
			}else if("basicinfo".equals(pstep)) {
				String accttype = member.getAccttype();
				List<Cert> certs = certService.findByAccttype(accttype); 
				session.setAttribute("certs", certs);
				return "redirect:/member/uploadCert.htm";
			}else if("uploadCert".equals(pstep)) {
				return "redirect:/member/checkemail.htm";
			}else if("checkemail".equals(pstep)) {
				return "redirect:/member/checkauthcode.htm";
			}
		}
		return "member/accttype";
	}
	
	@RequestMapping("/basicinfo")
	public String basicinfo() {
		return "member/basicinfo";
	}
	
	@RequestMapping("/uploadCert")
	public String uploadCert() {
		return "member/uploadCert";
	}
	
	@RequestMapping("/checkemail")
	public String checkemail() {
		return "member/checkemail";
	}
	
	@RequestMapping("/checkauthcode")
	public String checkauthcode() {
		return "member/checkauthcode";
	}
	
	@ResponseBody
	@RequestMapping("/updateAccttype")
	public Object updateAccttype(HttpSession session,String accttype) {
		AjaxResult ajax = new AjaxResult();
		try {
			Member member = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			member.setAccttype(accttype);
			int result = memberService.updateAccttype(member);
			if(result==1) {
				Ticket ticket = ticketService.getTicket(member.getId());
				ticket.setPstep("accttype");
				ticketService.updatePstep(ticket);
			}
			ajax.setSuccess(true);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("更新帐户类型失败");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/updateBasicinfo")
	public Object updateBasicinfo(HttpSession session,Member member) {
		AjaxResult ajax = new AjaxResult();
		try {
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			loginMember.setRealname(member.getRealname());
			loginMember.setCardnum(member.getCardnum());;
			loginMember.setTelephone(member.getTelephone());
			int result = memberService.updateBaseinfo(loginMember);
			if(result==1) {
				Ticket ticket = ticketService.getTicket(loginMember.getId());
				ticket.setPstep("basicinfo");
				ticketService.updatePstep(ticket);
			}
			ajax.setSuccess(true);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("更新帐户基本信息失败");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/upload")
	public Object upload(HttpSession session,Data data) {
		AjaxResult ajax = new AjaxResult();
		try {
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			List<MemberCert> certs = data.getCerts();
			
			String realPath = session.getServletContext().getRealPath("/pictures");
			for(MemberCert cert:certs) {
				MultipartFile fileImg = cert.getFileImg();
				String originalFilename = fileImg.getOriginalFilename();
				String fileName = UUID.randomUUID().toString()+"_"+originalFilename;
				
				String path = realPath+"\\cert\\"+fileName;
				File file = new File(path);
				fileImg.transferTo(file);
				cert.setMemberid(loginMember.getId());
				cert.setIconpath(fileName);
				memberCertService.saveMC(cert);
			}
			Ticket ticket = ticketService.getTicket(loginMember.getId());
			ticket.setPstep("uploadCert");
			ticketService.updatePstep(ticket);
			ajax.setSuccess(true);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("上传资质文件失败");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/doCheckemail")
	public Object doCheckemail(HttpSession session,String checkemail) {
		AjaxResult ajax = new AjaxResult();
		try {
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			
			if(!loginMember.getEmail().equals(checkemail)) {
				memberService.updateEmail(checkemail);
			}
			
			ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("auth").singleResult();
			Map<String,Object> map = new HashMap<String,Object>();
			//toEmail
			//authcode
			//loginacct
			//flag
			//refuseListener
			//passListener
			StringBuilder builder = new StringBuilder();
			for(int i=0;i<4;i++) {
				builder.append(new Random().nextInt(10));
			}
			map.put("toEmail", loginMember.getEmail());
			map.put("authcode", builder);
			map.put("loginacct", loginMember.getLoginacct());
			map.put("refuseListener", new RefuseListener());
			map.put("passListener", new PassListener());
			ProcessInstance instance = runtimeService.startProcessInstanceById(processDefinition.getId(), map);
			
			Ticket ticket = ticketService.getTicket(loginMember.getId());
			ticket.setPstep("checkemail");
			ticket.setAuthcode(builder.toString());
			ticket.setPiid(instance.getId());
			ticketService.updatePstep(ticket);
			ajax.setSuccess(true);
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("发送验证码出现异常");
			e.printStackTrace();
		}
		return ajax;
	}
	
	@ResponseBody
	@RequestMapping("/doFinish")
	public Object doFinish(HttpSession session,String authcode) {
		AjaxResult ajax = new AjaxResult();
		try {
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			Ticket ticket = ticketService.getTicket(loginMember.getId());
			if(ticket.getAuthcode().equals(authcode)) {
				Task task = taskService.createTaskQuery().processInstanceId(ticket.getPiid()).taskAssignee(loginMember.getLoginacct()).singleResult();
				taskService.complete(task.getId());
				
				memberService.updateAuthStatus("1");
				
				ticket.setPstep("checkauthcode");
				ticketService.updatePstep(ticket);
				ajax.setSuccess(true);
			}else {
				ajax.setSuccess(false);
				ajax.setMessage("验证码错误，请重新输入！");
			}
			
		}catch(Exception e) {
			ajax.setSuccess(false);
			ajax.setMessage("验证异常");
			e.printStackTrace();
		}
		return ajax;
	}
}