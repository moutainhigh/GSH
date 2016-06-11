<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>查询银行入账发布</title>
<meta charset="UTF-8">
</head>
<body>
<!-- External JS -->
    <div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;">
			<a id="theBank_search"  href="#" class="easyui-linkbutton"><i class="fa fa-save"></i>&nbsp;查询</a>
			<a id="theBank_close" href="#" class="easyui-linkbutton"><i class="fa fa-times"></i>&nbsp;关闭</a> 
		</div>
		<div data-options="region:'center',border:false" style="padding:9px;overflow: hidden;">
			<form id="account_theBank_serchtheBank_form" method="post">
				<table>
					<tr>
						<th align="left">付款方</th>
						<td colspan="2">
							<input style="width:150px;" class="easyui-validatebox" name="fukuanfang" type="text" />
						</td>
					</tr>
					<tr style="padding-top: 30px"></tr>
					<tr>
						<th align="left">凭证号</th>
						<td colspan="2"><input style="width:150px;" class="easyui-validatebox" name="documentNumber" type="text" />
					</tr>
					<tr style="padding-top: 30px"></tr>
					<tr>
						<th align="left">入账账户</th>
						<td colspan="2">
							<input id="accountCode_zhanghu" name="accountCode" class="easyui-combogrid" data-options="valueField: 'code',    
			        				idField:'accountCode',    
                                   textField:'accountCode',
                                   panelWidth:380,   
                                   required:true,
			        				url: '/cs/json/bankAccount.json',
						        columns:[[    
					                {field:'accountCode',title:'账户编码',width:80},    
					                {field:'accountBank',title:'开户银行',width:120},    
					                {field:'accountNumber',title:'账户号码',width:150}
					            ]] " />
						</td>
					</tr>
					<tr style="padding-top: 30px"></tr>
					<tr>
						<th align="left">状态</th>
						<td colspan="2">
							<select data-options="required:true" name="renling" style="width: 150px">
								<option selected="selected">未认领</option>
								<option>已认领</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
