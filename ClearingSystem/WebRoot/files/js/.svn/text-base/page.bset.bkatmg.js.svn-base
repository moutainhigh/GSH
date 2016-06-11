(function($) {
	$.util.namespace("bset.bkatmg");
	$.util.namespace("bset.bkatmg.newb.add");
	$.util.namespace("bset.bkatmg.newb.pub");
	$.util.namespace("bset.bkatmg.newb.edit");
	$.util.namespace("bset.bkatmg.newb.search");
	$.util.namespace("bset.bkatmg.view");

	bset.bkatmg.selectIndex;// 当前选中行

	bset.bkatmg.bsetBkatmgDg = '#bset_bkatmg_dg';

	var newbtn = '#newbtn', editbtn = '#editbtn', removebtn = '#removebtn', viewbtn = '#viewbtn', tranbtn = '#tranbtn', activbtn = '#activbtn', lockbtn = '#lockbtn', unlockbtn = '#unlockbtn', logoffbtn = '#logoffbtn',jiesuobtn='#jiesuobtn',shenqingjiesuobtn='#shenqingjiesuobtn';

	var bsetBkatmgNewbForm = '#bset_bkatmg_newb_form', bsetBkatmgNewbAccountType = "#bset_bkatmg_newb_accountType", bsetBkatmgNewbAccountCurrency = "#bset_bkatmg_newb_accountCurrency", bsetBkatmgNewbAccountBank = "#bset_bkatmg_newb_accountBank", bsetBkatmgNewbAccountNumber = "#bset_bkatmg_newb_accountNumber", bsetBkatmgNewbSaveBtn = "#bset_bkatmg_newb_save_btn", bsetBkatmgNewbCloseBtn = "#bset_bkatmg_newb_close_btn", accountBankAdress = "#accountBankAdress", accountBankNumber = "#accountBankNumber";

	/**
	 * 验证规则
	 */
	$.extend($.fn.validatebox.defaults.rules, {
		accountCode : {
			validator : function(value, opt) {
				var rules = $.fn.validatebox.defaults.rules;
				var result = eval("(" + $.ajax( {
					url : "/cs/bset/accountAction!findBianli.action",
					dataType : "json/xml/html",
					data : {
						"accountCode" : value
					},
					async : false,
					cache : false,
					type : "post"
					}).responseText + ")");
				return result.success==false;
			},
			message : '账户编码已存在，请重新输入！'
		}
	});
	
	/**
	 * 打开新建银行账户窗口
	 */
	window.bset.bkatmg.openNewDialog = function() {
		$('<div id="bset_bkatmg_new_dialog">').dialog({
			title : "新建资金账户",
			href : $.util.resolveClientUrl('/cs/page/bset/bkatmg/newb.jsp'),
			width : 400,
			height : 365,
			top : 5,
			iconCls : 'bicon-asterisk',
			resizable : false,
			modal : true,
			closed : true,
			cache : false,
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				window.bset.bkatmg.newb.pub.init();
				$("#bset_bkatmg_newb_accountCurrency").combobox('setValue','CNY');
				$(bsetBkatmgNewbSaveBtn).click(function() {
					if (!$(bsetBkatmgNewbForm).form('validate')) {
						return;
					}
					var formData = $.serializeObject($(bsetBkatmgNewbForm));
					$.post("/cs/bset/accountAction!save.action", formData, function(result) {
						var result = JSON.parse(result);
						if (result.success) {
							$(bset.bkatmg.bsetBkatmgDg).datagrid('insertRow', {
								index : 0,
								row : result.obj
							});
							window.bset.bkatmg.closeNewDialog();
						}
						$.messager.show({
							title : '提示',
							msg : result.msg,
							showType : 'slide'
						});
					});
				});
				$(bsetBkatmgNewbCloseBtn).click(function() {
					window.bset.bkatmg.closeNewDialog();
				});
			}
		}).dialog('open');
	};
	
	/**
	 * 关闭新建银行账户窗口
	 */
	window.bset.bkatmg.closeNewDialog = function() {
		$('#bset_bkatmg_new_dialog').dialog('close');
	};

	/**
	 * 打开编辑银行账户窗口
	 * 
	 * @param index
	 */
	window.bset.bkatmg.openEditDialog = function(index) {
		bset.bkatmg.selectIndex = index;
		$('<div id="bset_bkatmg_edit_dialog">').dialog({
			title : "编辑银行账户",
			href : $.util.resolveClientUrl('/cs/page/bset/bkatmg/newb.jsp'),
			width : 400,
			height : 365,
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
				$('#accountCode').validatebox('disableValidation');
				$('#accountCode').attr('readonly','true');
				$('#accountCode').css({"background-color":"#EBEBE4"});
				window.bset.bkatmg.newb.pub.init();
				var row = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[bset.bkatmg.selectIndex];
				$(bsetBkatmgNewbForm).form('load', row);
				window.bset.bkatmg.newb.pub.accountCurrencyOnChange(row.accountCurrency, row.accountCurrency);
				window.bset.bkatmg.newb.pub.accountTypeOnChange(row.accountType, row.accountType);
				
				$(bsetBkatmgNewbSaveBtn).click(function() {
					if (!$(bsetBkatmgNewbForm).form('validate')) {
						return;
					}
					var formData = $.serializeObject($(bsetBkatmgNewbForm));
					formData.id = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].id;
					$.post("/cs/bset/accountAction!update.action", formData, function(result) {
						var result = JSON.parse(result);
						if (result.success) {
							$(bset.bkatmg.bsetBkatmgDg).datagrid('updateRow', {
								index : index,
								row : result.obj
							});
							window.bset.bkatmg.closeEditDialog();
						}
						$.messager.show({
							title : '提示',
							msg : result.msg,
							showType : 'slide'
						});
					});
					
				});
				$(bsetBkatmgNewbCloseBtn).click(function() {
					window.bset.bkatmg.closeEditDialog();
				});
			}
		}).dialog('open');
	};

	/**
	 * 打开编辑银行账户窗口
	 * 
	 * @note 必须选中记录
	 */
	window.bset.bkatmg.onSelectOpenEditDialog = function() {
		var index = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRowIndex', $(bset.bkatmg.bsetBkatmgDg).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要编辑的行！',
				showType : 'slide'
			});
			return;
		}
		window.bset.bkatmg.openEditDialog(index);
	};

	/**
	 * 关闭编辑银行账户窗口
	 */
	window.bset.bkatmg.closeEditDialog = function() {
		$('#bset_bkatmg_edit_dialog').dialog('close');
	};

	/**
	 * 打开信息显示窗口
	 * 
	 * @param index
	 */
	window.bset.bkatmg.openViewDlgByIndex = function(index) {
		bset.bkatmg.selectIndex = index;
		$('<div id="viewDlg">').dialog({
			title : "账户详细信息",
			href : $.util.resolveClientUrl('/cs/page/bset/bkatmg/view.jsp'),
			width : 340,
			height : 305,
			top : 5,
			iconCls : 'bicon-file',
			resizable : false,
			modal : true,
			closed : true,
			cache : false,
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				var row = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[bset.bkatmg.selectIndex];
				for ( var item in row) {
					var txt = row[item];
					if (item == "accountType") {
						txt = window.bset.bkatmg.accountTypeFormater(txt);
					} else if (item == "accountCurrency") {
						txt = window.bset.bkatmg.accountCurrencyFormater(txt);
					} else if (item == "accountStatus") {
						txt = window.bset.bkatmg.accountStatusFormater(txt);
					} else if (item == "defaultIncAccount" || item == "defaultPayAccount") {
						txt = txt == 0 ? "否" : "是";
					}
					$('#' + item + 'Txt').html(txt);
				}
				$('#btnClose').click(function() {
					$('#viewDlg').dialog('close');
				});
			}
		}).dialog('open');
	};

	/**
	 * 打开信息显示窗口
	 * 
	 * @note 必须选中记录
	 */
	window.bset.bkatmg.openViewDlgOnSelect = function() {
		var index = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRowIndex', $(bset.bkatmg.bsetBkatmgDg).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要查看的记录！',
				showType : 'slide'
			});
			return;
		}
		window.bset.bkatmg.openViewDlgByIndex(index);
	};

	/**
	 * 删除记录
	 * 
	 * @param index
	 */
	window.bset.bkatmg.removeByIndex = function(index) {
		$.messager.confirm('确认', '您确认想要删除记录吗？', function(r) {
			if (r) {
				var id = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].id;
				$.post("/cs/bset/accountAction!delete.action",
						{
							'accountId' : id
						},
						function(result) {
							var result = JSON.parse(result);
							if (result.success) {
								$(bset.bkatmg.bsetBkatmgDg).datagrid('deleteRow', index);
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
	 * 删除记录
	 * 
	 * @note 必须选中记录
	 */
	window.bset.bkatmg.removeOnSelect = function() {
		var index = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRowIndex', $(bset.bkatmg.bsetBkatmgDg).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要删除的行！',
				showType : 'slide'
			});
			return;
		}
		window.bset.bkatmg.removeByIndex(index);
	};

	/**
	 * 激活记录
	 * 
	 * @param index
	 */
	window.bset.bkatmg.activationByIndex = function(index) {
		$.messager.confirm('确认', '您确认想要激活记录吗？', function(r) {
			if (r) {
				var id = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].id;
				$.post("/cs/bset/accountAction!updateByStatus.action",
						{
							'accountId' : id,
							'accountStatus' : 1
						},
						function(result) {
							var result = JSON.parse(result);
							if (result.success) {
								$(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].accountStatus = 1;
								$(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].accountLock = 0;
								$(bset.bkatmg.bsetBkatmgDg).datagrid('refreshRow', index);
								$.messager.show({
									title : '操作提示',
									msg : '激活成功！',
									showType : 'slide'
								});
								window.bset.bkatmg.toolBarUpdate(1,0);
							}
					});
				
			}
		});
	};

	/**
	 * 激活记录
	 * 
	 * @note 必须选中记录
	 */
	window.bset.bkatmg.activationOnSelect = function() {
		var index = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRowIndex', $(bset.bkatmg.bsetBkatmgDg).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要激活的记录！',
				showType : 'slide'
			});
			return;
		}
		window.bset.bkatmg.activationByIndex(index);
	};

	/**
	 * 挂起记录
	 * 
	 * @param index
	 */
	window.bset.bkatmg.lockByIndex = function(index) {
		$.messager.confirm('确认', '您确认想要挂起记录吗？', function(r) {
			if (r) {
				var id = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].id;
				$.post("/cs/bset/accountAction!updateByStatus.action",
						{
							'accountId' : id,
							'accountStatus' : 2
						},
						function(result) {
							var result = JSON.parse(result);
							if (result.success) {
								$(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].accountStatus = 2;
								$(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].accountLock = 0;
								$(bset.bkatmg.bsetBkatmgDg).datagrid('refreshRow', index);
								$.messager.show({
									title : '操作提示',
									msg : '挂起成功！',
									showType : 'slide'
								});
								window.bset.bkatmg.toolBarUpdate(2,0);
							}
					});
			}
		});
	};

	/**
	 * 挂起记录
	 * 
	 * @note 必须选中记录
	 */
	window.bset.bkatmg.lockOnSelect = function() {
		var index = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRowIndex', $(bset.bkatmg.bsetBkatmgDg).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要挂起的记录！',
				showType : 'slide'
			});
			return;
		}
		window.bset.bkatmg.lockByIndex(index);
	};

	/**
	 * 解挂记录
	 * 
	 * @param index
	 */
	window.bset.bkatmg.unlockByIndex = function(index) {
		$.messager.confirm('确认', '您确认想要解挂记录吗？', function(r) {
			if (r) {
				var id = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].id;
				$.post("/cs/bset/accountAction!updateByStatus.action",
						{
							'accountId' : id,
							'accountStatus' : 1
						},
						function(result) {
							var result = JSON.parse(result);
							if (result.success) {
								$(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].accountStatus = 1;
								$(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].accountLock = 0;
								$(bset.bkatmg.bsetBkatmgDg).datagrid('refreshRow', index);
								$.messager.show({
									title : '操作提示',
									msg : '解挂成功！',
									showType : 'slide'
								});
								window.bset.bkatmg.toolBarUpdate(1,0);
							}
					});
			}
		});
	};

	/**
	 * 解挂记录
	 * 
	 * @note 必须选中记录
	 */
	window.bset.bkatmg.unlockOnSelect = function() {
		var index = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRowIndex', $(bset.bkatmg.bsetBkatmgDg).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要解挂的记录！',
				showType : 'slide'
			});
			return;
		}
		window.bset.bkatmg.unlockByIndex(index);
	};

	/**
	 * 注销记录
	 * 
	 * @param index
	 */
	window.bset.bkatmg.logoffByIndex = function(index) {
		$.messager.confirm('确认', '您确认想要注销记录吗？（注销后不能恢复！）', function(r) {
			if (r) {
				var id = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].id;
				$.post("/cs/bset/accountAction!updateByStatus.action",
						{
							'accountId' : id,
							'accountStatus' : 3
						},
						function(result) {
							var result = JSON.parse(result);
							if (result.success) {
								$(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].accountStatus = 3;
								$(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].accountLock = 0;
								$(bset.bkatmg.bsetBkatmgDg).datagrid('refreshRow', index);
								$.messager.show({
									title : '操作提示',
									msg : '注销成功！',
									showType : 'slide'
								});
								window.bset.bkatmg.toolBarUpdate(3,0);
							}
					});
			}
		});
	};

	/**
	 * 注销记录
	 * 
	 * @note 必须选中记录
	 */
	window.bset.bkatmg.logoffOnSelect = function(index) {
		var index = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRowIndex', $(bset.bkatmg.bsetBkatmgDg).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要注销的记录！',
				showType : 'slide'
			});
			return;
		}
		window.bset.bkatmg.logoffByIndex(index);
	};
	
