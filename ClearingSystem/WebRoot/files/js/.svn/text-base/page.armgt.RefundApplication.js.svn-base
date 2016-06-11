(function($) {

	$.util.namespace("armgt.refundapplication");

	var dtgd = '#dtgd';

	window.armgt.refundapplication.optionsFormatter = function(value, row, index) {
		if (!row.id)
			return value;
		var options = ' <i class="bicon-print" onclick="window.armgt.refundapplication.print($.getDatagridRowIndex(this))"></i>';
		if (row.status < 3) {
			if (btn_submit_enable == "enable")
				options += ' <i class="bicon-ok" onclick="window.armgt.refundapplication.submit($.getDatagridRowIndex(this));"></i>';
			if (btn_revoke_enable == "enable")
				options += ' <i class="bicon-repeat" onclick="window.armgt.refundapplication.revoke($.getDatagridRowIndex(this));"></i>';
		} else if (row.status == 4) {
			if (btn_payment_enable == "enable")
				options += ' <i class="bicon-tag" onclick="window.armgt.refundapplication.pay($.getDatagridRowIndex(this));"></i>';
			if (btn_revoke_enable == "enable")
				options += ' <i class="bicon-repeat" onclick="window.armgt.refundapplication.revoke($.getDatagridRowIndex(this));"></i>';
		}
		return options;
	};

	window.armgt.refundapplication.statusFormatter = function(value, row, index) {
		if (!row.id)
			return;
		if (value == 1) {
			return "未提交";
		} else if (value == 2) {
			return "已驳回";
		} else if (value == 3) {
			return "审核中";
		} else if (value == 4) {
			return "待销账";
		} else {
			return "";
		}
	};

	window.armgt.refundapplication.amountFormatter = function(value, row, index) {
		return $.formatCurrencySign(value, currency_local);
	};

	window.armgt.refundapplication.disableToobar = function() {
		$('#btn_submit').linkbutton('disable');
		$('#btn_payment').linkbutton('disable');
		$('#btn_revoke').linkbutton('disable');
		$("#btn_print").linkbutton('disable');
	};

	window.armgt.refundapplication.onSelect = function(index, row) {
		window.armgt.refundapplication.disableToobar();
		if (row.status < 3) {
			$('#btn_submit').linkbutton(btn_submit_enable);
			$('#btn_revoke').linkbutton(btn_revoke_enable);
		} else if (row.status == 4) {
			$('#btn_payment').linkbutton(btn_payment_enable);
			$('#btn_revoke').linkbutton(btn_revoke_enable);
		}
		$("#btn_print").linkbutton('enable');
	};

	/**
	 * 提交申请
	 * 
	 * @param index
	 */
	window.armgt.refundapplication.submit = function(index) {
		$.messager.confirm('确认', '您确认提交记录吗？', function(r) {
			if (r) {
				$.post("/cs/armgt/ctpm!submit.action", {
					id : $(dtgd).datagrid('getRows')[index].id,
					settlementNo : $(dtgd).datagrid('getRows')[index].settlementNo,
					incomeAmount : $(dtgd).datagrid('getRows')[index].incomeAmount,
				}, function(result) {
					var result = JSON.parse(result);
					if (result.success) {
//						$(dtgd).datagrid('getRows')[index].status = 4;
//						$(dtgd).datagrid('refreshRow', index);
//						window.armgt.refundapplication.onSelect(index, $(dtgd).datagrid('getRows')[index]);
						$(dtgd).datagrid('load');
						if(result.obj==1){
							$('#btn_submit').linkbutton('disable');
							$('#btn_payment').linkbutton('disable');
							$('#btn_revoke').linkbutton('disable');
						}else{
							$('#btn_submit').linkbutton('disable');
							$('#btn_revoke').linkbutton('disable');
						}
						
					}
					$.messager.show({
						title : '提示',
						msg : result.msg,
						showType : 'slide'
					});
				});
			}
		});
	};

	/**
	 * 撤销
	 */
	window.armgt.refundapplication.revoke = function(index) {
		$.messager.confirm('确认', '您确认要撤销记录吗？<br/> 撤销后将不可恢复！', function(r) {
			if (r) {
				$.post("/cs/armgt/ctpm!revoke.action", {
					id : $(dtgd).datagrid('getRows')[index].id
				}, function(result) {
					var result = JSON.parse(result);
					if (result.success) {
						$(dtgd).datagrid('deleteRow', index);
						window.armgt.refundapplication.disableToobar();
					}
					$.messager.show({
						title : '提示',
						msg : result.msg,
						showType : 'slide'
					});
				});
			}
		});
	};

	/**
	 * 销账
	 * 
	 * @param rows
	 */
	window.armgt.refundapplication.pay = function(index) {
		$('<div id="dlgPay">').dialog({
			title : "销账",
			href : $.util.resolveClientUrl('/cs/page/ar-mgt/rfap/pay.jsp'),
			width : 750,
			height : 450,
			top : 5,
			iconCls : 'bicon-tag',
			resizable : false,
			modal : true,
			closed : true,
			cache : false,
			shadow : false,
			buttons : [ {
				text : '<i class="fa fa-save"></i> 确认',
				handler : function() {
					if (!$('#form').form('validate')) {
						return;
					}
					var form_data = $.serializeObject($('#form'));
					form_data.id = $(dtgd).datagrid('getRows')[index].id;
					$.post("/cs/armgt/ctpm!payment.action", {
						form : JSON.stringify(form_data),
						detail : JSON.stringify($('#dtgd_pay').datagrid('getRows'))
					}, function(result) {
						var result = JSON.parse(result);
						if (result.success) {
							$(dtgd).datagrid('deleteRow', index);
							$('#dlgPay').dialog('close');
						}
						$.messager.show({
							title : '提示',
							msg : result.msg,
							showType : 'slide'
						});
					});
				}
			}, {
				text : '<i class="fa fa-times"></i> 放弃',
				handler : function() {
					$('#dlgPay').dialog('close');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				$('#bankAccountNo').formatInput();
				var form_data = $(dtgd).datagrid('getRows')[index];
				form_data.netAmount = form_data.incomeAmount;
				var rows;
				$.ajax({
					async : false,
					type : "POST",
					data : {
						ctpmid : form_data.id
					},
					url : "/cs/armgt/receivableViewAction!list.action",
					dataType : 'json',
					success : function(data) {
						rows = data;
					}
				});
				if (rows[0].originalPaymentMethod == 2) {
					$('.xyk').removeClass('hidden');
				} else {
					$('.fxyk').removeClass('hidden');
				}
				
				$("#incomeAmount").numberbox({prefix:$.convertCurrencyUnit('CNY')});
				$("#customerFactorage").numberbox({prefix:$.convertCurrencyUnit('CNY')});
				$("#netAmount").numberbox({prefix:$.convertCurrencyUnit('CNY')});
				$("#factorage").numberbox({prefix:$.convertCurrencyUnit('CNY')});
				$("#bankRate").numberbox({prefix:$.convertCurrencyUnit('CNY')});
				$("#exchangeProfitLoss").numberbox({prefix:$.convertCurrencyUnit('CNY')});
				$('#form').form('load', form_data);
				window.armgt.refundapplication.onHidePanel();
				$('#dtgd_pay').datagrid('loadData', rows);
			}
		}).dialog('open');
	};

	window.armgt.refundapplication.pay.originalPaymentMethodFormatter = function(value, row, index) {
		switch (value) {
		case 0:
			return "";
		case 1:
			return "现金 ";
		case 2:
			return "信用卡 ";
		case 3:
			return "支票 ";
		case 4:
			return "转账汇款 ";
		case 5:
			return "内转 ";
		case 6:
			return "第三方支付";
		}
	};

	window.armgt.refundapplication.pay.fundAccountOnChange = function(newValue, oldValue) {
		if (currency_local == $(this).combogrid('grid').datagrid('getSelected').accountCurrency) {
			$('.currency').addClass('hidden');
		} else {
			$('.currency').removeClass('hidden');
		}
	};

	window.armgt.refundapplication.pay.netAmountOnChange = function(newValue, oldValue) {
		$('#exchangeProfitLoss').numberbox('setValue', $('#incomeAmount').numberbox('getValue') * 1 + $('#customerFactorage').numberbox('getValue') * 1 - newValue * $('#bankRate').numberbox('getValue'));
	};

	window.armgt.refundapplication.pay.bankRateOnChange = function(newValue, oldValue) {
		$('#exchangeProfitLoss').numberbox('setValue', $('#incomeAmount').numberbox('getValue') * 1 + $('#customerFactorage').numberbox('getValue') * 1 - newValue * $('#netAmount').numberbox('getValue'));
	};

	window.armgt.refundapplication.loadPageBar = function() {
		$(dtgd).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
		});
	};
	
	window.armgt.refundapplication.search=function(){
		var formData = $.serializeObject($("#RefundApplication_from"));
		$.post("/cs/armgt/ctpm!findAllSearch.action", formData, function(result) {
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
	
	window.armgt.refundapplication.print=function(data){
			var rows = $(dtgd).datagrid('getSelections');
			$('<div id="dlgRcpt">').dialog({
				title : "打印",
				href : $.util.resolveClientUrl('/cs/page/ar-mgt/rfap/receipt.jsp'),
				width : 730,
				height : 500,
				top : 5,
				iconCls : 'bicon-file',
				resizable : false,
				modal : true,
				closed : true,
				cache : false,
				buttons : [
				  {
					  text : '确认打印',
					  iconCls : 'bicon-print',
					  handler : function(){
						  $("#contents").printArea();//jquery拓展打印方法
							$.messager.show({
								title : '提示',
								msg : '打印成功',
								showType : 'slide'
							});
							$("#dlgRcpt").dialog('close');
					  }
				  }
				  ,{
					text : '关闭',
					iconCls : 'bicon-remove',
					handler : function() {
						$('#dlgRcpt').dialog('close');
					}
				} ],
				onClose : function() {
					$(this).dialog('destroy');
				}
			}).dialog('open');
	}
	
	
	window.armgt.refundapplication.paymentMethodOnChange = function(
			newValue, oldValue) {
		var result = eval("(" + $.ajax({
			url : "/cs/pub/find!findAccount.action",
			dataType : "json/xml/html",
			data : {
				"paymentMethod" : newValue
			},
			async : false,
			cache : false,
			type : "post"
		}).responseText + ")");
			var g = $('#fundAccount').combogrid('grid'); // 获取数据表格对象
			g.datagrid({
				data : result
			});
			var r = g.datagrid('getRows');
			for ( var i = 0; i < r.length; i++) {
				if (r[i].accountType == 'C' && r[i].defaultIncAccount == '1') {
					$('#fundAccount').combogrid('setValue', r[i].accountCode);
					return;
				} else {
					$('#fundAccount').combogrid('setValue', r[i].accountCode);
				}
			}	
			if(result.length==0){
				$("#fundAccount").combogrid('setText',"");
			}
			
	};
	
	window.armgt.refundapplication.onHidePanel=function(){
		var newValue=$("#paymentMethod").combobox('getValue');
		alert(newValue)
		var result = eval("(" + $.ajax({
			url : "/cs/pub/find!findAccount.action",
			dataType : "json/xml/html",
			data : {
				"paymentMethod" : newValue
			},
			async : false,
			cache : false,
			type : "post"
		}).responseText + ")");
			var g = $('#fundAccount').combogrid('grid'); // 获取数据表格对象
			g.datagrid({
				data : result
			});
			var r = g.datagrid('getRows');
			for ( var i = 0; i < r.length; i++) {
				if (r[i].accountType == 'C' && r[i].defaultIncAccount == '1') {
					$('#fundAccount').combogrid('setValue', r[i].accountCode);
					return;
				} else {
					$('#fundAccount').combogrid('setValue', r[i].accountCode);
				}
			}	
			if(result.length==0){
				$("#fundAccount").combogrid('setText',"");
			}
	};
	
	

	window.armgt.refundapplication.bindToolBarEvent = function() {
		$('#btn_submit').click(function() {
			if (!$('#btn_submit').linkbutton('options').disabled) {
				window.armgt.refundapplication.submit($(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected')));
			}
		});

		$('#btn_revoke').click(function() {
			if (!$('#btn_revoke').linkbutton('options').disabled) {
				window.armgt.refundapplication.revoke($(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected')));
			}
		});

		$('#btn_payment').click(function() {
			if (!$('#btn_payment').linkbutton('options').disabled) {
				window.armgt.refundapplication.pay($(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected')));
			}
		});
		$('#btnSearch').click(function(){
			window.armgt.refundapplication.search();
		});
		
		$("#btn_print").click(function(){
			if (!$('#btn_print').linkbutton('options').disabled) {
				window.armgt.refundapplication.print($(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected')));
			}
		});
	};

	$.extend($.fn.validatebox.defaults.rules, {
		less_equal_zero : {
			validator : function(value, opt) {
				return !(value * 1 > 0);
			},
			message : '必须小于0！'
		}
	});

	$(function() {
		window.armgt.refundapplication.loadPageBar();
		window.armgt.refundapplication.bindToolBarEvent();
		window.armgt.refundapplication.disableToobar();
	});

})(jQuery);