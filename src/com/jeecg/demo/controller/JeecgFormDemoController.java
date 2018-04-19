package com.jeecg.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.UploadFile;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.ComboTree;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.common.model.json.TreeGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.HttpRequest;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.core.util.MutiLangUtil;
import org.jeecgframework.core.util.MyClassLoader;
import org.jeecgframework.core.util.NumberComparator;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.tag.vo.easyui.ComboTreeModel;
import org.jeecgframework.tag.vo.easyui.TreeGridModel;
import org.jeecgframework.web.system.pojo.base.TSAttachment;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSFunction;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.demo.dao.JeecgMinidaoDao;
import com.jeecg.demo.entity.TSDocument;

/**
 * @ClassName: JeecgFormDemoController
 * @Description: TODO(演示例子处理类)
 * @author jeecg
 */
@Controller
@RequestMapping("/jeecgFormDemoController")
public class JeecgFormDemoController extends BaseController {
	private static final Logger logger = Logger.getLogger(JeecgFormDemoController.class);
	
	@Autowired
	private SystemService systemService;
	@Autowired
	private JeecgMinidaoDao jeecgMinidaoDao;
	
	@RequestMapping(params = "uitag")
	public ModelAndView uitag(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/form_uitag");
	}

	//add--begin--author:caoez Date:20180226 for:TASK #2536 【新功能】二维码生成功能
	@RequestMapping(params = "qrcode")
	public ModelAndView qrcode(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/form_QRCode");
	}
	//add--end--author:caoez Date:20180226 for:TASK #2536 【新功能】二维码生成功能

	@RequestMapping(params = "formValidDemo")
	public ModelAndView formValidDemo(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/form_valid");
	}

	@RequestMapping(params = "testsubmit=1",method ={RequestMethod.GET, RequestMethod.POST})
	public ModelAndView testsubmit(HttpServletRequest request) {
		logger.info("请求成功byebye");
		return new ModelAndView("com/jeecg/demo/form_valid");
	}
	
	@RequestMapping(params = "nature")
	public ModelAndView easyDemo(HttpServletRequest request) {
		logger.info("demo-nature");
		//ztree同步加载
		JSONArray jsonArray=JSONArray.fromObject(getZtreeData());
		request.setAttribute("regions", jsonArray.toString().replaceAll("pid","pId"));
		return new ModelAndView("com/jeecg/demo/form_nature");
	}
	//update-begin--Author:dangzhenghui  Date:20170408 for：TASK #1836 【demo】动态创建多tab demo，参考截图效果
	@RequestMapping(params = "ueditor")
	public ModelAndView ueditor(HttpServletRequest request) {
		logger.info("ueditor");
		return new ModelAndView("com/jeecg/demo/ueditor");
	}
	//update-end--Author:dangzhenghui  Date:20170408 for：TASK #1836 【demo】动态创建多tab demo，参考截图效果

	//update-begin--Author:xuelin  Date:20170822 for：TASK #2281 【demo】popup赋多个值 demo-------------------
	@RequestMapping(params = "popupMultiValue")
	public ModelAndView popupMultiValue(HttpServletRequest request) {
		logger.info("popupMultiValue");
		return new ModelAndView("com/jeecg/demo/form_popupMultiValue");
	}
	//update-end--Author:xuelin  Date:20170822 for：TASK #2281 【demo】popup赋多个值 demo----------------------

