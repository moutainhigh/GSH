<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>BSP票本管理</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<script type="text/javascript" src="${basePath}files/js/page.bsp.advanceEntry.js"></script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
		    <a href="#" class="easyui-linkbutton" iconCls="bicon-refresh" plain="true">刷新</a>
		</div>
		<div>
			Date From: <input class="easyui-datebox" style="width:100px"> To: <input class="easyui-datebox" style="width:100px"> 类型: <select class="easyui-combobox" panelHeight="auto" style="width:100px">
				<option value="1">国内</option>
				<option value="2">国际</option>
			</select> <a href="#" class="easyui-linkbutton" iconCls="bicon-search">Search</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="bsp_advanceEntry_dg" class="easyui-datagrid" data-options="fit:true,
						  pagination:true,
						  pagePosition:'both',
						  rownumbers:true,
						  singleSelect:true,
						  method:'get',
						  border:false,
						  url:'/cs/bsp/advanceEntryAction!findAll.action'">
			<thead>
				<tr>
					<th data-options="checkbox:true"></th>
					<th data-options="field:'settlementNo',width:100">交易号</th>
					<th data-options="field:'transactionType',width:100,formatter:window.bsp.advanceEntry.transactionType">交易类型</th>
					<th data-options="field:'transactionDate',width:100">交易日期</th>
					<th data-options="field:'amountMoney',width:100">金额</th>
					<th data-options="field:'voucherType',width:100,formatter:window.bsp.advanceEntry.voucherType">凭证类型</th>
					<th data-options="field:'invoiceDate',width:100">凭证日期</th>
					<th data-options="field:'invoiceMoney',width:100">发票总额</th>
					<th data-options="field:'invoiceNo',width:100">凭证号</th>
					<th data-options="field:'status',width:100,formatter:window.bsp.advanceEntry.status">状态</th>
				</tr>
			</thead>
		</table>
	</div>
<!-- 	<div data-options="region:'south'" style="height:70px;" id="sum_footer"></div> -->
</body>
</html>
