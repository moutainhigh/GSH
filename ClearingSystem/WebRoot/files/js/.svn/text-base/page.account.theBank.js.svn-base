(function($) {

	$.util.namespace("account.theBank");
	$.util.namespace("account.theBank.NewtheBank");
	
	var testData12345 = {
			"total" : 10,
			"rows" : [
			 {
				"toaccountId" : "1",
				"toaccountDate" : "2014-05-11",
				"fuKuanfang" : "上海XXXXX公司 C90111",
				"toaccountMoney" : "9800.00",
				"documentNumber" : "",
				"recordedAccount" : "王五",
				"state" : "0"
			}],
			"footer":[{
				"toaccountId" : "0",
				"toaccountDate" : "",
				"fuKuanfang" : "小计：",
				"toaccountMoney" : "122300.00",
				"documentNumber" : "",
				"recordedAccount" : "",
				"state" : ""
				}]
		};
	
	var testData23456 = {
			"total" : 10,
			"rows" : [
			{
				"toaccountId" : "2",
				"toaccountDate" : "2014-05-13",
				"fuKuanfang" : "上海XXXXX公司",
				"toaccountMoney" : "108000.00",
				"documentNumber" : "",
				"recordedAccount" : "李四",
				"state" : "0"
			}],
			"footer":[{
				"toaccountId" : "0",
				"toaccountDate" : "",
				"fuKuanfang" : "小计：",
				"toaccountMoney" : "122300.00",
				"documentNumber" : "",
				"recordedAccount" : "",
				"state" : ""
				}]
		};
	/**
	 * 主页面的按钮和datagrid
	 */
	var theBankNO = '#theBankNO',theBankNew = "#theBankNew",theBankEdit='#theBankEdit',theBankSearchGaoji='#theBank_search_gaoji';
	var accountTheBankDg = '#account_theBank_dg';
	
	/**
	 * 新增页面的按钮
	 */
	var theBankSave='#theBank_save',theBankRemoud='#theBank_remoud';
	var accountTheBankNewtheBankForm='#account_theBank_newtheBank_form';
	
	/**
	 *查询页面 
	 */
	var accountTheBankSerchtheBankForm='#account_theBank_serchtheBank_form';
	var theBankSearch='#theBank_search',theBankClose='#theBank_close';
	
	
	/**
	 * datagrid 设置分页栏
	 */
	window.account.theBank.loadPageBar = function() {
		$(accountTheBankDg).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ],
		});
	};
	
	
	/**
	 * 初始化屏蔽全部按钮
	 */
	window.account.theBank.disableToobar = function() {
		$(theBankEdit).linkbutton('disable');
		$(theBankNO).linkbutton('disable');
	};
	/**
	 * 单选模式工具栏按钮显示影藏
	 */
	window.account.theBank.toolBarUpdate = function(row) {
		window.account.theBank.disableToobar();
		if (row.state == 0) {
			$(theBankEdit).linkbutton('enable');
			$(theBankNO).linkbutton('enable');
		}
	};
	/**
	 * 多选模式工具栏按钮显示影藏
	 */
	window.account.theBank.toolBarUpdateBySelects = function(rows) {
		window.account.theBank.disableToobar();
		// 验证值
		var status = true;
		for ( var i = 0; i < rows.length; i++) {
			if (status && rows[i].state != 0) {
				status = false;
			}
			if (!status) {
				return;
			}
		}
		if (status){
			$(theBankNO).linkbutton('enable');
		}
	};
	
	/**
	 * 选择一行
	 */
	window.account.theBank.onSelect = function(index, row) {
		var selectRows = $(accountTheBankDg).datagrid('getSelections');
		if (selectRows.length == 1) {
			window.account.theBank.toolBarUpdate(row);
		} else {
			window.account.theBank.toolBarUpdateBySelects(selectRows);
		}
	};
	
	/**
	 * 取消选择一行
	 */
	window.account.theBank.onUnselect = function(index, row) {
		var selectRows = $(accountTheBankDg).datagrid('getSelections');
		if (selectRows.length == 1) {
			window.account.theBank.toolBarUpdate(selectRows[0]);
		} else if (selectRows.length > 1) {
			window.account.theBank.toolBarUpdateBySelects(selectRows);
		}
	};
	
	
	
	/**
	 * 状态
	 */
	window.account.theBank.status= function(value, row, index){
		if(row.toaccountId==0){
			return;
		}
		if (row.state == 0) {
			return "未认领";
		} else if (row.state == 1) {
			return "已认领-王二麻子";
		}
	};
	
	/**
	 * 状态的样式
	 */
	window.account.theBank.accountStatusStyler=function(value, row, index){
		if(row.toaccountId==0){
			return;
		}
		if (row.state == 0) {
			return 'background-color:blue;color:#fff;';
		} else if (row.state == 1) {
			return 'background-color:green;color:#fff;';
		}
	};
	
	
	/**
	 * 发布银行到账信息页面
	 */
	window.account.theBank.NewtheBank = function(){
		$('<div id="account_theBank_dialog">').dialog({
			title : "发布银行到账信息",
			href : $.util.resolveClientUrl('/cs/page/account/theBank/NewtheBank.jsp'),
			width : 280,
			height : 250,
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
				
				/**
				 * 保存
				 */
				$(theBankSave).click(function(){
					if (!$(accountTheBankNewtheBankForm).form('validate')) {
						return;
					}
					var str = JSON.stringify( $.serializeObject($(accountTheBankNewtheBankForm)));
					var json = jQuery.parseJSON(str);
					$(accountTheBankDg).datagrid('insertRow',{
						index:0,
						row:{
							toaccountDate: json.toaccountDate,
							fuKuanfang: json.fuKuanfang,
							toaccountMoney: json.toaccountMoney,
							documentNumber: json.documentNumber,
							recordedAccount: json.recordedAccount,
							state: json.state
						}
					});
					$.messager.show({
						title : '操作提示',
						msg : '保存成功',
						showType : 'slide'
					});
					$("#account_theBank_dialog").dialog('close');
					
				});
				
				/**
				 * 放弃
				 */
				$(theBankRemoud).click(function(){
					$("#account_theBank_dialog").dialog('close');
				});
			}
		}).dialog('open');
	};
	
	/**
	 * 编辑银行到账信息页面
	 */
	window.account.theBank.EdittheBank = function(){
		$('<div id="account_theBankEdit_dialog">').dialog({
			title : "编辑银行到账信息",
			href : $.util.resolveClientUrl('/cs/page/account/theBank/NewtheBank.jsp'),
			width : 280,
			height : 250,
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
				var getrow = $(accountTheBankDg).datagrid('getSelections', $(accountTheBankDg).datagrid('getSelected'));
				var index = $(accountTheBankDg).datagrid('getRowIndex',getrow[0]);
				var node = $(accountTheBankDg).datagrid('getSelected');
				$("#bank_toaccountMoney").numberbox('setValue',node.toaccountMoney);
				$("#bank_toaccountDate").datebox('setValue',node.toaccountDate);
				$('#bank_fuKuanfang').combogrid('setValue',node.fuKuanfang);
				$('#bank_recordedAccount').combogrid('setValue',node.recordedAccount);
				$("#bank_documentNumber").val(node.documentNumber);
				/**
				 * 保存
				 */
				$(theBankSave).click(function(){
					if (!$(accountTheBankNewtheBankForm).form('validate')) {
						return;
					}
					var str = JSON.stringify( $.serializeObject($(accountTheBankNewtheBankForm)));
					var json = jQuery.parseJSON(str);
					$(accountTheBankDg).datagrid('updateRow',{
						index:index,
						row:{
							toaccountDate: json.toaccountDate,
							fuKuanfang: json.fuKuanfang,
							toaccountMoney: json.toaccountMoney,
							documentNumber: json.documentNumber,
							recordedAccount: json.recordedAccount,
							state: json.state
						}
					});
					$(accountTheBankDg).datagrid('refreshRow', index);
					$.messager.show({
						title : '操作提示',
						msg : '修改成功',
						showType : 'slide'
					});
					$("#account_theBankEdit_dialog").dialog('close');
					
				});
				
				/**
				 * 放弃
				 */
				$(theBankRemoud).click(function(){
					$("#account_theBankEdit_dialog").dialog('close');
				});
			}
		}).dialog('open');
	};
	
	/**
	 * 查询银行入账发布
	 */
	window.account.theBank.searchtheBank = function(){
		$('<div id="theBank_searchtheBank_dialog">').dialog({
			title : "查询银行入账发布",
			href : $.util.resolveClientUrl('/cs/page/account/theBank/searchNewtheBank.jsp'),
			width : 260,
			height : 210,
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
				$(theBankSearch).click(function(){
					if (!$(accountTheBankSerchtheBankForm).form('validate')) {
						return;
					}
					var str = JSON.stringify( $.serializeObject($(accountTheBankSerchtheBankForm)));
					var json = jQuery.parseJSON(str);
					if(json.fukuanfang=='上海XXXXXxxxx旅行社 S90222'){
						$(accountTheBankDg).datagrid('loadData',testData12345);
					}else if(json.fukuanfang=='上海XXXXX公司'){
						$(accountTheBankDg).datagrid('loadData',testData23456);
					}else{
						$.messager.show({
							title : '操作提示',
							msg : '未查到任何信息，请输入正确信息！',
							showType : 'slide'
						});
					}
					
					$("#theBank_searchtheBank_dialog").dialog('close');
				});
				
				/**
				 * 关闭
				 */
				$(theBankClose).click(function(){
					$("#theBank_searchtheBank_dialog").dialog('close');
				});
			}
		}).dialog('open');
	};
	
	
	/**
	 * 编辑
	 */
	window.account.theBank.edittheBank_no1=function(){
		var bool=true;
		var getrow = $(accountTheBankDg).datagrid('getSelections', $(accountTheBankDg).datagrid('getSelected'));
		if (getrow.length <= 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择编辑的记录！',
				showType : 'slide'
			});
			return;
		}
		for ( var i = 0; i < getrow.length; i++) {
			if(getrow[i].state==1){
				$.messager.show({
					title : '提示',
					msg : getrow[i].fuKuanfang+"已认领，不可编辑，请重新选择"
				});
				bool=false;
				return;
			}
			
		}
		if(bool==true){
			window.account.theBank.EdittheBank();
		}
		
	};
	
	/**
	 * 删除
	 */
	window.account.theBank.deletetheBank=function(){
		var getrow = $(accountTheBankDg).datagrid('getSelections', $(accountTheBankDg).datagrid('getSelected'));
		if (getrow.length <= 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要删除的记录！',
				showType : 'slide'
			});
			return;
		}
		$.messager.confirm('确认', '您确定要删除所选择的记录吗？', function(r) {
		if (r) {
			for ( var i = 0; i < getrow.length; i++) {
				var row = $(accountTheBankDg).datagrid('getRowIndex',getrow[i]);
				$(accountTheBankDg).datagrid('deleteRow', row);
			}
			$.messager.show({
				title : '操作提示',
				msg : '删除成功！',
				showType : 'slide'
			});
		}
		});
	};
	
	
	/**
	 * 按钮事件绑定
	 */
	window.account.theBank.bindButtonEvent = function() {
		/**
		 * 发布银行到账信息
		 */
		$(theBankNew).click(function(){
			window.account.theBank.NewtheBank();
		});
		
		/**
		 * 删除
		 */
		$(theBankNO).click(function(){
			if (!$(theBankNO).linkbutton('options').disabled) {
				window.account.theBank.deletetheBank();
			}
		});
		/**
		 * 编辑
		 */
		$(theBankEdit).click(function(){
			if (!$(theBankEdit).linkbutton('options').disabled) {
				window.account.theBank.edittheBank_no1();
			}
		});
		/**
		 * 高级查询
		 */
		$(theBankSearchGaoji).click(function(){
			window.account.theBank.searchtheBank();
		});
	};
	
	
	
	$(function() {
		window.account.theBank.loadPageBar();
		window.account.theBank.bindButtonEvent();
	});

})(jQuery);