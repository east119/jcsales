package com.jeecg.jcfc.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 合同号
 * @author onlineGenerator
 * @date 2018-09-28 20:38:27
 * @version V1.0   
 *
 */
@Entity
@Table(name = "jcfc_contract", schema = "")
@SuppressWarnings("serial")
public class JcfcContractEntity implements java.io.Serializable {
	/**id*/
	private java.lang.String id;
	/**项目名称*/
	@Excel(name="项目名称",width=15)
	private java.lang.String xxmc;
	/**业务经理*/
	@Excel(name="业务经理",width=15,dictTable ="jcfc_person",dicCode ="ywbm",dicText ="ywmc")
	private java.lang.String ywjl;
	/**合同年份*/
	@Excel(name="合同年份",width=15)
	private java.lang.String htnf;
	/**合同编码*/
	@Excel(name="合同编码",width=15)
	private java.lang.String htbm;
	/**申请日期*/
	@Excel(name="申请日期",width=15,format = "yyyy-MM-dd")
	private java.util.Date sqrq;
	/**集成金额*/
//	@Excel(name="集成金额",width=15)
	private java.lang.String jcje;
	/**软件金额*/
//	@Excel(name="软件金额",width=15)
	private java.lang.String rjje;
	/**工程金额*/
//	@Excel(name="工程金额",width=15)
	private java.lang.String gcje;
	/**服务金额*/
//	@Excel(name="服务金额",width=15)
	private java.lang.String fwje;
	/**技术金额*/
//	@Excel(name="技术金额",width=15)
	private java.lang.String jsje;
	/**其它金额*/
//	@Excel(name="其它金额",width=15)
	private java.lang.String qtje;
	/**合计金额*/
	private java.lang.String hjje;
	/**集成编码*/
	@Excel(name="集成编码",width=15)
	private java.lang.String jcbm;
	/**软件编码*/
	@Excel(name="软件编码",width=15)
	private java.lang.String rjbm;
	/**工程编码*/
	@Excel(name="工程编码",width=15)
	private java.lang.String gcbm;
	/**服务编码*/
	@Excel(name="服务编码",width=15)
	private java.lang.String fwbm;
	/**技术编码*/
	@Excel(name="技术编码",width=15)
	private java.lang.String jsbm;
	/**其它编码*/
	@Excel(name="其它编码",width=15)
	private java.lang.String qtbm;
	/**优选*/
	@Excel(name="合同号",width=15)
	private java.lang.String yx;
	/**合同号*/
	private java.lang.String hth;
	/**业务序号*/
	private java.lang.String ywxh;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**流程状态*/
	private java.lang.String bpmStatus;
	/**删除标记*/
	private java.lang.Integer deleteFlag;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  id
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  id
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  项目名称
	 */

