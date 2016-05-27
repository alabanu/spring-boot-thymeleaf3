package com.github.dtrunk90.sbt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@RequestMapping
	public String root() {
		return "redirect:index.html";
	}

	@RequestMapping("index.html")
	public String index(ModelMap model) {
		model.addAttribute("hello", "world");
		return "index";
	}
}