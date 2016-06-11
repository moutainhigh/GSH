<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>数据范围</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<script src="${basePath}files/js/page.bset.dataPermission.js"></script>

<script>
// 	var testData = {
// 		total : 10,
// 		rows : [ {
// 			"name" : "顾晓青",
// 			"role" : "供应商结算",
// 			"userName" : "xiaoqin.gu@gshts.com",
// 			"permission" : "浦东机票业务部,浦东机票票务部",
// 			"remark" : "暂无"
// 		}, {
// 			"name" : "仇维娜",
// 			"role" : "客户结算",
// 			"userName" : "weina.qiu@gshts.com",
// 			"permission" : "浦西机票业务部,浦西机票票务部",
// 			"remark" : "暂无"
// 		}, {
// 			"name" : "唐彩虹",
// 			"role" : "财务总管",
// 			"userName" : "caihong.tang@gshts.com",
// 			"permission" : "总公司",
// 			"remark" : "暂无"
// 		} ]
// 	};
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="bicon-asterisk" plain="true" id="btnAdd">新建</a> <a href="#" class="easyui-linkbutton" iconCls="bicon-edit" plain="true" id="btnEdit">编辑</a> <a href="#" class="easyui-linkbutton" iconCls="bicon-remove" plain="true" id="btnRemove">删除</a> <a href="#" class="easyui-linkbutton" iconCls="bicon-search" plain="true" id="btnQuery">高级查询</a>
		</div>
		<div>
			Date From: <input class="easyui-datebox" style="width:100px"> To: <input class="easyui-datebox" style="width:100px"> Language: <select class="easyui-combobox" panelHeight="auto" style="width:100px">
				<option value="java">Java</option>
				<option value="c">C</option>
				<option value="basic">Basic</option>
				<option value="perl">Perl</option>
				<option value="python">Python</option>
			</select> <a href="#" class="easyui-linkbutton" iconCls="bicon-search" id="btnSearch">Search</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="dtgd" class="easyui-datagrid" data-options="fit:true,
						  url:'/cs/bset/dataPermissionAction!findAll.action',
						  pagination:true,
						  pagePosition:'both',
						  rownumbers:true,
						  singleSelect:true,
						  method:'get',
						  border:false">
			<thead>
				<tr>
<!-- 					<th data-options="field:'name',width:120">员工姓名</th> -->
					<th data-options="field:'uname',width:120">用户</th>
					<th data-options="field:'role',width:300">角色名称</th>
					<th data-options="field:'userName',width:150">系统用户</th>
					<th data-options="field:'permission',width:300">数据范围</th>
					<th data-options="field:'remark',width:230">描述</th>
				</tr>
			</thead>
		</table>
	</div>

</body>
</html>
