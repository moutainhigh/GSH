<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>
<body>
	<!-- 发送邮件方法 -->
	<script>
		$(function(){
			$("#emailSave").click(function(){
				var result = eval("(" + $.ajax( {
					url : "/cs/emailAction!sendEmail.action",
					dataType : "json",
					data : {
						"content2" : $("#contents").html(),
						"title" : $("#title").val(),
						"content" : $("#mailContent2").val(),
						"toAddress" : $("#toAddress").val(),
						"ccAddress" : $("#ccAddress").val(),
						"mailSignature" : $("#mailQm").val()
					},
					async : false,
					cache : false,
					type : "post"
				}).responseText + ")");
				
				if(result.success){
					$.messager.show({
						title : '提示',
						msg : '邮件发送成功',
						showType : 'slide'
					});
					$("#emailWin").window('close');
					$("#dlgNotice").dialog('close');
				}else{
					$.messager.show({
						title : '提示',
						msg : '邮件发送失败',
						showType : 'slide'
					});
					$("#emailWin").window('close');
				}
			});
			
			$("#emailClose").click(function(){
				$("#emailWin").window('close');
			});
		});
		
	</script>
	<div class="easyui-layout" data-options="fit:true"
		style="overflow: hidden;" id="email-div">
		<div data-options="region:'center',border:false"
			style="overflow: hidden;">
			<form id="formemail" method="post">
				<input type="hidden" id="emailContent" name="emailContent" /> <input
					type="hidden" id="emailtitle" name="emailtitle" /> <input
					type="hidden" id="pdftitle" name="pdftitle" /> <input
					type="hidden" id="djType" name="djType" /> <input type="hidden"
					name="recId" />
				<table class="table table-condensed" style="margin-top: 10px;margin-left: 10px">
					<tr>
						<th align="right" class="email-th">收件人</th>
						<td><input id="toAddress" name="mailInfo.toAddress"
							type="text" placeholder="多个邮箱请用逗号隔开" class="easyui-validatebox"
							required="true"/></td>
					</tr>
					<tr height="5px"></tr>
					<tr>
						<th align="right"  class="email-th">抄送</th>
						<td><input name="mailInfo.ccAddress" type="text" id="ccAddress"
							placeholder="多个邮箱请用逗号隔开" onchange="delCnDH(this);" /></td>
					</tr>
					<tr height="5px"></tr>
					<tr>
						<th align="right"  class="email-th">邮件主题</th>
						<td><input name="mailInfo.subject" type="text" id="title"
							 class="easyui-validatebox" required="true"/>
						</td>
					</tr>
					<tr height="5px"></tr>
					<tr>
						<th align="right" class="email-th">签名</th>
						<td><input name="mailQm" id="mailQm" type="text" placeholder="上海南光国际旅行社"
							 />
						</td>
					</tr>
					<tr height="5px"></tr>
					<tr>
						<th align="right" class="email-th">内容</th>
						<td>
							<input type="hidden" id="content_hide" />
							<textarea rows="4" cols="35" name="mailContent2" id="mailContent2"></textarea>
						</td>
						
					</tr>
				</table>
			</form>
		</div>
		<div data-options="region:'south',border:false" class="modal-footer"
			style="overflow: hidden; background-color: #f5f5f5; border-top: solid 1px #ddd;padding:5px 5px 5px">
			<a id="emailSave" href="#" class="easyui-linkbutton">&nbsp;确认</a>
			<a id="emailClose" href="#" class="easyui-linkbutton">&nbsp;放弃</a>
		</div>
	</div>
</body>
</html>
