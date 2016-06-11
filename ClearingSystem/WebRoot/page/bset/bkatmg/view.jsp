<%@ page pageEncoding="UTF-8"%>
<style>
.view_cll {
	display: none;
}
</style>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;">
		<a id="btnClose" href="#" class="easyui-linkbutton"><i class="fa fa-times"></i>&nbsp;关闭</a>
	</div>
	<div data-options="region:'center',border:false" style="padding:9px;overflow: hidden;">
		<table>
			<tr>
				<th align="left">账户编码</th>
				<td id="accountCodeTxt"></td>
			</tr>
			<tr>
				<th align="left">账户类型</th>
				<td id="accountTypeTxt"></td>
			</tr>
			<tr>
				<th align="left">币种</th>
				<td id="accountCurrencyTxt"></td>
			</tr>
			<tr>
				<th align="left">开户人</th>
				<td id="accountOwnerTxt"></td>
			</tr>
			<tr>
				<th align="left">开户银行</th>
				<td id="accountBankTxt"></td>
			</tr>
			<tr>
				<th align="left">账号</th>
				<td id="accountNumberTxt"></td>
			</tr>
			<tr class="ml view_cll">
				<th align="left">期初余额</th>
				<td id="accountBalanceTxt"></td>
			</tr>
			<tr>
				<th align="left">开户行地址</th>
				<td id="accountBankAdressTxt"></td>
			</tr>
			<tr>
				<th align="left">行号/SWIFT</th>
				<td id="accountBankNumberTxt"></td>
			</tr>
			<tr class="ml view_cll">
				<th align="left">余额提醒</th>
				<td id="accountReminderTxt"></td>
			</tr>
			<tr>
				<th align="left">状态</th>
				<td id="accountStatusTxt"></td>
			</tr>
			<tr>
				<th align="left">默认收款账户</th>
				<td id="defaultIncAccountTxt"></td>
			</tr>
			<tr>
				<th align="left">默认付款账户</th>
				<td id="defaultPayAccountTxt"></td>
			</tr>
		</table>
	</div>
</div>

