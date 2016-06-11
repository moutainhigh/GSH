<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>退款申请</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<script>
	var btn_submit_enable = "enable";
	var btn_payment_enable = "enable";
	var btn_revoke_enable = "enable";

	var currency_local = "${CURRENCY_UNIT}";
</script>
<script src="${basePath}files/js/page.armgt.RefundApplication.js"></script>

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
			<form id="RefundApplication_from">
				结算编号：<input name="settlementNo" class="easyui-validatebox" />
				客户编号：<input name="customerNo" class="easyui-validatebox" />
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
						  idField:'id',
						  url:'/cs/armgt/ctpm!datagrid.action',
						  border:false,
						  onSelect:window.armgt.refundapplication.onSelect">
			<thead>
				<tr>
					<th data-options="field:'options',width:70,align:'center',formatter:window.armgt.refundapplication.optionsFormatter">操作</th>
					<th data-options="field:'settlementNo',width:250">结算编号</th>
					<th data-options="field:'customerNo',width:80">客户编号</th>
					<th data-options="field:'bankAccountNo',width:120">账号</th>
					<th data-options="field:'createDate',width:70">创建日期</th>
					<th data-options="field:'incomeAmount',width:90,align:'right',formatter:window.armgt.refundapplication.amountFormatter">实收金额</th>
					<th data-options="field:'status',width:80,formatter:window.armgt.refundapplication.statusFormatter">状态</th>
					<th data-options="field:'remark',width:100">备注</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>
