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
	
	private BoardMapper mapper;
	
	@Override
	public void create(BoardVO board) {
		log.info("[create] :"+board);
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO read(Long bno) {
		log.info("[read] : "+bno);
		return mapper.read(bno);
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("[getList]");
		return mapper.getList(cri);
	}

	@Override
	public boolean update(BoardVO board) {
		log.info("[update]");
		return mapper.update(board) == 1;
	}

	@Override
	public boolean delete(Long bno) {
		log.info("[delete]");
		return mapper.delete(bno) == 1;
	}

	@Override
	public int getTotalContent(Criteria cri) {
		log.info("[getTotalContent]");
		return mapper.getTotalContent(cri);
	}

}
