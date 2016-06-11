(function($) {

	$.util.namespace("armgt.receivableRemind");
	

	
	/**
	 * datagrid 设置分页栏
	 */
	window.armgt.receivableRemind.loadPageBar = function() {
		$("#dtgd").datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ],
		});
	};


	window.armgt.receivableRemind.amountFormatter = function(value, row, index) {
		return $.formatCurrencySign(value, currency_local);
	};
	
	/**
	 * 按钮事件绑定
	 */
	window.armgt.receivableRemind.bindButtonEvent = function() {
		
	};
	
	window.armgt.receivableRemind.styler=function(index,row){
		var date1=new Date();
		var d1=date1.getMonth()+1;
		var date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
		if(row.deadline<date1){
			return 'background-color:red';  
		}
	};
	
	$(function() {
		$('#receivableRemind_Search').click(function(){
			var formData = $.serializeObject($("#receivableRemind_from"));
			$.post("/cs/armgt/receivableRemind!findAllSearch.action", formData, function(result) {
				var result = JSON.parse(result);
				if (result.success) {
					$("#dtgd").datagrid('loadData',result.obj);
				}else{
					$("#dtgd").datagrid('loadData',{total:0,rows:[]});
					$.messager.show({
						title : '提示',
						msg : '未找到应收账款信息',
						showType : 'slide'
					});
				}
			});
		});
		window.armgt.receivableRemind.loadPageBar();
		window.armgt.receivableRemind.bindButtonEvent();
	});

})(jQuery);