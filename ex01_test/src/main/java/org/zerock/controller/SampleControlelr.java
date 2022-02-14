package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.domain.PeopleDTO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("sample")
public class SampleControlelr {
	
	@InitBinder
	public void binder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@ExceptionHandler(Exception.class)
	public String exception(Exception ex, Model model) {
		model.addAttribute("exception", ex);
		return "error_page";
	}
	
	@RequestMapping("ex00")
	public String ex00(PeopleDTO peopleDTO) {
		System.out.println(peopleDTO);
		return "ex00";
	}
	
	@GetMapping("ex01")
	public String ex01(@ModelAttribute("name") String name, 
						@ModelAttribute("age") int age, 
						Date birth, Model model) {
		model.addAttribute("birth", birth);
		
		System.out.println("[model] : "+ model);
		System.out.println("[name] : "+ name);
		System.out.println("[age] : "+ age);
		System.out.println("[birth] : "+ birth);
		return "ex01";
	}
	
	@GetMapping("ex02")
	public void ex02(PeopleDTO peopleDTO) {
	}
	
	@RequestMapping(path="ex03", method=RequestMethod.GET)
	public String ex03(@ModelAttribute("dto") PeopleDTO peopleDTO) {
		return "ex03";
	}
	
	@RequestMapping(path="ex04", method=RequestMethod.GET)
	public ResponseEntity<String> ex04(PeopleDTO peopleDTO) {
		System.out.println(peopleDTO);
		return new ResponseEntity("peopleDTO", HttpStatus.OK);
	}
	
}
