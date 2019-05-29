package com.leave.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/")
public class CommonController {

	public String index()
	{
		return "index";
	}
}
