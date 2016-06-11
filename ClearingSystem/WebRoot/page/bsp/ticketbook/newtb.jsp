<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>新增BSP票本</title>
<meta charset="UTF-8">
</head>
<body>
<!-- External JS -->
    <script type="text/javascript" src="${basePath}files/js/page.bsp.ticketbook.newtb.js"></script>
    
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;">
			<a id="bsp_save"  href="#" class="easyui-linkbutton"><i class="fa fa-save"></i>&nbsp;保存</a>
			<a id="bsp_remoud" href="#" class="easyui-linkbutton"><i class="fa fa-times"></i>&nbsp;放弃</a> 
		</div>
		<div data-options="region:'center',border:false" style="padding:9px;overflow: hidden;">
			<form id="bsp_ticketbook_newtb_form" method="post">
				<input type="hidden" name="bspNumber" id="bsp_ticketbook_newtb_form_bspNumber">
				<input type="hidden" name="bspAllowance" id="bsp_ticketbook_newtb_form_bspAllowance">
				<table>
					<tr>
						<th align="left">起始票号</th>
						<td colspan="2"><input style="width:173px;" class="easyui-validatebox" name="bspStartBank" id="bsp_ticketbook_newtb_form_bspStartBank" data-options="required:true,validType:['minLength[10]','ticked']" type="text" /></td>
					</tr>
					<tr style="padding-top: 30px"></tr>
					<tr>
						<th align="left">终止票号</th>
						<td colspan="2"><input style="width:173px;" class="easyui-validatebox" name="bspTerminationBank" id="bsp_ticketbook_newtb_form_bspTerminationBank" data-options="required:true,validType:['minLength[10]','tickedDaxiao','ticked']" type="text" /></td>
					</tr>
					<tr style="padding-top: 30px"></tr>
					<tr>
						<th align="left">类型</th>
						<td colspan="2"><select  name="bspType">
							<option value="2" selected="selected">国内</option>
							<option value="1">国际</option>
						</select></td>
					</tr>
					<tr style="padding-top: 30px" ></tr>
					<tr>
						<th align="left">余量提醒</th>
						<td colspan="2"><input style="width:100px;" class="easyui-validatebox" name="bspAllowanceRemind" data-options="required:true" type="text" />
					</tr>
				</table>
			</form>
		</div>
	</div>
	
</body>
</html>
