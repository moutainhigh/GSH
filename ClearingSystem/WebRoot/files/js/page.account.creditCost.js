(function($) {

	$.util.namespace("credit.creditCost");
	var creditCreditCostDg = '#credit_creditCost_dg';
	var creditCostSearchGaoji='#creditCost_search_gaoji';
	var creditCostForm='#creditCost_form';
	var creditCostFormClick='#creditCost_form_click';
	
	/**
	 *查询页面 
	 */
	var accountCreditCostForm='#account_creditCost_form';
	var creditCostSearch = '#creditCost_search',creditCostClose='#creditCost_close';
	
	/**
	 * datagrid 设置分页栏
	 */
	window.credit.creditCost.loadPageBar = function() {
		$(creditCreditCostDg).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ],
		});
	};
	
	window.credit.creditCost.amountFormatter=function(value, row, index) {
		return $.formatCurrencySign(value, currency_local);
	};
	
	window.credit.creditCost.grossProfit=function(value, row, index){
		if(row.grossProfit<0){
			return 'color:red;';
		}
	};
	
	/**
	 * 查询信用卡
	 */
	window.credit.creditCost.searchcreditCost = function(){
		$('<div id="creditCost_searchcreditCost_dialog">').dialog({
			title : "查询信用卡成本",
			href : $.util.resolveClientUrl('/cs/page/account/creditCost/searchcreditCost.jsp'),
			width : 320,
			height : 120,
			top : 5,
			draggable : true,
			iconCls : 'bicon-plus',
			resizable : false,
			modal : true,
			closed : true,
			cache : false,
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				/**
				 * 查询
				 */
				$(creditCostSearch).click(function(){
					if (!$(accountCreditCostForm).form('validate')) {
						return;
					}
					var url = "/cs/account/creditCostAction!findAll.action";
					if ($("#creditCost_date_qi").datebox('getValue') != ""&& $("#creditCost_date_qi").datebox('getValue') != undefined) {
						url += "?qiDate="+ $("#creditCost_date_qi").datebox('getValue');
					}
					
					if ($("#creditCost_date_shi").datebox('getValue') != ""&& $("#creditCost_date_shi").datebox('getValue') != undefined) {
						url += "&shiDate="+ $("#creditCost_date_shi").datebox('getValue');
					}
					$(creditCreditCostDg).datagrid({
						url : url
					});
					
					$(creditCreditCostDg).datagrid('clearSelections');
//					if($(creditCreditCostDg).datagrid('getRows')==""){
//						$.messager.show({
//							title : '提示',
//							msg : '未查到相关信息',
//							showType : 'slide'
//						});
//					}
					$("#creditCost_searchcreditCost_dialog").dialog('close');
				});
				
				/**
				 * 关闭
				 */
				$(creditCostClose).click(function(){
					$("#creditCost_searchcreditCost_dialog").dialog('close');
				});
			}
		}).dialog('open');
	};
	
	
	
	/**
	 * 按钮事件绑定
	 */
	window.credit.creditCost.bindButtonEvent = function() {

		/**
		 * 查询
		 */
		$(creditCostSearchGaoji).click(function(){
			window.credit.creditCost.searchcreditCost();
		});
		
	};
	
	$(function() {
		
		window.credit.creditCost.searchcreditCost();
		window.credit.creditCost.loadPageBar();
		window.credit.creditCost.bindButtonEvent();
		//alert($("#credid_qidate").datebox('getValue'));
	});

})(jQuery);