<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>信用卡成本</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<script type="text/javascript">
	var currency_local = "${CURRENCY_UNIT}";
</script>
<script type="text/javascript" src="${basePath}files/js/page.account.creditCost.js"></script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:30px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
		    <a href="#" class="easyui-linkbutton" iconCls="bicon-search" plain="true" id="creditCost_search_gaoji">高级查询</a>
		    <a href="#" class="easyui-linkbutton" iconCls="bicon-refresh" plain="true">刷新</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="credit_creditCost_dg" class="easyui-datagrid" data-options="fit:true,
						  pagination:true,
						  pagePosition:'both',
						  rownumbers:true,
						  singleSelect:true,
						  method:'get',
						  showFooter:true,
						  border:false">
			<thead>
				<tr>
					<th data-options="field:'creditDate',width:100">日期</th>
					<th data-options="field:'tradeNo',width:100">单号</th>
					<th data-options="field:'userId',width:100">TC</th>
					<th data-options="field:'saleMoney',width:100,formatter:window.credit.creditCost.amountFormatter">销售金额</th>
					<th data-options="field:'costAmount',width:100,formatter:window.credit.creditCost.amountFormatter">成本金额</th>
					<th data-options="field:'grossProfit',width:100,styler:window.credit.creditCost.grossProfit,formatter:window.credit.creditCost.amountFormatter">毛利</th>
				</tr>
			</thead>
		</table>
	</div>

</body>
</html>
