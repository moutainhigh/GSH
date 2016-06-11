(function($) {
	$.util.namespace("bsp.ticketbook.newtb");
	var bspTicketbookNewtbForm = "#bsp_ticketbook_newtb_form",bspSave = "#bsp_save",bspRemoud = "#bsp_remoud";
	
	/**
	 * 验证规则
	 */
	$.extend($.fn.validatebox.defaults.rules, {
		tickedDaxiao:{
			validator : function(value){
				if($("#bsp_ticketbook_newtb_form_bspTerminationBank").val()>$("#bsp_ticketbook_newtb_form_bspStartBank").val()){
					return true;
				}
			},
			message : '终止票号必须大于起始票号'
		},
		ticked:{
			validator : function(value)  {
				$.post('/cs/bsp/bspTicketAction!tickedWeiyi.action',
					{
						"piaohao" : value
					},
					function(data) {
						var json = eval('(' + data + ')'); 
						abc=json.obj;
					});
				if(abc==1){
					return true;
				}
			},
			message : '票库已存在该票号段，请查看票库在进行输入'
		}
	});
	
	$(function() {
		var abc=1;
		$(bspSave).click(function(){
			if (!$(bspTicketbookNewtbForm).form('validate')) {
				return;
			}
			if($("#bsp_ticketbook_newtb_form_bspTerminationBank").val()<$("#bsp_ticketbook_newtb_form_bspStartBank").val()){
				$.messager.show({
					title : '操作提示',
					msg : '终止票号必须大于起始票号',
					showType : 'slide'
				});
				return;
			}
			var bspNumber=$("#bsp_ticketbook_newtb_form_bspTerminationBank").val()-$("#bsp_ticketbook_newtb_form_bspStartBank").val()+1;
			$("#bsp_ticketbook_newtb_form_bspNumber").val(bspNumber);
			$("#bsp_ticketbook_newtb_form_bspAllowance").val(bspNumber);

			var formData = $.serializeObject($(bspTicketbookNewtbForm));
			$.post("/cs/bsp/bspTicketAction!save.action", formData, function(result) {
				var result = JSON.parse(result);
				if (result.success) {
					$("#bsp_ticketbook_dg").datagrid('insertRow', {
						index : 0,
						row : result.obj
					});
					$("#bsp_ticketbook_newtb_dialog").dialog('close');
				}
				$.messager.show({
					title : '提示',
					msg : result.msg,
					showType : 'slide'
				});
			});
		});
		$(bspRemoud).click(function(){
			$("#bsp_ticketbook_newtb_dialog").dialog('close');
		});
	});

})(jQuery);

