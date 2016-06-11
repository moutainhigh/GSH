<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>查询信用卡成本</title>
<meta charset="UTF-8">
</head>
<body>
<!-- External JS -->
    <div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;">
			<a id="creditCost_search" class="easyui-linkbutton"><i class="fa fa-save"></i>&nbsp;查询</a>
			<a id="creditCost_close" class="easyui-linkbutton"><i class="fa fa-times"></i>&nbsp;关闭</a> 
		</div>
		<div data-options="region:'center',border:false" style="padding:9px;overflow: hidden;">
			<form id="account_creditCost_form" method="post">
				<table>
					
					<tr>
						<th align="left">日期</th>
						<td colspan="2"> <input class="easyui-datebox" id="creditCost_date_qi" name="qiDate" style="width:100px"> 至: <input class="easyui-datebox" id="creditCost_date_shi" name="shiDate" style="width:100px"></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			var date=new Date();
			var d=date.getMonth()+1;
			var date =  date.getFullYear()+'-'+d+'-01';
			var date1=new Date();
			var d1=date1.getMonth()+1;
			var date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
			$("#creditCost_date_qi").val(date);
			$("#creditCost_date_shi").val(date1);
		});
		
	</script>
</body>
</html>
