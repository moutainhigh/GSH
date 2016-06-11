(function($) {

	$.util.namespace("bsp.ticketbook");

	var bspTicketbookDg = '#bsp_ticketbook_dg', newbtn = '#newticketbook';

	/**
	 * datagrid 设置分页栏
	 */
	window.bsp.ticketbook.loadPageBar = function() {
		$(bspTicketbookDg).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ],
		});
	};
	
	/**
	 * 新增BSP票本方法
	 */
	window.bsp.ticketbook.openNewDialog = function() {
		$('<div id="bsp_ticketbook_newtb_dialog">').dialog({
			title : "新增BSP票本",
			href : $.util.resolveClientUrl('/cs/page/bsp/ticketbook/newtb.jsp'),
			width : 300,
			height : 210,
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

			}
		}).dialog('open');
	};
	
	window.bsp.ticketbook.search=function(){
		var formData = $.serializeObject($("#bspform"));
		$.post("/cs/bsp/bspTicketAction!findAllSearch.action", formData, function(result) {
			var result = JSON.parse(result);
			if (result.success) {
				$("#bsp_ticketbook_dg").datagrid('loadData',result.obj);
			}else{
				$("#bsp_ticketbook_dg").datagrid('loadData',{total:0,rows:[]});
				$.messager.show({
					title : '提示',
					msg : '未找到任何信息',
					showType : 'slide'
				});
			}
			
		});
	};
	
	window.bsp.ticketbook.bindButtonEvent = function() {
		$(newbtn).click(function() {//新增BSP票本，单击事件
			window.bsp.ticketbook.openNewDialog();
		});
		
		$("#btSearch").click(function(){
			window.bsp.ticketbook.search();
		});
	};
	
	/**
	 * 类型处理
	 */
	window.bsp.ticketbook.accountTypeFormater=function(value,row,index){
		if (value == 1){
			return '国际';
		}else if(value == 2){
			return '国内';
		}
	};

	$(function() {
		window.bsp.ticketbook.loadPageBar();
		window.bsp.ticketbook.bindButtonEvent();
	});

})(jQuery);