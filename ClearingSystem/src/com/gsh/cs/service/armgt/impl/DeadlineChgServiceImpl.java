package com.gsh.cs.service.armgt.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.model.base.DeadlineChg;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.service.armgt.DeadlineChgServiceI;

@Service("deadlineChgService")
public class DeadlineChgServiceImpl implements DeadlineChgServiceI {
	private BaseDaoI<DeadlineChg> deadlineChgDao;

	@Autowired
	public void setDeadlineChgDao(BaseDaoI<DeadlineChg> deadlineChgDao) {
		this.deadlineChgDao = deadlineChgDao;
	}

	public Datagrid find(Long rcvbid) {
		Datagrid dg = new Datagrid();
		String hql = "from DeadlineChg t where t.rcvbid = " + rcvbid;
		dg.setRows(this.deadlineChgDao.find(hql));
		dg.setTotal(new Long(dg.getRows().size()));
		return dg;
	}
}
