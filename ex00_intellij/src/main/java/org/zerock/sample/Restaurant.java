package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Component
@Getter
public class Restaurant {
	
	@Setter(onMethod_ = @Autowired)
	private Chef chef;
}