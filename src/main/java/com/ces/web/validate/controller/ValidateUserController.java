/*
 * Powered By CES
 */

package com.ces.web.validate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ces.web.validate.service.ValidateUserManager;



/**
 * @author CES
 * @version 1.0
 * @since 1.0
 */
@Controller
public class ValidateUserController {
	@Autowired
	private ValidateUserManager validateUserManager;

	public void addUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
	}

	
	public void validate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		
	}
	
	
}
