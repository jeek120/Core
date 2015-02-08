package com.xxfff.core.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.xxfff.core.model.BaseEntity;
import com.xxfff.core.util.StringEscapeEditor;

/**
 * 基础控制器
 * 
 * 其他控制器继承此控制器获得日期字段类型转换和防止XSS攻击的功能
 * 
 * @author 袁鹏
 * 
 */
@Controller
public class BaseController {
	
	@Autowired
	private HttpServletRequest request;

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

		/**
		 * 防止XSS攻击
		 */
		//binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
	}
	
	public Long getLoginId(){
		Long id = (Long)request.getSession().getAttribute("loginId");
		return id == null ? 0:id;
	}
	
	public boolean isLogin(){
		return getLoginId() == 0 ? false:true;
	}
	
	public String getRemoteIpAddr() { 
	       String ip = request.getHeader("x-forwarded-for"); 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("Proxy-Client-IP"); 
	       }
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getHeader("WL-Proxy-Client-IP"); 
	       } 
	       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	           ip = request.getRemoteAddr(); 
	       } 
	       return ip; 
	   } 
	
	public void preEntity(BaseEntity e){
		//if(StringUtils.isEmpty(e.getId())){
		if(null == e.getId()) { 
			e.setCreatIp(getRemoteIpAddr());
			e.setCreatTime(new Date());
			e.setCreator(Long.valueOf(getLoginId()));
		}
		e.setOperatIp(getRemoteIpAddr());
		e.setOperatTime(new Date());
		e.setOperator(Long.valueOf(getLoginId()));
	}
	
	public String getTheme(){
		return request.getSession().getAttribute("theme").toString();
	}
	
	public String getBasePath(){
    	String path = request.getContextPath();  
    	return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    }

	/**
	 * 用户跳转JSP页面
	 * 
	 * 此方法不考虑权限控制
	 * 
	 * @param folder
	 *            路径
	 * @param jspName
	 *            JSP名称(不加后缀)
	 * @return 指定JSP页面
	 */
//	@RequestMapping("/{folder}/{jspName}")
//	public String redirectJsp(@PathVariable String folder, @PathVariable String jspName) {
//		return "/" + folder + "/" + jspName;
//	}

}
