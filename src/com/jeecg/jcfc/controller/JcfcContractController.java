package com.jeecg.jcfc.controller;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.jeecg.jcfc.entity.JcfcContractEntity;
import com.jeecg.jcfc.service.JcfcContractServiceI;

/**   
 * @Title: Controller  
 * @Description: 合同号
 * @author onlineGenerator
 * @date 2018-09-28 20:38:27
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/jcfcContractController")
public class JcfcContractController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(JcfcContractController.class);

	@Autowired
	private JcfcContractServiceI jcfcContractService;
	@Autowired
	private SystemService systemService;
	
	private String[] PREFIX = {"J","R","G","F","S","Q"};
	/**
	 * 合同号生成
	 * 编码规则：JRGFSQ+业务号+年份后2位+顺序号
	 * 编码成都：10位
	 * @return
	 */
	private String getCode(JcfcContractEntity contract){
		String code = null;
		double max = 0;
		double money = 0;
		
		if(StringUtil.isNotEmpty(contract.getJcje())){
			code = PREFIX[0];
			max = Double.parseDouble(contract.getJcje());
		}
		if(StringUtil.isNotEmpty(contract.getRjje())){
			money = Double.parseDouble(contract.getRjje());
			if(max == 0 || money > max){
				code = PREFIX[1];
				max = money;
			}
		}
		if(StringUtil.isNotEmpty(contract.getGcje())){
			money = Double.parseDouble(contract.getGcje());
			if(max == 0 || money > max){
				code = PREFIX[2];
				max = money;
			}
		}
		if(StringUtil.isNotEmpty(contract.getFwje())){
			money = Double.parseDouble(contract.getFwje());
			if(max == 0 || money > max){
				code = PREFIX[3];
				max = money;
			}
		}
		if(StringUtil.isNotEmpty(contract.getJsje())){
			money = Double.parseDouble(contract.getJsje());
			if(max == 0 || money > max){
				code = PREFIX[4];
				max = money;
			}
		}
		if(StringUtil.isNotEmpty(contract.getQtje())){
			money = Double.parseDouble(contract.getQtje());
			if(max == 0 || money > max){
				code = PREFIX[5];
				max = money;
			}
		}
		
		return code;
	}
	
	private String getOrder(JcfcContractEntity contract){
		String year = new SimpleDateFormat("yyyy",Locale.CHINESE).format(contract.getSqrq());
		String sql = "select count(1) from jcfc_contract t where t.hth is not null and t.ywjl = '" + contract.getYwjl() + "' and year(t.sqrq) = '" + year +"'";
		long counts = this.systemService.getCountForJdbcParam(sql) + 1;
		String orderStr = "";
		if(counts < 10){
			orderStr = "00" + counts;
		}else if(counts < 100){
			orderStr = "0" + counts;
		}else{
			orderStr = "" + counts;
		}
		return orderStr;
	}

	/**
	 * 合同号列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/jeecg/jcfc/jcfcContractList");
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
	public void datagrid(JcfcContractEntity jcfcContract,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(JcfcContractEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jcfcContract, request.getParameterMap());
		try{
		//自定义追加查询条件
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		this.jcfcContractService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除合同号
	 * 
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(JcfcContractEntity jcfcContract, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		jcfcContract = systemService.getEntity(JcfcContractEntity.class, jcfcContract.getId());
		message = "合同号删除成功";
		try{
			jcfcContract.setDeleteFlag(1);
			jcfcContractService.saveOrUpdate(jcfcContract);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "合同号删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 批量删除合同号
	 * 
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "合同号删除成功";
		try{
			for(String id:ids.split(",")){
				JcfcContractEntity jcfcContract = systemService.getEntity(JcfcContractEntity.class, id);
				jcfcContract.setDeleteFlag(1);
				jcfcContractService.saveOrUpdate(jcfcContract);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "合同号删除失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加合同号
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(JcfcContractEntity jcfcContract, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "合同号添加成功";
		try{
			jcfcContractService.save(jcfcContract);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "合同号添加失败";
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
	public AjaxJson saveRows(JcfcContractEntity page){
		String message = null;
		List<JcfcContractEntity> jcfcContractList=page.getJcfcContractList();
		AjaxJson j = new AjaxJson();
		if(CollectionUtils.isNotEmpty(jcfcContractList)){
			for(JcfcContractEntity jcfcContract:jcfcContractList){
				String code = getCode(jcfcContract);
				if (StringUtil.isNotEmpty(jcfcContract.getId())) {
					JcfcContractEntity t =jcfcContractService.get(JcfcContractEntity.class, jcfcContract.getId());
					try {
						message = "JcfcContract例子更新成功";
						if(StringUtil.isNotEmpty(code)){
							String ywjlOld = t.getYwjl();
							String yearOld = new SimpleDateFormat("yy",Locale.CHINESE).format(t.getSqrq());
							String hthOld = t.getHth();
							
							String ywjl = jcfcContract.getYwjl();
							String year = new SimpleDateFormat("yy",Locale.CHINESE).format(jcfcContract.getSqrq());							
							String orderStr = "";
							if(StringUtil.isNotEmpty(hthOld)&&ywjl.equals(ywjlOld)&&year.equals(yearOld)){//原合同号不为空  and 业务经理和年份没有 变化
								orderStr = hthOld.substring(7);
							}else{
								orderStr = getOrder(jcfcContract);
							}
							jcfcContract.setHth(code + ywjl + year + orderStr);
						}
						MyBeanUtils.copyBeanNotNull2Bean(jcfcContract, t);
						jcfcContractService.saveOrUpdate(t);
						systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					try {
						message = "JcfcContract例子添加成功";
						if(StringUtil.isNotEmpty(code)){
							String ywjl = jcfcContract.getYwjl();
							String year = new SimpleDateFormat("yy",Locale.CHINESE).format(jcfcContract.getSqrq());
							String orderStr = getOrder(jcfcContract);
							jcfcContract.setHth(code + ywjl + year + orderStr);
						}
						jcfcContract.setDeleteFlag(0);
						jcfcContractService.save(jcfcContract);
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
	 * 更新合同号
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(JcfcContractEntity jcfcContract, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "合同号更新成功";
		JcfcContractEntity t = jcfcContractService.get(JcfcContractEntity.class, jcfcContract.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(jcfcContract, t);
			jcfcContractService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "合同号更新失败";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}
	

	/**
	 * 合同号新增页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(JcfcContractEntity jcfcContract, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jcfcContract.getId())) {
			jcfcContract = jcfcContractService.getEntity(JcfcContractEntity.class, jcfcContract.getId());
			req.setAttribute("jcfcContractPage", jcfcContract);
		}
		return new ModelAndView("com/jeecg/jcfc/jcfcContract-add");
	}
	/**
	 * 合同号编辑页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(JcfcContractEntity jcfcContract, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jcfcContract.getId())) {
			jcfcContract = jcfcContractService.getEntity(JcfcContractEntity.class, jcfcContract.getId());
			req.setAttribute("jcfcContractPage", jcfcContract);
		}
		return new ModelAndView("com/jeecg/jcfc/jcfcContract-update");
	}
	
	/**
	 * 导入功能跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","jcfcContractController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(JcfcContractEntity jcfcContract,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(JcfcContractEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jcfcContract, request.getParameterMap());
		List<JcfcContractEntity> jcfcContracts = this.jcfcContractService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"合同号");
		modelMap.put(NormalExcelConstants.CLASS,JcfcContractEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("合同号列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
			"导出信息"));
		modelMap.put(NormalExcelConstants.DATA_LIST,jcfcContracts);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * 导出excel 使模板
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(JcfcContractEntity jcfcContract,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"合同号");
    	modelMap.put(NormalExcelConstants.CLASS,JcfcContractEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("合同号列表", "导出人:"+ResourceUtil.getSessionUser().getRealName(),
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
				List<JcfcContractEntity> listJcfcContractEntitys = ExcelImportUtil.importExcel(file.getInputStream(),JcfcContractEntity.class,params);
				for (JcfcContractEntity jcfcContract : listJcfcContractEntitys) {
					jcfcContractService.save(jcfcContract);
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
