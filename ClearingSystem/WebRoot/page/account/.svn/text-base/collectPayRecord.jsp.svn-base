<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>应收应付记录</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<script type="text/javascript" src="${basePath}files/js/page.account.collectPayRecord.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="bicon-folder-open" plain="true" id="collectPayRecordMingxi">明细</a>
		    <a href="#" class="easyui-linkbutton" iconCls="bicon-search" plain="true" id="collectPayRecord_search_gaoji">高级查询</a>
		    <a href="#" class="easyui-linkbutton" iconCls="bicon-refresh" plain="true">刷新</a>
		</div>
		<div>
			<form id="account_collect_onclick_search_form" method="post">
				账户：<select onchange="changesearchCollectPayRecord_zhu();" id="zhcode" name="type">
								<option selected="selected" value="0">全部</option>
								<option value="1">应收账户</option>
								<option value="2">应付账户</option>
					</select>
			账户代码: <input id="collectPayRecord_zhanghu_zhu" name="tradeObject">
			交易日期: <input class="easyui-datebox" id="collect_date_qi" name="qiDate" style="width:100px"> 至: <input class="easyui-datebox" id="collect_date_shi" name="shiDate" style="width:100px"> 
			<a href="#" class="easyui-linkbutton" id="collect_onclick_search" iconCls="bicon-search">Search</a>
			</form>		
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="account_collectPayRecord_dg" class="easyui-datagrid" data-options="fit:true,
						  pagination:true,
						  pagePosition:'both',
						  rownumbers:true,
						  method:'get',
						  showFooter:true,
						  border:false,
						  singleSelect:true,
						  url:'/cs/account/accountDetailAction!findCollectPay.action',
						  onLoadSuccess:window.account.collectPayRecord.onLoadSuccess,
						  onSelect:window.account.collectPayRecord.onSelect">
			<thead>
				<tr>
					<th colspan="1">账户代码</th>
					<th colspan="5" align="left"><font id="collect_tradeObject">全部</font></th>
					<th>日期：</th>
					<th colspan="2"><font id="collect_tradeDate">0000-00-00至0000-00-00</font></th>
				</tr>
				<tr>
					<th data-options="field:'tradeDate',width:100">交易日期</th>
					<th data-options="field:'tradeObject',width:100">交易对象</th>
					<th data-options="field:'income',width:90,formatter:window.account.collectPayRecord.amountFormatter">期间收入</th>
					<th data-options="field:'expenditure',width:90,formatter:window.account.collectPayRecord.amountFormatter">期间支出</th>
					<th data-options="field:'balance',width:100,formatter:window.account.collectPayRecord.amountFormatter">余额</th>
					<th data-options="field:'accountCode',width:90,sortable:true">资金账户</th>
					<th data-options="field:'voucherNo',width:90">凭证号</th>
					<th data-options="field:'remark',width:100">备注</th>
					<th data-options="field:'status',width:100,formatter:window.account.collectPayRecord.status,styler:window.account.collectPayRecord.statusStyler">状态</th>
					<th data-options="field:'type',width:100,hidden:true">数据类型</th>
				</tr>
			</thead>
		</table>
	</div>
 	
<!--  	<div data-options="region:'south'" style="height:70px;" id="collectPayRecord_footer"> -->
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
