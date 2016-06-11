<%@ page pageEncoding="UTF-8"%>
<style>
#form {
	margin: 5px;
}

#form table th {
	text-align: right;
}
</style>
<form id="form">
	<table>
		<tr>
			<th>变更前</th>
			<td><input name="modifiedBefore" readonly="readonly" /></td>
		</tr>
		<tr>
			<th>变更后</th>
			<td><input name="modifiedOn" class="easyui-datebox" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<th>备注</th>
			<td><textarea name="remark" class="easyui-validatebox" data-options="required:true" style="width: 150px;height:65px;"></textarea>
			</td>
		</tr>
	</table>
</form>
