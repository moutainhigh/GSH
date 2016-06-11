<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<style>
.pad3{
	padding-bottom: 12px;
}
</style>
<div class="easyui-layout" data-options="fit:true"
	style="overflow: hidden;">
	<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;">
		<a id="btnClose" href="#" class="easyui-linkbutton"><i class="fa fa-times"></i>&nbsp;关闭</a>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<form id="form1" method="post" style="width: 100%; height: 100%;">
		<input type="hidden" id="sup_id" name="id"/>
			<div class="easyui-tabs" data-options="border:false,fit:true"
				style="overflow: hidden;">
				<div title="基本信息" data-options="">
					<table class="table table-striped table-condensed">
						<tr>
							<th>公司名称全称</th>
							<td colspan="3"><input id="cname"  type="text" name="cname" style="width: 80%" readonly="readonly"/></td>
						</tr>
						<tr>
							<th>公司编号</th>
							<td>
								<input type="text" id="companyNo" name="companyNo" readonly="readonly"/>
							</td>
							<th>公司简称</th>
							<td><input id="shortCode" type="text" name="shortCode" readonly="readonly"/></td>
						</tr>
						<tr>
							<th>公司电话</th>
							<td><input id="tel" type="text" name="tel" readonly="readonly"/></td>
							<th>公司传真</th>
							<td><input id="fax" type="text" name="fax" readonly="readonly"/></td>
						</tr>
						<tr>
							<th>邮政编码</th>
							<td><input id="zipCode" type="text" name="zipCode" readonly="readonly"/></td>
							<th>供应商类型</th>
							<td>
								<input type="text" id="supplierType" 
								name="supplierType" style="width: 220px;" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th>供应商级别</th>
							<td colspan="3">
							<input id="supplierLevel" type="text" name="supplierLevel" style="width: 220px;"  readonly="readonly">
							</td>
						</tr>
						<tr>
							<th>公司地址</th>
							<td colspan="3">
								<div class="input-prepend">
									<input id="edit_province" name="province"
										type="text" style="height: 30px; width: 100px" readonly="readonly"/>
									<input id="edit_city" name="city" type="text"
										style="height: 30px; width: 100px" readonly="readonly"/>
									<input type="text" id="s_address" name="address" style="width: 300px;" readonly="readonly">
								</div>
							</td>
						</tr>
						<tr>
							<th>法人代表</th>
							<td><input id="s_lr" type="text" name="lr" readonly="readonly"/></td>
							<th>法人联系地址</th>
							<td><input id="s_lrAddress" type="text" name="lrAddress" readonly="readonly"/></td>
						</tr>
						<tr>
							<th>主要联系人1</th>
							<td><input id="s_contacter1" type="text" name="contacter1" readonly="readonly"/></td>
							<th>联系人电话</th>
							<td><input id="tel1" type="text" name="tel1" readonly="readonly"/></td>
						</tr>
						<tr>
							<th>主要联系人2</th>
							<td><input id="s_contacter2" type="text" name="contacter2" readonly="readonly"/></td>
							<th>联系人电话</th>
							<td><input id="tel2" type="text" name="tel2" readonly="readonly"/></td>
						</tr>
						
					</table>
				</div>

				
				
				<div title="结算信息" data-options="">
					<table class="table"  style="margin: 0;">
						<tr>
							<th>联系人</th>
							<td colspan="3"><input id="s_linkMan" name="linkMan" type="text" style="margin: 0" readonly="readonly"/></td>
						</tr>
						<tr>
							<th>结算方式</th>
							<td>
								<div class="input-append" style="margin: 0">
									<input id="s_jsType" type="text" name="jsType" style="width: 100px;"  readonly="readonly">
									<input id="s_monthDay" name="monthDay" type="text"  style="width: 80px;" readonly="readonly"/>
									<input id="s_quota" name="quota"  type="text" readonly="readonly"/>
								</div>
							</td>
							<th>付款方式</th>
							<td>
								<input id="s_payType" type="text" name="payType"
									style="width: 100px;margin: 0" readonly="readonly">
							</td>
						</tr>
						<tr>
							<th>结算币种</th>
							<td>
								<input type="text" id="s_currency" name="currency"
									style="width: 100px; margin: 0" readonly="readonly">
							</td>
							<th>计算方式</th>
							<td>
								<div class="input-append" style="margin: 0">
									<input id="s_keepNum" type="text" name="keepNum" style="width: 100px;" readonly="readonly">
									<input id="s_carryType" type="text" name="carryType" style="width: 100px;" readonly="readonly">
								</div>
							</td>
						</tr>
						<tr>
							<th>其他财务信息</th>
							<td colspan="3">
								<textarea id="s_cwremark" name="financeRemark" rows="3" style="width:80%;margin: 0" readonly="readonly"></textarea>
							</td>
						</tr>
					</table>
					<table class="table" style="margin-bottom: 0">
						<tr class="info">
							<th>
								银行账户列表
							</th>
							
						</tr>
				    </table>
				    <table id="AccountNoDatagrid"  class="easyui-datagrid" style="width:780px;height:200px"   
        		data-options="url:'',fitColumns:true,singleSelect:true">   

				    	 <thead>   
					        <tr>   
					            <th data-options="field:'accName',width:20">开户名</th>   
					            <th data-options="field:'bank',width:20">开户行</th>   
					           <th data-options="field:'account',width:20">账号</th>
					           <th data-options="field:'bankAddress',width:20">开户行地址</th>
					           <th data-options="field:'bankNo',width:20">行号</th>
					           <th data-options="field:'accountCurrency',width:20">账户币种</th>
					           <th data-options="field:'accountType',width:20">账户类型</th>
					        </tr>   
					    </thead> 
				    </table>
					<input id="accRows" name="accRows" type="hidden"/>
				</div>
			</div>
		</form>
	</div>
</div>

