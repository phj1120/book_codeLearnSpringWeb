package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor // 생성자를 이용한 자동 주입
public class BoardController {
	
//	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
//	@GetMapping("/list") // http://localhost/board/list
//	public void list(Model m) {
//		log.info("list");
//		m.addAttribute("list", service.getList());
//	}

	@GetMapping("/list") // http://localhost/board/list
	public String list(Criteria criteria, Model m) {
		log.info("list");
		m.addAttribute("list", service.getList(criteria));
		m.addAttribute("pageMaker", new PageDTO(criteria, 114));
		return "/board/list";
	}
	
	@GetMapping("/register") // http://localhost/board/register
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("[BoardController.register] : " + board);
		service.regiseter(board);
		rttr.addFlashAttribute("result", board.getBno());
		rttr.addFlashAttribute("pageNum", cri.getPageNum());
		rttr.addFlashAttribute("amount", cri.getAmount());
		return "redirect:/board/list";
	}
	
	@GetMapping({"/get", "/modify"})
	public void read(@RequestParam("bno") long bno, @ModelAttribute("cri") Criteria cri, Model m) {
		log.info("[get or modify]");
		m.addAttribute("board", service.get(bno));
	}
	
	@PostMapping("remove")
	public String remove(long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr, Model model) {
		log.info("[remove]");
		if(service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addFlashAttribute("pageNum", cri.getPageNum());
		rttr.addFlashAttribute("amount", cri.getAmount());
		return "redirect:/board/list";
	}
	
	@PostMapping("modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("[modify]");
		if(service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addFlashAttribute("pageNum", cri.getPageNum());
		rttr.addFlashAttribute("amount", cri.getAmount());
		return "redirect:/board/list?pageNum="+cri.getPageNum()+"&amount="+cri.getAmount();
	}
	
}
