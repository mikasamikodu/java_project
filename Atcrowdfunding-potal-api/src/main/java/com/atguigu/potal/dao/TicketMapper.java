package com.atguigu.potal.dao;

import java.util.List;

import com.atguigu.bean.Ticket;

public interface TicketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ticket ticket);

    Ticket selectByPrimaryKey(Integer id);

	List<Ticket> selectAll();

	Ticket getTicketByMemberid(Integer memberid);

	void updatePstep(Ticket ticket);
}