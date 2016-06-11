(function($) {

	$.util.namespace("bsp.ticketguoneibook");

	var bspTicketguoneibookDg = '#bsp_ticketguoneibook_dg', newbtn = '#newticketguoneibook';

	/**
	 * datagrid 设置分页栏
	 */
	window.bsp.ticketguoneibook.loadPageBar = function() {
		$(bspTicketguoneibookDg).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ],
		});
	};
	
	window.bsp.ticketguoneibook.amountFormatter = function(value, row, index) {
		return $.formatCurrencySign(value, currency_local);
	};
	
	window.bsp.ticketguoneibook.bindButtonEvent = function() {
		$("#btSearch").click(function(){
			if (!$("#bspform").form('validate')) {
				return;
			}
			var url = "/cs/bsp/baspTicketUseAction!findGuonei.action";
			if ($("#bspType").combobox('getValue') != ""&& $("#bspType").combobox('getValue') != undefined) {
				url += "?type="+ $("#bspType").combobox('getValue');
			}
			if ($("#drawerDay").datebox('getValue') != ""&& $("#drawerDay").datebox('getValue') != undefined) {
				url += "&date="+ $("#drawerDay").datebox('getValue');
			}
			$(bspTicketguoneibookDg).datagrid({
				url : url
			});
		});
	};
	
	

	$(function() {
		
		var date1 = new Date();
		var d1 = date1.getMonth() + 1;
		var date1 = date1.getFullYear() + '-' + d1
				+ '-' + date1.getDate();
		
		$("#drawerDay").datebox('setValue', date1);
		
		window.bsp.ticketguoneibook.loadPageBar();
		window.bsp.ticketguoneibook.bindButtonEvent();
	});

})(jQuery);