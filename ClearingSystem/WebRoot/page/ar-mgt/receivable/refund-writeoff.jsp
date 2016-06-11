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
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'west',border:false" style="width:285px;padding:9px; overflow: hidden;">
		<form id="form">
			<table>
				<tr>
					<th>客户编号</th>
					<td><input id="customerNo" name="customerNo" readonly="readonly" /></td>
				</tr>
				<tr>
					<th>收款人</th>
					<td><input id="customerName" name="customerName" class="easyui-validatebox" data-options="required:true " /></td>
				</tr>
				<tr>
					<th>账号</th>
					<td><input id="bankAccountNo" name="bankAccountNo" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th>应收金额</th>
					<td><input id="incomeBeAmount" name="incomeBeAmount" class="easyui-numberbox" data-options="required:true,precision:2" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>实收金额</th>
					<td><input id="incomeAmount" name="incomeAmount" class="easyui-numberbox" data-options="required:true,precision:2" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>余额</th>
					<td><input id="balance" name="balance" class="easyui-numberbox" data-options="required:true,precision:2" readonly="readonly" />
					</td>
				</tr>
				<tr class="xyk hidden">
					<th>退还手续费</th>
					<td><input id="customerFactorage" name="customerFactorage" class="easyui-numberbox" data-options="validType:'less_equal_zero',required:true,precision:2" value="0" />
					</td>
				</tr>
				<tr>
					<th>付款方式</th>
					<td><select id="paymentMethod" name="paymentMethod" class="easyui-combobox" data-options="editable:false,panelHeight:'auto',required:true,onChange:window.armgt.receivable.writeoffRefund.paymentMethodOnChange,
					onHidePanel:window.armgt.receivable.writeoffIncome.onHidePanel">
							<option value="1">现金</option>
							<option value="2">信用卡</option>
							<option value="3">支票</option>
							<option value="4">转账/汇款</option>
							<option value="5">内转</option>
							<option value="6">第三方支付</option>
					</select>
					</td>
				</tr>
				<tr>
					<th>付款日期</th>
					<td><input name="incomeDate" class="easyui-datebox" data-options="required:true" />
					</td>
				</tr>
				<tr>
					<th>资金账户</th>
					<td><input id="fundAccount" name="fundAccount" class="easyui-combogrid" data-options="  
									        idField:'accountCode',    
	                                        textField:'accountCode',
	                                        panelWidth:380, 
	                                        required:true,
	                                        onChange:window.armgt.receivable.writeoffRefund.fundAccountOnChange,  
									        
									        columns:[[    
								                {field:'accountCode',title:'账户编码',width:80},    
								                {field:'accountBank',title:'开户银行',width:120},    
								                {field:'accountNumber',title:'账户号码',width:150},
								            	{field:'accountType',title:'账户类型',width:150}
								            ]] " />
					</td>
				</tr>
				<tr class="currency hidden">
					<th>汇出金额</th>
					<td><input id="netAmount" name="netAmount" class="easyui-numberbox" data-options="validType:'less_equal_zero',required:true,precision:2,onChange:window.armgt.receivable.writeoffRefund.netAmountOnChange" />
					</td>
				</tr>
				<tr class="fxyk hidden">
					<th>手续费</th>
					<td><input id="factorage" name="factorage" class="easyui-numberbox" data-options="required:true,precision:2" value="0" />
					</td>
				</tr>
				<tr class="currency hidden">
					<th>结算汇率</th>
					<td><input id="bankRate" name="bankRate" class="easyui-numberbox" data-options="required:true,precision:2,onChange:window.armgt.receivable.writeoffRefund.bankRateOnChange" value="1" />
					</td>
				</tr>
				<tr class="currency hidden">
					<th>汇兑损益</th>
					<td><input id="exchangeProfitLoss" name="exchangeProfitLoss" class="easyui-numberbox" data-options="required:true,precision:2" value="0" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>凭证号</th>
					<td><input id="voucherNo" name="voucherNo" class="easyui-validatebox" data-options="required:true " /></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" style="border-left:solid 1px #ccc;">
		<table id="dtgd_wto" class="easyui-datagrid" data-options="fit:true,
						  rownumbers:true,
						  singleSelect:true,
						  border:false,
						  fitColumns:true,
						  onClickRow:window.armgt.receivable.writeoff.onClickRow">
			<thead>
				<tr>
					<th data-options="field:'noticeNo',width:90">通知单号</th>
					<th data-options="field:'tradeDate',width:90">交易日期</th>
					<th data-options="field:'balance',width:90,formatter:window.armgt.receivable.amountFormatter">应收金额</th>
					<th data-options="field:'incomeAmount',width:90,formatter:window.armgt.receivable.amountFormatter,editor:{type:'numberbox',options:{validType:['amount[\'#dtgd_wto\',\'balance\']'],required:true,precision:2,onChange:window.armgt.receivable.writeoff.incomeAmountOnChange}}">实收金额</th>
					<th data-options="field:'originalPaymentMethod',width:90,formatter:window.armgt.receivable.writeoffRefund.originalPaymentMethodFormatter">原收款方式</th>
				</tr>
			</thead>
		</table>
	</div>
</div>