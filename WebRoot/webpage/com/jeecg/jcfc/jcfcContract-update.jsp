<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>合同号</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="jcfcContractController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${jcfcContractPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								申请日期:
							</label>
						</td>
						<td class="value">
									  <input id="sqrq" name="sqrq" type="text" style="width: 150px"  class="Wdate" onClick="WdatePicker()"  datatype="*" ignore="checked" value='<fmt:formatDate value='${jcfcContractPage.sqrq}' type="date" pattern="yyyy-MM-dd"/>'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">申请日期</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								项目名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="xxmc" name="xxmc" type="text" maxlength="255" style="width: 150px" class="inputxt"  datatype="*" ignore="checked"  value='${jcfcContractPage.xxmc}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">项目名称</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								合同编码:
							</label>
						</td>
						<td class="value">
						     	 <input id="htbm" name="htbm" type="text" maxlength="32" style="width: 150px" class="inputxt"  validType=",,id" datatype="*" ignore="checked"  value='${jcfcContractPage.htbm}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同编码</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								业务经理:
							</label>
						</td>
						<td class="value">
						     	 <input id="ywjl" name="ywjl" type="text" maxlength="10" style="width: 150px" class="inputxt"  datatype="*" ignore="checked"  value='${jcfcContractPage.ywjl}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务经理</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								集成金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="jcje" name="jcje" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${jcfcContractPage.jcje}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">集成金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								软件金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="rjje" name="rjje" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${jcfcContractPage.rjje}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">软件金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								工程金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="gcje" name="gcje" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${jcfcContractPage.gcje}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">工程金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								服务金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="fwje" name="fwje" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${jcfcContractPage.fwje}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">服务金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								技术金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="jsje" name="jsje" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${jcfcContractPage.jsje}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">技术金额</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								其它金额:
							</label>
						</td>
						<td class="value">
						     	 <input id="qtje" name="qtje" type="text" maxlength="32" style="width: 150px" class="inputxt"  ignore="ignore"  value='${jcfcContractPage.qtje}'/>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">其它金额</label>
						</td>
					</tr>
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/jcfc/jcfcContract.js"></script>		
