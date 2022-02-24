package org.zerock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
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
	public void list(@ModelAttribute("cri") Criteria cri, Model model) {
		List<BoardVO> list = service.getList(cri);
		int totalContent = service.getTotalContent(cri);
		model.addAttribute("pageMaker", new PageDTO(cri, totalContent));
		log.info(new PageDTO(cri, totalContent));
		model.addAttribute("list", list);
		log.info("[list]");
	}
	
	@PostMapping("/register")
	public String registerPost(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		service.create(board);
		rttr.addFlashAttribute("result", board.getBno());
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		log.info("[registerPost]");
		return "redirect:/board/list";
	}
	
	@GetMapping("/register")
	public void registerGet() {
		log.info("[registerGet]");
	}
	
	@GetMapping({"/get", "/modify"})
	public void read(long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		model.addAttribute("board", service.read(bno));
		
		log.info("[get or modify]");
	}
	
	@PostMapping("remove")
	public String remove(long bno, Criteria cri, RedirectAttributes rttr) {
		if(service.delete(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		log.info("[remove]");
		return "redirect:/board/list";
	}
	
	@PostMapping("modify")
	public String modify(BoardVO board, Criteria cri, RedirectAttributes rttr) {
		if(service.update(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		log.info("[modify]");
		return "redirect:/board/list";
	}
	
	@GetMapping("test") // http://localhost/board/test
	public void test(Model m) {
		
	}
	
	@GetMapping("result") 
	public void testResultGet(Criteria cri, Model m) {
		
	}
	
	@PostMapping("result")
	public void testResultPost(Criteria cri, Model m) {
		// 객체가 참조형인 경우 @ModelAttribute 자동으로 붙고
		// Model 에 객체 이름에서 앞글자 소문자로 바꾼 이름으로 저장 됨
		// 위의 경우 criteria
	}
	
}
