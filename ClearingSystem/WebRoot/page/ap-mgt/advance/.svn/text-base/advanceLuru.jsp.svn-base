<%@ page pageEncoding="UTF-8"%>

    <div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'west',border:false"  style="width:265px;padding:9px; overflow: hidden;">
			<form id="apmgt_advance_entry_form" method="post">
				<table>
					<tr>
						<th align="left">交易号</th>
						<td colspan="2">
							<input type="text" name="settlementNo" id="settlementNo" data-options="required:true" >
						</td>
					</tr>
					<tr style="padding-top: 30px"></tr>
					<tr>
						<th align="left">交易类型</th>
						<td colspan="2">
							<select id="transactionType" name="transactionType" data-options="required:true" >
								<option value="1">支出</option>
								<option value="2">收入</option>
							</select>
						</td>
					</tr>
					<tr style="padding-top: 30px"></tr>
					<tr>
						<th align="left">交易对象</th>
						<td colspan="2">
							<input style="width:150px;" class="easyui-validatebox" id="transactionOp" name="transactionOp" type="text" data-options="required:true" />
						</td>
					</tr>
					<tr style="padding-top: 30px"></tr>
					<tr>
						<th align="left">交易日期</th>
						<td colspan="2">
							<input style="width:150px;" class="easyui-datebox" id="transactionDate" name="transactionDate" data-options="required:true" />
						</td>
					</tr>
					<tr style="padding-top: 30px"></tr>
					<tr>
						<th align="left">金额</th>
						<td colspan="2">
							<input style="width:150px;" class="easyui-numberbox" id="amountMoney" name="amountMoney" data-options="min:0,precision:2,groupSeparator:','"  readonly="readonly" />
						</td>
					</tr>
					<tr style="padding-top: 30px"></tr>
					<tr>
						<th align="left">凭证类型</th>
						<td colspan="2">
							<select id="voucherType" name="voucherType" onchange="window.apmgt.advance.gaibian()">
								<option value="1">发票</option>
								<option value="2">行程单</option>
							</select>
						</td>
					</tr>
					<input type="hidden" name="supmid" id="supmid">
					<input type="hidden" name="pablid" id="pablid">
				</table>
			</form>
		</div>
		<div data-options="region:'center',border:false" style="border-left:solid 1px #ccc;">
		<a href="#" class="easyui-linkbutton" iconCls="bicon-asterisk" plain="true" id="advanceLuruAdd">添加</a>
		<a href="#" class="easyui-linkbutton" iconCls="bicon-remove" plain="true" id="advanceLuruDelete">删除</a>
		<table id="advanceLuru_datagrid" class="easyui-datagrid" data-options="fit:true,
						  rownumbers:true,
						  singleSelect:true,
						  border:false,
						  fitColumns:true,
						 onClickRow:window.apmgt.advance.onClickRow">
			<thead>
				<tr>
					<th data-options="field:'invoiceNo',width:90,editor:{type:'validatebox',options:{validType:['invoiceNo'],required:true}}">发票号</th>
					<th data-options="field:'invoiceMoney',width:90,formatter:window.apmgt.advance.amountFormatter,editor:{type:'numberbox',options:{validType:['realAmount'],required:true,precision:2,onChange:invoiceMoneyOnChange}}">发票金额</th>
					<th data-options="field:'invoiceDate',width:90">开票日期</th>
					<th data-options="field:'currencyType',width:90,hidden:true">货币</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
	