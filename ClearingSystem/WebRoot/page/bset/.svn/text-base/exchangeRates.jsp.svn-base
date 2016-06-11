<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/cs" prefix="cs"%>
<!DOCTYPE HTML>
<html>
<head>
<title>结算汇率</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->

<script src="${basePath}files/js/currency.json.js"></script>

<c:set var="JSHLXJ">
	<cs:validMethod code="JSHLXJ" />
</c:set>
<c:set var="JSHLBJ">
	<cs:validMethod code="JSHLBJ" />
</c:set>
<c:set var="JSHLSC">
	<cs:validMethod code="JSHLSC" />
</c:set>
<c:set var="JSHLSQJS">
	<cs:validMethod code="JSHLSQJS" />
</c:set>
<c:set var="JSHLJS">
	<cs:validMethod code="JSHLJS" />
</c:set>
<script type="text/javascript">
	var JSHLXJ=${JSHLXJ};
	var JSHLBJ=${JSHLBJ};
	var JSHLSC=${JSHLSC};
	var JSHLSQJS=${JSHLSQJS};
	var JSHLJS=${JSHLJS};
</script>
<script src="${basePath}files/js/page.bset.exchangeRates.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
<!-- 			<a href="#" class="easyui-linkbutton" iconCls="bicon-asterisk" plain="true" id="btnAdd">新建</a>  -->
			<a href="#" class="easyui-linkbutton" iconCls="bicon-edit" plain="true" id="btnEdit">编辑</a> 
			<a href="#" class="easyui-linkbutton" iconCls="bicon-remove" plain="true" id="btnRemove">删除</a> 
			<a href="#" class="easyui-linkbutton" iconCls="bicon-remove" plain="true" id="btnshenqingjiesuo">申请解锁</a> 
			<a href="#" class="easyui-linkbutton" iconCls="bicon-remove" plain="true" id="btnjiesuo">解锁</a> 
		</div>
		<div>
		<form id="exchangeRates_from">
			货币:<input class="easyui-combogrid" id="code_def" name="code" id="rate_code"
   				data-options="panelWidth:190,required:true,idField:'id',textField:'text',url:'/cs/aa/bb!findAll.action',   
				            columns:[[    
				                {field:'id',title:'货币代码',width:70},    
				                {field:'text',title:'货币名称',width:100}  
				            ]], onChange: window.bset.exchangeRates.onChange" />  
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
						  url:'/cs/bset/sertAction!datagrid.action',
						  onSelect:window.bset.exchangeRates.onSelect">
			<thead>
				<tr>
					<th data-options="field:'options',width:90,align:'center',formatter:window.bset.exchangeRates.opitonsFormatter">操作</th>
					<th data-options="field:'code',width:80,sortable:true">货币代码</th>
					<th data-options="field:'name',width:120,sortable:true,formatter:window.bset.exchangeRates.nameFormatter">货币名称</th>
					<th data-options="field:'buying',width:100,sortable:true">买入价</th>
					<th data-options="field:'selling',width:100,sortable:true">卖出价</th>
					<th data-options="field:'validity',width:160,sortable:true,formatter:window.bset.exchangeRates.validityFormatter">有效期</th>
					<th data-options="field:'establishDate',width:160,sortable:true">创建时间</th>
					<th data-options="field:'settlementexchangerateLock',width:80,sortable:true,align:'center',formatter:window.bset.exchangeRates.LockFormater,styler:window.bset.exchangeRates.LockStyler">是否锁定</th>
					<th data-options="field:'userId',width:60,sortable:true,align:'center',hidden:true">用户ID</th>
					<th data-options="field:'adminId',width:60,sortable:true,align:'center',hidden:true">管理员</th>
					<th data-options="field:'deptId',width:60,sortable:true,align:'center',hidden:true">所属部门</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
</html>
