package com.atguigu.potal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.Member;
import com.atguigu.bean.Ticket;
import com.atguigu.potal.service.MemberService;
import com.atguigu.potal.service.TicketService;
import com.atguigu.utils.AjaxResult;
import com.atguigu.utils.Const;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private TicketService ticketService;
	
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
			}else if("baseinfo".equals(pstep)) {
				return "redirect:/member/uploadCert.htm";
			}
		}
		return "member/accttype";
	}
	
	@RequestMapping("/apply")
	public String apply() {
		return "member/apply";
	}
	
	@RequestMapping("/basicinfo")
	public String apply1() {
		return "member/basicinfo";
	}
	
	@RequestMapping("/uploadCert")
	public String apply2() {
		return "member/uploadCert";
	}
	
	@RequestMapping("/apply3")
	public String apply3() {
		return "member/apply3";
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
	@RequestMapping("/baseinfo")
	public Object baseinfo(HttpSession session,Member member) {
		AjaxResult ajax = new AjaxResult();
		try {
			Member loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			loginMember.setRealname(member.getRealname());
			loginMember.setCardnum(member.getCardnum());;
			loginMember.setTelephone(member.getTelephone());
			int result = memberService.updateBaseinfo(loginMember);
			if(result==1) {
				Ticket ticket = ticketService.getTicket(loginMember.getId());
				ticket.setPstep("baseinfo");
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
	
	
}
