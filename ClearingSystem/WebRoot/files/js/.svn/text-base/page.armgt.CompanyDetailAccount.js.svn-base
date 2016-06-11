(function($) {

	$.util.namespace("armgt.cdac");

	var dtgd = '#dtgd';

	window.armgt.cdac.amountFormatter = function(value, row, index) {
		return $.formatCurrencySign(value, currency_local);
	};

	window.armgt.cdac.tradeWayFormatter = function(value, row, index) {
		switch (value) {
		case 1:
			return "现金";
		case 2:
			return "信用卡";
		case 3:
			return "支票";
		case 4:
			return "转账/汇款";
		case 5:
			return "内转";
		default:
			return "";
		}
	};
	
	window.armgt.cdac.status = function(value, row, index){
		if(row.id==0){
			return;
		}
		if (row.status1 == 1) {
			return "未复核";
		} else if (row.status1 == 2) {
			return "已复核";
		}
	};
	
	/**
	 * 状态的样式
	 */
	window.armgt.cdac.statusStyler = function(value, row, index){
		if(row.transactionID==0){
			return;
		}
		if (row.status1 == 1) {
			return 'background-color:blue;color:#fff;';
		} else if (row.status1 == 2) {
			return 'background-color:green;color:#fff;';
		}
	};

	window.armgt.cdac.onClickRow = function(rowIndex, rowData) {
		var rows = $(dtgd).datagrid('getSelections');
		$('#btnLeaf').linkbutton("disable");
		for ( var i = 0; i < rows.length; i++) {
			if (rows[i].settlementNo != rows[0].settlementNo||rows[i].status1!=2) {
				return;
			}
		}
		if (rows.length > 0)
			$('#btnLeaf').linkbutton("enable");
	};
	
	window.armgt.cdac.notice=function(){
		var rows = $(dtgd).datagrid('getSelections');
		$('<div id="dlgRcpt">').dialog({
			title : "收据",
			href : $.util.resolveClientUrl('/cs/page/ar-mgt/cdac/receipt.jsp'),
			width : 730,
			height : 500,
			top : 5,
			iconCls : 'bicon-file',
			resizable : false,
			modal : true,
			closed : true,
			cache : false,
			toolbar : [ {
				text : '打印',
				iconCls : 'bicon-print',
				handler : function() {
					$("#contents").printArea();//jquery拓展打印方法
					$.messager.show({
						title : '提示',
						msg : '打印成功',
						showType : 'slide'
					});
					$("#dlgRcpt").dialog('close');
				}
			}, {
				text : 'Email',
				iconCls : 'bicon-envelope',
				handler : function() {
					//发送邮件
					$('#emailWin').window({
						title : "邮件",
						href:"/cs/page/ar-mgt/cdac/email.jsp",
						width:380,
						height:300,
						closed : true,
						collapsible : false,
						minimizable : false,
						maximizable : false,
					}).window("open");	
				}
			}, {
				text : '下载',
				iconCls : 'bicon-download-alt',
				handler : function() {
				}
			} ],
			buttons : [ {
				text : '关闭',
				iconCls : 'bicon-remove',
				handler : function() {
					$('#dlgRcpt').dialog('close');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				var rows=$(dtgd).datagrid('getSelections');
				var date2=new Date();
				var d2=date2.getMonth()+1;
				date2 = date2.getFullYear()+'-'+d2+'-'+date2.getDate();
				$("#shoujianren").html($("#account_accountToreview_form_addressee").val());//收件人
				$("#zhifuhao").html(rows[0].voucherNo);//支付号（凭证号）
				$("#dateChuju").html(date2);//当前日期
				var banace=0;
				var jiaoyifangshi="";
				for(var i =0;i<rows.length;i++){
					banace=banace*1+rows[i].incomeAmount*1;
				}
				switch (rows[0].paymentMethod) {
				case 1:
					jiaoyifangshi= "现金";
				case 2:
					jiaoyifangshi=  "信用卡";
				case 3:
					jiaoyifangshi=  "支票";
				case 4:
					jiaoyifangshi=  "转账/汇款";
				case 5:
					jiaoyifangshi=  "内转";
				}
				$("#xiaoxieBanace").html(banace);
				$("#daxieBanace").html(window.armgt.cdac.DX(banace));//jquery.easyui.min封装的方法
				var table="<tr><th>文档号码</th><th>签发日</th><th>金额/人民币</th><th>已收金额</th></tr>";
				for(var i=0;i<rows.length;i++){
					table+="<tr><td>"+rows[i].noticeNo+"</td><td>"+date2+"</td><td>"+rows[i].incomeAmount+"</td><td>"+rows[i].incomeAmount+"</td></tr>";
				}
				table+="<tr><td colspan=\"4\">支付总额："+banace+"</td></tr><tr><td colspan=\"4\">支付方式："+jiaoyifangshi+"</td></tr><tr><td colspan=\"4\">收款日期："+rows[0].tradeDate+"</td></tr>";
				$("#jiaoyixinxi").html(table)
			}
			
		}).dialog('open');
		
	}
	
	window.armgt.cdac.DX=function(n) {
	    if (!/^(0|[1-9]\d*)(\.\d+)?$/.test(n))
	        return "数据非法";
	    var unit = "千百拾亿千百拾万千百拾元角分", str = "";
	        n += "00";
	    var p = n.indexOf('.');
	    if (p >= 0)
	        n = n.substring(0, p) + n.substr(p+1, 2);
	        unit = unit.substr(unit.length - n.length);
	    for (var i=0; i < n.length; i++)
	        str += '零壹贰叁肆伍陆柒捌玖'.charAt(n.charAt(i)) + unit.charAt(i);
	    return str.replace(/零(千|百|拾|角)/g, "零").replace(/(零)+/g, "零").replace(/零(万|亿|元)/g, "$1").replace(/(亿)万|壹(拾)/g, "$1$2").replace(/^元零?|零分/g, "").replace(/元$/g, "元整");
	}
	
	/**
	 * 签发收据-不包含明细
	 */
	window.armgt.cdac.noticeNo = function() {
		$('<div id="dlgNotice_qianfa">').dialog({
			title : "收据",
			href : $.util.resolveClientUrl('/cs/page/ar-mgt/cdac/receiptNo.jsp'),
			width : 730,
			height : 500,
			top : 20,
			iconCls : 'bicon-file',
			resizable : false,
			modal : true,
			closed : true,
			cache : false,
			toolbar : [ {
				text : '打印',
				iconCls : 'bicon-print',
				handler : function() {
					 $("#contents").printArea();//jquery拓展打印方法
						$.messager.show({
							title : '提示',
							msg : '打印成功',
							showType : 'slide'
						});
						$("#dlgNotice_qianfa").dialog('close');
				}
			}, {
				text : 'Email',
				iconCls : 'bicon-envelope',
				handler : function() {
					//发送邮件
					$('#emailWin').window({
						title : "邮件",
						href:"/cs/page/account/accountToreview/email.jsp",
						width:380,
						height:300,
						closed : true,
						collapsible : false,
						minimizable : false,
						maximizable : false,
					}).window("open");	
				}
			}, {
				text : '下载',
				iconCls : 'bicon-download-alt',
				handler : function() {
				}
			} ],
			buttons : [ {
				text : '关闭',
				iconCls : 'bicon-remove',
				handler : function() {
					$("#dlgNotice_qianfa").dialog('close');
				}
			} ],
			onClose : function() {
				$(this).dialog('destroy');
			},
			onLoad : function() {
				var rows=$(dtgd).datagrid('getSelections');
				var date2=new Date();
				var d2=date2.getMonth()+1;
				date2 = date2.getFullYear()+'-'+d2+'-'+date2.getDate();
				var banace=0;
				var jiaoyifangshi="";
				for(var i =0;i<rows.length;i++){
					banace=banace*1+rows[i].incomeAmount*1;
				}
				$("#zhifuhao").html(rows[0].voucherNo);//支付号（凭证号）
				$("#dateChuju").html(date2);//当前日期
				$("#xiaoxieBanace").html(banace);
				$("#daxieBanace").html(window.armgt.cdac.DX(banace));//jquery.easyui.min封装的方法
				var table="<tr><td colspan=\"4\">缴付："+rows[0].settlementNo+"</td></tr><tr><td colspan=\"4\">CNY："+banace+"</td></tr><tr><td colspan=\"4\">收款日期："+rows[0].tradeDate+"</td></tr>"
				$("#jiaoyixinxi").html(table)
			}
		}).dialog('open');
	};

	$(function() {
		$(dtgd).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ]
		});
		
		var date=new Date();
		var d=date.getMonth()+1;
		var date =  date.getFullYear()+'-'+d+'-01';
		var date1=new Date();
		var d1=date1.getMonth()+1;
		var date1 = date1.getFullYear()+'-'+d1+'-'+date1.getDate();
		
		$("#deadlineShi").datebox({
			value:date1,
			onSelect: function(date){
				$("#tradeDateQi").datebox('setValue', '');
				$("#tradeDateShi").datebox('setValue', '');
				$("#deadlineShi").datebox('setValue', date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate());
			},
			onChange: function(newValue, oldValue){
				$("#tradeDateQi").datebox('setValue', '');
				$("#tradeDateShi").datebox('setValue', '');
			}
		});
		
		$("#tradeDateQi").datebox({
			value:date,
			onSelect: function(date){
				$("#tradeDateQi").datebox('setValue', date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate());
		        $("#deadlineShi").datebox('setValue', '');
			},
			onChange: function(newValue, oldValue){
				 $("#deadlineShi").datebox('setValue', '');
			}
		});
		$("#tradeDateShi").datebox({
			value:date1,
			onSelect: function(date){
				$("#tradeDateShi").datebox('setValue', date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate());
				$("#deadlineShi").datebox('setValue', '');
			},
			onChange: function(newValue, oldValue){
				 $("#deadlineShi").datebox('setValue', '');
			}
		});
		
		$("#deadlineShi").datebox('setValue','');
		$("#tradeDateQi").datebox('setValue',date);
		$("#tradeDateShi").datebox('setValue',date1);
//		$("#deadlineQi").datebox('setValue',date);
		
		
		$('#btnLeaf').linkbutton("disable");
		
		$("#openAccountToreviewPanel").window({    
		    width:300,    
		    height:250,    
		    modal:true,
		    title:'收据信息'
		}).window("close");
		
		$("#account_accountToreview_form_datePayment").datebox({
			required:true
		});
		
		
		$("#openAccountToreviewPanel_shengcheng").click(function(){
			if (!$("#account_openAccountToreviewMingxi_form").form('validate')) {
				return;
			}
			var containsDetails = $('input:radio:checked').val();
			if(containsDetails==1){
				window.armgt.cdac.notice();
			}else{
				window.armgt.cdac.noticeNo();
			}
			
			$("#openAccountToreviewPanel").window('close');
		});
		
		$("#openAccountToreviewPanel_remoud").click(function(){
			$("#openAccountToreviewPanel").window('close');
		});
		
		
		$('#btnLeaf').click(function() {
			if ($('#btnLeaf').linkbutton("options").disabled)
				return;
			$("#openAccountToreviewPanel").window("open");
			//form数据清空
//			$(account_openAccountToreviewMingxi_form).form('clear');
			$("#bsp_ticketbook_newtb_form_drawee").val("");
			$("#account_accountToreview_form_addressee").val("");
			$("#account_accountToreview_form_datePayment").datebox('setValue',"");
			$("#remarks").val("");
			//查询销账明细，，获取收款人或者付款人信息
			var row=$(dtgd).datagrid('getSelected');
			if(row.type==1){//收入才会有收据信息，收款人默认为收款人
				var result = eval("(" + $.ajax( {
					url : "/cs/armgt/receivableViewAction!findALL.action",
					dataType : "json/xml/html",
					data:{
						voucherNo:row.voucherNo
					},
					async : false,
					cache : false,
					type : "post"
				}).responseText + ")");
				$("#bsp_ticketbook_newtb_form_drawee").val(result.rows[0].customerName);
			}
		});
		
		$("#btnSearch").click(function() {
			var formData = $.serializeObject($("#companyDetail_from"));
//			$.post("/cs/armgt/receivableViewAction!findSerach.action", formData, function(result) {
//				var result = JSON.parse(result);
//				if (result.success) {
//					$(dtgd).datagrid('loadData',result.obj);
//				}else{
//					$.messager.show({
//						title : '提示',
//						msg : result.msg,
//						showType : 'slide'
//					});
//					$(dtgd).datagrid('loadData',{total:0,rows:[]});
//				}
//				
//			});
			var url="/cs/armgt/receivableViewAction!findALL.action?abc=1";
			if($("#tradeDateQi").datebox('getValue')!=""&&$("#tradeDateQi").datebox('getValue')!=undefined){
				url+="&tradeDateQi="+$("#tradeDateQi").combobox('getValue');
			}
			if($("#tradeDateShi").datebox('getValue')!=""&&$("#tradeDateShi").datebox('getValue')!=undefined){
				url+="&tradeDateShi="+$("#tradeDateShi").combobox('getValue');
			}
			if($("#deadlineShi").datebox('getValue')!=""&&$("#deadlineShi").datebox('getValue')!=undefined){
				url+="&deadlineShi="+$("#deadlineShi").combobox('getValue');
			}
			if($("#customerNo").combogrid('getValue')!=""&&$("#customerNo").combogrid('getValue')!=undefined){
				url+="&customerNo="+$("#customerNo").combogrid('getValue');
			}
			if($("#noticeNo").val()!=""&&$("#noticeNo").val()!=undefined){
				url+="&noticeNo="+$("#noticeNo").val();
			}
			if($("#orderNo").val()!=""&&$("#orderNo").val()!=undefined){
				url+="&orderNo="+$("#orderNo").val();
			}
			if($("#affiliationNo").combobox('getValue')!=""&&$("#affiliationNo").combobox('getValue')!=undefined){
				url+="&affiliationNo="+$("#affiliationNo").combobox('getValue');
			}
			if($("input[name='counterFee']:checked").val()!=""&&$("input[name='counterFee']:checked").val()!=undefined){
				url+="&counterFee="+$("input[name='counterFee']:checked").val();
			}
			if($("#voucherNo").val()!=""&&$("#voucherNo").val()!=undefined){
				url+="&voucherNo="+$("#voucherNo").val();
			}
			$(dtgd).datagrid({
				url:url
			});
			
			$(dtgd).datagrid('clearSelections');
			$('#btnLeaf').linkbutton("disable");
		});
		
	});
})(jQuery);