(function($) {

	$.util.namespace("bset.companyAccount");
	$.util.namespace("bset.companyAccount.active");
	$.util.namespace("bset.companyAccount.view");
	var dtgd = '#dtgd', btnView = "#btnView", btnActive = "#btnActive", btnSearch = "#btnSearch";

	window.bset.companyAccount.loadPageBar = function() {
		$(dtgd).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
		});
	};

	window.bset.companyAccount.accountStatusFormater = function(value, row, index) {
		if (value == 0) {
			return "未激活";
		} else if (value == 1) {
			return "已激活";
		} else {
			return "未知";
		}
	};

	window.bset.companyAccount.optionsFormater = function(value, row, index) {
		var options = '<i class="bicon-folder-open" onclick="window.bset.companyAccount.view.open($.getDatagridRowIndex(this));"></i>';
		if (row.accountStatus == 0) {
			options += ' <i class="fa fa-bolt" onclick="window.bset.companyAccount.active.open($.getDatagridRowIndex(this));"></i>';
		}
		return options;
	};

	window.bset.companyAccount.accountBalanceFormater = function(value, row, index) {
		return $.formatCurrency(value);
	};

	window.bset.companyAccount.accountStatusStyler = function(value, row, index) {
		if (value == 0) {
			return 'background-color:blue;color:#fff;';
		} else if (value == 1) {
			return 'background-color:green;color:#fff;';
		} else {
			return 'background-color:#ccc;color:#000;';
		}
	};

	window.bset.companyAccount.onSelect = function(index, row) {
		window.bset.companyAccount.toolBarUpdate(row.accountStatus);
	};

	/**
	 * 工具栏按钮显示影藏
	 */
	window.bset.companyAccount.toolBarUpdate = function(accountStatus) {
		$(btnActive).linkbutton('disable');
		$("#btnView").linkbutton('enable');
		if (accountStatus == 0) {
			$(btnActive).linkbutton('enable');
		}
	};
	 
	window.bset.companyAccount.load=function(){
		$("#btnView").linkbutton('disable');
		$(btnActive).linkbutton('disable');
	};

	/**
	 * 工具栏按钮事件绑定
	 */
	window.bset.companyAccount.toolBarBindEvent = function() {
		$(btnActive).click(function() {
			if (!$(btnActive).linkbutton('options').disabled) {
				window.bset.companyAccount.active.openOnSelect();
			}
		});
		$(btnView).click(function() {
			window.bset.companyAccount.view.openOnSelect();
		});
		$("#btnSearch").click(function(){
			window.bset.companyAccount.view.search();
		});
	};
	
	window.bset.companyAccount.view.search=function(){
		var formData = $.serializeObject($("#companyAccount_Form"));
		$.post("/cs/bset/CollectpayaccountAction!findAllSearch.action?accountSource=1", formData, function(result) {
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
	window.bset.companyAccount.active.open = function(index) {
		$('<div id="dlgActive">').dialog({
			title : "激活账户",
			href : $.util.resolveClientUrl('/cs/page/bset/companyAccount/active.jsp'),
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
				window.bset.companyAccount.active.init(index);
			}
		}).dialog('open');
	};

	/**
	 * 打开激活窗口
	 * 
	 */
	window.bset.companyAccount.active.openOnSelect = function() {
		var index = $(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要激活的记录！',
				showType : 'slide'
			});
			return;
		}
		window.bset.companyAccount.active.open(index);
	};

	/**
	 * 关闭激活窗口
	 * 
	 */
	window.bset.companyAccount.active.close = function() {
		$('#dlgActive').dialog('close');
	};
	
	

	/**
	 * active 初始化
	 * 
	 * @param index
	 */
	window.bset.companyAccount.active.init = function(index) {
		$('#form').form('load', $(dtgd).datagrid('getRows')[index]);
		$('#btnSave').click(function() {
			window.bset.companyAccount.active.save(index);
		});
		$('#btnClose').click(function() {
			window.bset.companyAccount.active.close();
		});
		
	};

	/**
	 * active Save
	 * 
	 * @param index
	 */
	window.bset.companyAccount.active.save = function(index) {
		var formData = $.serializeObject($("#form"));
		formData.id = $(dtgd).datagrid('getRows')[index].id;
		$.post("/cs/bset/CollectpayaccountAction!update.action", formData, function(result) {
			var result = JSON.parse(result);
			if (result.success) {
				$(dtgd).datagrid('updateRow', {
					index : index,
					row : result.obj
				});
				window.bset.companyAccount.active.close();
				window.bset.companyAccount.toolBarUpdate(1);
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
	window.bset.companyAccount.view.open = function(index) {
		$('<div id="dlgView">').dialog({
			title : "账户详细信息",
			href : $.util.resolveClientUrl('/cs/page/bset/companyAccount/view.jsp'),
			width : 600,
			height : 520,
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
				window.bset.companyAccount.view.init(index);
			}
		}).dialog('open');
	};

	window.bset.companyAccount.view.openOnSelect = function() {
		var index = $(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要显示的记录！',
				showType : 'slide'
			});
			return;
		}
		window.bset.companyAccount.view.open(index);
	};
	

	/**
	 * 关闭显示窗口
	 * 
	 */
	window.bset.companyAccount.view.close = function() {
		$('#dlgView').dialog('close');
	};

	/**
	 * view 初始化
	 * 
	 * @param index
	 */
	window.bset.companyAccount.view.init = function(index) {
		var result = eval("(" + $.ajax( {
			url : "/cs/bset/CollectpayaccountAction!findSee.action",
			dataType : "json/xml/html",
			data : {
				"cpCode" : $(dtgd).datagrid('getRows')[index].accountCode
			},
			async : false,
			cache : false,
			type : "post"
			}).responseText + ")");
		var form_data = {
				cpName : result.obj.cpName,
				cpAbbreviation : result.obj.cpAbbreviation,
				cpType:result.obj.cpType,
				cpTypeExtend:result.obj.cpTypeExtend,
				cpCode : result.obj.cpCode,
				cpStatus : result.obj.cpStatus,
				cpIndustry : result.obj.cpIndustry,
				cpScale : result.obj.cpScale,
				cpTelephone : result.obj.cpTelephone,
				cpHome : result.obj.cpHome,
				cpFax : result.obj.cpFax,
				cpZipCode : result.obj.cpZipCode,
				cpProvinces : result.obj.cpProvinces,
				cpCity : result.obj.cpCity,
				cpAddress : result.obj.cpAddress,
				cpLrName : result.obj.cpLrName,
				lxr1 : result.obj.lxr1,
				lxr2 : result.obj.lxr2,
				lxr3 : result.obj.lxr3,
				lxr4 : result.obj.lxr4,
				lxr5 : result.obj.lxr5,
				lxr6 : result.obj.lxr6,
				cpValid : result.obj.cpValid,
				cpLogo : result.obj.cpLogo,
				cpYinYeZhiZhao : result.obj.cpYinYeZhiZhao,
				
				cpCaiWuBuLxrName : result.obj.cpCaiWuBuLxrName,
				cpJieSuanType : result.obj.cp_Calculate_type,
				cpdayOrMonth : result.obj.cp_DayOrMonth,
				cpQuota : result.obj.cp_Quota,
				cpPayWay : result.obj.cp_PayWay,
				cpSettlementStartDate : result.obj.cp_SettlementStartDate,
				cpSettlementEndDate : result.obj.cp_SettlementEndDate,
				cpCurrency : result.obj.cp_Currency,
				cpExchangeRate : result.obj.cp_ExchangeRate,
				cpDzDate : result.obj.cp_DzDate,
				cpCzDate : result.obj.cp_CzDate,
				cpRemittanceDate : result.obj.cp_RemittanceDate,
				cpDzDayCount : result.obj.cpDzDayCount,//未知字段
				cpJieSuanAccount : result.obj.cp_SettlementAccount,
				cpTuiKuanAccount : result.obj.cp_RefundAccount,
				cpCaiWuRemark : result.obj.cp_FinanceRemark
			};
		$('#form_khinfo').form('load', form_data);
		$('#btnClose').click(function() {
			window.bset.companyAccount.view.close();
		});
	};

	$(function() {
		window.bset.companyAccount.load();
		window.bset.companyAccount.loadPageBar();
		window.bset.companyAccount.toolBarBindEvent();
	});

})(jQuery);