package com.gsh.cs.service.bsp.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.filter.loginUser.User;
import com.gsh.cs.model.base.DataPermission;
import com.gsh.cs.model.easyui.Datagrid;
import com.gsh.cs.model.parameter.DataPermissionP;
import com.gsh.cs.service.bsp.DataPermissionServiceI;
@Transactional
@Service("dataPermissionService")
public class DataPermissionServiceImpl implements DataPermissionServiceI {
	private BaseDaoI<DataPermission> dataPermissionDao;
	
	public BaseDaoI<DataPermission> getDataPermissionDao() {
		return dataPermissionDao;
	}
	@Autowired
	public void setDataPermissionDao(BaseDaoI<DataPermission> dataPermissionDao) {
		this.dataPermissionDao = dataPermissionDao;
	}

	public Datagrid findAll(DataPermissionP p,User user) {
		Datagrid dg=new Datagrid();
		String hql="from DataPermission where 1=1 and cid = "+user.getCid(), countHql = "select count(*) ";
		countHql+=hql;
		dg.setRows(this.dataPermissionDao.find(hql, p.getPage(), p.getRows()));
		dg.setTotal(this.dataPermissionDao.count(countHql));
		return dg;
	}
	
	public String findAllList(User user) {
		String str="";
		List<DataPermission> dplist=new ArrayList<DataPermission>();
		String hql="from DataPermission where uid = "+user.getUid()+" and cid = "+user.getCid();
		dplist=this.dataPermissionDao.find(hql);
		for(DataPermission dp:dplist){
			if(!str.equals("")){
				str+=",";
			}
			str+=dp.getIdName();
		}
		return str;
	}

	public DataPermission save(DataPermissionP p) {
		DataPermission dp=new DataPermission();
		BeanUtils.copyProperties(p, dp, new String[] { "id" });
		dp.setId((Long) this.dataPermissionDao.save(dp));
		return dp;
	}
	public void update(DataPermissionP p) {
		DataPermission dp=new DataPermission();
		BeanUtils.copyProperties(p, dp);
		this.dataPermissionDao.update(dp);
	}
	public void delete(String id) {
		String hql="delete from DataPermission where id = "+id;
		dataPermissionDao.executeHql(hql);
	}

}
