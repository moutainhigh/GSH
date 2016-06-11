<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>查询客户未销账账款</title>
<meta charset="UTF-8">
</head>
<body>
<script type="text/javascript">
	$(function(){
		$("#customerNo_id").combogrid().next('span').find('input').keyup(function() {
			var newValue=$("#customerNo_id").combogrid('getText');
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
			var g = $("#customerNo_id").combogrid('grid'); // 获取数据表格对象
			g.datagrid('loadData', result.obj);
			$("#customerNo_id").combogrid('setValue',newValue);
 		});
				
	});
</script>
<!-- External JS -->
    <div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;">
			<a id="receivable_search"  href="#" class="easyui-linkbutton"><i class="fa fa-save"></i>&nbsp;查询</a>
			<a id="receivable_close" href="#" class="easyui-linkbutton"><i class="fa fa-times"></i>&nbsp;关闭</a> 
		</div>
		<div data-options="region:'center',border:false" style="padding:9px;overflow: hidden;">
			<form id="armgt_receivable_search_form" method="post">
				<table>
					<tr>
							<th>交易日期</th>
							<td><input class="easyui-datebox" id="tradeDateQi" name="tradeDateQi" style="width:100px"> To: <input class="easyui-datebox" style="width:100px" id="tradeDateShi" name="tradeDateShi"></td>
						</tr>
						<tr>
							<th>客户编号</th>
							<td><input name="customerNo" id="customerNo_id" class="easyui-combogrid" data-options="required:true,panelWidth:190,idField:'customerNo',textField:'customerName',url:'/cs/armgt/receivableViewAction!findAllCustomerNo.action',
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
							<th>归属人</th>
							<td><input name="affiliationNo" id="affiliationNo" class="easyui-combobox" data-options="panelHeight:'auto',valueField:'affiliationNo',textField:'affiliationPerson',url:'/cs/armgt/receivableViewAction!findAllList.action'" /></td>
						</tr>
						<tr>
							<th>结算期限</th>
							<td><input class="easyui-datebox" style="width:100px" name="deadlineShi" id="deadlineShi"></td>
						</tr>
						<tr>
							<td></td>
							<td></td>
						</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>
