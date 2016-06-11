<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/cs" prefix="cs"%>
<!DOCTYPE HTML>
<html>
<head>
<title>结算系统</title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="inc/easyui.jsp"></jsp:include>
<!-- External JS -->
<script src="${basePath}files/js/index.js"></script>
<!-- External CSS -->
<!-- 基本设置 -->
<c:set var="p1">
	<cs:validMethod code="JBSZ" />
</c:set>
<!-- 票证管理 -->
<c:set var="p2">
	<cs:validMethod code="PZGL" />
</c:set>
<!-- 应收账款 -->
<c:set var="p3">
	<cs:validMethod code="YSZK" />
</c:set>
<!-- 应付账款 -->
<c:set var="p4">
	<cs:validMethod code="YFZK" />
</c:set>
<!-- 账户管理 -->
<c:set var="p5">
	<cs:validMethod code="ZHGL" />
</c:set>
<c:set var="JBSZZJZHGL">
	<cs:validMethod code="JBSZZJZHGL" />
</c:set>
<c:set var="JBSZYSZH">
	<cs:validMethod code="JBSZYSZH" />
</c:set>
<c:set var="JBSZYFZH">
	<cs:validMethod code="JBSZYFZH" />
</c:set>
<c:set var="JBSZJSHL">
	<cs:validMethod code="JBSZJSHL" />
</c:set>
<c:set var="JBSZXYKSXF">
	<cs:validMethod code="JBSZXYKSXF" />
</c:set>
<c:set var="JBSZSJFWSZ">
	<cs:validMethod code="JBSZSJFWSZ" />
</c:set>
<c:set var="ZHGLYCDDCX">
	<cs:validMethod code="ZHGLYCDDCX" />
</c:set>
<c:set var="ZHGLXYKDZ">
	<cs:validMethod code="ZHGLXYKDZ" />
</c:set>
<c:set var="ZHGLZMFH">
	<cs:validMethod code="ZHGLZMFH" />
</c:set>
<c:set var="ZHGLSFKJL">
	<cs:validMethod code="ZHGLSFKJL" />
</c:set>
<c:set var="ZHGLHDYJ">
	<cs:validMethod code="ZHGLHDYJ" />
</c:set>
<c:set var="ZHGLXYKCB">
	<cs:validMethod code="ZHGLXYKCB" />
</c:set>
<script type="text/javascript">
	var p1=${p1};
	var p2=${p2};
	var p3=${p3};
	var p4=${p4};
	var p5=${p5};
	var JBSZZJZHGL=${JBSZZJZHGL};
	var JBSZYSZH=${JBSZYSZH};
	var JBSZYFZH=${JBSZYFZH};
	var JBSZJSHL=${JBSZJSHL};
	var JBSZXYKSXF=${JBSZXYKSXF};
	var JBSZSJFWSZ=${JBSZSJFWSZ};
	var ZHGLYCDDCX=${ZHGLYCDDCX};
	var ZHGLXYKDZ=${ZHGLXYKDZ};
	var ZHGLZMFH=${ZHGLZMFH};
	var ZHGLSFKJL=${ZHGLSFKJL};
	var ZHGLHDYJ=${ZHGLHDYJ};
	var ZHGLXYKCB=${ZHGLXYKCB};
</script>
<link rel="stylesheet" href="${basePath}files/css/index.css"></link>
</head>

<body class="easyui-layout">
	<div data-options="region:'west',border:false" style="width:250px;overflow: hidden;">
		<div class="easyui-layout" data-options="fit:true">
			<div data-options="region:'north',border:false" style="height:145px;">
				<div class="easyui-layout" data-options="fit:true">
					<div data-options="region:'north',border:false" style="height:45px;background-color: #1F232A;padding: 10px;overflow: hidden;">
						<font  class="user-greet" style="color: white;text-align: center;font-size: 18px;vertical-align: middle;">Settlement system</font>
					</div>
					<div data-options="region:'center',border:false" style="padding:10px;background:#303641;">
<!-- 						<div class="user-img"> -->
<!-- 							<img class="img-user" src="files/img/user/1.jpg"></img> -->
<!-- 						</div> -->
						<div class="user-info">
							<div class="user-greet">Welcome</div>
							<div class="user-name">${userSession.name}</div>
							<div class="user-role">
								<span class="role-name">业务员</span>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div data-options="region:'center',border:false">
				<div id="index_menu" class="easyui-accordion" data-options="fit:true,border:false" style="background: #303641;"></div>
			</div>
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<div id="index_tabs" class="easyui-tabs" data-options="fit:true,border:false,tabHeight:44,tools:'#tab-tools'" style="overflow: hidden;">
<!-- 			<div title="首页" data-options="iconCls:'bicon-home'" style="overflow: hidden;"> -->
<!-- 				<iframe src="home.jsp" frameborder="0" style="border:0;width:100%;height:100%;"></iframe> -->
<!-- 			</div> -->
		</div>
	</div>

<!-- 	<div id="tab-tools"> -->
<!-- 		<a href="javascript:void(0)" class="easyui-menubutton home-button" data-options="plain:true,menu:'#user_menu',menuAlign:'right'" style="padding: 5px;"> -->
<!-- 			<img class="img-user" src="files/img/user/1.1.jpg"></img> -->
<!-- 		</a> -->
<!-- 	</div> -->

<!-- 	<div id="user_menu" style="width:100px;"> -->
<!-- 		<div data-options="iconCls:'bicon-user'" id="my_info_btn">我的资料</div> -->
<!-- 		<div data-options="iconCls:'bicon-cog'" id="system_set_btn">系统设置</div> -->
<!-- 		<div class="menu-sep"></div> -->
<!-- 		<div data-options="iconCls:'bicon-fullscreen'" id="full_screen_btn">切换全屏</div> -->
<!-- 		<div class="menu-sep"></div> -->
<!-- 		<div data-options="iconCls:'bicon-lock'" id="user_lock_btn">锁定</div> -->
<!-- 		<div data-options="iconCls:'bicon-off'" id="user_logout_btn">退出</div> -->
<!-- 	</div> -->
</body>
</html>
