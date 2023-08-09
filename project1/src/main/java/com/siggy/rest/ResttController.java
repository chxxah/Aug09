package com.siggy.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.siggy.board.BoardService;
import com.siggy.login.LoginService;
import com.siggy.util.Util;

@RestController
public class ResttController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private Util util;
	
	@Autowired
	private BoardService boardService;

	// 아이디 중복검사 2023-08-02
	@PostMapping("/checkID")
	public String checkID(@RequestParam("id") String id) {
		System.out.println("id : " + id);
		int result = loginService.checkID(id);
		JSONObject json = new JSONObject();
		json.put("result", result);

		return json.toString();
	}

	@PostMapping("/checkID2")
	public String checkID2(@RequestParam("id") String id) {
		int result = loginService.checkID(id);
		return result + "";
	}

	// boardList2
	@GetMapping(value = "/boardList2", produces = "application/json; charset=UTF-8")
	public String boardList2(@RequestParam("pageNo") int pageNo) {
		// System.out.println("jq가 보내주는 : " + pageNo);

		List<Map<String, Object>> list = loginService.boardList2((pageNo - 1) * 10);
		JSONObject json = new JSONObject();
		JSONArray arr = new JSONArray(list);
		json.put("totalCount", loginService.totalCount());
		json.put("pageNo", pageNo);
		json.put("list", arr);
		// System.out.println(json.toString());

		return json.toString();
	}

	/*
	 * boardList2 = { totalCount : 128, pageNo:1, list : [ {bno:1, btitle:....}
	 * {bno:1, btitle:....}, {bno:1, btitle:....}, {bno:1, btitle:....}, {bno:1,
	 * btitle:....} ] }
	 * 
	 * 
	 * 객체 : {키 : 값, 이름 : 값,..............}
	 * 
	 */

	@PostMapping("/cdelR")
	public String cdelR(@RequestParam Map<String, Object> map, HttpSession session) {
		int result = 0;
		JSONObject json = new JSONObject();
		
		if (session.getAttribute("mid") != null) {

			if (map.get("bno") != null && map.get("cno") != null && !(map.get("bno").equals(""))
					&& !(map.get("cno").equals("")) && util.isNum(map.get("bno")) && util.isNum(map.get("cno"))) {
				
				
				System.out.println("hello");
				map.put("mid", session.getAttribute("mid"));
				result = boardService.cdel(map);
				System.out.println("삭제 결과" + result);
				
				json.put("result", result);
			}

		}
		
		return json.toString();
	}

}
