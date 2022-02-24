package org.zerock.domain;

import lombok.Data;

@Data
public class PageDTO {

	private int startPage;
	private int endPage;

	private boolean next;
	private boolean prev;
	private Criteria cri; // pageNum, amount
	
	public PageDTO(Criteria cri, int totalCount) {
		this.cri = cri;
//		endPage = ((cri.getPageNum()/10)+1)*10; 10 에서 문제 발생 올림 해줘야함(ceil)
		endPage = (int)Math.ceil(cri.getPageNum()/10.0)* 10;
		
		startPage = endPage - 9;
		int realEndPage = (int)Math.ceil(totalCount/cri.getAmount())+1;
		
		if(endPage > realEndPage ) {
			this.endPage = realEndPage;
		}
		
		prev = startPage > 1;
		next = endPage < realEndPage;
	}
}
