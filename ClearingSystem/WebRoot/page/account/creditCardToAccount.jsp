<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>信用卡到账管理</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<script>
	var btn_sure_enable = "enable";

	var currency_local = "${CURRENCY_UNIT}";
</script>
<script src="${basePath}files/js/page.account.creditCardToAccount.js"></script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="bicon-ok" plain="true" id="btn_sure">确认到账</a>
		</div>
		<div>
			<form id="creditCardToAccount_form">
				收款日期:
				<input class="easyui-datebox" id="incomeDateQi" name="incomeDateQi" style="width:100px">
				To:
				<input class="easyui-datebox" id="incomeDateShi" name="incomeDateShi" style="width:100px">
				交易号： <input type="text" style="width:100px"  name="tradeNo">
				<a href="#" class="easyui-linkbutton" iconCls="bicon-search" id="btnSearch">Search</a>
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
						  showFooter: true,
						  url:'/cs/account/ccta!datagrid.action',
						  border:false,
						  onSelect:window.account.ccta.onSelect">
			<thead>
				<tr>
					<th data-options="field:'options',width:70,align:'center',formatter:window.account.ccta.optionsFormatter">操作</th>
					<th data-options="field:'tradeNo',width:90">交易号</th>
					<th data-options="field:'paymentMethod',width:80">收款方式</th>
					<th data-options="field:'incomeDate',width:80">收款日期</th>
					<th data-options="field:'customerName',width:100">付款人</th>
					<th data-options="field:'incomeAmount',width:120,align:'right',formatter:window.account.ccta.amountFormatter">预计到账金额</th>
					<th data-options="field:'customerFactorage',hidden:true">信用卡手续费</th>
					<th data-options="field:'remark',width:190">备注</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>
