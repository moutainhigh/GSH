<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>异常订单查阅</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<script type="text/javascript" src="${basePath}files/js/page.account.orderAbnormal.js"></script>
<script>
	
</script>
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
		<table id="account_orderAbnormal_dg" class="easyui-datagrid" data-options="fit:true,
						  pagination:true,
						  pagePosition:'both',
						  rownumbers:true,
						  singleSelect:true,
						  showFooter: true,
						  method:'get',
						  url:'/cs/account/abnormalOrderAction!findAll.action',
						  border:false,
						  onLoadSuccess:window.account.orderAbnormal.onLoadSuccess">
			<thead>
				<tr>
					<th data-options="checkbox:true"></th>
					<th data-options="field:'exchangeNo',width:100">交换单号</th>
					<th data-options="field:'balance',width:100,formatter:window.account.orderAbnormal.amountFormatter">金额</th>
					<th data-options="field:'tradeDate',width:100">交易日期</th>
					<th data-options="field:'orderNo',width:100">订单号</th>
					<th data-options="field:'noticeNo',width:100">通知单号</th>
					<th data-options="field:'affiliationPerson',width:100">归属人</th>
				</tr>
			</thead>
		</table>
	</div>
<!-- <div data-options="region:'south'" style="height:70px;" id="sum_footer"></div> -->
</body>
</html>
