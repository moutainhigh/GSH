<%@ page pageEncoding="UTF-8"%>
<style>
#form {
	margin: 5px;
}

#form table th {
	text-align: right;
}
</style>

<script type="text/javascript">
	$(function(){
	$("#guishu2").hide();
		$("#customerNo_id").combogrid().next('span').find('input').keyup(function() {
			var newValue=$("#customerNo_id").combogrid('getText');
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
			var g = $("#customerNo_id").combogrid("grid"); // 获取数据表格对象
			g.datagrid("loadData", result.obj);
// 			alert(newValue)
			$("#customerNo_id").combogrid("setValue",newValue);
 		});
	});	
	
	
	var result = eval("(" + $.ajax( {
					url : "/cs/armgt/receivable!findAffiliationNo.action",
					dataType : "json/xml/html",
					async : false,
					cache : false,
					type : "post"
				}).responseText + ")");
	var affiliationNoJson=JSON.parse(result.obj);
	
	
	
	function gaibian(){
 		var orderNo = $("#orderNo_id").val();
 		if(orderNo==""||orderNo==null){
 			$("#affiliationNo1").combobox('clear');
 			$("#affiliationNo").combogrid('setValue',0);
			$("#guishu2").hide();
 			$("#guishu1").show();
 			$("#customerNo_id").combogrid({disabled:false});
 		}else{
 			$("#guishu1").hide();
 			$("#guishu2").show();
			$("#affiliationNo").combogrid('clear');
			var result = eval("(" + $.ajax( {
				url : "/cs/armgt/receivable!OrderSearch.action",
				dataType : "json/xml/html",
				data : {
					"orderNo" : orderNo
				},
				async : false,
				cache : false,
				type : "post"
			}).responseText + ")");
			
			if(result.success){
				$("#affiliationNo1").combobox({disabled:false});
				var json=JSON.parse(result.obj);
				$("#affiliationPerson").val(json[0].affiliationPerson);
				$("#affiliationNo1").val(json[0].affiliationNo);
				$("#affiliationNo1").combobox({    
				    data:json,   
				    value:'0', 
				    valueField:'affiliationNo',    
    				textField:'affiliationPerson',
    				onSelect:function(){
    					 $('#affiliationPerson').val($('#affiliationNo1').combobox('getText')); 
    				},
				}); 
 				$("#customerNo_id").combogrid({disabled:true});
 				$("#customerNo_id").combogrid("setValue",json[1].customerNo);
//  				alert(json[1].customerName)
 				$("#customerName").val(json[1].customerName);
			}else{
				$("#affiliationNo1").combobox({disabled:true});
				$("#customerNo_id").combogrid({disabled:true});
				$("#customerNo_id").combogrid("setValue",'');
				$("#affiliationNo1").combobox('clear');
			}
			
			
 		}
 	}
 	
 			/**
			 * 验证规则
			 */
			$.extend($.fn.validatebox.defaults.rules, {
				orderCode : {
					validator : function(value, opt) {
						var rules = $.fn.validatebox.defaults.rules;
						var result = eval("(" + $.ajax( {
							url : "/cs/armgt/receivable!OrderSearch.action",
							dataType : "json/xml/html",
							data : {
								"orderNo" : value
							},
							async : false,
							cache : false,
							type : "post"
							}).responseText + ")");
						return result.success==true;
					},
					message : '订单号不存在，请重新输入！'
				}
			});
			
			
</script>

<form id="form">
	<table>
		<tr>
			<th>单据类型</th>
			<td><select name="type" class="easyui-combobox" data-options="editable:false,panelHeight:'auto',required:true,onChange:window.armgt.receivable.add.typeOnChange">
					<option value="4">预收</option>
					<option value="5">押金</option>
					<option value="6">其它</option>
			</select></td>
		</tr>
		<tr>
			<th>应收金额</th>
			<td><input id="incomeBeAmount" name="incomeBeAmount" class="easyui-numberbox" value="0" data-options="min:0,precision:2,required:true,validType:'zero'" />
			</td>
		</tr>
		<tr>
<!-- 		class="easyui-validatebox" data-options="validType:'orderCode[0]'" -->
			<th>订单号</th>
			<td><input id="orderNo_id" name="orderNo" onchange="gaibian()" class="easyui-validatebox" data-options="validType:'orderCode[0]'"/></td>
		</tr>
		<tr>
			<th>结算期限</th>
			<td><input name="deadline" class="easyui-datebox" data-options="required:true" /></td>
		</tr>
		<tr id="guishu1">
			<th>归属信息</th>
			<td><input id="affiliationNo" name="affiliationNo"/>
			<input type="hidden" name="affiliationPerson" id="affiliationPerson"></td>
		</tr>
		<tr id="guishu2">
			<th>归属信息</th>
			<td><input id="affiliationNo1" name="affiliationNo1"/>
		</tr>
		<tr>
			<th>客户编号</th>
			<td><input id="customerNo_id" name="customerNo" class="easyui-combogrid" data-options="panelWidth:190,required:true,idField:'accountCode',textField:'accountName',url:'/cs/bset/CollectpayaccountAction!findList.action?accountSource=1',
						columns:[[    
				                {field:'accountCode',title:'客户编号',width:70},    
				                {field:'accountName',title:'客户名称',width:100}  
				            ]]" />
			    <input type="hidden" name="customerName" id="customerName">   </td>
		</tr>
		<tr>
			<th>备注</th>
			<td><textarea name="remark" style="width: 150px;height:65px;"></textarea>
			</td>
		</tr>
	</table>
</form>