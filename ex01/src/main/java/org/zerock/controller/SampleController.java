package org.zerock.controller;

import java.io.File;
import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
//	@InitBinder
//	public void initBinder(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
//	}
	
//	@RequestMapping("")
	@RequestMapping(value="", method= {RequestMethod.GET, RequestMethod.POST})
	public void basic() {
		log.info("[basic]");
	}
	
//	@RequestMapping(value="onlyGet", method=RequestMethod.GET)
	@GetMapping("onlyGet")
	public void basicOnlyGet() {
		log.info("basic [GET]");
	}
	
//	@RequestMapping(value="onlyPost", method=RequestMethod.POST)
	@PostMapping("onlyPost")
	public void basicOnlyPost() {
		log.info("basic [POST]");
	}
	
	@RequestMapping(value="ex01", method=RequestMethod.GET) // http://localhost:8080/sample/ex01?name=hj&age=25
	public String ex01(SampleDTO sampleDTO) {
		log.info("[ex01] sampleDTO : "+sampleDTO);
		return "ex01";
	}
	
	@RequestMapping("ex02") // http://localhost:8080/sample/ex02?name=hj&age=25
	public String ex02(@RequestParam("name") String name, @RequestParam("age") int age) { 
		log.info("[ex02] name = "+name);
		log.info("[ex02] age = "+age);
		
		return "ex02";
	}
	
	@RequestMapping("ex02List") // http://localhost:8080/sample/ex02List?ids=p&ids=h&ids=j
	public String ex02List(@RequestParam("ids")ArrayList<String> ids) { 
		log.info("[ex02List] ids = "+ids);
		
		return "ex02List";
	}
	
	@RequestMapping("ex02Array") //http://localhost:8080/sample/ex02Array?ids=p&ids=h&ids=j
	public String ex02Array(@RequestParam("ids")String[] ids) { 
		log.info("[ex02Array] ids = "+ids);
		
		return "ex02Array";
	}
	
	@RequestMapping("ex02Bean") // http://localhost:8080/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B2%5D.name=bbb
	public String ex02Bean(SampleDTOList list) {
		log.info("[ex02Bean] lists = "+list);
		
		return "ex02Bean";
	}	
	
//	@initBinder 주석 후 @DateTimeFormat 지정
//	http://localhost:8080/sample/ex03?title=test&birth=1998/11/20
	@RequestMapping("ex03") // http://localhost:8080/sample/ex03?title=test&dueDate=2022-02-13
	public String ex03(TodoDTO todoDTO) {
		log.info("[ex03] "+todoDTO);
		return "ex03";
	}
	
	@GetMapping("ex04") // http://localhost:8080/sample/ex04?title=test&birth=1998-11-20&page=2
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		log.info("[ex04] dto : " + dto);
		log.info("[ex04] page : " + page);
		
		return "sample/ex04";
	}

	@GetMapping("ex05") // http://localhost:8080/sample/ex05
	public void ex05() {
		log.info("[ex05] ");
	}
	
	@GetMapping("ex06") // http://localhost:8080/sample/ex06
	public @ResponseBody SampleDTO ex06() {
		log.info("[ex06]");
		SampleDTO sampleDTO = new SampleDTO();
		sampleDTO.setAge(25);
		sampleDTO.setName("phj");
		return sampleDTO;
	}
	
	@GetMapping("ex07") // http://localhost:8080/sample/ex07
	public ResponseEntity<String> ex07(){
		log.info("[ex07]");
		String msg = "{\"name\": \"홍길동\"}";
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<>(msg, header, HttpStatus.OK);
	}
	
	@GetMapping("exUpload")
	public void exUpload() {
		log.info("[exUpload]");
	}
	
	@PostMapping("exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		log.info("[exUploadPost]");
		String uploadFolder = "C:/upload/tmp";
		files.forEach(file -> {
			log.info("-----------------------------");
			log.info("name : "+file.getOriginalFilename());
			log.info("size : "+file.getSize());
			File saveFile = new File(uploadFolder,file.getOriginalFilename());
			
			try {
				file.transferTo(saveFile);
			}catch (Exception e) {
				log.error(e.getMessage());
			}
		});
	}
}
