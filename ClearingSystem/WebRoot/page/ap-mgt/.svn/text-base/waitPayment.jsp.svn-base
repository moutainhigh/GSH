<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>待支付账款</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- CSS -->


<!-- JS -->
<script>
	var btn_add_enable = "enable";
	var btn_revocation_enable = "enable";
	var btn_apply_enable = "enable";
	var btn_writeoff_enable = "enable";
	var btn_view_enable = "enable";
	var btn_yn_enable = "enable";
	var btn_query_enable = "enable";
// 	"${APPLY_POWWER}" == "true"
	var apply_power = "true";// 申请开关
	var uid = "${userSession.uid}";
	var ticket = "${userSession.ticket}";
	
	$(function(){
		$("#supplierNo1").combogrid().next('span').find('input').keyup(function() {
			var newValue=$("#supplierNo1").combogrid('getText');
			var result = eval("(" + $.ajax({
				url : "/cs/apmgt/wpAction!findAllSupplierNoJianSuo.action",
				dataType : "json/xml/html",
				data : {
					"code" : newValue
				},
				async : false,
				cache : false,
				type : "post"
			}).responseText + ")");
			var g = $("#supplierNo1").combogrid("grid"); // 获取数据表格对象
			g.datagrid("loadData", result.obj);
			$("#supplierNo1").combogrid("setValue",newValue);
 		});
	});	
	
	
</script>
<script src="${basePath}files/js/page.apmgt.waitPayment.js"></script>

</head>
<body class="easyui-layout">

<div data-options="region:'east',border:false,title:'查询设置',headerCls:'search-east',bodyCls:'search-east'" style="width:350px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'south',border:false" style="height:40px;background: #f5f5f5;border-top: solid 1px #ccc;padding: 5px;">
				<a href="#" class="easyui-linkbutton" iconCls="bicon-search" id="btnSearch">Search</a>
			</div>
			<div data-options="region:'center',border:false" style="padding:5px;background: #f5f5f5;overflow-y:scroll;">
				<form id="waitPayment_form" method="post">
				<table>
					<tr>
							<th>付款日期</th>
							<td><input class="easyui-datebox" id="tradeDateQi" name="tradeDateQi" style="width:100px"> To: <input class="easyui-datebox" style="width:100px" id="tradeDateShi" name="tradeDateShi"></td>
						</tr>
						<tr>
							<th>供应商编号</th>
							<td><input name="supplierNo" id="supplierNo1" class="easyui-combogrid" data-options="panelWidth:190,idField:'accountCode',textField:'accountName',editable:false,url:'/cs/bset/CollectpayaccountAction!findList.action?accountSource=2',
 						columns:[[     
 				                {field:'accountCode',title:'客户编号',width:70},     
				                {field:'accountName',title:'客户名称',width:100}   
 				            ]]" /></td>
						</tr>
						<tr>
							<th>交换单号</th>
							<td><input name="exchangeNo" id="exchangeNo" /></td>
						</tr>
						<tr>
							<th>订单号</th>
							<td><input name="orderNo" id="orderNo"/></td>
						</tr>
						<tr>
							<th>OP</th>
							<td><input name="affiliationNo" id="affiliationNo1" class="easyui-combobox" data-options="panelHeight:'auto',valueField:'affiliationNo',textField:'affiliationPerson',value:'',url:'/cs/apmgt/wpAction!findAllList.action'" /></td>
						</tr>
						<tr>
							<th>结算期限</th>
							<td><input class="easyui-datebox" style="width:100px" name="deadlineShi" id="deadlineShi"></td>
						</tr>
						<tr>
							<th>产品号</th>
							<td><input name="productNo" id="productNo"/></td>
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
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'bicon-thumbs-up',plain:true" id="btn_apply">付款申请</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-tag" plain="true" id="btn_writeoff">销账</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-folder-open" plain="true" id="btn_view">查看单据</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-ok-circle" plain="true" id="btn_y">同意</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-ban-circle" plain="true" id="btn_n">不同意</a>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="dtgd" class="easyui-datagrid" data-options="fit:true,
						  pagination:true,
						  pagePosition:'both',
						  rownumbers:true,
						  singleSelect:false,
						  method:'get',
						  border:false,
						  idField:'id',
 						  showFooter: true,
<!-- 						  url:'/cs/apmgt/wpAction!datagrid.action', -->
						  onSelect:window.apmgt.waitPayment.onSelect,
						  onUnselect:window.apmgt.waitPayment.onUnselect,
						  onLoadSuccess:window.apmgt.waitPayment.onLoadSuccess,
						  rowStyler : window.apmgt.waitPayment.StatusStyler">
			<thead>
				<tr>
					<th data-options="field:'options',width:65,sortable:true,align:'center',formatter:window.apmgt.waitPayment.optionsFormatter">操作</th>
					<th data-options="field:'exchangeNo',width:90,sortable:true">交换单号</th>
					<th data-options="field:'supplierNo',width:100,sortable:true,formatter:window.apmgt.waitPayment.amountFormatter2">供应商编号</th>
					<th data-options="field:'tradeDate',width:70,sortable:true,formatter:window.apmgt.waitPayment.amountFormatter2">交易日期</th>
					<th data-options="field:'productOrderNo',width:90,sortable:true,formatter:window.apmgt.waitPayment.amountFormatter2">产品单号</th>
					<th data-options="field:'deadline',width:70,sortable:true">结算期限</th>
					<th data-options="field:'payBeAmount',width:90,sortable:true,align:'right',formatter:window.apmgt.waitPayment.amountFormatter,styler:window.apmgt.waitPayment.incomeStyler">应付金额</th>
					<th data-options="field:'payAmount',width:90,sortable:true,align:'right',formatter:window.apmgt.waitPayment.amountFormatter,styler:window.apmgt.waitPayment.incomeStyler">已付金额</th>
					<th data-options="field:'balance',width:90,sortable:true,align:'right',formatter:window.apmgt.waitPayment.amountFormatter,styler:window.apmgt.waitPayment.incomeStyler">余额</th>
					<th data-options="field:'productNo',width:120,sortable:true">产品号</th>
					<th data-options="field:'orderNo',width:120,sortable:true">订单号</th>
					<th data-options="field:'type',width:80,sortable:true,formatter:window.apmgt.waitPayment.typeFormatter">类型</th>
					<th data-options="field:'affiliationPerson',width:70,sortable:true">归属</th>
					<th data-options="field:'remark',width:200,sortable:true">备注</th>
					<th data-options="field:'supplierName',width:100,sortable:true,hidden:true">供应商名称</th>
					<th data-options="field:'currencyType',width:100,sortable:true,hidden:true">货币</th>
					<th data-options="field:'abnormalStatus',width:200,hidden:true">付款通知单异常状态</th>
				</tr>
			</thead>
		</table>
	</div>

<!-- 	<div data-options="region:'south'" style="height:70px;" id="sum_footer"></div> -->
</div>
</div>
</body>
</html>
