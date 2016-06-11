<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>获得佣金</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<script type="text/javascript">
var currency_local = "${CURRENCY_UNIT}";
</script>
<script type="text/javascript" src="${basePath}files/js/page.account.commission.js"></script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="bicon-ok" id="commission_shouru" plain="true">收入</a>
		   	<a href="#" class="easyui-linkbutton" iconCls="bicon-search" plain="true" id="commission_search_gaoji">高级查询</a>
		    <a href="#" class="easyui-linkbutton" iconCls="bicon-refresh" plain="true">刷新</a>
		</div>
		<div>
			<form id="commission_form">
				产品航线：
				<input name="productRoute" style="width: 150px" class="easyui-combotree" data-options="required: true,url:'/cs/account/commissionAction!findAllRouteProducts.action'"/>
				交易日期：<input class="easyui-datebox" id="commission_date_qi_zhu" name="qiDate" style="width:100px"> 至: <input class="easyui-datebox" id="commission_date_shi_zhu" name="shiDate" style="width:100px">
				<a href="#" class="easyui-linkbutton" iconCls="bicon-search" id="btSearch">Search</a>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="commission_dg" class="easyui-datagrid" data-options="fit:true,
						  pagination:true,
						  pagePosition:'both',
						  rownumbers:true,
						  method:'get',
						  showFooter:true,
						  border:false,
						  url:'/cs/account/commissionAction!findAll.action',
						  onSelect:window.commission.onSelect,
						  onUnselect:window.commission.onUnselect,
						  onLoadSuccess:window.commission.productCommission.onLoadSuccess">
			<thead>
				<tr>
					<th data-options="checkbox:true"></th>
					<th data-options="field:'productOrderNo',width:100">产品单号</th>
					<th data-options="field:'productRoute',width:100,formatter:window.commission.productCommission.productRoute">产品航线</th>
					<th data-options="field:'supplierNo',width:100">供应商</th>
					<th data-options="field:'carrier',width:100">承运商</th>
					<th data-options="field:'incomeBeAmount',width:100,formatter:window.commission.productCommission.amountFormatter">应收金额</th>
					<th data-options="field:'incomeAmount',width:100,formatter:window.commission.productCommission.amountFormatter">已收金额</th>
					<th data-options="field:'balance',width:100,formatter:window.commission.productCommission.amountFormatter">余额</th>
					<th data-options="field:'tradeDate',width:100">日期</th>
					<th data-options="field:'currencyType',width:100,hidden:true">货币</th>
				</tr>
			</thead>
		</table>
	</div>
	
<!-- 	<div data-options="region:'south'" style="height:70px;" id="commission_footer"> -->
<!-- 		<table class="sum-footer"> -->
<!-- 			<tr> -->
<!-- 				<th></th> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<th>小计：</th> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<th>总计：</th> -->
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 	</div> -->

</body>
</html>
