(function($) {

	$.util.namespace("bset.exchangeRates");

	/**
	 * 验证规则
	 */
	$.extend($.fn.validatebox.defaults.rules, {
		codeReceipt : {
			validator : function(value, opt) {
				var code = $("#code_abc").combobox('getValue');
				var rules = $.fn.validatebox.defaults.rules;
				var result = eval("(" + $.ajax({
					url : "/cs/bset/sertAction!findBianli.action",
					dataType : "json/xml/html",
					data : {
						"code" : code
					},
					async : false,
					cache : false,
					type : "post"
				}).responseText + ")");
				return result.success == false;
			},
			message : '货币单位已存在，请重新选择！'
		}
	});

	window.bset.exchangeRates.add = function() {
		$('<div id="dlgAdd">')
				.dialog(
						{
							title : "新建",
							href : $.util
									.resolveClientUrl('/cs/page/bset/exchangeRates/add.jsp'),
							width : 270,
							height : 220,
							top : 5,
							iconCls : 'bicon-edit',
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
											$("#name").val($("#code_abc").combogrid('getText'));
											var formData = $
													.serializeObject($('#form'));
											$
													.post(
															"/cs/bset/sertAction!add.action",
															formData,
															function(result) {
																var result = JSON
																		.parse(result);
																if (result.success) {
																	$('#dtgd')
																			.datagrid(
																					'insertRow',
																					{
																						index : 0,
																						row : result.obj
																					});
																	$('#dlgAdd')
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
											$('#dlgAdd').dialog('close');
										}
									} ],
							onClose : function() {
								$(this).dialog('destroy');
							}
						}).dialog('open');
	};

	window.bset.exchangeRates.edit = function(index) {
		$('<div id="dlgAdd">')
				.dialog(
						{
							title : "编辑",
							href : $.util
									.resolveClientUrl('/cs/page/bset/exchangeRates/add.jsp'),
							width : 250,
							height : 200,
							top : 5,
							iconCls : 'bicon-edit',
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
											$("#name").val($("#code_abc").combogrid('getText'));
											var formData = $
													.serializeObject($('#form'));
											formData.id = $('#dtgd').datagrid(
													'getRows')[index].id;
											$
													.post(
															"/cs/bset/sertAction!edit.action",
															formData,
															function(result) {
																var result = JSON
																		.parse(result);
																if (result.success) {
																	$('#dtgd').datagrid('load');
																	$('#dlgAdd').dialog('close');
																}
																$.messager
																		.show({
																			title : '提示',
																			msg : result.msg,
																			showType : 'slide'
																		});
																window.bset.exchangeRates
																		.toolBarUpdate(0);
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
								var editRow = $('#dtgd').datagrid('getRows')[index];
								alert(editRow.userId)
								$('#code_abc').combogrid('disable');
								$('#form').form('load', editRow);
//								$('#code_abc').combogrid('disable');
//								$('#code_abc').combobox('disable');
//								$('#creditDesc').validatebox('disableValidation');
//								$('#code_abc').attr('readonly','true');
//								$('#creditDesc').css({"background-color":"#EBEBE4"});
							}
						}).dialog('open');
	};

	window.bset.exchangeRates.remove = function(index) {
		$.messager.confirm('确认', '您确认删除该记录吗？', function(r) {
			if (r) {
				var id = $('#dtgd').datagrid('getRows')[index].id;
				$.post("/cs/bset/sertAction!del.action", {
					'id' : id
				}, function(result) {
					var result = JSON.parse(result);
					if (result.success) {
						$('#dtgd').datagrid('deleteRow', index);
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

	window.bset.exchangeRates.opitonsFormatter = function(value, row, index) {
		var options ="";
		if(JSHLBJ==true&&row.settlementexchangerateLock == 1){
			options += '<i class="bicon-edit" onclick="window.bset.exchangeRates.edit($.getDatagridRowIndex(this));"></i>';
		}
		if(JSHLSC==true&&row.settlementexchangerateLock == 1){
			options += ' <i class="bicon-remove" onclick="window.bset.exchangeRates.remove($.getDatagridRowIndex(this));"></i>';
		}
		if(JSHLSQJS==true&&row.settlementexchangerateLock==0){
			options += ' <i class="fa fa-lock" onclick="window.bset.exchangeRates.shenqingjiesuoByIndex($.getDatagridRowIndex(this));"></i>';
		}
		if(JSHLJS==true&&row.settlementexchangerateLock==2){
			options += ' <i class="fa fa-lock" onclick="window.bset.exchangeRates.jiesuoByIndex($.getDatagridRowIndex(this));"></i>';
		}
		
//		if (row.settlementexchangerateLock == 1) {
//			var options = '<i class="bicon-edit" onclick="window.bset.exchangeRates.edit($.getDatagridRowIndex(this));"></i>';
//			options += ' <i class="bicon-remove" onclick="window.bset.exchangeRates.remove($.getDatagridRowIndex(this));"></i>';
//			return options;
//		}
		return options;
	};

	window.bset.exchangeRates.nameFormatter = function(value, row, index) {
		if(row.code=='CNY'){
			return '人民币';
		}
		return CURRENCY[row.code] ? CURRENCY[row.code].name : '';
	};

	window.bset.exchangeRates.validityFormatter = function(value, row, index) {
		if (value == undefined) {
			return '长期有效';
		}
		return value;
	};

	/**
	 * 0已锁、1未锁2、申请解锁
	 */
	window.bset.exchangeRates.LockStyler = function(value, row, index) {

		if (value == 0) {
			return 'background-color:red;color:#fff;';
		} else if (value == 1) {
			return 'background-color:green;color:#fff;';
		} else if (value == 2) {
			return 'background-color:orange;color:#fff;';
		}

	};
	window.bset.exchangeRates.LockFormater = function(value, row, index) {
		if (value == 0) {
			return "已锁";
		} else if (value == 1) {
			return "未锁";
		} else if (value == 2) {
			return "申请解锁";
		}

	};

	/**
	 * 工具栏按钮显示影藏
	 */
	window.bset.exchangeRates.toolBarUpdate = function(
			settlementexchangerateLock) {
		$('#btnEdit').linkbutton('disable');
		$('#btnRemove').linkbutton('disable');
		$('#btnjiesuo').linkbutton('disable');
		$('#btnshenqingjiesuo').linkbutton('disable');
		if (settlementexchangerateLock == 0) {// 这里后面需要加判断，管理员是解锁，普通用户是申请解锁，目前没有那个条件就先放一起
			$('#btnshenqingjiesuo').linkbutton('enable');
		} else if (settlementexchangerateLock == 2) {
			$('#btnjiesuo').linkbutton('enable');
		} else if (settlementexchangerateLock == 1) {
			$('#btnEdit').linkbutton('enable');
			$('#btnRemove').linkbutton('enable');
		}
	};
	
	window.bset.exchangeRates.load = function(){
		$('#btnEdit').linkbutton('disable');
		$('#btnRemove').linkbutton('disable');
		$('#btnjiesuo').linkbutton('disable');
		$('#btnshenqingjiesuo').linkbutton('disable');
	}

	window.bset.exchangeRates.onSelect = function(index, row) {
		window.bset.exchangeRates.toolBarUpdate(row.settlementexchangerateLock);
	};

	/**
	 * 申请解锁
	 */
	window.bset.exchangeRates.shenqingjiesuo = function() {
		var index = $('#dtgd').datagrid('getRowIndex',
				$('#dtgd').datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要申请解锁的记录！',
				showType : 'slide'
			});
			return;
		}
		window.bset.exchangeRates.shenqingjiesuoByIndex(index);
	};
	window.bset.exchangeRates.shenqingjiesuoByIndex = function(index) {
		$.messager
				.confirm(
						'确认',
						'您确认想要申请解锁这条信息吗？',
						function(r) {
							if (r) {
								var id = $('#dtgd').datagrid('getRows')[index].id;
								$
										.post(
												"/cs/bset/sertAction!updateByLock.action",
												{
													'id' : id,
													'settlementexchangerateLock' : 2
												},
												function(result) {
													var result = JSON
															.parse(result);
													if (result.success) {
														$('#dtgd').datagrid(
																'getRows')[index].settlementexchangerateLock = 2;
														$('#dtgd').datagrid(
																'refreshRow',
																index);
														$.messager.show({
															title : '操作提示',
															msg : '申请解锁成功！',
															showType : 'slide'
														});
														window.bset.exchangeRates
																.toolBarUpdate(2);
													}

												});
							}
						});
	};

	/**
	 * 解锁
	 */
	window.bset.exchangeRates.jiesuo = function() {
		var index = $('#dtgd').datagrid('getRowIndex',
				$('#dtgd').datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要解锁的记录！',
				showType : 'slide'
			});
			return;
		}
		window.bset.exchangeRates.jiesuoByIndex(index);
	};
	/**
	 * 解锁操作
	 */
	window.bset.exchangeRates.jiesuoByIndex = function(index) {
		$.messager
				.confirm(
						'确认',
						'您确认想要解锁这条信息吗？',
						function(r) {
							if (r) {
								var id = $('#dtgd').datagrid('getRows')[index].id;
								$
										.post(
												"/cs/bset/sertAction!updateByLock.action",
												{
													'id' : id,
													'settlementexchangerateLock' : 1
												},
												function(result) {
													var result = JSON
															.parse(result);
													if (result.success) {
														$('#dtgd').datagrid(
																'getRows')[index].settlementexchangerateLock = 1;
														$('#dtgd').datagrid(
																'refreshRow',
																index);
														$.messager.show({
															title : '操作提示',
															msg : '解锁成功！',
															showType : 'slide'
														});
														window.bset.exchangeRates
																.toolBarUpdate(1);
													}

												});
							}
						});
	};

	window.bset.exchangeRates.search = function() {
		var formData = $.serializeObject($("#exchangeRates_from"));
		$.post("/cs/bset/sertAction!findAllSearch.action", formData, function(
				result) {
			var result = JSON.parse(result);
			if (result.success) {
				$('#dtgd').datagrid('loadData', result.obj);
			} else {
				$.messager.show({
					title : '提示',
					msg : result.msg,
					showType : 'slide'
				});
				$('#dtgd').datagrid('loadData', {
					total : 0,
					rows : []
				});
			}

		});
	};

	window.bset.exchangeRates.onChange = function(newValue, oldValue) {
//		if(newValue==""){
//			
//		}
//		var result = eval("(" + $.ajax({
//			url : "/cs/aa/bb!find.action",
//			dataType : "json/xml/html",
//			data : {
//				"cu" : newValue
//			},
//			async : false,
//			cache : false,
//			type : "post"
//		}).responseText + ")");
//		var g = $('#rate_code').combogrid('grid'); // 获取数据表格对象
//		g.datagrid('loadData', result.obj);

	};
	
	
	$(function() {
		window.bset.exchangeRates.load();
		if(JSHLXJ==false){
			$("#btnAdd").hide();
		}
		if(JSHLBJ==false){
			$("#btnEdit").hide();
		}
		if(JSHLSC==false){
			$("#btnRemove").hide();
		}
		if(JSHLSQJS==false){
			$("#btnshenqingjiesuo").hide();
		}
		if(JSHLJS==false){
			$("#btnjiesuo").hide();
		}
		
		$("#code_def").combogrid().next('span').find('input').keyup(function() {
			var newValue=$("#code_def").combogrid('getText');
			var result = eval("(" + $.ajax({
				url : "/cs/aa/bb!find.action",
				dataType : "json/xml/html",
				data : {
					"cu" : newValue
				},
				async : false,
				cache : false,
				type : "post"
			}).responseText + ")");
			var g = $('#code_def').combogrid('grid'); // 获取数据表格对象
			g.datagrid('loadData', result.obj);
			$("#code_def").combogrid('setValue',newValue);
 		});
		
		
		$('#dtgd').datagrid('getPager').pagination(
				{
					layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links',
							'sep', 'next', 'last', 'sep', 'refresh' ]
				});
		$('#btnAdd').click(function() {
			window.bset.exchangeRates.add();
		});
		$('#btnEdit').click(function() {
			if (!$('#btnEdit').linkbutton('options').disabled) {
				var index = $('#dtgd').datagrid('getRowIndex',
					$('#dtgd').datagrid('getSelected'));
				if (index < 0) {
					$.messager.show({
						title : '操作提示',
						msg : '请选择要编辑的记录！',
						showType : 'slide'
					});
					return;
				}
				window.bset.exchangeRates.edit($('#dtgd').datagrid(
						'getRowIndex',
						$('#dtgd').datagrid('getSelected')));
			}
		});
		$('#btnRemove').click(function() {
			if (!$('#btnRemove').linkbutton('options').disabled) {
				var index = $('#dtgd').datagrid('getRowIndex',
						$('#dtgd').datagrid('getSelected'));
				if (index < 0) {
					$.messager.show({
						title : '操作提示',
						msg : '请选择要删除的行！',
						showType : 'slide'
					});
					return;
				}
				window.bset.exchangeRates
						.remove($('#dtgd').datagrid('getRowIndex',
								$('#dtgd').datagrid('getSelected')));
			}
		});
		/**
		 * 申请解锁
		 */
		$('#btnshenqingjiesuo').click(function() {
			if (!$('#btnshenqingjiesuo').linkbutton('options').disabled) {
				window.bset.exchangeRates.shenqingjiesuo();
			}
		});
		/**
		 * 解锁
		 */
		$('#btnjiesuo').click(function() {
			if (!$('#btnjiesuo').linkbutton('options').disabled) {
				window.bset.exchangeRates.jiesuo();
			}
		});

		$("#btnSearch").click(function() {
			window.bset.exchangeRates.search();
		});
	});

})(jQuery);