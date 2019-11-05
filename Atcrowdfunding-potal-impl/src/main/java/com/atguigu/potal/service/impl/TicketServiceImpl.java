package com.atguigu.potal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.bean.Ticket;
import com.atguigu.potal.dao.TicketMapper;
import com.atguigu.potal.service.TicketService;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketMapper ticketMapper;

	@Override
	public Ticket getTicket(Integer memberid) {
		return ticketMapper.getTicketByMemberid(memberid);
	}

	@Override
	public void saveTicket(Ticket ticket) {
		ticketMapper.insert(ticket);
	}

	@Override
	public void updatePstep(Ticket ticket) {
		ticketMapper.updatePstep(ticket);		
	}
}
