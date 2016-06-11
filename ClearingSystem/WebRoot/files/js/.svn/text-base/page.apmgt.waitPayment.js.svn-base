(function($) {

	$.util.namespace("apmgt.waitPayment");
	$.util.namespace("apmgt.waitPayment.add");
	$.util.namespace("apmgt.waitPayment.writeoffPay");
	$.util.namespace("apmgt.waitPayment.writeoffIncome");
	$.util.namespace("apmgt.waitPayment.apply");
	$.util.namespace("apmgt.waitPayment.cancel");

	var dtgd = '#dtgd';

	var first_has;// 标记价格修改是否第一次启用编辑

	window.apmgt.waitPayment.loadPageBar = function() {
		$(dtgd).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ],
			buttons : [ {
				iconCls : 'bicon-check',
				handler : function() {
					$(dtgd).datagrid('options').singleSelect = false;
					$.messager.show({
						title : '操作提示',
						msg : '已开启多选！',
						showType : 'slide'
					});
				}
			}, {
				iconCls : 'bicon-stop',
				handler : function() {
					$(dtgd).datagrid('options').singleSelect = true;
					$(dtgd).datagrid('unselectAll');
					$.messager.show({
						title : '操作提示',
						msg : '已关闭多选！',
						showType : 'slide'
					});
				}
			} ]
		});
	};

	window.apmgt.waitPayment.optionsFormatter = function(value, row, index) {
		var options = '';
		if (row.type < 5) {
			if (btn_view_enable == "enable"&&row.exchangeNo.indexOf('*')==-1)
				options += ' <i class="bicon-folder-open" onclick="window.apmgt.waitPayment.ChakanDanju($.getDatagridRowIndex(this))"></i>';
		}
		if (row.cancelStatus == 2) {
			if (btn_yn_enable == "enable") {
				options += ' <i class="bicon-ok-circle" onclick="window.apmgt.waitPayment.btnN($.getDatagridRowIndex(this))"></i>';
				options += ' <i class="bicon-ban-circle" onclick="window.apmgt.waitPayment.btnN($.getDatagridRowIndex(this))"></i>';
			}
		} else {
			if (apply_power && row.balance > 0 && row.affiliationPerson!=undefined) {
				if (btn_apply_enable == "enable"){
					options += ' <i class="bicon-thumbs-up" onclick="window.apmgt.waitPayment.applyByIndex($.getDatagridRowIndex(this));"></i>';
				}
			} else if(row.affiliationPerson!=undefined){
				if (row.balance > 0) {
					if (btn_writeoff_enable == "enable")
						options += ' <i class="bicon-tag" onclick="window.apmgt.waitPayment.writeoffPayByIndex($.getDatagridRowIndex(this));"></i>';
				} else {
					if (btn_writeoff_enable == "enable")
						options += ' <i class="bicon-tag" onclick="window.apmgt.waitPayment.writeoffIncomeByIndex($.getDatagridRowIndex(this));"></i>';
				}
			}
			if (row.revocationHas == 1) {
				if (btn_revocation_enable == "enable")
					options += ' <i class="bicon-remove" onclick="window.apmgt.waitPayment.revocation($.getDatagridRowIndex(this));"></i>';
			}
		}
		return options;
	};
	
	window.apmgt.waitPayment.incomeStyler=function(value, row, index){
		if(row.deadline==undefined&&row.affiliationPerson==undefined){
			return 'background-color:#CFCFCF;';
		}
	}

	window.apmgt.waitPayment.typeFormatter = function(value, row, index) {
		switch (value) {
		case 1:
			return "业务应付";
		case 2:
			return "业务押金";
		case 3:
			return "业务暂支";
		case 4:
			return "业务应退";
		case 5:
			return "预付";
		case 6:
			return "押金";
		case 7:
			return "其它";
		case 8:
			return "ADM";
		case 9:
			return "ACM";
		case 10:
			return "MCO";
		default:
			return "";
		}
	};

	window.apmgt.waitPayment.amountFormatter = function(value, row, index) {
		if(row.currencyType==undefined){
			return $.formatCurrencySign(value, "CNY");
		}
		return $.formatCurrencySign(value, row.currencyType);
	};
	window.apmgt.waitPayment.amountFormatter2 = function(value, row, index) {
		if(row.currencyType==undefined){
			return $.formatCurrencySign(value, "CNY");
		}else{
			return value;
		}
	};


	window.apmgt.waitPayment.onLoadSuccess = function(data) {
//		var sumfootertable = '<table class="sum-footer">';
//		var row1 = '<tr><th></th>';
//		var row2 = '<tr><th>小计：</th>';
//		var row3 = '<tr><th>总计：</th>';
//		var i = 1;
//		for ( var item in data.sumfooter) {
//			row1 += '<th class="sum-footer-' + i + ' sum-footer-left">应付金额 </th>';
//			row1 += '<th class="sum-footer-' + i + ' ">已付金额 </th>';
//			row1 += '<th class="sum-footer-' + i + ' ">余额（' + item + '）</th>';
//			row3 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + data.sumfooter[item][0].payBeAmount + '</td>';
//			row3 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][0].payAmount + '</td>';
//			row3 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][0].balance + '</td>';
//			if (data.sumfooter[item][1]) {
//				row2 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + data.sumfooter[item][1].payBeAmount + '</td>';
//				row2 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][1].payAmount + '</td>';
//				row2 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][1].balance + '</td>';
//			} else {
//				row2 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + 0.00 + '</td>';
//				row2 += '<td class="sum-footer-' + i + ' ">' + 0.00 + '</td>';
//				row2 += '<td class="sum-footer-' + i + ' ">' + 0.00 + '</td>';
//			}
//			if (i = 1)
//				i++;
//			else
//				i--;
//		}
//		sumfootertable += row1 + '</tr>' + row2 + '</tr>' + row3 + '</tr></table>';
//		$('#sum_footer').html(sumfootertable);
	};

	window.apmgt.waitPayment.toolbarDisable = function() {
		$('#btn_revocation').linkbutton('disable');
		$('#btn_apply').linkbutton('disable');
		$('#btn_writeoff').linkbutton('disable');
		$('#btn_view').linkbutton('disable');
		$('#btn_y').linkbutton('disable');
		$('#btn_n').linkbutton('disable');
	};

	/**
	 * 单选模式工具栏按钮显示影藏
	 */
	window.apmgt.waitPayment.toolBarUpdate = function(row) {
		window.apmgt.waitPayment.toolbarDisable();
		if (row.cancelStatus == 2) {
			$('#btn_y').linkbutton(btn_yn_enable);
			$('#btn_n').linkbutton(btn_yn_enable);
		} else {
			if (apply_power && row.balance > 0) {
				$('#btn_apply').linkbutton(btn_apply_enable);
			} else {
				$('#btn_writeoff').linkbutton(btn_writeoff_enable);
			}
			if (row.revocationHas == 1) {
				$('#btn_revocation').linkbutton(btn_revocation_enable);
			}
		}
		if (row.exchangeNo.indexOf('*')!=-1) {
			$('#btn_view').linkbutton('disable');
		}else if (row.type < 5) {
			$("#btn_view").linkbutton(btn_view_enable);
		}
	};

	/**
	 * 多选模式工具栏按钮显示影藏
	 */
	window.apmgt.waitPayment.toolBarUpdateBySelects = function(rows) {
		window.apmgt.waitPayment.toolbarDisable();
		var balance = 0;
		for ( var i = 0; i < rows.length; i++) {
			balance += rows[i].balance;
			if (rows[i].cancelStatus == 2 || rows[i].currencyType != rows[0].currencyType || rows[i].supplierNo != rows[0].supplierNo) {
				return;
			}
		}
		if (apply_power && balance > 0) {
			$('#btn_apply').linkbutton(btn_apply_enable);
		} else {
			$('#btn_writeoff').linkbutton(btn_writeoff_enable);
		}
	};
	
	window.apmgt.waitPayment.XuanzhongQuxiao=function(row,sum){
		var getData=$(dtgd).datagrid('getData');
		if(sum==1){
			for(var i=0;i<getData.rows.length;i++){
				if("*"+row.exchangeNo==getData.rows[i].exchangeNo){
					$(dtgd).datagrid('selectRow',i);
					break;
				}
				if(row.exchangeNo.substr(1, row.exchangeNo.length)==getData.rows[i].exchangeNo){
					$(dtgd).datagrid('selectRow',i);
					break;
				}
			}
		}
		else{
			for(var f=0;f<getData.rows.length;f++){
				if("*"+row.exchangeNo==getData.rows[f].exchangeNo){
					var index=$(dtgd).datagrid('getRowIndex',getData.rows[f]);
					$(dtgd).datagrid('unselectRow',f);
				}
				if(row.exchangeNo.substr(1, row.exchangeNo.length)==getData.rows[f].exchangeNo){
					$(dtgd).datagrid('unselectRow',f);
				}
			}
			
		}
	};

	window.apmgt.waitPayment.onSelect = function(index, row) {
		var selectRows = $(dtgd).datagrid('getSelections');
		var flog = true;
		for(var i = 0; i <selectRows.length; i++){
			if(selectRows[i].exchangeNo == "*"+row.exchangeNo){
				flog = false;
				break;
			}
			if (row.exchangeNo.substr(1, row.exchangeNo.length) == selectRows[i].exchangeNo) {
				flog = false;
				break;
			}
		}
		if(flog){
			window.apmgt.waitPayment.XuanzhongQuxiao(row,1);
		}
		
		
		var selectRows = $(dtgd).datagrid('getSelections');
		if (selectRows.length == 1) {
			window.apmgt.waitPayment.toolBarUpdate(row);
		} else {
			window.apmgt.waitPayment.toolBarUpdateBySelects(selectRows);
		}
	};

	window.apmgt.waitPayment.onUnselect = function(index, row) {
		var selectRows = $(dtgd).datagrid('getSelections');
		var flog = false;
		for(var i = 0; i <selectRows.length; i++){
			if(selectRows[i].exchangeNo == "*"+row.exchangeNo){
				flog = true;
				break;
			}
			if (row.exchangeNo.substr(1, row.exchangeNo.length) == selectRows[i].exchangeNo) {
				flog = true;
				break;
			}
		}
		if(flog){
			window.apmgt.waitPayment.XuanzhongQuxiao(row,0);
		}
		
		
		var selectRows = $(dtgd).datagrid('getSelections');
		if (selectRows.length == 1) {
			window.apmgt.waitPayment.toolBarUpdate(selectRows[0]);
		} else if (selectRows.length > 1) {
			window.apmgt.waitPayment.toolBarUpdateBySelects(selectRows);
		}else{
			window.apmgt.waitPayment.toolbarDisable();
		}
	};

	/**
	 * 新建
	 * 
	 */
	window.apmgt.waitPayment.add = function() {
		$('<div id="dlgAdd">').dialog({
			title : "新建",
			href : $.util.resolveClientUrl('/cs/page/ap-mgt/waitPayment/add.jsp'),
			width : 280,
			height : 'auto',
			top : 5,
			iconCls : 'bicon-asterisk',
			resizable : false,
			modal : true,
			closed : true,
			cache : false,
			shadow : false,
			buttons : [ {
				text : '<i class="fa fa-save"></i> 确认',
				handler : function() {
					if (!$('form').form('validate')) {
						return;
					}
					var form_data = $.serializeObject($('form'));
					console.info(form_data);
					$.post("/cs/apmgt/wpAction!add.action", form_data, function(result) {
						var result = JSON.parse(result);
						if (result.success) {
							$(dtgd).datagrid('insertRow', {
								index : 0,
								row : result.obj
							});
							$('#dlgAdd').dialog('close');
							$('#supplierNo1').combogrid({
								url:'/cs/apmgt/wpAction!findAllSupplierNo.action'
							});
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
					$('#dlgAdd').dialog('close');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				var result = eval("(" + $.ajax( {
					url : "/cs/armgt/receivable!findAffiliationNo.action",
					dataType : "json/xml/html",
					async : false,
					cache : false,
					type : "post"
				}).responseText + ")");
				var json=JSON.parse(result.obj);
				$('#affiliationPerson').val(json[0].name);
				$('#affiliationNo').val(json[0].uid);
				$("#affiliationNo").combogrid({    
					panelWidth:265,   
				    data:json,    
				    value:'0',
				    idField:'uid',    
				    textField:'name',      
				    columns:[[    
				        {field:'name',title:'姓名',width:60},    
				        {field:'sex',title:'性别',width:60},    
				        {field:'dept',title:'部门',width:60},    
				        {field:'empId',title:'员工号',width:60}    
				    ]],    
				    onHidePanel: function(){    
			            $('#affiliationPerson').val($('#affiliationNo').combogrid('getText'));    
			        }
				}); 
				window.apmgt.waitPayment.add.typeOnChange(5, 0);
			}
		}).dialog('open');
	};

	window.apmgt.waitPayment.add.typeOnChange = function(nv, ov) {
		if (nv < 8) {
			$('.type1').removeClass('trhidden');
			$('.type2').addClass('trhidden');
		} else {
			$('.type2').removeClass('trhidden');
			$('.type1').addClass('trhidden');
		}
		if (nv == 5 || nv == 8) {
			$('#payBeAmount').numberbox({
				min : 0,
				max : 999999999
			});
		} else if (nv == 9) {
			$('#payBeAmount').numberbox({
				min : -999999999,
				max : 0
			});
		} else {
			$('#payBeAmount').numberbox({
				min : -999999999,
				max : 999999999
			});
		}
	};

	/**
	 * 撤销
	 * 
	 * @param index
	 */
	window.apmgt.waitPayment.revocation = function(index) {
		$.messager.confirm('确认', '您确认想要撤销记录吗？', function(r) {
			if (r) {
				$.post("/cs/apmgt/wpAction!revocation.action", {
					id : $(dtgd).datagrid('getRows')[index].id
				}, function(result) {
					var result = JSON.parse(result);
					if (result.success) {
						$(dtgd).datagrid('getRows')[index].revocationHas = 2;
						$(dtgd).datagrid('refreshRow', index);
						$(dtgd).datagrid('insertRow', {
							index : 0,
							row : result.obj
						});
						$(dtgd).datagrid('clearSelections');
						window.apmgt.waitPayment.toolbarDisable();
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
	 * @param rows
	 */
	window.apmgt.waitPayment.writeoffPay = function(rows) {
		$('<div id="dlgRtof">').dialog({
			title : "销账",
			href : $.util.resolveClientUrl('/cs/page/ap-mgt/waitPayment/writeoff_pay.jsp'),
			width : 650,
			height : 480,
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
					var rows = $('#dtgd_payable_detail').datagrid('getRows');
					for ( var r = 0; r < rows.length; r++) {
						if (!$('#dtgd_payable_detail').datagrid('validateRow', r)) {
							return;
						}
					}
					$('#dtgd_payable_detail').datagrid('acceptChanges');
					var form_data = $.serializeObject($('#form'));
					$.post("/cs/apmgt/wpAction!pay.action", {
						form : JSON.stringify(form_data),
						detail : JSON.stringify($('#dtgd_payable_detail').datagrid('getRows'))
					}, function(result) {
						var result = JSON.parse(result);
						if (result.success) {
							$(dtgd).datagrid('load');
							$('#dlgRtof').dialog('close');
							$(dtgd).datagrid('clearSelections');
							window.apmgt.waitPayment.toolbarDisable();
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
					$('#dlgRtof').dialog('close');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				//接口获取账号
				var index = $("#dtgd").datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
				var result = eval("(" + $.ajax( {
					url : "/cs/bset/CollectpayaccountAction!findBankAccountNo.action",
					dataType : "json/xml/html",
					data : {
						"accountCode" : $("#dtgd").datagrid('getRows')[index].supplierNo
					},
					async : false,
					cache : false,
					type : "post"
				}).responseText + ")");
				
				
				var form_data = {
					supplierNo : rows[0].supplierNo,
					supplierName : rows[0].supplierName,
					bankAccountNo : '',
					currencyType : rows[0].currencyType,
					payBeAmount : 0,
					payAmount : 0,
					balance : 0
				};
				var details = [];
				for ( var i = 0; i < rows.length; i++) {
					form_data.payBeAmount += rows[i].balance;
					details[i] = {
						exchangeNo : rows[i].exchangeNo,
						tradeDate : rows[i].tradeDate,
						balance : rows[i].balance,
						payAmount : rows[i].balance,
						pablId : rows[i].id,
						reverseHas : rows[i].reverseHas,
						currencyType :rows[i].currencyType
					};
				}
				form_data.payAmount = form_data.payBeAmount;
				$("#payBeAmount").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$("#payAmount").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$("#balance").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$("#bankRate").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$("#exchangeProfitLoss").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$("#factorage").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$('#form').form('load', form_data);
				if(result.success){
					if(result.obj!=undefined){
						var json=JSON.parse(result.obj);
						if(json[0].account!=""){
							$('#bankAccountNo').combobox({    
								data:json,    
								value:json[0].account,
							    valueField:'account',    
							    textField:'account'   
							}); 
						}
					}
				}
				$('#dtgd_payable_detail').datagrid('loadData', details);
			}
		}).dialog('open');
	};

	window.apmgt.waitPayment.writeoffPay.fundAccountOnChange = function(nv, ov) {
		var row = $(this).combogrid('grid').datagrid('getSelected');
		if (row.accountCurrency != $('#currencyType').val()) {
			$('.hl').removeClass('trhidden');
			$('#bankRate').numberbox('disableValidation');
		} else {
			$('.hl').addClass('trhidden');
			$('#bankRate').numberbox('enableValidation');
		}
	};

	window.apmgt.waitPayment.writeoffPay.bankRateOnChange = function(nv, ov) {
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
		$('#exchangeProfitLoss').numberbox('setValue', ($('#payAmount').val() * op_rate) - ($('#payAmount').val() * nv));
	};

	window.apmgt.waitPayment.writeoffPay.onClickRow = function(rowIndex, rowData) {
		if (rowData.reverseHas == 2)
			return;
		first_has = true;
		$('#dtgd_payable_detail').datagrid('beginEdit', rowIndex);
	};

	window.apmgt.waitPayment.writeoffPay.payAmountOnChange = function(newValue, oldValue) {
		oldValue = isNaN(oldValue) ? 0 : oldValue * 1;
		newValue = isNaN(newValue) ? 0 : newValue * 1;
		if (first_has) {
			first_has = false;
		} else {
			$('#payAmount').val(($('#payAmount').val() * 1) - oldValue + newValue);
			$('#balance').val($('#payBeAmount').val() * 1 - $('#payAmount').val() * 1);
		}
	};

	/**
	 * 销账-付款
	 * 
	 * @param index
	 */
	window.apmgt.waitPayment.writeoffPayByIndex = function(index) {
		window.apmgt.waitPayment.writeoffPay([ $(dtgd).datagrid('getRows')[index] ]);
	};

	/**
	 * 销账-收入
	 * 
	 * @param rows
	 */
	window.apmgt.waitPayment.writeoffIncome = function(rows) {
		$('<div id="dlgRtof">').dialog({
			title : "销账",
			href : $.util.resolveClientUrl('/cs/page/ap-mgt/waitPayment/writeoff_income.jsp'),
			width : 650,
			height : 470,
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
					var rows = $('#dtgd_payable_detail').datagrid('getRows');
					for ( var r = 0; r < rows.length; r++) {
						if (!$('#dtgd_payable_detail').datagrid('validateRow', r)) {
							return;
						}
					}
					$('#dtgd_payable_detail').datagrid('acceptChanges');
					var form_data = $.serializeObject($('#form'));
					$.post("/cs/apmgt/wpAction!income.action", {
						form : JSON.stringify(form_data),
						detail : JSON.stringify($('#dtgd_payable_detail').datagrid('getRows'))
					}, function(result) {
						var result = JSON.parse(result);
						if (result.success) {
							$(dtgd).datagrid('load');
							$('#dlgRtof').dialog('close');
							$(dtgd).datagrid('clearSelections');
							window.apmgt.waitPayment.toolbarDisable();
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
					$('#dlgRtof').dialog('close');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				//接口获取账号
				var index = $("#dtgd").datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
				var result = eval("(" + $.ajax( {
					url : "/cs/bset/CollectpayaccountAction!findBankAccountNo.action",
					dataType : "json/xml/html",
					data : {
						"accountCode" : $("#dtgd").datagrid('getRows')[index].supplierNo
					},
					async : false,
					cache : false,
					type : "post"
				}).responseText + ")");
				
				var form_data = {
					supplierNo : rows[0].supplierNo,
					supplierName : rows[0].supplierName,
					bankAccountNo : '',
					currencyType : rows[0].currencyType,
					payBeAmount : 0,
					payAmount : 0,
					balance : 0
				};
				var details = [];
				for ( var i = 0; i < rows.length; i++) {
					form_data.payBeAmount += rows[i].balance;
					details[i] = {
						exchangeNo : rows[i].exchangeNo,
						tradeDate : rows[i].tradeDate,
						balance : rows[i].balance,
						payAmount : rows[i].balance,
						pablId : rows[i].id,
						reverseHas : rows[i].reverseHas,
						currencyType :rows[i].currencyType
					};
				}
				form_data.payAmount = form_data.payBeAmount;
				$("#payBeAmount").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$("#payAmount").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$("#balance").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$("#bankRate").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$("#exchangeProfitLoss").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$("#factorage").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$('#form').form('load', form_data);
				if(result.success){
					if(result.obj!=undefined){
						var json=JSON.parse(result.obj);
						if(json[0].account!=""){
							$('#bankAccountNo').combobox({    
								data:json,    
								value:json[0].account,
							    valueField:'account',    
							    textField:'account'   
							}); 
						}
					}
					
					
				}
				$('#dtgd_payable_detail').datagrid('loadData', details);
			}
		}).dialog('open');
	};

	window.apmgt.waitPayment.writeoffIncome.fundAccountOnChange = function(nv, ov) {
		var row = $(this).combogrid('grid').datagrid('getSelected');
		if (row.accountCurrency != $('#currencyType').val()) {
			$('.hl').removeClass('trhidden');
			$('#bankRate').numberbox('disableValidation');
		} else {
			$('.hl').addClass('trhidden');
			$('#bankRate').numberbox('enableValidation');
		}
	};

	window.apmgt.waitPayment.writeoffIncome.bankRateOnChange = function(nv, ov) {
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

	window.apmgt.waitPayment.writeoffIncome.onClickRow = function(rowIndex, rowData) {
		if (rowData.reverseHas == 2)
			return;
		first_has = true;
		$('#dtgd_payable_detail').datagrid('beginEdit', rowIndex);
	};

	window.apmgt.waitPayment.writeoffIncome.payAmountOnChange = function(newValue, oldValue) {
		oldValue = isNaN(oldValue) ? 0 : oldValue * 1;
		newValue = isNaN(newValue) ? 0 : newValue * 1;
		if (first_has) {
			first_has = false;
		} else {
			$('#payAmount').val(($('#payAmount').val() * 1) - oldValue + newValue);
			$('#balance').val($('#payBeAmount').val() * 1 - $('#payAmount').val() * 1);
		}
	};

	/**
	 * 销账-收入
	 * 
	 * @param index
	 */
	window.apmgt.waitPayment.writeoffIncomeByIndex = function(index) {
		window.apmgt.waitPayment.writeoffIncome([ $(dtgd).datagrid('getRows')[index] ]);
	};

	/**
	 * 付款申请
	 * 
	 * @param rows
	 */
	window.apmgt.waitPayment.apply = function(rows) {
		$('<div id="dlgApply">').dialog({
			title : "付款申请",
			href : $.util.resolveClientUrl('/cs/page/ap-mgt/waitPayment/apply.jsp'),
			width : 650,
			height : 380,
			top : 5,
			iconCls : 'bicon-asterisk',
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
					var rows = $('#dtgd_apply').datagrid('getRows');
					for ( var r = 0; r < rows.length; r++) {
						if (!$('#dtgd_apply').datagrid('validateRow', r)) {
							return;
						}
					}
					$('#dtgd_apply').datagrid('acceptChanges');
					var form_data = $.serializeObject($('#form'));
					$.post("/cs/apmgt/wpAction!apply.action", {
						form : JSON.stringify(form_data),
						detail : JSON.stringify($('#dtgd_apply').datagrid('getRows'))
					}, function(result) {
						var result = JSON.parse(result);
						if (result.success) {
							$(dtgd).datagrid('load');
							$('#dlgApply').dialog('close');
							$(dtgd).datagrid('clearSelections');
							window.apmgt.waitPayment.toolbarDisable();
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
					$('#dlgApply').dialog('close');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				//接口获取账号
				var index = $("#dtgd").datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
				var result = eval("(" + $.ajax( {
					url : "/cs/bset/CollectpayaccountAction!findBankAccountNo.action",
					dataType : "json/xml/html",
					data : {
						"accountCode" : $("#dtgd").datagrid('getRows')[index].supplierNo
					},
					async : false,
					cache : false,
					type : "post"
				}).responseText + ")");
				
				document.getElementById('factorage_apply').style.display = 'none';
				var form_data = {
					supplierNo : rows[0].supplierNo,
					supplierName : rows[0].supplierName,
					bankAccountNo : "",
					currencyType : rows[0].currencyType,
					deadline : rows[0].deadline,
					payBeAmount : 0,
					payAmount : 0,
					balance : 0
				};
				var details = [];
				for ( var i = 0; i < rows.length; i++) {
					form_data.payBeAmount += rows[i].balance;
					details[i] = {
						exchangeNo : rows[i].exchangeNo,
						tradeDate : rows[i].tradeDate,
						balance : rows[i].balance,
						payAmount : rows[i].balance,
						pablId : rows[i].id,
						reverseHas : rows[i].reverseHas,
						currencyType :rows[i].currencyType
					};
				}
				form_data.payAmount = form_data.payBeAmount;
				$("#payBeAmount").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$("#payAmount").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$("#balance").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$("#factorage").numberbox({prefix:$.convertCurrencyUnit(rows[0].currencyType)});
				$('#form').form('load', form_data);
				if(result.success){
					if(result.obj!=undefined){
						if(result.obj!="null"&&result.obj!="[]"){
							var json=JSON.parse(result.obj);
							$('#bankAccountNo').combobox({    
								data:json,    
								value:json[0].account,
							    valueField:'account',    
							    textField:'account'   
							}); 
						}
					}
				}
				
				$('#dtgd_apply').datagrid('loadData', details);
			}
		}).dialog('open');
	};

	window.apmgt.waitPayment.apply.onClickRow = function(rowIndex, rowData) {
		if (rowData.reverseHas == 2)
			return;
		first_has = false;
		$('#dtgd_apply').datagrid('beginEdit', rowIndex);
	};

	window.apmgt.waitPayment.apply.payAmountOnChange = function(newValue, oldValue) {
		oldValue = isNaN(oldValue) ? 0 : oldValue * 1;
		newValue = isNaN(newValue) ? 0 : newValue * 1;
		if (first_has) {
			$('#payAmount').numberbox('setValue',($('#payAmount').numberbox('getValue') * 1 - oldValue + newValue));
			$('#balance').numberbox('setValue',($('#balance').numberbox('getValue') * 1 + oldValue - newValue));
//			first_has = false;
		} else {
//			alert(oldValue)
//			alert(newValue)
//			$('#payAmount').numberbox('setValue',$('#payAmount').numberbox('getValue') * 1 - oldValue + newValue);
//			$('#balance').numberbox('setValue',$('#payBeAmount').numberbox('getValue') * 1 - $('#payAmount').numberbox('getValue') * 1);
			first_has = true;
		}
	};

	/**
	 * 付款申请
	 * 
	 * @param index
	 */
	window.apmgt.waitPayment.applyByIndex = function(index) {
		window.apmgt.waitPayment.apply([ $(dtgd).datagrid('getRows')[index] ]);
	};

	/**
	 * 按钮事件绑定
	 */
	window.apmgt.waitPayment.bindToolBarEvent = function() {
		$('#btn_add').click(function() {
			window.apmgt.waitPayment.add();
		});
		$('#btn_revocation').click(function() {
			if (!$('#btn_revocation').linkbutton('options').disabled) {
				window.apmgt.waitPayment.revocation($(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected')));
			}
		});
		$('#btn_writeoff').click(function() {
			if (!$('#btn_writeoff').linkbutton('options').disabled) {
				var rows = $(dtgd).datagrid('getSelections');
				var balance = 0;
				for ( var i = 0; i < rows.length; i++) {
					balance += rows[i].balance;
				}
				if (balance > 0) {
					window.apmgt.waitPayment.writeoffPay(rows);
				} else {
					window.apmgt.waitPayment.writeoffIncome(rows);
				}
			}
		});
		$('#btn_apply').click(function() {
			if (!$('#btn_apply').linkbutton('options').disabled) {
				window.apmgt.waitPayment.apply($(dtgd).datagrid('getSelections'));
			}
		});

		$('#btnOpenView').click(function() {
			if (!$('#btnOpenView').linkbutton('options').disabled) {

			}
		});
		$('#btn_y').click(function() {
			if (!$('#btn_y').linkbutton('options').disabled) {
				var index = $("#dtgd").datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
				window.apmgt.waitPayment.btnY(index);
			}
		});
		$('#btn_n').click(function() {
			if (!$('#btn_n').linkbutton('options').disabled) {
				var index = $("#dtgd").datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
				window.apmgt.waitPayment.btnN(index);
			}
		});
		$('#btn_query').click(function(){
			window.apmgt.waitPayment.Search();
		});
		
		$('#btnSearch').click(function(){
			window.apmgt.waitPayment.ZhuSearch();
		});
		
		$("#btn_view").click(function(){
			if (!$('#btn_view').linkbutton('options').disabled) {
				var index = $("#dtgd").datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
				window.apmgt.waitPayment.ChakanDanju(index);
			}
			
		});
	};
	
	window.apmgt.waitPayment.ChakanDanju=function(index){
		var type = $("#dtgd").datagrid('getRows')[index].type;
		var code = $("#dtgd").datagrid('getRows')[index].exchangeNo;
		var content = '';
		if(type==1) {
			content = '<iframe src="http\://119.90.57.77\:8980/order/business/menu/form/xoForm2.jsp?numBill='+code+'&source=cs&ticket='+ticket+'&uid='+uid+'" style="width:99%;height:99%; overflow: hidden;">aaaaa</iframe>';
		} else if(type==2) {
			content = '<iframe src="http\://119.90.57.77\:8980/order/business/menu/form/yjxoForm2.jsp?numBill='+code+'&source=cs&ticket='+ticket+'&uid='+uid+'" style="width:99%;height:99%; overflow: hidden;">aaaaa</iframe>';
		} else if(type==3) {
			content = '<iframe src="http\://119.90.57.77\:8980/order/business/menu/form/fkxoForm2.jsp?numBill='+code+'&source=cs&ticket='+ticket+'&uid='+uid+'" style="width:99%;height:99%; overflow: hidden;">aaaaa</iframe>';
		}
		$('<div id="dlg_writeoff">').dialog({
			title : "查看单据",
			content : content,
			fit : true,
			top : 5,
			iconCls : 'bicon-tag',
			resizable : false,
			modal : true,
			closed : true,
			cache : false,
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				
			}
		}).dialog('open');
	}
	
	window.apmgt.waitPayment.btnY=function(index){
		var result = eval("(" + $.ajax( {
			url : "/cs/apmgt/wpAction!tngyiButongyi.action",
			dataType : "json/xml/html",
			data : {
				"status" : 1,
				"id" : $("#dtgd").datagrid('getRows')[index].id,
				"exchangeNo" : $("#dtgd").datagrid('getRows')[index].exchangeNo
			},
			async : false,
			cache : false,
			type : "post"
		}).responseText + ")");
		if (result.success) {
			$(dtgd).datagrid('load');
			$(dtgd).datagrid('clearSelections');
			window.apmgt.waitPayment.toolbarDisable();
			$.messager.show({
				title : '提示',
				msg : '同意作废成功',
				showType : 'slide'
			});
		}else{
			$(dtgd).datagrid('load');
			$(dtgd).datagrid('clearSelections');
			window.apmgt.waitPayment.toolbarDisable();
			$.messager.show({
				title : '提示',
				msg : '同意作废失败',
				showType : 'slide'
			});
		}
//		$('#btn_y').linkbutton('disable');
//		$('#btn_n').linkbutton('disable');
		
	};
	
	window.apmgt.waitPayment.btnN=function(index){
		var result = eval("(" + $.ajax( {
			url : "/cs/apmgt/wpAction!tngyiButongyi.action",
			dataType : "json/xml/html",
			data : {
				"status" : 0,
				"id" : $("#dtgd").datagrid('getRows')[index].id,
				"exchangeNo" : $("#dtgd").datagrid('getRows')[index].exchangeNo
			},
			async : false,
			cache : false,
			type : "post"
		}).responseText + ")");
		if (result.success) {
			$("#dtgd").datagrid('load');
			$.messager.show({
				title : '提示',
				msg : '不同意作废成功',
				showType : 'slide'
			});
		}else{
			$.messager.show({
				title : '提示',
				msg : '不同意作废失败',
				showType : 'slide'
			});
		}
		$('#btn_y').linkbutton('disable');
		$('#btn_n').linkbutton('disable');
	};
	
	window.apmgt.waitPayment.ZhuSearch=function(){
		if (!$("#waitPayment_form").form('validate')) {
			return;
		}
		var url="/cs/apmgt/wpAction!datagrid.action?abc=1";
		if($("#tradeDateQi").datebox('getValue')!=""&&$("#tradeDateQi").datebox('getValue')!=undefined){
			url+="&tradeDateQi="+$("#tradeDateQi").combobox('getValue');
		}
		if($("#tradeDateShi").datebox('getValue')!=""&&$("#tradeDateShi").datebox('getValue')!=undefined){
			url+="&tradeDateShi="+$("#tradeDateShi").combobox('getValue');
		}
		if($("#deadlineShi").datebox('getValue')!=""&&$("#deadlineShi").datebox('getValue')!=undefined){
			url+="&deadlineShi="+$("#deadlineShi").combobox('getValue');
		}
		if($("#supplierNo1").combogrid('getValue')!=""&&$("#supplierNo1").combogrid('getValue')!=undefined){
			url+="&supplierNo="+$("#supplierNo1").combogrid('getValue');
		}
		if($("#exchangeNo").val()!=""&&$("#exchangeNo").val()!=undefined){
			url+="&exchangeNo="+$("#exchangeNo").val();
		}
		if($("#orderNo").val()!=""&&$("#orderNo").val()!=undefined){
			url+="&orderNo="+$("#orderNo").val();
		}
		if($("#affiliationNo1").combobox('getValue')!=""&&$("#affiliationNo1").combobox('getValue')!=undefined){
			url+="&affiliationNo="+$("#affiliationNo1").combobox('getValue');
		}
		if($("#productNo").val()!=""&&$("#productNo").val()!=undefined){
			url+="&productNo="+$("#productNo").val();
		}
		if($("#groupNumber").val()!=""&&$("#groupNumber").val()!=undefined){
			url+="&groupNumber="+$("#groupNumber").val();
		}
		
		$(dtgd).datagrid({
			url:url
		});
		$(dtgd).datagrid('clearSelections');
		window.apmgt.waitPayment.toolbarDisable();
		
	};
	
//	window.apmgt.waitPayment.Search=function(){
//		$('<div id="apmgt_waitPayment_search">').dialog({
//			title : "查询供应商未销账账款",
//			href : $.util.resolveClientUrl('/cs/page/ap-mgt/waitPayment/search.jsp'),
//			width : 350,
//			height : 300,
//			top : 5,
//			iconCls : 'bicon-asterisk',
//			resizable : false,
//			modal : true,
//			closed : true,
//			cache : false,
//			onClose : function() {
//				$(this).dialog('destroy');
//			},
//			onLoad : function() {
//				var date=new Date();
//				var d=date.getMonth()+1;
//				var date =  date.getFullYear()+'-'+d+'-01';
//				var date1=new Date();
//				var d1=date1.getMonth()+1;
//				var date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
//				$("#tradeDateQi").datebox('setValue',date);
//				$("#tradeDateShi").datebox('setValue',date1);
//				$("#deadlineQi").datebox('setValue',date);
//				$("#deadlineShi").datebox('setValue',date1);
//				
//				$("#waitPayment_search").click(function() {
//					if (!$("#apmgt_waitPayment_search_form").form('validate')) {
//						return;
//					}
////					var formData = $.serializeObject($("#apmgt_waitPayment_search_form"));
////					$.post("/cs/apmgt/wpAction!findAllSearch.action", formData, function(result) {
////						var result = JSON.parse(result);
////						if (result.success) {
////							$(dtgd).datagrid('loadData',result.obj);
////						}else{
////							$(dtgd).datagrid('loadData',{total:0,rows:[]});
////							$.messager.show({
////								title : '提示',
////								msg : '未找到供应商未销账账款信息',
////								showType : 'slide'
////							});
////						}
////						$('#apmgt_waitPayment_search').dialog('close');
////					});
//					var url="/cs/apmgt/wpAction!datagrid.action";
//					if($("#tradeDateQi").datebox('getValue')!=""&&$("#tradeDateQi").datebox('getValue')!=undefined){
//						url+="?tradeDateQi="+$("#tradeDateQi").combobox('getValue');
//					}
//					if($("#tradeDateShi").datebox('getValue')!=""&&$("#tradeDateShi").datebox('getValue')!=undefined){
//						url+="&tradeDateShi="+$("#tradeDateShi").combobox('getValue');
//					}
//					if($("#deadlineShi").datebox('getValue')!=""&&$("#deadlineShi").datebox('getValue')!=undefined){
//						url+="&deadlineShi="+$("#deadlineShi").combobox('getValue');
//					}
//					if($("#supplierNo1").combobox('getValue')!=""&&$("#supplierNo1").combobox('getValue')!=undefined){
//						url+="&supplierNo="+$("#supplierNo1").combobox('getValue');
//					}
//					if($("#exchangeNo").val()!=""&&$("#exchangeNo").val()!=undefined){
//						url+="&exchangeNo="+$("#exchangeNo").val();
//					}
//					if($("#orderNo").val()!=""&&$("#orderNo").val()!=undefined){
//						url+="&orderNo="+$("#orderNo").val();
//					}
//					if($("#affiliationNo").combobox('getValue')!=""&&$("#affiliationNo").combobox('getValue')!=undefined){
//						url+="&affiliationNo="+$("#affiliationNo").combobox('getValue');
//					}
//					if($("#productNo").val()!=""&&$("#productNo").val()!=undefined){
//						url+="&productNo="+$("#productNo").val();
//					}
//					
//					$(dtgd).datagrid({
//						url:url
//					});
//					
//					$('#apmgt_waitPayment_search').dialog('close');
//					
//				});
//				$("#waitPayment_close").click(function() {
//					$('#apmgt_waitPayment_search').dialog('close');
//				});
//			}
//		}).dialog('open');
//	};

	window.apmgt.waitPayment.cancel.ok = function(index) {

	};

	/**
	 * 验证规则
	 */
	$.extend($.fn.validatebox.defaults.rules, {
		amount : {
			validator : function(value, opt) {
				var rules = $.fn.validatebox.defaults.rules;
				var row = $('#' + opt[0]).datagrid('getRows')[$.getDatagridRowIndex(this)];
				if (row.balance > 0) {
					rules.amount.message = '必须在0~' + row.balance + '之间。';
					return value <= row.balance && value > 0;
				} else {
					rules.amount.message = '必须在' + row.balance + '~0之间。';
					return value >= row.balance && value < 0;
				}
			},
			message : '填写金额不能超出范围！'
		}
	});

	window.apmgt.waitPayment.waitremind=function(){
		//先查询是否有需要提醒的数据
		$.post("/cs/apmgt/waitPaymentRemindAction!findAll.action", function(result) {
			var result = JSON.parse(result);
			if(result.rows.length>0){
				$('<div id="waitremind">').dialog({
					title : "应付账款账期提醒",
					href : $.util.resolveClientUrl('/cs/page/ap-mgt/waitPayment/waitremind.jsp'),
					width : 555,
					height : 423,
					top : 5,
					iconCls : 'bicon-plus',
					resizable : false,
					modal : true,
					closed : true,
					cache : false,
					onClose : function() {
						$(this).dialog('destroy');
					},
					onLoad : function() {
						$('#waitremind_dg').datagrid({    
						    width : 540 ,
						    height : 345,
						    pagination:true,
						    showFooter:true,
							pagePosition:'both',
							rownumbers:true,
							method:'get',
							border:false,
						    url:'/cs/apmgt/waitPaymentRemindAction!findAll.action',
						    rowStyler:window.apmgt.waitPayment.styler,
						    columns:[[  
								{field:'deadline',title:'结算期限',width:100}, 
								{field:'supplierNo',title:'应付账户',width:100,align:'right'}, 
								{field:'payBeAmount',title:'应收金额',width:100,align:'left',formatter:window.apmgt.waitPayment.amountFormatter},  
								{field:'payAmount',title:'已收金额',width:100,formatter:window.apmgt.waitPayment.amountFormatter},
								{field:'balance',title:'余额',width:100,formatter:window.apmgt.waitPayment.amountFormatter},
								{field:'currencyType',title:'货币',hidden:true,width:100,align:'right'}
						    ]]    
						});

						$("#waitremindClose").click(function(){
							$('#waitremind').dialog('close');
						});
					}
				}).dialog('open');
			}
		});
		
		
	};
	
	window.apmgt.waitPayment.styler=function(index,row){
		var date1=new Date();
		var d1=date1.getMonth()+1;
		var date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
		if(row.deadline<date1){
			return 'background-color:#D1EEEE';  
		}
	};
	
	window.apmgt.waitPayment.StatusStyler = function(index,row){
		if(row.abnormalStatus==2){
			return 'background-color:red';
		}else if(row.yajinSort==10){
			return 'background-color:#CFCFCF';
		}
	}
	
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
		
		$("#deadlineShi").datebox('setValue', '');
		$("#tradeDateQi").datebox('setValue',date);
		$("#tradeDateShi").datebox('setValue',date1);
////		$("#deadlineQi").datebox('setValue',date);
//		$("#deadlineShi").datebox('setValue',date1);
		
		window.apmgt.waitPayment.waitremind();
//		window.apmgt.waitPayment.loadPageBar(); //是否显示单选多选
		window.apmgt.waitPayment.bindToolBarEvent();
		window.apmgt.waitPayment.toolbarDisable();
	});

})(jQuery);