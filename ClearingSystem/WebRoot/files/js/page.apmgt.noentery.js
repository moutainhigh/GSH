(function($) {

	$.util.namespace("apmgt.noentery");

	var dtgd = '#dtgd',btnentry = '#btn_entry';
	var noenteryLuruDatagrid='#noenteryLuru_datagrid',noenteryLuruAdd='#noenteryLuruAdd',noenteryLuruDelete='#noenteryLuruDelete',noenteryLuruJieshu='#noenteryLuruJieshu';
	var bianjizhuangtai=0;//0是未开启编辑状态1、开启编辑状态
	
	
	window.apmgt.noentery.loadPageBar = function() {
		$(dtgd).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
		});
	};

	window.apmgt.noentery.optionsFormatter = function(value, row, index) {
		var options;
		if (btnentry == "enable")
			options += ' <i class="bicon-pencil" onclick="window.apmgt.noentery.submit($.getDatagridRowIndex(this));"></i>';
		return options;
	};

	window.apmgt.noentery.statusFormatter = function(value, row, index) {
		if (value == 5) {
			return "待确认";
		}
	};

	window.apmgt.noentery.amountFormatter = function(value, row, index) {
		if(row.currencyType==undefined){
			return $.formatCurrencySign(value, "CNY");
		}
		return $.formatCurrencySign(value, row.currencyType);
	};

