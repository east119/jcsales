package com.jeecg.restful;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.annotation.JAuth;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.enums.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/spaApi")
@JAuth(auth=Permission.SKIP_AUTH)
public class SpaApi extends BaseController {
	static List<Map<String, Object>> testData = new ArrayList<Map<String, Object>>();
	static {
		
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("id", "1");
		mp.put("name", "Mr. Nice");
		
		Map<String, Object> mp2 = new HashMap<String, Object>();
		mp2.put("id", "2");
		mp2.put("name", "Narco");
		
		Map<String, Object> mp3 = new HashMap<String, Object>();
		mp3.put("id", "3");
		mp3.put("name", "scott");
		
		Map<String, Object> mp4 = new HashMap<String, Object>();
		mp4.put("id", "4");
		mp4.put("name", "Tornado");
		
		Map<String, Object> mp6 = new HashMap<String, Object>();
		mp6.put("id", "6");
		mp6.put("name", "dog");
		
		testData.add(mp);
		testData.add(mp2);
		testData.add(mp3);
		testData.add(mp4);
		testData.add(mp6);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> get(HttpServletRequest request, HttpServletResponse response) {
		return testData;
	}
	
	
	@RequestMapping(value = "/one/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getById(@PathVariable("id") String id,HttpServletRequest request, HttpServletResponse response) {
		for(Map<String, Object> mp :testData){
			if(id.equals(mp.get("id"))){
				return mp;
			}
		}
		return null;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public void post(@RequestBody Map<String,Object> hero,HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("POST" + request.getPathInfo());
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public void put(@RequestBody Map<String,Object> hero,HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("PUT" + request.getPathInfo());
		System.out.println("PUT" + hero.get("name").toString());
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	public void delete(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("DELETE" +  request.getPathInfo());
	}
	

	
}
