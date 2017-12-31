/*
 * Powered By CES
 */

package com.ces.web.validate.service;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ces.web.validate.dao.ValidateUserDao;
import com.ces.web.validate.model.ValidateUser;


@Service
public class ValidateUserManager{
	@Autowired
	private ValidateUserDao validateUserDao;
	
	
	public void addUser(HttpServletRequest request) throws UnsupportedEncodingException{
		String userName = new String(request.getParameter("userName").getBytes("iso-8859-1"), "utf-8");  
		String userPasswd =  request.getParameter("userPasswd");
		String mdmm =  request.getParameter("mdmm");
		if("MDLUOPC".equals(mdmm)){
			if(StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(userPasswd)){
				ValidateUser base =  new  ValidateUser();		
				System.out.println("----------------保存成功----------------------");
			}
		}		
	}
	
	

	
	
}
