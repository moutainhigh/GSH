package com.gsh.cs.service.apmgt;

import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.PaymentBudgetPRMT;

public interface PaymentBudgetServiceI {
	public Datagrid findAll(PaymentBudgetPRMT p);
}
