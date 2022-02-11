package org.zerock.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.mapper.StudentMapper;
import org.zerock.mapper.TimeMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TimeMapperTest {
	
	@Setter(onMethod_=@Autowired)
	private TimeMapper timeMapper;
	
	@Setter(onMethod_=@Autowired)
	private StudentMapper studentMapper;
	
//	@Test
//	public void testGetTime() {
//		log.info("getTime1");
//		log.info(timeMapper.getClass().getName());
//		log.info(timeMapper.getTime());
//	}

//	@Test
//	public void testGetTime2() {
//		log.info("getTime2");
//		log.info(timeMapper.getTime2());
//	}

	
//	안되네... DB에서 Commit 안해서 그런거였음

//	@Test
//	public void testGetStuedent1() {
//		log.info("getStudent1");
//		log.info(studentMapper.getStudent1());
//	}

// xml 로 읽어오는건데 아직도 안됨	
	@Test
	public void testGetStuedent2() {
		log.info("getStudent2");
		log.info(studentMapper.getClass().getName());
		log.info(studentMapper.getStudent2());
		log.info(studentMapper.getStudent2());
	}
	
}
