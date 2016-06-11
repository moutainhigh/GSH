<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>查询银行账户管理</title>
<meta charset="UTF-8">
</head>
<body>
<!-- External JS -->
    <div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;">
			<a id="bkatmg_search"  href="#" class="easyui-linkbutton"><i class="fa fa-save"></i>&nbsp;查询</a>
			<a id="bkatmg_close" href="#" class="easyui-linkbutton"><i class="fa fa-times"></i>&nbsp;关闭</a> 
		</div>
		<div data-options="region:'center',border:false" style="padding:9px;overflow: hidden;">
			<form id="bset_bkatmg_search_form" method="post">
				<table>
					<tr>
						<th align="left">账户编码</th>
						<td colspan="2">
							<input style="width:130px;" class="easyui-validatebox" name="accountCode" type="text" />
						</td>
					</tr>
					<tr style="padding-top: 30px"></tr>
					<tr>
						<th align="left" id="accountBank_kaihuhang">开户银行</th>
						<td colspan="2"><input style="width:130px;" class="easyui-validatebox" id="accountBank" name="accountBank" type="text" /></td>
					</tr>
					<tr style="padding-top: 30px"></tr>
					<tr>
						<th align="left">账户币种</th>
						<td colspan="2"> <input class="easyui-combobox" id="bkatmg_accountCurrency" name="accountCurrency" style="width:130px" 
    						data-options="valueField:'id',textField:'text',url:'/cs/files/baseJson/CurrencyUnit.json'"></td>
					</tr>
					<tr>
						<th align="left">账户类型</th>
						<td colspan="2"><select  name="accountType" class="easyui-combobox" id="bkatmg_accountType" data-options="editable:false,panelHeight:'auto',onChange:window.bset.bkatmg.newb.search.accountTypeOnChange">
							<option value="C">公司</option>
							<option value="P">个人</option>
							<option value="M">现金</option>
							<option value="X">信用卡</option>
							<option value="O">第三方支付</option>
						</select></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
