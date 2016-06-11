<%@ page pageEncoding="UTF-8"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'south',border:false" style="height:40px;background:#eee;border-top: solid 1px #ccc;overflow: hidden;text-align: right;padding: 6px;">
		<a id="btnClose" href="#" class="easyui-linkbutton"><i class="fa fa-times"></i>&nbsp;关闭</a>
	</div>
	<div data-options="region:'center',border:false"
		style="overflow: hidden;">
		<form id="form_khinfo" style="width: 100%; height: 100%;">
			<div class="easyui-tabs" data-options="border:false,fit:true"
				style="overflow: hidden;">
					
					<div title="基本信息" data-options="">
						<table class="table table-striped  table-condensed">
							<tr>
								<th>
									客户全称
								</th>
								<td>
									<input type="text" name="cpName" readonly="readonly">
								</td>
								<th>
									客户简称
								</th>
								<td>
									<input type="text" name="cpAbbreviation" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th>
									客户类型
								</th>
								<td>
									<input type="text" name="cpType" readonly="readonly">
								</td>
								<th>
									客户类型细分
								</th>
								<td>
									<input type="text" name="cpTypeExtend" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th>
									客户编号
								</th>
								<td>
									<input type="text" name="cpCode" readonly="readonly">
								</td>
								<th>
									客户状态
								</th>
								<td>
									<input id="edit_cpStatus" name="cpStatus" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th>
									所属行业
								</th>
								<td>
									<input type="text" name="cpIndustry" readonly="readonly">
								</td>
								<th>
									公司规模
								</th>
								<td>
									<input type="text" name="cpScale" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th>
									公司电话
								</th>
								<td>
									<input type="text" name="cpTelephone" readonly="readonly">
								</td>
								<th>
									公司主页
								</th>
								<td>
									<input type="text" name="cpHome" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th>
									公司传真
								</th>
								<td>
									<input type="text" name="cpFax" readonly="readonly">
								</td>
								<th>
									邮政编码
								</th>
								<td>
									<input type="text" name="cpZipCode" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th>
									公司地址
								</th>
								<td colspan="3">
									<div class="input-append">
										<input type="text" name="cpProvinces"
											style="width: 80px;" readonly="readonly" />
										<input type="text" name="cpCity" style="width: 80px;"
											readonly="readonly" />
										<input type="text" name="cpAddress"
											style="width: 300px;" readonly="readonly">
									</div>
								</td>
							</tr>
							<tr>
								<th>
									法人代表
								</th>
								<td>
									<input type="text" name="cpLrName" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th>
									<p id="looklxr1">联系人1</p>
								</th>
								<td>
									<input type="text" style="height: 30px; width: 200px;"
									type="text" name="lxr1" id="lxr1" readonly="readonly">
									
								</td>
								<th>
									<p id="looklxr2">联系人2</p>
								</th>
								<td>
									<input type="text" style="height: 30px; width: 200px;"
									type="text" name="lxr2" id="lxr2" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th>
									<p id="looklxr3">联系人3</p>
								</th>
								<td>
									<input type="text" style="height: 30px; width: 200px;"
									type="text" name="lxr3" id="lxr3" readonly="readonly">
								</td>
								<th>
									<p id="looklxr4">联系人4</p>
								</th>
								<td>
									<input type="text" style="height: 30px; width: 200px;"
									type="text" name="lxr4" id="lxr4" readonly="readonly">
								</td>
							</tr>
							<tr>
								<th>
									<p id="looklxr5">联系人5</p>
								</th>
								<td>
									<input type="text" style="height: 30px; width: 200px;"
									type="text" name="lxr5" id="lxr5" readonly="readonly">
								</td>
								<th>
									<p id="looklxr6">联系人6</p>
								</th>
								<td>
									<input type="text" style="height: 30px; width: 200px;"
									type="text" name="lxr6" id="lxr6" readonly="readonly">
								</td>
							</tr>

							<tr>
								<th>
									营业执照有效期
								</th>
								<td>
									<input type="text" name="cpValid" readonly="readonly">
								</td>
								<th>

								</th>
								<td>

								</td>
							</tr>

							<tr>
								<th>
									公司LOGO
								</th>
								<td>
									<input type="text" name="cpLogo" readonly="readonly" />
								</td>
								<th>
									营业执照
								</th>
								<td>
									<input type="text" name="cpYinYeZhiZhao" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div id="cpLogo_view">

									</div>
								</td>
								<td colspan="2">
									<div id="cpYinYeZhiZhao_view"></div>
								</td>
							</tr>
						</table>
					</div>
					
					<div title="财务信息" data-options="">
						<table class="table table-striped table-condensed">
							<tr>
								<th>
									财务部联系人
								</th>
								<td>
									<input type="text" name="cpCaiWuBuLxrName" readonly="readonly" />
								</td>
								<th>
								</th>
								<td>
								</td>
							</tr>
							<tr>
								<th>
									结算方式
								</th>
								<td colspan="3">
									<div class="input-prepend">
										<input type="text" name="cpJieSuanType"
											style="width: 80px;" readonly="readonly">
										<input name="cpdayOrMonth" style="width: 50px;"
											type="text" readonly="readonly" />
										<input name="cpQuota" type="text" readonly="readonly" />
									</div>
								</td>
							</tr>
							<tr>
								<th>
									付款方式
								</th>
								<td>
									<input name="cpPayWay" type="text" style="width: 100px;"
										readonly="readonly">
								</td>
								<th>
									结算日期
								</th>
								<td>
									<div>
										<!-- 
									<input name="entity.cpJieSuanStartDate" type="text"
										class="easyui-datebox" style="height: 30px; width: 96px;">
									 -->
										<span>每月</span>
										<input type="text" id="cjss"
											name="cpSettlementStartDate"
											style="height: 20px; width: 30px;" readonly="readonly" />
										<span>日</span>
										<input type="text" value="至"
											style="height: 20px; width: 15px;" readonly="readonly" />
										<!-- 
									<input name="entity.cpJieSuanEndDate" type="text"
										class="easyui-datebox" style="height: 30px; width: 96px;">
									 -->
										<input type="text" id="cjse" name="cpSettlementEndDate"
											style="height: 20px; width: 30px;" readonly="readonly" />
										<span>日</span>
									</div>
								</td>
							</tr>

							<tr>
								<th>
									币种
								</th>
								<td>
									<input type="text" name="cpCurrency"
										style="width: 100px;" readonly="readonly">
								</td>
								<th>
									汇率标准
								</th>
								<td>
									<input name="cpExchangeRate" type="text" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th>
									对账日期
								</th>
								<td>
									<input type="text" id="cpDzDate" name="cpDzDate"
										style="height: 20px; width: 30px;" readonly="readonly" />
									<span>日</span>
								</td>
								<th>
									出账日期
								</th>
								<td>
									<div class="input-prepend">
										<input type="text" id="cpCzDate" name="cpCzDate"
											style="height: 20px; width: 30px;" readonly="readonly" />
										<span>日</span>
									</div>
								</td>
							</tr>
							<tr>
								<th>
									汇款日期
								</th>
								<td>
									<input type="text" id="cpHuiDate"
										name="cpRemittanceDate"
										style="height: 20px; width: 30px;" readonly="readonly" />
									<span>日</span>
								</td>
								<th>
									到账天数
								</th>
								<td>
									<input name="cpDzDayCount" type="text" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th>
									结算账户
								</th>
								<td colspan="3">
									<input name="cpJieSuanAccount" type="text" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th>
									退款账户
								</th>
								<td colspan="3">
									<input name="cpTuiKuanAccount" type="text" readonly="readonly" />
								</td>
							</tr>
							<tr>
								<th>
									其他财务信息
								</th>
								<td colspan="3">
									<textarea name="cpCaiWuRemark" rows="3"
										style="width: 90%;" readonly="readonly"></textarea>
								</td>
							</tr>
						</table>
					</div>
					
					
			</div>
		</form>
	</div>
</div>
