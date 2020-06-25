package com.cafe24.memory.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/implement")
public class ImplementController {
	
	@PostMapping("/implementList")
	public String implement(Model model) {
		
		return "implement/implementList";
	}
	
	@GetMapping("/implementList")
	public String implement() {
		
		return "implement/implementList";
	}
	
	@GetMapping("/implementUpdate")
	public String implementUpdate() {
		
		return "implement/implementUpdate";
	}
	
	@GetMapping("implementInsert")
	public String implementInsert() {
		
		return "implement/implementInsert";
	}
	
}
