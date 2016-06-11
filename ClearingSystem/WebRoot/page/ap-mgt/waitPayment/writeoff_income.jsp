<%@ page pageEncoding="UTF-8"%>
<style>
#form table th {
	text-align: right;
}

.trhidden {
	display: none;
}
</style>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'west',border:false,width:250" style="padding:9px;overflow: hidden;">
		<form id="form">
			<table>
				<tr>
					<th>供应商编号</th>
					<td><input name="supplierNo" readonly="readonly" /> <input type="hidden" name="currencyType" id="currencyType" /></td>
				</tr>
				<tr>
					<th>付款人</th>
					<td><input name="supplierName" /></td>
				</tr>
				<tr>
					<th>账号</th>
					<td><input name="bankAccountNo" id="bankAccountNo"/></td>
				</tr>
				<tr>
					<th>应付金额</th>
					<td><input id="payBeAmount" name="payBeAmount" readonly="readonly" data-options="precision:2"/></td>
				</tr>
				<tr>
					<th>实付金额</th>
					<td><input id="payAmount" name="payAmount" readonly="readonly" data-options="precision:2"/></td>
				</tr>
				<tr>
					<th>余额</th>
					<td><input id="balance" name="balance" readonly="readonly" data-options="precision:2"/></td>
				</tr>
				<tr>
					<th>收款方式</th>
					<td><input name="paymentMethod" class="easyui-combobox" data-options="valueField: 'code',    
									        		 textField: 'text', 
									        		 panelHeight:'auto',
									        		 required:true,   
									        		 value:4,
									       			 url: '/cs/files/baseJson/payWay.json'" /></td>
				</tr>
				<tr>
					<th>收款日期</th>
					<td><input name="tradeDate" class="easyui-datebox" data-options="required:true " /></td>
				</tr>
				<tr>
					<th>资金账户</th>
					<td><input id="fundAccount" name="fundAccount" class="easyui-combogrid" data-options="valueField: 'code',    
									        idField:'accountCode',    
	                                        textField:'accountCode',
	                                        panelWidth:380, 
	                                        required:true,  
									        url: '/cs/pub/find!findAccount.action',
									        onChange:window.apmgt.waitPayment.writeoffIncome.fundAccountOnChange,
									        columns:[[    
								                {field:'accountCode',title:'账户编码',width:80},    
								                {field:'accountBank',title:'开户银行',width:120},    
								                {field:'accountNumber',title:'账户号码',width:150}
								            ]] " /></td>
				</tr>
				<tr class="hl trhidden">
					<th>银行汇率</th>
					<td><input id="bankRate" name="bankRate" class="easyui-numberbox" value="1" data-options="precision:2,required:true,novalidate:true,onChange:window.apmgt.waitPayment.writeoffIncome.bankRateOnChange" /></td>
				</tr>
				<tr class="hl trhidden">
					<th>汇兑损益</th>
					<td><input id="exchangeProfitLoss" name="exchangeProfitLoss" class="easyui-numberbox" value="0" data-options="precision:2,required:true" readonly="readonly" /></td>
				</tr>
				<tr>
					<th>手续费</th>
					<td><input id="factorage" name="factorage" class="easyui-numberbox" value="0" data-options="precision:2,required:true" /></td>
				</tr>
				<tr>
					<th>交易号</th>
					<td><input name="tradeNo" class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<th>凭证号</th>
					<td><input name="voucherNo" class="easyui-validatebox" data-options="required:true " /></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false,height:'auto'" style="border-left:solid 1px #ccc;">
		<table id="dtgd_payable_detail" class="easyui-datagrid" data-options="fit:true,
						  rownumbers:true,
						  singleSelect:true,
						  border:false,
						  fitColumns:true,
						  onClickRow:window.apmgt.waitPayment.writeoffIncome.onClickRow">
			<thead>
				<tr>
					<th data-options="field:'exchangeNo',width:110">交换单号</th>
					<th data-options="field:'tradeDate',width:100">交易日期</th>
					<th data-options="field:'balance',width:90,formatter:window.apmgt.waitPayment.amountFormatter">应付金额</th>
					<th data-options="field:'payAmount',width:130,formatter:window.apmgt.waitPayment.amountFormatter,editor:{type:'numberbox',options:{validType:['amount[\'dtgd_payable_detail\']'],required:true,precision:2,onChange:window.apmgt.waitPayment.writeoffIncome.payAmountOnChange}}">本次付款金额</th>
					<th data-options="field:'currencyType',width:90,hidden:true">货币</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