	/**
	 *下拉联动数据---省市区
	 */
	@RequestMapping(params="regionSelect",method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, String>> cityselect(HttpServletRequest req) throws Exception{
		logger.info("----省市区联动-----");
		String pid=req.getParameter("pid");
		List<Map<String, String>> list=jeecgMinidaoDao.getProCity(pid);
		return jeecgMinidaoDao.getProCity(pid);
	}
	
	/**
	 * Ztree
	 * 获取所有的省市区数据
	 * @return
	 */
	public List<Map<String, String>> getZtreeData(){
		return jeecgMinidaoDao.getAllRegions();
	}
	
	/**
	 * 父级DEMO下拉菜单
	 */
	@RequestMapping(params = "getComboTreeData")
	@ResponseBody
	public List<ComboTree> getComboTreeData(HttpServletRequest request, ComboTree comboTree) {
		CriteriaQuery cq = new CriteriaQuery(TSDepart.class);
		if (comboTree.getId() != null) {
			cq.eq("TSPDepart.id", comboTree.getId());
		}
		if (comboTree.getId() == null) {
			cq.isNull("TSPDepart");
		}
		cq.add();
		List<TSDepart> demoList = systemService.getListByCriteriaQuery(cq, false);
		List<ComboTree> comboTrees = new ArrayList<ComboTree>();
		ComboTreeModel comboTreeModel = new ComboTreeModel("id", "departname", "TSDeparts");
		comboTrees = systemService.ComboTree(demoList, comboTreeModel, null, false);
		return comboTrees;
	}
	
	/**
	 * 加载ztree
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(params="getTreeData",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson getTreeData(TSDepart depatr,HttpServletResponse response,HttpServletRequest request ){
		AjaxJson j = new AjaxJson();
		try{
			List<TSDepart> depatrList = new ArrayList<TSDepart>();
			StringBuffer hql = new StringBuffer(" from TSDepart t");
			//hql.append(" and (parent.id is null or parent.id='')");
			depatrList = this.systemService.findHql(hql.toString());
			List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = null;
			for (TSDepart tsdepart : depatrList) {
				String sqls = null;
				Object[] paramss = null;
				map = new HashMap<String,Object>();
				map.put("id", tsdepart.getId());
				map.put("name", tsdepart.getDepartname());
				if (tsdepart.getTSPDepart() != null) {
					map.put("pId", tsdepart.getTSPDepart().getId());
					map.put("open",false);
				}else {
					map.put("pId", "1");
					map.put("open",false);
				}
				sqls = "select count(1) from t_s_depart t where t.parentdepartid = ?";
				paramss = new Object[]{tsdepart.getId()};
				long counts = this.systemService.getCountForJdbcParam(sqls, paramss);
				if(counts>0){
					dataList.add(map);
				}else{
					TSDepart de = this.systemService.get(TSDepart.class, tsdepart.getId());
					if (de != null) {
						map.put("id", de.getId());
						map.put("name", de.getDepartname());
						if(tsdepart.getTSPDepart()!=null){
							map.put("pId", tsdepart.getTSPDepart().getId());
							map.put("open",false);
						}else{
							map.put("pId", "1");
							map.put("open",false);
						}
						dataList.add(map);
					}else{
						map.put("open",false);
						dataList.add(map);
					}
				}
			}
		j.setObj(dataList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return j;
	}
	
	
	/**
	 * 自动完成请求返回数据
	 * @param request
	 * @param responss
	 */
	@RequestMapping(params = "getAutocompleteData",method ={RequestMethod.GET, RequestMethod.POST})
	public void getAutocompleteData(HttpServletRequest request, HttpServletResponse response) {
		//update-begin-author:taoYan date:20170803 for:参数名修改------
		String searchVal = request.getParameter("q");
		//update-end-author:taoYan date:20170803 for:参数名修改------
		String hql = "from TSUser where userName like '%"+searchVal+"%'";
		List autoList = systemService.findHql(hql);
		try {
			response.setContentType("application/json;charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(JSONHelper.listtojson(new String[]{"userName"},1,autoList));
            response.getWriter().flush();
		} catch (Exception e1) {
			e1.printStackTrace();
		}finally{
			try {
				response.getWriter().close();
			} catch (IOException e) {
			}
		}

	}
	//update-begin--Author:dangzhenghui  Date:20170429 for：TASK #1904 【demo】电子签章
	@RequestMapping(params = "eSign")
	public ModelAndView eSignDemo(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/zsign");
	}
	//update-end--Author:dangzhenghui  Date:20170429 for：TASK #1904 【demo】电子签章

	//add-begin--Author:xuelin  Date:20170503 for：#1903 【demo】左右布局demo--------------------
	@RequestMapping(params = "siteSelect")
	public ModelAndView siteSelect(HttpServletRequest request) {
		logger.info("----左右布局 demo转入页面-----");
		return new ModelAndView("com/jeecg/demo/siteSelect");
	}	
	//add-end--Author:xuelin  Date:20170503 for：#1903 【demo】左右布局demo----------------------

	//add-begin--Author:xuelin  Date:20170506 for：TASK #1902 【demo】上下特殊布局--------------------	
	/**
	 * 上下特殊布局
	 */
	@RequestMapping(params = "specialLayout")
	public ModelAndView rowListDemo(HttpServletRequest request) {
		logger.info("----上下特殊布局 demo转入页面-----");
		return new ModelAndView("com/jeecg/demo/specialLayout");
	}
	//add-end--Author:xuelin  Date:20170506 for：TASK #1902 【demo】上下特殊布局----------------------
	
	
	//update--begin--author:zhangjiaqiang date:20170618 for:通用上传demo
	@RequestMapping(params = "commonUpload")
	public ModelAndView commonUploadDemo(){
		return new ModelAndView("system/commonupload/commonUploadFile");
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(params = "saveUploadFile")
	@ResponseBody
	public AjaxJson saveUploadFile(String documentTitle,String filename,String swfpath){
		AjaxJson ajaxJson = new AjaxJson();
		try {
			//update--begin--author:zhangjiaqiang date:20170914 for:未上传文件报错
			if(StringUtil.isEmpty(filename)){
				ajaxJson.setSuccess(false);
				ajaxJson.setMsg("未上传文件");
				return ajaxJson;
			}
			//update--end--author:zhangjiaqiang date:20170914 for:未上传文件报错
			TSTypegroup tsTypegroup=systemService.getTypeGroup("fieltype","文档分类");
			TSType tsType = systemService.getType("files","附件", tsTypegroup);
			TSDocument document = new TSDocument();
			document.setDocumentTitle(documentTitle);
			document.setRealpath(filename);
			document.setSubclassname(MyClassLoader.getPackPath(document));
			document.setCreatedate(DateUtils.gettimestamp());
			document.setTSType(tsType);
			document.setSwfpath(swfpath);
			//update--begin--author:zhangjiaqiang date:20170621 for:修订通用文件上传之后，下载和预览异常
			String fileName = filename.substring(filename.lastIndexOf("/")+1,filename.lastIndexOf("."));
			document.setAttachmenttitle(fileName);
			document.setExtend(filename.substring(filename.lastIndexOf(".") + 1));
			//update--end--author:zhangjiaqiang date:20170621 for:修订通用文件上传之后，下载和预览异常
			systemService.save(document);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxJson.setSuccess(false);
			ajaxJson.setMsg("失败："+e.getMessage());
		}
		return ajaxJson;
	}
	
	
	
	//update--end--author:zhangjiaqiang date:20170618 for：通用上传demo
	
	
	
	//update-begin--Author:dangzhenghui  Date:20170524 for：TASK #1901 【demo】文档管理demo


	/**
	 * 文件添加跳转
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "addFiles")
	public ModelAndView addFiles(HttpServletRequest req) {
		return new ModelAndView("system/document/files");
	}

	//update--begin--author:zhangjiaqiang date:20170911 for:文档管理编辑
	/**
	 * 文件编辑跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "editFiles")
	public ModelAndView editFiles(TSDocument doc, ModelMap map,HttpServletRequest request) {
		if (StringUtil.isNotEmpty(doc.getId())) {
			doc = systemService.getEntity(TSDocument.class, doc.getId());
			map.put("doc", doc);
			TSAttachment attachment = systemService.get(TSAttachment.class, doc.getId());
			map.put("attachment",attachment);
		}
		return new ModelAndView("system/document/files");
	}
	//update--end--author:zhangjiaqiang date:20170911 for:文档管理编辑
	/**
	 * 保存文件
	 *
	 * @param document
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "saveFiles", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson saveFiles(HttpServletRequest request, HttpServletResponse response, TSDocument document) {
		AjaxJson j = new AjaxJson();
		Map<String, Object> attributes = new HashMap<String, Object>();
		TSTypegroup tsTypegroup=systemService.getTypeGroup("fieltype","文档分类");
		TSType tsType = systemService.getType("files","附件", tsTypegroup);
		//update--begin--author:zhangjiaqiang date:20170914 for:文档管理编辑
		String documentId = oConvertUtils.getString(request.getParameter("documentId"));// 文件ID
		String documentTitle = oConvertUtils.getString(request.getParameter("documentTitle"));// 文件标题
		if (StringUtil.isNotEmpty(documentId)) {
			document.setId(documentId);
			document = systemService.getEntity(TSDocument.class, documentId);
			document.setDocumentTitle(documentTitle);
		}
		//update--end--author:zhangjiaqiang date:20170914 for:文档管理编辑
		document.setSubclassname(MyClassLoader.getPackPath(document));
		document.setCreatedate(DateUtils.gettimestamp());
		document.setTSType(tsType);
		UploadFile uploadFile = new UploadFile(request, document);
		uploadFile.setCusPath("files");
		//设置weboffice转化【不设置该字段，则不做在线预览转化】
		uploadFile.setSwfpath("swfpath");
		document = systemService.uploadFile(uploadFile);
		attributes.put("url", document.getRealpath());
		attributes.put("fileKey", document.getId());
		attributes.put("name", document.getAttachmenttitle());
		attributes.put("viewhref", "commonController.do?objfileList&fileKey=" + document.getId());
		attributes.put("delurl", "commonController.do?delObjFile&fileKey=" + document.getId());
		j.setMsg("文件添加成功");
		j.setAttributes(attributes);
		return j;
	}
	
	/**
	 * 新闻法规文件列表
	 */
	@RequestMapping(params = "documentList")
	public void documentList(HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSDocument.class, dataGrid);
		String typecode = oConvertUtils.getString(request.getParameter("typecode"));
		cq.createAlias("TSType", "TSType");
		cq.eq("TSType.typecode", typecode);
		cq.add();
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除文档
	 *
	 * @param document
	 * @return
	 */
	@RequestMapping(params = "delDocument")
	@ResponseBody
	public AjaxJson delDocument(TSDocument document, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		document = systemService.getEntity(TSDocument.class, document.getId());
		message = "" + document.getDocumentTitle() + "被删除成功";
		systemService.delete(document);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);
		j.setSuccess(true);
		j.setMsg(message);
		return j;
	}
	//update-end--Author:dangzhenghui  Date:20170524 for：TASK #1901 【demo】文档管理demo
	//update-begin--Author:dangzhenghui  Date:20170531 for：TASK #2038 【demo】树形列表 分页demo
	/**
	 * 权限列表
	 */
	@RequestMapping(params = "functionGrid")
	@ResponseBody
	public  Object functionGrid(HttpServletRequest request,TreeGrid treegrid, Integer type,HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TSFunction.class,dataGrid);
		boolean pageflag=true;
		String selfId = request.getParameter("selfId");
		if (selfId != null) {
			cq.notEq("id", selfId);
		}
		if (treegrid.getId() != null) {
			pageflag=false;
			cq.eq("TSFunction.id", treegrid.getId());
		}
		if (treegrid.getId() == null) {
			cq.isNull("TSFunction");
		}
		if(type != null){
			cq.eq("functionType", type.shortValue());
		}
		cq.addOrder("functionOrder", SortDirection.asc);
		cq.add();

		//update--begin------author:scott--------------date:20151208-----------for:手工加载数据权限条件--------
		//获取装载数据权限的条件HQL
		cq = HqlGenerateUtil.getDataAuthorConditionHql(cq, new TSFunction());
		cq.add();
		//update--end------author:scott--------------date:20151208-----------for:手工加载数据权限条件--------

		List<TSFunction> functionList = systemService.getListByCriteriaQuery(cq, pageflag);
		Long total=systemService.getCountForJdbc("select count(*) from t_s_function where functionlevel=0");
//        update-start-Author:zhangguoming  Date:20140914 for：菜单管理页面：菜单排序
		Collections.sort(functionList, new NumberComparator());
//        update-end-Author:zhangguoming  Date:20140914 for：菜单管理页面：菜单排序
		List<TreeGrid> treeGrids = new ArrayList<TreeGrid>();
		TreeGridModel treeGridModel = new TreeGridModel();
		treeGridModel.setIcon("TSIcon_iconPath");
		treeGridModel.setTextField("functionName");
		treeGridModel.setParentText("TSFunction_functionName");
		treeGridModel.setParentId("TSFunction_id");
		treeGridModel.setSrc("functionUrl");
		treeGridModel.setIdField("id");
		treeGridModel.setChildList("TSFunctions");
		// 添加排序字段
		treeGridModel.setOrder("functionOrder");
		//        update-begin--Author:chenj  Date:20160722 for：添加菜单图标样式
		treeGridModel.setIconStyle("functionIconStyle");
		//        update-end--Author:chenj  Date:20160722 for：添加菜单图标样式

		treeGridModel.setFunctionType("functionType");

		treeGrids = systemService.treegrid(functionList, treeGridModel);

		MutiLangUtil.setMutiTree(treeGrids);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("rows",treeGrids);
		jsonObject.put("total",total);
		if (pageflag){
			return jsonObject;
		}
		return treeGrids;

	}

	/**
	 * 权限列表页面跳转
	 *
	 * @return
	 */
	@RequestMapping(params = "function")
	public ModelAndView function(ModelMap model) {
		return new ModelAndView("com/jeecg/demo/functionList");
	}
	//update-end--Author:dangzhenghui  Date:20170531 for：TASK #2038 【demo】树形列表 分页demo
	
	//update-begin--Author:LiShaoQing Date:20170815 for：【demo】可排序多选demo
	
	/**
	 * 菜单进入可排序多选界面
	 */
	@RequestMapping(params = "selectSort")
	public ModelAndView selectSort() {
		return new ModelAndView("com/jeecg/demo/form_selectSort");
	}
	
	/**
	 * 跳转可排序多选用户选择界面
	 * @return
	 */
	@RequestMapping(params = "gridSelectdemo")
	public ModelAndView gridSelectdemo() {
		return new ModelAndView("com/jeecg/demo/gridSelectdemo");
	}
	
	/**
	 * 可排序多选界面查用户表放在Datagrid中
	 * @param user
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(params = "easyUIGrid", method = RequestMethod.POST)
	@ResponseBody
	public void getEasyUIGrid(TSUser user,HttpServletRequest request,HttpServletResponse response,DataGrid dataGrid)throws Exception{
		CriteriaQuery cq = new CriteriaQuery(TSUser.class, dataGrid);
        //查询条件组装器
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, user);
        Short[] userstate = new Short[]{Globals.User_Normal, Globals.User_ADMIN, Globals.User_Forbidden};
        cq.in("status", userstate);
        cq.eq("deleteFlag", Globals.Delete_Normal);
        cq.add();
        this.systemService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
	}
	//update-end--Author:LiShaoQing Date:20170815 for：【demo】可排序多选demo
	
	//update-begin--Author:zhangliang Date:20170823 for：TASK #2299 【jeecg demo】 ztree 实现一个可编辑的树
	@RequestMapping(params = "ztreeDemo")
	public ModelAndView ztreeDemo(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/demo/ztreeDemo");
	}
	
	
	@RequestMapping(params="getTreeDemoData",method ={RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public AjaxJson getTreeDemoData(TSDepart depatr,HttpServletResponse response,HttpServletRequest request ){
		AjaxJson j = new AjaxJson();
		try{
			List<TSDepart> depatrList = new ArrayList<TSDepart>();
			StringBuffer hql = new StringBuffer(" from TSDepart t");
			depatrList = this.systemService.findHql(hql.toString());
			List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
			Map<String,Object> map = null;
			for (TSDepart tsdepart : depatrList) {
				map = new HashMap<String,Object>();
				map.put("chkDisabled",false);
				map.put("click", true);
				map.put("id", tsdepart.getId());
				map.put("name", tsdepart.getDepartname());
				map.put("nocheck", false);
				map.put("struct","TREE");
				map.put("title",tsdepart.getDepartname());
				if (tsdepart.getTSPDepart() != null) {
					map.put("parentId",tsdepart.getTSPDepart().getId());
				}else {
					map.put("parentId","0");
				}
				dataList.add(map);
			}
		j.setObj(dataList);
		}catch(Exception e){
			e.printStackTrace();
		}
		return j;
	}
	
	/**
	 * 删除部门
	 * @param depart
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TSDepart depart, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		depart = systemService.getEntity(TSDepart.class, depart.getId());
        Long childCount = systemService.getCountForJdbc("select count(1) from t_s_depart where parentdepartid ='" + depart.getId() + "'");
        if(childCount>0){
        	j.setSuccess(false);
        	j.setMsg("有下级,不能删除");
        	return j;
        }
        systemService.executeSql("delete from t_s_role_org where org_id=?", depart.getId());
        //systemService.delete();
        j.setMsg("删除成功");
		return j;
	}
	//update-end--Author:zhangliang Date:20170823 for：TASK #2299 【jeecg demo】 ztree 实现一个可编辑的树

	//add-begin--Author:yugw  Date:20170910 for：TASK #2335 【UI例子】多tab例子--------------------	
	/**
	 * 多选项卡demo
	 */
	@RequestMapping(params = "tabsDemo")
	public ModelAndView tabsDemo(HttpServletRequest request) {
		logger.info("----多选项卡demo转入页面-----");
		return new ModelAndView("com/jeecg/demo/tabsDemo");
	}
	@RequestMapping(params = "tabDemo")
	public ModelAndView tabDemo(HttpServletRequest request) {
		logger.info("----选项卡demo转入页面-----");
		return new ModelAndView("com/jeecg/demo/tabDemo");
	}
	//add-end--Author:yugw  Date:20170910 for：TASK #2335 【UI例子】多tab例子----------------------
	
	//add-begin-Author:baiyu Date:2018-03-27 for:接口测试
	/**
	 * 常用示例Demo:接口测试页面跳转
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "interfaceTestDemo")
	public ModelAndView interfaceTestDemo(HttpServletRequest request) {
		logger.info("----接口测试demo转入页面-----");
		return new ModelAndView("com/jeecg/demo/form_interfaceTestDemo");
	}
	/**
	 * 常用示例Demo:接口测试
	 * @param request
	 * @param response
	 * @return AjaxJson
	 */
	@RequestMapping(params = "interfaceTest")
	@ResponseBody
	public AjaxJson testInterface(HttpServletRequest request,HttpServletResponse response) {
			 AjaxJson j=new AjaxJson();
		 try {
			 String serverUrl = request.getParameter("serverUrl");//请求的地址
			 String requestBody = request.getParameter("requestBody");//请求的参数
			 String requestMethod = request.getParameter("requestMethod");//请求的方式
				 if(requestMethod.equals("POST")){
					 if(requestBody !=""){
						 logger.info("----请求接口开始-----");
						 String sendPost = HttpRequest.sendPost(serverUrl, requestBody);
						 logger.info("----请求接口结束-----"+sendPost);
						 j.setSuccess(true);
						 j.setObj(sendPost);
					 }else{
						 j.setSuccess(false);
						 j.setObj("请填写请求参数");
					 }
					 
				 }
				 if(requestMethod.equals("GET")){
					  logger.info("----请求接口开始-----");
					  String sendGet = HttpRequest.sendGet(serverUrl, requestBody);
					  logger.info("----请求接口结束-----"+sendGet);
					  j.setSuccess(true);
					  j.setObj(sendGet);
				 }
		} catch (Exception e) {
			j.setSuccess(false);
			j.setObj("服务器请求失败");
			e.printStackTrace();
		}
		return j;
	}
	//add-end-Author:baiyu Date:2018-03-27 for:接口测试
}
