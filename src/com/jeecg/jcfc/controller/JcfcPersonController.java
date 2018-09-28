package com.jeecg.jcfc.controller;
import com.jeecg.jcfc.entity.JcfcPersonEntity;
import com.jeecg.jcfc.service.JcfcPersonServiceI;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.apache.commons.collections.CollectionUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.common.TreeChildCount;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import java.io.OutputStream;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jeecgframework.core.util.ResourceUtil;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.Map;
import java.util.HashMap;
import org.jeecgframework.core.util.ExceptionUtil;

/**   
 * @Title: Controller  
 * @Description: 业务经理
 * @author onlineGenerator
 * @date 2018-09-27 11:22:24
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/jcfcPersonController")
public class JcfcPersonController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(JcfcPersonController.class);

	@Autowired
	private JcfcPersonServiceI jcfcPersonService;
	@Autowired
	private SystemService systemService;
	


	/**
	 * 业务经理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/jcfc/jcfcPersonList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(JcfcPersonEntity jcfcPerson,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(JcfcPersonEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jcfcPerson, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.jcfcPersonService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除业务经理
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(JcfcPersonEntity jcfcPerson, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		jcfcPerson = systemService.getEntity(JcfcPersonEntity.class, jcfcPerson.getId());
		message = "业务经理删除成功";
		try{
			jcfcPerson.setDeleteFlag(1);
			jcfcPersonService.saveOrUpdate(jcfcPerson);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "业务经理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除业务经理
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "业务经理删除成功";
		try{
			for(String id:ids.split(",")){
				JcfcPersonEntity jcfcPerson = systemService.getEntity(JcfcPersonEntity.class, id);
				jcfcPerson.setDeleteFlag(1);
				jcfcPersonService.saveOrUpdate(jcfcPerson);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "业务经理删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加业务经理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(JcfcPersonEntity jcfcPerson, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "业务经理添加成功";
		try{
			jcfcPersonService.save(jcfcPerson);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "业务经理添加失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 保存新增/更新的行数据
	 * @param page
	 * @return
	 */
	@RequestMapping(params = "saveRows")
	@ResponseBody
	public AjaxJson saveRows(JcfcPersonEntity page){
		String message = null;
		List<JcfcPersonEntity> jcfcPersonList=page.getJcfcPersonList();
		AjaxJson j = new AjaxJson();
		if(CollectionUtils.isNotEmpty(jcfcPersonList)){
			for(JcfcPersonEntity jcfcPerson:jcfcPersonList){
				if (StringUtil.isNotEmpty(jcfcPerson.getId())) {
					JcfcPersonEntity t =jcfcPersonService.get(JcfcPersonEntity.class, jcfcPerson.getId());
					try {
						message = "JcfcPerson例子更新成功";
						MyBeanUtils.copyBeanNotNull2Bean(jcfcPerson, t);
						jcfcPersonService.saveOrUpdate(t);
						systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						message = "JcfcPerson例子添加成功";
						jcfcPerson.setDeleteFlag(0);
						jcfcPersonService.save(jcfcPerson);
						systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		}
		return j;
	}
	
	/**
	 * 更新业务经理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(JcfcPersonEntity jcfcPerson, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "业务经理更新成功";
		JcfcPersonEntity t = jcfcPersonService.get(JcfcPersonEntity.class, jcfcPerson.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(jcfcPerson, t);
			jcfcPersonService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "业务经理更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 业务经理新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(JcfcPersonEntity jcfcPerson, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jcfcPerson.getId())) {
			jcfcPerson = jcfcPersonService.getEntity(JcfcPersonEntity.class, jcfcPerson.getId());
			req.setAttribute("jcfcPersonPage", jcfcPerson);
		}
		return new ModelAndView("com/jeecg/jcfc/jcfcPerson-add");
	}
	/**
	 * 业务经理编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(JcfcPersonEntity jcfcPerson, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jcfcPerson.getId())) {
			jcfcPerson = jcfcPersonService.getEntity(JcfcPersonEntity.class, jcfcPerson.getId());
			req.setAttribute("jcfcPersonPage", jcfcPerson);
		}
		return new ModelAndView("com/jeecg/jcfc/jcfcPerson-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","jcfcPersonController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(JcfcPersonEntity jcfcPerson,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(JcfcPersonEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jcfcPerson, request.getParameterMap());
		List<JcfcPersonEntity> jcfcPersons = this.jcfcPersonService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"业务经理");
		modelMap.put(NormalExcelConstants.CLASS,JcfcPersonEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("业务经理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,jcfcPersons);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(JcfcPersonEntity jcfcPerson,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"业务经理");
    	modelMap.put(NormalExcelConstants.CLASS,JcfcPersonEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("业务经理列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
    	"导出信息"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<JcfcPersonEntity> listJcfcPersonEntitys = ExcelImportUtil.importExcel(file.getInputStream(),JcfcPersonEntity.class,params);
				for (JcfcPersonEntity jcfcPerson : listJcfcPersonEntitys) {
					jcfcPersonService.save(jcfcPerson);
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(e.getMessage());
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}
	
	
}
