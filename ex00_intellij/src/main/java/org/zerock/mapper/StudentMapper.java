package org.zerock.mapper;

import org.apache.ibatis.annotations.Select;

public interface StudentMapper {
	
	@Select("SELECT name FROM book_ex.student")
	public String getStudent1();
	
	public String getStudent2();
}
