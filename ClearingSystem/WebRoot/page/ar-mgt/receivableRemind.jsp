<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>应收账款账期提醒</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<script>
	var currency_local = "${CURRENCY_UNIT}";
</script>
<script src="${basePath}files/js/page.armgt.receivableRemind.js"></script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="bicon-refresh" plain="true" id="btn_shua">刷新</a>
		</div>
		<div>
			<form id="receivableRemind_from">
				应收账户:<input name="customerNo" id="customerNo" class="easyui-combobox" data-options="panelHeight:'auto',valueField:'customerNo',textField:'customerNo',url:'/cs/armgt/receivableViewAction!findAllCustomerNo.action'" />
				结算期限:<input class="easyui-datebox" name="deadline" id="deadline" style="width:100px">
				<a href="#" class="easyui-linkbutton" iconCls="bicon-search" id="receivableRemind_Search">Search</a>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="dtgd" class="easyui-datagrid" data-options="fit:true,
						 pagination:true,
						  pagePosition:'both',
						  rownumbers:true,
						  singleSelect:true,
						  method:'get',
						  border:false,
						  fitColumns:true,
						  idField:'id',
						  showFooter: true,
						  url:'/cs/armgt/receivableRemind!findAll.action',
						  rowStyler:window.armgt.receivableRemind.styler">
			<thead>
				<tr>
					<th data-options="field:'deadline',width:120,align:'right'">结算期限</th>
					<th data-options="field:'customerNo',width:120,align:'right'">应收账户</th>
					<th data-options="field:'incomeBeAmount',width:120,align:'right',formatter:window.armgt.receivableRemind.amountFormatter">应收金额</th>
					<th data-options="field:'incomeAmount',width:120,align:'right',formatter:window.armgt.receivableRemind.amountFormatter">已收金额</th>
					<th data-options="field:'balance',width:120,align:'right',formatter:window.armgt.receivableRemind.amountFormatter">余额</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>
