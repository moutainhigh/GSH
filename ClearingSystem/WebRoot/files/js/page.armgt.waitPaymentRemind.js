(function($) {

	$.util.namespace("armgt.waitPaymentRemind");
	

	
	/**
	 * datagrid 设置分页栏
	 */
	window.armgt.waitPaymentRemind.loadPageBar = function() {
		$("#dtgd").datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ],
		});
	};


	window.armgt.waitPaymentRemind.amountFormatter = function(value, row, index) {
		return $.formatCurrencySign(value, row.currencyType);
	};
	
	/**
	 * 按钮事件绑定
	 */
	window.armgt.waitPaymentRemind.bindButtonEvent = function() {
		
	};
	
	window.armgt.waitPaymentRemind.styler=function(index,row){
		var date1=new Date();
		var d1=date1.getMonth()+1;
		var date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
		if(row.deadline<date1){
			return 'background-color:red';  
		}
	};
	
	window.armgt.waitPaymentRemind.onLoadSuccess = function(data) {
		var sumfootertable = '<table class="sum-footer">';
		var row1 = '<tr><th></th>';
		var row2 = '<tr><th>小计：</th>';
		var row3 = '<tr><th>总计：</th>';
		var i = 1;
		for ( var item in data.sumfooter) {
			row1 += '<th class="sum-footer-' + i + ' sum-footer-left">应付金额 </th>';
			row1 += '<th class="sum-footer-' + i + ' ">已付金额 </th>';
			row1 += '<th class="sum-footer-' + i + ' ">余额（' + item + '）</th>';
			row3 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + data.sumfooter[item][0].payBeAmount + '</td>';
			row3 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][0].payAmount + '</td>';
			row3 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][0].balance + '</td>';
			if (data.sumfooter[item][1]) {
				row2 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + data.sumfooter[item][1].payBeAmount + '</td>';
				row2 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][1].payAmount + '</td>';
				row2 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][1].balance + '</td>';
			} else {
				row2 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + 0.00 + '</td>';
				row2 += '<td class="sum-footer-' + i + ' ">' + 0.00 + '</td>';
				row2 += '<td class="sum-footer-' + i + ' ">' + 0.00 + '</td>';
			}
			if (i = 1)
				i++;
			else
				i--;
		}
		sumfootertable += row1 + '</tr>' + row2 + '</tr>' + row3 + '</tr></table>';
		$('#sum_footer').html(sumfootertable);
	};
	
	
	$(function() {
		$('#payableRemind_Search').click(function(){
			var formData = $.serializeObject($("#payableRemind_from"));
			$.post("/cs/apmgt/waitPaymentRemindAction!findAllSearch.action", formData, function(result) {
				var result = JSON.parse(result);
				if (result.success) {
					$("#dtgd").datagrid('loadData',result.obj);
				}else{
					$("#dtgd").datagrid('loadData',{total:0,rows:[]});
					$.messager.show({
						title : '提示',
						msg : '未找到应收账款信息',
						showType : 'slide'
					});
				}
			});
		});
		window.armgt.waitPaymentRemind.loadPageBar();
		window.armgt.waitPaymentRemind.bindButtonEvent();
	});

})(jQuery);