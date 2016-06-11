<%@ page pageEncoding="UTF-8"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'west',border:false" style="width:270px;padding:9px;overflow: hidden;">
		<form id="form">
			<table>
				<tr>
					<th>供应商编号</th>
					<td><input name="supplierNo" readonly="readonly" /> <input type="hidden" name="currencyType" id="currencyType" />
					</td>
				</tr>
				<tr>
					<th>收款人</th>
					<td><input name="supplierName" />
					</td>
				</tr>
				<tr>
					<th>账号</th>
					<td><input name="bankAccountNo" id="bankAccountNo"/>
					</td>
				</tr>
				<tr>
					<th>应付金额</th>
					<td><input id="payBeAmount" name="payBeAmount" readonly="readonly" data-options="precision:2" />
					</td>
				</tr>
				<tr>
					<th>实付金额</th>
					<td><input id="payAmount" name="payAmount" readonly="readonly" data-options="precision:2"/>
					</td>
				</tr>
				<tr>
					<th>余额</th>
					<td><input id="balance" name="balance" readonly="readonly" data-options="precision:2"/>
					</td>
				</tr>
				<tr>
					<th>付款方式</th>
					<td><input name="paymentMethod" class="easyui-combobox" data-options="valueField: 'code',    
									        		 textField: 'text', 
									        		 panelHeight:'auto',
									        		 required:true,   
									        		 value:4,
									       			 url: '/cs/files/baseJson/payWay.json'" /></td>
				</tr>
				<tr id="factorage_apply">
					<th>手续费</th>
					<td><input id="factorage" name="factorage" class="easyui-numberbox" value="0" data-options="precision:2,required:true" />
					</td>
				</tr>
				<tr>
					<th>结算期限</th>
					<td><input name="deadline" class="easyui-datebox" /></td>
				</tr>
				<tr>
					<th>备注</th>
					<td><textarea name="remark" style="width: 150px;height:65px;"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" style="border-left:solid 1px #ccc;">
		<table id="dtgd_apply" class="easyui-datagrid" data-options="fit:true,
						  rownumbers:true,
						  singleSelect:true,
						  border:false,
						  fitColumns:true,
						  onClickRow:window.apmgt.waitPayment.apply.onClickRow">
			<thead>
				<tr>
					<th data-options="field:'exchangeNo',width:110">交换单号</th>
					<th data-options="field:'tradeDate',width:100">交易日期</th>
					<th data-options="field:'balance',width:90,formatter:window.apmgt.waitPayment.amountFormatter">应付金额</th>
					<th data-options="field:'payAmount',width:130,formatter:window.apmgt.waitPayment.amountFormatter,editor:{type:'numberbox',options:{validType:['amount[\'dtgd_apply\']'],required:true,precision:2,onChange:window.apmgt.waitPayment.apply.payAmountOnChange}}">本次付款金额</th>
					<th data-options="field:'currencyType',width:90,hidden:true">货币</th>
				</tr>
			</thead>
		</table>
	</div>
</div>



