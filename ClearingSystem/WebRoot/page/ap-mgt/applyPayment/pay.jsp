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
	<div data-options="region:'west',border:false,width:280" style="padding:9px;overflow: hidden;">
		<form id="form">
			<table>
				<tr>
					<th>结算编号</th>
					<td><input name="settlementNo" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>供应商编号</th>
					<td><input name="supplierNo" readonly="readonly" /> <input type="hidden" name="currencyType" id="currencyType" />
					</td>
				</tr>
				<tr>
					<th>收款人</th>
					<td><input name="supplierName" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>账号</th>
					<td><input name="bankAccountNo" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>实付金额</th>
					<td><input id="payAmount" name="payAmount" readonly="readonly" data-options="precision:2"/>
					</td>
				</tr>
				<tr>
					<th>付款方式</th>
					<td><input id="paymentMethod" name="paymentMethod" class="easyui-combobox" data-options="valueField: 'code',    
									        		 textField: 'text', 
									        		 panelHeight:'auto',
									        		 required:true,   
									       			 url: '/cs/files/baseJson/payWay.json',
									       			 onChange:window.apmgt.applyPayment.paymentMethodOnChange,
									       			 onHidePanel:window.apmgt.applyPayment.onHidePanel"/>
					</td>
				</tr>
				<tr>
					<th>付款日期</th>
					<td><input name="tradeDate" class="easyui-datebox" data-options="required:true " />
					</td>
				</tr>
				<tr>
					<th>资金账户</th>
					<td><input id="fundAccount" name="fundAccount" class="easyui-combogrid" data-options="valueField: 'code',    
									        idField:'accountCode',    
	                                        textField:'accountCode',
	                                        panelWidth:380, 
	                                        required:true, 
									        onChange:window.apmgt.applyPayment.pay.fundAccountOnChange,
									        columns:[[    
								                {field:'accountCode',title:'账户编码',width:80},    
								                {field:'accountBank',title:'开户银行',width:120},    
								                {field:'accountNumber',title:'账户号码',width:150}
								            ]] " />
					</td>
				</tr>
				<tr class="hl trhidden">
					<th>银行汇率</th>
					<td><input id="bankRate" name="bankRate" class="easyui-numberbox" value="1" data-options="precision:2,required:true,novalidate:true,onChange:window.apmgt.applyPayment.pay.bankRateOnChange" />
					</td>
				</tr>
				<tr class="hl trhidden">
					<th>汇兑损益</th>
					<td><input id="exchangeProfitLoss" name="exchangeProfitLoss" class="easyui-numberbox" value="0" data-options="precision:2,required:true" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>手续费</th>
					<td><input id="factorage" name="factorage" class="easyui-numberbox" value="0" data-options="precision:2,required:true" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th>凭证号</th>
					<td><input name="voucherNo" class="easyui-validatebox" data-options="required:true " />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false,height:'auto'" style="border-left:solid 1px #ccc;">
		<table id="dtgd_payable_detail" class="easyui-datagrid" data-options="fit:true,
						  rownumbers:true,
						  singleSelect:true,
						  border:false,
						  fitColumns:true">
			<thead>
				<tr>
					<th data-options="field:'exchangeNo',width:90">交换单号</th>
					<th data-options="field:'payAmount',width:90,formatter:window.apmgt.applyPayment.amountFormatter">本次付款金额</th>
					<th data-options="field:'currencyType',width:90,hidden:true">货币</th>
				</tr>
			</thead>
		</table>
	</div>
</div>


