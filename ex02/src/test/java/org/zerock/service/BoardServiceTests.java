package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	// test에서는 @AllArgsConstruct로 자동 주입하려 해봤는데 안되네
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	
	// 서비스가 존재하는지 확인
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("새 타이틀");
		board.setContent("새 글");
		board.setWriter("phj");
		
		service.regiseter(board);
		log.info("[BoardServiceTests.testRegister] 게시판의 번호 : "+board.getBno());
	}
	
	@Test
	public void testGet() {
		log.info("[BoardServiceTests.testGet] "+ service.get(162L));
	}

	@Test
	public void testGetList() {
		service.getList().forEach(board -> log.info("[BoardServiceTests.testGetList] : "+board));
	}
	
	@Test
	public void testModify() {
		BoardVO board = service.get(88L);
		
		if(board == null) {
			return;
		}
		
		board.setTitle("제목 수정");
		log.info("[BoardServiceTests.testModify] 수정 여부 : " + service.modify(board));
	}
	
	@Test
	public void testRemove() {
		log.info("[BoardServiceTests.testModify] 삭제 여부 : " + service.remove(90L));
	}
}
