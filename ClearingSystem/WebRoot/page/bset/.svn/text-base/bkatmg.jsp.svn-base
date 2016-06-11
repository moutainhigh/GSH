<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/cs" prefix="cs"%>
<!DOCTYPE HTML>
<html>
<head>
<title></title>
<meta charset="UTF-8">

<!-- IMPORT EASYUI -->
<jsp:include page="../../inc/easyui.jsp"></jsp:include>
<!-- External CSS -->
<!-- External JS -->
<c:set var="ZJZHGLXJ">
	<cs:validMethod code="ZJZHGLXJ" />
</c:set>
<c:set var="ZJZHGLBJ">
	<cs:validMethod code="ZJZHGLBJ" />
</c:set>
<c:set var="ZJZHGLCK">
	<cs:validMethod code="ZJZHGLCK" />
</c:set>
<c:set var="ZJZHGLSC">
	<cs:validMethod code="ZJZHGLSC" />
</c:set>
<c:set var="ZJZHGLZZ">
	<cs:validMethod code="ZJZHGLZZ" />
</c:set>
<c:set var="ZJZHGLJH">
	<cs:validMethod code="ZJZHGLJH" />
</c:set>
<c:set var="ZJZHGLGQ">
	<cs:validMethod code="ZJZHGLGQ" />
</c:set>
<c:set var="ZJZHGLJG">
	<cs:validMethod code="ZJZHGLJG" />
</c:set>
<c:set var="ZJZHGLZX">
	<cs:validMethod code="ZJZHGLZX" />
</c:set>
<c:set var="ZJZHGLSQJS">
	<cs:validMethod code="ZJZHGLSQJS" />
</c:set>
<c:set var="ZJZHGLJS">
	<cs:validMethod code="ZJZHGLJS" />
</c:set>
<script type="text/javascript">
	var ZJZHGLXJ=${ZJZHGLXJ};
	var ZJZHGLBJ=${ZJZHGLBJ};
	var ZJZHGLCK=${ZJZHGLCK};
	var ZJZHGLSC=${ZJZHGLSC};
	var ZJZHGLZZ=${ZJZHGLZZ};
	var ZJZHGLJH=${ZJZHGLJH};
	var ZJZHGLGQ=${ZJZHGLGQ};
	var ZJZHGLJG=${ZJZHGLJG};
	var ZJZHGLZX=${ZJZHGLZX};
	var ZJZHGLSQJS=${ZJZHGLSQJS};
	var ZJZHGLJS=${ZJZHGLJS};
</script>
<script src="${basePath}files/js/page.bset.bkatmg.js"></script> 
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background: #f5f5f5;">
		<div style="margin-bottom:5px">
			<a href="#" class="easyui-linkbutton" iconCls="bicon-asterisk" plain="true" id="newbtn">新建</a> 
			<a href="#" class="easyui-linkbutton" iconCls="bicon-edit" plain="true" id="editbtn">编辑</a> 
			<a href="#" class="easyui-linkbutton" iconCls="bicon-remove" plain="true" id="removebtn">删除</a> 
			<a href="#" class="easyui-linkbutton" iconCls="bicon-folder-open" plain="true" id="viewbtn">查看</a> 
			<a href="#" class="easyui-linkbutton" plain="true" id="tranbtn"><i class="fa fa-clipboard"></i> 转账</a> 
			<a href="#" class="easyui-linkbutton" plain="true" id="activbtn"><i class="fa fa-bolt"></i> 激活</a> 
			<a href="#" class="easyui-linkbutton" plain="true" id="lockbtn"><i class="fa fa-link"></i> 挂起</a> 
			<a href="#" class="easyui-linkbutton" plain="true" id="unlockbtn"><i class="fa fa-unlink"></i> 解挂</a>
			<a href="#" class="easyui-linkbutton" plain="true" id="logoffbtn"><i class="bicon-off"></i> 注销</a> 
			<a href="#" class="easyui-linkbutton" plain="true" id="shenqingjiesuobtn"><i class="fa fa-lock"></i> 申请解锁</a> 
			<a href="#" class="easyui-linkbutton" plain="true" id="jiesuobtn"><i class="fa fa-lock"></i> 解锁</a> 
