package com.gsh.cs.service.bsp;

import java.util.List;

import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.Interface.TicketsShiyong;
import com.gsh.cs.model.base.BaspTicketUse;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.BaspTicketUsePRMT;

public interface BaspTicketUseServiceI {
	public Datagrid findGuoji(BaspTicketUsePRMT p,User user);
	public Datagrid findGuonei(BaspTicketUsePRMT p,User user);
	public void save(BaspTicketUse btu);
	public List<BaspTicketUse> findAll(String type,String createrNo);
	public BaspTicketUse updateBank(String airlineCompany,String ticketNo,String type,String typeTgq);
	public List<TicketsShiyong> ticketsReport(BaspTicketUsePRMT p);
}
