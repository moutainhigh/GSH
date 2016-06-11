<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>客户明细账目</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<script type="text/javascript">
	var currency_local = "${CURRENCY_UNIT}";
</script>
<script src="${basePath}files/js/page.armgt.CompanyDetailAccount.js"></script>
<style>
.search-east {
	border-left: solid 1px #ccc;
}
</style>
<script type="text/javascript">
	$(function(){
		$("#customerNo").combogrid().next('span').find('input').keyup(function() {
			var newValue=$("#customerNo").combogrid('getText');
			var result = eval("(" + $.ajax({
				url : "/cs/bset/CollectpayaccountAction!findAllListJiansuo.action?accountSource=1",
				dataType : "json/xml/html",
				data : {
					"code" : newValue
				},
				async : false,
				cache : false,
				type : "post"
			}).responseText + ")");
			var g = $("#customerNo").combogrid("grid"); // 获取数据表格对象
			g.datagrid("loadData", result.obj);
			$("#customerNo").combogrid("setValue",newValue);
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
				<form id="companyDetail_from">
					<table>
						<tr>
							<th>付款日期</th>
							<td><input class="easyui-datebox" id="tradeDateQi" name="tradeDateQi" style="width:100px"> To: <input class="easyui-datebox" style="width:100px" id="tradeDateShi" name="tradeDateShi"></td>
						</tr>
						<tr>
							<th>手续费</th>
							<td><input type="radio" name="counterFee" value="1"/>包含 <input type="radio" name="counterFee" value="2"/>不包含</td>
						</tr>
						<tr>
							<th>客户编号</th>
							<td><input name="customerNo" id="customerNo" class="easyui-combogrid" data-options="panelWidth:190,idField:'customerNo',textField:'customerName',url:'/cs/armgt/receivableViewAction!findAllCustomerNo.action',
							columns:[[    
				                {field:'customerNo',title:'客户编号',width:70},    
				                {field:'customerName',title:'客户名称',width:100}  
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
							<th>OP</th>
							<td><input name="affiliationNo" id="affiliationNo" class="easyui-combobox" data-options="panelHeight:'auto',valueField:'affiliationNo',textField:'affiliationPerson',value:'',url:'/cs/armgt/receivableViewAction!findAllList.action'" /></td>
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
							<td></td>
							<td></td>
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
					<a href="#" class="easyui-linkbutton" iconCls="bicon-leaf" plain="true" id="btnLeaf">出具收据</a>
				</div>
			</div>
			<div data-options="region:'center',border:false">
				<table id="dtgd" class="easyui-datagrid" data-options="fit:true,
						  pagination:true,
						  pagePosition:'both',
						  rownumbers:true,
						  method:'get',
						  showFooter: true,
<!-- 						  url:'/cs/armgt/receivableViewAction!findALL.action', -->
						  border:false,
						  onClickRow:window.armgt.cdac.onClickRow">
					<thead>
						<tr>
							<th data-options="field:'id',checkbox:true"></th>
							<th data-options="field:'settlementNo',width:90">结算单号</th>
							<th data-options="field:'noticeNo',width:90">通知单号</th>
							<th data-options="field:'customerNo',width:100">客户编号</th>
							<th data-options="field:'tradeDate',width:70">交易日期</th>
							<th data-options="field:'orderNo',width:90">订单号</th>
							<th data-options="field:'paymentMethod',width:70,formatter:window.armgt.cdac.tradeWayFormatter">交易方式</th>
							<th data-options="field:'incomeAmount',width:90,align:'right',formatter:window.armgt.cdac.amountFormatter">已收金额</th>
							<th data-options="field:'voucherNo',width:80">凭证号</th>
							<th data-options="field:'affiliationPerson',width:60">归属人</th>
							<th data-options="field:'handPerson',width:100">经手人</th>
							<th data-options="field:'remark',width:200">备注</th>
							<th data-options="field:'deadline',hidden:true">结算期限</th>
							<th data-options="field:'status',width:70,sortable:true,formatter:window.armgt.cdac.status,styler:window.armgt.cdac.statusStyler">状态</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
	
	<div id="openAccountToreviewPanel"> 
 	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;">
			<a id="openAccountToreviewPanel_shengcheng"  href="#" class="easyui-linkbutton"><i class="fa fa-save"></i>&nbsp;生成</a>
			<a id="openAccountToreviewPanel_remoud" href="#" class="easyui-linkbutton"><i class="fa fa-times"></i>&nbsp;放弃</a> 
		</div>  
 	<div data-options="region:'center',border:false" style="padding:9px;overflow: hidden;">
       <form id="account_openAccountToreviewMingxi_form" method="post">
			<table>
				<tr>
					<th style="width: 55px" align="left">包含明细</th>
					<td>是<input type="radio" name="containsDetails" value="1" id="bsp_ticketbook_newtb_form_containsDetails1" checked="checked">
					否<input type="radio" name="containsDetails" value="0" id="bsp_ticketbook_newtb_form_containsDetails2"></td>
				</tr>
				<tr style="padding-top: 30px"></tr>
				<tr>
					<th align="left">付款人</th>
					<td colspan="2"><input style="width:150px;" class="easyui-validatebox" name="Drawee" id="bsp_ticketbook_newtb_form_drawee" data-options="required:true" type="text" /></td>
				</tr>
				<tr style="padding-top: 30px"></tr>
				<tr>
					<th align="left">收件人</th>
					<td colspan="2"><input style="width:150px;" class="easyui-validatebox" data-options="required:true" name="addressee" id="account_accountToreview_form_addressee"/></td>
				</tr>
				<tr style="padding-top: 30px" ></tr>
				<tr>
					<th align="left">付款日期</th>
					<td colspan="2"><input style="width:150px;" id="account_accountToreview_form_datePayment" name="datePayment" type="text" /></td>
				</tr>
				<tr style="padding-top: 30px" ></tr>
				<tr>
					<th align="left">备注</th>
					<td colspan="2"><textarea cols="18" rows="2" name="remarks" id="remarks"></textarea></td>
				</tr>
			</table>
		</form>
		</div>
	</div>
</div> 

</body>
</html>
