<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="jcfcContractList" checkbox="true" pagination="true" fitColumns="true" title="合同号" actionUrl="jcfcContractController.do?datagrid" 
  	idField="id" sortName="createDate" sortOrder="desc"  fit="true" queryMode="group">
	<t:dgCol title="id"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="申请日期"  field="sqrq" extendParams="editor:'datebox'"  formatter="yyyy-MM-dd"  query="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="项目名称"  field="xxmc" extendParams="editor:'text'"  query="true"  queryMode="single"  width="120"></t:dgCol>
	<t:dgCol title="合同编码"  field="htbm" extendParams="editor:'text'"  query="true"  queryMode="single"  width="120"></t:dgCol>
	<t:dgCol title="业务经理"  field="ywjl" extendParams="editor:'text'"  query="true"  queryMode="single"  width="120"></t:dgCol>
	<t:dgCol title="集成金额"  field="jcje" extendParams="editor:'text'"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="软件金额"  field="rjje" extendParams="editor:'text'"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="工程金额"  field="gcje" extendParams="editor:'text'"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="服务金额"  field="fwje" extendParams="editor:'text'"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="技术金额"  field="jsje" extendParams="editor:'text'"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="其它金额"  field="qtje" extendParams="editor:'text'"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="合计金额"  field="hjje"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="集成编码"  field="jcbm"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="软件编码"  field="rjbm"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="工程编码"  field="gcbm"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="服务编码"  field="fwbm"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="技术编码"  field="jsbm"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="其它编码"  field="qtbm"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="优选"  field="yx"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="合同号"  field="hth"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="创建日期"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="更新日期"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="流程状态"  field="bpmStatus"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
	<t:dgCol title="删除标记"  field="deleteFlag"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   
   	<t:dgToolBar  title="录入" icon="icon-add"  funname="addRow"></t:dgToolBar>
	<t:dgToolBar  title="编辑" icon="icon-edit"  funname="editRow"></t:dgToolBar>
	<t:dgToolBar  title="保存" icon="icon-save" url="jcfcContractController.do?doAdd" funname="saveData"></t:dgToolBar>
	<t:dgToolBar  title="取消编辑" icon="icon-undo" funname="reject"></t:dgToolBar>
	<t:dgToolBar title="批量删除"  icon="icon-remove" url="jcfcContractController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
	 
	<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
	<t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
	<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
//添加行
function addRow(title,addurl,gname){
	$('#'+gname).datagrid('appendRow',{});
	var editIndex = $('#'+gname).datagrid('getRows').length-1;
	$('#'+gname).datagrid('selectRow', editIndex)
			.datagrid('beginEdit', editIndex);
}
//保存数据
function saveData(title,addurl,gname){
	if(!endEdit(gname))
		return false;
	var rows=$('#'+gname).datagrid("getChanges","inserted");
	var uprows=$('#'+gname).datagrid("getChanges","updated");
	rows=rows.concat(uprows);
	if(rows.length<=0){
		tip("没有需要保存的数据！")
		return false;
	}
	var result={};
	for(var i=0;i<rows.length;i++){
		for(var d in rows[i]){
			result["list["+i+"]."+d]=rows[i][d];
		}
	}
	$.ajax({
		url:"jcfcContractController.do?doAdd",
		type:"post",
		data:result,
		dataType:"json",
		success:function(data){
			tip(data.msg);
			if(data.success){
				reloadTable();
			}
		}
	})
}
//结束编辑
function endEdit(gname){
	var  editIndex = $('#'+gname).datagrid('getRows').length-1;
	for(var i=0;i<=editIndex;i++){
		if($('#'+gname).datagrid('validateRow', i)){
			$('#'+gname).datagrid('endEdit', i);
		}else{
			tip("请选择必填项(带有红色三角形状的字段)!");
			return false;
		}
	}
	return true;
}
//编辑行
function editRow(title,addurl,gname){
	var rows=$('#'+gname).datagrid("getChecked");
	if(rows.length==0){
		tip("请选择条目");
		return false;
	}
	for(var i=0;i<rows.length;i++){
		var index= $('#'+gname).datagrid('getRowIndex', rows[i]);
		$('#'+gname).datagrid('beginEdit', index);
	}
}
//取消编辑
function reject(title,addurl,gname){
	$('#'+gname).datagrid('clearChecked');
	$('#'+gname).datagrid('rejectChanges');


}   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'jcfcContractController.do?upload', "jcfcContractList");
}

//导出
function ExportXls() {
	JeecgExcelExport("jcfcContractController.do?exportXls","jcfcContractList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("jcfcContractController.do?exportXlsByT","jcfcContractList");
}

//bootstrap列表图片格式化
function btListImgFormatter(value,row,index){
	return listFileImgFormat(value,"image");
}
//bootstrap列表文件格式化
function btListFileFormatter(value,row,index){
	return listFileImgFormat(value);
}

//列表文件图片 列格式化方法
function listFileImgFormat(value,type){
	var href='';
	if(value==null || value.length==0){
		return href;
	}
	var value1 = "img/server/"+value;
	if("image"==type){
 		href+="<img src='"+value1+"' width=30 height=30  onmouseover='tipImg(this)' onmouseout='moveTipImg()' style='vertical-align:middle'/>";
	}else{
 		if(value.indexOf(".jpg")>-1 || value.indexOf(".gif")>-1 || value.indexOf(".png")>-1){
 			href+="<img src='"+value1+"' onmouseover='tipImg(this)' onmouseout='moveTipImg()' width=30 height=30 style='vertical-align:middle'/>";
 		}else{
 			var value2 = "img/server/"+value+"?down=true";
 			href+="<a href='"+value2+"' class='ace_button' style='text-decoration:none;' target=_blank><u><i class='fa fa-download'></i>点击下载</u></a>";
 		}
	}
	return href;
}

</script>
