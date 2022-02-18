package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Setter(onMethod_=@Autowired)
	private BoardMapper mapper;
	
	@Test
	public void readTest() {
		long bno = 60l;
		BoardVO board = mapper.read(bno);
		log.info("[getBoard] : " +board);
	}
	
	@Test
	public void testGetList() {
		log.info("[testGetList]");
		mapper.getList().forEach(board -> log.info(board));
//		List<BoardVO> board = boardMapper.getList();
//		for (BoardVO boardVO : board) {
//			log.info("[getBoardTest] : " +boardVO);
	}
	
	@Test
	public void insertTest() {
		BoardVO board = new BoardVO();
		board.setTitle("새 타이틀");
		board.setContent("새 글");
		board.setWriter("hj");
		mapper.insert(board);
	}
	
	@Test
	public void insertSelectKeyTest() {
		BoardVO board = new BoardVO();
		board.setTitle("새 타이틀");
		board.setContent("새 글");
		board.setWriter("hj");
		mapper.insertSelectKey(board);

		log.info(board.getBno());
	}

	@Test
	public void updateTest() {
		long bno = 68;
		
		BoardVO board = new BoardVO();
		board.setBno(bno);
		board.setTitle("수정 타이틀");
		board.setContent("수정 글");
		board.setWriter("phj");
		int cnt = mapper.update(board);
		log.info("[updateTest] : "+cnt);
	}
	
	@Test
	public void deleteTest() {
		long bno = 70;
		int cnt = mapper.delete(bno);
		log.info("[deleteTest] : "+cnt);
	}

}
