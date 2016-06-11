(function($) {

	$.util.namespace("bsp.returnHistory");
	$.util.namespace("bsp.returnHistory.edit");

	var bspSalesInformationDg = '#bsp_SalesInformation_dg', btnSee = '#btnSee',btnEdit='#btnEdit';

	/**
	 * datagrid 设置分页栏
	 */
	window.bsp.returnHistory.loadPageBar = function() {
		$(bspSalesInformationDg).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ],
		});
	};
	
	window.bsp.returnHistory.load=function(){
		$(btnEdit).linkbutton('disable');
		$(btnSee).linkbutton('disable');
	}
	
	window.bsp.returnHistory.onSelect=function(index, row){
		if (row.status == 1) {
			$(btnEdit).linkbutton('enable');
		}
		$(btnSee).linkbutton('enable');
	}
	
	window.bsp.returnHistory.statusFormater=function(value, row, index) {
		if(value==1){
			return '未验证';
		}else if(value==2){
			return '已验证';
		}
	}
	
	window.bsp.returnHistory.statusStyler=function(value,row,index){
		if(value==1){
			return 'background-color:red;color:#fff;';
		}else{
			return 'background-color:green;color:#fff;';
		}
	}
	
	window.bsp.returnHistory.amountFormatter=function(value, row, index) {
		if(value!=null){
			return $.formatCurrencySign(value, row.currencyType);
		}else{
			return null;
		}
		
	}
	
	window.bsp.returnHistory.edit.open=function(index,btn){
		var value="";
		if(btn==1){
			value="编辑历史退换信息";
		}else{
			value="查看历史退换信息";
		}
		$('<div id="returnEdit">').dialog({
			title : value,
			href : $.util.resolveClientUrl('/cs/page/bsp/returnHistory/returnHistoryEdit.jsp?btn='+btn),
			width : 580,
			height : 300,
			top : 5,
			iconCls : 'bicon-asterisk',
			resizable : false,
			modal : true,
			closed : true,
			cache : false,
			onClose : function() {
				$(this).dialog('destroy');
				$(bspSalesInformationDg).datagrid('clearSelections');
				window.bsp.returnHistory.load();
			},
			onLoad : function() {
				window.bsp.returnHistory.edit.returnHistoryEdit(index);
			}
		}).dialog('open');
	}
	
	window.bsp.returnHistory.edit.returnHistoryEdit=function(index){
		$('#bsp_returnHistory_form').form('load', $(bspSalesInformationDg).datagrid('getRows')[index]);
		$('#returnHistoryRdit_save').click(function() {
			window.bsp.returnHistory.edit.save(index);
		});
		$('#returnHistoryRdit_remoud').click(function() {
			$(bspSalesInformationDg).datagrid('clearSelections');
			window.bsp.returnHistory.load();
			$('#returnEdit').dialog('destroy');
			$('#returnEdit').dialog('close');
		});
		$("#currencyType").combobox({    
		    onSelect:function(rec){
	    		var result = eval('(' + $.ajax( {
					url : '/cs/bset/sertAction!findCurrency.action',
					 data : {
						'currency' : rec.code,
						'money' : $('#costScny').numberbox('getValue')
					},
					dataType : 'json/xml/html',
					async : false,
					cache : false,
					type : 'post'
				}).responseText + ')');
				var json=JSON.parse(result.obj);
				$("#salesFcny").numberbox('setValue',json);
	    	}
		}); 
	}
	
	window.bsp.returnHistory.edit.save=function(index){
		if (!$('#bsp_returnHistory_form').form('validate')) {
			return;
		}
		var formData = $.serializeObject($("#bsp_returnHistory_form"));
		formData.id = $(bspSalesInformationDg).datagrid('getRows')[index].id;
		formData.paymentMethodName=$("#paymentMethodCode").combobox('getText');
		formData.receivablesMethodName=$("#receivablesMethodCode").combobox('getText');
		formData.suppliersName=$("#suppliersCode").combogrid('getText');
		$.post("/cs/bsp/salesInformationAction!update.action", formData, function(result) {
			var result = JSON.parse(result);
			if (result.success) {
				$('#returnEdit').dialog('close');
				$(bspSalesInformationDg).datagrid('load');
				$(bspSalesInformationDg).datagrid('clearSelections');
				window.bsp.returnHistory.load();
			}
			$.messager.show({
				title : '提示',
				msg : result.msg,
				showType : 'slide'
			});
		});
	}
	//结算价=SCNY*(1-C%)
	window.bsp.returnHistory.edit.costScny=function(newValue,oldValue){
		var currency=$("#currencyType").combobox('getValue');
		if(currency!='CNY'){
			var result = eval('(' + $.ajax( {
				url : '/cs/bset/sertAction!findCurrency.action',
				 data : {
					'currency' : currency,
					'money' : newValue
				},
				dataType : 'json/xml/html',
				async : false,
				cache : false,
				type : 'post'
			}).responseText + ')');
			var json=JSON.parse(result.obj);
			$("#salesFcny").numberbox('setValue',json);
		}else{
			$("#salesFcny").numberbox('setValue',newValue);
		}
		$("#costPrice").numberbox('setValue',newValue*1*(1-$("#costC").numberbox('getValue')/100));
	}
	
	window.bsp.returnHistory.edit.costC=function(newValue,oldValue){
		$("#costPrice").numberbox('setValue',$("#costScny").numberbox('getValue')*1*(1-newValue/100));
	}
	
	window.bsp.returnHistory.edit.costPrice=function(newValue,oldValue){
		$("#costC").numberbox('setValue',(1-newValue*1/$("#costScny").numberbox('getValue')*1)*100);
		$("#costTotal").numberbox('setValue',newValue*1+$("#costTax").numberbox('getValue')*1);
	}
	
	window.bsp.returnHistory.edit.costTax=function(newValue,oldValue){
		$("#costTotal").numberbox('setValue',newValue*1+$("#costPrice").numberbox('getValue')*1);
	}
	
	//销售价=FCNY*(1-让利%)
	window.bsp.returnHistory.edit.salesFcny=function(newValue,oldValue){
		$("#salesPrice").numberbox('setValue',newValue*(1-$("#salesNone").numberbox('getValue')/100));
	}
	window.bsp.returnHistory.edit.salesNone=function(newValue,oldValue){
		$("#salesPrice").numberbox('setValue',$("#salesFcny").numberbox('getValue')*(1-newValue/100));
	}
	//销售总价=销售价+销售税+服务费
	window.bsp.returnHistory.edit.salesPrice=function(newValue,oldValue){
		$("#salesNone").numberbox('setValue',(1-newValue*1/$("#salesFcny").numberbox('getValue')*1)*100);
		$("#salesTotal").numberbox('setValue',newValue*1+$("#salesTax").numberbox('getValue')*1+$("#salesServicePrice").numberbox('getValue')*1);
	}
	//销售总价=销售价+销售税+服务费
	window.bsp.returnHistory.edit.salesTax=function(newValue,oldValue){
		$("#salesTotal").numberbox('setValue',newValue*1+$("#salesPrice").numberbox('getValue')*1+$("#salesServicePrice").numberbox('getValue')*1);
	}
	//销售总价=销售价+销售税+服务费
	window.bsp.returnHistory.edit.salesServicePrice=function(newValue,oldValue){
		$("#salesTotal").numberbox('setValue',newValue*1+$("#salesTax").numberbox('getValue')*1+$("#salesPrice").numberbox('getValue')*1);
	}
	
	
	window.bsp.returnHistory.bindButtonEvent = function() {
		$(btnEdit).click(function(){
			if (!$(btnEdit).linkbutton('options').disabled) {
				var index = $(bspSalesInformationDg).datagrid('getRowIndex', $(bspSalesInformationDg).datagrid('getSelected'));
				window.bsp.returnHistory.edit.open(index,1);
			}
		});
		
		$(btnSee).click(function(){
			if (!$(btnSee).linkbutton('options').disabled) {
				var index = $(bspSalesInformationDg).datagrid('getRowIndex', $(bspSalesInformationDg).datagrid('getSelected'));
				window.bsp.returnHistory.edit.open(index,2);
//				$('input[name=costScny]').attr("disabled", true);
			}
		});
		$("#btSearch").click(function(){
			var url = "/cs/armgt/receivable!datagrid.action?abc=1";
			if ($("#tradeDateQi").datebox('getValue') != ""&& $("#tradeDateQi").datebox('getValue') != undefined) {
				url += "&tradeDateQi="+ $("#tradeDateQi").combobox('getValue');
			}
			$(dtgd).datagrid({
				url : url
			});
		});
	};
	

	$(function() {
		window.bsp.returnHistory.load();
		window.bsp.returnHistory.loadPageBar();
		window.bsp.returnHistory.bindButtonEvent();
	});

})(jQuery);