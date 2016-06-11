<%@ page pageEncoding="UTF-8"%>
<style>
#form table th {
	text-align: right;
}

.trhidden {
	display: none;
}
</style>
<script type="text/javascript">
	$(function(){
		$("#guishu2").hide();
		$("#supplierNo_id").combogrid().next('span').find('input').keyup(function() {
			var newValue=$("#supplierNo_id").combogrid('getText');
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
			var g = $("#supplierNo_id").combogrid("grid"); // 获取数据表格对象
			g.datagrid("loadData", result.obj);
			$("#supplierNo_id").combogrid("setValue",newValue);
 		});
	});	
	
	function gaibian(){
 		var productOrderNo = $("#productOrderNo").val();
 		if(productOrderNo==""||productOrderNo==null){
 			$("#affiliationNo3").combobox('clear');
 			$("#affiliationNo").combogrid('setValue',0);
			$("#guishu2").hide();
 			$("#guishu1").show();
 			$("#supplierNo_id").combogrid({disabled:false});
 		}else{
 			$("#guishu1").hide();
 			$("#guishu2").show();
			$("#affiliationNo").combogrid('clear');
			var result = eval("(" + $.ajax( {
				url : "/cs/apmgt/wpAction!OrderSearch.action",
				dataType : "json/xml/html",
				data : {
					"productOrderNo" : productOrderNo
				},
				async : false,
				cache : false,
				type : "post"
			}).responseText + ")");
			
			if(result.success){
				$("#affiliationNo3").combobox({disabled:false});
				var json=JSON.parse(result.obj);
				$("#affiliationPerson").val(json[0].affiliationPerson);
				$("#affiliationNo3").val(json[0].affiliationNo);
				$("#affiliationNo3").combobox({    
				    data:json,   
				    value:'0', 
				    valueField:'affiliationNo',    
    				textField:'affiliationPerson',
    				onSelect:function(){
    					 $('#affiliationPerson').val($('#affiliationNo3').combobox('getText')); 
    				},
				}); 
 				$("#supplierNo_id").combogrid({disabled:true});
 				$("#supplierNo_id").combogrid("setValue",json[1].customerNo);
 				$("#supplierName").val(json[1].customerName);
			}else{
				$("#affiliationNo3").combobox({disabled:true});
				$("#supplierNo_id").combogrid({disabled:true});
				$("#supplierNo_id").combogrid("setValue",'');
				$("#affiliationNo3").combobox('clear');
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
							url : "/cs/apmgt/wpAction!OrderSearch.action",
							dataType : "json/xml/html",
							data : {
								"productOrderNo" : value
							},
							async : false,
							cache : false,
							type : "post"
							}).responseText + ")");
						return result.success==true;
					},
					message : '产品单号不存在，请重新输入！'
				}
			});
			
</script>

<form id="form" style="margin: 5px;">
	<table>
		<tr>
			<th>单据类型</th>
			<td><select name="type" class="easyui-combobox" data-options="editable:false,panelHeight:'auto',required:true,onChange:window.apmgt.waitPayment.add.typeOnChange">
					<option value="5">预付</option>
					<option value="6">押金</option>
					<option value="7">其它</option>
					<option value="8">ADM</option>
					<option value="9">ACM</option>
					<option value="10">MCO</option>
			</select>
			</td>
		</tr>
		<tr>
			<th>应付金额</th>
			<td><input name="payBeAmount" class="easyui-numberbox" value="0" data-options="precision:2,required:true" /></td>
		</tr>
		<tr>
			<th>产品单号</th>
			<td><input name="productOrderNo" id="productOrderNo"  onchange="gaibian()"  class="easyui-validatebox" data-options="validType:'orderCode[0]'"/>
			</td>
		</tr>
		
		<tr class="type1">
			<th>结算期限</th>
			<td><input name="deadline" class="easyui-datebox" data-options="required:true" />
			</td>
		</tr>
		<tr id="guishu1">
			<th>归属信息</th>
			<td><input id="affiliationNo" name="affiliationNo" /> <br />
			<input type="hidden" name="affiliationPerson" id="affiliationPerson"/></td>
		</tr>
		<tr id="guishu2">
			<th>归属信息</th>
			<td><input id="affiliationNo3" name="affiliationNo1"/>
		</tr>
		<tr>
			<th>供应商编号</th>
			<td><input name="supplierNo" id="supplierNo_id" class="easyui-combogrid" data-options="panelWidth:190,required:true,idField:'accountCode',textField:'accountName',url:'/cs/bset/CollectpayaccountAction!findList.action?accountSource=2',
						columns:[[    
				                {field:'accountCode',title:'客户编号',width:70},    
				                {field:'accountName',title:'客户名称',width:100}  
				            ]],
				           onHidePanel: function(){   
				           		$('#supplierName').val($('#supplierNo_id').combogrid('grid').datagrid('getSelected').accountName); 
				        	}"/>
			<input type="hidden" name="supplierName" id="supplierName"/></td>
		</tr>
		<tr>
			<th>备注</th>
			<td><textarea name="remark" style="width: 150px;height:65px;"></textarea></td>
		</tr>
		
		<tr class="type2">
			<th>产品号</th>
			<td><input id="productNo" name="productNo" />
			</td>
		</tr>
		<tr style="display:none;" >
			<th>订单号</th>
			<td><input name="orderNo" />
			</td>
		</tr>
	</table>
</form>