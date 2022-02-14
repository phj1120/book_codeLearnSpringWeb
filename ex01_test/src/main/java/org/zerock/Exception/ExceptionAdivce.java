package org.zerock.Exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class ExceptionAdivce {

	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {
		log.error(ex.getMessage());
		log.error(model);
		return "error-page";
	}
}
