(function($) {

	$.util.namespace("account.accountToreview");
	$.util.namespace("account.accountToreview.open");
	
	var testData1 = {
			"total" : 10,
			"rows" : [
			 {
				"orderId" : "abc123456789",
				"account" : "cen1234",
				"price" : "150",
				"name" : "张三",
				"status" : "1",
			}, {
				"orderId" : "abc123456789",
				"account" : "cen1234",
				"price" : "150",
				"name" : "李四",
				"status" : "2",
			}],
			"footer":[{
				"orderId" : "",
				"account" : "小计：",
				"price" : "300",
				"name" : "",
				"status" : "",
			}]
		};
	/**
	 * 主页面的按钮和datagrid
	 */
	var ToreviewOK = '#ToreviewOK', ToreviewNO = '#ToreviewNO',ToreviewSearchGaoji='#Toreview_search_gaoji',ToreviewMingxi='#ToreviewMingxi';
	var accountToreviewDg = '#account_toreview_dg';
	var accountToreviewOnclickSearch="#accountToreview_onclick_search";
	
	/**
	 * 账目明细
	 */
	var openAccountRemoud='#openAccount_remoud',openAccountToreviewPanel='#openAccountToreviewPanel';
	var openAccountToreviewDg = '#openAccountToreview_dg';
	
	/**
	 *查询页面 
	 */
	var accountToreviewSearch='#accountToreview_search',accountToreviewClose='#accountToreview_close',accountDateQi = '#account_date_qi';
	
	/**
	 * datagrid 设置分页栏
	 */
	window.account.accountToreview.loadPageBar = function() {
		$(accountToreviewDg).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ],
		});
	};
	
	/**
	 * 页面小计和总计
	 */