//	window.bset.bkatmg.searchbtn=function(){
//		$('<div id="bset_bkatmg_search">').dialog({
//			title : "查询银行账户管理",
//			href : $.util.resolveClientUrl('/cs/page/bset/bkatmg/search.jsp'),
//			width : 250,
//			height : 210,
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
//				$("#bkatmg_search").click(function() {
//					if (!$("#bset_bkatmg_search_form").form('validate')) {
//						return;
//					}
//					var formData = $.serializeObject($("#bset_bkatmg_search_form"));
//					$.post("/cs/bset/accountAction!findAllSearch.action", formData, function(result) {
//						var result = JSON.parse(result);
//						if (result.success) {
//							$(bset.bkatmg.bsetBkatmgDg).datagrid('loadData',result.obj);
//						}else{
//							$(bset.bkatmg.bsetBkatmgDg).datagrid('loadData',{total:0,rows:[]});
//							$.messager.show({
//								title : '提示',
//								msg : '未找到银行账户信息',
//								showType : 'slide'
//							});
//						}
//						$('#bset_bkatmg_search').dialog('close');
//					});
//				});
//				$("#bkatmg_close").click(function() {
//					$('#bset_bkatmg_search').dialog('close');
//				});
//			}
//		}).dialog('open');
//	};
	
	window.bset.bkatmg.onSelectZhuanzhangDialog = function() {
		var index = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRowIndex', $(bset.bkatmg.bsetBkatmgDg).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要转账的账户！',
				showType : 'slide'
			});
			return;
		}
		window.bset.bkatmg.openZhuanzhangOnSelect(index);
	};
	
	window.bset.bkatmg.openZhuanzhangOnSelect=function(index){
		bset.bkatmg.selectIndex = index;
		$('<div id="ZhuanzhangDlg">').dialog({
			title : "账户转账功能",
			href : $.util.resolveClientUrl('/cs/page/bset/bkatmg/zhuanzhang.jsp'),
			width : 300,
			height : 290,
			top : 5,
			iconCls : 'bicon-file',
			resizable : false,
			modal : true,
			closed : true,
			cache : false,
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				
				var row = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[bset.bkatmg.selectIndex];
				$('#moneyYue').numberbox('setValue',row.accountBalance);
				$('#zhaunchuZhanghu').val(row.accountCode);
				$('#accountCurrency').val(row.accountCurrency);
				$('#zhuanchuKaihuhang').val(row.accountBank);
				$('#zhaunchuZhanghu').validatebox('disableValidation');
				$('#zhaunchuZhanghu').attr('readonly','true');
				$('#zhaunchuZhanghu').css({"background-color":"#EBEBE4"});
				$('#moneyYue').validatebox('disableValidation');
				$('#moneyYue').attr('readonly','true');
				$('#moneyYue').css({"background-color":"#EBEBE4"});
				
				$('#zhuanruAccount').combogrid({    
					panelWidth:400,    
		            idField:'accountCode',    
		            textField:'accountBank',    
		            url:'/cs/bset/accountAction!findAccount.action?code='+$('#zhaunchuZhanghu').val()+'&accountCurrency='+$('#accountCurrency').val(),    
		            columns:[[    
		                {field:'accountCode',title:'账户编码',width:60},    
		                {field:'accountType',title:'账户类型',width:50,
		                	formatter: function(value,row,index){
			    				if (value == 'C'){
			    					return '公司';
			    				} else if(value == 'P'){
			    					return '个人';
			    				}else if(value == 'M'){
			    					return '现金';
			    				}else if(value == 'X'){
			    					return '信用卡';
			    				}else if(value == 'O'){
			    					return '第三方账户';
			    				}
			    			}
		                },    
		                {field:'accountCurrency',title:'账户币种',width:50},    
		                {field:'accountBank',title:'开户银行',width:70},
		                {field:'accountNumber',title:'账户号码',width:150}       
		            ]],
		            onHidePanel: function(){   
			           		$('#zhuanruKaihuhang').val($('#zhuanruAccount').combogrid('grid').datagrid('getSelected').accountBank); 
			           		$('#currencyType').val($('#zhuanruAccount').combogrid('grid').datagrid('getSelected').accountCurrency); 
			       			
			        }
				}); 
				 


				
				$('#btnzz').click(function() {
					if (!$("#zhuanzhangForm").form('validate')) {
						return;
					}
					var formData = $.serializeObject($("#zhuanzhangForm"));
					$.post("/cs/account/accountDetailAction!zhuanZhang.action", formData, function(result) {
						var result = JSON.parse(result);
						if (result.success) {
							$(bset.bkatmg.bsetBkatmgDg).datagrid('load');
							$('#ZhuanzhangDlg').dialog('close');
						}
						$.messager.show({
							title : '提示',
							msg :  result.msg,
							showType : 'slide'
						});
						
						
					});
					
					
				});
				$('#btnzzClose').click(function() {
					$('#ZhuanzhangDlg').dialog('close');
				});
			}
		}).dialog('open');
	};
	
	window.bset.bkatmg.newb.search.accountTypeOnChange=function(accountType,oldValue){
		var accountBankTitle="开户银行";
		if(accountType == "C" || accountType == "P" || accountType == "X"){
			accountBankTitle = "开户银行";
		}else if(accountType == "O"){
			accountBankTitle="第三方名称"
		}else{
			$("#accountBank").validatebox('disableValidation');
			$("#accountBank").attr('readonly', true);
		}
		$('#accountBank_kaihuhang').html(accountBankTitle);
	};

	/**
	 * 按钮事件绑定
	 */
	window.bset.bkatmg.bindButtonEvent = function() {
		$(newbtn).click(function() {
			window.bset.bkatmg.openNewDialog();
		});
		$(editbtn).click(function() {
			if (!$(editbtn).linkbutton('options').disabled) {
				window.bset.bkatmg.onSelectOpenEditDialog();
			}
		});
		$(removebtn).click(function() {
			if (!$(removebtn).linkbutton('options').disabled) {
				window.bset.bkatmg.removeOnSelect();
			}
		});
		$(activbtn).click(function() {
			if (!$(activbtn).linkbutton('options').disabled) {
				window.bset.bkatmg.activationOnSelect();
			}
		});
		$(viewbtn).click(function() {
			if (!$(viewbtn).linkbutton('options').disabled) {
				window.bset.bkatmg.openViewDlgOnSelect();
			}
		});
		$(tranbtn).click(function() {
			if (!$(tranbtn).linkbutton('options').disabled) {
				window.bset.bkatmg.onSelectZhuanzhangDialog();
			}
		});
		$(lockbtn).click(function() {
			if (!$(lockbtn).linkbutton('options').disabled) {
				window.bset.bkatmg.lockOnSelect();
			}
		});
		$(unlockbtn).click(function() {
			if (!$(unlockbtn).linkbutton('options').disabled) {
				window.bset.bkatmg.unlockOnSelect();
			}
		});
		$(logoffbtn).click(function() {
			if (!$(logoffbtn).linkbutton('options').disabled) {
				window.bset.bkatmg.logoffOnSelect();
			}
		});
//		$(searchbtn).click(function() {
//			if (!$(searchbtn).linkbutton('options').disabled) {
//				window.bset.bkatmg.searchbtn();
//			}
//		});
		$(jiesuobtn).click(function(){
			if (!$(jiesuobtn).linkbutton('options').disabled) {
				window.bset.bkatmg.jiesuobkatmg();
			}
		});	
		
		$(shenqingjiesuobtn).click(function(){
			if (!$(shenqingjiesuobtn).linkbutton('options').disabled) {
				window.bset.bkatmg.shenqingjiesuobkatmg();
			}
		});
		
		$('#btSearch').click(function(){
			window.bset.bkatmg.searchZhu();
		});

	};
	
	window.bset.bkatmg.searchZhu=function(){
		if (!$("#bkatmg_form").form('validate')) {
			return;
		}
		var formData = $.serializeObject($("#bkatmg_form"));
		$.post("/cs/bset/accountAction!findAllSearch.action", formData, function(result) {
			var result = JSON.parse(result);
			if (result.success) {
				$(bset.bkatmg.bsetBkatmgDg).datagrid('loadData',result.obj);
			}else{
				$.messager.show({
					title : '提示',
					msg : result.msg,
					showType : 'slide'
				});
				$(bset.bkatmg.bsetBkatmgDg).datagrid('loadData',{total:0,rows:[]});
			}
			
		});
	};

	/**
	 * 解锁
	 */
	window.bset.bkatmg.jiesuobkatmg=function(){
		var index = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRowIndex', $(bset.bkatmg.bsetBkatmgDg).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要解锁的记录！',
				showType : 'slide'
			});
			return;
		}
		window.bset.bkatmg.jiesuobkatmgByIndex(index);
	};
	/**
	 * 解锁操作
	 */
	window.bset.bkatmg.jiesuobkatmgByIndex=function(index){
		$.messager.confirm('确认', '您确认想要解锁这条信息吗？', function(r) {
			if (r) {
				var id = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].id;
				var accountStatus = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].accountStatus;
				$.post("/cs/bset/accountAction!updateByaccountLock.action",
						{
							'accountId' : id,
							'accountLock' : 1
						},
						function(result) {
							var result = JSON.parse(result);
							if (result.success) {
								$(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].accountLock = 1;
								$(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].accountStatus = accountStatus;
								$(bset.bkatmg.bsetBkatmgDg).datagrid('refreshRow', index);
								$.messager.show({
									title : '操作提示',
									msg : '解锁成功！',
									showType : 'slide'
								});
								window.bset.bkatmg.toolBarUpdate(accountStatus,1);
							}
							
					});
			}
		});soa_companysys_service_app
	};
	
	/**
	 * 申请解锁
	 */
	window.bset.bkatmg.shenqingjiesuobkatmg=function(){
		var index = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRowIndex', $(bset.bkatmg.bsetBkatmgDg).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要申请解锁的记录！',
				showType : 'slide'
			});
			return;
		}
		window.bset.bkatmg.shenqingjiesuobkatmgByIndex(index);
	};
	window.bset.bkatmg.shenqingjiesuobkatmgByIndex=function(index){
		$.messager.confirm('确认', '您确认想要申请解锁这条信息吗？', function(r) {
			if (r) {
				var id = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].id;
				var accountStatus = $(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].accountStatus;
				$.post("/cs/bset/accountAction!updateByaccountLock.action",
						{
							'accountId' : id,
							'accountLock' : 2
						},
						function(result) {
							var result = JSON.parse(result);
							if (result.success) {
								$(bset.bkatmg.bsetBkatmgDg).datagrid('getRows')[index].accountLock = 2;
								$(bset.bkatmg.bsetBkatmgDg).datagrid('refreshRow', index);
								$.messager.show({
									title : '操作提示',
									msg : '申请解锁成功！',
									showType : 'slide'
								});
								window.bset.bkatmg.toolBarUpdate(accountStatus,2);
							}
							
					});
			}
		});
	};
	
	/**
	 * datagrid 设置分页栏
	 */
	window.bset.bkatmg.loadPageBar = function() {
		$(bset.bkatmg.bsetBkatmgDg).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
		});
		if(ZJZHGLXJ==false){
			$("#newbtn").hide();
		}
		if(ZJZHGLBJ==false){
			$("#editbtn").hide();
		}
		if(ZJZHGLCK==false){
			$("#viewbtn").hide();
		}
		if(ZJZHGLSC==false){
			$("#removebtn").hide();
		}
		if(ZJZHGLZZ==false){
			$("#tranbtn").hide();
		}
		if(ZJZHGLJH==false){
			$("#activbtn").hide();
		}
		if(ZJZHGLGQ==false){
			$("#lockbtn").hide();
		}
		if(ZJZHGLJG==false){
			$("#unlockbtn").hide();
		}
		if(ZJZHGLZX==false){
			$("#logoffbtn").hide();
		}
		if(ZJZHGLSQJS==false){
			$("#shenqingjiesuobtn").hide();
		}
		if(ZJZHGLJS==false){
			$("#jiesuobtn").hide();
		}
		
	};

	/**
	 * 工具栏按钮显示影藏
	 */
	window.bset.bkatmg.toolBarUpdate = function(accountStatus,accountLock) {
		$(editbtn).linkbutton('disable');
		$(removebtn).linkbutton('disable');
		$(tranbtn).linkbutton('disable');
		$(activbtn).linkbutton('disable');
		$(lockbtn).linkbutton('disable');
		$(unlockbtn).linkbutton('disable');
		$(logoffbtn).linkbutton('disable');
		$(jiesuobtn).linkbutton('disable');
		$(shenqingjiesuobtn).linkbutton('disable');
		$("#viewbtn").linkbutton('enable');
		if (accountStatus == 0) {
			$(editbtn).linkbutton('enable');
			$(removebtn).linkbutton('enable');
			$(activbtn).linkbutton('enable');
		} else if (accountStatus == 1&&accountLock==1) {
			$(tranbtn).linkbutton('enable');
			$(lockbtn).linkbutton('enable');
			$(logoffbtn).linkbutton('enable');
		} else if (accountStatus == 2&&accountLock==1) {
			$(unlockbtn).linkbutton('enable');
			$(logoffbtn).linkbutton('enable');
		}
		
		if(accountLock == 0){//这里后面需要加判断，管理员是解锁，普通用户是申请解锁，目前没有那个条件就先放一起
			$(shenqingjiesuobtn).linkbutton('enable');
		}else if(accountLock == 2){
			$(jiesuobtn).linkbutton('enable');
		}
	};
	
	window.bset.bkatmg.load = function(){
		$("#viewbtn").linkbutton('disable');
		$(editbtn).linkbutton('disable');
		$(removebtn).linkbutton('disable');
		$(tranbtn).linkbutton('disable');
		$(activbtn).linkbutton('disable');
		$(lockbtn).linkbutton('disable');
		$(unlockbtn).linkbutton('disable');
		$(logoffbtn).linkbutton('disable');
		$(jiesuobtn).linkbutton('disable');
		$(shenqingjiesuobtn).linkbutton('disable');
	}
	

	// dtgd 事件，formater

	window.bset.bkatmg.onSelect = function(index, row) {
		window.bset.bkatmg.toolBarUpdate(row.accountStatus,row.accountLock);
		
	};

	window.bset.bkatmg.optionsFormater = function(value, row, index) {
		var options="";
		if(ZJZHGLBJ==true&&row.accountStatus == 0){
			options += '<i class="bicon-edit" onclick="window.bset.bkatmg.openEditDialog($.getDatagridRowIndex(this))"></i>';
		}
		if(ZJZHGLCK==true){
			options += '<i class="bicon-folder-open" onclick="window.bset.bkatmg.openViewDlgByIndex($.getDatagridRowIndex(this));"></i>';
		}
		if(ZJZHGLSC==true&&row.accountStatus == 0){
			options += ' <i class="bicon-remove" onclick="window.bset.bkatmg.removeByIndex($.getDatagridRowIndex(this))"></i> ';
		}
		if(ZJZHGLZZ==true&&row.accountStatus == 1&&row.accountLock==1){
			options += ' <i class="fa fa-clipboard" onclick="window.bset.bkatmg.openZhuanzhangOnSelect($.getDatagridRowIndex(this))"></i>';
		}
		if(ZJZHGLJH==true&&row.accountStatus == 0){
			options += ' <i class="fa fa-bolt" onclick="window.bset.bkatmg.activationByIndex($.getDatagridRowIndex(this));"></i>';
		}
		if(ZJZHGLGQ==true&&row.accountStatus == 1&&row.accountLock==1){
			options += ' <i class="fa fa-link" onclick="window.bset.bkatmg.lockByIndex($.getDatagridRowIndex(this));"></i>';
			options += ' <i class="bicon-off" onclick="window.bset.bkatmg.logoffByIndex($.getDatagridRowIndex(this));"></i>';
		}
		if(ZJZHGLJG==true&&row.accountStatus == 2&&row.accountLock==1){
			options += ' <i class="fa fa-unlink" onclick="window.bset.bkatmg.unlockByIndex($.getDatagridRowIndex(this));"></i>';
		}
		if(ZJZHGLZX==true&&row.accountStatus == 2&&row.accountLock==1){
			options += ' <i class="bicon-off" onclick="window.bset.bkatmg.logoffByIndex($.getDatagridRowIndex(this));"></i>';
		}
		if(ZJZHGLSQJS==true&&row.accountLock==0){
			options += ' <i class="fa fa-lock" onclick="window.bset.bkatmg.shenqingjiesuobkatmgByIndex($.getDatagridRowIndex(this));"></i>';
		}
		if(ZJZHGLJS==true&&row.accountLock == 2){
			options += ' <i class="fa fa-lock" onclick="window.bset.bkatmg.jiesuobkatmgByIndex($.getDatagridRowIndex(this));"></i>';
		}
		
//		var options = '<i class="bicon-folder-open" onclick="window.bset.bkatmg.openViewDlgByIndex($.getDatagridRowIndex(this));"></i>';
//		if (row.accountStatus == 0) {
//			options += ' <i class="bicon-edit" onclick="window.bset.bkatmg.openEditDialog($.getDatagridRowIndex(this))"></i> <i class="bicon-remove" onclick="window.bset.bkatmg.removeByIndex($.getDatagridRowIndex(this))"></i> <i class="fa fa-bolt" onclick="window.bset.bkatmg.activationByIndex($.getDatagridRowIndex(this));"></i>';
//		} else if (row.accountStatus == 1&&row.accountLock==1) {
//			options += ' <i class="fa fa-clipboard"></i> <i class="fa fa-link" onclick="window.bset.bkatmg.lockByIndex($.getDatagridRowIndex(this));"></i> <i class="fa fa-lock" onclick="window.bset.bkatmg.logoffByIndex($.getDatagridRowIndex(this));"></i>';
//		} else if (row.accountStatus == 2&&row.accountLock==1) {
//			options += ' <i class="fa fa-unlink" onclick="window.bset.bkatmg.unlockByIndex($.getDatagridRowIndex(this));"></i> <i class="fa fa-lock" onclick="window.bset.bkatmg.logoffByIndex($.getDatagridRowIndex(this));"></i>';
//		}
		return options;
	};

	window.bset.bkatmg.accountCodeFormater = function(value, row, index) {
		return '<a href="#" class="link-btn">' + value + '</a>';
	};

	window.bset.bkatmg.accountTypeFormater = function(value, row, index) {

		if (value == "C") {
			return "公司";
		} else if (value == "P") {
			return "个人";
		} else if (value == "M") {
			return "现金";
		} else if (value == "X") {
			return "信用卡";
		} else if (value == "O") {
			return "第三方支付";
		} else {
			return "未知";
		}

	};

	window.bset.bkatmg.accountCurrencyFormater = function(value, row, index) {
		switch(value){
			case 'CNY' :
				return '人民币';
			case 'AUD' :
				return '澳大利亚元';
			case 'BRL' :
				return '巴西雷亚尔';
			case 'CAD' :
				return '加拿大元';
			case 'CHF' :
				return '瑞士法郎';
			case 'DKK' :
				return '丹麦克朗';
			case 'EUR' :
				return '欧元';
			case 'GBP' :
				return '英镑';
			case 'HKD' :
				return '港币';
			case 'IDR' :
				return '印尼卢比';
			case 'JPY' :
				return '日元';
			case 'KRW' :
				return '韩国元';
			case 'PTC' :
				return '澳门元';
			case 'MYR' :
				return '林吉特';
			case 'NOK' :
				return '挪威克朗';
			case 'NZD' :
				return '新西兰元';
			case 'PHP' :
				return '菲律宾比索';
			case 'SUR' :
				return '卢布';
			case 'SEK' :
				return '瑞典克朗';
			case 'SGD' :
				return '新加坡元';
			case 'THB' :
				return '泰国铢';
			case 'TWD' :
				return '新台币';
			case 'USD' :
				return '美元';
		};
	};

	window.bset.bkatmg.accountStatusFormater = function(value, row, index) {

		if (value == 0) {
			return "未激活";
		} else if (value == 1) {
			return "使用中";
		} else if (value == 2) {
			return "已挂起";
		} else if (value == 3) {
			return "已注销";
		} else {
			return "未知";
		}

	};

	window.bset.bkatmg.accountStatusStyler = function(value, row, index) {

		if (value == 0) {
			return 'background-color:blue;color:#fff;';
		} else if (value == 1) {
			return 'background-color:green;color:#fff;';
		} else if (value == 2) {
			return 'background-color:orange;color:#fff;';
		} else if (value == 3) {
			return 'background-color:red;color:#fff;';
		} else {
			return 'background-color:#ccc;color:#000;';
		}

	};
	
	/**
	 * 0已锁、1未锁2、申请解锁
	 */
	window.bset.bkatmg.accountLockStyler = function(value, row, index) {

		if (value == 0) {
			return 'background-color:red;color:#fff;';
		} else if (value == 1) {
			return 'background-color:green;color:#fff;';
		}else if (value == 2) {
			return 'background-color:orange;color:#fff;';
		}

	};
	window.bset.bkatmg.accountLockFormater = function(value, row, index) {

		if (value == 0) {
			return "已锁";
		} else if (value == 1) {
			return "未锁";
		}else if (value == 2) {
			return "申请解锁";
		}

	};

	window.bset.bkatmg.accountBalanceFormater = function(value, row, index) {
		return $.formatCurrency(value);
	};

	/**
	 * 账户类型onChange事件
	 */
	window.bset.bkatmg.newb.pub.accountTypeOnChange = function(accountType, oldValue) {
		if(accountType=="C"||accountType=="P"||accountType=="M"||accountType=="X"){
			$("#bset_bkatmg_newb_accountCurrency").combobox('select', 'CNY');
		}
		var accountCurrency = $(bsetBkatmgNewbAccountCurrency).combobox('getValue');
		if(accountType=="C"||accountType=="P"||accountType=="O"||accountType=="X"){
			$("#tr1").show(); 
			$("#tr2").show(); 
			$("#tr3").show(); 
			$("#tr4").show(); 
			$("#tr5").show(); 
			$("#tr6").show();
			$("#bset_bkatmg_newb_accountBank").validatebox('enableValidation');
			$("#bset_bkatmg_newb_accountNumber").validatebox('enableValidation');
			$('#bset_bkatmg_newb_accountCurrency').combobox('reload','/cs/files/baseJson/CurrencyUnit.json'); 
		}else{
			$("#tr1").hide(); 
			$("#tr2").hide();
			$("#tr3").hide(); 
			$("#tr4").hide(); 
			$("#tr5").hide(); 
			$("#tr6").hide();
			$("#bset_bkatmg_newb_accountBank").validatebox('disableValidation');
			$("#bset_bkatmg_newb_accountNumber").validatebox('disableValidation');
			$('#bset_bkatmg_newb_accountCurrency').combobox('reload','/cs/files/baseJson/CNY.json'); 
		}
		
		if(((accountType=="P"||accountType=="X")&&accountCurrency=="CNY")||accountType=="O"||accountType=="M"){
			$("#tr3").hide(); 
			$("#tr4").hide(); 
			$("#tr5").hide(); 
			$("#tr6").hide();
		}else{
			$("#tr3").show(); 
			$("#tr4").show(); 
			$("#tr5").show(); 
			$("#tr6").show();
		}
		
		
		
		var accountOwnerTitle = "开户人";
		var accountBankTitle = "开户银行";
//		$(bsetBkatmgNewbAccountBank).validatebox('enableValidation');
//		$(bsetBkatmgNewbAccountBank).attr('readonly', false);
		$(bsetBkatmgNewbAccountNumber).validatebox('enableValidation');
		$(bsetBkatmgNewbAccountNumber).attr('readonly', false);
		if (accountType != "C") {// 默认账户处理
//			$('input[name=defaultIncAccount]').attr("disabled", true);
//			$('input[name=defaultPayAccount]').attr("disabled", true);
		} else if ($('input[name=accountStatus]:checked').val() == 1) {
//			$('input[name=defaultIncAccount]').attr("disabled", false);
//			$('input[name=defaultPayAccount]').attr("disabled", false);
		}
		if (accountType == "C" || accountType == "P" || accountType == "X") {
			accountBankTitle = "开户银行";
			if (accountType == "C") {
				if (accountCurrency == "CNY") {
					accountOwnerTitle = "开户名称CN";
					var result = eval("(" + $.ajax( {
						url : "/cs/armgt/receivable!findAffiliationNo.action",
						dataType : "json/xml/html",
						async : false,
						cache : false,
						type : "post"
					}).responseText + ")");
					var json=JSON.parse(result.obj);
					$('#accountOwner').val(json[1].cName);
				} else{
					accountOwnerTitle = "开户名称EN";
					$('#accountOwner').val("");
				}
			} else {
				accountOwnerTitle = "持卡人";
				$('#accountOwner').val("");
			}
		} else {
			$('#accountOwner').val("");
			if (accountType == "O" || accountType == "M") {
//				$(bsetBkatmgNewbAccountNumber).validatebox('disableValidation');
//				$(bsetBkatmgNewbAccountNumber).attr('readonly', true);
				if (accountType == "M") {
					accountOwnerTitle = "自定义账户名";
					$("#bset_bkatmg_newb_accountBank").validatebox('disableValidation');
					$("#bset_bkatmg_newb_accountNumber").validatebox('disableValidation');
				} else {
					$("#bset_bkatmg_newb_accountBank").validatebox('enableValidation');
					$("#bset_bkatmg_newb_accountNumber").validatebox('enableValidation');
					$(bsetBkatmgNewbAccountNumber).validatebox('disableValidation');
					$(bsetBkatmgNewbAccountNumber).attr('readonly', true);
					accountBankTitle = "第三方名称";
					accountOwnerTitle = "用户名";
				}
			}
		}
		$('#bset_bkatmg_newb_accountBankTitle').html(accountBankTitle);
		$('#bset_bkatmg_newb_accountOwnerTitle').html(accountOwnerTitle);
	};

	/**
	 * 账户币种onChange事件
	 */
	window.bset.bkatmg.newb.pub.accountCurrencyOnChange = function(accountCurrency, oldValue) {
		var accountType = $(bsetBkatmgNewbAccountType).combobox('getValue');
		var accountOwnerTitle;
		if (accountType == "C") {
			if (accountCurrency == "CNY") {
				accountOwnerTitle = "开户公司(CN)";
				var result = eval("(" + $.ajax( {
					url : "/cs/armgt/receivable!findAffiliationNo.action",
					dataType : "json/xml/html",
					async : false,
					cache : false,
					type : "post"
				}).responseText + ")");
				var json=JSON.parse(result.obj);
				$('#accountOwner').val(json[1].cName);
			} else {
				accountOwnerTitle = "开户公司(EN)";
				$('#accountOwner').val("");
			}
			$('#bset_bkatmg_newb_accountOwnerTitle').html(accountOwnerTitle);
		}
		if (accountCurrency == "CNY") {
			$(accountBankAdress).validatebox('disableValidation');
			$(accountBankNumber).validatebox('disableValidation');
			$("#accountBankAdress").validatebox({    
			    required: false
			});
			$("#accountBankNumber").validatebox({    
			    required: false
			});
			$("#tr5").show(); 
			$("#tr6").show();
			
		} else {
			$(accountBankAdress).validatebox('enableValidation');
			$(accountBankNumber).validatebox('enableValidation');
			$("#accountBankAdress").validatebox({    
			    required: true
			});
			$("#accountBankNumber").validatebox({    
			    required: true
			});
			$("#tr5").hide(); 
			$("#tr6").hide();
		}
	};

	/**
	 * 账户新增编辑页 初始化
	 */
	window.bset.bkatmg.newb.pub.init = function() {
		$("#bset_bkatmg_newb_accountNumber").formatInput();
		$("#accountCode").upperbox();
		$('input[name=accountStatus]').click(function() {
			var cdval = $('input[name=accountStatus]:checked').val();
			if (cdval == 1) {
				if ($('#bset_bkatmg_newb_accountType').combobox('getValue') == "C") {
					$('input[name=defaultIncAccount]').attr("disabled", false);
					$('input[name=defaultPayAccount]').attr("disabled", false);
				}
			} else {
				$("input[name='defaultIncAccount'][value='0']").attr("checked", true);
				$("input[name='defaultPayAccount'][value='0']").attr("checked", true);
				$('input[name=defaultIncAccount]').attr("disabled", true);
				$('input[name=defaultPayAccount]').attr("disabled", true);
			}
		});
	};

	$(function() {
		window.bset.bkatmg.load();
		window.bset.bkatmg.loadPageBar();
		window.bset.bkatmg.bindButtonEvent();
		//alert('<%=session.getAttribute("abc")%>');
	});

})(jQuery);