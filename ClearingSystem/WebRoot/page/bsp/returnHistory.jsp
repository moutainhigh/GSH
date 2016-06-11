<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>历史退换</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<script type="text/javascript">
	var currency_local = "${CURRENCY_UNIT}";
</script>
<script type="text/javascript" src="${basePath}files/js/page.bsp.returnHistory.js"></script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="bicon-edit" plain="true" id="btnEdit">编辑</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-folder-open" plain="true" id="btnSee">查看</a>  
		    <a href="#" class="easyui-linkbutton" iconCls="bicon-refresh" plain="true">刷新</a>
		</div>
		<div>
			<form id="bspform">
				客户编号：<input type="text"  class="easyui-combobox">
				<a href="#" class="easyui-linkbutton" iconCls="bicon-search" id="btSearch">Search</a>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="bsp_SalesInformation_dg" class="easyui-datagrid" data-options="fit:true,
						  pagination:true,
						  pagePosition:'both',
						  rownumbers:true,
						  singleSelect:true,
						  method:'get',
						  border:false,
						  url:'/cs/bsp/salesInformationAction!findAll.action',
						  onSelect:window.bsp.returnHistory.onSelect">
			<thead>
				<tr>
					<th data-options="checkbox:true"></th>
					<th data-options="field:'passengerInfo',width:100">旅客</th>
					<th data-options="field:'cpCodeMainOrder',width:100">客户编号</th>
					<th data-options="field:'tickets',width:160">票号</th>
					<th data-options="field:'createDate',width:160">创建日期</th>
					<th data-options="field:'salesPrice',width:160,formatter:window.bsp.returnHistory.amountFormatter">销售价</th>
					<th data-options="field:'costPrice',width:160,formatter:window.bsp.returnHistory.amountFormatter">成本价</th>
					<th data-options="field:'suppliersCode',width:160">供应商编号</th>
					<th data-options="field:'status',width:100,formatter:window.bsp.returnHistory.statusFormater,styler:window.bsp.returnHistory.statusStyler">状态</th>
					
				</tr>
			</thead>
		</table>
	</div>

</body>
</html>
