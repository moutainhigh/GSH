(function($) {

	$.util.namespace("commission.productCommission");
	var commissionDg = '#commission_dg';
	var commissionShouru = '#commission_shouru';
	var commissionSearchGaoji='#commission_search_gaoji';
	var accountCommissionShourucommissionForm='#account_commission_shourucommission_form';
	
	/**
	 * datagrid 设置分页栏
	 */
	window.commission.productCommission.loadPageBar = function() {
		$(commissionDg).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ],
		});
	};
	
	/**
	 * 页面小计和总计
	 */
	window.commission.productCommission.onLoadSuccess=function(data){
		var sumfootertable = '<table class="sum-footer">';
		var row1 = '<tr><th></th>';
		var row2 = '<tr><th>小计：</th>';
		var row3 = '<tr><th>总计：</th>';
		var i = 1;
		for ( var item in data.sumfooter) {
			row1 += '<th class="sum-footer-' + i + ' sum-footer-left">收入</th>';
			row1 += '<th class="sum-footer-' + i + ' ">支出 </th>';
			row1 += '<th class="sum-footer-' + i + ' ">余额（' + item + '）</th>';
			row3 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + data.sumfooter[item][0].incomeBeAmount + '</td>';
			row3 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][0].incomeAmount + '</td>';
			row3 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][0].balance + '</td>';
			if (data.sumfooter[item][1]) {
				row2 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + data.sumfooter[item][1].incomeBeAmount + '</td>';
				row2 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][1].incomeAmount + '</td>';
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
		$('#commission_footer').html(sumfootertable);
	};
	
	window.commission.productCommission.amountFormatter = function(value, row, index) {
		if(row.currencyType==undefined){
			return $.formatCurrencySign(value, "CNY");
		}
		return $.formatCurrencySign(value, row.currencyType);
	};
	
	window.commission.productCommission.productRoute=function(value, row, index){
		if(value!=null&&value!=""){
			var addPassenger = eval("(" + $.ajax({
				url : "/cs/account/commissionAction!findRouteProducts.action",
				dataType : "json",
				data : {
					id : value
				},
				async : false,
				cache : false,
				type : "post"
			}).responseText + ")");
			return addPassenger[0].text;
		}
		return "";
	}
	/**
	 * 初始化屏蔽全部按钮
	 */
	window.commission.disableToobar = function() {
		$(commissionShouru).linkbutton('disable');
	};
	/**
	 * 单选模式工具栏按钮显示影藏
	 */
	window.commission.toolBarUpdate = function(row) {
		$(commissionShouru).linkbutton('enable');
	};
	/**
	 * 多选模式工具栏按钮显示影藏
	 */
	window.commission.toolBarUpdateBySelects = function(rows) {
		window.commission.disableToobar();
		// 验证值
		for ( var i = 0; i < rows.length; i++) {
			if(i+1<=rows.length){
				if (rows[i].currencyType != rows[0].currencyType ) {
					return;
				}
			}
		}
		$(commissionShouru).linkbutton('enable');
	};
	
	/**
	 * 选择一行
	 */
	window.commission.onSelect = function(index, row) {
		var selectRows = $(commissionDg).datagrid('getSelections');
		if (selectRows.length == 1) {
			window.commission.toolBarUpdate(row);
		} else {
			window.commission.toolBarUpdateBySelects(selectRows);
		}
	};
	
	/**
	 * 取消选择一行
	 */
	window.commission.onUnselect = function(index, row) {
		var selectRows = $(commissionDg).datagrid('getSelections');
		if (selectRows.length == 1) {
			window.commission.toolBarUpdate(selectRows[0]);
		} else if (selectRows.length > 1) {
			window.commission.toolBarUpdateBySelects(selectRows);
		}
	};
	
	/**
	 * 收入佣金
	 */
	window.commission.productCommission.CommissionShouru = function(){
		$('<div id="commission_shouruproductCommission_dialog">').dialog({
			title : "收入佣金",
			href : $.util.resolveClientUrl('/cs/page/account/commission/commissionShouru.jsp'),
			width : 600,
			height : 400,
			top : 5,
			draggable : true,
			iconCls : 'bicon-plus',
			resizable : false,
			modal : true,
			closed : true,
			cache : false,
			buttons : [ {
				text : '<i class="fa fa-save"></i> 收入',
				handler : function() {
					commissionShouru_yongjin_save();
				}
			}, {
				text : '<i class="fa fa-times"></i> 放弃',
				handler : function() {
					commissionShouru_yongjin_close();
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				//changeCommission();
				var getrow1 = $(commissionDg).datagrid('getSelections', $(commissionDg).datagrid('getSelected'));
				var yingshou=0;
				var balance=0;
				var shishou=0
				for ( var i = 0; i < getrow1.length; i++) {
					yingshou=yingshou+Number(getrow1[i].incomeBeAmount);
					shishou=shishou+Number(getrow1[i].incomeBeAmount);
					balance=balance+Number(getrow1[i].balance);
				}
				$("#carrier").val(getrow1[0].carrier);
				$("#currencyType").val(getrow1[0].currencyType);
				$("#yingshou").numberbox({prefix:$.convertCurrencyUnit(getrow1[0].currencyType)});
				$("#yingshou").numberbox('setValue',yingshou);
				$("#shishou").numberbox({prefix:$.convertCurrencyUnit(getrow1[0].currencyType)});
				$("#shishou").numberbox('setValue',shishou);
				$("#incomeAmount").val(shishou);
				$("#balance").numberbox({prefix:$.convertCurrencyUnit(getrow1[0].currencyType)});
				$("#balance").numberbox('setValue',0);
				$("#factorage").numberbox({prefix:$.convertCurrencyUnit(getrow1[0].currencyType)});
				$("#factorage").numberbox('setValue',0);
				$("#paymentMethod").combobox('setValue',4);
				var details = [];
				var getrow = $(commissionDg).datagrid('getSelections', $(commissionDg).datagrid('getSelected'));
				for ( var i = 0; i < getrow.length; i++) {
					details.push(JSON.parse(JSON.stringify(getrow[i])));
					details[i].cmsnid = details[i].id;
					details[i].incomeBeAmount = details[i].incomeBeAmount;
					details[i].incomeAmount = details[i].balance;
					details[i].currencyType = details[i].currencyType;
				}
				$('#dtgdWto_chanpindan').datagrid('loadData', details);
				window.armgt.receivable.writeoffIncome
				.paymentMethodOnChange(4, 4);
			}
		}).dialog('open');
	};
	
	window.commission.productCommission.paymentMethodOnChange = function(
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
			var g = $('#comm_fundAccount').combogrid('grid'); // 获取数据表格对象
			g.datagrid({
				data : result
			});
			var r = g.datagrid('getRows');
			for ( var i = 0; i < r.length; i++) {
				if (r[i].accountType == 'C' && r[i].defaultIncAccount == '1') {
					$('#comm_fundAccount').combogrid('setValue', r[i].accountCode);
					return;
				} else {
					$('#comm_fundAccount').combogrid('setValue', r[i].accountCode);
				}
			}	
			if(result.length==0){
				$("#comm_fundAccount").combogrid('setText',"");
			}
	}
	
	//收款方式改变
	window.commission.productCommission.onHidePanel=function(){
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
			var g = $('#comm_fundAccount').combogrid('grid'); // 获取数据表格对象
			g.datagrid({
				data : result
			});
			var r = g.datagrid('getRows');
			for ( var i = 0; i < r.length; i++) {
				if (r[i].accountType == 'C' && r[i].defaultIncAccount == '1') {
					$('#comm_fundAccount').combogrid('setValue', r[i].accountCode);
					return;
				} else {
					$('#comm_fundAccount').combogrid('setValue', r[i].accountCode);
				}
			}	
			if(result.length==0){
				$("#comm_fundAccount").combogrid('setText',"");
			}
	}
	
	window.commission.productCommission.fundAccountOnChange = function(nv, ov) {
		var row = $(this).combogrid('grid').datagrid('getSelected');
		if (row.accountCurrency != $('#currencyType').val()) {
			$('.hl').removeClass('trhidden');
			$('#bankRate').numberbox('disableValidation');
		} else {
			$('.hl').addClass('trhidden');
			$('#bankRate').numberbox('enableValidation');
		}
	};
	
	window.commission.productCommission.bankRateOnChange = function(nv, ov) {
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
		$('#exchangeProfitLoss').numberbox('setValue', ($('#incomeAmount').val() * op_rate) - ($('#incomeAmount').val() * nv));
	};
	
	/**
	 * 放弃
	 */
	commissionShouru_yongjin_close=function(){
		$("#commission_shouruproductCommission_dialog").dialog('close');
	};
	
	
	/**
	 * 验证规则
	 */
	$.extend($.fn.validatebox.defaults.rules, {
		paidinMoney_wto : {
			validator : function(value) {
				var rules = $.fn.validatebox.defaults.rules;
				var row = $('#dtgdWto_chanpindan').datagrid('getRows')[$.getDatagridRowIndex(this)];
				if (row.incomeBeAmount > 0) {
					rules.paidinMoney_wto.message = '必须在0~' + row.incomeBeAmount + '之间。';
					return value <= row.incomeBeAmount && value > 0;
				} else {
					rules.paidinMoney_wto.message = '必须在' + row.incomeBeAmount + '~0之间。';
					return value >= row.incomeBeAmount && value < 0;
				}
			},
			message : '填写金额不能超出范围！'
		}
	});
	
	
	var wtoChangeflog = false;
	/**
	 * 修改
	 */
	window.commission.onClickRow = function(rowIndex, rowData) {
		wtoChangeflog = false;
		$('#dtgdWto_chanpindan').datagrid('beginEdit', rowIndex);
	};

	/**
	 * 实收金额改变的时候
	 */
	changeAmountOnChange = function(newValue, oldValue){
		var getrow1 = $(commissionDg).datagrid('getSelections', $(commissionDg).datagrid('getSelected'));
		if (wtoChangeflog) {
			var yingshou = $('#yingshou').numberbox('getValue');
			alert(yingshou);
			var shishou=($('#shishou').numberbox('getValue')* 1) - (oldValue * 1) + (newValue * 1);
			$('#shishou').numberbox('setValue',shishou);
			$("#incomeAmount").val(shishou);
			$('#balance').numberbox('setValue',yingshou-shishou);
		} else
			wtoChangeflog = true;
	};
	
	/**
	 * 收入佣金
	 */
	
	commissionShouru_yongjin_save=function(){
		if (!$(accountCommissionShourucommissionForm).form('validate')) {
			return;
		}
		var rows = $('#dtgdWto_chanpindan').datagrid('getRows');
		for ( var r = 0; r < rows.length; r++) {
			if (!$('#dtgdWto_chanpindan').datagrid('validateRow', r)) {
				return;
			}
		}
		$.messager.confirm('确认', '您确定收入吗？', function(r) {
			$('#dtgdWto_chanpindan').datagrid('acceptChanges');
			var form_data = $.serializeObject($('#account_commission_shourucommission_form'));
			$.post("/cs/account/commissionAction!payment.action", {
				form : JSON.stringify(form_data),
				detail : JSON.stringify($('#dtgdWto_chanpindan').datagrid('getRows'))
			}, function(result) {
				var result = JSON.parse(result);
				if (result.success) {
					$(commissionDg).datagrid('reload');
					$('#commission_shouruproductCommission_dialog').dialog('close');
				}
				$.messager.show({
					title : '提示',
					msg : result.msg,
					showType : 'slide'
				});
			});
			
			/**
			 * 按钮不可用
			 */
			var selectRows = $(commissionDg).datagrid('getSelections');
			if (selectRows.length == 1) {
				window.commission.toolBarUpdate(selectRows[0]);
			} else if (selectRows.length > 1) {
				window.commission.toolBarUpdateBySelects(selectRows);
			}
		});
		
		
	};
	
	
	/**
	 * 查询或得佣金
	 */
	window.commission.productCommission.searchcommission = function(){
		$('<div id="commission_searchcommission_dialog">').dialog({
			title : "查询或得佣金",
			href : $.util.resolveClientUrl('/cs/page/account/commission/searchcommissionShouru.jsp'),
			width : 350,
			height : 200,
			top : 5,
			draggable : true,
			iconCls : 'bicon-plus',
			resizable : false,
			modal : true,
			closed : true,
			cache : false,
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				var date=new Date();
				var d=date.getMonth()+1;
				var date =  date.getFullYear()+'-'+d+'-01';
				var date1=new Date();
				var d1=date1.getMonth()+1;
				var date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
				$("#commission_date_qi").datebox("setValue",date);
				$("#commission_date_shi").datebox("setValue",date1);
				/**
				 * 查询
				 */
				$("#commission_search").click(function(){
					if (!$("#account_serchcommission_form").form('validate')) {
						return;
					}
					var formData = $.serializeObject($("#account_serchcommission_form"));
					$.post("/cs/account/commissionAction!findAllSearch.action", formData, function(result) {
						var result = JSON.parse(result);
						if (result.success) {
							$(commissionDg).datagrid('loadData',result.obj);
						}else{
							$(commissionDg).datagrid('loadData',{total:0,rows:[]});
							$.messager.show({
								title : '提示',
								msg : '未找到或得佣金信息',
								showType : 'slide'
							});
						}
						
					});
					
					$("#commission_searchcommission_dialog").dialog('close');
					
				});
				
				/**
				 * 关闭
				 */
				$("#commission_close").click(function(){
					$("#commission_searchcommission_dialog").dialog('close');
				});
			}
		}).dialog('open');
	};
	
	window.commission.search=function(){
		if (!$("#commission_form").form('validate')) {
			return;
		}
		var formData = $.serializeObject($("#commission_form"));
		$.post("/cs/account/commissionAction!findAllSearch.action", formData, function(result) {
			var result = JSON.parse(result);
			if (result.success) {
				$(commissionDg).datagrid('loadData',result.obj);
			}else{
				$(commissionDg).datagrid('loadData',{total:0,rows:[]});
				$.messager.show({
					title : '提示',
					msg : '未找到或得佣金信息',
					showType : 'slide'
				});
			}
			
		});
	};
	

	
	
	/**
	 * 按钮事件绑定
	 */
	window.commission.productCommission.bindButtonEvent = function() {
		/**
		 * 收入
		 */
		$(commissionShouru).click(function(){
			window.commission.commissionShouru();
		});
		
		/**
		 * 查询
		 */
		$(commissionSearchGaoji).click(function(){
			window.commission.productCommission.searchcommission();
		});
		
		$("#btSearch").click(function(){
			window.commission.search();
		});
		
	};
	
	/**
	 * 收入
	 */
	window.commission.commissionShouru=function(){
		var getrow = $(commissionDg).datagrid('getSelections', $(commissionDg).datagrid('getSelected'));
		if (getrow.length <= 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要收入的佣金！',
				showType : 'slide',
			});
			return;
		}
		window.commission.productCommission.CommissionShouru();
		
	};
	
	$(function() {
		var date=new Date();
		var d=date.getMonth()+1;
		var date =  date.getFullYear()+'-'+d+'-01';
		var date1=new Date();
		var d1=date1.getMonth()+1;
		var date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
		$("#commission_date_qi_zhu").datebox('setValue',date);
		$("#commission_date_shi_zhu").datebox('setValue',date1);
		
		window.commission.disableToobar(); 
		window.commission.productCommission.loadPageBar();
		window.commission.productCommission.bindButtonEvent();
	});

})(jQuery);