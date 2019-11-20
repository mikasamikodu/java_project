package com.atguigu.potal.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.context.ApplicationContext;

import com.atguigu.bean.Member;
import com.atguigu.potal.service.MemberService;
import com.atguigu.potal.service.TicketService;
import com.atguigu.utils.ApplicationUtils;

public class RefuseListener implements ExecutionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		Integer memberid = (Integer)execution.getVariable("memberid");
		ApplicationContext applicationContext = ApplicationUtils.applicationContext;
		MemberService memberService = applicationContext.getBean(MemberService.class);
		Member member = new Member();
		member.setId(memberid);
		member.setAuthstatus("0");
		memberService.updateAuthStatus(member);
		TicketService ticketService = applicationContext.getBean(TicketService.class);
		ticketService.updateStatus(memberid);

	}

}
