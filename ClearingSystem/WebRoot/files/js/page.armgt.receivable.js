(function($) {

	$.util.namespace("armgt.receivable");
	$.util.namespace("armgt.receivable.add");
	$.util.namespace("armgt.receivable.notice");
	$.util.namespace("armgt.receivable.input");
	$.util.namespace("armgt.receivable.writeoff");
	$.util.namespace("armgt.receivable.writeoffIncome");
	$.util.namespace("armgt.receivable.writeoffRefund");
	$.util.namespace("armgt.receivable.apply");

	var dtgd = '#dtgd';

	/**
	 * 验证规则
	 */
	// $.extend($.fn.validatebox.defaults.rules, {
	// orderCode : {
	// validator : function(value, opt) {
	// var rules = $.fn.validatebox.defaults.rules;
	// var result = eval("(" + $.ajax( {
	// url : "/cs/armgt/receivable!OrderSearch.action",
	// dataType : "json/xml/html",
	// data : {
	// "accountCode" : value
	// },
	// async : false,
	// cache : false,
	// type : "post"
	// }).responseText + ")");
	// if(result.success){
	// //var json=JSON.parse(result.obj);
	// $('#affiliationPerson').val(json[0].affiliationPerson);
	// $('#affiliationNo').val(json[0].affiliationNo);
	// $("#affiliationNo").combogrid({
	// panelWidth:265,
	// data:json,
	// value:'0',
	// idField:'affiliationNo',
	// textField:'affiliationPerson',
	// columns:[[
	// {field:'affiliationPerson',title:'姓名',width:60},
	// {field:'sex',title:'性别',width:60},
	// {field:'dept',title:'部门',width:60},
	// {field:'empId',title:'员工号',width:60}
	// ]],
	// onHidePanel: function(){
	// $('#affiliationPerson').val($('#affiliationNo').combogrid('getText'));
	// }});
	//					
	// $("#customerNo_id").combogrid({
	//						
	// });
	// }
	//				
	// return result.success==true;
	// },
	// message : '账户编码不存在，请重新输入！'
	// }
	// });
	window.armgt.receivable.optionsFormatter = function(value, row, index) {
		if (!row.id)
			return;
		var options = "";
		if (row.cancelStatus == 2) {
			if (btn_yn_enable == "enable") {
				options += ' <i class="bicon-ok-circle" onclick="window.armgt.receivable.btnY($.getDatagridRowIndex(this))"></i>';
				options += ' <i class="bicon-ban-circle" onclick="window.armgt.receivable.btnN($.getDatagridRowIndex(this))"></i>';
			}
		}else{
			if (row.incomeAmount == 0) {
				options += ' <i class="bicon-edit" onclick="window.armgt.receivable.deadline($.getDatagridRowIndex(this));"></i>';
			}
			if (apply_power && row.balance < 0) {
				options += ' <i class="bicon-thumbs-up" onclick="window.armgt.receivable.applyByIndex($.getDatagridRowIndex(this));"></i>';
			} else {
				if (row.balance < 0)
					options += ' <i class="bicon-tag" onclick="window.armgt.receivable.writeoffRefundByIndex($.getDatagridRowIndex(this));"></i>';
				else
					options += ' <i class="bicon-tag" onclick="window.armgt.receivable.writeoffIncomeByIndex($.getDatagridRowIndex(this));"></i>';
			}
			if (row.revocationHas == 1) {
				options += ' <i class="bicon-remove" onclick="window.armgt.receivable.revocation($.getDatagridRowIndex(this));"></i>';
			}
		} 
		if (row.type < 4&&row.noticeNo.indexOf('*')==-1) {
			options += ' <i class="bicon-folder-open" onclick="window.armgt.receivable.incDanjuWin($.getDatagridRowIndex(this))"></i>';
		}
		return options;
	};

	window.armgt.receivable.deadlineStyler = function(value, row, index) {
		if (row.deadlineHas == 1) {
			return 'background-color:#1ABC9C;';
		}
	};
	
	window.armgt.receivable.incomeStyler=function(value, row, index) {
		if(row.deadline==undefined&&row.affiliationPerson==undefined){
			return 'background-color:#CFCFCF;';
		}
	}

	window.armgt.receivable.typeFormatter = function(value, row, index) {
		if (!row.id)
			return;
		if (value == 1) {
			return "业务应收";
		} else if (value == 2) {
			return "业务押金";
		} else if (value == 3) {
			return "业务应退";
		} else if (value == 4) {
			return "预收";
		} else if (value == 5) {
			return "押金";
		} else if (value == 6) {
			return "其它";
		}
	};

	window.armgt.receivable.amountFormatter = function(value, row, index) {
		if(row.currencyType==undefined){
			return $.formatCurrencySign(value, "CNY");
		}
		return $.formatCurrencySign(value, currency_local);
	};
	
	window.armgt.receivable.amountFormatter2 = function(value, row, index) {
		if(row.affiliationPerson==undefined){
			return $.formatCurrencySign(value, "CNY");
		}else{
			return value;
		}
	};

	window.armgt.receivable.toolbarDisable = function() {
		$('#btn_revocation').linkbutton('disable');
		$('#btn_change_deadline').linkbutton('disable');
		$('#btn_writeoff').linkbutton('disable');
		$('#btn_apply').linkbutton('disable');
		$('#btn_view').linkbutton('disable');
		$('#btn_notice').linkbutton('disable');
		$('#btn_y').linkbutton('disable');
		$('#btn_n').linkbutton('disable');
	};

	/**
	 * 单选模式工具栏按钮显示影藏
	 */
	window.armgt.receivable.toolBarUpdate = function(row) {
		window.armgt.receivable.toolbarDisable();
		if(row.cancelStatus == 2){
			$('#btn_notice').linkbutton('disable');
			$('#btn_y').linkbutton(btn_yn_enable);
			$('#btn_n').linkbutton(btn_yn_enable);
		}else{
			if (row.incomeAmount == 0) {
				$('#btn_change_deadline').linkbutton(btn_change_deadline_enable);
			}
			if (apply_power && row.balance < 0) {
				$('#btn_apply').linkbutton(btn_apply_enable);
			} else {
				$('#btn_writeoff').linkbutton(btn_writeoff_enable);
			}
			if (row.revocationHas == 1) {
				$('#btn_revocation').linkbutton(btn_revocation_enable);
			}
		}
		if (row.type < 4&&row.noticeNo.indexOf('*')==-1) {
			$('#btn_view').linkbutton(btn_view_enable);
		}
	};

	/**
	 * 多选模式工具栏按钮显示影藏
	 */
	window.armgt.receivable.toolBarUpdateBySelects = function(rows) {
		window.armgt.receivable.toolbarDisable();

		var balance = 0;
		for ( var i = 0; i < rows.length; i++) {
			balance += rows[i].balance;
			if (rows[i].cancelStatus == 2 || rows[i].originalPaymentMethod == 2
					|| rows[i].customerNo != rows[0].customerNo)
				return;
		}
		if (apply_power && balance < 0) {
			$('#btn_apply').linkbutton(btn_apply_enable);
		} else {
			$('#btn_writeoff').linkbutton(btn_writeoff_enable);
		}
	};

	window.armgt.receivable.XuanzhongQuxiao = function(row, sum) {
		var getData = $(dtgd).datagrid('getData');
		if (sum == 1) {
			for ( var i = 0; i < getData.rows.length; i++) {
				if ("*" + row.noticeNo == getData.rows[i].noticeNo) {
					$(dtgd).datagrid('selectRow', i);
					break;
				}
				if (row.noticeNo.substr(1, row.noticeNo.length) == getData.rows[i].noticeNo) {
					$(dtgd).datagrid('selectRow', i);
					break;
				}
			}
		} else {
			for ( var f = 0; f < getData.rows.length; f++) {
				if ("*" + row.noticeNo == getData.rows[f].noticeNo) {
					var index = $(dtgd)
							.datagrid('getRowIndex', getData.rows[f]);
					$(dtgd).datagrid('unselectRow', f);
					break;
				}
				if (row.noticeNo.substr(1, row.noticeNo.length) == getData.rows[f].noticeNo) {
					$(dtgd).datagrid('unselectRow', f);
					break;
				}
			}

		}
	};

	window.armgt.receivable.onSelect = function(index, row) {
		var selectRows = $(dtgd).datagrid('getSelections');
		var flog = true;
		for(var i = 0; i <selectRows.length; i++){
			if(selectRows[i].noticeNo == "*"+row.noticeNo){
				flog = false;
				break;
			}
			if (row.noticeNo.substr(1, row.noticeNo.length) == selectRows[i].noticeNo) {
				flog = false;
				break;
			}
		}
		if(flog){
			window.armgt.receivable.XuanzhongQuxiao(row, 1);
		}
		
		 var selectRows = $(dtgd).datagrid('getSelections');
		 if (selectRows.length == 1) {
			 window.armgt.receivable.toolBarUpdate(selectRows[0]);
//			 $('#btn_notice').linkbutton('enable');
		 } else {
			 window.armgt.receivable.toolBarUpdateBySelects(selectRows);
//			 $('#btn_notice').linkbutton('disable');
		 }
	};

	window.armgt.receivable.onUnselect = function(index, row) {
		var selectRows = $(dtgd).datagrid('getSelections');
		var flog = false;
		for(var i = 0; i <selectRows.length; i++){
			if(selectRows[i].noticeNo == "*"+row.noticeNo){
				flog = true;
				break;
			}
			if (row.noticeNo.substr(1, row.noticeNo.length) == selectRows[i].noticeNo) {
				flog = true;
				break;
			}
		}
		if(flog){
			window.armgt.receivable.XuanzhongQuxiao(row, 0);
		}
		
		 var selectRows = $(dtgd).datagrid('getSelections');
		 if (selectRows.length == 1) {
			 window.armgt.receivable.toolBarUpdate(selectRows[0]);
//			 $('#btn_notice').linkbutton('enable');
		 } else if (selectRows.length > 1) {
			 window.armgt.receivable.toolBarUpdateBySelects(selectRows);
//			 $('#btn_notice').linkbutton('disable');
		 } else {
			 window.armgt.receivable.toolbarDisable();
		 }
	};

	window.armgt.receivable.bindToolTip = function(jqs) {
		jqs.click(function() {
			if ($(this).attr("class").indexOf("tooltip-f") > -1) {
				return;
			}
			var index = $.getDatagridRowIndex(this);
			var row = $(dtgd).datagrid('getRows')[index];
			if (row.deadlineHas == 0) {
				return;
			}
			var querys = {};
			$(this).tooltip({
				content : $('<table></table>'),
				trackMouse : false,
				showDelay : 2,
				showEvent : "click",
				hideDelay : 1,
				onShow : function() {
					var t = $(this);
					t.tooltip('tip').unbind().bind('mouseenter', function() {
						t.tooltip('show');
					}).bind('mouseleave', function() {
						t.tooltip('hide');
					});
				},
				onUpdate : function(cc) {
					cc.datagrid({
						width : 380,
						height : 'auto',
						rownumbers : true,
						singleSelect : true,
						method : 'get',
						border : true,
						idField : "id",
						url : '/cs/armgt/receivable!deadlineDtgd.action',
						queryParams : {
							rcvbid : row.id
						},
						columns : [ [ {
							field : 'modifiedBefore',
							title : '变更前',
							width : 70
						}, {
							field : 'modifiedOn',
							title : '变更后',
							width : 70
						}, {
							field : 'remark',
							title : '备注',
							width : 200
						} ] ]
					});
				}
			}).tooltip('show');
		});
	};

	window.armgt.receivable.stylerdatagrid=function(index,row){
		if(row.yajinSort==10){
			return 'background-color:#CFCFCF';
		}
		
	}
	
	
	window.armgt.receivable.onLoadSuccess = function(data) {
		var jqs = $('.datagrid-view2 .datagrid-body .datagrid-row td[field=deadline] .datagrid-cell');
		window.armgt.receivable.bindToolTip(jqs);
	};

	/**
	 * 新建
	 */
	window.armgt.receivable.add = function() {
		$('<div id="dlgAdd">')
				.dialog(
						{
							title : "新建",
							href : $.util
									.resolveClientUrl('/cs/page/ar-mgt/receivable/add.jsp'),
							width : 300,
							height : 'auto',
							top : 5,
							iconCls : 'bicon-edit',
							resizable : false,
							modal : true,
							closed : true,
							cache : false,
							shadow : false,
							buttons : [
									{
										text : '<i class="fa fa-save"></i> 确认',
										handler : function() {
											if (!$('#form').form('validate')) {
												return;
											}
//											alert($("#customerNo_id").combogrid("getValue")+"123");
											if($("#customerName").val()==""){
												$("#customerName").val($("#customerNo_id").combogrid('getText'));
											}
											
											var form_data = $.serializeObject($('form'));
											console.info(form_data);
											$.post("/cs/armgt/receivable!add.action",form_data,function(result) {
												var result = JSON.parse(result);
												if (result.success) {
													$(dtgd).datagrid('insertRow',{
														index : 0,
														row : result.obj
													});
													$('#dlgAdd').dialog('close');
													$('#customerNo').combogrid({
														url:'/cs/armgt/receivableViewAction!findAllCustomerNo.action'
													});
												}
												$.messager.show({
													title : '提示',
													msg : result.msg,
													showType : 'slide'
												});
											});
										}
									},
									{
										text : '<i class="fa fa-times"></i> 放弃',
										handler : function() {
											$('#dlgAdd').dialog('close');
										}
									} ],
							onClose : function() {
								$(this).dialog('destroy');
							},
							onLoad : function() {
								var result = eval("("+ $.ajax({
									url : "/cs/armgt/receivable!findAffiliationNo.action",
									dataType : "json/xml/html",
									async : false,
									cache : false,
									type : "post"
								}).responseText + ")");
								var json = JSON.parse(result.obj);
								$('#affiliationPerson').val(json[0].name);
								$('#affiliationNo').val(json[0].uid);
								$("#affiliationNo").combogrid({
									panelWidth : 265,
									data : json,
									value : '0',
									idField : 'uid',
									textField : 'name',
									columns : [ [
									{
										field : 'name',
										title : '姓名',
										width : 60
									},
									{
										field : 'sex',
										title : '性别',
										width : 60,
										formatter : function(value,row,index) {
											if (value == '1') {
												return '男';
											} else if (value == '0') {
												return '女';
											}
										}
									},
									{
										field : 'dept_name',
										title : '部门',
										width : 60
									},
									{
										field : 'empId',
										title : '员工号',
										width : 60
									} ] ],
									onHidePanel : function() {
										$('#affiliationPerson').val(
											$('#affiliationNo').combogrid('getText'));
										}
									});
							}
						}).dialog('open');
	};

	window.armgt.receivable.add.typeOnChange = function(newValue, oldValue) {
		if (newValue == "4") {
			$('#incomeBeAmount').numberbox('options').min = 0;
			$('#incomeBeAmount').numberbox('setValue', 0);
		} else {
			$('#incomeBeAmount').numberbox('options').min = -99999999;
		}
	};

	/**
	 * 撤销
	 * 
	 * @param index
	 */
	window.armgt.receivable.revocation = function(index) {
		$.messager.confirm('确认', '您确认想要撤销记录吗？', function(r) {
			if (r) {
				$.post("/cs/armgt/receivable!revocation.action", {
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
						window.armgt.receivable.toolbarDisable();
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
	 * 更改结算期限
	 */
	window.armgt.receivable.deadline = function(index) {
		$('<div id="dlgDeadline">')
				.dialog(
						{
							title : "变更结算期限",
							href : $.util
									.resolveClientUrl('/cs/page/ar-mgt/receivable/deadline.jsp'),
							width : 280,
							height : 220,
							top : 5,
							iconCls : 'bicon-edit',
							resizable : false,
							modal : true,
							closed : true,
							cache : false,
							shadow : false,
							buttons : [
									{
										text : '<i class="fa fa-save"></i> 确认',
										handler : function() {
											if (!$('#form').form('validate')) {
												return;
											}
											var form_data = $.serializeObject($('#form'));
											form_data.rcvbid = $(dtgd).datagrid('getRows')[index].id;
											$.post("/cs/armgt/receivable!deadline.action",{
																form : JSON
																		.stringify(form_data)
															},
															function(result) {
																var result = JSON
																		.parse(result);
																if (result.success) {
																	$(dtgd)
																			.datagrid(
																					'updateRow',
																					{
																						index : index,
																						row : result.obj
																					});
																	var jqs = $('#datagrid-row-r1-2-'
																			+ index
																			+ ' td[field=deadline] .datagrid-cell');
																	window.armgt.receivable
																			.bindToolTip(jqs);
																	$(
																			'#dlgDeadline')
																			.dialog(
																					'close');
																}
																$.messager
																		.show({
																			title : '提示',
																			msg : result.msg,
																			showType : 'slide'
																		});
															});
										}
									},
									{
										text : '<i class="fa fa-times"></i> 放弃',
										handler : function() {
											$('#dlgDeadline').dialog('close');
										}
									} ],
							onClose : function() {
								$(this).dialog('destroy');
							},
							onLoad : function() {
								$('#form').form(
										'load',
										{
											modifiedBefore : $(dtgd).datagrid(
													'getRows')[index].deadline
										});
							}
						}).dialog('open');
	};

	/**
	 * 销账-收入
	 */
	window.armgt.receivable.writeoffIncome = function(rows) {
		$('<div id="dlg_writeoff">')
				.dialog(
						{
							title : "销账",
							href : $.util
									.resolveClientUrl('/cs/page/ar-mgt/receivable/writeoff.jsp'),
							width : 640,
							height : 480,
							top : 5,
							iconCls : 'bicon-tag',
							resizable : false,
							modal : true,
							closed : true,
							cache : false,
							buttons : [
									{
										text : '<i class="fa fa-save"></i> 确认',
										handler : function() {
											if (!$('#form').form('validate')) {
												return;
											}
											for ( var r = 0; r < $('#dtgd_wto')
													.datagrid('getRows').length; r++) {
												if (!$('#dtgd_wto').datagrid(
														'validateRow', r)) {
													return;
												}
											}
											$('#dtgd_wto').datagrid(
													'acceptChanges');
											var form_data = $.serializeObject($('#form'));
											form_data.publication = form_data.paymentMethod == 2 ? $(
													'#cardCode').combogrid(
													'grid').datagrid(
													'getSelected').publication
													: 0;
											form_data.settlement = form_data.paymentMethod == 2 ? $(
													'#cardCode').combogrid(
													'grid').datagrid(
													'getSelected').settlement
													: 0;
											console.info(form_data);
											$(this).linkbutton('disable');
											$.post("/cs/armgt/receivable!income.action",
															{
																form : JSON
																		.stringify(form_data),
																detail : JSON
																		.stringify($(
																				'#dtgd_wto')
																				.datagrid(
																						'getRows')),
															},
															function(result) {
																var result = JSON
																		.parse(result);
																$.messager
																		.show({
																			title : '提示',
																			msg : result.msg,
																			showType : 'slide'
																		});
																if (result.success) {
																	$(dtgd)
																			.datagrid(
																					'load');
																	$(
																			'#dlg_writeoff')
																			.dialog(
																					'close');
																	// $('#btn_writeoff').linkbutton('disable');
																	$(dtgd)
																			.datagrid(
																					'clearSelections');
																	window.armgt.receivable
																			.toolbarDisable();
																}
																$(this)
																		.linkbutton(
																				'enable');
															});
										}
									},
									{
										text : '<i class="fa fa-times"></i> 放弃',
										handler : function() {
											$('#dlg_writeoff').dialog('close');
										}
									} ],
							onClose : function() {
								$(this).dialog('destroy');
							},
							onLoad : function() {
								// window.armgt.receivable.writeoffIncome.paymentMethodOnChange(1,
								// 0);

								$('#cardExpirationDate').formatInput({
									cutbit : 2,
									deimiter : '/'
								});
								var form_data = {
									customerNo : rows[0].customerNo,
									customerName : rows[0].customerName,
									currencyType : currency_local,
									incomeBeAmount : 0,
									incomeAmount : 0,
									netAmount : 0,
									balance : 0
								};
								var details = [];
								for ( var i = 0; i < rows.length; i++) {
									form_data.incomeBeAmount += rows[i].balance;
									details.push({
										rcvbid : rows[i].id,
										noticeNo : rows[i].noticeNo,
										tradeDate : rows[i].tradeDate,
										balance : rows[i].balance,
										reverseHas : rows[i].reverseHas,
										incomeAmount : rows[i].balance
									});
								}
								form_data.incomeAmount = form_data.incomeBeAmount;
								form_data.netAmount = form_data.incomeBeAmount;
								form_data.paymentMethod = 4;
								$("#incomeBeAmount").numberbox({
									prefix : $.convertCurrencyUnit('CNY')
								});
								$("#incomeAmount").numberbox({
									prefix : $.convertCurrencyUnit('CNY')
								});
								$("#balance").numberbox({
									prefix : $.convertCurrencyUnit('CNY')
								});
								$("#netAmount").numberbox({
									prefix : $.convertCurrencyUnit('CNY')
								});
								$("#bankRate").numberbox({
									prefix : $.convertCurrencyUnit('CNY')
								});
								$("#exchangeProfitLoss").numberbox({
									prefix : $.convertCurrencyUnit('CNY')
								});
								$('#form').form('load', form_data);
								$('#dtgd_wto').datagrid('loadData', details);
								window.armgt.receivable.writeoffIncome
										.paymentMethodOnChange(4, 4);
								// $('#btn_writeoff').linkbutton('disable');
							}
						}).dialog('open');
	};

	window.armgt.receivable.writeoffIncomeByIndex = function(index) {
		window.armgt.receivable
				.writeoffIncome([ $(dtgd).datagrid('getRows')[index] ]);
	};

	window.armgt.receivable.writeoffIncome.paymentMethodOnChange = function(
			newValue, oldValue) {
		$('.pm').addClass('hidden');
		$('.hd' + newValue).removeClass('hidden');
		$('#bankAccountNo').validatebox('disableValidation');
		$('#cardCode').combogrid('disableValidation');
		$('#cardExpirationDate').validatebox('disableValidation');
		$('#fundAccount').combogrid('disableValidation');

		$('#tradeNo').validatebox('disableValidation');
		$('#voucherNo').validatebox('disableValidation');
		if (newValue == 2) {
			$('#cardCode').combogrid('enableValidation');
			$('#bankAccountNo').validatebox('enableValidation');
			$('#cardExpirationDate').validatebox('enableValidation');
			$('#tradeNo').validatebox('enableValidation');
		} else if (newValue == 3) {
			$('#bankAccountNo').validatebox('disableValidation');
			$('#tradeNo').validatebox('enableValidation');
		} else if (newValue == 4) {
			$('#bankAccountNo').validatebox('disableValidation');
			$('#fundAccount').combogrid('enableValidation');
			$('#voucherNo').validatebox('enableValidation');
		} else if (newValue == 6) {
			$('#bankAccountNo').validatebox('enableValidation');
			$('#fundAccount').combogrid('enableValidation');
			$('#voucherNo').validatebox('enableValidation');
		}else {
			$('#bankAccountNo').validatebox('disableValidation');
			$('#fundAccount').combogrid('enableValidation');
			$('#voucherNo').validatebox('enableValidation');
			$('#bankAccountNo').val("");
		}
		if (newValue == 2 || newValue == 3 || newValue == 4) {
			var index = $("#dtgd").datagrid('getRowIndex',
					$(dtgd).datagrid('getSelected'));
			var result = eval("(" + $.ajax({
				url : "/cs/bset/CollectpayaccountAction!findSee.action",
				dataType : "json/xml/html",
				data : {
					"cpCode" : $("#dtgd").datagrid('getRows')[index].customerNo
				},
				async : false,
				cache : false,
				type : "post"
			}).responseText + ")");
			if (result.obj!=undefined&&result.obj.cp_SettlementAccount != "") {
				$('#bankAccountNo').val(result.obj.cp_SettlementAccount);
			}
		}

		// var rules = $.fn.validatebox.defaults.rules;
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
	
	window.armgt.receivable.writeoffIncome.onHidePanel=function(){
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

	window.armgt.receivable.writeoffIncome.fundAccountOnChange = function(
			newValue, oldValue) {
		if (currency_local == $(this).combogrid('grid').datagrid('getSelected').accountCurrency) {
			$('.cy').addClass('hidden');
		} else {
			$('.cy').removeClass('hidden');
		}
	};

	window.armgt.receivable.writeoffIncome.cardCodeOnChange = function(
			newValue, oldValue) {
		$('#customerFactorage').numberbox(
				'setValue',
				$(this).combogrid('grid').datagrid('getSelected').publication
						* $('#netAmount').numberbox('getValue') / 100);
	};

	window.armgt.receivable.writeoffIncome.netAmountOnChange = function(
			newValue, oldValue) {
		if ($('#paymentMethod').combobox('getValue') != 4)
			return;
		if (currency_local == $('#fundAccount').combogrid('grid').datagrid(
				'getSelected').accountCurrency)
			$('#exchangeProfitLoss').numberbox('setValue',
					newValue - $('#incomeAmount').numberbox('getValue'));
		else
			$('#exchangeProfitLoss').numberbox(
					'setValue',
					newValue * $('#bankRate').numberbox('getValue')
							- $('#incomeAmount').numberbox('getValue'));
	};

	window.armgt.receivable.writeoffIncome.bankRateOnChange = function(
			newValue, oldValue) {
		if ($('#paymentMethod').combobox('getValue') == 4
				&& currency_local != $('#fundAccount').combogrid('grid')
						.datagrid('getSelected').accountCurrency)
			$('#exchangeProfitLoss').numberbox(
					'setValue',
					newValue * $('#netAmount').numberbox('getValue')
							- $('#incomeAmount').numberbox('getValue'));
	};

	var first_has = false;

	window.armgt.receivable.writeoff.onClickRow = function(rowIndex, rowData) {
		if (rowData.reverseHas == 2)
			return;
		first_has = false;
		$('#dtgd_wto').datagrid('beginEdit', rowIndex);
	};

	window.armgt.receivable.writeoff.incomeAmountOnChange = function(newValue,
			oldValue) {
		oldValue = isNaN(oldValue) ? 0 : oldValue * 1;
		newValue = isNaN(newValue) ? 0 : newValue * 1;
		if (first_has) {
			$('#incomeAmount')
					.numberbox(
							'setValue',
							($('#incomeAmount').numberbox('getValue') * 1
									- oldValue + newValue));
			$('#netAmount')
					.numberbox(
							'setValue',
							($('#netAmount').numberbox('getValue') * 1
									- oldValue + newValue));
			$('#balance')
					.numberbox(
							'setValue',
							($('#balance').numberbox('getValue') * 1 + oldValue - newValue));
		} else
			first_has = true;
	};

	/**
	 * 销账-退款
	 */
	window.armgt.receivable.writeoffRefund = function(rows) {
		$('<div id="dlg_writeoff">')
				.dialog(
						{
							title : "销账",
							href : $.util
									.resolveClientUrl('/cs/page/ar-mgt/receivable/refund-writeoff.jsp'),
							width : 770,
							height : 480,
							top : 5,
							iconCls : 'bicon-tag',
							resizable : false,
							modal : true,
							closed : true,
							cache : false,
							buttons : [
									{
										text : '<i class="fa fa-save"></i> 确认',
										handler : function() {
											if (!$('#form').form('validate')) {
												return;
											}
											for ( var r = 0; r < $('#dtgd_wto').datagrid('getRows').length; r++) {
												if (!$('#dtgd_wto').datagrid('validateRow', r)) {
													return;
												}
											}
											$('#dtgd_wto').datagrid('acceptChanges');
											var form_data = $.serializeObject($('#form'));
											form_data.publication = 0;
											form_data.settlement = 0;
											$(this).linkbutton('disable');
											$.post("/cs/armgt/receivable!refund.action",{
												form : JSON.stringify(form_data),
												detail : JSON.stringify($('#dtgd_wto').datagrid('getRows'))
											},
											function(result) {
												var result = JSON.parse(result);
												$.messager.show({
													title : '提示',
													msg : result.msg,
													showType : 'slide'
												});
												if (result.success) {
													$(dtgd).datagrid('load');
													$('#dlg_writeoff').dialog('close');
													$(dtgd).datagrid('clearSelections');
													window.armgt.receivable.toolbarDisable();
												}
												$(this).linkbutton('enable');
											});
										}
									},
									{
										text : '<i class="fa fa-times"></i> 放弃',
										handler : function() {
											$('#dlg_writeoff').dialog('close');
										}
									} ],
							onClose : function() {
								$(this).dialog('destroy');
							},
							onLoad : function() {
								if (rows[0].originalPaymentMethod == 2) {
									$('.xyk').removeClass('hidden');
								} else {
									$('.fxyk').removeClass('hidden');
								}
								// 接口获取账号
								var index = $("#dtgd").datagrid('getRowIndex',
										$(dtgd).datagrid('getSelected'));
								var result = eval("("+ $.ajax({
										url : "/cs/bset/CollectpayaccountAction!findSee.action",
										dataType : "json/xml/html",
										data : {
											"cpCode" : $("#dtgd").datagrid('getRows')[index].customerNo
										},
										async : false,
										cache : false,
										type : "post"
									}).responseText + ")");
								if (result.obj!=undefined&&result.obj.cp_RefundAccount != "") {
									$('#bankAccountNo').val(
											result.obj.cp_RefundAccount);
								}

								var form_data = {
									customerNo : rows[0].customerNo,
									customerName : rows[0].customerName,
									incomeBeAmount : 0,
									incomeAmount : 0,
									netAmount : 0,
									balance : 0
								};
								var details = [];
								for ( var i = 0; i < rows.length; i++) {
									form_data.incomeBeAmount += rows[i].balance;
									details.push({
										rcvbid : rows[i].id,
										noticeNo : rows[i].noticeNo,
										tradeDate : rows[i].tradeDate,
										balance : rows[i].balance,
										incomeAmount : rows[i].balance,
										originalPaymentMethod : rows[i].originalPaymentMethod,
										reverseHas : rows[i].reverseHas
									});
								}
								form_data.incomeAmount = form_data.incomeBeAmount;
								form_data.netAmount = form_data.incomeBeAmount;
								$("#incomeBeAmount").numberbox({
									prefix : $.convertCurrencyUnit('CNY')
								});
								$("#incomeAmount").numberbox({
									prefix : $.convertCurrencyUnit('CNY')
								});
								$("#balance").numberbox({
									prefix : $.convertCurrencyUnit('CNY')
								});
								$("#customerFactorage").numberbox({
									prefix : $.convertCurrencyUnit('CNY')
								});
								$("#netAmount").numberbox({
									prefix : $.convertCurrencyUnit('CNY')
								});
								$("#factorage").numberbox({
									prefix : $.convertCurrencyUnit('CNY')
								});
								$("#bankRate").numberbox({
									prefix : $.convertCurrencyUnit('CNY')
								});
								$("#exchangeProfitLoss").numberbox({
									prefix : $.convertCurrencyUnit('CNY')
								});
								$('#form').form('load', form_data);
								$('#dtgd_wto').datagrid('loadData', details);
								window.armgt.receivable.writeoffIncome
										.paymentMethodOnChange(1, 0);
							}
						}).dialog('open');
	};

	window.armgt.receivable.writeoffRefundByIndex = function(index) {
		window.armgt.receivable
				.writeoffRefund([ $(dtgd).datagrid('getRows')[index] ]);
	};

	window.armgt.receivable.writeoffRefund.originalPaymentMethodFormatter = function(
			value, row, index) {
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
		}
	};

	window.armgt.receivable.writeoffRefund.fundAccountOnChange = function(
			newValue, oldValue) {
		if (currency_local == $(this).combogrid('grid').datagrid('getSelected').accountCurrency) {
			$('.currency').addClass('hidden');
		} else {
			$('.currency').removeClass('hidden');
		}
	};

	window.armgt.receivable.writeoffRefund.netAmountOnChange = function(
			newValue, oldValue) {
		$('#exchangeProfitLoss').numberbox(
				'setValue',
				$('#incomeAmount').numberbox('getValue') * 1
						+ $('#customerFactorage').numberbox('getValue') * 1
						- newValue * $('#bankRate').numberbox('getValue'));
	};

	window.armgt.receivable.writeoffRefund.bankRateOnChange = function(
			newValue, oldValue) {
		$('#exchangeProfitLoss').numberbox(
				'setValue',
				$('#incomeAmount').numberbox('getValue') * 1
						+ $('#customerFactorage').numberbox('getValue') * 1
						- newValue * $('#netAmount').numberbox('getValue'));
	};

	/**
	 * 申请-退款
	 */
	window.armgt.receivable.apply = function(rows) {
		$('<div id="dlg_writeoff">')
				.dialog(
						{
							title : "申请退款",
							href : $.util
									.resolveClientUrl('/cs/page/ar-mgt/receivable/apply.jsp'),
							width : 770,
							height : 480,
							top : 5,
							iconCls : 'bicon-tag',
							resizable : false,
							modal : true,
							closed : true,
							cache : false,
							buttons : [
									{
										text : '<i class="fa fa-save"></i> 确认',
										handler : function() {
											if (!$('#form').form('validate')) {
												return;
											}
											for ( var r = 0; r < $('#dtgd_wto')
													.datagrid('getRows').length; r++) {
												if (!$('#dtgd_wto').datagrid(
														'validateRow', r)) {
													return;
												}
											}
											// 接口获取账号
											var index = $("#dtgd").datagrid('getRowIndex',
													$(dtgd).datagrid('getSelected'));
											var result = eval("("+ $.ajax({
													url : "/cs/bset/CollectpayaccountAction!findSee.action",
													dataType : "json/xml/html",
													data : {
														"cpCode" : $("#dtgd").datagrid('getRows')[index].customerNo
													},
													async : false,
													cache : false,
													type : "post"
												}).responseText+ ")");
											if(result.obj!=undefined&&result.obj.cp_RefundAccount!=""){
												$('#bankAccountNo').val(result.obj.cp_RefundAccount);
											}

											$('#dtgd_wto').datagrid(
													'acceptChanges');
											var form_data = $
													.serializeObject($('#form'));
											$(this).linkbutton('disable');
											$.post("/cs/armgt/receivable!apply.action",{
												form : JSON.stringify(form_data),
												detail : JSON.stringify($('#dtgd_wto').datagrid('getRows'))
											},
											function(result) {
												var result = JSON.parse(result);
												$.messager.show({
													title : '提示',
													msg : result.msg,
													showType : 'slide'
												});
												if (result.success) {
													$(dtgd).datagrid('load');
													$('#dlg_writeoff').dialog('close');
													$(dtgd).datagrid('clearSelections');
													window.armgt.receivable.toolbarDisable();
												}
												$(this).linkbutton('enable');
											});
										}
									},
									{
										text : '<i class="fa fa-times"></i> 放弃',
										handler : function() {
											$('#dlg_writeoff').dialog('close');
										}
									} ],
							onClose : function() {
								$(this).dialog('destroy');
							},
							onLoad : function() {
								// 接口获取账号
								var index = $("#dtgd").datagrid('getRowIndex',
										$(dtgd).datagrid('getSelected'));
								var result = eval("("+ $.ajax({
									url : "/cs/bset/CollectpayaccountAction!findSee.action",
									dataType : "json/xml/html",
									data : {
										"cpCode" : $("#dtgd").datagrid('getRows')[index].customerNo
									},
									async : false,
									cache : false,
									type : "post"
								}).responseText + ")");
								if (result.obj!=undefined&&result.obj.cp_RefundAccount != "") {
									$('#bankAccountNo').val(result.obj.cp_RefundAccount);
								}

								if (rows[0].originalPaymentMethod == 2) {
									$('.xyk').removeClass('hidden');
								}
								var form_data = {
									customerNo : rows[0].customerNo,
									customerName : rows[0].customerName,
									incomeBeAmount : 0,
									incomeAmount : 0,
									balance : 0
								};
								var details = [];
								for ( var i = 0; i < rows.length; i++) {
									form_data.incomeBeAmount += rows[i].balance;
									details
											.push({
												rcvbid : rows[i].id,
												noticeNo : rows[i].noticeNo,
												tradeDate : rows[i].tradeDate,
												balance : rows[i].balance,
												incomeAmount : rows[i].balance,
												reverseHas : rows[i].reverseHas,
												originalPaymentMethod : rows[i].originalPaymentMethod
											});
								}
								form_data.incomeAmount = form_data.incomeBeAmount;
								$('#form').form('load', form_data);
								$('#dtgd_wto').datagrid('loadData', details);
							}
						}).dialog('open');
	};

	window.armgt.receivable.applyByIndex = function(index) {
		// window.armgt.receivable.writeoffRefund([
		// $(dtgd).datagrid('getRows')[index] ]);//修改
		window.armgt.receivable.apply([ $(dtgd).datagrid('getRows')[index] ]);
	};

	window.armgt.receivable.apply.onClickRow = function(rowIndex, rowData) {
		if (rowData.reverseHas == 2)
			return;
		first_has = false;
		$('#dtgd_wto').datagrid('beginEdit', rowIndex);
	};

	window.armgt.receivable.apply.incomeAmountOnChange = function(newValue,
			oldValue) {
		oldValue = isNaN(oldValue) ? 0 : oldValue * 1;
		newValue = isNaN(newValue) ? 0 : newValue * 1;
		if (first_has) {
			$('#incomeAmount').numberbox('setValue',($('#incomeAmount').numberbox('getValue') * 1 - oldValue + newValue));
			$('#balance').numberbox('setValue',($('#balance').numberbox('getValue') * 1 + oldValue - newValue));
		} else
			first_has = true;
	};

	/**
	 * 按钮事件绑定
	 */
	window.armgt.receivable.bindToolBarEvent = function() {
		$('#btn_add').click(function() {
			window.armgt.receivable.add();
		});
		$('#btn_revocation').click(
				function() {
					if (!$('#btn_revocation').linkbutton('options').disabled) {
						window.armgt.receivable
								.revocation($(dtgd).datagrid('getRowIndex',
										$(dtgd).datagrid('getSelected')));
					}
				});
		$('#btn_change_deadline').click(function() {
			if (!$('#btn_change_deadline').linkbutton('options').disabled) {
				window.armgt.receivable.deadline($(dtgd).datagrid('getRowIndex',
					$(dtgd).datagrid('getSelected')));
			}
		});
		$('#btnView').click(function() {
			if (!$('#btnView').linkbutton('options').disabled) {

			}
		});
		$('#btn_writeoff').click(
				function() {
					if (!$('#btn_writeoff').linkbutton('options').disabled) {
						var rows = $(dtgd).datagrid('getSelections');
						if (rows.length <= 0) {
							$.messager.show({
								title : '操作提示',
								msg : '请选择要销账的记录！',
								showType : 'slide',
							});
							return;
						}
						var balance = 0;
						for ( var i = 0; i < rows.length; i++) {
							balance += rows[i].balance;
						}
						if (balance >= 0)
							window.armgt.receivable.writeoffIncome($(dtgd)
									.datagrid('getSelections'));
						else
							window.armgt.receivable.writeoffRefund($(dtgd)
									.datagrid('getSelections'));
					}
				});

		$('#btn_notice').click(function() {
			window.armgt.receivable.notice.openOnSelect();
		});

		$('#btn_apply').click(
				function() {
					if (!$('#btn_apply').linkbutton('options').disabled) {
						window.armgt.receivable.apply($(dtgd).datagrid(
								'getSelections'));
					}
				});

		$('#btnSearch').click(function() {
			window.armgt.receivable.ZhuSearch();
		});

		$("#btn_view").click(
				function() {
					if (!$('#btn_view').linkbutton('options').disabled) {
						var index = $("#dtgd").datagrid('getRowIndex',
								$(dtgd).datagrid('getSelected'));
						window.armgt.receivable.incDanjuWin(index);
					}
				});
		
		$('#btn_y').click(function() {
			if (!$('#btn_y').linkbutton('options').disabled) {
				var index = $("#dtgd").datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
				window.armgt.receivable.btnY(index);
			}
		});
		$('#btn_n').click(function() {
			if (!$('#btn_n').linkbutton('options').disabled) {
				var index = $("#dtgd").datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
				window.armgt.receivable.btnN(index);
			}
		});
	};
	
	window.armgt.receivable.btnY=function(index){
		var result = eval("(" + $.ajax( {
			url : "/cs/armgt/receivable!tngyiButongyi.action",
			dataType : "json/xml/html",
			data : {
				"status" : 1,
				"id" : $("#dtgd").datagrid('getRows')[index].id,
				"noticeNo" : $("#dtgd").datagrid('getRows')[index].noticeNo
			},
			async : false,
			cache : false,
			type : "post"
		}).responseText + ")");
		if (result.success) {
			$(dtgd).datagrid('load');
			$(dtgd).datagrid('clearSelections');
			window.armgt.receivable.toolbarDisable();
			$.messager.show({
				title : '提示',
				msg : '同意作废成功',
				showType : 'slide'
			});
		}else{
			$(dtgd).datagrid('load');
			$(dtgd).datagrid('clearSelections');
			window.armgt.receivable.toolbarDisable();
			$.messager.show({
				title : '提示',
				msg : '同意作废失败',
				showType : 'slide'
			});
		}
//		$('#btn_y').linkbutton('disable');
//		$('#btn_n').linkbutton('disable');
	}
	
	window.armgt.receivable.btnN=function(index){
		var result = eval("(" + $.ajax( {
			url : "/cs/armgt/receivable!tngyiButongyi.action",
			dataType : "json/xml/html",
			data : {
				"status" : 0,
				"id" : $("#dtgd").datagrid('getRows')[index].id,
				"noticeNo" : $("#dtgd").datagrid('getRows')[index].noticeNo
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
	}

	window.armgt.receivable.incDanjuWin = function(index) {
		//获取filter和ticked
		var type = $("#dtgd").datagrid('getRows')[index].type;
		var code = $("#dtgd").datagrid('getRows')[index].noticeNo;
		var content = '';
		if (type == 1) {
			content = '<iframe src="http\://119.90.57.77\:8980/order/business/menu/form/invForm2.jsp?numBill='
					+ code
					+ '&source=cs&ticket='+ticket+'&uid='+uid+'" style="width:99%;height:99%; overflow: hidden;">aaaaa</iframe>';
		} else if (type == 2) {
			content = '<iframe src="http\://119.90.57.77\:8980/order/business/menu/form/yjinvForm2.jsp?numBill='
					+ code
					+ '&source=cs&ticket='+ticket+'&uid='+uid+'" style="width:99%;height:99%; overflow: hidden;">aaaaa</iframe>';
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
	};

	window.armgt.receivable.ZhuSearch = function() {
		if (!$("#receivable_from").form('validate')) {
			return;
		}
		var url = "/cs/armgt/receivable!datagrid.action?abc=1";
		if ($("#tradeDateQi").datebox('getValue') != ""&& $("#tradeDateQi").datebox('getValue') != undefined) {
			url += "&tradeDateQi="+ $("#tradeDateQi").combobox('getValue');
		}
		if ($("#tradeDateShi").datebox('getValue') != ""&& $("#tradeDateShi").datebox('getValue') != undefined) {
			url += "&tradeDateShi="+ $("#tradeDateShi").combobox('getValue');
		}
		if ($("#customerNo").combogrid('getValue') != ""&& $("#customerNo").combogrid('getValue') != undefined) {
			url += "&customerNo="+ $("#customerNo").combogrid('getValue');
		}
		if ($("#deadlineShi").datebox('getValue') != ""&& $("#deadlineShi").datebox('getValue') != undefined) {
			url += "&deadlineShi="+ $("#deadlineShi").datebox('getValue');
		}
		
		if ($("#noticeNo").val() != ""&& $("#noticeNo").val() != undefined) {
			url += "&noticeNo="+ $("#noticeNo").val();
		}
		if ($("#orderNo").val() != ""&& $("#orderNo").val() != undefined) {
			url += "&orderNo="+ $("#orderNo").val();
		}
		if ($("#affiliationNo2").combobox('getValue') != ""&& $("#affiliationNo2").combobox('getValue') != undefined) {
			url += "&affiliationNo="+ $("#affiliationNo2").combobox('getValue');
		}
		if($("#groupNumber").val()!=""&&$("#groupNumber").val()!=undefined){
			url+="&groupNumber="+$("#groupNumber").val();
		}
		
		$(dtgd).datagrid({
			url : url
		});
//		$(dtgd).datagrid('clearSelections');
		window.armgt.receivable.toolbarDisable();
	};
	

	window.armgt.receivable.notice.open = function(opt) {
		$('<div id="dlgNotice">')
				.dialog(
						{
							title : "通知单",
							href : $.util
									.resolveClientUrl('/cs/page/ar-mgt/receivable/notice.jsp'),
							width : 760,
							height : 500,
							top : 5,
							iconCls : 'bicon-file',
							resizable : false,
							modal : true,
							closed : true,
							cache : false,
							toolbar : [ {
								text : '打印',
								iconCls : 'bicon-print',
								handler : function() {
									$("#contents").printArea();//jquery拓展打印方法
									$.messager.show({
										title : '提示',
										msg : '打印成功',
										showType : 'slide'
									});
									$("#dlgNotice").dialog('close');
								}
							}, {
								text : 'Email',
								iconCls : 'bicon-envelope',
								handler : function() {
									//发送邮件
									$('#emailWin').window({
										title : "邮件",
										href:"/cs/page/ar-mgt/receivable/email.jsp",
										width:380,
										height:300,
										closed : true,
										collapsible : false,
										minimizable : false,
										maximizable : false,
									}).window("open");	
								}
							}, {
								text : '下载',
								iconCls : 'bicon-download-alt',
									handler : function() {
//										$.post("/cs/armgt/receivable!text.action",{
//											html : $("html").val()
//										},
//										function(result) {
//											var result = JSON.parse(result);
//											$.messager.show({
//												title : '提示',
//												msg : result.msg,
//												showType : 'slide'
//											});
//											if (result.success) {
//												$(dtgd).datagrid('load');
//												$('#dlg_writeoff').dialog('close');
//											}
//											$(this).linkbutton('enable');
//										});
								}
							} ],
							buttons : [ {
								text : '关闭',
								iconCls : 'bicon-remove',
								handler : function() {
									window.armgt.receivable.notice.close();
								}
							} ],
							onClose : function() {
								$(this).dialog('destroy');
							},
							onLoad : function() {
								window.armgt.receivable.notice.init(opt);
							}
						}).dialog('open');
	};

	//付款、催款通知单赋值
	window.armgt.receivable.notice.init = function(opt) {
		var date = new Date();
		var d = date.getMonth() + 1;
		var date = date.getFullYear() + '-' + d
				+ '-' + date.getDate();
		var arr=date.split("-");    
		var starttime=new Date(arr[0],arr[1],arr[2]);    
		var starttimes=starttime.getTime();   
		  
		var arrs=opt.deadline.split("-");    
		var lktime=new Date(arrs[0],arrs[1],arrs[2]);    
		var lktimes=lktime.getTime();   
		if(starttimes>=lktimes)    
		{   
			$("#tongzhishu").html("催款通知书");
		}else {
			$("#tongzhishu").html("付款通知书");
		}
		$("#jiesuanDate").html(opt.deadline);
		//查询账户信息
		var result = eval("(" + $.ajax( {
			 url : "/cs/bset/accountAction!findByDefaultIncAccount.action",
			 dataType : "json/xml/html",
			 async : false,
			 cache : false,
			 type : "post"
		}).responseText + ")");
		if(result.success){
			$("#kaihuren").html(result.obj[0].accountOwner);
			$("#yinhang").html(result.obj[0].accountBank);
			$("#zhanghao").html(result.obj[0].accountNumber);
		}
		$("#customerName").html(opt.data[0].customerName);
		$("#dayingDate").html(date);
		$("#customerNo1").html(opt.data[0].customerNo);
		$("#jiesuanDate2").html(opt.deadline);
		$("#dangqianDate").html(date);
		$("#tongzhidanhao").html(opt.data[0].noticeNo);
		$("#fukuanqixian").html(opt.deadline);
		$("#lvkeName").html("唐彩红");
		$("#yingshoujine").html(opt.data[0].balance);
		$("#yingshouzonge").html(opt.data[0].balance);
	};

	window.armgt.receivable.notice.close = function() {
		$('#dlgNotice').dialog('close');
	};

	window.armgt.receivable.input.open = function(data) {
		$('<div id="dlgInput">')
				.dialog(
						{
							title : "输入结算期限",
							href : $.util
									.resolveClientUrl('/cs/page/ar-mgt/receivable/deadlineInput.jsp'),
							width : 200,
							height : 120,
							top : 5,
							iconCls : 'bicon-edit',
							resizable : false,
							modal : true,
							closed : true,
							cache : false,
							onClose : function() {
								$(this).dialog('destroy');
							},
							onLoad : function() {
								window.armgt.receivable.input.init(data);
							}
						}).dialog('open');
	};

	window.armgt.receivable.input.close = function() {
		$('#dlgInput').dialog('close');
	};

	window.armgt.receivable.input.sure = function(data) {
		if (!$('#form').form('validate')) {
			return;
		}
		window.armgt.receivable.notice.open({
			data : data,
			deadline : $('#deadline').datebox('getValue'),
			type : 2
		});
		window.armgt.receivable.input.close();
	};

	window.armgt.receivable.input.init = function(data) {
		$('#deadline').datebox('setValue', $.getSystemDateStr());
		$('#btnSave').click(function() {
			window.armgt.receivable.input.sure(data);
		});
		$('#btnClose').click(function() {
			window.armgt.receivable.input.close();
		});
	};

	window.armgt.receivable.notice.handle = function(data) {
		if ($.compareSystemDate(data[0].deadline) > 0) {
			window.armgt.receivable.input.open(data);
			return;
		}
		window.armgt.receivable.notice.open({
			data : data,
			deadline : data[0].deadline,
			type : 1
		});
	};

	window.armgt.receivable.notice.openByIndex = function(index) {
		window.armgt.receivable.notice
				.handle([ $(dtgd).datagrid('getRows')[index] ]);
	};

	window.armgt.receivable.notice.openOnSelect = function() {
		window.armgt.receivable.notice
				.handle($(dtgd).datagrid('getSelections'));
	};

	/**
	 * 验证规则
	 */
	$.extend($.fn.validatebox.defaults.rules, {
		zero : {
			validator : function(value, opt) {
				return value * 1 != 0;
			},
			message : '金额不能为0！'
		},
		less_equal_zero : {
			validator : function(value, opt) {
				return !(value * 1 > 0);
			},
			message : '必须小于0！'
		},
		amount : {
			validator : function(value, opt) {
				var rules = $.fn.validatebox.defaults.rules;
				var row = $(opt[0]).datagrid('getRows')[$
						.getDatagridRowIndex(this)];
				if (row[opt[1]] > 0) {
					rules.amount.message = '必须在0~' + row[opt[1]] + '之间。';
					return value <= row[opt[1]] && value > 0;
				} else {
					rules.amount.message = '必须在' + row[opt[1]] + '~0之间。';
					return value >= row[opt[1]] && value < 0;
				}
			},
			message : '填写金额不能超出范围！'
		}
	});

	window.armgt.receivable.loadPageBar = function() {
		$(dtgd).datagrid('getPager').pagination(
				{
					layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links',
							'sep', 'next', 'last', 'sep', 'manual', 'sep',
							'refresh' ],
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

		$(dtgd).datagrid('getPager').pagination().find('a.l-btn').tooltip(
				{
					content : function() {
						var cc = $(this).find('span.l-btn-icon').attr('class')
								.split('');
						var icon = cc[1].split('-')[1];
						if (icon == "check") {
							return "启用多选"
						} else if (icon == "stop") {
							return "关闭多选";
						}
						return icon + ' page';
					}
				});

	};

	window.armgt.receivable.Tixing = function() {
		$.post("/cs/armgt/receivableRemind!findAll.action", function(result) {
			var result = JSON.parse(result);
			if(result.rows.length>0){
				$('<div id="Tixing">').dialog(
						{
							title : "应收账款账期提醒",
							href : $.util
									.resolveClientUrl('/cs/page/ar-mgt/receivable/receTx.jsp'),
							width : 560,
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
								$('#receTx_dg').datagrid({
									width : 550,
									height : 345,
									pagination : true,
									pagePosition : 'both',
									rownumbers : true,
									method : 'get',
									border : false,
									url : '/cs/armgt/receivableRemind!findAll.action',
									rowStyler : window.armgt.receivable.styler,
									columns : [ [
											{
												field : 'deadline',
												title : '结算期限',
												width : 100
											},
											{
												field : 'customerNo',
												title : '应收账户',
												width : 100,
												align : 'right'
											},
											{
												field : 'incomeBeAmount',
												title : '应收金额',
												width : 100,
												align : 'left',
												formatter : window.armgt.receivable.amountFormatter
											},
											{
												field : 'incomeAmount',
												title : '已收金额',
												width : 100,
												formatter : window.armgt.receivable.amountFormatter
											},
											{
												field : 'balance',
												title : '余额',
												width : 100,
												formatter : window.armgt.receivable.amountFormatter
											} ] ]
								});

						$("#receTxClose").click(function() {
							$('#Tixing').dialog('close');
						});
					}
				}).dialog('open');
			}
		});
	};

	window.armgt.receivable.styler = function(index, row) {
		var date1 = new Date();
		var d1 = date1.getMonth() + 1;
		var date1 = date1.getFullYear() + '-' + d1 + '-' + date1.getDate();
		if (row.deadline < date1) {
			return 'background-color:#D1EEEE';
		}
	};

	$(function() {
		var date = new Date();
		var d = date.getMonth() + 1;
		var date = date.getFullYear() + '-' + d + '-01';
		var date1 = new Date();
		var d1 = date1.getMonth() + 1;
		var date1 = date1.getFullYear() + '-' + d1
				+ '-' + date1.getDate();
		
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
		
		$("#tradeDateQi").datebox('setValue',date);
		$("#tradeDateShi").datebox('setValue',date1);
		$("#deadlineShi").datebox('setValue', '');
		
		window.armgt.receivable.Tixing();
//		window.armgt.receivable.loadPageBar();//是否显示单选多选
		window.armgt.receivable.bindToolBarEvent();
		window.armgt.receivable.toolbarDisable();
	});

})(jQuery);