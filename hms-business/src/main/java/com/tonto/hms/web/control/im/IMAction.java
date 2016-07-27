package com.tonto.hms.web.control.im;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/im")
public class IMAction {

	
	@RequestMapping("/view")
	public ModelAndView view()
	{
		return new ModelAndView("im/im");
	}
	
	
	
	
}
