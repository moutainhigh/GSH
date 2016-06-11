package com.gsh.cs.service.account;

import java.util.List;

import com.gsh.cs.model.base.CurrencyUnit;

public interface CurrencyUnitServiceI {
	public List<CurrencyUnit> findAll(String cu);
}
