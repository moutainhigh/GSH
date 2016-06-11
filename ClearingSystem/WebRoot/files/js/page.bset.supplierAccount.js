(function($) {

	$.util.namespace("bset.supplierAccount");
	$.util.namespace("bset.supplierAccount.active");
	$.util.namespace("bset.supplierAccount.view");
	var dtgd = '#dtgd', btnView = "#btnView", btnActive = "#btnActive", btnSearch = "#btnSearch";

	window.bset.supplierAccount.loadPageBar = function() {
		$(dtgd).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
		});
	};

	window.bset.supplierAccount.accountStatusFormater = function(value, row, index) {
		if (value == 0) {
			return "未激活";
		} else if (value == 1) {
			return "已激活";
		} else {
			return "未知";
		}
	};

	window.bset.supplierAccount.optionsFormater = function(value, row, index) {
		var options = '<i class="bicon-folder-open" onclick="window.bset.supplierAccount.view.open($.getDatagridRowIndex(this));"></i>';
		if (row.accountStatus == 0) {
			options += ' <i class="fa fa-bolt" onclick="window.bset.supplierAccount.active.open($.getDatagridRowIndex(this));"></i>';
		}
		return options;
	};

	window.bset.supplierAccount.accountBalanceFormater = function(value, row, index) {
		return $.formatCurrency(value);
	};

	window.bset.supplierAccount.accountStatusStyler = function(value, row, index) {
		if (value == 0) {
			return 'background-color:blue;color:#fff;';
		} else if (value == 1) {
			return 'background-color:green;color:#fff;';
		} else {
			return 'background-color:#ccc;color:#000;';
		}
	};

	window.bset.supplierAccount.onSelect = function(index, row) {
		window.bset.supplierAccount.toolBarUpdate(row.accountStatus);
	};

	/**
	 * 工具栏按钮显示影藏
	 */
	window.bset.supplierAccount.toolBarUpdate = function(accountStatus) {
		$(btnActive).linkbutton('disable');
		if (accountStatus == 0) {
			$(btnActive).linkbutton('enable');
		}
	};
	
	window.bset.supplierAccount.load=function(){
		$("#btnView").linkbutton('disable');
		$(btnActive).linkbutton('disable');
	};

	/**
	 * 工具栏按钮事件绑定
	 */
	window.bset.supplierAccount.toolBarBindEvent = function() {
		$(btnActive).click(function() {
			if (!$(btnActive).linkbutton('options').disabled) {
				window.bset.supplierAccount.active.openOnSelect();
			}
		});
		$(btnView).click(function() {
			window.bset.supplierAccount.view.openOnSelect();
		});
		$("#btnSearch").click(function(){
			window.bset.supplierAccount.view.search();
		});
	};
	
	window.bset.supplierAccount.view.search=function(){
		var formData = $.serializeObject($("#supplierAccount_Form"));
		$.post("/cs/bset/CollectpayaccountAction!findAllSearch.action?accountSource=2", formData, function(result) {
			var result = JSON.parse(result);
			if (result.success) {
				$(dtgd).datagrid('loadData',result.obj);
			}else{
				$.messager.show({
					title : '提示',
					msg : result.msg,
					showType : 'slide'
				});
				$(dtgd).datagrid('loadData',{total:0,rows:[]});
			}
			
		});
	};

	/**
	 * 打开激活窗口
	 * 
	 * @param index
	 */
	window.bset.supplierAccount.active.open = function(index) {
		$('<div id="dlgActive">').dialog({
			title : "激活账户",
			href : $.util.resolveClientUrl('/cs/page/bset/supplierAccount/active.jsp'),
			width : 270,
			height : 180,
			top : 5,
			iconCls : 'bicon-asterisk',
			resizable : false,
			modal : true,
			closed : true,
			cache : false,
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				window.bset.supplierAccount.active.init(index);
			}
		}).dialog('open');
	};

	/**
	 * 打开激活窗口
	 * 
	 */
	window.bset.supplierAccount.active.openOnSelect = function() {
		var index = $(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要激活的记录！',
				showType : 'slide'
			});
			return;
		}
		window.bset.supplierAccount.active.open(index);
	};

	/**
	 * 关闭激活窗口
	 * 
	 */
	window.bset.supplierAccount.active.close = function() {
		$('#dlgActive').dialog('close');
	};

	/**
	 * active 初始化
	 * 
	 * @param index
	 */
	window.bset.supplierAccount.active.init = function(index) {
		$('#form').form('load', $(dtgd).datagrid('getRows')[index]);
		$('#btnSave').click(function() {
			window.bset.supplierAccount.active.save(index);
		});
		$('#btnClose').click(function() {
			window.bset.supplierAccount.active.close();
		});
	};

	/**
	 * active Save
	 * 
	 * @param index
	 */
	window.bset.supplierAccount.active.save = function(index) {
		var formData = $.serializeObject($("#form"));
		formData.id = $(dtgd).datagrid('getRows')[index].id;
		$.post("/cs/bset/CollectpayaccountAction!update.action", formData, function(result) {
			var result = JSON.parse(result);
			if (result.success) {
				$(dtgd).datagrid('updateRow', {
					index : index,
					row : result.obj
				});
				window.bset.supplierAccount.active.close();
				window.bset.supplierAccount.toolBarUpdate(1);
			}
			$.messager.show({
				title : '提示',
				msg : result.msg,
				showType : 'slide'
			});
			
		});
	};

	/**
	 * 查看账户信息
	 * 
	 * @param index
	 */
	window.bset.supplierAccount.view.open = function(index) {
		$('<div id="dlgView">').dialog({
			title : "账户详细信息",
			href : $.util.resolveClientUrl('/cs/page/bset/supplierAccount/view.jsp'),
			width : 800,
			height : 470,
			top : 5,
			iconCls : 'bicon-asterisk',
			resizable : false,
			modal : true,
			closed : true,
			cache : false,
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				window.bset.supplierAccount.view.init(index);
			}
		}).dialog('open');
	};

	window.bset.supplierAccount.view.openOnSelect = function() {
		var index = $(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要显示的记录！',
				showType : 'slide'
			});
			return;
		}
		window.bset.supplierAccount.view.open(index);
	};

	/**
	 * 关闭显示窗口
	 * 
	 */
	window.bset.supplierAccount.view.close = function() {
		$('#dlgView').dialog('close');
	};

	/**
	 * view 初始化
	 * 
	 * @param index
	 */
	window.bset.supplierAccount.view.init = function(index) {
		var result = eval("(" + $.ajax( {
			url : "/cs/bset/CollectpayaccountAction!findSupplier.action",
			dataType : "json/xml/html",
			data : {
				"accountCode" : $(dtgd).datagrid('getRows')[index].accountCode
			},
			async : false,
			cache : false,
			type : "post"
			}).responseText + ")");
		var result1 = eval("(" + $.ajax( {
			url : "/cs/bset/CollectpayaccountAction!findBankAccountNo.action",
			dataType : "json/xml/html",
			data : {
				"accountCode" : $(dtgd).datagrid('getRows')[index].accountCode
			},
			async : false,
			cache : false,
			type : "post"
			}).responseText + ")");
		var json=JSON.parse(result1.obj);
		$("#AccountNoDatagrid").datagrid({    
		    data:json,  
		});  


		
		var form_data = {
				cname : result.obj.cname,
				companyNo : result.obj.companyNo,
				shortCode : result.obj.shortCode,
				tel : result.obj.tel,
				fax : result.obj.fax,
				zipCode : result.obj.zipCode,
				supplierType : result.obj.supplierType,
				supplierLevel : result.obj.supplierLevel,
				province : result.obj.province,
				city : result.obj.city,
				address : result.obj.address,
				lr : result.obj.lr,
				lrAddress : result.obj.lrAddress,
				contacter1 : result.obj.contacter1,
				tel1 : result.obj.tel1,
				contacter2 : result.obj.contacter2,
				tel2 : result.obj.tel2,
				linkMan : result.obj.linkMan,
				jsType : result.obj.jsType,
				monthDay : result.obj.monthDay,
				quota : result.obj.quota,
				payType : result.obj.payType,
				currency : result.obj.currency,
				keepNum : result.obj.keepNum,
				carryType : result.obj.carryType,
				financeRemark : result.obj.financeRemark,
			};
		$('#form1').form('load', form_data);
		
		if($("#supplierLevel").val()==1){
			$("#supplierLevel").val("战略供应商");
		}else if($("#supplierLevel").val()==2){
			$("#supplierLevel").val("优先供应商");
		}else if($("#supplierLevel").val()==3){
			$("#supplierLevel").val("普通供应商");
		}else if($("#supplierLevel").val()==4){
			$("#supplierLevel").val("淘汰供应商");
		}else if($("#supplierLevel").val()==5){
			$("#supplierLevel").val("身份未定");
		}
		
		if($("#s_jsType").val()==1){
			$("#s_jsType").val("自然月");
		}else{
			$("#s_jsType").val("固定天");
		}
		
		if($("#s_payType").val()==1){
			$("#s_payType").val("转账");
		}else if($("#s_payType").val()==2){
			$("#s_payType").val("汇款");
		}else if($("#s_payType").val()==3){
			$("#s_payType").val("现金");
		}else if($("#s_payType").val()==4){
			$("#s_payType").val("信用卡");
		}else if($("#s_payType").val()==5){
			$("#s_payType").val("支票");
		}
		
		if($("#s_keepNum").val()==0){
			$("#s_keepNum").val("元");
		}else if($("#s_keepNum").val()==1){
			$("#s_keepNum").val("角");
		}else if($("#s_keepNum").val()==2){
			$("#s_keepNum").val("分");
		}
		
		if($("#s_carryType").val()==1){
			$("#s_carryType").val("四舍五入");
		}else if($("#s_carryType").val()==2){
			$("#s_carryType").val("逢一进十");
		}else if($("#s_carryType").val()==3){
			$("#s_carryType").val("舍去余数");
		}
		
		$('#btnClose').click(function() {
			window.bset.supplierAccount.view.close();
		});
	};

	$(function() {
		window.bset.supplierAccount.load();
		window.bset.supplierAccount.loadPageBar();
		window.bset.supplierAccount.toolBarBindEvent();
	});

})(jQuery);