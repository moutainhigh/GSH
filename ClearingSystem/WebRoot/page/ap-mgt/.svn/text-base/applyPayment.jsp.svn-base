<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>付款申请</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<style type="text/css">
.sum-footer td,.sum-footer th {
	height: 20px;
	width: 100px;
	text-align: center;
}
</style>
<!-- External JS -->
<script>
	var btn_submit_enable = "enable";
	var btn_payment_enable = "enable";
	var btn_revoke_enable = "enable";
</script>
<script src="${basePath}files/js/page.apmgt.applyPayment.js"></script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="bicon-ok" plain="true" id="btn_submit">提交审核</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-print" plain="true" id="btn_print">打印</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-tag" plain="true" id="btn_payment">销账</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-repeat" plain="true" id="btn_revoke">撤销</a>
		</div>
		<div>
			<form id="applyPayment_from">
				结算编号：<input name="settlementNo" class="easyui-validatebox" />
				供应商编号：<input name="supplierNo" class="easyui-validatebox" />
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
						  url:'/cs/apmgt/apply!datagrid.action',
						  border:false,
						  showFooter: true,
						  onSelect:window.apmgt.applyPayment.onSelect,
						  onLoadSuccess:window.apmgt.applyPayment.onLoadSuccess">
			<thead>
				<tr>
					<th data-options="field:'options',width:70,align:'center',formatter:window.apmgt.applyPayment.optionsFormatter">操作</th>
					<th data-options="field:'settlementNo',width:90">结算编号</th>
					<th data-options="field:'exchangeNos',width:120">交换单号</th>
					<th data-options="field:'supplierNo',width:80">供应商编号</th>
					<th data-options="field:'bankAccountNo',width:120">账号</th>
					<th data-options="field:'createDate',width:70">创建日期</th>
					<th data-options="field:'deadline',width:70">结算期限</th>
					<th data-options="field:'payAmount',width:90,align:'right',formatter:window.apmgt.applyPayment.amountFormatter">实付金额</th>
					<th data-options="field:'status',width:60,formatter:window.apmgt.applyPayment.statusFormatter">状态</th>
					<th data-options="field:'remark',width:200">备注</th>
					<th data-options="field:'currencyType',width:100,hidden:true">货币</th>
					<th data-options="field:'paymentMethod',hidden:true">货币</th>
				</tr>
			</thead>
		</table>
	</div>
<!-- 	<div data-options="region:'south'" style="height:70px;" id="sum_footer"></div> -->
</body>
</html>
