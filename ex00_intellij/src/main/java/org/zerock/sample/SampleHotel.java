package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Component
@Getter
@AllArgsConstructor
public class SampleHotel {
	
	@Autowired
	private Chef chef;
	
	
	
}

//@Component
//@ToString
//@Getter
//public class SampleHotel {
//	
//	private Chef chef;
//	
//	public SampleHotel(Chef chef) {
//		this.chef = chef;
//	}
//}
