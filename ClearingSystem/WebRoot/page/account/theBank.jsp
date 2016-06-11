<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>银行入账发布</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<script type="text/javascript" src="${basePath}files/js/page.account.theBank.js"></script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="bicon-asterisk" plain="true" id="theBankNew">发布银行到账信息</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-edit" plain="true" id="theBankEdit">编辑</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-remove" plain="true" id="theBankNO">删除</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-search" plain="true" id="theBank_search_gaoji">高级查询</a>
		    <a href="#" class="easyui-linkbutton" iconCls="bicon-refresh" plain="true">刷新</a>
		</div>
		<div>
			状态: <select>
				<option selected="selected">全部</option>
				<option>未认领</option>
				<option>已认领</option>
			</select> 凭证号: <input type="text" style="width:100px">
			到账日期: <input class="easyui-datebox" style="width:100px"> 至: <input class="easyui-datebox" style="width:100px"> <a href="#" class="easyui-linkbutton" iconCls="bicon-search">Search</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="account_theBank_dg" class="easyui-datagrid" data-options="fit:true,
						  pagination:true,
						  pagePosition:'both',
						  rownumbers:true,
						  showFooter:true,
						  method:'get',
						  border:false,
						  url:'/cs/json/theBank.json',
						  onSelect:window.account.theBank.onSelect,
						  onUnselect:window.account.theBank.onUnselect,">
			<thead>
				<tr>
					<th data-options="field:'toaccountId',width:100,checkbox : true"></th>
					<th data-options="field:'toaccountDate',width:100">到账日期</th>
					<th data-options="field:'fuKuanfang',width:200">付款方</th>
					<th data-options="field:'toaccountMoney',width:100">到账金额</th>
					<th data-options="field:'documentNumber',width:100">凭证号</th>
					<th data-options="field:'recordedAccount',width:200">入账账户</th>
					<th data-options="field:'state',width:100,formatter:window.account.theBank.status,styler:window.account.theBank.accountStatusStyler">状态</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>
