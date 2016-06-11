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
<!-- 		<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;"> -->
<!-- 			<a id="commission_yongjinshouru"  href="#" class="easyui-linkbutton"><i class="fa fa-save"></i>&nbsp;收入</a> -->
<!-- 			<a id="commission_close" href="#" class="easyui-linkbutton"><i class="fa fa-times"></i>&nbsp;关闭</a>  -->
<!-- 		</div> -->
		<div data-options="region:'west',border:false"  style="width:265px;padding:9px; overflow: hidden;">
			<form id="account_commission_shourucommission_form" method="post">
				<table>
					<input type="hidden" name="currencyType" id="currencyType" />
					<tr>
						<th align="left">付款人</th>
						<td colspan="2"  id="fukanrenID">
							<input style="width:150px;" class="easyui-validatebox" id="carrier" name="carrier"  data-options="required:true"/>
						</td>
					</tr>
					<tr>
						<th align="left">应收金额</th>
						<td colspan="2">
							<input style="width:150px;" class="easyui-numberbox" id="yingshou" name="incomeBeAmount" data-options="min:0,precision:2,groupSeparator:','" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th align="left">实收金额</th>
						<td colspan="2">
							<input style="width:150px;" class="easyui-numberbox" id="shishou" name="incomeAmount" data-options="min:0,precision:2,groupSeparator:','" readonly="readonly" />
							<input type="hidden" id="incomeAmount" name="incomeAmount_zuofei" >
						</td>
					</tr>
					<tr>
						<th align="left">余额</th>
						<td colspan="2">
							<input style="width:150px;" class="easyui-numberbox" id="balance" name="balance" data-options="min:0,precision:2,groupSeparator:','"  readonly="readonly" />
						</td>
					</tr>
					<tr>
						<th align="left">收款方式</th>
						<td colspan="2">
							<select id="paymentMethod" name="paymentMethod"class="easyui-combobox" data-options="editable:false,panelHeight:'auto',onChange:window.commission.productCommission.paymentMethodOnChange,
									onHidePanel:window.commission.productCommission.onHidePanel">
								<option selected="selected" value="3">支票</option>
								<option value="4">转账/汇款</option>
								<option value="6">第三方支付</option>
							</select>
						</td>
					</tr>
					<tr>
						<th align="left">收款日期</th>
						<td colspan="2">
							<input class="easyui-datebox" id="tradeDate" name="tradeDate" style="width:100px" data-options="required:true">
						</td>
					</tr>
					<tr>
					<th>资金账户</th>
					<td><input id="comm_fundAccount" name="fundAccount" class="easyui-combogrid" data-options="valueField: 'code',    
									        idField:'accountCode',    
	                                        textField:'accountCode',
	                                        panelWidth:380, 
	                                        required:true,  
									        onChange:window.commission.productCommission.fundAccountOnChange,
									        columns:[[    
								                {field:'accountCode',title:'账户编码',width:80},    
								                {field:'accountBank',title:'开户银行',width:120},    
								                {field:'accountNumber',title:'账户号码',width:150}
								            ]] " /></td>
				</tr>
				<tr class="hl trhidden">
					<th>银行汇率</th>
					<td><input id="bankRate" name="bankRate" class="easyui-numberbox" value="1" data-options="precision:2,required:true,novalidate:true,onChange:window.commission.productCommission.bankRateOnChange" /></td>
				</tr>
				<tr class="hl trhidden">
					<th>汇兑损益</th>
					<td><input id="exchangeProfitLoss" name="exchangeProfitLoss" class="easyui-numberbox" value="0" data-options="precision:2,required:true" readonly="readonly" /></td>
				</tr>
				<tr>
					<th>手续费</th>
					<td><input name="factorage" id="factorage" class="easyui-numberbox" value="0" data-options="precision:2,required:true" /></td>
				</tr>
				<tr>
					<th>凭证号</th>
					<td><input name="voucherNo" class="easyui-validatebox" data-options="required:true " /></td>
				</tr>
					
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false" style="border-left:solid 1px #ccc;">
		<table id="dtgdWto_chanpindan" class="easyui-datagrid" data-options="fit:true,
						  rownumbers:true,
						  singleSelect:true,
						  border:false,
						  fitColumns:true,
						 onClickRow:window.commission.onClickRow">
			<thead>
				<tr>
					<th data-options="field:'cmsnid',width:90">佣金ID</th>
					<th data-options="field:'productOrderNo',width:90">交换单号</th>
					<th data-options="field:'incomeBeAmount',width:90,formatter:window.commission.productCommission.currencyType">应收金额</th>
					<th data-options="field:'incomeAmount',width:90,formatter:window.commission.productCommission.currencyType,editor:{type:'numberbox',options:{validType:['paidinMoney_wto'],required:true,precision:2,onChange:changeAmountOnChange}}">本次实收金额</th>
					<th data-options="field:'currencyType',width:90,hidden:true">货币</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
	