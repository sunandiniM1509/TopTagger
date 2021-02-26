package com.knowledgedb.toptagger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		
		return "fancy-login";
		
	}

	// @GetMapping("/home")
	// public String showHome() {

	// 	return "home";
	// }

	@GetMapping("/home")
	public String showWelcomePage() {

		return "home";
	}
	@GetMapping("/admin")
	public String showAdminPage() {

		return "admin";
	}

	
}









