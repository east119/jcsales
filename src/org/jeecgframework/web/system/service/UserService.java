package org.jeecgframework.web.system.service;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.system.pojo.base.TSUser;
/**
 * 
 * @author  张代浩
 *
 */
public interface UserService extends CommonService{

	public TSUser checkUserExits(TSUser user);
	public TSUser checkUserExits(String username,String password);
	public String getUserRole(TSUser user);
	public void pwdInit(TSUser user, String newPwd);
	/**
	 * 判断这个角色是不是还有用户使用
	 *@Author JueYue
	 *@date   2013-11-12
	 *@param id
	 *@return
	 */
	public int getUsersOfThisRole(String id);
	
	/**
	 * 物理删除用户
	 * @param user
	 */
	public String trueDel(TSUser user);
	
	//update--begin-- author:Yandong -- date:20180115-- for:TASK #2494 【改造】Jeecg 代码事务不严谨，control的逻辑改到service里面---
	/**
	 * 添加或者修改用户，添加用户组织机构关联表，用户角色关联表
	 * @param user
	 * @param orgIds
	 * @param roleIds
	 */
	public void saveOrUpdate(TSUser user, String[] orgIds, String[] roleIds);
	//update--end-- author:Yandong -- date:20180115-- for:TASK #2494 【改造】Jeecg 代码事务不严谨，control的逻辑改到service里面---
}
