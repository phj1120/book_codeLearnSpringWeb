package org.zerock.domain;

import lombok.Data;

@Data
public class PageDTO {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total;
	private Criteria cri;

	public PageDTO(Criteria cri, int total) {
		
		this.total = total;
		this.cri = cri;
		
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)*10);
		this.startPage = endPage - 9;
		
		int realEnd = (int)(Math.ceil((total*1.0)/cri.getAmount()));
		
		if (this.endPage > realEnd) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}
}
