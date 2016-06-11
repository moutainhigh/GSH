(function($) {

	$.util.namespace("apmgt.advance");

	var dtgd = '#dtgd',btnentry = '#btn_entry';
	var advanceLuruDatagrid='#advanceLuru_datagrid',advanceLuruAdd='#advanceLuruAdd',advanceLuruDelete='#advanceLuruDelete';

	window.apmgt.advance.loadPageBar = function() {
		$(dtgd).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
		});
	};

	window.apmgt.advance.optionsFormatter = function(value, row, index) {
		var options;
		if (btnentry == "enable")
			options += ' <i class="bicon-pencil" onclick="window.apmgt.advance.submit($.getDatagridRowIndex(this));"></i>';
		return options;
	};

	window.apmgt.advance.statusFormatter = function(value, row, index) {
		if (value == 5) {
			return "待确认";
		}
	};

	window.apmgt.advance.amountFormatter = function(value, row, index) {
		if(row.currencyType==undefined){
			return $.formatCurrencySign(value, "CNY");
		}
		return $.formatCurrencySign(value, row.currencyType);
	};

	window.apmgt.advance.onLoadSuccess = function(data) {
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
		$('#sum_footer').html(sumfootertable);
	};

	window.apmgt.advance.disableToobar = function() {
		$(btnentry).linkbutton('disable');
	};

	window.apmgt.advance.onSelect = function(index, row) {
		window.apmgt.advance.disableToobar();
		if (row.productStatus == 2) {
			$(btnentry).linkbutton('enable');
		}
	};
	
	window.apmgt.advance.paymentMethod=function(value, row, index){
		if(value==1){
			return '现金';
		}else if(value==2){
			return '信用卡';
		}else if(value==3){
			return '支票';
		}else if(value==4){
			return '转账汇款/5内转';
		}
	};
	
	
	var wtoChangeflog = false;
	/**
	 * 修改
	 */
	window.apmgt.advance.onClickRow = function(rowIndex, rowData) {
		wtoChangeflog = false;
		$(advanceLuruDatagrid).datagrid('beginEdit', rowIndex);
	};
	
	invoiceMoneyOnChange=function(){

	};


	/**
	 * 凭证录入
	 * 
	 * @param index
	 */
	window.apmgt.advance.luru = function(index) {
		$('<div id="advance_dlgPay">').dialog({
			title : "凭证录入",
			href : $.util.resolveClientUrl('/cs/page/ap-mgt/advance/advanceLuru.jsp'),
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
					advance_entry_save();
				}
			}, {
				text : '<i class="fa fa-times"></i> 放弃',
				handler : function() {
					advance_entry_close();
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				var getrow1 = $(dtgd).datagrid('getSelections', $(dtgd).datagrid('getSelected'));
				$("#settlementNo").val(getrow1[0].settlementNo);
				$("#transactionOp").val(getrow1[0].supplierNo);
				$("#supmid").val(getrow1[0].supmid);
				$("#pablid").val(getrow1[0].pablid);
				$("#amountMoney").numberbox({prefix:$.convertCurrencyUnit(getrow1[0].currencyType)});
				$("#amountMoney").numberbox('setValue',getrow1[0].payAmount);
				var date1=new Date();
				var d1=date1.getMonth()+1;
				date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
				$("#transactionDate").datebox('setValue',date1);
				
				if (!$(advanceLuruAdd).linkbutton('options').disabled) {
					$(advanceLuruDatagrid).datagrid('insertRow',{
						index: 0,	// 索引从0开始
						row: {
							invoiceNo: '',
							invoiceMoney: getrow1[0].payAmount,
							invoiceDate: date1,
							currencyType : getrow1[0].currencyType
						}
					});
				}
				$(advanceLuruDatagrid).datagrid('beginEdit', 0);
				
				$(advanceLuruAdd).click(function(){
					var rows = $('#advanceLuru_datagrid').datagrid('getRows');
					for ( var r = 0; r < rows.length; r++) {
						if (!$('#advanceLuru_datagrid').datagrid('validateRow', r)) {
							return;
						}
					}
					if (!$(advanceLuruAdd).linkbutton('options').disabled) {
						$(advanceLuruDatagrid).datagrid('insertRow',{
							index: 0,	// 索引从0开始
							row: {
								invoiceNo: '',
								invoiceMoney: getrow1[0].payAmount,
								invoiceDate: date1,
								currencyType : getrow1[0].currencyType
							}
						});
					}
					$(advanceLuruDatagrid).datagrid('beginEdit', 0);
				});
				$(advanceLuruDelete).click(function(){
					if (!$(advanceLuruDelete).linkbutton('options').disabled) {
						var index = $(advanceLuruDatagrid).datagrid('getRowIndex', $(advanceLuruDatagrid).datagrid('getSelected'));
						$(advanceLuruDatagrid).datagrid('deleteRow',index);
					}
				});
			}
		}).dialog('open');
	};
	
	advance_entry_save=function(){
		var rowsStr="";
		if (!$("#apmgt_advance_entry_form").form('validate')) {
			return;
		}
		var rows = $('#advanceLuru_datagrid').datagrid('getRows');
		for ( var r = 0; r < rows.length; r++) {
			if (!$('#advanceLuru_datagrid').datagrid('validateRow', r)) {
				return;
			}
		}
		$.messager.confirm('确认', '您确定录入吗？', function(r) {
			var invoiceNo="";
			var invoiceDate="";
			if(rows.length>0){
				var invoiceNo="";
				invoiceDate=rows[0].invoiceDate;
				for(var i=0;i<rows.length;i++){
					if(invoiceNo!=""){
						invoiceNo+",";
					}
					var index = $('#advanceLuru_datagrid').datagrid('getRowIndex',rows[i]);
					var ed = $('#advanceLuru_datagrid').datagrid('getEditor', {index:index,field:'invoiceNo'});
					if(invoiceNo!=""){
						invoiceNo+=",";
					}
					invoiceNo+=$(ed.target).val();
					
				}
			}
			var formData = $.serializeObject($("#apmgt_advance_entry_form"));
			$.post("/cs/bsp/advanceEntryAction!save.action?invoiceNo="+invoiceNo+"&invoiceDate="+invoiceDate, formData, function(result) {
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
				$("#advance_dlgPay").dialog('close');
			});
			
			
		});
		
	};
	advance_entry_close=function(){
		$("#advance_dlgPay").dialog('close');
	};
	
	window.apmgt.advance.gaibian=function(){
		var voucherType=$("#voucherType").val();
		if(voucherType==2){
			$(advanceLuruAdd).linkbutton('disable');
			$(advanceLuruDelete).linkbutton('disable');
			$(advanceLuruDatagrid).datagrid('loadData',{total:0,rows:[]});
		}else{
			$(advanceLuruAdd).linkbutton('enable');
			$(advanceLuruDelete).linkbutton('enable');
			var getrow1 = $(dtgd).datagrid('getSelections', $(dtgd).datagrid('getSelected'));
			var date1=new Date();
			var d1=date1.getMonth()+1;
			date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
			if (!$(advanceLuruAdd).linkbutton('options').disabled) {
				$(advanceLuruDatagrid).datagrid('insertRow',{
					index: 0,	// 索引从0开始
					row: {
						invoiceNo: '',
						invoiceMoney: getrow1[0].payAmount,
						invoiceDate: date1
					}
				});
			}
			$(advanceLuruDatagrid).datagrid('beginEdit', 0);
		}
		
		
	};
	
	window.apmgt.advance.search=function(){
		$('<div id="apmgt_advancet_search">').dialog({
			title : "查询供应商暂支款",
			href : $.util.resolveClientUrl('/cs/page/ap-mgt/advance/search.jsp'),
			width : 350,
			height : 230,
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
				var date=new Date();
				var d=date.getMonth()+1;
				var date =  date.getFullYear()+'-'+d+'-01';
				var date1=new Date();
				var d1=date1.getMonth()+1;
				var date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
				$("#tradeDateQi").datebox('setValue',date);
				$("#tradeDateShi").datebox('setValue',date1);
				$("#advance_search").click(function() {
					if (!$("#apmgt_advance_search_form").form('validate')) {
						return;
					}
					var formData = $.serializeObject($("#apmgt_advance_search_form"));
					$.post("/cs/apmgt/payableViewAction!findZhanzhiSearch.action?status=2", formData, function(result) {
						var result = JSON.parse(result);
						if (result.success) {
							$("#dtgd").datagrid('loadData',result.obj);
						}else{
							$("#dtgd").datagrid('loadData',{total:0,rows:[]});
							$.messager.show({
								title : '提示',
								msg : '未找到供应商暂支款信息',
								showType : 'slide'
							});
						}
						$('#apmgt_advancet_search').dialog('close');
					});
				});
				
				$("#advance_close").click(function() {
					$('#apmgt_advancet_search').dialog('close');
				});
			}
		}).dialog('open');
	};

	window.apmgt.advance.bindToolBarEvent = function() {
		$(btnentry).click(function() {
			if (!$(btnentry).linkbutton('options').disabled) {
				window.apmgt.advance.luru($(dtgd).datagrid('getRowIndex', $(dtgd).datagrid('getSelected')));
			}
		});
		
		$('#btn_query').click(function(){
			window.apmgt.advance.search();
		});
		
		$('#btnSearch').click(function(){
			window.apmgt.advance.searchZhu();
		});
	};
	
	window.apmgt.advance.searchZhu=function(){
		var formData = $.serializeObject($("#advance_form"));
		$.post("/cs/apmgt/payableViewAction!findZhanzhiSearch.action?status=2", formData, function(result) {
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

	/**
	 * 验证规则
	 */
	$.extend($.fn.validatebox.defaults.rules, {
		realAmount : {
			validator : function(value) {
				var getrow = $(advanceLuruDatagrid).datagrid('getRows');
				var money1=$("#amountMoney").numberbox('getValue');
				var money=0;
				for(var i=0;i<getrow.length;i++){
					var index = $(advanceLuruDatagrid).datagrid('getRowIndex',getrow[i]);
					var ed = $(advanceLuruDatagrid).datagrid('getEditor', {index:index,field:'invoiceMoney'});
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
				var getrow = $(advanceLuruDatagrid).datagrid('getRows');
				for(var o=0;o<getrow.length;o++){
					if(b){
						var index = $(advanceLuruDatagrid).datagrid('getRowIndex',getrow[o]);
						var ed = $(advanceLuruDatagrid).datagrid('getEditor', {index:index,field:'invoiceNo'});
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
						var index = $(advanceLuruDatagrid).datagrid('getRowIndex',getrow[i]);
						var ed1 = $(advanceLuruDatagrid).datagrid('getEditor', {index:index,field:'invoiceNo'});
						for(var j=0;j<getrow.length;j++){
							var index1 = $(advanceLuruDatagrid).datagrid('getRowIndex',getrow[j]);
							var ed2 = $(advanceLuruDatagrid).datagrid('getEditor', {index:index1,field:'invoiceNo'});
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
		window.apmgt.advance.loadPageBar();
		window.apmgt.advance.bindToolBarEvent();
		window.apmgt.advance.disableToobar();
	});

})(jQuery);