<%@ page pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$("#code_abc").combogrid().next('span').find('input').keyup(function() {
			var newValue=$("#code_abc").combogrid('getText');
			var result = eval("(" + $.ajax({
				url : "/cs/aa/bb!find.action",
				dataType : "json/xml/html",
				data : {
					"cu" : newValue
				},
				async : false,
				cache : false,
				type : "post"
			}).responseText + ")");
			var g = $("#code_abc").combogrid("grid"); // 获取数据表格对象
			g.datagrid("loadData", result.obj);
			$("#code_abc").combogrid("setValue",newValue);
 		});
	});	

</script>

<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" style="padding:9px;overflow: hidden;">
		<form id="form">
			<input type="hidden" name="establishDate" value="">
			<input type="hidden" name="userId" value="">
			<input type="hidden" name="settlementexchangerateLock" value="">
			<input type="hidden" name="adminId" value="">
			<input type="hidden" name="deptId" value="">
			<table>
				<tr>
					<th align="left">货币单位</th>
					<td><input id="code_abc" name="code" class="easyui-combogrid" data-options=" panelWidth:190,required:true,idField:'id',textField:'text',url:'/cs/aa/bb!findAll.action',validType:'codeReceipt[0]',    
				            columns:[[    
				                {field:'id',title:'货币代码',width:70},    
				                {field:'text',title:'货币名称',width:100}  
				            ]],onChange:function(newValue, oldValue){
				            	var g = $('#code_abc').combogrid('grid');	// 获取数据表格对象
								var r = g.datagrid('getSelected');
				            	var selling = $('#selling').numberbox('getValue');
				            	if(selling!=null&&selling!=''){
				            		$('#gongshi').html('1CNY人民币='+selling+newValue+r.text);
				            	}
				            }" />
				            <input type="hidden" name="name" id="name">
					</td>
				</tr>
				<tr>
					<th align="left">买入价</th>
					<td><input name="buying" class="easyui-numberbox" data-options="min:0,precision:2" /></td>
				</tr>
				<tr>
					<th align="left">卖出价</th>
					<td><input name="selling" id="selling" class="easyui-numberbox" data-options="required:true,min:0,precision:2,onChange:function(newValue, oldValue){
						var g = $('#code_abc').combogrid('grid');	// 获取数据表格对象
						var r = g.datagrid('getSelected');
						if(r.text!=null&&r.text!=''){
		            		$('#gongshi').html('1CNY人民币='+newValue+r.id+r.text);
		            	}
					}" /></td>
				</tr>
				<tr>
					<th align="left">有效日期</th>
					<td><input name="validity" class="easyui-datebox" data-options="editable:false" />
					</td>
				</tr>
				<tr>
					<td colspan="2" style="color: red;font-size: 14px" id="gongshi"></td>
				</tr>
			</table>
		</form>
	</div>
</div>
