package org.zerock.domain;

import lombok.Data;

@Data
public class SampleDTO {
	private String name;
	private int age;
	
	
	public String setName(String name) {
		System.out.println("[SampleDTO] name : "+this.name+" -> "+name);
		this.name = name;
		return name;
	}
	
	public int setAge(int age) {
		System.out.println("[SampleDTO] age : "+this.age+" -> "+age);
		this.age = age;
		return age;
	}
}
