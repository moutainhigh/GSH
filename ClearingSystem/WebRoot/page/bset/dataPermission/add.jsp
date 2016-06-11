<%@ page pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function(){
		$('#idPermission').combotree({    
					    url: '${basePath}/bset/dataPermissionAction!findDeptByCid.action',    
					    multiple: true,
					    required:true,
					    editable:false,
					    onChange:function(newValue, oldValue){
				   			 var result = eval("("+ $.ajax({
								url : "${basePath}/bset/dataPermissionAction!findUidByDeptString.action?dept="+newValue,
								dataType : "json/xml/html",
								async : false,
								cache : false,
								type : "post"
								}).responseText + ")");
							$("#idName").val(result.obj);
							$("#name").val(result.msg);
					    }   
					}); 
			});	
			
	    	
	    	$('#uid').combogrid({    
			    url:'${basePath}/bset/dataPermissionAction!findCsUser.action',    
			    multiple: false,
			    required:true,
			    editable:false,
			    idField:'uid',    
		    	textField:'name',
			    columns:[[    
			        {field:'name',title:'用户',width:100},    
			        {field:'email',title:'Email',width:150},    
			    ]],    
 				onChange:function(newValue, oldValue){
 					var g = $('#uid').combogrid('grid');	// 获取数据表格对象
					var r = g.datagrid('getSelected');
 					$("#userName").val(r.email);
 					$("#name").val(r.name);
 					$("#role").val(r.roleName);
 				}
			});  

	    	
// 	    	findCsUser
// 	    	findCsYsYfUser
</script>

<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center',border:false" style="padding:9px;overflow: hidden;">
		<form id="form">
			<input type="hidden" id="name" name="name" value="">
			<input type="hidden" id="permission" name="permission" value="">
			<input type="hidden" id="id" name="id" value="">
			<input type="hidden" id="createDate" name="createDate" value="">
			<input type="hidden" id="uname" name="uname" value="">
			<input id="idName" name="idName" type="hidden" />
			<input id="cid" name="cid" type="hidden" />
			<table>
				<tr>
					<th align="left">用户</th>
					<td><input id="uid" name="uid"/>
					</td>
				</tr>
				<tr>
					<th align="left">部门</th>
					<td><input id="idPermission" name="idPermission"/>
					</td>
				</tr>
<!-- 				<tr> -->
<!-- 					<th align="left">员工</th> -->
<!-- 					<td></td> -->
<!-- 				</tr> -->
				<tr>
					<th align="left">角色名称</th>
					<td><input id="role" name="role" class="easyui-validatebox"  readonly="readonly"/></td>
				</tr>
				<tr>
					<th align="left">系统用户</th>
					<td><input id="userName" name="userName" class="easyui-validatebox"  readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<th align="left">描述</th>
					<td><input name="remark" class="easyui-validatebox" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
