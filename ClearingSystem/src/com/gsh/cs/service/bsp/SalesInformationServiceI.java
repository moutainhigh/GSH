package com.gsh.cs.service.bsp;

import com.gsh.cs.model.Interface.CreateByHand;
import com.gsh.cs.model.base.SalesInformation;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.SalesInformationPRMT;

public interface SalesInformationServiceI {
	public Datagrid findAll(SalesInformationPRMT P);
	
	public void saveCreateByHand(CreateByHand cb);
	
	public SalesInformation update(SalesInformationPRMT p);
}
