<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/cs" prefix="cs"%>
<!DOCTYPE HTML>
<html>
<head>
<title>信用卡手续费</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<c:set var="XYKSXFXJ">
	<cs:validMethod code="XYKSXFXJ" />
</c:set>
<c:set var="XYKSXFBJ">
	<cs:validMethod code="XYKSXFBJ" />
</c:set>
<c:set var="XYKSXFSC">
	<cs:validMethod code="XYKSXFSC" />
</c:set>
<c:set var="XYKSXFSQJS">
	<cs:validMethod code="XYKSXFSQJS" />
</c:set>
<c:set var="XYKSXFJS">
	<cs:validMethod code="XYKSXFJS" />
</c:set>
<script type="text/javascript">
	var XYKSXFXJ=${XYKSXFXJ};
	var XYKSXFBJ=${XYKSXFBJ};
	var XYKSXFSC=${XYKSXFSC};
	var XYKSXFSQJS=${XYKSXFSQJS};
	var XYKSXFJS=${XYKSXFJS};
</script>

<script src="${basePath}files/js/page.bset.creditCardFee.js"></script>

</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="bicon-asterisk" plain="true" id="btnAdd">新建</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-edit" plain="true" id="btnEdit">编辑</a> 
			<a href="#" class="easyui-linkbutton" iconCls="bicon-remove" plain="true" id="btnRemove">删除</a> 
			<a href="#" class="easyui-linkbutton" iconCls="fa fa-lock" plain="true" id="btnshenqingjiesuo">申请解锁</a> 
			<a href="#" class="easyui-linkbutton" iconCls="fa fa-lock" plain="true" id="btnjiesuo">解锁</a> 
		</div>
		<div>
		<form id="creditCardFee_form">
			信用卡代码：<input id="creditCode_zhu" name="creditCode" class="easyui-combogrid" data-options="editable:false,panelWidth:290,idField:'id',textField:'text',url:'/cs/files/baseJson/CreditCardCode.json',    
				            columns:[[    
				                {field:'id',title:'信用卡代码',width:70},    
				                {field:'code',title:'英文代码名称',width:100}, 
				                {field:'text',title:'中文代码名称',width:100}  
				            ]] "/>
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
						  url:'/cs/bset/creditcardfeeServiceAction!findAll.action',
						  onSelect:window.bset.creditCardFee.onSelect">
			<thead>
				<tr>
					<th data-options="field:'options',width:60,align:'center',formatter:window.bset.creditCardFee.optionsFormater">操作</th>
					<th data-options="field:'creditCode',width:150,sortable:true">信用卡代码</th>
					<th data-options="field:'creditDesc',width:200,sortable:true">描述</th>
					<th data-options="field:'publication',width:100,sortable:true,align:'right'">公布手续费（%）</th>
					<th data-options="field:'settlement',width:100,sortable:true,align:'right'">结算手续费（%）</th>
					<th data-options="field:'establishDate',width:130,sortable:true">创建日期</th>
					<th data-options="field:'expirationdate',width:130,sortable:true">失效日期</th>
					<th data-options="field:'creditcardfeeLock',width:80,sortable:true,align:'center',formatter:window.bset.creditCardFee.LockFormater,styler:window.bset.creditCardFee.LockStyler">是否锁定</th>
					<th data-options="field:'userId',width:60,sortable:true,align:'center',hidden:true">用户ID</th>
					<th data-options="field:'adminId',width:60,sortable:true,align:'center',hidden:true">管理员</th>
					<th data-options="field:'deptId',width:60,sortable:true,align:'center',hidden:true">所属部门</th>
				</tr>
			</thead>
		</table>
	</div>

</body>
</html>
