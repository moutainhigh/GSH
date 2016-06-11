(function($) {

	$.util.namespace("apmgt.applyPayment");
	$.util.namespace("apmgt.applyPayment.pay");

	var dtgd = '#dtgd';

	window.apmgt.applyPayment.loadPageBar = function() {
		$(dtgd).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
		});
	};

	window.apmgt.applyPayment.optionsFormatter = function(value, row, index) {
		var options = "";
		if(row.supplierNo!=undefined){
			options = ' <i class="bicon-print" onclick="window.apmgt.applyPayment.print($.getDatagridRowIndex(this));"></i>';
		}
		if (row.status < 3) {
			if (btn_submit_enable == "enable")
				options += ' <i class="bicon-ok" onclick="window.apmgt.applyPayment.submit($.getDatagridRowIndex(this));"></i>';
			if (btn_revoke_enable == "enable")
				options += ' <i class="bicon-repeat" onclick="window.apmgt.applyPayment.revoke($.getDatagridRowIndex(this));"></i>';
		} else if (row.status == 4) {
			if (btn_payment_enable == "enable")
				options += ' <i class="bicon-tag" onclick="window.apmgt.applyPayment.pay($.getDatagridRowIndex(this));"></i>';
			if (btn_revoke_enable == "enable")
				options += ' <i class="bicon-repeat" onclick="window.apmgt.applyPayment.revoke($.getDatagridRowIndex(this));"></i>';
		}
		return options;
	};

	window.apmgt.applyPayment.statusFormatter = function(value, row, index) {
		if (value == 1) {
			return "未提交";
		} else if (value == 2) {
			return "已驳回";
		} else if (value == 3) {
			return "审批中";
		} else if (value == 4) {
			return "待销账";
		}
	};

	window.apmgt.applyPayment.amountFormatter = function(value, row, index) {
		if(row.currencyType==undefined){
			return $.formatCurrencySign(value, "CNY");
		}
		return $.formatCurrencySign(value, row.currencyType);
	};

