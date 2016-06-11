<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>未销账账款</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<script>
	var btn_add_enable = "enable";
	var btn_revocation_enable = "enable";
	var btn_change_deadline_enable = "enable";
	var btn_apply_enable = "enable";
	var btn_writeoff_enable = "enable";
	var btn_view_enable = "enable";
	var btn_notice_enable = "enable";
	var btn_query_enable = "enable";
	var btn_yn_enable="enable";

	var apply_power = "true";// 申请开关
	var currency_local = "${CURRENCY_UNIT}";
	var uid = "${userSession.uid}";
	var ticket = "${userSession.ticket}";
	
	$(function(){
 		$("#customerNo").combogrid().next('span').find('input').keyup(function() {
			var newValue=$("#customerNo").combogrid('getText');
			var result = eval("(" + $.ajax({
				url : "/cs/armgt/receivableViewAction!findAllCustomerNoJiansuo.action",
				dataType : "json/xml/html",
				data : {
					"code" : newValue
				},
				async : false,
				cache : false,
				type : "post"
			}).responseText + ")");
			var g = $("#customerNo").combogrid('grid'); // 获取数据表格对象
			g.datagrid('loadData', result.obj);
			$("#customerNo").combogrid('setValue',newValue);
 		});
	});	
</script>
<script src="${basePath}files/js/page.armgt.receivable.js"></script>

</head>
<body class="easyui-layout">

<div data-options="region:'east',border:false,title:'查询设置',headerCls:'search-east',bodyCls:'search-east'" style="width:350px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'south',border:false" style="height:40px;background: #f5f5f5;border-top: solid 1px #ccc;padding: 5px;">
				<a href="#" class="easyui-linkbutton" iconCls="bicon-search" id="btnSearch">Search</a>
			</div>
			<div data-options="region:'center',border:false" style="padding:5px;background: #f5f5f5;overflow-y:scroll;">
				<form id="receivable_from" method="post">
				<table>
						<tr>
							<th>交易日期</th>
							<td><input class="easyui-datebox" id="tradeDateQi" name="tradeDateQi" style="width:100px"> To: <input class="easyui-datebox" style="width:100px" id="tradeDateShi" name="tradeDateShi"></td>
						</tr>
						<tr>
							<th>客户编号</th>
							<td><input name="customerNo" id="customerNo" class="easyui-combogrid" data-options="panelWidth:190,idField:'accountCode',textField:'accountName',editable:false,url:'/cs/bset/CollectpayaccountAction!findList.action?accountSource=1',
							columns:[[    
				                {field:'accountCode',title:'客户编号',width:70},    
				                {field:'accountName',title:'客户名称',width:100}  
				            ]]" /></td>
						</tr>
						<tr>
							<th>通知单号</th>
							<td><input name="noticeNo" id="noticeNo"/></td>
						</tr>
						<tr>
							<th>订单号</th>
							<td><input name="orderNo" id="orderNo" /></td>
						</tr>
						<tr>
							<th>归属人</th>
							<td><input name="affiliationNo" id="affiliationNo2" class="easyui-combobox" data-options="panelHeight:'auto',valueField:'affiliationNo',textField:'affiliationPerson',value:'',url:'/cs/armgt/receivableViewAction!findAllList.action'" /></td>
						</tr>
						<tr>
							<th>结算期限</th>
							<td><input class="easyui-datebox" style="width:100px" name="deadlineShi" id="deadlineShi"></td>
						</tr>
						<tr>
							<th>团号</th>
							<td><input name="groupNumber" id="groupNumber"/></td>
						</tr>
				</table>
			</form>
			</div>
		</div>
	</div>


<div data-options="region:'center',border:false">
	<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'north',border:false" style="height:30px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="bicon-asterisk" plain="true" id="btn_add">新建</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-remove" plain="true" id="btn_revocation">撤销</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-edit" plain="true" id="btn_change_deadline">更改期限</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-tag" plain="true" id="btn_writeoff">销账</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-thumbs-up" plain="true" id="btn_apply">退款申请</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-folder-open" plain="true" id="btn_view">查看单据</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-file" plain="true" id="btn_notice">创建通知书</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-ok-circle" plain="true" id="btn_y">同意</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-ban-circle" plain="true" id="btn_n">不同意</a>
		</div>
	</div>
	<div data-options="region:'center',collapsible:true,border:false">
		<table id="dtgd" class="easyui-datagrid" data-options="fit:true,
 						  pagination:true, 
 						  pagePosition:'both', 
						  rownumbers:true,
						  singleSelect:false,
						  method:'get',
						  border:false,
						  idField:'id',
 						  showFooter: true, 
<!--  						  url:'/cs/armgt/receivable!datagrid.action', -->
						  onSelect:window.armgt.receivable.onSelect,
						  onUnselect:window.armgt.receivable.onUnselect,
						  onLoadSuccess:window.armgt.receivable.onLoadSuccess,
						  rowStyler:window.armgt.receivable.stylerdatagrid">
			<thead>
				<tr>
					<th data-options="field:'options',width:120,align:'center',formatter:window.armgt.receivable.optionsFormatter">操作</th>
					<th data-options="field:'noticeNo',width:90,sortable:true">通知单号</th>
					<th data-options="field:'customerNo',width:100,sortable:true,formatter:window.armgt.receivable.amountFormatter2">客户编号</th>
					<th data-options="field:'tradeDate',width:70,sortable:true,formatter:window.armgt.receivable.amountFormatter2">交易日期</th>
					<th data-options="field:'orderNo',width:90,sortable:true,formatter:window.armgt.receivable.amountFormatter2">订单号</th>
					<th data-options="field:'deadline',width:70,sortable:true,align:'right',styler:window.armgt.receivable.deadlineStyler">结算期限</th>
					<th data-options="field:'incomeBeAmount',sortable:true,width:90,align:'right',formatter:window.armgt.receivable.amountFormatter,styler:window.armgt.receivable.incomeStyler">应收金额</th>
					<th data-options="field:'incomeAmount',sortable:true,width:90,align:'right',formatter:window.armgt.receivable.amountFormatter,styler:window.armgt.receivable.incomeStyler">已收金额</th>
					<th data-options="field:'balance',width:90,sortable:true,align:'right',formatter:window.armgt.receivable.amountFormatter,styler:window.armgt.receivable.incomeStyler">余额</th>
					<th data-options="field:'type',width:60,sortable:true,formatter:window.armgt.receivable.typeFormatter">类型</th>
					<th data-options="field:'affiliationPerson',sortable:true,width:60">归属</th>
					<th data-options="field:'remark',width:200">备注</th>
					<th data-options="field:'abnormalStatus',width:200,hidden:true">付款通知单异常状态</th>
				</tr>
			</thead>
		</table>
	</div>
</div>
</div>
</body>
</html>
