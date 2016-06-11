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
<script type="text/javascript" src="${basePath}files/js/page.bsp.ticketbook.js"></script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="bicon-plus" plain="true" id="newticketbook">新增</a> 
		    <a href="#" class="easyui-linkbutton" iconCls="bicon-refresh" plain="true">刷新</a>
		</div>
		<div>
			<form id="bspform">
				类型: <select class="easyui-combobox" name="bspType" panelHeight="auto" style="width:100px">
					<option value="2">国内</option>
					<option value="1">国际</option>
				</select> <a href="#" class="easyui-linkbutton" iconCls="bicon-search" id="btSearch">Search</a>
			</form>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="bsp_ticketbook_dg" class="easyui-datagrid" data-options="fit:true,
						  pagination:true,
						  pagePosition:'both',
						  rownumbers:true,
						  singleSelect:true,
						  method:'get',
						  border:false,
						  url:'/cs/bsp/bspTicketAction!findAll.action'">
			<thead>
				<tr>
					<th data-options="checkbox:true"></th>
					<th data-options="field:'bspStartBank',width:160">起始票号</th>
					<th data-options="field:'bspTerminationBank',width:160">终止票号</th>
					<th data-options="field:'bspNumber',width:120">数量</th>
					<th data-options="field:'bspType',width:100,formatter:window.bsp.ticketbook.accountTypeFormater">类型</th>
					<th data-options="field:'bspCreationDate',width:160">创建日期</th>
					<th data-options="field:'bspAllowance',width:100">余量</th>
					<th data-options="field:'userId',width:160,hidden:true">用户ID</th>
					<th data-options="field:'deptId',width:100,hidden:true">部门ID</th>
				</tr>
			</thead>
		</table>
	</div>

</body>
</html>
