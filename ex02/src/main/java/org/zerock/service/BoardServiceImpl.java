package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
		return mapper.delete(bno) == 0;
	}

	@Override
	public List<BoardVO> getList() {
		log.info("[BoardServiceImpl.getList]");
		return mapper.getList();
	}
	
}
