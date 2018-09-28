<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>业务经理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="jcfcPersonController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${jcfcPersonPage.id }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							业务编码:
						</label>
					</td>
					<td class="value">
					     	 <input id="ywbm" name="ywbm" type="text" maxlength="32" style="width: 150px" class="inputxt"  validType=",,id" datatype="*" ignore="checked" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务编码</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							业务经理:
						</label>
					</td>
					<td class="value">
					     	 <input id="ywmc" name="ywmc" type="text" maxlength="32" style="width: 150px" class="inputxt"  validType=",,id" datatype="*" ignore="checked" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">业务经理</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							删除标记:
						</label>
					</td>
					<td class="value">
					     	 <input id="deleteFlag" name="deleteFlag" type="text" maxlength="10" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">删除标记</label>
						</td>
				</tr>
				
				
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/jeecg/jcfc/jcfcPerson.js"></script>		
