<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>查询银行入账发布</title>
<meta charset="UTF-8">
</head>
<body>
<!-- External JS -->
    <div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;">
			<a id="commission_search"  href="#" class="easyui-linkbutton"><i class="fa fa-save"></i>&nbsp;查询</a>
			<a id="commission_close" href="#" class="easyui-linkbutton"><i class="fa fa-times"></i>&nbsp;关闭</a> 
		</div>
		<div data-options="region:'center',border:false" style="padding:9px;overflow: hidden;">
			<form id="account_serchcommission_form" method="post">
				<table>
					<tr>
						<th align="left">产品航线</th>
						<td colspan="2">
							<input name="productRoute" style="width: 150px" class="easyui-combobox" data-options="panelHeight:'auto',valueField:'productRoute',textField:'productRoute',url:'/cs/account/commissionAction!findProductRoute.action'"/>
						</td>
					</tr>
					<tr>
						<th align="left">供应商</th>
						<td colspan="2">
							<input name="supplierNo" style="width: 150px" class="easyui-combobox" data-options="panelHeight:'auto',valueField:'supplierNo',textField:'supplierNo',url:'/cs/account/commissionAction!findsupplierNo.action'"/>
						</td>
					</tr>
					<tr>
						<th align="left">承运商</th>
						<td colspan="2">
							<input name="carrier" style="width: 150px" class="easyui-combobox" data-options="panelHeight:'auto',valueField:'carrier',textField:'carrier',url:'/cs/account/commissionAction!findcarrier.action'"/>
						</td>
					</tr>
					<tr>
						<th align="left">交易日期</th>
						<td colspan="2"> <input class="easyui-datebox" id="commission_date_qi" name="qiDate" style="width:100px"> 至: <input class="easyui-datebox" id="commission_date_shi" name="shiDate" style="width:100px"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
