<%@ page pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	application.setAttribute("basePath", path+"/");
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <title>商家登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="Joychao <joy@joychao.cc>">    
  </head>
    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	<link class="bootstrap library" rel="stylesheet" type="text/css" href="http://sandbox.runjs.cn/js/sandbox/bootstrap-2.2.0/css/bootstrap.min.css">
	<script class="bootstrap library" src="http://sandbox.runjs.cn/js/sandbox/jquery/jquery-1.7.2.min.js" type="text/javascript"></script>
	<script class="bootstrap library" src="http://sandbox.runjs.cn/js/sandbox/bootstrap-2.2.0/js/bootstrap.min.js" type="text/javascript"></script>
  	<script src="${basePath}files/js/index.js"></script>
	<!-- External CSS -->
	<link rel="stylesheet" href="${basePath}files/css/index.css"></link>
  	<style type="text/css">
  	*{margin:0;padding: 0;}
      body{background: #444 url(http://sandbox.runjs.cn/uploads/rs/418/nkls38xx/carbon_fibre_big.png)}
      .loginBox{width:420px;height:230px;padding:0 20px;border:1px solid #fff; color:#000; margin-top:40px; border-radius:8px;background: white;box-shadow:0 0 15px #222; background: -moz-linear-gradient(top, #fff, #efefef 8%);background: -webkit-gradient(linear, 0 0, 0 100%, from(#f6f6f6), to(#f4f4f4));font:11px/1.5em 'Microsoft YaHei' ;position: absolute;left:50%;top:50%;margin-left:-210px;margin-top:-115px;}
      .loginBox h2{height:45px;font-size:20px;font-weight:normal;}
      .loginBox .left{border-right:1px solid #ccc;height:100%;padding-right: 20px; 
 	</style>
  </head>
  <body>
    <div class="container">
    	<form id="loginForm"  method="post" action="login!login.action"> 
        <section class="loginBox row-fluid">
          <section class="span7 left">
            <h2>商户登录</h2>
            <p><input type="text" name="userName" /></p>
            <p><input type="text" name="password" /></p>
            <section class="row-fluid">
              <section class="span8 lh30"><label><input type="checkbox" name="rememberme" />下次自动登录</label></section>
          <section class="span1"><input id="Logbutton" type="submit" value=" 登录 " class="btn btn-primary"></section>
            </section>
          </section>
          <section class="span5 right">
            <h2>没有帐户？</h2>
            <section>
              <p>这里有一段文字啊，很多的文字啊，太多太多的文字了，主要可以介绍介绍注册的好处和公司或者项目概况。。。</p>
              <p><input type="button" value=" 注册 " class="btn"></p>
            </section>
          </section>
        </section><!-- /loginBox -->
        </form>
    </div> <!-- /container -->
    <script type="text/javascript">
    	$(function(){
//     		$("#Logbutton").click(function(){
//     			alert('${basePath}');
//     			alert("192.168.1.116:8080"+${basePath}+"login!login.action")
// 	    		var form_data = $.serializeObject($('#loginForm'));
// 				$.post("192.168.1.116:8080/cs/login!login.action", {
// 					form : JSON.stringify(form_data),
// 				}, function(result) {
// 					var result = JSON.parse(result);
// 					if (result.success) {
// 						$(dtgd).datagrid('load');
// 						$('#dlgRtof').dialog('close');
// 					}
// 					$.messager.show({
// 						title : '提示',
// 						msg : result.msg,
// 						showType : 'slide'
// 					});
// 				});
//     		});
    	});
    </script>
  </body>
</html>
