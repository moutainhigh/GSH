<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>编辑历史退换信息</title>
<meta charset="UTF-8">
</head>
<body>
<!-- External JS -->
<script type="text/javascript">
	var btn=<%= request.getParameter("btn")%>
	var currentBtn = document.getElementById("returnHistoryRdit_save");
    if (btn == 1) {
        currentBtn.style.display = "block"; //style中的display属性
    }
    else if (btn == 2) {
        currentBtn.style.display = "none";
        $("#currencyType").attr("disabled", true);
        $("#costScny").attr("disabled", true);
        $("#costC").attr("disabled", true);
        $("#costTax").attr("disabled", true);
        $("#costTotal").attr("disabled", true);
        $("#salesFcny").attr("disabled", true);
        $("#salesNone").attr("disabled", true);
        $("#salesPrice").attr("disabled", true);
        $("#salesTax").attr("disabled", true);
        $("#salesServicePrice").attr("disabled", true);
        $("#salesTotal").attr("disabled", true);
        $("#suppliersCode").attr("disabled", true);
        $("#paymentMethodCode").attr("disabled", true);
        $("#cpNameMainOrder").attr("disabled", true);
        $("#receivablesMethodCode").attr("disabled", true);
    }
</script>
    
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;">
			<a id="returnHistoryRdit_remoud" href="#" class="easyui-linkbutton" style="width: 50px;float: right;"><i class="fa fa-times"></i>&nbsp;关闭</a> 
			<a id="returnHistoryRdit_save"  href="#" class="easyui-linkbutton" style="width: 50px;float: right;"><i class="fa fa-save"></i>&nbsp;保存</a>
			
		</div>
		<div data-options="region:'center',border:false" style="padding:9px;overflow: hidden;">
			<form id="bsp_returnHistory_form" method="post">
				<table>
					<input type="hidden" name="passengerInfo">
					<input type="hidden" name="cpCodeMainOrder">
					<input type="hidden" name="tickets">
<!-- 					<input type="hidden" name="suppliersCode"> -->
					<input type="hidden" name="suppliersName">
<!-- 					<input type="hidden" name="paymentMethodCode"> -->
					<input type="hidden" name="paymentMethodName">
<!-- 					<input type="hidden" name="receivablesMethodCode"> -->
					<input type="hidden" name="receivablesMethodName">
					<input type="hidden" name="createDate">
					<input type="hidden" name="linkman">
					<input type="hidden" name="linkmanPhone">
					<input type="hidden" name="proType">
					<input type="hidden" name="tgType">
					<input type="hidden" name="oldLineInfo">
					<input type="hidden" name="codeProdouctOrder">
