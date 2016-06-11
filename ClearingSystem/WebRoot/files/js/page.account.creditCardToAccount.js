(function($) {

	$.util.namespace("account.ccta");

	var dtgd = '#dtgd';

	window.account.ccta.optionsFormatter = function(value, row, index) {
		if (!row.id)
			return value;
		var options = '';
		if (btn_sure_enable == "enable")
			options += ' <i class="bicon-ok" onclick="window.account.ccta.sure($.getDatagridRowIndex(this));"></i>';
		return options;
	};

	window.account.ccta.amountFormatter = function(value, row, index) {
		var v=value+row.customerFactorage;
		return $.formatCurrencySign(v, currency_local);
	};

	window.account.ccta.onSelect = function(index, row) {
		// $('#btn_sure').linkbutton('disable');
		$('#btn_sure').linkbutton(btn_sure_enable);
	};

	window.account.ccta.sure = function(index) {
		$('<div id="dlg_sure">').dialog({
			title : "确认到账",
			href : $.util.resolveClientUrl('/cs/page/account/ccta/sure.jsp'),
			width : 250,
			height : 'auto',
			top : 5,
			iconCls : 'bicon-ok',
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
					$.post("/cs/account/ccta!sure.action", form_data, function(result) {
						var result = JSON.parse(result);
						if (result.success) {
							$(dtgd).datagrid('deleteRow', index);
							$('#dlg_sure').dialog('close');
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
					$('#dlg_sure').dialog('close');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				var row = $(dtgd).datagrid('getRows')[index];
				if (row.paymentMethod == 2)
					$('.pm').removeClass('hidden');
				$('#form').form('load', row);
			}
		}).dialog('open');
	};
	
	window.account.ccta.search=function(){
		var formData = $.serializeObject($("#creditCardToAccount_form"));
		$.post("/cs/account/ccta!findAllSearch.action", formData, function(result) {
			var result = JSON.parse(result);
			if (result.success) {
				$("#dtgd").datagrid('loadData',result.obj);
			}else{
				$.messager.show({
					title : '提示',
					msg : result.msg,
					showType : 'slide'
				});
				$("#dtgd").datagrid('loadData',{total:0,rows:[]});
			}
			
		});
	};

	$(function() {
		var date=new Date();
		var d=date.getMonth()+1;
		var date =  date.getFullYear()+'-'+d+'-01';
		var date1=new Date();
		var d1=date1.getMonth()+1;
		var date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
		$("#incomeDateQi").datebox('setValue',date);
		$("#incomeDateShi").datebox('setValue',date1);
		
		$(dtgd).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
		});
		$('#btn_sure').linkbutton('disable');
		$('#btn_sure').click(function() {
			if (!$('#btn_sure').linkbutton('options').disabled) {
				window.account.ccta.sure($(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected')));
			}
		});
		
		$('#btnSearch').click(function(){
			window.account.ccta.search();
		});
	});

})(jQuery);