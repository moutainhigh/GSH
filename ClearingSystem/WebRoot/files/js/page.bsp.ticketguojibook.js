(function($) {

	$.util.namespace("bsp.ticketguojibook");

	var bspTicketguojibookDg = '#bsp_ticketguojibook_dg', newbtn = '#newticketguojibook';

	/**
	 * datagrid 设置分页栏
	 */
	window.bsp.ticketguojibook.loadPageBar = function() {
		$(bspTicketguojibookDg).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ],
		});
	};
	
	window.bsp.ticketguojibook.amountFormatter = function(value, row, index) {
		return $.formatCurrencySign(value, currency_local);
	};

	
	window.bsp.ticketguojibook.bindButtonEvent = function() {
		
	};


	$(function() {
		window.bsp.ticketguojibook.loadPageBar();
		window.bsp.ticketguojibook.bindButtonEvent();
	});

})(jQuery);