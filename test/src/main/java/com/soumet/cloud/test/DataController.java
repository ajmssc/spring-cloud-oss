package com.soumet.cloud.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Home page controller.
 * 
 * @author Paul Chapman
 */
@Controller
public class DataController {

	@RequestMapping("/")
	@ResponseBody
	public String home(HttpServletRequest request, HttpServletResponse response) {
		return this + "::home";
	}

	@RequestMapping("/1")
	@ResponseBody
	public String home1(HttpServletRequest request, HttpServletResponse response) {
		return this + "::home1";
	}

	@RequestMapping("/2")
	@ResponseBody
	public String home2() {
		return this + "::home2";
	}

}
