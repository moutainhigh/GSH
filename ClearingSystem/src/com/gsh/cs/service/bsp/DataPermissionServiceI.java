package com.gsh.cs.service.bsp;

import java.util.List;

import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.DataPermission;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.DataPermissionP;

public interface DataPermissionServiceI {
	public Datagrid findAll(DataPermissionP p,User user);
	public String findAllList(User user);
	public DataPermission save(DataPermissionP p);
	public void update(DataPermissionP p);
	public void delete(String id);
}