<!-- 					<input type="hidden" name="currencyType"> -->
					<tr>
						<th colspan="2">应付</th>
						<th>SCNY</th>
						<th>公布C</th>
						<th>结算价</th>
						<th>结算税</th>
						<th>结算总价</th>
					</tr>
					<tr>
						<td colspan="2"></td>
						<td colspan="5"><hr style="margin:0px;height:1px;border:0px;background-color:#D5D5D5;color:#D5D5D5;"/></td>
					</tr>
					<tr>
						<td colspan="2"><input style="width:100px;" class="easyui-combobox" name="currencyType" id="currencyType" data-options="value:'CNY',required:true,valueField:'code',textField:'name',url:'/cs/bset/sertAction!findList.action'" type="text" /></td>
						<td><input style="width:70px;" class="easyui-numberbox" name="costScny" id="costScny" data-options="required:true,onChange:window.bsp.returnHistory.edit.costScny" precision="2" type="text" /></td>
						<td><input style="width:70px;" class="easyui-numberbox" name="costC" id="costC" data-options="required:true,onChange:window.bsp.returnHistory.edit.costC" precision="2" type="text" /></td>
						<td><input style="width:70px;" class="easyui-numberbox" name="costPrice" id="costPrice" data-options="required:true,onChange:window.bsp.returnHistory.edit.costPrice" precision="2" type="text" /></td>
						<td><input style="width:70px;" class="easyui-numberbox" name="costTax" id="costTax" data-options="required:true,onChange:window.bsp.returnHistory.edit.costTax" precision="2" type="text" /></td>
						<td><input style="width:70px;" class="easyui-numberbox" name="costTotal" id="costTotal" data-options="required:true" precision="2" type="text" /></td>
					</tr>
					<tr>
						<td colspan="7"><hr style="margin:0px;height:1px;border:0px;background-color:#D5D5D5;color:#D5D5D5;"/></td>
					</tr>
					<tr>
						<th>应收</th>
						<th>FCNY</th>
						<th>让利</th>
						<th>销售价</th>
						<th>销售税</th>
						<th>服务费</th>
						<th>销售总价</th>
					</tr>
					<tr>
						<td></td>
						<td colspan="6"><hr style="margin:0px;height:1px;border:0px;background-color:#D5D5D5;color:#D5D5D5;"/></td>
					</tr>
					<tr>
						<td></td>
						<td><input style="width:70px;" class="easyui-numberbox" name="salesFcny" id="salesFcny" data-options="required:true,onChange:window.bsp.returnHistory.edit.salesFcny" precision="2" type="text" /></td>
						<td><input style="width:70px;" class="easyui-numberbox" name="salesNone" id="salesNone" data-options="required:true,onChange:window.bsp.returnHistory.edit.salesNone" precision="2" type="text" /></td>
						<td><input style="width:70px;" class="easyui-numberbox" name="salesPrice" id="salesPrice" data-options="required:true,onChange:window.bsp.returnHistory.edit.salesPrice" precision="2" type="text" /></td>
						<td><input style="width:70px;" class="easyui-numberbox" name="salesTax" id="salesTax" data-options="required:true,onChange:window.bsp.returnHistory.edit.salesTax" precision="2" type="text" /></td>
						<td><input style="width:70px;" class="easyui-numberbox" name="salesServicePrice" id="salesServicePrice" data-options="required:true,onChange:window.bsp.returnHistory.edit.salesServicePrice" precision="2" type="text" /></td>
						<td><input style="width:70px;" class="easyui-numberbox" name="salesTotal" id="salesTotal" data-options="required:true" precision="2" type="text" /></td>
					</tr>
					<tr>
						<td colspan="7"><hr style="margin:0px;height:1px;border:0px;background-color:#D5D5D5;color:#D5D5D5;"/></td>
					</tr>
					<tr>
						<th><p style="width: 70px">供应商信息</p></th>
						<td colspan="2"><input name="suppliersCode" style="width: 120px" id="suppliersCode" class="easyui-combogrid" data-options="required:true,panelWidth:190,idField:'accountCode',textField:'accountName',editable:false,url:'/cs/bset/CollectpayaccountAction!findList.action?accountSource=2',
 						columns:[[     
 				                {field:'accountCode',title:'客户编号',width:70},     
				                {field:'accountName',title:'客户名称',width:100}   
 				            ]]" /></td>
						<th>付款方式</th>
						<td colspan="3"><input name="paymentMethodCode" id="paymentMethodCode" class="easyui-combobox" data-options="required:true,valueField: 'code',    
									        		 textField: 'text', 
									        		 panelHeight:'auto',
									        		 required:true,   
									        		 value:4,
									       			 url: '/cs/files/baseJson/payWay.json'" /></td>
					</tr>
					<tr>
						<th><p style="width: 70px">客户信息</p></th>
						<td colspan="2"><input style="width:120px;" class="easyui-validatebox" name="cpNameMainOrder" id="cpNameMainOrder" type="text" readonly="readonly"/></td>
						<th>收款方式</th>
						<td colspan="3"><select id="receivablesMethodCode" name="receivablesMethodCode" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'">
							<option value="1">现金</option>
							<option value="2">信用卡</option>
							<option value="3">支票</option>
							<option value="4">转账/汇款</option>
							<option value="5">内转</option>
					</select></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</body>
</html>
