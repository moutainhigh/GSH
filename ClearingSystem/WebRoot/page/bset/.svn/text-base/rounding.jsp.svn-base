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
<script src="${basePath}files/js/page.bset.rounding.js"></script>
</head>
<body>

	<table>
		<tr>
			<th>计算方式：</th>
			<td><select class="easyui-combobox" style="width:50px;" data-options="panelHeight:'auto'">
					<option value="0">元</option>
					<option value="1">角</option>
					<option value="2">分</option>
			</select></td>
			<td><select class="easyui-combobox" style="width:100px;" data-options="panelHeight:'auto'">
					<option value="4">四舍五入</option>
					<option value="0">缝一进十</option>
					<option value="9">舍去余数</option>
			</select></td>
		</tr>
		<tr>
			<td colspan="4" style="text-align: center;padding-top: 20px;"><a href="#" class="easyui-linkbutton" onclick="window.bset.rounding.save();"><i class="fa fa-save"></i> 保存</a></td>
		</tr>
	</table>


</body>
</html>
