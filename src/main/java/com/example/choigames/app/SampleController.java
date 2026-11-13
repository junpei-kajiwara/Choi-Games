package com.example.choigames.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SampleController {
	
	@GetMapping("/menu")
	public String index() {
		return "Menu";
	}
}
