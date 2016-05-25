package com.github.dtrunk90.sbt.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	@Autowired
	private MessageSource messageSource;

	@RequestMapping
	public String root() {
		return "redirect:index.html";
	}

	@RequestMapping("index.html")
	public String index(ModelMap model) {
		System.out.println("##### " + messageSource);
		System.out.println("#####" + messageSource.getMessage("hello", null, Locale.getDefault()));
		model.addAttribute("hello", "world");
		return "index";
	}
}