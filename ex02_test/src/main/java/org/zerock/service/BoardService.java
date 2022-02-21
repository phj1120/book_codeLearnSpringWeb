package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardService {

	public void create(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public List<BoardVO> getList();
	
	public boolean update(BoardVO board);
	
	public boolean delete(Long bno);
	
}
