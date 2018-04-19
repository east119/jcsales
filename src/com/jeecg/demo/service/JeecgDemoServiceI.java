package com.jeecg.demo.service;
import com.jeecg.demo.entity.JeecgDemoEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface JeecgDemoServiceI extends CommonService{
	
 	public void delete(JeecgDemoEntity entity) throws Exception;
 	
 	public Serializable save(JeecgDemoEntity entity) throws Exception;
 	
 	public void saveOrUpdate(JeecgDemoEntity entity) throws Exception;
// add-begin--Author:weict  Date:20170609 for：TASK #2087 【demo】springjdbc demo-------------------- 
 	public void jdbcBatchSave() throws Exception;
 	
	public void jdbcProcedure() throws Exception;
// add-end--Author:weict  Date:20170609 for：TASK #2087 【demo】springjdbc demo---------------------- 

}
