package com.leave.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/")
public class CommonController {

	public String index()
	{
		return "index";
	}
	
	@RequestMapping(path = "/members", method = RequestMethod.GET)
	public String Members()
	{
		return "members";
	}
}
