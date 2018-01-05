package cn.gov.web.system.user.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.gov.web.system.user.entity.PmsUser;
import cn.gov.web.system.user.service.IPmsUserService;

@Controller
public class UserController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private IPmsUserService pmsUserService;

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest req, PmsUser user) {
		log.info(user);
		System.out.println("11111");
		ModelAndView mv = new ModelAndView();
		PmsUser u = pmsUserService.test(user.getUsername(), user.getRemark());

		if (u != null) {

			req.getSession().setAttribute("user", u);
			mv.addObject("remark", u.getRemark());
			System.out.println(u.getRemark());
		}
//		org.apache.cxf.frontend.ServerFactoryBean
		mv.setViewName("index");
		return mv;
	}

}
