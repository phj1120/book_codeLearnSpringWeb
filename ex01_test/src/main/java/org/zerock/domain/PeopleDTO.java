package org.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PeopleDTO {
	private String name;
	private int age;
//	@DateTimeFormat(pattern="yyyy-mm-dd")
	private Date birth;
}
