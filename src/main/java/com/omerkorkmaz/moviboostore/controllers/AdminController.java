package com.omerkorkmaz.moviboostore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController
{
	
	@RequestMapping("/adminhome")
	public String adminHome(Model model) {
		return "adminhome";
	}

}
