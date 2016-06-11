(function($) {

	$.util.namespace("bsp.advanceEntry");
	var bspAdvanceEntryDg = '#bsp_advanceEntry_dg';
	
	/**
	 * datagrid 设置分页栏
	 */
	window.bsp.advanceEntry.loadPageBar = function() {
		$(bspAdvanceEntryDg).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ],
		});
	};
	
//	window.bsp.advanceEntry.amountFormatter = function(value, row, index) {
//		return $.formatCurrencySign(value, row.currencyType);
//	};
//
//	window.bsp.advanceEntry.onLoadSuccess = function(data) {
//		var sumfootertable = '<table class="sum-footer">';
//		var row1 = '<tr><th></th>';
//		var row2 = '<tr><th>小计：</th>';
//		var row3 = '<tr><th>总计：</th>';
//		var i = 1;
//		for ( var item in data.sumfooter) {
//			row1 += '<th class="sum-footer-' + i + ' sum-footer-left">金额 </th>';
//			row1 += '<th class="sum-footer-' + i + ' ">发票总额 （' + item + '）</th>';
//			row3 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + data.sumfooter[item][0].amountMoney + '</td>';
//			row3 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][0].invoiceMoney + '</td>';
//			if (data.sumfooter[item][1]) {
//				row2 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + data.sumfooter[item][1].amountMoney + '</td>';
//				row2 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][1].invoiceMoney + '</td>';
//			} else {
//				row2 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + 0.00 + '</td>';
//				row2 += '<td class="sum-footer-' + i + ' ">' + 0.00 + '</td>';
//			}
//			if (i = 1)
//				i++;
//			else
//				i--;
//		}
//		sumfootertable += row1 + '</tr>' + row2 + '</tr></table>';
//		$('#sum_footer').html(sumfootertable);
//	};
	
	window.bsp.advanceEntry.status=function(value, row, index){
		if(value==1){
			return '已完成';
		}else if(value==2){
			return '未完成';
		}
	};
	
	window.bsp.advanceEntry.voucherType=function(value, row, index){
		if(value==1){
			return '发票';
		}else if(value==2){
			return '行程单';
		}
	};
	
	window.bsp.advanceEntry.transactionType=function(value, row, index){
		if(value==1){
			return '支出';
		}else if(value==2){
			return '收入';
		}
	};
	
	/**
	 * 按钮事件绑定
	 */
	window.bsp.advanceEntry.bindButtonEvent = function() {
		
	};
	
	$(function() {
		window.bsp.advanceEntry.loadPageBar();
		window.bsp.advanceEntry.bindButtonEvent();
		//alert($("#credid_qidate").datebox('getValue'));
	});

})(jQuery);