//	window.apmgt.applyPayment.onLoadSuccess = function(data) {
//		var sumfootertable = '<table class="sum-footer">';
//		var row1 = '<tr><th></th>';
//		var row2 = '<tr><th>小计：</th>';
//		var row3 = '<tr><th>总计：</th>';
//		var i = 1;
//		for ( var item in data.sumfooter) {
//			row1 += '<th class="sum-footer-' + i + ' sum-footer-left">实付金额（' + item + '）</th>';
//			row3 += '<td class="sum-footer-' + i + ' sum-footer-left">' + data.sumfooter[item][0].payAmount + '</td>';
//			if (data.sumfooter[item][1]) {
//				row2 += '<td class="sum-footer-' + i + ' sum-footer-left">' + data.sumfooter[item][1].payAmount + '</td>';
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

	window.apmgt.applyPayment.disableToobar = function() {
		$('#btn_submit').linkbutton('disable');
		$('#btn_payment').linkbutton('disable');
		$('#btn_revoke').linkbutton('disable');
		$('#btn_print').linkbutton('disable');
	};

	window.apmgt.applyPayment.onSelect = function(index, row) {
		window.apmgt.applyPayment.disableToobar();
		if (row.status < 3) {
			$('#btn_submit').linkbutton(btn_submit_enable);
			$('#btn_revoke').linkbutton(btn_revoke_enable);
		} else if (row.status == 4) {
			$('#btn_payment').linkbutton(btn_payment_enable);
			$('#btn_revoke').linkbutton(btn_revoke_enable);
		}
		$('#btn_print').linkbutton('enable');
	};

	/**
	 * 提交申请
	 */
	window.apmgt.applyPayment.submit = function(index) {
		$.messager.confirm('确认', '您确认提交记录吗？', function(r) {
			if (r) {
				$.post("/cs/apmgt/apply!submit.action", {
					id : $(dtgd).datagrid('getRows')[index].id,
					settlementNo : $(dtgd).datagrid('getRows')[index].settlementNo,
					payAmount : $(dtgd).datagrid('getRows')[index].payAmount
				}, function(result) {
					var result = JSON.parse(result);
					if (result.success) {
//						$(dtgd).datagrid('getRows')[index].status = 4;
//						$(dtgd).datagrid('refreshRow', index);
//						window.apmgt.applyPayment.onSelect(index, $(dtgd).datagrid('getRows')[index]);
						$(dtgd).datagrid('load');
						if(result.obj==1){
							$('#btn_submit').linkbutton('disable');
							$('#btn_revoke').linkbutton('disable');
							$('#btn_payment').linkbutton('disable');
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
	window.apmgt.applyPayment.revoke = function(index) {
		$.messager.confirm('确认', '您确认要撤销记录吗？<br/> 撤销后将不可恢复！', function(r) {
			if (r) {
				$.post("/cs/apmgt/apply!revoke.action", {
					id : $(dtgd).datagrid('getRows')[index].id
				}, function(result) {
					var result = JSON.parse(result);
					if (result.success) {
						$(dtgd).datagrid('deleteRow', index);
						window.apmgt.applyPayment.disableToobar();
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
	 * 销账-付款
	 * 
	 * @param index
	 */
	window.apmgt.applyPayment.pay = function(index) {
		$('<div id="dlgPay">').dialog({
			title : "销账",
			href : $.util.resolveClientUrl('/cs/page/ap-mgt/applyPayment/pay.jsp'),
			width : 600,
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
					$.post("/cs/apmgt/apply!payment.action", {
						form : JSON.stringify(form_data),
						detail : JSON.stringify($('#dtgd_payable_detail').datagrid('getRows'))
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
				//$('#bankAccountNo').formatInput();
				var form_data = $(dtgd).datagrid('getRows')[index];
				var rows;
				$.ajax({
					async : false,
					type : "POST",
					data : {
						supmid : form_data.id
					},
					url : "/cs/apmgt/payableViewAction!list.action",
					dataType : 'json',
					success : function(data) {
						rows = data;
					}
				});
				$("#payAmount").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$("#bankRate").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$("#exchangeProfitLoss").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$("#factorage").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$('#form').form('load', form_data);
				window.apmgt.applyPayment.onHidePanel();
				$('#dtgd_payable_detail').datagrid('loadData', rows);
			}
		}).dialog('open');
	};

	window.apmgt.applyPayment.pay.fundAccountOnChange = function(nv, ov) {
		var row = $(this).combogrid('grid').datagrid('getSelected');
		if (row.accountCurrency != $('#currencyType').val()) {
			$('.hl').removeClass('trhidden');
			$('#bankRate').numberbox('disableValidation');
		} else {
			$('.hl').addClass('trhidden');
			$('#bankRate').numberbox('enableValidation');
		}
	};

	window.apmgt.applyPayment.pay.bankRateOnChange = function(nv, ov) {
		nv = isNaN(nv) ? 0 : nv * 1;
		var op_rate = 0;
		$.ajax({
			async : false,
			type : "POST",
			data : {
				code : $('#currencyType').val()
			},
			url : '/cs/pub/find!findRate.action',
			dataType : 'json',
			success : function(data) {
				op_rate = data * 1;
			}
		});
		$('#exchangeProfitLoss').numberbox('setValue', ($('#payAmount').numberbox('getValue') * op_rate) - ($('#payAmount').numberbox('getValue') * nv));
	};
	
	window.apmgt.applyPayment.print=function(data){
		var rows = $(dtgd).datagrid('getSelections');
		$('<div id="dlgRcpt">').dialog({
			title : "打印",
			href : $.util.resolveClientUrl('/cs/page/ap-mgt/applyPayment/receipt.jsp'),
			width : 650,
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
			},onLoad:function(){
				var str;
				var supplierNo;
				var danjihao="";
				var date1 = new Date();
				var d1 = date1.getMonth() + 1;
				var date1 = date1.getFullYear() + '-' + d1
						+ '-' + date1.getDate();
				var row=$(dtgd).datagrid('getSelected');
				$.ajax({
					async : false,
					type : "POST",
					data : {
						exchangeNo : row.exchangeNos
					},
					url : '/cs/apmgt/wpAction!findAllexNo.action',
					dataType : 'json',
					success : function(data) {
						if(data.type==1){//"1业务应付 2业务押金 3业务暂支 4业务应退 5预付6押金7其它8ADM9ACM10MCO"
							str="业务应付";
						}else if(data.type==2){
							str="业务押金";
						}else if(data.type==3){
							str="业务暂支";
						}else if(data.type==4){
							str="业务应退";
						}else if(data.type==5){
							str="预付";
						}else if(data.type==6){
							str="押金";
						}else if(data.type==7){
							str="其它";
						}else if(data.type==8){
							str="ADM";
						}else if(data.type==9){
							str="ACM";
						}else if(data.type==10){
							str="MCO";
						}
					}
				});
				$.ajax({
					async : false,
					type : "POST",
					data : {
						accountSource:2,
						supplierNo : row.supplierNo
					},
					url : '/cs/bset/CollectpayaccountAction!findList.action',
					dataType : 'json',
					success : function(data) {
						supplierNo=data[0].accountName;
					}
				});
				
				$.ajax({
					async : false,
					type : "POST",
					data : {
						supmid : row.id
					},
					url : '/cs/apmgt/wpAction!findSupmid.action',
					dataType : 'json',
					success : function(data) {
						for(var i = 0;i<data.length;i++){
							if(danjihao!=""){
								danjihao+",";
							}
							danjihao+=data[0].exchangeNo;
						}
					}
				});
				$("#date").html(date1);
				$("#yongtu").html("支付供应商"+str);
				$("#danwei").html(row.supplierNo+supplierNo);
				$("#zhanghao").html(row.bankAccountNo);
				$("#kaihuhang").val();
				$("#hanghao").val();
				$("#caiwushenhe").html(row.sprName);
				$("#fuhe").html(row.spDate);
				if(row.currencyType!='CNY'){
					
				}else{
					$("#jine").html(window.apmgt.applyPayment.DX(row.payAmount)+"("+row.payAmount+")");
				}
				
				if(row.paymentMethod==1){
					$("input[name=type]:eq(0)").attr("checked",'checked');
				}else if(row.paymentMethod==2){
					$("input[name=type]:eq(1)").attr("checked",'checked');
				}else if(row.paymentMethod==3){
					$("input[name=type]:eq(2)").attr("checked",'checked');
				}else if(row.paymentMethod==4){
					$("input[name=type]:eq(3)").attr("checked",'checked');
				}else if(row.paymentMethod==5){
					$("input[name=type]:eq(4)").attr("checked",'checked');
				}
				$("#danjihao").html(danjihao);
				$("#zhushi").html(row.remark);
			}
		}).dialog('open');
	}

	window.apmgt.applyPayment.DX=function(n) {
	    if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
	        return "数据非法";
	    var unit = "千百拾亿千百拾万千百拾元角分", str = "";
	        n += "00";
	    var p = n.indexOf('.');
	    if (p >= 0)
	        n = n.substring(0, p) + n.substr(p+1, 2);
	        unit = unit.substr(unit.length - n.length);
	    for (var i=0; i < n.length; i++)
	        str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
	    return str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
	}
	
	window.apmgt.applyPayment.paymentMethodOnChange = function(
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
	
	window.apmgt.applyPayment.onHidePanel=function(){
		var newValue=$("#paymentMethod").combobox('getValue');
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
	
	
	window.apmgt.applyPayment.bindToolBarEvent = function() {
		$('#btn_print').click(function() {
			if (!$('#btn_print').linkbutton('options').disabled) {
				window.apmgt.applyPayment.print($(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected')));
			}
		});
		$('#btn_submit').click(function() {
			if (!$('#btn_submit').linkbutton('options').disabled) {
				window.apmgt.applyPayment.submit($(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected')));
			}
		});

		$('#btn_revoke').click(function() {
			if (!$('#btn_revoke').linkbutton('options').disabled) {
				window.apmgt.applyPayment.revoke($(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected')));
			}
		});

		$('#btn_payment').click(function() {
			if (!$('#btn_payment').linkbutton('options').disabled) {
				window.apmgt.applyPayment.pay($(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected')));
			}
		});
		
		$('#btnSearch').click(function(){
			window.apmgt.applyPayment.Search();
		});

	};
	
	window.apmgt.applyPayment.Search=function(){
		var formData = $.serializeObject($("#applyPayment_from"));
		$.post("/cs/apmgt/apply!findAllSearch.action", formData, function(result) {
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
	
	/**
	 * 验证规则
	 */
	$.extend($.fn.validatebox.defaults.rules, {
		realAmount : {
			validator : function(value) {
				return value * 1 == $('input[name=paymentAmount]').val() * 1;
			},
			message : '实付金额必须等于应付金额！'
		}
	});

	$(function() {
		window.apmgt.applyPayment.loadPageBar();
		window.apmgt.applyPayment.bindToolBarEvent();
		window.apmgt.applyPayment.disableToobar();
	});

})(jQuery);