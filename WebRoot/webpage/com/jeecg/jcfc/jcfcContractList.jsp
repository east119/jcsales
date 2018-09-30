<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker,autocomplete"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="jcfcContractrowList" checkbox="true"  pagination="true" fitColumns="false" sortName="createDate"  
  	title="合同号" actionUrl="jcfcContractController.do?datagrid" idField="id"  queryMode="group">
    <t:dgCol title="id"  field="id"  hidden="true" ></t:dgCol>
    <t:dgCol title="项目名称"  field="xxmc"  queryMode="single" query="true" extendParams="editor:{type:'validatebox',options:{required:'true'}}" width="200"></t:dgCol>
    <t:dgCol title="业务经理"  field="ywjl"  queryMode="single" query="true" filterType="combobox" extendParams="editor:'combobox'" width="100" dictionary="jcfc_person where delete_flag = 0,ywbm,ywmc"></t:dgCol> 
    <t:dgCol title="合同年份"  field="htnf"  queryMode="single" query="true" filterType="combobox" extendParams="editor:'combobox'" width="100" dictionary="htnf"></t:dgCol>
    <t:dgCol title="合同编码"  field="htbm"  extendParams="editor:'text'" width="100"></t:dgCol>
   	<t:dgCol title="申请日期"  field="sqrq"  queryMode="group"  query="true" extendParams="editor:'datebox'" width="100" formatter="yyyy-MM-dd"></t:dgCol>
    <t:dgCol title="集成金额"  field="jcje"  extendParams="editor:{type:'validatebox',options:{validType:'decimalTwo'}}" width="100"></t:dgCol>
    <t:dgCol title="软件金额"  field="rjje"  extendParams="editor:{type:'validatebox',options:{validType:'decimalTwo'}}" width="100"></t:dgCol>
    <t:dgCol title="工程金额"  field="gcje"  extendParams="editor:{type:'validatebox',options:{validType:'decimalTwo'}}" width="100"></t:dgCol>
    <t:dgCol title="服务金额"  field="fwje"  extendParams="editor:{type:'validatebox',options:{validType:'decimalTwo'}}" width="100"></t:dgCol>
    <t:dgCol title="技术金额"  field="jsje"  extendParams="editor:{type:'validatebox',options:{validType:'decimalTwo'}}" width="100"></t:dgCol>
    <t:dgCol title="其它金额"  field="qtje"  extendParams="editor:{type:'validatebox',options:{validType:'decimalTwo'}}" width="100"></t:dgCol>
    <t:dgCol title="合计金额"  field="hjje"  hidden="true" ></t:dgCol>
    <t:dgCol title="集成编码"  field="jcbm" width="100"></t:dgCol>
    <t:dgCol title="软件编码"  field="rjbm" width="100"></t:dgCol>
    <t:dgCol title="工程编码"  field="gcbm" width="100"></t:dgCol>
    <t:dgCol title="服务编码"  field="fwbm" width="100"></t:dgCol>
    <t:dgCol title="技术编码"  field="jsbm" width="100"></t:dgCol>
    <t:dgCol title="其它编码"  field="qtbm" width="100"></t:dgCol>
    <t:dgCol title="合同号"  field="yx" width="100" extendParams="styler:fmtype"></t:dgCol>
    <t:dgCol title="合同号"  field="hth"  hidden="true" width="100"></t:dgCol>
    <t:dgCol title="业务序号"  field="ywxh"  hidden="true" ></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true" ></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true" ></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate"  hidden="true" ></t:dgCol>
    <t:dgCol title="更新人名称"  field="updateName"  hidden="true" ></t:dgCol>
    <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true" ></t:dgCol>
    <t:dgCol title="更新日期"  field="updateDate"  hidden="true" ></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true" ></t:dgCol>
    <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true" ></t:dgCol>
    <t:dgCol title="流程状态"  field="bpmStatus"  hidden="true" ></t:dgCol>
    <t:dgCol title="是否删除"  field="deleteFlag"  hidden="true" queryMode="single" query="true" dictionary="dev_flag" defaultVal="0"></t:dgCol>
    <t:dgToolBar operationCode="add" title="录入" icon="icon-add"  funname="addRow"></t:dgToolBar>
    <t:dgToolBar operationCode="edit" title="编辑" icon="icon-edit"  funname="editRow"></t:dgToolBar>
    <t:dgToolBar operationCode="save" title="保存" icon="icon-save" url="jcfcContractController.do?saveRows" funname="saveData"></t:dgToolBar>
    <t:dgToolBar operationCode="undo" title="取消编辑" icon="icon-undo" funname="reject"></t:dgToolBar>
    <t:dgToolBar operationCode="del" title="批量删除"  icon="icon-remove" url="jcfcContractController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
    <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript" src="plug-in/mutitables/mutitables.urd.js"></script>
 <script type="text/javascript">
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
		    if(rows[i].jcje || rows[i].rjje || rows[i].gcje || rows[i].fwje || rows[i].jsje || rows[i].qtje){
				for(var d in rows[i]){
					result["jcfcContractList["+i+"]."+d]=rows[i][d];
				}
		    }else{
		        tip("金额至少填写一项")
				return false;
		    }
		}
		$.ajax({
			url:"<%=basePath%>/"+addurl,
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
				var editors =$('#'+gname).datagrid('getEditors', i);
				if(editors.length>0){
				    if(editors[5].target.val() || editors[6].target.val() || editors[7].target.val() || 
				            editors[8].target.val() || editors[9].target.val() || editors[10].target.val()){
				        $('#'+gname).datagrid('endEdit', i);
				    }else{
				        tip("金额至少要填写一项")
						return false;
				    }
				}else{
				    $('#'+gname).datagrid('endEdit', i);
				}
			}else{
			    tip("请填写必填项(带有红色三角形状的字段)!");
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
	//导出
	function ExportXls() {
		JeecgExcelExport("jcfcContractController.do?exportXls","jcfcContractrowList");
	}
	//列表字段颜色 ,逻辑判断函数 //可添加更多CSS样式
	function fmtype(val,row,index){
	    var s1 = 'background-color:red;color:#FFF;';
	    var s2 = 'background-color:#3a87ad;color:#FFF;';
	    if (val =='') {
	        return s1
	    }
	    return s2
	}
 </script>