(function($) {

	$.util.namespace("bset.dataPermission");
	$.util.namespace("bset.dataPermission.editer");
	$.util.namespace("bset.dataPermission.add");
	$.util.namespace("bset.dataPermission.edit");

	var dtgd = '#dtgd', btnAdd = "#btnAdd", btnEdit = "#btnEdit", btnRemove = "#btnRemove", btnQuery = "#btnQuery", btnSearch = "#btnSearch";

	window.bset.dataPermission.loadPageBar = function() {
		$(dtgd).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
		});
	};

	window.bset.dataPermission.toolBarBindEvent = function() {
		$(btnAdd).click(function() {
			window.bset.dataPermission.add.open();
		});
		$(btnEdit).click(function() {
			window.bset.dataPermission.edit.openOnSelect();
		});
		$(btnRemove).click(function() {
			window.bset.dataPermission.removeOnSelect();
		});
		$(btnQuery).click(function() {

		});
		$(btnSearch).click(function() {

		});
	};

	window.bset.dataPermission.optionsFormater = function(value, row, index) {
		var options = '<i class="bicon-edit" onclick="window.bset.dataPermission.edit.open($.getDatagridRowIndex(this));"></i>';
		options += ' <i class="bicon-remove" onclick="window.bset.dataPermission.remove($.getDatagridRowIndex(this));"></i>';
		return options;
	};

	window.bset.dataPermission.editer.codeOnHidePanel = function() {
		$('input[name=name]').val($(this).combobox('getText'));
	};

	/**
	 * 打开新建窗口
	 * 
	 */
	window.bset.dataPermission.add.open = function() {
		$('<div id="dp_dlgAdd">').dialog({
			title : "新建",
			href : $.util.resolveClientUrl('/cs/page/bset/dataPermission/add.jsp'),
			width : 280,
			height : 220,
			top : 5,
			iconCls : 'bicon-asterisk',
			resizable : false,
			modal : true,
			closed : true,
			cache : false,
			buttons : [ {
				text : '<i class="fa fa-save"></i> 保存',
				handler : function() {
					$("#permission").val($('#idPermission').combotree('getText'));
					$("#uname").val($('#uid').combobox('getText'));
					if (!$('#form').form('validate')) {
						return;
					}
					var form_data = $.serializeObject($('#form'));
					$.post("/cs/bset/dataPermissionAction!save.action",form_data,function(result) {
						var result = JSON.parse(result);
						if (result.success) {
							$(dtgd).datagrid('insertRow',{
								index : 0,
								row : result.obj
							});
							$('#dp_dlgAdd').dialog('close');
						}
						$.messager.show({
							title : '提示',
							msg : result.msg,
							showType : 'slide'
						});
					});
				}
			}, {
				text : '<i class="fa fa-times"></i> 取消',
				handler : function() {
					$('#dp_dlgAdd').dialog('close');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				window.bset.dataPermission.add.init();
			}
		}).dialog('open');
	};

	

	/**
	 * 打开编辑窗口
	 * 
	 * @param index
	 */
	window.bset.dataPermission.edit.open = function(index) {
		$('<div id="dlgEdit">').dialog({
			title : "编辑",
			href : $.util.resolveClientUrl('/cs/page/bset/dataPermission/add.jsp'),
			width : 280,
			height : 220,
			top : 5,
			iconCls : 'bicon-edit',
			resizable : false,
			modal : true,
			closed : true,
			cache : false,
			buttons : [ {
				text : '<i class="fa fa-save"></i> 保存',
				handler : function() {
					$("#permission").val($('#idPermission').combotree('getText'));
					$("#uname").val($('#uid').combobox('getText'));
					if (!$('#form').form('validate')) {
						return;
					}
					var form_data = $.serializeObject($('#form'));
					$.post("/cs/bset/dataPermissionAction!update.action",form_data,function(result) {
						var result = JSON.parse(result);
						if (result.success) {
							$(dtgd).datagrid('load');
							$('#dlgEdit').dialog('close');
						}
						$.messager.show({
							title : '提示',
							msg : result.msg,
							showType : 'slide'
						});
					});
				}
			}, {
				text : '<i class="fa fa-times"></i> 取消',
				handler : function() {
					$('#dlgEdit').dialog('close');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				window.bset.dataPermission.edit.init(index);
			}
		}).dialog('open');
	};

	/**
	 * 打开编辑窗口
	 * 
	 */
	window.bset.dataPermission.edit.openOnSelect = function() {
		var index = $(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要编辑的记录！',
				showType : 'slide'
			});
			return;
		}
		window.bset.dataPermission.edit.open(index);
	};

	/**
	 * 关闭编辑窗口
	 * 
	 */
	window.bset.dataPermission.edit.close = function() {
		$('#dlgEdit').dialog('close');
	};

	window.bset.dataPermission.edit.init = function(index) {
		$('#form').form('load', $(dtgd).datagrid('getRows')[index]);
		$('#idPermission').combotree({    
					    url: '/cs/bset/dataPermissionAction!findDeptByCid.action',
//					    value:$(dtgd).datagrid('getRows')[index].idPermission,
					    multiple: true,
					    required:true,
					    editable:false
					}); 
		var strs= new Array(); //定义一数组 
		strs=$(dtgd).datagrid('getRows')[index].idPermission.split(","); //字符分割 
		$('#idPermission').combotree('setValues',strs);
		 var result = eval("("+ $.ajax({
				url : '/cs/bset/dataPermissionAction!findUidByDeptString.action?dept='+$(dtgd).datagrid('getRows')[index].idPermission,
				dataType : "json/xml/html",
				async : false,
				cache : false,
				type : "post"
				}).responseText + ")");
			$("#idName").val(result.obj);
			$("#name").val(result.msg);
		
		$('#uid').combogrid({    
		    url:'/cs/bset/dataPermissionAction!findCsUser.action',  
		    value:$(dtgd).datagrid('getRows')[index].uid,
		    multiple: false,
		    required:true,
		    editable:false,
		    idField:'uid',    
	    	textField:'name',
		    columns:[[    
		        {field:'name',title:'用户',width:100},    
		        {field:'email',title:'Email',width:150},    
		    ]]   
		});  
	};

	window.bset.dataPermission.edit.save = function(index) {
		if (!$('#form').form('validate')) {
			return;
		}
		$(dtgd).datagrid('updateRow', {
			index : index,
			row : $.serializeObject($('#form'))
		});
		$.messager.show({
			title : '操作提示',
			msg : '修改成功',
			showType : 'slide'
		});
		window.bset.dataPermission.edit.close();
	};

	/**
	 * 删除
	 * 
	 * @param index
	 */
	window.bset.dataPermission.remove = function(index) {
		$.messager.confirm('确认', '您确认想要删除记录吗？', function(r) {
			if (r) {
				$.post("/cs/bset/dataPermissionAction!delete.action", {
					id : $(dtgd).datagrid('getRows')[index].id
				}, function(result) {
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
	window.bset.dataPermission.removeOnSelect = function() {
		var index = $(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected'));
		if (index < 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要删除的行！',
				showType : 'slide'
			});
			return;
		}
		window.bset.dataPermission.remove(index);
	};

	$(function() {
		window.bset.dataPermission.loadPageBar();
		window.bset.dataPermission.toolBarBindEvent();
	});

})(jQuery);