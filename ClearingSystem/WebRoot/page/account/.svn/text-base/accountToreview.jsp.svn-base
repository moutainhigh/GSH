<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>账目复核</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<script>
	var settlement_day = "${SETTLEMENT_DAY}";
	var currency_local = "${CURRENCY_UNIT}";
</script>
<script type="text/javascript" src="${basePath}files/js/page.account.accountToreview.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="bicon-ok" plain="true" id="ToreviewOK">确认</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-repeat" plain="true" id="ToreviewNO">驳回</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-folder-open" plain="true" id="ToreviewMingxi">明细</a>
			<a href="#" class="easyui-linkbutton" iconCls="bicon-search" plain="true" id="Toreview_search_gaoji">高级查询</a>
		    <a href="#" class="easyui-linkbutton" iconCls="bicon-refresh" plain="true">刷新</a>
		</div>
		<div>
			<form  id="account_accountToreview_onclick_search_form" method="post">
				账户: <input id="accountCode_zhanghu_zhu" name="accountCode" class="easyui-combogrid" data-options="valueField: 'code',    
			        required:true,
			        idField:'accountCode',    
                    textField:'accountCode',
                    panelWidth:380,   
			        url: '/cs/bset/accountAction!findAllList.action',
			        columns:[[    
		                {field:'accountCode',title:'账户编码',width:80},    
		                {field:'accountBank',title:'开户银行',width:120},    
		                {field:'accountNumber',title:'账户号码',width:150}
		            ]] " />
				凭证号: <input type="text" style="width:100px" id="account_documentNumber" name="voucherNo">
				交易日期: <input class="easyui-datebox" style="width:100px" id="accountabc_date_qi" name="qiDate"> 至: <input class="easyui-datebox" id="accountdef_date_shi" name="shiDate" style="width:100px"> 
				<a href="" class="easyui-linkbutton" id="accountToreview_onclick_search" iconCls="bicon-search">Search</a>
			</form>
			
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="account_toreview_dg" class="easyui-datagrid" data-options="fit:true,
						  pagination:true,
						  pagePosition:'both',
						  rownumbers:true,
						  method:'get',
						  showFooter:true,
						  border:false,
						  onSelect:window.account.accountToreview.onSelect,
						  onUnselect:window.account.accountToreview.onUnselect,
						  onLoadSuccess:window.account.accountToreview.onLoadSuccess">
			<thead>
				<tr>
					<th colspan="2">账户代码</th>
					<th colspan="5" align="left"><font id="accountCode"></font></th>
					<th>日期：</th>
					<th colspan="3"><font id="tradeDate"></font></th>
				</tr>
				<tr>
					<th data-options="field:'id',width:100,checkbox : true"></th>
					<th data-options="field:'options',width:100,sortable:true,align:'center',formatter:window.account.accountToreview.optionsFormater">操作</th>
					<th data-options="field:'tradeDate',width:100,sortable:true">交易日期</th>
					<th data-options="field:'tradeObject',width:100,sortable:true">交易对象</th>
					<th data-options="field:'income',width:90,sortable:true,formatter:window.account.accountToreview.amountFormatter">期间收入</th>
					<th data-options="field:'expenditure',width:90,sortable:true,formatter:window.account.accountToreview.amountFormatter">期间支出</th>
					<th data-options="field:'balance',width:100,sortable:true,formatter:window.account.accountToreview.amountFormatter">余额</th>
					<th data-options="field:'accountCode',width:90,sortable:true">资金账户</th>
					<th data-options="field:'voucherNo',width:90,sortable:true">凭证号</th>
					<th data-options="field:'remark',width:100,sortable:true">备注</th>
					<th data-options="field:'settlementNo',width:100,sortable:true">结算编号</th>
					<th data-options="field:'type',width:100,hidden:true">数据类型</th>
					<th data-options="field:'status',width:70,sortable:true,formatter:window.account.accountToreview.status,styler:window.account.accountToreview.statusStyler">状态</th>
				</tr>
			</thead>
		</table>
	</div>
	
<!-- 	<div data-options="region:'south'" style="height:70px;" id="collectPayRecord_footer"> -->
<!-- 		<table class="sum-footer"> -->
<!-- 			<tr> -->
<!-- 				<th></th> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<th>小计：</th> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<th>总计：</th> -->
<!-- 			</tr> -->
<!-- 		</table> -->
<!-- 	</div> -->

</body>
</html>
