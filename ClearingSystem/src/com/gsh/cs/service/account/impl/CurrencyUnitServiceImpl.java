package com.gsh.cs.service.account.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsh.cs.dao.BaseDaoI;
import com.gsh.cs.model.base.CurrencyUnit;
import com.gsh.cs.service.account.CurrencyUnitServiceI;

@Transactional
@Service("currencyUnitService")
public class CurrencyUnitServiceImpl implements CurrencyUnitServiceI {
	private BaseDaoI<CurrencyUnit> currencyUnitDao;
	
	public BaseDaoI<CurrencyUnit> getCurrencyUnitDao() {
		return currencyUnitDao;
	}
	@Autowired
	public void setCurrencyUnitDao(BaseDaoI<CurrencyUnit> currencyUnitDao) {
		this.currencyUnitDao = currencyUnitDao;
	}

	public List<CurrencyUnit> findAll(String cu) {
		
		String hql="from CurrencyUnit c where 1=1";
		Map<String, Object> params = new HashMap<String, Object>(0);
		if(cu!=null&&!cu.equals("")) {
			 if (cu.matches("[a-zA-Z]+")){
				 hql+=" and c.id like :cu";
					params.put("cu", "%"+cu+"%");
			 }else{
				 hql+=" and c.text like :cu";
					params.put("cu", "%"+cu+"%");
			 }
			
		}
		List<CurrencyUnit> li= currencyUnitDao.find(hql,params);
		return li;
	}

}
