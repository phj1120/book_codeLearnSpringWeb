package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
//	주입할 파라미터에 대한 생성자 하나만 있을 경우 자동 주입
//	@Setter(onMethod_=@Autowired)
	private BoardMapper mapper;

	@Override
	public void regiseter(BoardVO board) {
		log.info("[BoardServiceImpl.register] "+board);
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("[BoardServiceImpl.get]");
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("[BoardServiceImpl.modify]");
		return mapper.update(board)==1; 
	}

	@Override
	public boolean remove(Long bno) {
		log.info("[BoardServiceImpl.remove]");
		// 삭제시 modal 창 안떠서 왜 그런지 한참 찾았음
		// return 을 0 으로 해서 오류 발생
		return mapper.delete(bno) == 1;
	}

//	@Override
//	public List<BoardVO> getList() {
//		log.info("[BoardServiceImpl.getList]");
//		return mapper.getList();
//	}
	
	@Override
	public List<BoardVO> getList(Criteria criteria){
		log.info("[getList]");
		return mapper.getList(criteria);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		log.info("[getTotalCount]");
		return mapper.getTotalCount(cri);
	}
	
}
