package com.atguigu.potal.service;

import com.atguigu.bean.Ticket;

public interface TicketService {

	Ticket getTicket(Integer memberid);

	void saveTicket(Ticket ticket);

	void updatePstep(Ticket ticket);

	Ticket getTicketByPiid(String piid);

	void updateStatus(Integer memberid);

}
