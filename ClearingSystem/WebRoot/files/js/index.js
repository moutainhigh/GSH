(function($) {

	$.util.namespace("indexpage");

	var menus = [ {
		title : "基本设置",
		url : "",
		icon : "bicon-cog",
		childs : [ {
			title : "资金账户管理",
			url : "page/bset/bkatmg.jsp",
			icon : "bicon-bell"
		}, {
			title : "应收账户",
			url : "page/bset/companyAccount.jsp",
			icon : "bicon-align-right"
		}, {
			title : "应付账户",
			url : "page/bset/supplierAccount.jsp",
			icon : "bicon-align-left"
		}, {
			title : "结算汇率",
			url : "page/bset/exchangeRates.jsp",
			icon : "bicon-repeat"
		}, {
			title : "信用卡手续费",
			url : "page/bset/creditCardFee.jsp",
			icon : "bicon-certificate"
		}, {
			title : "数据范围设置",
			url : "page/bset/dataPermission.jsp",
			icon : "bicon-cog"
		} ]
	}, {
		title : "票证管理",
		url : "",
		icon : "bicon-hdd",
		childs : [ {
			title : "BSP票本管理",
			url : "page/bsp/ticketbook.jsp",
			icon : "bicon-magnet"
		} , {
			title : "BSP票本使用记录",
			url : "page/bsp/ticketguoneibook.jsp",
			icon : "bicon-magnet"
		} 
//		, {
//			title : "BSP票本国际使用记录",
//			url : "page/bsp/ticketguojibook.jsp",
//			icon : "bicon-magnet"
//		} 
		, {
			title : "凭证录入",
			url : "page/bsp/advanceEntry.jsp",
			icon : "bicon-magnet"
		} , {
			title : "历史退换",
			url : "page/bsp/returnHistory.jsp",
			icon : "bicon-magnet"
		} ]
	}, {
		title : "应收账款-A/R管理",
		url : "",
		icon : "bicon-download",
		childs : [
//		 {
//			title : "应收账款账期提醒",
//			url : "page/ar-mgt/receivableRemind.jsp",
//			icon : "bicon-align-right"
//		},
		{
			title : "客户未销账账款",
			url : "page/ar-mgt/receivable.jsp",
			icon : "bicon-align-right"
		}, {
			title : "客户退款申请单",
			url : "page/ar-mgt/RefundApplication.jsp",
			icon : "bicon-book"
		}, {
			title : "客户销账明细",
			url : "page/ar-mgt/CompanyDetailAccount.jsp",
			icon : "bicon-book"
		} ]
	}, {
		title : "应付账款-A/P管理",
		url : "",
		icon : "bicon-download",
		childs : [ 
//		{
//			title : "应付账款提醒",
//			url : "page/ap-mgt/waitPaymentRemind.jsp",
//			icon : "bicon-align-right"
//		},
		{
			title : "供应商未销账账款",
			url : "page/ap-mgt/waitPayment.jsp",
			icon : "bicon-align-right"
		}, {
			title : "供应商暂支款",
			url : "page/ap-mgt/advance.jsp",
			icon : "bicon-align-right"
		}, {
			title : "供应商未录入凭证",
			url : "page/ap-mgt/Noentry.jsp",
			icon : "bicon-align-right"
		}, {
			title : "供应商付款申请单",
			url : "page/ap-mgt/applyPayment.jsp",
			icon : "bicon-book"
		}, {
			title : "供应商销账明细",
			url : "page/ap-mgt/paymentDetailed.jsp",
			icon : "bicon-book"
		}, {
			title : "预算",
			url : "page/ap-mgt/paymentBudget.jsp",
			icon : "bicon-book"
		} ]
	}, {
		title : "账户管理",
		url : "",
		icon : "bicon-leaf",
		childs : [ {
			title : "异常订单查阅",
			url : "page/account/orderAbnormal.jsp",
			icon : "bicon-globe"
		}, {
			title : "信用卡到账",
			url : "page/account/creditCardToAccount.jsp",
			icon : "bicon-user"
		}, {
			title : "账目复核",
			url : "page/account/accountToreview.jsp",
			icon : "bicon-user"
		}, {
			title : "收款付款记录",
			url : "page/account/collectPayRecord.jsp",
			icon : "bicon-briefcase"
		}, {
			title : "或得佣金",
			url : "page/account/commission.jsp",
			icon : "bicon-fire"
		}, {
			title : "信用卡成本",
			url : "page/account/creditCost.jsp",
			icon : "bicon-fire"
		} ]
	} ];

	var indexTabs = "#index_tabs", fullScreenBtn = "#full_screen_btn", userLockBtn = "#user_lock_btn", userLockDialog = "#user_lock_dialog";

	/**
	 * 新建TAB选项卡
	 */
	window.indexpage.newTab = function(opt) {
		opt = $.extend({
			title : '',
			icon : '',
			href : ''
		}, opt || {});
		var tab = $(indexTabs).tabs('exists', opt.title);
		if (tab) {
			$(indexTabs).tabs('select', opt.title);// 选中选项卡
			return;
		}
		$(indexTabs).tabs("add", {
			title : opt.title,
			closable : true,
			iconCls : opt.icon,
			bodyCls : "index-tab-body",
			content : '<iframe src="' + opt.href + '" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>'
		});
	};

	/**
	 * 加载菜单栏
	 */
	window.indexpage.loadMenu = function() {
		for ( var m = 0; m < menus.length; m++) {
			var content = '';
			for ( var i = 0; i < menus[m].childs.length; i++) {
				var item = menus[m].childs[i];
				if(JBSZZJZHGL==true&&item.title=='资金账户管理'){
					content += '<div class="menu-bar"><a onclick="window.indexpage.MenuBarClick(this);" mlink="' + item.url + '" mtitle="' + item.title + '" micon="' + item.icon + '"><i class="' + item.icon + ' bicon-white"></i>&nbsp;&nbsp;&nbsp;' + item.title + '</a></div>';
				}
				if(JBSZYSZH==true&&item.title=='应收账户'){
					content += '<div class="menu-bar"><a onclick="window.indexpage.MenuBarClick(this);" mlink="' + item.url + '" mtitle="' + item.title + '" micon="' + item.icon + '"><i class="' + item.icon + ' bicon-white"></i>&nbsp;&nbsp;&nbsp;' + item.title + '</a></div>';
				}
				if(JBSZYFZH==true&&item.title=='应付账户'){
					content += '<div class="menu-bar"><a onclick="window.indexpage.MenuBarClick(this);" mlink="' + item.url + '" mtitle="' + item.title + '" micon="' + item.icon + '"><i class="' + item.icon + ' bicon-white"></i>&nbsp;&nbsp;&nbsp;' + item.title + '</a></div>';
				}
				if(JBSZJSHL==true&&item.title=='结算汇率'){
					content += '<div class="menu-bar"><a onclick="window.indexpage.MenuBarClick(this);" mlink="' + item.url + '" mtitle="' + item.title + '" micon="' + item.icon + '"><i class="' + item.icon + ' bicon-white"></i>&nbsp;&nbsp;&nbsp;' + item.title + '</a></div>';
				}
				if(JBSZXYKSXF==true&&item.title=='信用卡手续费'){
					content += '<div class="menu-bar"><a onclick="window.indexpage.MenuBarClick(this);" mlink="' + item.url + '" mtitle="' + item.title + '" micon="' + item.icon + '"><i class="' + item.icon + ' bicon-white"></i>&nbsp;&nbsp;&nbsp;' + item.title + '</a></div>';
				}
				if(JBSZSJFWSZ==true&&item.title=='数据范围设置'){
					content += '<div class="menu-bar"><a onclick="window.indexpage.MenuBarClick(this);" mlink="' + item.url + '" mtitle="' + item.title + '" micon="' + item.icon + '"><i class="' + item.icon + ' bicon-white"></i>&nbsp;&nbsp;&nbsp;' + item.title + '</a></div>';
				}
				if((p2==true&&menus[m].title=='票证管理')||(p3==true&&menus[m].title=='应收账款-A/R管理')||(p4==true&&menus[m].title=='应付账款-A/P管理')){
					content += '<div class="menu-bar"><a onclick="window.indexpage.MenuBarClick(this);" mlink="' + item.url + '" mtitle="' + item.title + '" micon="' + item.icon + '"><i class="' + item.icon + ' bicon-white"></i>&nbsp;&nbsp;&nbsp;' + item.title + '</a></div>';
				}
				if(ZHGLYCDDCX==true&&item.title=='异常订单查阅'){
					content += '<div class="menu-bar"><a onclick="window.indexpage.MenuBarClick(this);" mlink="' + item.url + '" mtitle="' + item.title + '" micon="' + item.icon + '"><i class="' + item.icon + ' bicon-white"></i>&nbsp;&nbsp;&nbsp;' + item.title + '</a></div>';
				}
				if(ZHGLXYKDZ==true&&item.title=='信用卡到账'){
					content += '<div class="menu-bar"><a onclick="window.indexpage.MenuBarClick(this);" mlink="' + item.url + '" mtitle="' + item.title + '" micon="' + item.icon + '"><i class="' + item.icon + ' bicon-white"></i>&nbsp;&nbsp;&nbsp;' + item.title + '</a></div>';
				}
				if(ZHGLZMFH==true&&item.title=='账目复核'){
					content += '<div class="menu-bar"><a onclick="window.indexpage.MenuBarClick(this);" mlink="' + item.url + '" mtitle="' + item.title + '" micon="' + item.icon + '"><i class="' + item.icon + ' bicon-white"></i>&nbsp;&nbsp;&nbsp;' + item.title + '</a></div>';
				}
				if(ZHGLSFKJL==true&&item.title=='收款付款记录'){
					content += '<div class="menu-bar"><a onclick="window.indexpage.MenuBarClick(this);" mlink="' + item.url + '" mtitle="' + item.title + '" micon="' + item.icon + '"><i class="' + item.icon + ' bicon-white"></i>&nbsp;&nbsp;&nbsp;' + item.title + '</a></div>';
				}
				if(ZHGLHDYJ==true&&item.title=='或得佣金'){
					content += '<div class="menu-bar"><a onclick="window.indexpage.MenuBarClick(this);" mlink="' + item.url + '" mtitle="' + item.title + '" micon="' + item.icon + '"><i class="' + item.icon + ' bicon-white"></i>&nbsp;&nbsp;&nbsp;' + item.title + '</a></div>';
				}
				if(ZHGLXYKCB==true&&item.title=='信用卡成本'){
					content += '<div class="menu-bar"><a onclick="window.indexpage.MenuBarClick(this);" mlink="' + item.url + '" mtitle="' + item.title + '" micon="' + item.icon + '"><i class="' + item.icon + ' bicon-white"></i>&nbsp;&nbsp;&nbsp;' + item.title + '</a></div>';
				}
			}
			if(p1==true&&menus[m].title=='基本设置'){
				$("#index_menu").accordion("add", {
					title : menus[m].title,
					selected : false,
					content : content,
					iconCls : menus[m].icon + " bicon-white",
					bodyCls : "menu-item-body"
				});
			}
			if(p2==true&&menus[m].title=='票证管理'){
				$("#index_menu").accordion("add", {
					title : menus[m].title,
					selected : false,
					content : content,
					iconCls : menus[m].icon + " bicon-white",
					bodyCls : "menu-item-body"
				});
			}
			if(p3==true&&menus[m].title=='应收账款-A/R管理'){
				$("#index_menu").accordion("add", {
					title : menus[m].title,
					selected : false,
					content : content,
					iconCls : menus[m].icon + " bicon-white",
					bodyCls : "menu-item-body"
				});
			}
			if(p4==true&&menus[m].title=='应付账款-A/P管理'){
				$("#index_menu").accordion("add", {
					title : menus[m].title,
					selected : false,
					content : content,
					iconCls : menus[m].icon + " bicon-white",
					bodyCls : "menu-item-body"
				});
			}
			if(p5==true&&menus[m].title=='账户管理'){
				$("#index_menu").accordion("add", {
					title : menus[m].title,
					selected : false,
					content : content,
					iconCls : menus[m].icon + " bicon-white",
					bodyCls : "menu-item-body"
				});
			}
			
		}
//		$("#index_menu").accordion("select", 0);
		$("#index_menu").accordion("unselect", 0);
	};

	/**
	 * 菜单栏点击
	 */
	window.indexpage.MenuBarClick = function(target) {
		window.indexpage.newTab({
			title : $(target).attr("mtitle"),
			href : $(target).attr("mlink"),
			icon : $(target).attr("micon")
		});
	};

	window.indexpage.bindUserMenuButtonEvent = function() {
		$(fullScreenBtn).click(function() {
			if ($.util.supportsFullScreen) {
				if ($.util.isFullScreen()) {
					$.util.cancelFullScreen();
				} else {
					$.util.requestFullScreen();
				}
			} else {
				$.easyui.messager.show("当前浏览器不支持全屏 API，请更换至最新的 Chrome/Firefox/Safari 浏览器或通过 F11 快捷键进行操作。");
			}
		});

		$(userLockBtn).click(function() {
		});

	};

	$(function() {
		window.indexpage.loadMenu();
		window.indexpage.bindUserMenuButtonEvent();
	});

})(jQuery);