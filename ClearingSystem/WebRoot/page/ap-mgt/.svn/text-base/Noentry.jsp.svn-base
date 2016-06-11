<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>未录入凭证</title>
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
	var btn_entry_enable = "enable";
</script>
<script src="${basePath}files/js/page.apmgt.noentery.js"></script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="bicon-pencil" plain="true" id="btn_entry">录入</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-search" plain="true" id="btn_query">高级查询</a>
		</div>
		<div>
			<form id="Noentry_form">
				付款日期：<input class="easyui-datebox" id="tradeDateQi_Zhu" name="tradeDateQi" style="width:100px"> To: <input class="easyui-datebox" style="width:100px" id="tradeDateShi_Zhu" name="tradeDateShi">
				结算编号：<input name="settlementNo" id="settlementNo" style="width: 100px"/>
				供应商编号：<input name="supplierNo" id="supplierNo" style="width: 100px" class="easyui-combobox" data-options="panelHeight:'auto',valueField:'supplierNo',textField:'supplierNo',url:'/cs/apmgt/wpAction!findAllSupplierNo.action'" />
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
<!-- 						  url:'/cs/apmgt/apply!datagrid.action', -->
						  border:false,
						  showFooter: true,
						  onSelect:window.apmgt.noentery.onSelect,
						  onLoadSuccess:window.apmgt.noentery.onLoadSuccess">
			<thead>
				<tr>
					<th data-options="field:'options',width:70,align:'center',formatter:window.apmgt.noentery.optionsFormatter">操作</th>
					<th data-options="field:'settlementNo',width:90">结算编号</th>
					<th data-options="field:'supplierNo',width:80">供应商编号</th>
					<th data-options="field:'bankAccountNo',width:120">账号</th>
					<th data-options="field:'createDate',width:70">创建日期</th>
					<th data-options="field:'deadline',width:70">结算期限</th>
					<th data-options="field:'payAmount',width:90,align:'right',formatter:window.apmgt.noentery.amountFormatter">实付金额</th>
					<th data-options="field:'status',width:60,formatter:window.apmgt.noentery.statusFormatter">状态</th>
					<th data-options="field:'tradeDate',width:90">付款日期</th>
					<th data-options="field:'paymentMethod',width:90">付款方式</th>
					<th data-options="field:'supplierName',width:90">付款人</th>
					<th data-options="field:'remark',width:200">备注</th>
					<th data-options="field:'noticeStatus',width:200,hidden:true">通知单状态</th>
					<th data-options="field:'productStatus',width:200,hidden:true">产品状态</th>
					<th data-options="field:'currencyType',width:100,hidden:true">货币</th>
				</tr>
			</thead>
		</table>
	</div>
<!-- 	<div data-options="region:'south'" style="height:70px;" id="sum_footer"></div> -->
</body>
</html>