<!-- 			<a href="#" class="easyui-linkbutton" iconCls="bicon-search" plain="true" id="searchbtn">高级查询</a> -->
		</div>
		<div>
			<form id="bkatmg_form">
				账户编码：<input style="width:130px;" class="easyui-validatebox" name="accountCode" type="text" />
				开户银行：<input style="width:130px;" class="easyui-validatebox" id="accountBank" name="accountBank" type="text" />
				账户币种：<input class="easyui-combogrid" id="bkatmg_accountCurrency" name="accountCurrency" style="width:130px" 
	    						data-options="editable:false, panelWidth:190,idField:'id',textField:'text',url:'/cs/files/baseJson/CurrencyUnit.json',   
					            columns:[[    
					                {field:'id',title:'货币代码',width:70},    
					                {field:'text',title:'货币名称',width:100}  
					            ]] " /> 
	    		账户类型：<select  name="accountType" class="easyui-combobox" id="bkatmg_accountType" data-options="editable:false,panelHeight:'auto',onChange:window.bset.bkatmg.newb.search.accountTypeOnChange">
								<option value="C">公司</option>
								<option value="P">个人</option>
								<option value="M">现金</option>
								<option value="X">信用卡</option>
								<option value="O">第三方支付</option>
							</select>
				<a href="#" class="easyui-linkbutton" iconCls="bicon-search" id="btSearch">Search</a>
			</form>
			
		</div>
	</div>
	<div data-options="region:'center',border:false">
		<table id="bset_bkatmg_dg" class="easyui-datagrid" data-options="fit:true,
						  pagination:true,
						  pagePosition:'both',
						  rownumbers:true,
						  singleSelect:true,
						  method:'get',
						  border:false,
						  url:'/cs/bset/accountAction!findAll.action',
						  onSelect:window.bset.bkatmg.onSelect">
			<thead>
				<tr>
					<th data-options="field:'options',width:100,align:'center',formatter:window.bset.bkatmg.optionsFormater">操作</th>
					<th data-options="field:'accountCode',width:80,sortable:true,formatter:window.bset.bkatmg.accountCodeFormater">账户编码</th>
					<th data-options="field:'accountType',width:80,sortable:true,formatter:window.bset.bkatmg.accountTypeFormater">账户类型</th>
					<th data-options="field:'accountCurrency',width:80,sortable:true,formatter:window.bset.bkatmg.accountCurrencyFormater">账户币种</th>
					<th data-options="field:'accountOwner',width:150,sortable:true">开户人</th>
					<th data-options="field:'accountBank',width:200,sortable:true">开户银行</th>
					<th data-options="field:'accountNumber',width:160,sortable:true,align:'center'">账户号码</th>
					<th data-options="field:'accountBalance',width:100,sortable:true,align:'right',formatter:window.bset.bkatmg.accountBalanceFormater">账户结余</th>
					<th data-options="field:'accountStatus',width:60,sortable:true,formatter:window.bset.bkatmg.accountStatusFormater,styler:window.bset.bkatmg.accountStatusStyler">状态</th>
					<th data-options="field:'accountReminder',width:60,sortable:true,align:'center',hidden:true">余额提醒</th>
					<th data-options="field:'accountBankAdress',width:60,sortable:true,align:'center',hidden:true">开户行地址</th>
					<th data-options="field:'accountBankNumber',width:60,sortable:true,align:'center',hidden:true">行号/SWIFT</th>
					<th data-options="field:'defaultIncAccount',width:60,sortable:true,align:'center',hidden:true">是否默认收款账户</th>
					<th data-options="field:'defaultPayAccount',width:60,sortable:true,align:'center',hidden:true">是否默认付款账户</th>
					<th data-options="field:'accountLock',width:80,sortable:true,align:'center',formatter:window.bset.bkatmg.accountLockFormater,styler:window.bset.bkatmg.accountLockStyler">是否锁定</th>
					<th data-options="field:'userId',width:60,align:'center',hidden:true">用户ID</th>
					<th data-options="field:'adminId',width:60,align:'center',hidden:true">管理员</th>
					<th data-options="field:'deptId',width:60,align:'center',hidden:true">所属部门</th>
				</tr>
			</thead>
		</table>
	</div>

</body>
</html>