	@Column(name ="XXMC",nullable=false,length=255)
	public java.lang.String getXxmc(){
		return this.xxmc;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  项目名称
	 */
	public void setXxmc(java.lang.String xxmc){
		this.xxmc = xxmc;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务经理
	 */

	@Column(name ="YWJL",nullable=false,length=10)
	public java.lang.String getYwjl(){
		return this.ywjl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务经理
	 */
	public void setYwjl(java.lang.String ywjl){
		this.ywjl = ywjl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同年份
	 */

	@Column(name ="HTNF",nullable=false,length=4)
	public java.lang.String getHtnf(){
		return this.htnf;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同年份
	 */
	public void setHtnf(java.lang.String htnf){
		this.htnf = htnf;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同编码
	 */

	@Column(name ="HTBM",nullable=true,length=32)
	public java.lang.String getHtbm(){
		return this.htbm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同编码
	 */
	public void setHtbm(java.lang.String htbm){
		this.htbm = htbm;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  申请日期
	 */
	@Temporal(TemporalType.DATE)
	@Column(name ="SQRQ",nullable=true)
	public java.util.Date getSqrq(){
		return this.sqrq;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  申请日期
	 */
	public void setSqrq(java.util.Date sqrq){
		this.sqrq = sqrq;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  集成金额
	 */

	@Column(name ="JCJE",nullable=true,length=32)
	public java.lang.String getJcje(){
		return this.jcje;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  集成金额
	 */
	public void setJcje(java.lang.String jcje){
		this.jcje = jcje;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  软件金额
	 */

	@Column(name ="RJJE",nullable=true,length=32)
	public java.lang.String getRjje(){
		return this.rjje;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  软件金额
	 */
	public void setRjje(java.lang.String rjje){
		this.rjje = rjje;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工程金额
	 */

	@Column(name ="GCJE",nullable=true,length=32)
	public java.lang.String getGcje(){
		return this.gcje;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工程金额
	 */
	public void setGcje(java.lang.String gcje){
		this.gcje = gcje;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务金额
	 */

	@Column(name ="FWJE",nullable=true,length=32)
	public java.lang.String getFwje(){
		return this.fwje;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务金额
	 */
	public void setFwje(java.lang.String fwje){
		this.fwje = fwje;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  技术金额
	 */

	@Column(name ="JSJE",nullable=true,length=32)
	public java.lang.String getJsje(){
		return this.jsje;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  技术金额
	 */
	public void setJsje(java.lang.String jsje){
		this.jsje = jsje;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  其它金额
	 */

	@Column(name ="QTJE",nullable=true,length=32)
	public java.lang.String getQtje(){
		return this.qtje;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  其它金额
	 */
	public void setQtje(java.lang.String qtje){
		this.qtje = qtje;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合计金额
	 */

	@Column(name ="HJJE",nullable=true,length=32)
	public java.lang.String getHjje(){
		return this.hjje;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合计金额
	 */
	public void setHjje(java.lang.String hjje){
		this.hjje = hjje;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  集成编码
	 */

	@Column(name ="JCBM",nullable=true,length=32)
	public java.lang.String getJcbm(){
		return this.jcbm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  集成编码
	 */
	public void setJcbm(java.lang.String jcbm){
		this.jcbm = jcbm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  软件编码
	 */

	@Column(name ="RJBM",nullable=true,length=32)
	public java.lang.String getRjbm(){
		return this.rjbm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  软件编码
	 */
	public void setRjbm(java.lang.String rjbm){
		this.rjbm = rjbm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工程编码
	 */

	@Column(name ="GCBM",nullable=true,length=32)
	public java.lang.String getGcbm(){
		return this.gcbm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工程编码
	 */
	public void setGcbm(java.lang.String gcbm){
		this.gcbm = gcbm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  服务编码
	 */

	@Column(name ="FWBM",nullable=true,length=32)
	public java.lang.String getFwbm(){
		return this.fwbm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  服务编码
	 */
	public void setFwbm(java.lang.String fwbm){
		this.fwbm = fwbm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  技术编码
	 */

	@Column(name ="JSBM",nullable=true,length=32)
	public java.lang.String getJsbm(){
		return this.jsbm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  技术编码
	 */
	public void setJsbm(java.lang.String jsbm){
		this.jsbm = jsbm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  其它编码
	 */

	@Column(name ="QTBM",nullable=true,length=32)
	public java.lang.String getQtbm(){
		return this.qtbm;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  其它编码
	 */
	public void setQtbm(java.lang.String qtbm){
		this.qtbm = qtbm;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  优选
	 */

	@Column(name ="YX",nullable=true,length=32)
	public java.lang.String getYx(){
		return this.yx;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  优选
	 */
	public void setYx(java.lang.String yx){
		this.yx = yx;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同号
	 */

	@Column(name ="HTH",nullable=true,length=32)
	public java.lang.String getHth(){
		return this.hth;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同号
	 */
	public void setHth(java.lang.String hth){
		this.hth = hth;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  业务序号
	 */

	@Column(name ="YWXH",nullable=true,length=5)
	public java.lang.String getYwxh(){
		return this.ywxh;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  业务序号
	 */
	public void setYwxh(java.lang.String ywxh){
		this.ywxh = ywxh;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */

	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */

	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */

	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */

	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */

	@Column(name ="UPDATE_DATE",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属部门
	 */

	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */

	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  流程状态
	 */

	@Column(name ="BPM_STATUS",nullable=true,length=32)
	public java.lang.String getBpmStatus(){
		return this.bpmStatus;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  流程状态
	 */
	public void setBpmStatus(java.lang.String bpmStatus){
		this.bpmStatus = bpmStatus;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  删除标记
	 */

	@Column(name ="DELETE_FLAG",nullable=true,length=10)
	public java.lang.Integer getDeleteFlag(){
		return this.deleteFlag;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  删除标记
	 */
	public void setDeleteFlag(java.lang.Integer deleteFlag){
		this.deleteFlag = deleteFlag;
	}
	
	
    private List<JcfcContractEntity> jcfcContractList ;
	
    @Transient
	public List<JcfcContractEntity> getJcfcContractList() {
		return jcfcContractList;
	}

	public void setJcfcContractList(List<JcfcContractEntity> jcfcContractList) {
		this.jcfcContractList = jcfcContractList;
	}
}
