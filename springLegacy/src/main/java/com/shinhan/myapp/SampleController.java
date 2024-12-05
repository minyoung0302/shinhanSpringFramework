package com.shinhan.myapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

	@RequestMapping("/test1")
	public String f1(Model dataStore) {
		dataStore.addAttribute("myname","minyoung");
		dataStore.addAttribute("score","99");
		return "test1";
	}
}
