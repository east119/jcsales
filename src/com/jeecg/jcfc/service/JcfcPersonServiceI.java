package com.jeecg.jcfc.service;
import com.jeecg.jcfc.entity.JcfcPersonEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface JcfcPersonServiceI extends CommonService{
	
 	public void delete(JcfcPersonEntity entity) throws Exception;
 	
 	public Serializable save(JcfcPersonEntity entity) throws Exception;
 	
 	public void saveOrUpdate(JcfcPersonEntity entity) throws Exception;
 	
}
