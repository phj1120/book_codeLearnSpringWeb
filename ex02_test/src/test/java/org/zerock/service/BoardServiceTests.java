package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/park/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_=@Autowired)
	private BoardService service;

	
	// 서비스가 존재하는지 확인
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	
	@Test
	public void createTest() {
		BoardVO board =new BoardVO();
		board.setContent("새글");
		board.setTitle("새 타이틀");
		board.setWriter("HJ");
		service.create(board);
		
		log.info("[createTest]");
	}
	
	
	@Test
	public void readTest() {
		long bno = 161L;
		service.read(bno);
		
		log.info("[getTest]");
	}
	
	@Test
	public void readListTest() {
		service.getList(new Criteria(2, 10));
		log.info("[readListTest]");
	}
	
	@Test
	public void updateTest() {
		BoardVO board =new BoardVO();
		board.setContent("수정 글");
		board.setTitle("수정 타이틀");
		board.setWriter("PHJ");
		service.update(board);
		
		log.info("[updateTest]");
	}
	
	@Test
	public void deleteTest() {
		long bno = 161L;
		service.delete(bno);

		log.info("[deleteTest]");
	}
	
	
}
