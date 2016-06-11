(function($) {

	$.util.namespace("account.orderAbnormal");
	var accountOrderAbnormalDg = '#account_orderAbnormal_dg';
	/**
	 * datagrid 设置分页栏
	 */
	window.account.orderAbnormal.loadPageBar = function() {
		$(accountOrderAbnormalDg).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ],
		});
	};
	
	window.account.orderAbnormal.amountFormatter = function(value, row, index) {
		if(row.currencyType==undefined){
			return $.formatCurrencySign(value, "CNY");
		}
		return $.formatCurrencySign(value, row.currencyType);
	};
	
//	window.account.orderAbnormal.onLoadSuccess = function(data) {
//		var sumfootertable = '<table class="sum-footer">';
//		var row1 = '<tr><th></th>';
//		var row2 = '<tr><th>小计：</th>';
//		var row3 = '<tr><th>总计：</th>';
//		var i = 1;
//		for ( var item in data.sumfooter) {
//			row1 += '<th class="sum-footer-' + i + ' sum-footer-left">实付金额（' + item + '）</th>';
//			row3 += '<td class="sum-footer-' + i + ' sum-footer-left">' + data.sumfooter[item][0].balance + '</td>';
//			if (data.sumfooter[item][1]) {
//				row2 += '<td class="sum-footer-' + i + ' sum-footer-left">' + data.sumfooter[item][1].balance + '</td>';
//			} else {
//				row2 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + 0.00 + '</td>';
//			}
//			if (i = 1)
//				i++;
//			else
//				i--;
//		}
//		sumfootertable += row1 + '</tr>' + row2 + '</tr>' + row3 + '</tr></table>';
//		$('#sum_footer').html(sumfootertable);
//	};
	
	$(function() {
		window.account.orderAbnormal.loadPageBar();
	});

})(jQuery);