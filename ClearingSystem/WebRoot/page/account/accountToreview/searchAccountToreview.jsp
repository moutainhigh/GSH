<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>查询账目复核</title>
<meta charset="UTF-8">
</head>
<body>
<!-- External JS -->
    <div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;">
			<a id="accountToreview_search"  href="#" class="easyui-linkbutton"><i class="fa fa-save"></i>&nbsp;查询</a>
			<a id="accountToreview_close" href="#" class="easyui-linkbutton"><i class="fa fa-times"></i>&nbsp;关闭</a> 
		</div>
		<div data-options="region:'center',border:false" style="padding:9px;overflow: hidden;">
			<form id="account_accountToreview_serchAccountToreview_form" method="post">
				<table>
					<tr>
						<th align="left">账户</th>
<!-- 						<td colspan="2"> -->
<!-- 							<select> -->
<!-- 								<option selected="selected">银行账户</option> -->
<!-- 								<option>现金账户</option> -->
<!-- 								<option>第三方支付账户</option> -->
<!-- 							</select> -->
<!-- 						</td> -->
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
						<th align="left">凭证号</th>
						<td colspan="2"><input style="width:150px;" class="easyui-validatebox" name="documentNumber" type="text" />
					</tr>
					<tr style="padding-top: 30px"></tr>
					<tr>
						<th align="left">交易日期</th>
						<td colspan="2"> <input class="easyui-datebox" id="account_date_qi" name="date_qi" data-options="required:true,editable:false" style="width:100px"> 至: <input class="easyui-datebox" id="account_date_shi" name="date_shi" data-options="required:true,editable:false" style="width:100px"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			var date=new Date();
			var d=date.getMonth()+1;
			var date =  date.getFullYear()+'-'+d+'-01';
			var date1=new Date();
			var d1=date1.getMonth()+1;
			var date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
			$("#account_date_qi").val(date);
			$("#account_date_shi").val(date1);
		});
		
	</script>
</body>
</html>
