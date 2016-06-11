<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>账目明细</title>
<meta charset="UTF-8">
</head>
<body>
<!-- External JS -->
    <div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;">
			<a id="openAccount_remoud" class="easyui-linkbutton" style="width: 50px;float: right;"><i class="fa fa-times"></i>&nbsp;关闭</a> 
			<a id="chansheng_save"  class="easyui-linkbutton" style="width: 100px;float: right;"><i class="fa fa-save"></i>&nbsp;签发收据</a>
		</div>
		<div data-options="region:'center',border:false" style="overflow: hidden;">
			<table id="openCollectPayRecord_dg" class="easyui-datagrid" data-options="
					width : 540 ,
				    height : 275,
				    pagination:true,
				    showFooter:true,
					pagePosition:'both',
					rownumbers:true,
					method:'get',
					border:false">
			<thead>
				<tr>
					<th data-options="field:'exchangeNo',width:120,align:'right'">文档号码</th>
					<th data-options="field:'accountNo',width:120,align:'right'">账户</th>
					<th data-options="field:'payAmount',width:120,align:'right',formatter:window.account.collectPayRecord.amountFormatter">金额</th>
					<th data-options="field:'currencyType',width:100,hidden:true">货币</th>
					<th data-options="field:'theOperator',width:100">操作人</th>
				</tr>
			</thead>
		</table>
		<div data-options="region:'south'" style="height:70px;" id="sum_footer2"></div>
		</div>
	</div>
</body>
</html>
