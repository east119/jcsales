package com.jeecg.jcfc.service;
import com.jeecg.jcfc.entity.JcfcContractEntity;
import org.jeecgframework.core.common.service.CommonService;

import java.io.Serializable;

public interface JcfcContractServiceI extends CommonService{
	
 	public void delete(JcfcContractEntity entity) throws Exception;
 	
 	public Serializable save(JcfcContractEntity entity) throws Exception;
 	
 	public void saveOrUpdate(JcfcContractEntity entity) throws Exception;
 	
}
