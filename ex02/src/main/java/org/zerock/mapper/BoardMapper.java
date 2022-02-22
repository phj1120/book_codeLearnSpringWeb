package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {
//	@Select("SELECT * FROM tbl_board WHERE writer=newble")
	public List<BoardVO> getList();
	
//	@Insert("INSERT INTO tbl_board(bno, title, content, writer) VALUES (seq_board.nextval, 'test_title', 'test_content', 'test_writer')")
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int delete(Long bno);
	
	public int update(BoardVO board);
	
	public List<BoardVO> getList(Criteria criteria);
}
