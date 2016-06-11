<%@ page pageEncoding="UTF-8"%>
<script>
	var XYKCODE = [ {
		"id" : "DCC00111",
		"text" : "国内信用卡XXX"
	}, {
		"id" : "OTH00111",
		"text" : "其它信用卡XXX"
	} ];
</script>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;">
		<a id="btnSave" href="#" class="easyui-linkbutton"><i class="fa fa-save"></i>&nbsp;保存</a> <a id="btnClose" href="#" class="easyui-linkbutton"><i class="fa fa-times"></i>&nbsp;放弃</a>
	</div>
	<div data-options="region:'center',border:false" style="padding:9px;overflow: hidden;">
		<form id="form">
			<input type="hidden" name="userId" value="">
			<input type="hidden" name="creditcardfeeLock" value="">
			<input type="hidden" name="adminId" value="">
			<input type="hidden" name="deptId" value="">
			<table>
				<tr>
					<th align="left">信用卡代码</th>
					<td><input id="creditCode" name="creditCode" class="easyui-combogrid" data-options="editable:false,panelWidth:290,required:true,idField:'id',textField:'text',url:'/cs/files/baseJson/CreditCardCode.json',validType:'creditCode[0]',    
				            columns:[[    
				                {field:'id',title:'信用卡代码',width:70},    
				                {field:'code',title:'英文代码名称',width:100}, 
				                {field:'text',title:'中文代码名称',width:100}  
				            ]],
						onHidePanel: function(){   
				            $('#creditDesc').val($('#creditCode').combogrid('grid').datagrid('getSelected').code+'--'+$('#creditCode').combogrid('grid').datagrid('getSelected').text);    
				        }" /></td>
				</tr>
				<tr>
					<th align="left">名称</th>
					<td><input id="creditDesc" name="creditDesc"  class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<th align="left">公布手续费</th>
					<td><input name="publication" class="easyui-numberbox" value="0" data-options="min:0,precision:2,suffix:'%',required:true" />
					</td>
				</tr>
				<tr>
					<th align="left">结算手续费</th>
					<td><input name="settlement" class="easyui-numberbox" value="0" data-options="min:0,precision:2,suffix:'%',required:true" />
					</td>
				</tr>
				<tr>
					<th align="left">失效日期</th>
					<td><input name="expirationdate" class="easyui-datebox" data-options="required:true"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>