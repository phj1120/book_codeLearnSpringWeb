package org.zerock.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class DomainTests {
	
	@Test
	public void pageDTOTest() {
		
		Criteria cri = new Criteria();
		cri.setAmount(10); // 페이지 당 10개 씩 
		cri.setPageNum(12); // 현재 2페이지 (11 ~ 20) 번 째 글 확인
		int total = 114; // 총 114 개의 글
		
		PageDTO page = new PageDTO(cri, total);
		log.info("");
		log.info(page);
		log.info("");
		
		Criteria cri2 = new Criteria();
		cri2.setAmount(10); // 페이지 당 10개 씩 
		cri2.setPageNum(1); // 현재 2페이지 (11 ~ 20) 번 째 글 확인
		int total2 = 114; // 총 114 개의 글
		
		PageDTO page2 = new PageDTO(cri2, total2);
		log.info("");
		log.info(page2);
		log.info("");
		
	}
}
