<%@ page pageEncoding="UTF-8"%>
<style>
.hidden {
	display: none;
}

#form {
	margin: 5px;
}

#form table th {
	text-align: right;
}
</style>
<form id="form">
	<table>
		<tr>
			<th>交易号</th>
			<td><input name="tradeNo" id="tradeNo" readonly="readonly" /></td>
		</tr>
		<tr>
			<th>客户编号</th>
			<td><input id="customerNo" name="customerNo" readonly="readonly" /></td>
		</tr>
		<tr>
			<th>付款人</th>
			<td><input id="customerName" name="customerName" readonly="readonly" /></td>
		</tr>
		<tr>
			<th>收款方式</th>
			<td><select id="paymentMethod" name="paymentMethod" class="easyui-combobox" data-options="disabled:true">
					<option value="1">现金</option>
					<option value="2">信用卡</option>
					<option value="3">支票</option>
					<option value="4">转账/汇款</option>
					<option value="5">内转</option>
			</select>
			</td>
		</tr>
		<tr class="pm hidden">
			<th>账号</th>
			<td><input name="bankAccountNo" readonly="readonly" /></td>
		</tr>
		<tr class="pm hidden">
			<th>信用卡代码</th>
			<td><input name="cardCode" readonly="readonly" /></td>
		</tr>
		<tr class="pm hidden">
			<th>信用卡有效期</th>
			<td><input name="cardExpirationDate" readonly="readonly" /></td>
		</tr>
		<tr>
			<th>实收金额</th>
			<td><input name="incomeAmount" class="easyui-numberbox" data-options="precision:2" readonly="readonly" />
			</td>
		</tr>
		<tr class="pm hidden">
			<th>手续费</th>
			<td><input name="customerFactorage" class="easyui-numberbox" data-options="precision:2" value="0"  readonly="readonly" />
			</td>
		</tr>
		<tr>
			<th>收款日期</th>
			<td><input name="incomeDate" readonly="readonly" />
			</td>
		</tr>
		<tr>
			<th>到账日期</th>
			<td><input name="arriveDate" class="easyui-datebox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<th>到账金额</th>
			<td><input name="netAmount" class="easyui-numberbox" data-options="required:true,precision:2" />
			</td>
		</tr>
		<tr>
			<th>资金账户</th>
			<td><input id="fundAccount" name="fundAccount" class="easyui-combogrid" data-options="  
									        idField:'accountCode',    
	                                        textField:'accountCode',
	                                        panelWidth:380, 
	                                        required:true,  
									        url: '/cs/pub/find!findAccount.action',
									        columns:[[    
								                {field:'accountCode',title:'账户编码',width:80},    
								                {field:'accountBank',title:'开户银行',width:120},    
								                {field:'accountNumber',title:'账户号码',width:150}
								            ]] " />
			</td>
		</tr>
		<tr>
			<th>凭证号</th>
			<td><input name="voucherNo" class="easyui-validatebox" data-options="required:true " /></td>
		</tr>
	</table>
</form>