(function($) {

	$.util.namespace("apmgt.paymentDetailed");

	var dtgd = '#dtgd';

	window.apmgt.paymentDetailed.amountFormatter = function(value, row, index) {
		if(row.currencyType==undefined){
			return $.formatCurrencySign(value, "CNY");
		}
		return $.formatCurrencySign(value, row.currencyType);
	};

	window.apmgt.paymentDetailed.loadPageBar = function() {
		$(dtgd).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
		});
	};
	
	window.apmgt.paymentDetailed.onLoadSuccess = function(data) {
		var sumfootertable = '<table class="sum-footer">';
		var row1 = '<tr><th></th>';
		var row2 = '<tr><th>小计：</th>';
		var row3 = '<tr><th>总计：</th>';
		var i = 1;
		for ( var item in data.sumfooter) {
			row1 += '<th class="sum-footer-' + i + ' sum-footer-left">实付金额（' + item + '）</th>';
			row3 += '<td class="sum-footer-' + i + ' sum-footer-left">' + data.sumfooter[item][0].payAmount + '</td>';
			if (data.sumfooter[item][1]) {
				row2 += '<td class="sum-footer-' + i + ' sum-footer-left">' + data.sumfooter[item][1].payAmount + '</td>';
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
	
	//1现金 /2信用卡/ 3支票 /4转账汇款/5内转
	window.apmgt.paymentDetailed.paymentMethod=function(value, row, index){
		if(value==1)
			return '现金';
		else if(value==2)
			return '信用卡';
		else if(value==3)
			return '支票';
		else if(value==4)
			return '转账/汇款';
		else if(value==5)
			return '内转';
	};
	
	window.apmgt.paymentDetailed.bindToolBarEvent = function() {
		$("#btnSearch").click(function() {
			if (!$("#paymentDetaile_from").form('validate')) {
				return;
			}
//			var formData = $.serializeObject($("#paymentDetaile_from"));
//			$.post("/cs/apmgt/payableViewAction!findSearch.action", formData, function(result) {
//				var result = JSON.parse(result);
//				if (result.success) {
//					$(dtgd).datagrid('loadData',result.obj);
//				}else{
//					$.messager.show({
//						title : '提示',
//						msg : result.msg,
//						showType : 'slide'
//					});
//					$(dtgd).datagrid('loadData',{total:0,rows:[]});
//				}
//				
//			});
			var url="/cs/apmgt/payableViewAction!findAll.action?abc=1";
			if($("#tradeDateQi").datebox('getValue')!=""&&$("#tradeDateQi").datebox('getValue')!=undefined){
				url+="&tradeDateQi="+$("#tradeDateQi").combobox('getValue');
			}
			if($("#tradeDateShi").datebox('getValue')!=""&&$("#tradeDateShi").datebox('getValue')!=undefined){
				url+="&tradeDateShi="+$("#tradeDateShi").combobox('getValue');
			}
			if($("#deadlineShi").datebox('getValue')!=""&&$("#deadlineShi").datebox('getValue')!=undefined){
				url+="&deadlineShi="+$("#deadlineShi").combobox('getValue');
			}
			if($("#supplierNo").combogrid('getValue')!=""&&$("#supplierNo").combogrid('getValue')!=undefined){
				url+="&supplierNo="+$("#supplierNo").combogrid('getValue');
			}
			if($("#exchangeNo").val()!=""&&$("#exchangeNo").val()!=undefined){
				url+="&exchangeNo="+$("#exchangeNo").val();
			}
			if($("#orderNo").val()!=""&&$("#orderNo").val()!=undefined){
				url+="&orderNo="+$("#orderNo").val();
			}
			if($("#affiliationNo").combobox('getValue')!=""&&$("#affiliationNo").combobox('getValue')!=undefined){
				url+="&affiliationNo="+$("#affiliationNo").combobox('getValue');
			}
			if($("#productNo").val()!=""&&$("#productNo").val()!=undefined){
				url+="&productNo="+$("#productNo").val();
			}
			if($("input[name='counterFee']:checked").val()!=""&&$("input[name='counterFee']:checked").val()!=undefined){
				url+="&counterFee="+$("input[name='counterFee']:checked").val();
			}
			if($("#voucherNo").val()!=""&&$("#voucherNo").val()!=undefined){
				url+="&voucherNo="+$("#voucherNo").val();
			}
			
			
			
			$(dtgd).datagrid({
				url:url
			});
			$(dtgd).datagrid('clearSelections');
			
		});
	};
	$(function() {
		var date=new Date();
		var d=date.getMonth()+1;
		var date =  date.getFullYear()+'-'+d+'-01';
		var date1=new Date();
		var d1=date1.getMonth()+1;
		var date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
		$("#deadlineShi").datebox({
			value:date1,
			onSelect: function(date){
				$("#tradeDateQi").datebox('setValue', '');
				$("#tradeDateShi").datebox('setValue', '');
				$("#deadlineShi").datebox('setValue', date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate());
			},
			onChange: function(newValue, oldValue){
				$("#tradeDateQi").datebox('setValue', '');
				$("#tradeDateShi").datebox('setValue', '');
			}
		});
		
		$("#tradeDateQi").datebox({
			value:date,
			onSelect: function(date){
				$("#tradeDateQi").datebox('setValue', date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate());
		        $("#deadlineShi").datebox('setValue', '');
			},
			onChange: function(newValue, oldValue){
				 $("#deadlineShi").datebox('setValue', '');
			}
		});
		$("#tradeDateShi").datebox({
			value:date1,
			onSelect: function(date){
				$("#tradeDateShi").datebox('setValue', date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate());
				$("#deadlineShi").datebox('setValue', '');
			},
			onChange: function(newValue, oldValue){
				 $("#deadlineShi").datebox('setValue', '');
			}
		});
		$("#deadlineShi").datebox('setValue','');
		$("#tradeDateQi").datebox('setValue',date);
		$("#tradeDateShi").datebox('setValue',date1);
//		$("#deadlineQi").datebox('setValue',date);
		
		window.apmgt.paymentDetailed.loadPageBar();
		window.apmgt.paymentDetailed.bindToolBarEvent();
	});

})(jQuery);