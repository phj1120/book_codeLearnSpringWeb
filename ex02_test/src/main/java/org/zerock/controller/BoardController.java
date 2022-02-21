package org.zerock.controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {

	@Setter(onMethod_=@Autowired)
	private BoardService service;
	
	@GetMapping("/list")
	public void list(@RequestParam(required=false, defaultValue="asc") String sort, Model model) {
		List<BoardVO> list = service.getList();
		
		// bno 로 정렬
		list.sort(new Comparator<BoardVO>() {
			@Override
			public int compare(BoardVO arg0, BoardVO arg1) {
			      long bno0 = arg0.getBno();
	              long bno1 = arg1.getBno();
	              
	              if(bno0 == bno1) return 0;
	              // 오름 차순 정렬
	              else if("asc".equals(sort) && bno0 > bno1) {
		              return 1;
	              // 내림 차순 정렬
	              }else if ("desc".equals(sort) && bno1 > bno0) {
		              return 1;
	              }
	              else return -1;
			}
		});
		
		model.addAttribute("sort", sort);
		model.addAttribute("list", list);
		log.info("[list]");
	}
	
	@PostMapping("/register")
	public String registerPost(BoardVO board) {
		service.create(board);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/register")
	public void registerGet() {
		
	}
	
	@GetMapping({"/get", "/modify"})
	public void read(long bno, Model model) {
		model.addAttribute("board", service.read(bno));
	}
	
	@PostMapping("remove")
	public String remove(long bno) {
		service.delete(bno);
		
		return "redirect:/board/list";
	}
	
	@PostMapping("modify")
	public String modify(BoardVO board) {
		service.update(board);
		
		return "redirect:/board/list";
	}
	
}