//	window.account.accountToreview.onLoadSuccess=function(data){
//		var sumfootertable = '<table class="sum-footer">';
//		var row1 = '<tr><th></th>';
//		var row2 = '<tr><th>小计：</th>';
//		var row3 = '<tr><th>总计：</th>';
//		var i = 1;
//		for ( var item in data.sumfooter) {
//			row1 += '<th class="sum-footer-' + i + ' sum-footer-left">收入</th>';
//			row1 += '<th class="sum-footer-' + i + ' ">支出 </th>';
//			row1 += '<th class="sum-footer-' + i + ' ">余额（' + item + '）</th>';
//			row3 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + data.sumfooter[item][0].income + '</td>';
//			row3 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][0].expenditure + '</td>';
//			row3 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][0].balance + '</td>';
//			if (data.sumfooter[item][1]) {
//				row2 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + data.sumfooter[item][1].income + '</td>';
//				row2 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][1].expenditure + '</td>';
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
//		$('#collectPayRecord_footer').html(sumfootertable);
//	};
	
	
	/**
	 * 初始化屏蔽全部按钮
	 */
	window.account.accountToreview.disableToobar = function() {
		$(ToreviewOK).linkbutton('disable');
		$(ToreviewNO).linkbutton('disable');
		$(ToreviewMingxi).linkbutton('disable');
	};
	/**
	 * 单选模式工具栏按钮显示影藏
	 */
	window.account.accountToreview.toolBarUpdate = function(row) {
		window.account.accountToreview.disableToobar();
		if (row.status == 1) {
			$(ToreviewOK).linkbutton('enable');
			$(ToreviewNO).linkbutton('enable');
		}
		if(row.type!=4){
			$(ToreviewMingxi).linkbutton('enable');
		}
		
	};
	/**
	 * 多选模式工具栏按钮显示影藏
	 */
	window.account.accountToreview.toolBarUpdateBySelects = function(rows) {
		window.account.accountToreview.disableToobar();
		var balance = 0;
		// 验证值
		var status = true;
		for ( var i = 0; i < rows.length; i++) {
			if (status && rows[i].status != 1) {
				status = false;
			}
			if (!status) {
				return;
			}
		}
		if (status){
			$(ToreviewOK).linkbutton('enable');
			$(ToreviewNO).linkbutton('enable');
		}
	};
	
	/**
	 * 选择一行
	 */
	window.account.accountToreview.onSelect = function(index, row) {
		var chongxin_selectRows = $(accountToreviewDg).datagrid('getSelections');
		if (chongxin_selectRows.length == 1) {
			window.account.accountToreview.toolBarUpdate(row);
		} else {
			window.account.accountToreview.toolBarUpdateBySelects(chongxin_selectRows);
		}
	};
	
	/**
	 * 取消选择一行
	 */
	window.account.accountToreview.onUnselect = function(index, row) {
		var selectRows = $(accountToreviewDg).datagrid('getSelections');
		if (selectRows.length == 1) {
			window.account.accountToreview.toolBarUpdate(selectRows[0]);
		} else if (selectRows.length > 1) {
			window.account.accountToreview.toolBarUpdateBySelects(selectRows);
		}
	};
	
	
	
	/**
	 * 1.未确认和驳回  2、已确认 3、以驳回
	 */
	window.account.accountToreview.optionsFormater = function(value, row, index) {
		if(row.id == 0){
			return;
		}
		var options = "";
		if (row.status == 1&&row.currencyType!=undefined) {
			options += '<i class="bicon-ok" onclick="window.account.accountToreview.querenOnIndex($.getDatagridRowIndex(this))"></i>&nbsp;';
			options += '<i class="bicon-repeat" onclick="window.account.accountToreview.bohuiOnIndex($.getDatagridRowIndex(this))"></i>&nbsp;';
		} else if (row.status == 2&&row.currencyType!=undefined) {
			options += '<i class="bicon-folder-open" onclick="window.account.accountToreview.Mingxi($.getDatagridRowIndex(this))"></i>';
		}
		
		if(row.type!=4&&row.currencyType!=undefined){
			options += '<i class="bicon-folder-open" onclick="window.account.accountToreview.Mingxi($.getDatagridRowIndex(this))"></i>';
		}

		return options;
	};
	
	window.account.accountToreview.status = function(value, row, index){
		if(row.id==0){
			return;
		}
		if (row.status == 1) {
			return "未复核";
		} else if (row.status == 2) {
			return "已复核";
		}
	};
	
	/**
	 * 状态的样式
	 */
	window.account.accountToreview.statusStyler = function(value, row, index){
		if(row.id==0){
			return;
		}
		if (row.status == 1) {
			return 'background-color:blue;color:#fff;';
		} else if (row.status == 2) {
			return 'background-color:green;color:#fff;';
		}
	};
	
	/**
	 * 查询账目复核
	 */
	window.account.accountToreview.searchAccountToreview = function(){
		$('<div id="account_searchAccountToreview_dialog">').dialog({
			title : "查询账目复核",
			href : $.util.resolveClientUrl('/cs/page/account/accountToreview/searchAccountToreview.jsp'),
			width : 330,
			height : 180,
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
				
				/**
				 * 查询
				 */
				$(accountToreviewSearch).click(function(){
					if (!$(account_accountToreview_serchAccountToreview_form).form('validate')) {
						return;
					}
					var formData = $.serializeObject($(account_accountToreview_serchAccountToreview_form));
					$.post("/cs/account/accountDetailAction!findToReview.action", formData, function(result) {
						var result = JSON.parse(result);
						if (result.success) {
							$("#accountCode").html(result.obj.rows[0].accountCode+"-"+result.obj.rows[0].accountBank);
							$("#tradeDate").html($("#accountabc_date_qi").datebox('getValue')+"至"+$("#accountdef_date_shi").datebox('getValue'));
							$(accountToreviewDg).datagrid('loadData',result.obj);
						}else{
							$.messager.show({
								title : '提示',
								msg : result.msg,
								showType : 'slide'
							});
							$("#accountCode").html("");
							$("#tradeDate").html("");
							$(accountToreviewDg).datagrid('loadData',{total:0,rows:[]});
						}
						
					});
					
					$("#account_searchAccountToreview_dialog").dialog('close');
				});
				
				/**
				 * 关闭
				 */
				$(accountToreviewClose).click(function(){
					$("#account_searchAccountToreview_dialog").dialog('close');
				});
			}
		}).dialog('open');
	};
	
	
	/**
	 * 账目明细
	 */
	window.account.accountToreview.Mingxi = function(index){
		$('<div id="account_openAccountToreview_dialog">').dialog({
			title : "账目明细",
			href : $.util.resolveClientUrl('/cs/page/account/accountToreview/openAccountToreview.jsp'),
			width : 550,
			height : 427,
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
				var id = $(accountToreviewDg).datagrid('getRows')[index].id;
				var type = $(accountToreviewDg).datagrid('getRows')[index].type;
				$('#openAccountToreview_dg').datagrid({    
				    url:'/cs/account/accountDetailAction!AccountMingxi.action?id='+id+"&type="+type,
				    onLoadSuccess:window.account.accountToreview.open.onLoadSuccess
				});
				$("#openAccount_remoud").click(function(){
					$("#account_openAccountToreview_dialog").dialog("close");
				});
			}
		}).dialog('open');
	};
	
	window.account.accountToreview.amountFormatter=function(value, row, index) {
		if(row.currencyType==undefined){
			return $.formatCurrencySign(value, "CNY");
		}
		return $.formatCurrencySign(value, row.currencyType);
	};
	
	/**
	 * 页面小计和总计
	 */
	window.account.accountToreview.open.onLoadSuccess=function(data){
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
		$('#sum_footer1').html(sumfootertable);
	};
	
	
	/**
	 * 按钮事件绑定
	 */
	window.account.accountToreview.bindButtonEvent = function() {
		/**
		 * 确定
		 */
		$(ToreviewOK).click(function(){
			if (!$(ToreviewOK).linkbutton('options').disabled) {
				window.account.accountToreview.querenOnSelect();
			}
			
		});
		/**
		 * 驳回
		 */
		$(ToreviewNO).click(function(){
			if (!$(ToreviewNO).linkbutton('options').disabled) {
				window.account.accountToreview.bohuiOnSelect();
			}
			
		});
		
		/**
		 * 高级查询
		 */
		$(ToreviewSearchGaoji).click(function(){
			window.account.accountToreview.searchAccountToreview();
		});
		/**
		 * 明细
		 */
		$(ToreviewMingxi).click(function(){
			if (!$(ToreviewMingxi).linkbutton('options').disabled) {
				window.account.accountToreview.MingxiOnSelect();
			}
		});
		
		/**
		 * 查询
		 */
		$(accountToreviewOnclickSearch).click(function(){
			window.account.accountToreview.accountToreviewOnclickSearch();
		});
	};
	
	/**
	 * 主页面条件查询
	 */
	window.account.accountToreview.accountToreviewOnclickSearch=function(){
		if (!$("#account_accountToreview_onclick_search_form").form('validate')) {
			return;
		}
		var url = "/cs/account/accountDetailAction!findToReview.action";
		if ($("#accountCode_zhanghu_zhu").combogrid('getValue') != ""&& $("#accountCode_zhanghu_zhu").combogrid('getValue') != undefined) {
			url += "?accountCode="+ $("#accountCode_zhanghu_zhu").combogrid('getValue');
		}
		if ($("#accountabc_date_qi").datebox('getValue') != ""&& $("#accountabc_date_qi").datebox('getValue') != undefined) {
			url += "&qiDate="+ $("#accountabc_date_qi").combobox('getValue');
		}
		if ($("#accountdef_date_shi").datebox('getValue') != ""&& $("#accountdef_date_shi").datebox('getValue') != undefined) {
			url += "&shiDate="+ $("#accountdef_date_shi").combobox('getValue');
		}
		if ($("#account_documentNumber").val() != ""&& $("#account_documentNumber").val() != undefined) {
			url += "&voucherNo="+ $("#account_documentNumber").val();
		}
		$(accountToreviewDg).datagrid({
			url : url
		});
		
		
//		var formData = $.serializeObject($("#account_accountToreview_onclick_search_form"));
//		$.post("/cs/account/accountDetailAction!findToReview.action", formData, function(result) {
//			var result = JSON.parse(result);
//			if (result.success) {
//				$("#accountCode").html(result.obj.rows[0].accountCode+"-"+result.obj.rows[0].accountBank);
//				$("#tradeDate").html($("#accountabc_date_qi").datebox('getValue')+"至"+$("#accountdef_date_shi").datebox('getValue'));
//				$(accountToreviewDg).datagrid('loadData',result.obj);
//			}else{
//				$.messager.show({
//					title : '提示',
//					msg : result.msg,
//					showType : 'slide'
//				});
//				$("#accountCode").html("");
//				$("#tradeDate").html("");
//				$(accountToreviewDg).datagrid('loadData',{total:0,rows:[]});
//			}
//			
//		});
	};
	
	/**
	 * 明细
	 */
	window.account.accountToreview.MingxiOnSelect = function() {
		var getrow = $(accountToreviewDg).datagrid('getSelections', $(accountToreviewDg).datagrid('getSelected'));
		if (getrow.length <= 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要处理的明细！',
				showType : 'slide'
			});
			return;
		}
		for ( var i = 0; i < getrow.length; i++) {
			var row = $(accountToreviewDg).datagrid('getRowIndex',getrow[i]);
			window.account.accountToreview.Mingxi(row);
		}
			
	};
	
	/**
	 * 单行确认
	 * 
	 * @param index
	 */
	window.account.accountToreview.querenOnIndex = function(index) {
		$.messager.confirm('确认', '您确认要处理改行吗？', function(r) {
			if (r) {
				var id = $(accountToreviewDg).datagrid('getRows')[index].id;
				$.post("/cs/account/accountDetailAction!AccountDetailToreview.action",
						{
							'id' : id,
						},
						function(result) {
							var result = JSON.parse(result);
							if (result.success) {
								window.account.accountToreview.accountToreviewOnclickSearch();
								$.messager.show({
									title : '操作提示',
									msg : result.msg,
									showType : 'slide'
								});
								/**
								 * 按钮不可用
								 */
								var selectRows = $(accountToreviewDg).datagrid('getSelections');
								if (selectRows.length == 1) {
									window.account.accountToreview.toolBarUpdate(selectRows[0]);
								} else {
									window.account.accountToreview.toolBarUpdateBySelects(selectRows);
								}
							}
					});
				
				
			}
		});
	};

	/**
	 * 多行确认
	 * 
	 * @note 必须选中记录
	 */
	window.account.accountToreview.querenOnSelect = function() {
		var getrow = $(accountToreviewDg).datagrid('getSelections', $(accountToreviewDg).datagrid('getSelected'));
		if (getrow.length <= 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要处理的记录！',
				showType : 'slide'
			});
			return;
		}
		$.messager.confirm('确认', '您确认要处理改行吗？', function(r) {
			if (r) {
				var id = "";
				for ( var i = 0; i < getrow.length; i++) {
					if(id!=""){
						id+=",";
					}
					id+=getrow[i].id;
				}
				$.post("/cs/account/accountDetailAction!AccountDetailToreview.action",
						{
							'id' : id,
						},
						function(result) {
							var result = JSON.parse(result);
							if (result.success) {
								window.account.accountToreview.accountToreviewOnclickSearch();
								$.messager.show({
									title : '操作提示',
									msg : result.msg,
									showType : 'slide'
								});
								/**
								 * 按钮不可用
								 */
								var selectRows = $(accountToreviewDg).datagrid('getSelections');
								if (selectRows.length == 1) {
									window.account.accountToreview.toolBarUpdate(selectRows[0]);
								} else {
									window.account.accountToreview.toolBarUpdateBySelects(selectRows);
								}
							}
					});
				
			};
		});
	};
	
	
	/**
	 * 单行驳回
	 * 
	 * @param index
	 */
	window.account.accountToreview.bohuiOnIndex = function(index) {
		$.messager.confirm('确认', '您确认想要驳回该行记录吗？', function(r) {
			if (r) {
				var id = $(accountToreviewDg).datagrid('getRows')[index].id;
				var type = $(accountToreviewDg).datagrid('getRows')[index].type;
				$.post("/cs/account/accountDetailAction!AccountDetailReject.action",
						{
							'id' : id,
							'type' : type
						},
						function(result) {
							var result = JSON.parse(result);
							if (result.success) {
								window.account.accountToreview.accountToreviewOnclickSearch();
								$.messager.show({
									title : '操作提示',
									msg : result.msg,
									showType : 'slide'
								});
								
							}
					});
				
			}
		});
	};

	/**
	 * 多行驳回
	 * 
	 * @note 必须选中记录
	 */
	window.account.accountToreview.bohuiOnSelect = function() {
		var getrow = $(accountToreviewDg).datagrid('getSelections', $(accountToreviewDg).datagrid('getSelected'));
		if (getrow.length <= 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要驳回的记录！',
				showType : 'slide'
			});
			return;
		}
		$.messager.confirm('确认', '您确认想要驳回该行记录吗？', function(r) {
		if (r) {
			var id = "";
			var type = "";
			for ( var i = 0; i < getrow.length; i++) {
				if(id!=""){
					id+=",";
				}
				if(type!=""){
					type+=",";
				}
				id+=getrow[i].id;
				type+=getrow[i].type;
			}
			$.post("/cs/account/accountDetailAction!AccountDetailReject.action",
					{
						'id' : id,
						'type' : type
					},
					function(result) {
						var result = JSON.parse(result);
						if (result.success) {
							window.account.accountToreview.accountToreviewOnclickSearch();
							$.messager.show({
								title : '操作提示',
								msg : result.msg,
								showType : 'slide'
							});
							
						}
				});
		}
		});
	};
	
	
	
	window.account.accountToreview.TiXing=function(){
		var date=new Date();
		if(date.getDate()+3>=settlement_day&&date.getDate()<=settlement_day){
			$.messager.show({
				title : '操作提示',
				msg : '关账日期为每个月的'+settlement_day+'号，请及时核对账户信息！',
				showType : 'slide'
			});
		}
	}
	
	
	$(function() {
		/**
		 * 高级查询不初始化加载
		 */
		//window.account.accountToreview.searchAccountToreview();
		
		/**
		 * 给页面查询加载时间
		 */
		var date=new Date();
		var d=date.getMonth()+1;
		var date =  date.getFullYear()+'-'+d+'-01';
		var date1=new Date();
		var d1=date1.getMonth()+1;
		var date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
		$("#accountabc_date_qi").datebox('setValue',date);
		$("#accountdef_date_shi").datebox('setValue',date1);

		window.account.accountToreview.TiXing();
		window.account.accountToreview.loadPageBar();
		window.account.accountToreview.bindButtonEvent();
		
	});
})(jQuery);