(function($) {

	$.util.namespace("bset.creditCardFee");
	$.util.namespace("bset.creditCardFee.editer");
	$.util.namespace("bset.creditCardFee.add");
	$.util.namespace("bset.creditCardFee.edit");
	$(function() {
		if(XYKSXFXJ==false){
			$("#btnAdd").hide();
		}
		if(XYKSXFBJ==false){
			$("#btnEdit").hide();
		}
		if(XYKSXFSC==false){
			$("#btnRemove").hide();
		}
		if(XYKSXFSQJS==false){
			$("#btnshenqingjiesuo").hide();
		}
		if(XYKSXFJS==false){
			$("#btnjiesuo").hide();
		}
	});
	
	var dtgd = '#dtgd', btnAdd = "#btnAdd", btnEdit = "#btnEdit", btnRemove = "#btnRemove", btnQuery = "#btnQuery", btnSearch = "#btnSearch";
	var btnshenqingjiesuo='#btnshenqingjiesuo',btnjiesuo='#btnjiesuo';
	window.bset.creditCardFee.loadPageBar = function() {
		$(dtgd).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
		});
	};
	
	/**
	 * 验证规则
	 */
	$.extend($.fn.validatebox.defaults.rules, {
		creditCode : {
			validator : function(value, opt) {
				var rules = $.fn.validatebox.defaults.rules;
				var creditCode = $("#creditCode").combobox('getValue');
				var result = eval("(" + $.ajax( {
					url : "/cs/bset/creditcardfeeServiceAction!findBianli.action",
					dataType : "json/xml/html",
					data : {
						"creditCode" : creditCode
					},
					async : false,
					cache : false,
					type : "post"
					}).responseText + ")");
				return result.success==false;
			},
			message : '信用卡代码已存在，请重新选择！'
		}
	});

	window.bset.creditCardFee.toolBarBindEvent = function() {
		$(btnAdd).click(function() {
			if (!$(btnAdd).linkbutton('options').disabled) {
				window.bset.creditCardFee.add.open();
			}
		});
		$(btnEdit).click(function() {
			if (!$(btnEdit).linkbutton('options').disabled) {
				window.bset.creditCardFee.edit.openOnSelect();
			}
		});
		$(btnRemove).click(function() {
			if (!$(btnAdd).linkbutton('options').disabled) {
				window.bset.creditCardFee.removeOnSelect();
			}
		});
		$(btnSearch).click(function() {
			if (!$(btnAdd).linkbutton('options').disabled) {
				window.bset.creditCardFee.search();
			}
		});
		$(btnshenqingjiesuo).click(function(){
			if (!$(btnshenqingjiesuo).linkbutton('options').disabled) {
				window.bset.creditCardFee.shenqingjiesuo();
			}
		});
		$(btnjiesuo).click(function(){
			if (!$(btnjiesuo).linkbutton('options').disabled) {
				window.bset.creditCardFee.jiesuo();
			}
		});
		
	};
	
	window.bset.creditCardFee.search=function(){
		if (!$("#creditCardFee_form").form('validate')) {
			return;
		}
		var formData = $.serializeObject($("#creditCardFee_form"));
		$.post("/cs/bset/creditcardfeeServiceAction!findAllSearch.action", formData, function(result) {
			var result = JSON.parse(result);
			if (result.success) {
				$(dtgd).datagrid('loadData',result.obj);
			}else{
				$(dtgd).datagrid('loadData',{total:0,rows:[]});
				$.messager.show({
					title : '提示',
					msg : '未找到信用卡手续费信息',
					showType : 'slide'
				});
			}
			
		});
	}
	
	/**
	 * 申请解锁
	 */
	window.bset.creditCardFee.shenqingjiesuo=function(){
		var index = $(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要申请解锁的记录！',
				showType : 'slide'
			});
			return;
		}
		window.bset.creditCardFee.shenqingjiesuoByIndex(index);
	};
	window.bset.creditCardFee.shenqingjiesuoByIndex=function(index){
		$.messager.confirm('确认', '您确认想要申请解锁这条信息吗？', function(r) {
			if (r) {
				var id = $(dtgd).datagrid('getRows')[index].id;
				$.post("/cs/bset/creditcardfeeServiceAction!updateByCreditcardfeeLock.action",
						{
							'creditcardfeeId' : id,
							'creditcardfeeLock' : 2
						},
						function(result) {
							var result = JSON.parse(result);
							if (result.success) {
								$(dtgd).datagrid('getRows')[index].creditcardfeeLock = 2;
								$(dtgd).datagrid('refreshRow', index);
								$.messager.show({
									title : '操作提示',
									msg : '申请解锁成功！',
									showType : 'slide'
								});
								window.bset.creditCardFee.toolBarUpdate(2);
							}
							
					});
			}
		});
	};
	

	/**
	 * 解锁
	 */
	window.bset.creditCardFee.jiesuo=function(){
		var index = $(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要解锁的记录！',
				showType : 'slide'
			});
			return;
		}
		window.bset.creditCardFee.jiesuoByIndex(index);
	};
	/**
	 * 解锁操作
	 */
	window.bset.creditCardFee.jiesuoByIndex=function(index){
		$.messager.confirm('确认', '您确认想要解锁这条信息吗？', function(r) {
			if (r) {
				var id = $(dtgd).datagrid('getRows')[index].id;
				$.post("/cs/bset/creditcardfeeServiceAction!updateByCreditcardfeeLock.action",
						{
							'creditcardfeeId' : id,
							'creditcardfeeLock' : 1
						},
						function(result) {
							var result = JSON.parse(result);
							if (result.success) {
								$(dtgd).datagrid('getRows')[index].creditcardfeeLock = 1;
								$(dtgd).datagrid('refreshRow', index);
								$.messager.show({
									title : '操作提示',
									msg : '解锁成功！',
									showType : 'slide'
								});
								window.bset.creditCardFee.toolBarUpdate(1);
							}
							
					});
			}
		});
	};
	

	/**
	 * 0已锁、1未锁2、申请解锁
	 */
	window.bset.creditCardFee.LockStyler = function(value, row, index) {

		if (value == 0) {
			return 'background-color:red;color:#fff;';
		} else if (value == 1) {
			return 'background-color:green;color:#fff;';
		}else if (value == 2) {
			return 'background-color:orange;color:#fff;';
		}

	};
	window.bset.creditCardFee.LockFormater = function(value, row, index) {
		if (value == 0) {
			return "已锁";
		} else if (value == 1) {
			return "未锁";
		}else if (value == 2) {
			return "申请解锁";
		}

	};
	
	/**
	 * 工具栏按钮显示影藏
	 */
	window.bset.creditCardFee.toolBarUpdate = function(creditCardfeeLock) {
		$(btnEdit).linkbutton('disable');
		$(btnRemove).linkbutton('disable');
		$(btnjiesuo).linkbutton('disable');
		$(btnshenqingjiesuo).linkbutton('disable');
		if(creditCardfeeLock == 0){//这里后面需要加判断，管理员是解锁，普通用户是申请解锁，目前没有那个条件就先放一起
			$(btnshenqingjiesuo).linkbutton('enable');
		}else if(creditCardfeeLock == 2){
			$(btnjiesuo).linkbutton('enable');
		}else if(creditCardfeeLock == 1){
			$(btnEdit).linkbutton('enable');
			$(btnRemove).linkbutton('enable');
		}
	};
	
	window.bset.creditCardFee.load = function(){
		$(btnEdit).linkbutton('disable');
		$(btnRemove).linkbutton('disable');
		$(btnjiesuo).linkbutton('disable');
		$(btnshenqingjiesuo).linkbutton('disable');
	};
	
	// dtgd 事件，formater

	window.bset.creditCardFee.onSelect = function(index, row) {
		window.bset.creditCardFee.toolBarUpdate(row.creditcardfeeLock);
		
	};
	
	
	window.bset.creditCardFee.optionsFormater = function(value, row, index) {
		var options = "";
		if(XYKSXFBJ==true&&row.creditcardfeeLock == 1){
			options += '<i class="bicon-edit" onclick="window.bset.creditCardFee.edit.open($.getDatagridRowIndex(this));"></i>';
		}
		if(XYKSXFSC==true&&row.creditcardfeeLock == 1){
			options += ' <i class="bicon-remove" onclick="window.bset.creditCardFee.remove($.getDatagridRowIndex(this));"></i>';
		}
		if(XYKSXFSQJS==true&&row.creditcardfeeLock == 0){
			options += ' <i class="fa fa-lock" onclick="window.bset.creditCardFee.shenqingjiesuoByIndex($.getDatagridRowIndex(this));"></i>';
		}
		if(XYKSXFJS==true&&row.creditcardfeeLock == 2){
			options += ' <i class="fa fa-lock" onclick="window.bset.creditCardFee.jiesuoByIndex($.getDatagridRowIndex(this));"></i>';
		}
//		if (row.creditcardfeeLock == 1) {
//			var options = '<i class="bicon-edit" onclick="window.bset.creditCardFee.edit.open($.getDatagridRowIndex(this));"></i>';
//			options += ' <i class="bicon-remove" onclick="window.bset.creditCardFee.remove($.getDatagridRowIndex(this));"></i>';
//			return options;
//		}
		return options;
	};


	/**
	 * 打开新建窗口
	 * 
	 */
	window.bset.creditCardFee.add.open = function() {
		$('<div id="dlgAdd">').dialog({
			title : "新建",
			href : $.util.resolveClientUrl('/cs/page/bset/creditCardFee/editer.jsp'),
			width : 280,
			height : 220,
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
				$('#creditDesc').validatebox('disableValidation');
				$('#creditDesc').attr('readonly','true');
				$('#creditDesc').css({"background-color":"#EBEBE4"});
				window.bset.creditCardFee.add.init();
			}
		}).dialog('open');
	};

	window.bset.creditCardFee.add.close = function() {
		$('#dlgAdd').dialog('close');
	};

	window.bset.creditCardFee.add.init = function() {
		$('#btnSave').click(function() {
			window.bset.creditCardFee.add.save();
		});
		$('#btnClose').click(function() {
			window.bset.creditCardFee.add.close();
		});
	};

	window.bset.creditCardFee.add.save = function() {
		if (!$('form').form('validate')) {
			return;
		}
		var formData = $.serializeObject($("#form"));
		$.post("/cs/bset/creditcardfeeServiceAction!save.action", formData, function(result) {
			var result = JSON.parse(result);
			if (result.success) {
				$(dtgd).datagrid('insertRow', {
					index : 0,
					row : result.obj
				});
				window.bset.creditCardFee.add.close();
			}
			$.messager.show({
				title : '提示',
				msg : result.msg,
				showType : 'slide'
			});
		});
		
	};

	/**
	 * 打开编辑窗口
	 * 
	 * @param index
	 */
	window.bset.creditCardFee.edit.open = function(index) {
		$('<div id="dlgEdit">').dialog({
			title : "编辑",
			href : $.util.resolveClientUrl('/cs/page/bset/creditCardFee/editer.jsp'),
			width : 280,
			height : 250,
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
				$('#creditCode').combobox('disable');
				$('#creditDesc').validatebox('disableValidation');
				$('#creditDesc').attr('readonly','true');
				$('#creditDesc').css({"background-color":"#EBEBE4"});
				window.bset.creditCardFee.edit.init(index);
			}
		}).dialog('open');
	};

	/**
	 * 打开编辑窗口
	 * 
	 */
	window.bset.creditCardFee.edit.openOnSelect = function() {
		var index = $(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要编辑的记录！',
				showType : 'slide'
			});
			return;
		}
		window.bset.creditCardFee.edit.open(index);
	};

	/**
	 * 关闭编辑窗口
	 * 
	 */
	window.bset.creditCardFee.edit.close = function() {
		$('#dlgEdit').dialog('close');
	};

	window.bset.creditCardFee.edit.init = function(index) {

		$('#form').form('load', $(dtgd).datagrid('getRows')[index]);

		$('#btnSave').click(function() {
			window.bset.creditCardFee.edit.save(index);
		});
		$('#btnClose').click(function() {
			window.bset.creditCardFee.edit.close();
		});
	};

	window.bset.creditCardFee.edit.save = function(index) {
		if (!$('#form').form('validate')) {
			return;
		}
		
		var formData = $.serializeObject($("#form"));
		formData.id = $(dtgd).datagrid('getRows')[index].id;
		$.post("/cs/bset/creditcardfeeServiceAction!update.action", formData, function(result) {
			var result = JSON.parse(result);
			if (result.success) {
				$(dtgd).datagrid('updateRow', {
					index : index,
					row : result.obj
				});
				window.bset.creditCardFee.edit.close();
				window.bset.creditCardFee.toolBarUpdate(0);
			}
			$.messager.show({
				title : '提示',
				msg : result.msg,
				showType : 'slide'
			});
		});
	};

	/**
	 * 删除
	 * 
	 * @param index
	 */
	window.bset.creditCardFee.remove = function(index) {
		$.messager.confirm('确认', '您确认想要删除记录吗？', function(r) {
			if (r) {
				var id = $(dtgd).datagrid('getRows')[index].id;
				$.post("/cs/bset/creditcardfeeServiceAction!delete.action",
						{
							'creditCardFeeId' : id
						},
						function(result) {
							var result = JSON.parse(result);
							if (result.success) {
								$(dtgd).datagrid('deleteRow', index);
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
	window.bset.creditCardFee.removeOnSelect = function() {
		var index = $(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要删除的行！',
				showType : 'slide'
			});
			return;
		}
		window.bset.creditCardFee.remove(index);
	};

	$(function() {
		window.bset.creditCardFee.load();
		window.bset.creditCardFee.loadPageBar();
		window.bset.creditCardFee.toolBarBindEvent();
	});

})(jQuery);