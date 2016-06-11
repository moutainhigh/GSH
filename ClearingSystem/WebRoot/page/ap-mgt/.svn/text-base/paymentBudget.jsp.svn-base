<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>应付预算</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<script type="text/javascript">
	$(function(){
		var date1 = new Date();
			var d1 = date1.getMonth() + 1;
			var date1 = date1.getFullYear() + '-' + d1
					+ '-' + date1.getDate();
			$("#tradeDate").datebox('setValue',date1);		
			$('#supplierType').combobox({
				value:'',
				url : '/cs/apmgt/paymentBudgetAction!findByPurType.action',
				valueField : 'code',
				textField : 'purType'
			});
	});
</script>
<script type="text/javascript" src="${basePath}files/js/page.apmgt.paymentBudget.js"></script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
		    <a href="#" class="easyui-linkbutton" iconCls="bicon-refresh" plain="true">刷新</a>
		</div>
		<div>
			截止日期:<input id="tradeDate" style="width:100px"  class="easyui-datebox"> 供应商类型：<input id="supplierType" class="easyui-combobox"> 
			<a href="#" class="easyui-linkbutton" iconCls="bicon-search" id="pbSearch">Search</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="apmgt_paymentBudget_dg" class="easyui-datagrid" data-options="fit:true,
<!-- 						  pagination:true, -->
<!-- 						  pagePosition:'both', -->
						  rownumbers:true,
						  singleSelect:true,
						  method:'get',
						  border:false,
						  showFooter: true,
<!--  						  url:'/cs/apmgt/paymentBudgetAction!findAll.action', -->
						  onLoadSuccess:window.apmgt.paymentBudget.onLoadSuccess">
			<thead>
				<tr>
					<th data-options="field:'orderNo',width:100">订单号</th>
					<th data-options="field:'tradeDate',width:100">订单日期</th>
					<th data-options="field:'cost',width:90">成本</th>
					<th data-options="field:'sale',width:85">销售</th>
					<th data-options="field:'supplierName',width:100">供应商</th>
					<th data-options="field:'type',width:100">类型</th>
					<th data-options="field:'customerName',width:100">客户</th>
					<th data-options="field:'customerType',width:100">客户类型</th>
					<th data-options="field:'issuer',width:100">业务员</th>
					<th data-options="field:'dept',width:100">部门</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>
