<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>应收账户</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<script src="${basePath}files/js/page.bset.companyAccount.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="bicon-folder-open" plain="true" id="btnView">查看</a> <a href="#" class="easyui-linkbutton" plain="true" id="btnActive"><i class="fa fa-bolt"></i> 激活</a>
		</div>
		<div>
			<form id="companyAccount_Form">
				账户编码：<input class="easyui-validatebox" name="accountCode" style="width:100px">
				账户名称：<input class="easyui-validatebox" name="accountName" style="width:100px">
				账户类型：<input id="accountType" style="width: 100px" class="easyui-combobox" name="accountType"   
   						 data-options="editable:false,valueField:'accountType',textField:'accountType',url:'/cs/bset/CollectpayaccountAction!findAllTypeShou.action'" />  
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
						  border:false,
						  url:'/cs/bset/CollectpayaccountAction!findAll.action?accountSource=1',
						  onSelect:window.bset.companyAccount.onSelect">
			<thead>
				<tr>
					<th data-options="field:'options',width:60,align:'center',formatter:window.bset.companyAccount.optionsFormater">操作</th>
					<th data-options="field:'accountCode',width:150,sortable:true">账户编码</th>
					<th data-options="field:'accountType',width:80,sortable:true">账户类型</th>
					<th data-options="field:'accountName',width:200,sortable:true">账户名称</th>
					<th data-options="field:'accountBalance',width:120,sortable:true,align:'right',formatter:window.bset.companyAccount.accountBalanceFormater">账户余额</th>
					<th data-options="field:'accountDate',width:120,sortable:true">创建时间</th>
					<th data-options="field:'accountStatus',width:60,sortable:true,formatter:window.bset.companyAccount.accountStatusFormater,styler:window.bset.companyAccount.accountStatusStyler">账户状态</th>
				</tr>
			</thead>
		</table>
	</div>

</body>
</html>
