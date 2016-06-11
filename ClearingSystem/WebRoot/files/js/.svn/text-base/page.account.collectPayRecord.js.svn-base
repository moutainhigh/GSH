(function($) {

	$.util.namespace("account.collectPayRecord");

	var testData1 = {
			"total" : 10,
			"rows" : [
			 {
				"orderId" : "abc123456789",
				"account" : "cen1234",
				"price" : "150",
				"name" : "张三",
				"status" : "1",
			}, {
				"orderId" : "abc123456789",
				"account" : "cen1234",
				"price" : "150",
				"name" : "李四",
				"status" : "1",
			}]
		};
	
	/**
	 * 主页面的按钮和datagrid
	 */
	var collectPayRecordMingxi = '#collectPayRecordMingxi';
	var collectPayRecordSearchGaoji="#collectPayRecord_search_gaoji";
	var accountCollectPayRecordDg = '#account_collectPayRecord_dg';
	
	/**
	 * datagrid 设置分页栏
	 */
	window.account.collectPayRecord.loadPageBar = function() {
		$(accountCollectPayRecordDg).datagrid('getPager').pagination({
			layout : [ 'list', 'sep', 'first', 'prev', 'sep', 'links', 'sep', 'next', 'last', 'sep', 'refresh' ],
		});
	};
	
	/**
	 * 页面小计和总计
	 */
	window.account.collectPayRecord.onLoadSuccess=function(data){
		var sumfootertable = '<table class="sum-footer">';
		var row1 = '<tr><th></th>';
		var row2 = '<tr><th>小计：</th>';
		var row3 = '<tr><th>总计：</th>';
		var i = 1;
		for ( var item in data.sumfooter) {
			row1 += '<th class="sum-footer-' + i + ' sum-footer-left">收入</th>';
			row1 += '<th class="sum-footer-' + i + ' ">支出 </th>';
			row1 += '<th class="sum-footer-' + i + ' ">余额（' + item + '）</th>';
			row3 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + data.sumfooter[item][0].income + '</td>';
			row3 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][0].expenditure + '</td>';
			row3 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][0].balance + '</td>';
			if (data.sumfooter[item][1]) {
				row2 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + data.sumfooter[item][1].income + '</td>';
				row2 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][1].expenditure + '</td>';
				row2 += '<td class="sum-footer-' + i + ' ">' + data.sumfooter[item][1].balance + '</td>';
			} else {
				row2 += '<td class="sum-footer-' + i + ' sum-footer-left ">' + 0.00 + '</td>';
				row2 += '<td class="sum-footer-' + i + ' ">' + 0.00 + '</td>';
				row2 += '<td class="sum-footer-' + i + ' ">' + 0.00 + '</td>';
			}
			if (i = 1)
				i++;
			else
				i--;
		}
		sumfootertable += row1 + '</tr>' + row2 + '</tr>' + row3 + '</tr></table>';
		$('#collectPayRecord_footer').html(sumfootertable);
	};
	
	window.account.collectPayRecord.amountFormatter = function(value, row, index) {
		if(row.currencyType==undefined){
			return $.formatCurrencySign(value, "CNY");
		}
		return $.formatCurrencySign(value, row.currencyType);
	};
	
	
	/**
	 * 选择一行
	 */
	window.account.collectPayRecord.onSelect = function(index, row) {
		if(row.type!=4){
			$(collectPayRecordMingxi).linkbutton('enable');
		}else{
			$(collectPayRecordMingxi).linkbutton('disable');
		}
		
	};
	
	
	window.account.collectPayRecord.status = function(value, row, index){
		if(row.id==0){
			return;
		}
		if (row.status == 1) {
			return "未复核";
		} else if (row.status == 2) {
			return "已复核";
		}
	};
	
	/**
	 * 状态的样式
	 */
	window.account.collectPayRecord.statusStyler = function(value, row, index){
		if(row.transactionID==0){
			return;
		}
		if (row.status == 1) {
			return 'background-color:blue;color:#fff;';
		} else if (row.status == 2) {
			return 'background-color:green;color:#fff;';
		}
	};
	
	window.account.collectPayRecord.Mingxi=function(index){
		$('<div id="account_openAccountToreview_dialog">').dialog({
			title : "账目明细",
			href : $.util.resolveClientUrl('/cs/page/account/collectPayRecord/openAccountToreview.jsp'),
			width : 550,
			height : 427,
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
				var id = $(accountCollectPayRecordDg).datagrid('getRows')[index].id;
				var type = $(accountCollectPayRecordDg).datagrid('getRows')[index].type;
				var status = $(accountCollectPayRecordDg).datagrid('getRows')[index].status;
				$('#openCollectPayRecord_dg').datagrid({    
				    url:'/cs/account/accountDetailAction!AccountMingxi.action?id='+id+"&type="+type,
				    onLoadSuccess:window.account.collectPayRecord.Mingxi.onLoadSuccess1
				});
				
				if(type==1&&status==2){//收入才会有收据信息，收款人默认为收款人
					var currentBtn = document.getElementById("chansheng_save");
					currentBtn.style.display = "block";
				}else{
					var currentBtn = document.getElementById("chansheng_save");
					currentBtn.style.display = "none";
				}
				$("#chansheng_save").click(function(){
					$("#openAccountToreviewPanel").window("open");
					//form数据清空
//					$(account_openAccountToreviewMingxi_form).form('clear');
					$("#bsp_ticketbook_newtb_form_drawee").val("");
					$("#account_accountToreview_form_addressee").val("");
					$("#account_accountToreview_form_datePayment").datebox('setValue',"");
					$("#remarks").val("");
					//查询销账明细，，获取收款人或者付款人信息
					var row=$("#account_collectPayRecord_dg").datagrid('getSelected');
					if(type==1){//收入才会有收据信息，收款人默认为收款人
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
				
				$("#openAccount_remoud").click(function(){
					$("#account_openAccountToreview_dialog").dialog("close");
				});
			}
		}).dialog('open');
	};
	
	/**
	 * 页面小计和总计
	 */
	window.account.collectPayRecord.Mingxi.onLoadSuccess1=function(data){
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
		$('#sum_footer2').html(sumfootertable);
	};
	
	
	/**
	 * 明细
	 */
	window.account.collectPayRecord.MingxiOnSelect = function() {
		var getrow = $(accountCollectPayRecordDg).datagrid('getSelections', $(accountCollectPayRecordDg).datagrid('getSelected'));
		if (getrow.length <= 0) {
			$.messager.show({
				title : '操作提示',
				msg : '请选择要查看的明细！',
				showType : 'slide'
			});
			return;
		}
		
		for ( var i = 0; i < getrow.length; i++) {
			var row = $(accountCollectPayRecordDg).datagrid('getRowIndex',getrow[i]);
			window.account.collectPayRecord.Mingxi(row);
		}
			
	};
	
	
	/**
	 * 查询收款付款记录
	 */
	window.account.collectPayRecord.searchcollectPayRecord = function(){
		$('<div id="account_searchcollectPayRecord_dialog">').dialog({
			title : "查询收款付款记录",
			href : $.util.resolveClientUrl('/cs/page/account/collectPayRecord/searchCollectPayRecord.jsp'),
			width : 330,
			height : 180,
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
				changesearchCollectPayRecord();
				/**
				 * 查询
				 */
				$("#collectPayRecord_search").click(function(){
					if (!$("#account_collectPayRecord_serchcollectPayRecord_form").form('validate')) {
						return;
					}
					var formData = $.serializeObject($("#account_collectPayRecord_serchcollectPayRecord_form"));
					$.post("/cs/account/accountDetailAction!findCollectPay.action", formData, function(result) {
						var result = JSON.parse(result);
						if (result.success) {
							if($("#ZHcode").val()==0){
								$("#collect_tradeObject").html("全部");
							}else if($("#collectPayRecord_zhanghu").combogrid('getValue')==""){
								if($("#ZHcode").val()==1){
									$("#collect_tradeObject").html("应收账户");
								}else if($("#ZHcode").val()==2){
									$("#collect_tradeObject").html("应付账户");
								}
							}
							
							else{
								$("#collect_tradeObject").html(result.obj.rows[0].tradeNo+"-"+result.obj.rows[0].tradeObject);
							}
							$("#collect_tradeDate").html($("#collectPayRecord_date_qi").datebox('getValue')+"至"+$("#collectPayRecord_date_shi").datebox('getValue'));
							$(accountCollectPayRecordDg).datagrid('loadData',result.obj);
						}else{
							$.messager.show({
								title : '提示',
								msg : result.msg,
								showType : 'slide'
							});
							$("#collect_tradeObject").html("");
							$("#collect_tradeDate").html("");
							$(accountCollectPayRecordDg).datagrid('loadData',{total:0,rows:[]});
						}
						
					});
					$("#account_searchcollectPayRecord_dialog").dialog('close');
				});
				
				/**
				 * 关闭
				 */
				$("#collectPayRecord_close").click(function(){
					$("#account_searchcollectPayRecord_dialog").dialog('close');
				});
			}
		}).dialog('open');
	};
	
	changesearchCollectPayRecord=function(){
		var ZHcode = $("#ZHcode").val();
		if(ZHcode==0){
			$('#collectPayRecord_zhanghu').combogrid({
				idField:'accountCode',   
		        textField:'accountCode', 
		        required:false,
			    columns:[[    
			    ]]   
			});
			$('#collectPayRecord_zhanghu').combogrid("clear");
			$('#collectPayRecord_zhanghu').combogrid('disable');
		}else if(ZHcode==1){
			$('#collectPayRecord_zhanghu').combogrid('enable'); 
			$('#collectPayRecord_zhanghu').combogrid({    
				idField:'accountCode',   
		        textField:'accountCode', 
			    panelWidth:380,    
				url: '/cs/bset/CollectpayaccountAction!findAllList.action?accountSource=1',     
			    columns:[[    
				     {field:'accountCode',title:'账户编码',width:80},     
					 {field:'accountType',title:'账户类型',width:120},    
				     {field:'accountName',title:'账户名称',width:150} 
			    ]]    
			});  
			$('#collectPayRecord_zhanghu').combogrid("clear");
		}else if(ZHcode==2){
			$('#collectPayRecord_zhanghu').combogrid('enable'); 
			$('#collectPayRecord_zhanghu').combogrid({    
				idField:'accountCode',   
		        textField:'accountCode', 
			    panelWidth:380,    
				url: '/cs/bset/CollectpayaccountAction!findAllList.action?accountSource=2',   
			    columns:[[    
				      {field:'accountCode',title:'账户编码',width:80},     
					 {field:'accountType',title:'账户类型',width:120},    
				     {field:'accountName',title:'账户名称',width:150} 
			    ]]    
			});  
		}
	};
	
	changesearchCollectPayRecord_zhu=function(){
		var zhcode = $("#zhcode").val();
		if(zhcode==0){
			$('#collectPayRecord_zhanghu_zhu').combogrid({
				idField:'accountCode',   
		        textField:'accountCode', 
		        required:false,
			    columns:[[    
			    ]]   
			});
			$('#collectPayRecord_zhanghu_zhu').combogrid("clear");
			$('#collectPayRecord_zhanghu_zhu').combogrid('disable');
		}else if(zhcode==1){
			$('#collectPayRecord_zhanghu_zhu').combogrid('enable'); 
			$('#collectPayRecord_zhanghu_zhu').combogrid({    
				idField:'accountCode',   
		        textField:'accountCode', 
			    panelWidth:380,    
				url: '/cs/bset/CollectpayaccountAction!findAllList.action?accountSource=1',     
			    columns:[[    
				      {field:'accountCode',title:'账户编码',width:80},     
					 {field:'accountType',title:'账户类型',width:120},    
				     {field:'accountName',title:'账户名称',width:150} 
			    ]]    
			});  
			$('#collectPayRecord_zhanghu_zhu').combogrid("clear");
		}else if(zhcode==2){
			$('#collectPayRecord_zhanghu_zhu').combogrid('enable'); 
			$('#collectPayRecord_zhanghu_zhu').combogrid({    
				idField:'accountCode',   
		        textField:'accountCode', 
			    panelWidth:380,    
				url: '/cs/bset/CollectpayaccountAction!findAllList.action?accountSource=2',   
			    columns:[[    
			            {field:'accountCode',title:'账户编码',width:80},     
						{field:'accountType',title:'账户类型',width:120},    
						{field:'accountName',title:'账户名称',width:150} 
			    ]]    
			});  
		}
	};
	
	/**
	 * 按钮事件绑定
	 */
	window.account.collectPayRecord.bindButtonEvent = function() {
		
		/**
		 * 明细
		 */
		$(collectPayRecordMingxi).click(function(){
			window.account.collectPayRecord.MingxiOnSelect();
		});
		
		/**
		 * 查询
		 */
		$(collectPayRecordSearchGaoji).click(function(){
			window.account.collectPayRecord.searchcollectPayRecord();
		});
		
		/**
		 * 主页面查询
		 */
		$("#collect_onclick_search").click(function(){
			if (!$("#account_collect_onclick_search_form").form('validate')) {
				return;
			}
			var formData = $.serializeObject($("#account_collect_onclick_search_form"));
			$.post("/cs/account/accountDetailAction!findCollectPay.action", formData, function(result) {
				var result = JSON.parse(result);
				if (result.success) {
					if($("#zhcode").val()==0){
						$("#collect_tradeObject").html("全部");
					}else if($("#collectPayRecord_zhanghu_zhu").combogrid('getValue')==""){
						if($("#zhcode").val()==1){
							$("#collect_tradeObject").html("应收账户");
						}else if($("#zhcode").val()==2){
							$("#collect_tradeObject").html("应付账户");
						}
					}
					else{
						$("#collect_tradeObject").html(result.obj.rows[0].tradeNo+"-"+result.obj.rows[0].tradeObject);
					}
					
					$("#collect_tradeDate").html($("#collect_date_qi").datebox('getValue')+"至"+$("#collect_date_shi").datebox('getValue'));
					$(accountCollectPayRecordDg).datagrid('loadData',result.obj);
				}else{
					$.messager.show({
						title : '提示',
						msg : result.msg,
						showType : 'slide'
					});
					$("#collect_tradeObject").html("");
					$("#collect_tradeDate").html("");
					$(accountCollectPayRecordDg).datagrid('loadData',{total:0,rows:[]});
				}
				
			});
		});
	};
	
	/**
	 * 签发收据
	 */
	window.account.collectPayRecord.notice = function() {
		$('<div id="dlgNotice_qianfa">').dialog({
			title : "收据",
			href : $.util.resolveClientUrl('/cs/page/account/accountToreview/receipt.jsp'),
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
				var row=$("#account_collectPayRecord_dg").datagrid('getSelected');
				var date2=new Date();
				var d2=date2.getMonth()+1;
				date2 = date2.getFullYear()+'-'+d2+'-'+date2.getDate();
				var rows = $('#openCollectPayRecord_dg').datagrid("getRows");
				$("#shoujianren").html($("#account_accountToreview_form_addressee").val());//收件人
				$("#zhifuhao").html(row.voucherNo);//支付号（凭证号）
				$("#dateChuju").html(date2);//当前日期
				$("#xiaoxieBanace").html(row.income);
				$("#daxieBanace").html(window.account.collectPayRecord.DX(row.income));//jquery.easyui.min封装的方法
				var table="<tr><th>文档号码</th><th>签发日</th><th>金额/人民币</th><th>已收金额</th></tr>";
				for(var i=0;i<rows.length;i++){
					table+="<tr><td>"+rows[i].exchangeNo+"</td><td>"+date2+"</td><td>"+rows[i].payBayAmount+"</td><td>"+rows[i].payAmount+"</td></tr>"
				}
				table+="<tr><td colspan=\"4\">支付总额："+row.income+"</td></tr><tr><td colspan=\"4\">支付方式："+rows[0].paymentMethod+"</td></tr><tr><td colspan=\"4\">收款日期："+row.tradeDate+"</td></tr>"
				$("#jiaoyixinxi").html(table)
			}
		}).dialog('open');
	};
	
	/**
	 * 签发收据-不包含明细
	 */
	window.account.collectPayRecord.noticeNo = function() {
		$('<div id="dlgNotice_qianfa">').dialog({
			title : "收据",
			href : $.util.resolveClientUrl('/cs/page/account/accountToreview/receiptNo.jsp'),
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
				var row=$("#account_collectPayRecord_dg").datagrid('getSelected');
				var date2=new Date();
				var d2=date2.getMonth()+1;
				date2 = date2.getFullYear()+'-'+d2+'-'+date2.getDate();
				var rows = $('#openCollectPayRecord_dg').datagrid("getRows");
				$("#zhifuhao").html(row.voucherNo);//支付号（凭证号）
				$("#dateChuju").html(date2);//当前日期
				$("#xiaoxieBanace").html(row.income);
				$("#daxieBanace").html(window.account.collectPayRecord.DX(row.income));//jquery.easyui.min封装的方法
				var table="<tr><td colspan=\"4\">缴付："+row.settlementNo+"</td></tr><tr><td colspan=\"4\">CNY："+row.income+"</td></tr><tr><td colspan=\"4\">收款日期："+row.tradeDate+"</td></tr>"
				$("#jiaoyixinxi").html(table)
			}
		}).dialog('open');
	};
	
	window.account.collectPayRecord.DX=function(n) {
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
	
	
	$(function() {
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
				alert(222)
				window.account.collectPayRecord.notice();
			}else{
				window.account.collectPayRecord.noticeNo();
			}
			
			$("#openAccountToreviewPanel").window('close');
		});
		
		$("#openAccountToreviewPanel_remoud").click(function(){
			$("#openAccountToreviewPanel").window('close');
		});
		
		
		$(collectPayRecordMingxi).linkbutton('disable');
		var aa=new Date();
		var bb=aa.getMonth()+1;
		var aa=aa.getFullYear()+'-'+bb+'-01';
		var cc=new Date();
		var dd=cc.getMonth()+1;
		var cc = cc.getFullYear()+'-'+dd+'-'+cc.getDate();
		$("#collect_date_qi").datebox('setValue',aa);
		$("#collect_date_shi").datebox('setValue',cc);
		/**
		 * 关闭初始化加载高级查询页面
		 */
		//window.account.collectPayRecord.searchcollectPayRecord();
		/**
		 * 初始化的时候加载页面的查询数据
		 */
		changesearchCollectPayRecord_zhu();
		window.account.collectPayRecord.loadPageBar();
		window.account.collectPayRecord.bindButtonEvent();
		
	});

})(jQuery);