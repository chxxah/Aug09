package com.siggy.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//첫화면 로딩 :index.jsp호풀
@Controller
public class IndexController {

	@GetMapping(value = {"/", "/index"})
	public String index() {
		return "index"; //데이터 붙임 없이 index.jsp페이지만 보여줍니다
	}
	
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	//임시
	@GetMapping("/board2")
	public String menu2() {
		return "board2";
	}
	
	@GetMapping("/mooni")
	public String mooni() {
		return "mooni";
	}
	@GetMapping("/notice")
	public String notice() {
		return "notice";
	}
}
