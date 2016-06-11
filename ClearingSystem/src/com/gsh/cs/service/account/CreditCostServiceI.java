package com.gsh.cs.service.account;

import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.CreditCostPRMT;

public interface CreditCostServiceI {
	public Datagrid findAll(CreditCostPRMT P,User user);
}
