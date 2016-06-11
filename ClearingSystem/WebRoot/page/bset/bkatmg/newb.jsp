<%@ page pageEncoding="UTF-8"%>

<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;">
		<a id="bset_bkatmg_newb_save_btn" href="#" class="easyui-linkbutton"><i class="fa fa-save"></i>&nbsp;保存</a> <a id="bset_bkatmg_newb_close_btn" href="#" class="easyui-linkbutton"><i class="fa fa-times"></i>&nbsp;放弃</a>
	</div>
	<div data-options="region:'center',border:false" style="padding:9px;overflow: hidden;">
		<form id="bset_bkatmg_newb_form">
			<input type="hidden" name="userId" value="">
			<input type="hidden" name="accountLock" value="">
			<input type="hidden" name="adminId" value="">
			<input type="hidden" name="deptId" value="">
			<table>
				<tr>
					<th align="left">账户编码</th>
					<th align="left"></th>
					<th align="left">账户类型</th>
					<th align="left">币种</th>
				</tr>
				<tr>
					<td colspan="2"><input id="accountCode" name="accountCode" class="easyui-validatebox" style="width:173px;" data-options="validType:'accountCode[0]'" type="text" placeholder="若不输入，系统将自动赋值"/></td>
					<td><select id="bset_bkatmg_newb_accountType" name="accountType" class="easyui-combobox" data-options="editable:false,panelHeight:'auto',onChange:window.bset.bkatmg.newb.pub.accountTypeOnChange">
							<option value="C">公司</option>
							<option value="P">个人</option>
							<option value="M">现金</option>
							<option value="X">信用卡</option>
							<option value="O">第三方支付</option>
					</select></td>
					<td><select id="bset_bkatmg_newb_accountCurrency" name="accountCurrency" class="easyui-combobox" style="width:73px;" data-options="editable:false,panelHeight:'auto',valueField:'id',textField:'text',url:'/cs/files/baseJson/CurrencyUnit.json',onChange:window.bset.bkatmg.newb.pub.accountCurrencyOnChange"></select></td>
				</tr>
				<tr>
					<th id="bset_bkatmg_newb_accountOwnerTitle" align="left">开户公司CN</th>
					<th></th>
					<th align="left">期初余额</th>
					<th></th>
				</tr>
				<tr>
					<td colspan="2"><input id="accountOwner" name="accountOwner" style="width:173px;" class="easyui-validatebox" data-options="required:true" type="text" /></td>
					<td colspan="2"><input name="accountBalance" style="width:179px;" class="easyui-numberbox" data-options="required:true,min:0,precision:2,groupSeparator:','" type="text" /></td>
				</tr>
				<tr id="tr1">
					<th id="bset_bkatmg_newb_accountBankTitle" align="left" >开户银行</th>
					<th></th>
					<th align="left">账号</th>
					<th></th>
				</tr>
				<tr id="tr2">
					<td colspan="2" ><input id="bset_bkatmg_newb_accountBank" name="accountBank" style="width:173px;" class="easyui-validatebox" data-options="required:true" type="text" /></td>
<!-- 					<td colspan="2" ><input id="bset_bkatmg_newb_accountNumber" name="accountNumber" style="width:173px;" class="easyui-validatebox" data-options="required:true,validType:'minLength_fengfenzheng[23]'" type="text" /></td> -->
					<td colspan="2" ><input id="bset_bkatmg_newb_accountNumber" name="accountNumber" style="width:173px;" class="easyui-validatebox" data-options="required:true" type="text" /></td>
				</tr>
				<tr id="tr3">
					<th align="left">开户行地址</th>
					<th align="left"></th>
					<th align="left"></th>
					<th align="left">行号/SWIFT</th>
				</tr>
				<tr id="tr4">
					<td colspan="3"><input id="accountBankAdress" name="accountBankAdress" style="width:273px;" class="easyui-validatebox" data-options="novalidate:true" type="text" /></td>
					<td><input id="accountBankNumber" name="accountBankNumber" style="width:73px;" class="easyui-validatebox" data-options="novalidate:true" type="text" /></td>
				</tr>
				<tr>
					<th align="left">余额提醒</th>
					<th></th>
					<th align="left">是否激活账户</th>
					<th></th>
				</tr>
				<tr>
					<td colspan="2"><input name="accountReminder" style="width:150px;" class="easyui-numberbox" data-options="required:true,min:0,precision:2,groupSeparator:','" type="text" />
					</td>
					<td colspan="2"><input type="radio" name="accountStatus" value="1" /> Y&nbsp;&nbsp;<input type="radio" name="accountStatus" value="0" checked="checked" /> N</td>
				</tr>
				<tr id="tr5">
					<th colspan="2" align="left"  class="hl trhidden">设置为默认收款账户</th>
					<th colspan="2" align="left"  class="hl trhidden">设置为默认付款账户</th>
				</tr>
				<tr id="tr6">
					<td colspan="2"><input type="radio" name="defaultIncAccount" value="1" disabled="disabled" /> Y&nbsp;&nbsp;<input type="radio" name="defaultIncAccount" value="0" checked="checked" disabled="disabled" /> N</td>
					<td colspan="2"><input type="radio" name="defaultPayAccount" value="1" disabled="disabled" /> Y&nbsp;&nbsp;<input type="radio" name="defaultPayAccount" value="0" checked="checked" disabled="disabled" /> N</td>
				</tr>
				
			</table>
		</form>
	</div>
</div>

