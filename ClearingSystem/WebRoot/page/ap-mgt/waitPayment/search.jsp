<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>查询供应商未销账账款</title>
<meta charset="UTF-8">
</head>
<body>
<!-- External JS -->
    <div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;">
			<a id="waitPayment_search"  href="#" class="easyui-linkbutton"><i class="fa fa-save"></i>&nbsp;查询</a>
			<a id="waitPayment_close" href="#" class="easyui-linkbutton"><i class="fa fa-times"></i>&nbsp;关闭</a> 
		</div>
		<div data-options="region:'center',border:false" style="padding:9px;overflow: hidden;">
			<form id="apmgt_waitPayment_search_form" method="post">
				<table>
					<tr>
							<th>付款日期</th>
							<td><input class="easyui-datebox" id="tradeDateQi" name="tradeDateQi" style="width:100px"> To: <input class="easyui-datebox" style="width:100px" id="tradeDateShi" name="tradeDateShi"></td>
						</tr>
						<tr>
							<th>供应商编号</th>
							<td><input name="supplierNo" id="supplierNo1" class="easyui-combobox" data-options="required:true,panelHeight:'auto',valueField:'supplierNo',textField:'supplierNo',url:'/cs/apmgt/wpAction!findAllSupplierNo.action'" /></td>
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
							<td><input name="affiliationNo" id="affiliationNo" class="easyui-combobox" data-options="panelHeight:'auto',valueField:'affiliationNo',textField:'affiliationPerson',url:'/cs/apmgt/wpAction!findAllList.action'" /></td>
						</tr>
						<tr>
							<th>结算期限</th>
							<td><input class="easyui-datebox" style="width:100px" name="deadlineShi" id="deadlineShi"></td>
						</tr>
						<tr>
							<th>产品号</th>
							<td><input name="productNo" id="productNo"/></td>
						</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
