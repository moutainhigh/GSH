<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>付款明细</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<script src="${basePath}files/js/page.apmgt.paymentDetailed.js"></script>
<style>
.search-east {
	border-left: solid 1px #ccc;
}
</style>
<script type="text/javascript">
	$(function(){
		$("#supplierNo").combogrid().next('span').find('input').keyup(function() {
			var newValue=$("#supplierNo").combogrid('getText');
			var result = eval("(" + $.ajax({
				url : "/cs/bset/CollectpayaccountAction!findAllListJiansuo.action?accountSource=2",
				dataType : "json/xml/html",
				data : {
					"code" : newValue
				},
				async : false,
				cache : false,
				type : "post"
			}).responseText + ")");
			var g = $("#supplierNo").combogrid("grid"); // 获取数据表格对象
			g.datagrid("loadData", result.obj);
			$("#supplierNo").combogrid("setValue",newValue);
 		});
	});	
</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'east',border:false,title:'查询设置',headerCls:'search-east',bodyCls:'search-east'" style="width:350px;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'south',border:false" style="height:40px;background: #f5f5f5;border-top: solid 1px #ccc;padding: 5px;">
				<a href="#" class="easyui-linkbutton" iconCls="bicon-search" id="btnSearch">Search</a>
			</div>
			<div data-options="region:'center',border:false" style="padding:5px;background: #f5f5f5;overflow-y:scroll;">
				<form id="paymentDetaile_from">
					<table>
						<tr>
							<th>付款日期</th>
							<td><input class="easyui-datebox" style="width:100px" name="tradeDateQi" id="tradeDateQi"> To: <input class="easyui-datebox" style="width:100px" name="tradeDateShi" id="tradeDateShi"></td>
						</tr>
						<tr>
							<th>手续费</th>
							<td><input type="radio" name="counterFee" value="1"/>包含 <input type="radio" name="counterFee" value="2"/>不包含</td>
						</tr>
						<tr>
							<th>供应商编号</th>
							<td><input name="supplierNo" id="supplierNo" class="easyui-combogrid" data-options="panelWidth:190,idField:'accountCode',textField:'accountName',url:'/cs/bset/CollectpayaccountAction!findList.action?accountSource=2',
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
							<td><input name="affiliationNo" id="affiliationNo" class="easyui-combobox" data-options="panelHeight:'auto',valueField:'affiliationNo',textField:'affiliationPerson',value:'',url:'/cs/apmgt/wpAction!findAllList.action'" /></td>
						</tr>
						<tr>
							<th>结算期限</th>
							<td><input class="easyui-datebox" style="width:100px" name="deadlineShi" id="deadlineShi"></td>
						</tr>
						<tr>
							<th>凭证号</th>
							<td><input name="voucherNo" id="voucherNo"/></td>
						</tr>
						<tr>
							<th>产品号</th>
							<td><input name="productNo" id="productNo"/></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
						</tr>
					</table>
				</form>
			</div>
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
						  idField:'wpid',
						  showFooter: true,
<!-- 						  url:'/cs/apmgt/payableViewAction!findAll.action', -->
						  onLoadSuccess:window.apmgt.paymentDetailed.onLoadSuccess">
			<thead>
				<tr>
					<th data-options="field:'settlementNo',width:90">结算单号</th>
					<th data-options="field:'exchangeNo',width:90">交换单号</th>
					<th data-options="field:'supplierNo',width:100">供应商</th>
					<th data-options="field:'tradeDate',width:70">付款日期</th>
					<th data-options="field:'orderNo',width:90">订单号</th>
					<th data-options="field:'paymentMethod',width:70,formatter:window.apmgt.paymentDetailed.paymentMethod">付款方式</th>
					<th data-options="field:'payAmount',width:90,align:'right',formatter:window.apmgt.paymentDetailed.amountFormatter">支付金额</th>
					<th data-options="field:'productNo',width:120">产品号</th>
					<th data-options="field:'voucherNo',width:80">凭证号</th>
					<th data-options="field:'affiliationPerson',width:70">归属人</th>
					<th data-options="field:'handPerson',width:100">经手人</th>
					<th data-options="field:'remark',width:200">备注</th>
					<th data-options="field:'deadline',hidden:true">结算期限</th>
				</tr>
			</thead>
		</table>
	</div>
<!-- 	<div data-options="region:'south'" style="height:70px;" id="sum_footer"></div> -->
</body>
</html>