//	window.apmgt.noentery.onLoadSuccess = function(data) {
//		var sumfootertable = '<table class="sum-footer">';
//		var row1 = '<tr><th></th>';
//		var row2 = '<tr><th>小计：</th>';
//		var row3 = '<tr><th>总计：</th>';
//		var i = 1;
//		for ( var item in data.sumfooter) {
//			row1 += '<th class="sum-footer-' + i + ' sum-footer-left">实付金额（' + item + '）</th>';
//			row3 += '<td class="sum-footer-' + i + ' sum-footer-left">' + data.sumfooter[item][0].payAmount + '</td>';
//			if (data.sumfooter[item][1]) {
//				row2 += '<td class="sum-footer-' + i + ' sum-footer-left">' + data.sumfooter[item][1].payAmount + '</td>';
//			} else {
//				row2 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + 0.00 + '</td>';
//			}
//			if (i = 1)
//				i++;
//			else
//				i--;
//		}
//		sumfootertable += row1 + '</tr>' + row2 + '</tr>' + row3 + '</tr></table>';
//		$('#sum_footer').html(sumfootertable);
//	};

	window.apmgt.noentery.disableToobar = function() {
		$(btnentry).linkbutton('disable');
	};

	window.apmgt.noentery.onSelect = function(index, row) {
		window.apmgt.noentery.disableToobar();
//		if (row.noticeStatus == 1 && row.productStatus == 1) {
			$(btnentry).linkbutton('enable');
//		}
	};
	
	
	var wtoChangeflog = false;
	/**
	 * 修改
	 */
	window.apmgt.noentery.onClickRow = function(rowIndex, rowData) {
		wtoChangeflog = false;
		$(noenteryLuruDatagrid).datagrid('beginEdit', rowIndex);
	};
	
	invoiceMoneyOnChange=function(){
		
	};

	/**
	 * 凭证录入
	 * 
	 * @param index
	 */
	window.apmgt.noentery.luru = function(index) {
		$('<div id="noentery_dialog">').dialog({
			title : "凭证录入",
			href : $.util.resolveClientUrl('/cs/page/ap-mgt/Noentry/noentryLuru.jsp'),
			width : 600,
			height : 260,
			top : 5,
			draggable : true,
			iconCls : 'bicon-plus',
			resizable : false,
			modal : true,
			closed : true,
			cache : false,
			buttons : [ {
				text : '<i class="fa fa-save"></i> 录入',
				handler : function() {
					noentery_entry_save();
				}
			}, {
				text : '<i class="fa fa-times"></i> 放弃',
				handler : function() {
					noentery_entry_close();
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				var getrow1 = $(dtgd).datagrid('getSelections', $(dtgd).datagrid('getSelected'));
				$("#settlementNo").val(getrow1[0].settlementNo);
				$("#transactionOp").val(getrow1[0].supplierNo);
				$("#amountMoney").numberbox({prefix:$.convertCurrencyUnit(getrow1[0].currencyType)});
				$("#amountMoney").numberbox('setValue',getrow1[0].payAmount);
				var date1=new Date();
				var d1=date1.getMonth()+1;
				date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
				$("#transactionDate").datebox('setValue',date1);
				
				if (!$(noenteryLuruAdd).linkbutton('options').disabled) {
					$(noenteryLuruDatagrid).datagrid('insertRow',{
						index: 0,	// 索引从0开始
						row: {
							invoiceNo: '',
							invoiceMoney: getrow1[0].payAmount,
							invoiceDate: date1
						}
					});
				}
				$(noenteryLuruDatagrid).datagrid('beginEdit', 0);
				
				$(noenteryLuruAdd).click(function(){
					var rows = $('#noenteryLuru_datagrid').datagrid('getRows');
					for ( var r = 0; r < rows.length; r++) {
						if (!$('#noenteryLuru_datagrid').datagrid('validateRow', r)) {
							return;
						}
					}
					if (!$(noenteryLuruAdd).linkbutton('options').disabled) {
						$(noenteryLuruDatagrid).datagrid('insertRow',{
							index: 0,	// 索引从0开始
							row: {
								invoiceNo: '',
								invoiceMoney: getrow1[0].payAmount,
								invoiceDate: date1
							}
						});
					}
					$(noenteryLuruDatagrid).datagrid('beginEdit', 0);
				});
				$(noenteryLuruDelete).click(function(){
					if (!$(noenteryLuruDelete).linkbutton('options').disabled) {
						if(bianjizhuangtai==0){
							var index = $(noenteryLuruDatagrid).datagrid('getRowIndex', $(noenteryLuruDatagrid).datagrid('getSelected'));
							$(noenteryLuruDatagrid).datagrid('deleteRow',index);
						}
					}
				});
			}
		}).dialog('open');
	};
	
	noentery_entry_save=function(){
		var rowsStr="";
		if (!$("#apmgt_noentery_noenteryLuru_form").form('validate')) {
			return;
		}
		var rows = $('#noenteryLuru_datagrid').datagrid('getRows');
		for ( var r = 0; r < rows.length; r++) {
			if (!$('#noenteryLuru_datagrid').datagrid('validateRow', r)) {
				return;
			}
		}
		$.messager.confirm('确认', '您确定录入吗？', function(r) {
			var invoiceDate="";
			var invoiceNo="";
			if(rows.length>0){
				invoiceDate=rows[0].invoiceDate;
				for(var i=0;i<rows.length;i++){
					var index = $(noenteryLuruDatagrid).datagrid('getRowIndex',rows[i]);
					var ed = $(noenteryLuruDatagrid).datagrid('getEditor', {index:index,field:'invoiceNo'});
					if(invoiceNo!=""){
						invoiceNo+=",";
					}
					invoiceNo+=$(ed.target).val();
				}
			}
			var formData = $.serializeObject($("#apmgt_noentery_noenteryLuru_form"));
			$.post("/cs/bsp/advanceEntryAction!saveWeiluru.action?invoiceNo="+invoiceNo+"&invoiceDate="+invoiceDate, formData, function(result) {
				var result = JSON.parse(result);
				if(result.success){
					var getrow = $("#dtgd").datagrid('getSelections', $("#dtgd").datagrid('getSelected'));
					for ( var i = 0; i < getrow.length; i++) {
						var row = $("#dtgd").datagrid('getRowIndex',getrow[i]);
						$("#dtgd").datagrid('deleteRow', row);
					}
					$(btnentry).linkbutton('disable');
				}
				$.messager.show({
					title : '提示',
					msg : result.msg
				}); 
				$("#noentery_dialog").dialog('close');
			});
			
			
		});
		
	};
	noentery_entry_close=function(){
		$("#noentery_dialog").dialog('close');
	};
	
	window.apmgt.noentery.gaibian=function(){
		var voucherType=$("#voucherType").val();
		if(voucherType==2){
			$(noenteryLuruAdd).linkbutton('disable');
			$(noenteryLuruDelete).linkbutton('disable');
			$(noenteryLuruJieshu).linkbutton('disable');
			$(noenteryLuruDatagrid).datagrid('loadData',{total:0,rows:[]});
		}else{
			$(noenteryLuruAdd).linkbutton('enable');
			$(noenteryLuruDelete).linkbutton('enable');
			$(noenteryLuruJieshu).linkbutton('disable');
			var getrow1 = $(dtgd).datagrid('getSelections', $(dtgd).datagrid('getSelected'));
			var date1=new Date();
			var d1=date1.getMonth()+1;
			date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
			
			if (!$(noenteryLuruAdd).linkbutton('options').disabled) {
				$(noenteryLuruDatagrid).datagrid('insertRow',{
					index: 0,	// 索引从0开始
					row: {
						invoiceNo: '',
						invoiceMoney: getrow1[0].payAmount,
						invoiceDate: date1
					}
				});
			}
			$(noenteryLuruDatagrid).datagrid('beginEdit', 0);
		}
		
		
	};

	window.apmgt.noentery.bindToolBarEvent = function() {
		$(btnentry).click(function() {
			if (!$(btnentry).linkbutton('options').disabled) {
				window.apmgt.noentery.luru($(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected')));
			}
		});
		
		$('#btn_query').click(function(){
			window.apmgt.noentery.Search();
		});
		
		$('#btnSearch').click(function(){
			window.apmgt.noentery.SearchZhu();
		});
	};
	window.apmgt.noentery.SearchZhu=function(){
		if (!$("#Noentry_form").form('validate')) {
			return;
		}
		var formData = $.serializeObject($("#Noentry_form"));
		$.post("/cs/apmgt/payableViewAction!findZhanzhiSearch.action?status=1", formData, function(result) {
			var result = JSON.parse(result);
			if (result.success) {
				$('#dtgd').datagrid('loadData',result.obj);
			}else{
				$('#dtgd').datagrid('loadData',{total:0,rows:[]});
				$.messager.show({
					title : '提示',
					msg : '未找到供应商未录入凭证信息',
					showType : 'slide'
				});
			}
			$('#noenterySearch_dialog').dialog('close');
		});
	};
	
	window.apmgt.noentery.Search=function(){
		$('<div id="noenterySearch_dialog">').dialog({
			title : "查询凭证录入",
			href : $.util.resolveClientUrl('/cs/page/ap-mgt/Noentry/search.jsp'),
			width : 350,
			height : 170,
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
				$("#tradeDateQi").datebox('setValue',date);
				$("#tradeDateShi").datebox('setValue',date1);
				
				$("#Noentry_search").click(function() {
					if (!$("#apmgt_Noentry_search_form").form('validate')) {
						return;
					}
					var formData = $.serializeObject($("#apmgt_Noentry_search_form"));
					$.post("/cs/apmgt/payableViewAction!findZhanzhiSearch.action?status=1", formData, function(result) {
						var result = JSON.parse(result);
						if (result.success) {
							$('#dtgd').datagrid('loadData',result.obj);
						}else{
							$('#dtgd').datagrid('loadData',{total:0,rows:[]});
							$.messager.show({
								title : '提示',
								msg : '未找到供应商未录入凭证信息',
								showType : 'slide'
							});
						}
						$('#noenterySearch_dialog').dialog('close');
					});
				});
				
				$("#Noentry_close").click(function() {
					$('#noenterySearch_dialog').dialog('close');
				});
			}
		}).dialog('open');
	};

	/**
	 * 验证规则
	 */
	$.extend($.fn.validatebox.defaults.rules, {
		realAmount : {
			validator : function(value) {
				var getrow = $(noenteryLuruDatagrid).datagrid('getRows');
				var money1=$("#amountMoney").numberbox('getValue');
				var money=0;
				for(var i=0;i<getrow.length;i++){
					var index = $(noenteryLuruDatagrid).datagrid('getRowIndex',getrow[i]);
					var ed = $(noenteryLuruDatagrid).datagrid('getEditor', {index:index,field:'invoiceMoney'});
					money = money + parseInt($(ed.target).numberbox('getValue'));
				}
				return money == money1;
			},
			message : '发票金额必须等于本身金额'
		},
		invoiceNo:{
			validator : function(value) {
				var b=true;
				var bool=true;
				var getrow = $(noenteryLuruDatagrid).datagrid('getRows');
				for(var o=0;o<getrow.length;o++){
					if(b){
						var index = $(noenteryLuruDatagrid).datagrid('getRowIndex',getrow[o]);
						var ed = $(noenteryLuruDatagrid).datagrid('getEditor', {index:index,field:'invoiceNo'});
						var result = eval("(" + $.ajax( {
							url : "/cs/bsp/advanceEntryAction!findBianli.action",
							dataType : "json/xml/html",
							data : {
								"invoiceNo" : $(ed.target).val()
							},
							async : false,
							cache : false,
							type : "post"
							}).responseText + ")");
						if(result.success==true){
							b=false;
							break;
						}
					}
					
				}
				
				if(b){
					for(var i=0;i<getrow.length;i++){
						var index = $(noenteryLuruDatagrid).datagrid('getRowIndex',getrow[i]);
						var ed1 = $(noenteryLuruDatagrid).datagrid('getEditor', {index:index,field:'invoiceNo'});
						for(var j=0;j<getrow.length;j++){
							var index1 = $(noenteryLuruDatagrid).datagrid('getRowIndex',getrow[j]);
							var ed2 = $(noenteryLuruDatagrid).datagrid('getEditor', {index:index1,field:'invoiceNo'});
							if(i!=j&&$(ed1.target).val()==$(ed2.target).val()){
								bool=false;
								break;
							}
						}
					}
				}
				
				return bool&&b;
			},
			message : '发票号不能重复'
		}
	});

	$(function() {
		var date=new Date();
		var d=date.getMonth()+1;
		var date =  date.getFullYear()+'-'+d+'-01';
		var date1=new Date();
		var d1=date1.getMonth()+1;
		var date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
		$("#tradeDateQi_Zhu").datebox('setValue',date);
		$("#tradeDateShi_Zhu").datebox('setValue',date1);
		
		window.apmgt.noentery.loadPageBar();
		window.apmgt.noentery.bindToolBarEvent();
		window.apmgt.noentery.disableToobar();
	});

})(jQuery);