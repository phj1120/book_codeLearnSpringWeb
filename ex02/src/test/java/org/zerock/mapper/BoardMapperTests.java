package org.zerock.mapper;

import java.util.List;

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
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Setter(onMethod_=@Autowired)
	private BoardMapper mapper;

//	@Test
//	public void testGetList() {
//		mapper.getList().forEach(board -> log.info(board));
//	}
	
	@Test
	public void testGetList() {
		Criteria criteria = new Criteria();
		criteria.setPageNum(3);
		criteria.setAmount(10);
		List<BoardVO> listWithPaging = mapper.getList(criteria);
		log.info("[testGetListWithPaging]");
		listWithPaging.forEach(board -> log.info(board));
	}
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newble");
		
		mapper.insert(board);
		log.info("[testInsert] bno : "+board.getBno());		
		log.info(board);
	}

	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 select Key");
		board.setContent("새로 작성하는 내용 select Key");
		board.setWriter("newble");
		
		log.info("[testInsertSelectKey] ori bno : "+board.getBno());		
		mapper.insertSelectKey(board);
		log.info("[testInsertSelectKey] after bno : "+board.getBno());		
		log.info(board);
	}
	
	@Test
	public void testRead() {
		long bno = 34L;
		BoardVO board = mapper.read(bno);
		log.info("[tetRead] : " + board);
	}

	@Test
	public void testDelete() {
		long bno = 35L;
		log.info("[testDelete] 실행 전 : "+mapper.read(bno));
		log.info("[testDelete] count : "+mapper.delete(bno));
		log.info("[testDelete] 실행 후 : "+mapper.read(bno));
	}	
	
	@Test
	public void testUpdate() {
		long bno = 31L;
		log.info("[testUpdate] 실행 전 : "+mapper.read(bno));
		
		BoardVO board = new BoardVO();
		board.setBno(bno);
		board.setTitle("수정 한 글");
		board.setContent("수정 한 내용");
		board.setWriter("hj");

		log.info("[testUpdate] count : "+mapper.update(board));
		log.info("[testUpdate] 실행 후 : "+mapper.read(bno));
	}
	
	@Test
	public void testGetTotalCount() {
		Criteria cri = new Criteria();
		log.info("[testGetTotalCount] : "+mapper.getTotalCount(cri));
		
	}

}	

