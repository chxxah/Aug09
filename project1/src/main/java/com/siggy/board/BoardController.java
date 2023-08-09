package com.siggy.board;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.siggy.util.Util;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BoardController {
	// user -> Controller -> Service -> DAO -> mybatis -> DB

	// AutoWired 말고 resource로 연결zz
	@Resource(name = "boardService")
	// 자바가 이름으로 연결해줍니다
	private BoardService boardService;

	@Autowired
	private Util util;

	@GetMapping("/board")
	public String board(@RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo, Model model) {
		// 서비스에서 값 가져오기
		// boardService.boardList(); //한줄로 줄이기

		// 페이지네이션인포 -> 값 넣고 -> DB -> jsp
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageNo); // 현재 페이지 번호
		paginationInfo.setRecordCountPerPage(10); // 한 페이지에 게시되는 게시물 건수
		paginationInfo.setPageSize(10); // 페이징 리스트의 사이즈
		// 전체 글수 가져오는 명령문장
		int totalCount = boardService.totalCount();
		paginationInfo.setTotalRecordCount(totalCount); // 전체 게시물 건 수

		int firstRecordIndex = paginationInfo.getFirstRecordIndex(); // 시작 위치
		int recordCountPerPage = paginationInfo.getRecordCountPerPage(); // 페이지당 몇개?

		// System.out.println(firstRecordIndex);
		// System.out.println(recordCountPerPage);
		// System.out.println(pageNo);
		// System.out.println(totalCount);

		PageDTO page = new PageDTO();
		page.setFirstRecordIndex(firstRecordIndex);
		page.setRecordCountPerPage(recordCountPerPage);

		// 보드 서비스 수정합니다.
		List<BoardDTO> list = boardService.boardList(page);

		model.addAttribute("list", list);
		model.addAttribute("paginationInfo", paginationInfo);
		return "board";
	}

	// http://localhost:8080/pro1/detail?bno=105
	// 파라미터로 들어오는값 잡기
	@GetMapping("/detail") // model은 jsp에 값을 붙이기 위해서 넣었습니다
	public String detail(HttpServletRequest request, Model model) {
		// String bno = request.getParameter("bno");
		int bno = util.strToInt(request.getParameter("bno"));

		// bno에 요청하는 값이 있습니다. 이 값을 db까지 보내겠습니다.
		// System.out.println("bno" + bno);
		BoardDTO dto = new BoardDTO();
		dto.setBno(bno);

		BoardDTO result = boardService.detail(dto);
		
		//System.out.println(result.getCommentcount());
		if (result.getCommentcount() >0) {
			//데이터 베이스에 물어봐서 jsp로 보냅니다
			List<Map<String, Object>> commentsList = boardService.commentsList(bno);
			model.addAttribute("commentsList", commentsList);
		}
		model.addAttribute("dto", result);

		return "detail";
	}

	@GetMapping("/write")
	public String write(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("mname") != null) {

			return "write";
		} else {
			return "redirect:/login"; // 슬러시 넣어주세요.

		}
	}

	@PostMapping("/write")
	public String write2(HttpServletRequest request) {
		// 사용자가 입력한 데이터 변수에 담기
		// Service -> DAO -> mybatis -> DB로 보내서 저장하기
		// System.out.println(request.getParameter("title"));
		// System.out.println(request.getParameter("content"));
		HttpSession session = request.getSession();
		if (session.getAttribute("mid") != null) {
			// 로그인했습니다 = 아래 로직을 여기로 가져오세요

			BoardDTO dto = new BoardDTO();

			dto.setBtitle(request.getParameter("title"));
			dto.setBcontent(util.getIp() + request.getParameter("content"));
			dto.setBip(util.getIp()); // 얻어온 ip도 저장해서 데이터 베이스로 보내겠습니다

			dto.setM_id((String) session.getAttribute("mid"));
			dto.setM_name((String) session.getAttribute("mname"));
			dto.setUuid(UUID.randomUUID().toString());
			System.out.println(dto.getUuid());
			System.out.println(dto.getUuid().length());

			boardService.write(dto);

			return "redirect:board";

		} else {
			return "redirect:/login";
		}
	}

	@GetMapping("/delete")
	public String delete(@RequestParam(value = "bno", required = false, defaultValue = "0") int bno,
			HttpSession session) {
		// HttpServletRequest의 getParameter();합친거
		// 로그인 여부 확인
		// System.out.println(session.getAttribute("mid"));
		// dto
		if (session.getAttribute("mid") != null) {

			BoardDTO dto = new BoardDTO();
			dto.setBno(bno);
			dto.setM_id((String) session.getAttribute("mid"));
			// 추후 로그인 하면 사용자의 정보도 담아서 보냅니다

			boardService.delete(dto);
			return "redirect:board";
		} else {
			return "redirect:/login";
		}

	}

	@GetMapping("/edit")
	public ModelAndView edit(HttpServletRequest request) {
		HttpSession session = request.getSession();

		// 데이터 베이스에 bno를 보내서 dto를 얻어옵니다
		// mv에 실어 보냅니다

		ModelAndView mv = new ModelAndView(); // edit.jsp

		// 로그인 하지 않으면 로그인 화면으로 던져주세요
		// if문으로 만들어주세요
		if (session.getAttribute("mid") != null) {
			BoardDTO dto = new BoardDTO();

			// dto를 하나 만들어서 거기에 담겠습니다 = bno, mid
			dto.setBno(util.strToInt(request.getParameter("bno")));
			dto.setM_id((String) session.getAttribute("mid"));

//		BoardDTO dto = boardService.detail(util.strToInt(request.getParameter("bno")));

			BoardDTO result = boardService.detail(dto);
			if (result != null) {
				mv.addObject("dto", result);
				mv.setViewName("edit");

			} else { // 다른사람글이라면 null입니다. 경고창으로 이동합니다.
				mv.setViewName("warning");
			}

		} else {
			mv.setViewName("redirect:/login");
		}
		return mv;

	}

	@PostMapping("/edit")
	public String edit(BoardDTO dto) {
		// System.out.println("map : " + map);
		boardService.edit(dto);

		return "redirect:detail?bno=" + dto.getBno();

	}
	
	@GetMapping("/cdel") //bno,cno
	public String cdel(@RequestParam Map<String, Object> map, HttpSession session) {
		//로그인 여부 검사 
		if (session.getAttribute("mid") != null) {
			//값이 들어왔는지 여부 검사
			System.out.println(map.get("bno"));
			System.out.println(map.get("cno"));
			System.out.println(map.get("cno").equals(""));
			if (map.get("bno") != null && map.get("cno") != null &&
					!(map.get("bno").equals(""))  &&  !(map.get("cno").equals("")) && 
					util.isNum(map.get("bno")) && util.isNum(map.get("cno")) ) {
				
				System.out.println("hello");
				map.put("mid", session.getAttribute("mid"));
			int result = boardService.cdel(map);
			System.out.println("삭제 결과" +  result);
			}
			
			
		}
		return "redirect:/detail?bno="+map.get("bno");
	}
	
	 
	   @PostMapping("/cedit")
	   public String ceidt(@RequestParam Map<String, Object> map, HttpSession session) {
	      if (session.getAttribute("mid") != null) {
	         if (map.get("bno") != null && !(map.get("bno").equals("")) && map.containsKey("cno") && !(map.get("cno").equals(""))) {
	            map.put("mid", session.getAttribute("mid"));
	            System.out.println(map);
	            int result = boardService.cedit(map);
	            
	            return "redirect:/detail?bno=" + map.get("bno");            
	         } else {
	            return "redirect:/board";
	         }         
	      } else {
	         return "redirect:/login";
	      }
	      
	   }

	

}
