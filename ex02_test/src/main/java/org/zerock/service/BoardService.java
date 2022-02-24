package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {

	public void create(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public List<BoardVO> getList(Criteria cri);
	
	public boolean update(BoardVO board);
	
	public boolean delete(Long bno);
	
	public int getTotalContent(Criteria cri);
}
