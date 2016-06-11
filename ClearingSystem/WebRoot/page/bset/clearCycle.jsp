<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title></title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<style type="text/css">
body {
	text-align: center;
	padding-top: 100px;
	font-size: 12px;
}

table {
	margin-left: auto;
	margin-right: auto;
	border: 1px solid #ccc;
	width: auto;
	padding: 20px;
}
</style>

<!-- External JS -->
<script src="${basePath}files/js/page.bset.clearCycle.js"></script>
</head>
<body>

	<table>
		<tr>
			<th>结算日：</th>
			<td><select class="easyui-combobox" style="width:100px;" data-options="panelHeight:'auto',onChange:window.bset.clearCycle.typeOnChange">
					<option value="1">自然月月底</option>
					<option value="2">每月固定日</option>
			</select></td>
			<td colspan="2" style="text-align: left;">每月&nbsp;<input id="day" class="easyui-numberspinner" style="width:50px;" data-options="required:true,min:1,max:28,disabled:true">
			</td>
		</tr>
		<tr>
			<th>有效期：</th>
			<td><select name="deadline" class="easyui-combobox" style="width:100px;" data-options="panelHeight:'auto',onChange:window.bset.clearCycle.deadlineOnChange">
					<option value="1">长期有效</option>
					<option value="2">起讫期限</option>
			</select></td>
			<td><input id="startDate" type="text" class="easyui-datebox" style="width:100px;" data-options="required:true,disabled:true" /></td>
			<td>至 <input id="endDate" type="text" class="easyui-datebox" style="width:100px;" data-options="required:true,disabled:true" /></td>
		</tr>
		<tr>
			<td colspan="4" style="text-align: center;padding-top: 20px;"><a href="#" class="easyui-linkbutton" onclick="window.bset.clearCycle.save();"><i class="fa fa-save"></i> 保存</a></td>
		</tr>
	</table>


</body>
</html>
