(function($) {

	$.util.namespace("apmgt.paymentBudget");

	var apmgtPaymentBudgetDg = '#apmgt_paymentBudget_dg';

	/**
	 * datagrid 设置分页栏
	 */
	window.apmgt.paymentBudget.loadPageBar = function() {
//		$(apmgtPaymentBudgetDg).datagrid('getPager').pagination({
//			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ],
//		});
	};
	
	window.apmgt.paymentBudget.onLoadSuccess = function(data) {
		var sumfootertable = '<table class="sum-footer">';
		var row1 = '<tr><th></th>';
		var row2 = '<tr><th>小计：</th>';
		var row3 = '<tr><th>总计：</th>';
		var i = 1;
		for ( var item in data.sumfooter) {
			row1 += '<th class="sum-footer-' + i + ' sum-footer-left">实付金额（' + item + '）</th>';
			row3 += '<td class="sum-footer-' + i + ' sum-footer-left">' + data.sumfooter[item][0].payBeAmount + '</td>';
			if (data.sumfooter[item][1]) {
				row2 += '<td class="sum-footer-' + i + ' sum-footer-left">' + data.sumfooter[item][1].payBeAmount + '</td>';
			} else {
				row2 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + 0.00 + '</td>';
			}
			if (i = 1)
				i++;
			else
				i--;
		}
		sumfootertable += row1 + '</tr>' + row2 + '</tr>' + row3 + '</tr></table>';
		$('#sum_footer').html(sumfootertable);
	};
	
	window.apmgt.paymentBudget.amountFormatter = function(value, row, index) {
		return $.formatCurrencySign(value, row.currencyType);
	};
	
	window.apmgt.paymentBudget.bindButtonEvent = function() {
		$("#pbSearch").click(function(){
			var result = eval("("+ $.ajax({
				url : "/cs/apmgt/paymentBudgetAction!findAllSearch.action",
				dataType : "json/xml/html",
				data : {
					"tradeDate" : $("#tradeDate").datebox('getValue'),
					"supplier" : $("#supplierType").combobox('getValue'),
					"supplierName" : $("#supplierType").combobox('getText')
				},
				async : false,
				cache : false,
				type : "post"
			}).responseText + ")");
			if (result.success) {
				$('#apmgt_paymentBudget_dg').datagrid('loadData',result.obj);
			}

			
		});
	};
	
	

	$(function() {
		window.apmgt.paymentBudget.loadPageBar();
		window.apmgt.paymentBudget.bindButtonEvent();
	});
})(jQuery);