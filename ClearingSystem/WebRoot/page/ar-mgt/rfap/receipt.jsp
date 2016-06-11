<%@ page pageEncoding="utf-8"%>

<style>
.rcpt-body {
	height: 'auto';
	font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	font-size: 12px;
	line-height: 20px;
	color: #333;
	margin-left: auto;
	margin-right: auto;
}

.rcpt-body div,.rcpt-body table {
	width: 600px;
	margin-left: auto;
	margin-right: auto;
}

.rcpt-title {
	text-align: center;
	font-size: 20px;
}

.rcpt-table-thright {
	align: left;
	width: 70px;
}

.rcpt-table-thcenter {
	align: left;
	width: 300px;
}

.rcpt-table-thleft {
	align: left;
}
</style>
<div class="rcpt-body" id="contents">
	<div class="rcpt-title">
		<div>
			<h4>客户退款申请单</h4>
		</div>
	</div>
	<table class="rcpt-table">
		<tr>
			<th rowspan="3" class="rcpt-table-thright">申请人：</th>
			<td rowspan="3" class="rcpt-table-thcenter">张三</td>
			<th class="rcpt-table-thleft">支付号：</th>
			<td>N3N3N3A</td>
		</tr>
		<tr>
			<th class="rcpt-table-thleft">申请日期：</th>
			<td>2014-05-06</td>
		</tr>
		<tr>
			<th class="rcpt-table-thleft">付款人：</th>
			<td>唐彩虹</td>
		</tr>
	</table>
	<div class="rcpt-say">
		我司已收到您的款项，总计人民币<em style="text-decoration: underline">贰仟元（CNY:2000.00）</em>，具体说明如下：
	</div>
</div>