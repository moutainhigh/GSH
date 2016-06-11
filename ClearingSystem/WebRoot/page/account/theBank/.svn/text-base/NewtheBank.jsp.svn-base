<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>发布银行到账信息</title>
<meta charset="UTF-8">
</head>
<body>
<!-- External JS -->
   <script type="text/javascript">
		var bankData = {
			total : 10,
			rows : [
			 {
				"id" : "123",
				"name" : "张三",
			}, {
				"id" : "345",
				"name" : "李四",
			}]
		};
	</script>
    <div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;">
			<a id="theBank_save"  href="#" class="easyui-linkbutton"><i class="fa fa-save"></i>&nbsp;保存</a>
			<a id="theBank_remoud" href="#" class="easyui-linkbutton"><i class="fa fa-times"></i>&nbsp;放弃</a> 
		</div>
		<div data-options="region:'center',border:false" style="padding:9px;overflow: hidden;">
			<form id="account_theBank_newtheBank_form" method="post">
				<table>
					<tr>
						<th align="left">到账金额</th>
						<td colspan="2"><input style="width:150px;" class="easyui-numberbox" name="toaccountMoney" id="bank_toaccountMoney" data-options="required:true,min:0,precision:2,groupSeparator:','" type="text" /></td>
					</tr>
					<tr style="padding-top: 30px"></tr>
					<tr>
						<th align="left">到账日期</th>
						<td colspan="2"><input style="width:150px;" class="easyui-datebox" name="toaccountDate" id="bank_toaccountDate" data-options="required:true,editable:false" type="text" /></td>
					</tr>
					<tr style="padding-top: 30px"></tr>
					<tr>
						<th align="left">付款方</th>
						<td colspan="2"><input style="width:150px;"  name="fuKuanfang"  class="easyui-combogrid" id="bank_fuKuanfang" style="width:250px;"   
									        data-options="    
									            panelWidth:150,    
									            panelHeight:150, 
									            value:'请选择',    
									            idField:'name',    
									            textField:'name',    
									            data:bankData,    
									            columns:[[    
									                {field:'id',title:'编号',width:50},    
									                {field:'name',title:'付款方',width:90}    
									            ]]    
									        "/>
						</td>
					<tr style="padding-top: 30px" ></tr>
					<tr>
						<th align="left">入账账户</th>
						<td colspan="2">
						<input id="accountCode_zhanghu" name="accountCode" id="bank_recordedAccount" class="easyui-combogrid" data-options="valueField: 'code',    
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
					<tr style="padding-top: 30px" ></tr>
					<tr>
						<th align="left">凭证号</th>
						<td colspan="2"><input style="width:150px;" class="easyui-validatebox" id="bank_documentNumber" name="documentNumber" data-options="required:true" type="text" />
						<input type="hidden" value="0" name="state"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</body>
</html>
