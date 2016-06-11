<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>查询应收应付记录</title>
<meta charset="UTF-8">
</head>
<body>
<!-- External JS -->
    <div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;">
			<a id="collectPayRecord_search"  href="#" class="easyui-linkbutton"><i class="fa fa-save"></i>&nbsp;查询</a>
			<a id="collectPayRecord_close" href="#" class="easyui-linkbutton"><i class="fa fa-times"></i>&nbsp;关闭</a> 
		</div>
		<div data-options="region:'center',border:false" style="padding:9px;overflow: hidden;">
			<form id="account_collectPayRecord_serchcollectPayRecord_form" method="post">
				<table>
					<tr>
						<th align="left">账户</th>
						<td colspan="2">
							<select onchange="changesearchCollectPayRecord();" id="ZHcode" name="type">
								<option selected="selected" value="0">全部</option>
								<option value="1">应收账户</option>
								<option value="2">应付账户</option>
							</select>
						</td>
					</tr>
					<tr style="padding-top: 30px"></tr>
					<tr>
						<th align="left">账户代码</th>
						<td colspan="2">
							<input id="collectPayRecord_zhanghu" name="tradeNo">
						</td>
					</tr>
					<tr style="padding-top: 30px"></tr>
					<tr>
						<th align="left">交易日期</th>
						<td colspan="2"> <input class="easyui-datebox" id="collectPayRecord_date_qi" name="date_qi" data-options="required:true,editable:false" style="width:100px"> 至: <input class="easyui-datebox" id="collectPayRecord_date_shi" name="date_shi" data-options="required:true,editable:false" style="width:100px"></td>
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
			$("#collectPayRecord_date_qi").val(date);
			$("#collectPayRecord_date_shi").val(date1);
		});
		
	</script>
</body>
</html>
