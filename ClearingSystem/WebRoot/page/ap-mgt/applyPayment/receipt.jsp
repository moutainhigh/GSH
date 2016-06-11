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
	<table class="rcpt-table" width="700px" height="100px">
		<tr>
			<td colspan="4">
				<table style="width: 100%; height: 100%;">
					<tr align="center">
						<td><img alt="logo" src="${basePath}logo/10019.jpg" width="120px" height="70px">
						</td>
					</tr>
					<tr align="center">
						<td><font
							style="font-family: 黑体; font-size: 25px; color: black;">
								付款通知单 </font></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<th></th>
			<td></td>
			<td>制单人：${userSession.name}</td>
			<td></td>
		</tr>
		<tr>
			<th></th>
			<td></td>
			<td>日期：<span id="date"></span></td>
			<td></td>
		</tr>
		<tr>
			<td colspan="4" class="rcpt-table-thleft" align="left">支款用途：<span id="yongtu">asdasd</span></td>
		</tr>
		<tr>
			<td colspan="4" align="left" class="rcpt-table-thleft">收款单位：<span id="danwei">asdasd</span></td>
		</tr>
		<tr>
			<td colspan="4" align="left" class="rcpt-table-thleft">账号：<span id="zhanghao">asdasd</span></td>
		</tr>
		<tr>
			<td colspan="4" align="left" class="rcpt-table-thleft">开户行：<span id="kaihuhang">asdasd</span></td>
		</tr>
		<tr>
			<td colspan="4" align="left" class="rcpt-table-thleft">行号：<span id="hanghao">asdasd</span></td>
		</tr>
		<tr>
			<td colspan="4" align="left" class="rcpt-table-thleft">支款金额：<span id="jine">asdasd</span></td>
		</tr>
		<tr>
			<td colspan="4" align="left">支款类别:
			<input type="radio" name="type">现金<input type="radio" name="type">信用卡<input type="radio" name="type">支票
			<input type="radio" name="type">转账/汇款<input type="radio" name="type">内转</td>
		</tr>
		<tr>
			<td colspan="4" align="left">单据号：<span id="danjihao">asdasd</span></td>
		</tr>
		<tr>
			<td align="left">财务审核：<span id="caiwushenhe"></span></td>
			<td align="left">时间：<span id="fuhe"></span></td>
			<td align="left">领款人：<span id="lingkuanren"></span></td>
			<td></td>
		</tr>
		<tr>
			<td align="left" colspan="4" class="rcpt-table-thleft">注释：<span id="zhushi">asdasd</span></td>
		</tr>
	</table>
</div>